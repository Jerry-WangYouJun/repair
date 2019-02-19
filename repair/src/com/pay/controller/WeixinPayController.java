package com.pay.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.StringUtils;
import com.model.User;
import com.pay.config.WxPayConfig;
import com.pay.msgreply.XMLUtil;
import com.pay.util.CommonUtil;
import com.pay.util.OrderUtils;
import com.pay.util.RequestHandler;
import com.pay.util.Sha1Util;
import com.pay.util.WXAuthUtil;
import com.pay.util.WeixinPayUtil;
import com.service.UserService;

import net.sf.json.JSONObject;

/**
 * 微信支付Controller
 * @author Jeff Xu
 * @since 2016-08-11
 */

@Controller
@RequestMapping("/wx")
@SuppressWarnings("rawtypes")
public class WeixinPayController {
	@Autowired
	UserService userService ;
	
	
	private static String baseUrl = "http://suxiu110.cn";
	Map<String,String>  excuteResultMap = new HashMap<>();
	
	@RequestMapping("/menu")
	public void createMenu(){
		
	}
	
	@RequestMapping("/token")
	public void getToken(HttpServletResponse response, HttpServletRequest request ,  String signature,
			String timestamp, String nonce, String echostr) throws IOException {
		//接受微信服务器发送过来的XML形式的消息
        InputStream in=request.getInputStream();
        BufferedReader reader=new BufferedReader(new InputStreamReader(in,"UTF-8"));
        String sReqData="";
        String itemStr="";//作为输出字符串的临时串，用于判断是否读取完毕
        while((itemStr=reader.readLine())!=null){
            sReqData+=itemStr;
        }
        in.close();
        reader.close();

        System.out.println("收到消息："+sReqData);
       
  	}
	
	@RequestMapping("/index")
	public String act(HttpServletRequest request, HttpServletResponse response){
		//授权后要跳转的链接
		//邀约传web   活动传act   文章传article
		String backUri = baseUrl + "/wx/checkact"  ;
		//URLEncoder.encode 后可以在backUri 的url里面获取传递的所有参数
		backUri = URLEncoder.encode(backUri);
		//scope 参数视各自需求而定，这里用scope=snsapi_base 不弹出授权页面直接授权目的只获取统一支付接口的openid
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
				"appid=" + WxPayConfig.appid +
				"&redirect_uri=" +
				 backUri+
				"&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
		System.out.println("url:" + url);
		return "redirect:"+url;
	}

	
	@RequestMapping("/checkact")
	public String checkact(HttpServletRequest request, HttpServletResponse response) throws ClientProtocolException, IOException{
			        JSONObject userInfo   = null ;
			        try{
			        		userInfo = getUserInfo(request, response);
			        }catch(Exception e){
			        	System.out.println("获取用户信息失败或网络异常");
			          	 return "forward:/web/registerInit?openId=1" ;
			        }
		              String  openId = userInfo.getString("openid");
		        String token_user_focus    = WXAuthUtil.getAccessToken();
		        String infoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+token_user_focus
                        + "&openid="+ openId
                        + "&lang=zh_CN";
                System.out.println("infoUrl:"+infoUrl);
		        JSONObject jsonObject = WXAuthUtil.doGetJson(infoUrl);
		           if("0".equals(jsonObject.getString("subscribe"))){     
			        	   System.out.println("没有关注");
			        	   return "forward:/wx/focus.jsp";
		           }else{
			           return "forward:/web/wxlogin?openId="+ openId + "&&headImg=" + userInfo.getString("headimgurl") ;
		           }
		
	}
	
	public static JSONObject getUserInfo(HttpServletRequest request, HttpServletResponse response) throws ClientProtocolException, IOException{
		String code =request.getParameter("code");
	      //第二步：通过code换取网页授权access_token
	         String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+WxPayConfig.appid
	                + "&secret="+WxPayConfig.appsecret
	                + "&code="+code
	                + "&grant_type=authorization_code";

	        System.out.println("url:"+url);
	        JSONObject jsonObject = WXAuthUtil.doGetJson(url);
	        /*
	         { "access_token":"ACCESS_TOKEN",
	            "expires_in":7200,
	            "refresh_token":"REFRESH_TOKEN",
	            "openid":"OPENID",
	            "scope":"SCOPE" 
	           }
	         */
	        System.out.println(jsonObject);
	        String openid = jsonObject.getString("openid");
	        String access_token = jsonObject.getString("access_token");
	        String refresh_token = jsonObject.getString("refresh_token");
	        //第五步验证access_token是否失效；展示都不需要
	        String chickUrl="https://api.weixin.qq.com/sns/auth?access_token="+access_token+"&openid="+openid;

	        JSONObject chickuserInfo = WXAuthUtil.doGetJson(chickUrl);
	        System.out.println(chickuserInfo.toString());
	        if(!"0".equals(chickuserInfo.getString("errcode"))){
	            // 第三步：刷新access_token（如果需要）-----暂时没有使用,参考文档https://mp.weixin.qq.com/wiki，
	            String refreshTokenUrl="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+openid+"&grant_type=refresh_token&refresh_token="+refresh_token;

	            JSONObject refreshInfo = WXAuthUtil.doGetJson(chickUrl);
	            /*
	             * { "access_token":"ACCESS_TOKEN",
	                "expires_in":7200,
	                "refresh_token":"REFRESH_TOKEN",
	                "openid":"OPENID",
	                "scope":"SCOPE" }
	             */
	            System.out.println(refreshInfo.toString());
	            access_token=refreshInfo.getString("access_token");
	        }
	               
	               // 第四步：拉取用户信息(需scope为 snsapi_userinfo)
	               String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token
	                        + "&openid="+openid
	                        + "&lang=zh_CN";
	                System.out.println("infoUrl:"+infoUrl);
	                JSONObject userInfo = WXAuthUtil.doGetJson(infoUrl);
	                /*
	                 {    "openid":" OPENID",
	                    " nickname": NICKNAME,
	                    "sex":"1",
	                    "province":"PROVINCE"
	                    "city":"CITY",
	                    "country":"COUNTRY",
	                    "headimgurl":    "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
	                    "privilege":[ "PRIVILEGE1" "PRIVILEGE2"     ],
	                    "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
	                    }
	                 */
	                System.out.println("JSON-----"+userInfo.toString());
	                System.out.println("名字-----"+userInfo.getString("nickname"));
	                System.out.println("头像-----"+userInfo.getString("headimgurl"));
	                return userInfo;
	}
	
	/**
	 * 微信网页授权获取用户基本信息，先获取 code，跳转 url 通过 code 获取 openId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/userAuth")
	public String userAuth(HttpServletRequest request, HttpServletResponse response){
		try {
			String cardNumber = request.getParameter("cardNumber");
			String orderId = OrderUtils.genOrderNo(cardNumber);
			String totalFee = request.getParameter("totalFee");
			 totalFee = "0.01";
			System.out.println("in userAuth,orderId:" + orderId);
			
			//授权后要跳转的链接
			String backUri = baseUrl + "/wx/toPay";
			backUri = backUri + "?orderId=" + orderId+"&totalFee="+totalFee;
			//URLEncoder.encode 后可以在backUri 的url里面获取传递的所有参数
			backUri = URLEncoder.encode(backUri);
			//scope 参数视各自需求而定，这里用scope=snsapi_base 不弹出授权页面直接授权目的只获取统一支付接口的openid
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
					"appid=" + WxPayConfig.appid +
					"&redirect_uri=" +
					 backUri+
					"&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
			System.out.println("url:" + url);
			response.sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/toPay")
	public String toPay(HttpServletRequest request, HttpServletResponse response, Model model){
		try {
			String orderId = request.getParameter("orderId");
			System.out.println("in toPay,orderId:" + orderId);
			
			String totalFeeStr = request.getParameter("totalFee");
			Float totalFee = 0.0f;
			
			if(StringUtils.isNotEmpty(totalFeeStr)){
				totalFee = new Float(totalFeeStr);
			}
			//TODO 测试用代码 totalFee = 0.01f ;
			//网页授权后获取传递的参数
			//String userId = request.getParameter("userId"); 	
			String code = request.getParameter("code");
			System.out.println("code:"+code);
			if(code == null){
				  return null ;
			}
			//获取统一下单需要的openid
			String openId =getOpenId(code);
			
			
			//获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
			//随机数 
			//String nonce_str = "1add1a30ac87aa2db72f57a2375d8fec";
			String nonce_str = UUID.randomUUID().toString().replaceAll("-", "");
			//商品描述
			String body = orderId;
			//商户订单号
			String out_trade_no = orderId;
			//订单生成的机器 IP
			String spbill_create_ip = request.getRemoteAddr();
			//总金额
			//TODO
			Integer total_fee = Math.round(totalFee*100);
			//Integer total_fee = 1;
			
			//商户号
			//String mch_id = partner;
			//子商户号  非必输
			//String sub_mch_id="";
			//设备号   非必输
			//String device_info="";
			//附加数据
			//String attach = userId;
			//总金额以分为单位，不带小数点
			//int total_fee = intMoney;
			//订 单 生 成 时 间   非必输
			//String time_start ="";
			//订单失效时间      非必输
			//String time_expire = "";
			//商品标记   非必输
			//String goods_tag = "";
			//非必输
			//String product_id = "";
					
			//这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
			String notify_url = baseUrl + "/wx/notifyUrl";
			
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("appid", WxPayConfig.appid);
			packageParams.put("mch_id", WxPayConfig.partner);
			packageParams.put("nonce_str", nonce_str);
			packageParams.put("body", body);
			packageParams.put("out_trade_no", out_trade_no);
			packageParams.put("total_fee", total_fee+"");
			packageParams.put("spbill_create_ip", spbill_create_ip);
			packageParams.put("notify_url", notify_url);
			packageParams.put("trade_type", WxPayConfig.trade_type);  
			packageParams.put("openid", openId);  

			RequestHandler reqHandler = new RequestHandler(request, response);
			reqHandler.init(WxPayConfig.appid, WxPayConfig.appsecret, WxPayConfig.partnerkey);
			
			String sign = reqHandler.createSign(packageParams);
			System.out.println("sign:"+sign);
			String xml="<xml>"+
					"<appid>"+WxPayConfig.appid+"</appid>"+
					"<mch_id>"+WxPayConfig.partner+"</mch_id>"+
					"<nonce_str>"+nonce_str+"</nonce_str>"+
					"<sign>"+sign+"</sign>"+
					"<body><![CDATA["+body+"]]></body>"+
					"<out_trade_no>"+out_trade_no+"</out_trade_no>"+
					"<total_fee>"+total_fee+""+"</total_fee>"+
					"<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>"+
					"<notify_url>"+notify_url+"</notify_url>"+
					"<trade_type>"+WxPayConfig.trade_type+"</trade_type>"+
					"<openid>"+openId+"</openid>"+
					"</xml>";
			System.out.println("xml："+xml);
			
			String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
			String prepay_id="";
			try {
				prepay_id = WeixinPayUtil.getPayNo(createOrderURL, xml);
				System.out.println("prepay_id:" + prepay_id);
				if(prepay_id.equals("")){
					request.setAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");
					response.sendRedirect("error.jsp");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			SortedMap<String, String> finalpackage = new TreeMap<String, String>();
			String timestamp = Sha1Util.getTimeStamp();
			String packages = "prepay_id="+prepay_id;
			finalpackage.put("appId", WxPayConfig.appid);
			finalpackage.put("timeStamp", timestamp);
			finalpackage.put("nonceStr", nonce_str);
			finalpackage.put("package", packages);
			finalpackage.put("signType", WxPayConfig.signType);
			String finalsign = reqHandler.createSign(finalpackage);
			System.out.println("/jsapi?appid="+WxPayConfig.appid+"&timeStamp="+timestamp+"&nonceStr="+nonce_str+"&package="+packages+"&sign="+finalsign);
			
			model.addAttribute("appid", WxPayConfig.appid);
			model.addAttribute("timeStamp", timestamp);
			model.addAttribute("nonceStr", nonce_str);
			model.addAttribute("packageValue", packages);
			model.addAttribute("sign", finalsign);
			
			model.addAttribute("bizOrderId", orderId);
			model.addAttribute("orderId", orderId);
			model.addAttribute("payPrice", total_fee);
			model.addAttribute("cardNumber", orderId.substring(2, orderId.length()- 8));
			model.addAttribute("fee", totalFee);
			return "forward:/wx/jsapi.jsp";
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param code 网页授权后获取传递的参数
	 * @return
	 */
	private String getOpenId(String code) {
		String openId = "";
		String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ WxPayConfig.appid + "&secret=" + WxPayConfig.appsecret + "&code=" + code + "&grant_type=authorization_code";
		System.out.println("URL:"+URL);
		JSONObject jsonObject = CommonUtil.httpsRequest(URL, "GET", null);
		if (null != jsonObject) {
			openId = jsonObject.getString("openid");
			System.out.println("openId:" + openId);
		}
		return openId ;
	}
	

	/**
	 * 微信异步回调，不会跳转页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/notifyUrl")
	public String weixinReceive(HttpServletRequest request,HttpServletResponse response, Model model){
		System.out.println("==开始进入h5支付回调方法==");
		String xml_review_result = WeixinPayUtil.getXmlRequest(request);
		System.out.println("微信支付结果:"+xml_review_result);
		Map resultMap = null;
		try {
			resultMap = WeixinPayUtil.doXMLParse(xml_review_result);
			System.out.println("resultMap:"+resultMap);
			if(resultMap != null && resultMap.size() > 0){
				String sign_receive = (String)resultMap.get("sign");
				System.out.println("sign_receive:"+sign_receive);
				resultMap.remove("sign");
				String checkSign = WeixinPayUtil.getSign(resultMap,WxPayConfig.partnerkey);
				System.out.println("checkSign:"+checkSign);

				//签名校验成功
				if(checkSign != null && sign_receive != null &&
						checkSign.equals(sign_receive.trim())){
					System.out.println("weixin receive check Sign sucess");
                    try{
                    	//获得返回结果
                    	String return_code = (String)resultMap.get("return_code");
                    
                    	if("SUCCESS".equals(return_code)){
                    		String out_trade_no = (String)resultMap.get("out_trade_no");
                    		System.out.println("weixin pay sucess,out_trade_no:"+out_trade_no);
                    		//处理支付成功以后的逻辑，这里是写入相关信息到文本文件里面，如果有订单的处理订单
                    		try{
                    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh24:mm:ss");
                    			String content = out_trade_no+"        "+sdf.format(new Date());
                    			String fileUrl = System.getProperty("user.dir") + File.separator+"WebContent" + File.separator + "data" + File.separator + "order.txt";
                    			//TxtUtil.writeToTxt(content, fileUrl);
                    			String iccid =out_trade_no.substring(2, out_trade_no.length()- 8) ;
                    		}catch(Exception e){
                    			e.printStackTrace();
                    		}
                    	}else{
                    	    model.addAttribute("payResult", "0");
                    	    model.addAttribute("err_code_des", "通信错误");
                    	}
                    }catch(Exception e){
                    	e.printStackTrace();
                    }
				}else{
					//签名校验失败
					System.out.println("weixin receive check Sign fail");
                    String checkXml = "<xml><return_code><![CDATA[FAIL]]></return_code>"+
                    					"<return_msg><![CDATA[check sign fail]]></return_msg></xml>";
                    WeixinPayUtil.getTradeOrder("http://weixin.xinfor.com/wx/notifyUrl", checkXml);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 页面js返回支付成功后，查询微信后台是否支付成功，然后跳转结果页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/success")
	public String toWXPaySuccess(HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException{
		String id = request.getParameter("orderId");
		String iccid = "";
		if(StringUtils.isNotEmpty(id)){
			iccid =  id.substring(2, id.length()- 8) ;
		}
		System.out.println("toWXPaySuccess, orderId: " + id);
		try {
			Map resultMap = WeixinPayUtil.checkWxOrderPay(id);
			System.out.println("resultMap:" + resultMap);
			String return_code = (String)resultMap.get("return_code");
        	String result_code = (String)resultMap.get("result_code");
        	System.out.println("return_code:" + return_code + ",result_code:" + result_code);
        	if("SUCCESS".equals(return_code)){
        		if("SUCCESS".equals(result_code)){
            	    model.addAttribute("orderId", id);
        			model.addAttribute("payResult", "1");
        		}else{
        			String err_code = (String)resultMap.get("err_code");
            	    String err_code_des = (String)resultMap.get("err_code_des");
            	    System.out.println("weixin resultCode:"+result_code+",err_code:"+err_code+",err_code_des:"+err_code_des);
            	    model.addAttribute("err_code", err_code);
            	    model.addAttribute("err_code_des", err_code_des);
        			model.addAttribute("payResult", "0");
        		}
        	}else{
        	    model.addAttribute("payResult", "0");
        	    model.addAttribute("err_code_des", "通信错误");
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String url = "http://suxiu110.cn/wx/index"  ;
		response.sendRedirect(url);
		return null;
	}
}
