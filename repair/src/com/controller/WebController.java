package com.controller;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.StringUtils;
import com.model.CardAttribute;
import com.model.Member;
import com.model.Pagination;
import com.model.QueryData;
import com.model.User;
import com.service.CardService;
import com.service.MemberService;
import com.service.OrderService;
import com.service.UserService;

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
    
	
	/**
	 * 通过微信公众号登录
	 * @param request
	 * @param session
	 * @param openId
	 * @param headImg
	 * @return
	 */
	@RequestMapping("/wxlogin")
	public String wxLogin(HttpServletRequest request , HttpSession session , String openId ,String headImg) {
		User user = new User();
		user.setOpenid(openId);
		session.setAttribute("headImg", headImg);
		List<User> userList = userService.queryByWhere(user);
		if(userList!=null && userList.size() >0 && StringUtils.isNotEmpty(openId)) {
			Member member = memberService.queryMemberByCode(userList.get(0).getName());
			 session.setAttribute("openid", openId);
			 session.setAttribute("loginUser", userList.get(0));
			 session.setAttribute("loginMember", member);
			 return "forward:/new/person_center.jsp";
		}else {
			request.setAttribute("openid", openId);
			session.removeAttribute("loginUser");
			 session.removeAttribute("loginMember");
			 session.removeAttribute("headImg");
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
	public String updateMember(HttpServletRequest request , HttpSession session , String openid ) {
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
	public String custNotOverList(HttpServletRequest request ,HttpSession session ) {
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
		QueryData qo = new QueryData();
		qo.setOrderState("待付款");
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
	
	@RequestMapping("/updateOrderState")
	public String updateOrderState(HttpServletRequest request ,HttpSession session  , String orderNumber , String state) throws UnsupportedEncodingException{
		orderService.updateOrderState(orderNumber ,  new String(state.getBytes("ISO-8859-1"),"UTF-8"));
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
}
