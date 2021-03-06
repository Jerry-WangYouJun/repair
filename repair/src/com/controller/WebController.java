package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.StringUtils;
import com.model.Card;
import com.model.CardAttribute;
import com.model.Member;
import com.model.MemberAttribute;
import com.model.OrderAttribute;
import com.model.Pagination;
import com.model.QueryData;
import com.model.User;
import com.pay.util.NoticeUtil;
import com.pay.util.WXAuthUtil;
import com.service.CardService;
import com.service.MemberService;
import com.service.OrderService;
import com.service.PurchaseRecordService;
import com.service.UserService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/web")
public class WebController {
	
	@Autowired
	UserService userService  ;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	CardService cardService ;
	
	 private DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    private CardService service;

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private PurchaseRecordService recordService;
    
	
	/**
	 * 通过微信公众号登录
	 * @param request
	 * @param session
	 * @param openId
	 * @param headImg
	 * @return
	 */
	@RequestMapping("/wxlogin")
	public String wxLogin(HttpServletRequest request , HttpSession session , String openId ,String headImg , String type , String value ) {
		User user = new User();
		user.setOpenid(openId);
		session.setAttribute("headImg", headImg);
		List<User> userList = userService.queryByWhere(user);
		if(userList!=null && userList.size() >0 && StringUtils.isNotEmpty(openId)) {
			Member member = memberService.queryMemberByCode(userList.get(0).getName());
			 session.setAttribute("openid", openId);
			 session.setAttribute("loginUser", userList.get(0));
			 session.setAttribute("loginMember", member);
			 if(StringUtils.isNotEmpty(type)){
					return "forward:/web/" + type ;
			 }else{
				 return "forward:/new/person_center.jsp";
			 }
		}else {
			request.setAttribute("openid", openId);
			session.removeAttribute("loginUser");
			 session.removeAttribute("loginMember");
			return "forward:/new/register.jsp";
		}
	}
	
	@RequestMapping("/checkUser")
	public String checkUser(User user , HttpServletRequest request , HttpSession session){
		user = userService.checkUser(user);
		if(user.getRoleId() != null  ){
			Member member = memberService.queryMemberByCode(user.getName());
			 session.setAttribute("openid", user.getOpenid());
			 session.setAttribute("loginUser", user);
			 session.setAttribute("loginMember", member);
			 return "forward:/new/person_center.jsp";
		}else{
			 session.removeAttribute("loginUser");
			 session.removeAttribute("loginMember");
			 session.removeAttribute("headImg");
			 request.setAttribute("msg", "用户名或者密码错误");
			return "forward:/new/web_login.jsp";
		}
	}
	
	@RequestMapping("/register")
	public String registerInit(HttpServletRequest request , HttpSession session , String openid ) {
		request.setAttribute("openid", openid);
		return "forward:/new/register.jsp";
	}
	
	@RequestMapping("/updateMember")
	public String updateMember(HttpServletRequest request , HttpSession session , Integer id ) {
		User user = userService.selectUserById(id);
		Member member = memberService.queryMemberByCode(user.getName());
		request.setAttribute("", member);
		return "forward:/new/register.jsp";
	}
	
	@RequestMapping("/repairCards")
	public String repairCars(HttpServletRequest request ,HttpSession session) {
		
		QueryData qo = new QueryData();
		Member m = (Member)session.getAttribute("loginMember");
		qo.setMbmberId(m.getMemberId());
		List<CardAttribute> list =  cardService.queryAllCards(qo, new Pagination());
		request.setAttribute("cardList", list);
		return "forward:/new/repair_cards.jsp";
	}
	
	
	@RequestMapping("/queryCard")
	public String queryCard(HttpServletRequest request ,HttpSession session) {
		
		QueryData qo = new QueryData();
		Member m = (Member)session.getAttribute("loginMember");
		qo.setMbmberId(m.getMemberId());
		List<CardAttribute> list =  cardService.queryAllCards(qo, new Pagination());
		request.setAttribute("cardList", list);
		return "forward:/new/repair_cards.jsp";
	}
	
	@RequestMapping("/orderInit")
	public String orderInit(HttpServletRequest request ,HttpSession session  , String cardNumber) {
		request.setAttribute("cardNumber", cardNumber);
		String code = getNewOrderNumber(session);
		request.setAttribute("orderNumber", code);
		return "forward:/new/new_order.jsp";
	}
	
	@RequestMapping("/workList")
	public String workList(HttpServletRequest request ,HttpSession session  , String cardNumber) {
		Member m = (Member)session.getAttribute("loginMember");
		QueryData qo = new QueryData();
		qo.setSearchName(m.getName());
		List list = orderService.queryAllOrders(qo, new Pagination());
		request.setAttribute("list", list);
		return "forward:/new/work_order_list.jsp";
	}
	
	@RequestMapping("/custNotOverList")
	public String custNotOverList(HttpServletRequest request ,HttpSession session , String memberCode ) {
		Member m = (Member)session.getAttribute("loginMember");
		QueryData qo = new QueryData();
		qo.setMbmberId(m.getMemberId());
		qo.setOrderState("待付款");
		List list = orderService.queryAllOrders(qo, new Pagination());
		request.setAttribute("list", list);
		return "forward:/new/work_order_list.jsp";
	}
	
	@RequestMapping("/notOverList")
	public String notOverList(HttpServletRequest request ,HttpSession session  , String cardNumber) {
		Member m = (Member)session.getAttribute("loginMember");
		QueryData qo = new QueryData();
		qo.setOrderState("待付款");
		qo.setSearchName(m.getName());
		List list = orderService.queryAllOrders(qo, new Pagination());
		request.setAttribute("list", list);
		return "forward:/new/work_order_list.jsp";
	}
	
	@RequestMapping("/custOrderList")
	public String custOrderList(HttpServletRequest request ,HttpSession session  , String cardNumber) {
		Member m = (Member)session.getAttribute("loginMember");
		QueryData qo = new QueryData();
		qo.setMbmberId(m.getMemberId());
		List list = orderService.queryAllOrders(qo, new Pagination());
		request.setAttribute("list", list);
		return "forward:/new/work_order_list.jsp";
	}
	
	@RequestMapping("/carRecords")
	public String carRecords(HttpServletRequest request ,HttpSession session  , String cardNumber) {
		Member m = (Member)session.getAttribute("loginMember");
		QueryData qo = new QueryData();
		qo.setMbmberId(m.getMemberId());
		qo.setSearchCardNumber(cardNumber);
		List list = orderService.queryAllOrders(qo, new Pagination());
		request.setAttribute("list", list);
		return "forward:/new/work_order_list.jsp";
	}
	
	@RequestMapping("/updateOrderState")
	public String updateOrderState(HttpServletRequest request ,HttpSession session  , String orderNumber , String state) throws UnsupportedEncodingException{
		try {
			QueryData qo = new QueryData();
			qo.setSearchOrderNumber(orderNumber);
			OrderAttribute order = orderService.queryAllOrders(qo, new Pagination()).get(0) ;
			if("已付款".equals(order.getState())){
	 			  return null;
			 }
			Card card = service.querySingleCard(order.getCardNumber());
	         BigDecimal money = new BigDecimal(card.getCardBalance());
	         money = money.subtract(new BigDecimal(order.getOrderMoney()));
	         int flag = money.compareTo(BigDecimal.ZERO); 
	         qo = new QueryData();
	         qo.setSearchCardNumber(order.getCardNumber());
	         List<MemberAttribute> custList = memberService.queryAllMembers(qo, new Pagination());
	     	 Member custMaster =  new Member();
	     	if(custList != null  && custList.size() >0) {
	     		custMaster =  custList.get(0);
	     		if(flag == 1){
	     			 order.setMoneyBalance(money + "");
	     			qo = new QueryData(); 
	     			qo.setSearchName(order.getBrokerage());
	     			List<MemberAttribute> custWorkList = memberService.queryAllMembers(qo, new Pagination());
	      	  		if(custWorkList != null  && custWorkList.size() >0) {
	      	  			 Member custWork = custWorkList.get(0);
	      	  			  orderService.updateOrderState(orderNumber ,  "已付款");
	      	  			  service.updateCardBalance(order.getCardNumber(),money);
	      	  			  recordService.insertConsumeRecord(order,"consume");
	      	  			  WXAuthUtil.sendTemplateMsg(NoticeUtil.successPay(order , custMaster));
	      	  			  WXAuthUtil.sendTemplateMsg(NoticeUtil.successPayWorker(order , custWork , custMaster.getName()));
	      	  		}else{
	      	  			  orderService.updateOrderState(orderNumber ,  "已付款");
	      	  			  service.updateCardBalance(order.getCardNumber(),money);
	      	  			  recordService.insertConsumeRecord(order,"consume");
	      	  			  WXAuthUtil.sendTemplateMsg(NoticeUtil.successPay(order , custMaster));
	      	  			 request.setAttribute("msg", "扣款成功，工人姓名有误，请核对");
		     			 request.setAttribute("orderNow", order.getOrderNumber());
	      	  		}
	     		}else{
	     			request.setAttribute("msg", "付款失败，余额不足");
	     			request.setAttribute("orderNow", order.getOrderNumber());
	     			WXAuthUtil.sendTemplateMsg(NoticeUtil.failPay(order , custMaster));
	     		}
	     	}else{
	     		request.setAttribute("msg", "付款失败，未找到关联持卡人，请核对卡信息");
     			request.setAttribute("orderNow", order.getOrderNumber());
	     	}
		} catch (Exception e) {
			 System.out.println("出现异常" + e.getMessage());
		} 
		return "forward:/web/custNotOverList";
	}
	
	public  String getNewOrderNumber(HttpSession session){
        boolean flag = true;
        String order_subfix = service.getCompanyCode();
        String order_fubfix = "";
        DecimalFormat df = new DecimalFormat("000");
        while(flag){
        		User user = (User)session.getAttribute("loginUser");
        		String code = "00" + user.getRoleId();
            order_fubfix = dateFormat.format(new Date()).toString()+ code + df.format((int)(Math.random()*1000));
            String result = orderService.checkOrderNumberIsAlreadyExist(order_subfix+order_fubfix);
            if("0".equals(result)){
                flag = false;
            }
        }
        return order_subfix+order_fubfix;
    }
	
	
	@RequestMapping("/registerInit")
	public String registerInit(HttpServletRequest  request , HttpSession session ,String openId , String remark , Integer id  ) {
		return "forward:/wx/registerInit.jsp";
	}
	
	@RequestMapping("/pay")
	public String pay(HttpServletRequest  request , HttpSession session , String cardNumber ) {
		request.setAttribute("cardNumber", cardNumber);
		return "forward:/wx/xfpay.jsp";
	}
	
	
	/**
	 * 
	 * @param request
	 * @param session
	 * @param value   值
	 * @param colName 字段名
	 */
	@RequestMapping("/check_unique")
	public void check_unique(HttpServletResponse response , HttpServletRequest  request , HttpSession session ,String value , String colName   ) {
		  response.setContentType("text/text;charset=UTF-8");
	        PrintWriter out;
	        JSONObject json = new JSONObject();
	        try {
	        		colName = "idCard".equals(colName)?"id_card":colName;
	            int i  = memberService.check_unique(colName, value);
	            out = response.getWriter();
	            if(i > 0) {
		            	json.put("msg", "数据已经在，请重新输入");
		            	json.put("success", false);
	            }else {
	            		json.put("success", true);
	            }
	            out.println(json);
	            out.flush();
	            out.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	            
	        }
	}
	
	 @RequestMapping("/phoneQuery")
	    public  String phoneQuery(String phone,HttpServletResponse response,HttpSession session , HttpServletRequest request) {
	    		QueryData qo = new QueryData();
	    		qo.setMemberPhone(phone);
	    		List<CardAttribute>  list=service.queryAllCards(qo, new Pagination());
	    		request.setAttribute("cardList", list);
	    		return "forward:/new/query_phone.jsp";
	    }
	 @RequestMapping("/cardQuery")
	    public  String cardQuery(String cardNumber,HttpServletResponse response,HttpSession session , HttpServletRequest request) {
	    		QueryData qo = new QueryData();
	    		qo.setSearchCardNumber(cardNumber);
	    		List<CardAttribute>  list=service.queryAllCards(qo, new Pagination());
	    		request.setAttribute("cardList", list);
	    		return "forward:/new/query_card.jsp";
	    }
	 
	 @RequestMapping("/deleteCard")
	    public String deleteCard(String ids,HttpServletResponse response) {
	        response.setContentType("text/text;charset=UTF-8");
	            String[] strs = ids.split(",");
	            List<String> delList = new ArrayList<String>();
	            for (String str : strs) {
	                delList.add(str);
	            }
	            service.delCardWithIds(delList);
	            return "forward:/web/repairCards";
	    }
	 
	    @ExceptionHandler({Exception.class})       
	    public String exception(Exception e) {       
	        return "forward:/new/reload.jsp";       
	    } 
}
