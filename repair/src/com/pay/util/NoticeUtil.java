package com.pay.util;

import java.util.ArrayList;
import java.util.List;

import com.common.DateUtils;
import com.model.Member;
import com.model.Order;
import com.model.OrderAttribute;
import com.pay.msg.Template;
import com.pay.msg.TemplateParam;

public class NoticeUtil {
	
	//注册成功通知
	static String registerSuccess = "jzTnjntZsjIbozjbWIBMlFiFywvZJ967vPtLvlvvCwM";
	
	static String confirmPay = "CZIkJgbuP2S53tV5SiacIgB6S2CyjzGvhBG9Z5x9hvE";
	
	static String paySuccess="esM36cKV45_69lZ25TDHdsonBz9JOI52gDtfObW_qQs";
	
	static String wxPay = "qX095qLB2lVlxqVQ9_LibLh5IaA3ymP4jjEXlGZ4pt4";
//	
//	/**
//	 * 注册成功
//	 * @param customer
//	 * @param joinOpenID 
//	 * @return
//	 */
	public static Template registerSuccess(Member member) {
		 Template tem=new Template();  
	        tem.setTemplateId(registerSuccess);  
	        tem.setTopColor("#000000");  
	                  
	        List<TemplateParam> paras=new ArrayList<TemplateParam>();  
	        paras.add(new TemplateParam("first","恭喜您，会员注册成功","#333"));  
	        paras.add(new TemplateParam("keyword1", member.getName(),"#333"));
	        paras.add(new TemplateParam("keyword2", member.getPhone(),"#333"));
	        paras.add(new TemplateParam("remark","感谢你对山东速修信息的支持!!!","#333"));  
	                  
	        tem.setTemplateParamList(paras);  
	        tem.setToUser(member.getOpenId());//用户openid
	        //设置超链接
	        tem.setUrl("http://suxiu110.cn/wx/index" );  
	        return tem;
	}

	public static Template confirmPay(OrderAttribute order, String openId) {
		Template tem=new Template();  
        tem.setTemplateId(confirmPay);  
        tem.setTopColor("#000000");  
                  
        List<TemplateParam> paras=new ArrayList<TemplateParam>();  
        paras.add(new TemplateParam("first","您好！您已成功享受服务，以下为服务收费明细。请您在未完成订单中核对后确认付款。","#333"));  
        paras.add(new TemplateParam("keyword1", "山东速修信息","#333"));
        paras.add(new TemplateParam("keyword2", order.getBrokerage(),"#333"));
        paras.add(new TemplateParam("keyword3", order.getOrderContent(),"#333"));
        paras.add(new TemplateParam("keyword4", DateUtils.getToday(),"#333"));
        paras.add(new TemplateParam("keyword5", order.getOrderMoney() + "","#333"));
        paras.add(new TemplateParam("remark","感谢你对山东速修信息的支持!!!","#333"));  
                  
        tem.setTemplateParamList(paras);  
        tem.setToUser(openId);//用户openid
        //设置超链接
        tem.setUrl("http://suxiu110.cn/wx/index" );  
        return tem;
	}

	public static Template successPay(Order order, Member custMaster) {
		Template tem=new Template();  
        tem.setTemplateId(paySuccess);  
        tem.setTopColor("#000000");  
                  
        List<TemplateParam> paras=new ArrayList<TemplateParam>();  
        paras.add(new TemplateParam("first","您好！您已成功付款。","#333"));  
        paras.add(new TemplateParam("keyword1", custMaster.getName(),"#333"));
        paras.add(new TemplateParam("keyword2", order.getOrderContent(),"#333"));
        paras.add(new TemplateParam("keyword3", order.getOrderMoney() + "","#333"));
        paras.add(new TemplateParam("keyword4", DateUtils.getToday(),"#333"));
        paras.add(new TemplateParam("remark","感谢你对山东速修信息的支持!!!","#333"));  
        tem.setTemplateParamList(paras);  
        tem.setToUser(custMaster.getOpenId());
        //设置超链接
        tem.setUrl("http://suxiu110.cn/wx/index" );  
        return tem;
	}

	public static Template failPay(Order order, Member custMaster) {
		Template tem=new Template();  
        tem.setTemplateId(paySuccess);  
        tem.setTopColor("#000000");  
                  
        List<TemplateParam> paras=new ArrayList<TemplateParam>();  
        paras.add(new TemplateParam("first","您好！付款失败，余额不足。","#333"));  
        paras.add(new TemplateParam("keyword1", custMaster.getName(),"#333"));
        paras.add(new TemplateParam("keyword2", order.getOrderContent(),"#333"));
        paras.add(new TemplateParam("keyword3", order.getOrderMoney() + "","#333"));
        paras.add(new TemplateParam("keyword4", DateUtils.getToday(),"#333"));
        paras.add(new TemplateParam("remark","感谢你对山东速修信息的支持!!!","#333"));  
        tem.setTemplateParamList(paras);  
        tem.setToUser(custMaster.getOpenId());
        //设置超链接
        tem.setUrl("http://suxiu110.cn/wx/index" );  
        return tem;
	}
	
	public static Template wxPay(String fee , String openId) {
		Template tem=new Template();  
        tem.setTemplateId(wxPay);  
        tem.setTopColor("#000000");  
                  
        List<TemplateParam> paras=new ArrayList<TemplateParam>();  
        paras.add(new TemplateParam("first","您好！您已成功付款。","#333"));  
        paras.add(new TemplateParam("keyword1", fee,"#333"));
        paras.add(new TemplateParam("keyword2", DateUtils.getFormatDate(DateUtils.getToday()),"#333"));
        paras.add(new TemplateParam("keyword3", "山东速修信息" + "","#333"));
        paras.add(new TemplateParam("remark","感谢你对山东速修信息的支持!!!","#333"));  
        tem.setTemplateParamList(paras);  
        tem.setToUser(openId);
        //设置超链接
        tem.setUrl("http://suxiu110.cn/wx/index" );  
        return tem;
	}
}
