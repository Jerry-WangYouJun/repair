package com.pay.util;

import java.util.ArrayList;
import java.util.List;

import com.common.DateUtils;
import com.model.Member;
import com.pay.msg.Template;
import com.pay.msg.TemplateParam;

public class NoticeUtil {
	
	//注册成功通知
	static String registerSuccess = "jzTnjntZsjIbozjbWIBMlFiFywvZJ967vPtLvlvvCwM";
	
	//积分变动提醒
	static String pointUpdate ="IEHbFW5TDYZy-OXaYcEuY-4TkAUHnJTzCTcQe-8BeOQ";
	
	
	/**
	 *  约会提醒
	 * @param location
	 * @param customer
	 * @return
	 */
//	public static Template inviteRemind( String msg  ,  String inviteDate, Customer customer , Integer id  , int time) {
//		 Template tem=new Template();  
//	        tem.setTemplateId(actState);  
//	        tem.setTopColor("#000000");  
//	        List<TemplateParam> paras=new ArrayList<TemplateParam>();  
//	        paras.add(new TemplateParam("first","您的约会讲在" + time + "小时后开始","#333"));  
//	        paras.add(new TemplateParam("keyword1", "" ,"#333"));
//	        paras.add(new TemplateParam("keyword2", inviteDate,"#333"));
//	        paras.add(new TemplateParam("remark", "请注意约会时间," + msg,"#333"));  
//	        tem.setTemplateParamList(paras);  
//	        tem.setToUser(customer.getOpenId());//用户openid
//	        //设置超链接
//	        tem.setUrl("http://www.ringfingerdating.cn/ring/wx/index/web/"+id);  
//	        return tem;
//	}
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
//	
//	/**
//	 * 发出约会邀约 1
//	 * @param customer
//	 * @return
//	 */
//	public static Template inviteInit(InviteDetail detail , String joinOpenID) {
//		 Template tem=new Template();  
//	        tem.setTemplateId(inviteInit);  
//	        tem.setTopColor("#000000");  
//	        List<TemplateParam> paras=new ArrayList<TemplateParam>();  
//	        paras.add(new TemplateParam("first","您有新的约会邀请","#333"));  
//	        paras.add(new TemplateParam("keyword1", "等待确认","#333"));
//	        paras.add(new TemplateParam("keyword2","无名指之约","#333"));
//	        paras.add(new TemplateParam("keyword3", "待定","#333"));
//	        paras.add(new TemplateParam("keyword4", "待定","#333"));
//	        paras.add(new TemplateParam("remark","点击查看详情并选择具体约会时间地点","#333"));  
//	        tem.setTemplateParamList(paras);  
//	        tem.setToUser(joinOpenID);//用户openid
//	        //设置超链接
//	        tem.setUrl("http://www.ringfingerdating.cn/ring/wx/index/web/"+detail.getInviteId() );  
//	        return tem;
//	}
//	/**
//	 * 约会时间地点确认 2 
//	 * @param customer
//	 * @param joinOpenID
//	 * @return
//	 */
//	public static Template inviteConfirm(Customer customer , String joinOpenID , Integer id ) {
//		 Template tem=new Template();  
//	        tem.setTemplateId(inviteConfirm);  
//	        tem.setTopColor("#000000");  
//	        List<TemplateParam> paras=new ArrayList<TemplateParam>();  
//	        paras.add(new TemplateParam("first","您发起的约会邀约已经通过确认","#333"));  
//	        String no = "1".equals(customer.getSex())?("MO"+customer.getId()):("FO" + customer.getId());
//	        paras.add(new TemplateParam("keyword1", no ,"#333"));
//	        paras.add(new TemplateParam("keyword2", DateUtils.getToday() ,"#333"));
//	        paras.add(new TemplateParam("remark","点击查看详情","#333"));  
//	        tem.setTemplateParamList(paras);  
//	        tem.setToUser(customer.getOpenId());//用户openid
//	        //设置超链接
//	        tem.setUrl("http://www.ringfingerdating.cn/ring/wx/index/web/"+id);  
//	        return tem;
//	}
//	/**
//	 * 拒绝邀请，3/5
//	 * @param location
//	 * @param customer
//	 * @return
//	 */
//	public static Template inviteRefuse( String reason  ,  Customer customer , Integer id ) {
//		 Template tem=new Template();  
//	        tem.setTemplateId(inviteRefuse);  
//	        tem.setTopColor("#000000");  
//	        List<TemplateParam> paras=new ArrayList<TemplateParam>();  
//	        paras.add(new TemplateParam("first","您发起的约会邀约被拒绝","#333"));  
//	        paras.add(new TemplateParam("keyword1", reason,"#333"));
//	        paras.add(new TemplateParam("keyword2",DateUtils.getToday(),"#333"));
//	        paras.add(new TemplateParam("remark","","#333"));  
//	        tem.setTemplateParamList(paras);  
//	        tem.setToUser(customer.getOpenId());//用户openid
//	        //设置超链接
//	        tem.setUrl("http://www.ringfingerdating.cn/ring/wx/index/web/"+id);  
//	        return tem;
//	}
//	
//	
//	/**
//	 * 接受邀请 4
//	 * @param location
//	 * @param customer
//	 * @return
//	 */
//	public static Template inviteAccept(Location location  ,  Customer customer ,Integer id ) {
//		 Template tem=new Template();  
//	        tem.setTemplateId(inviteAccept);  
//	        tem.setTopColor("#000000");  
//	        List<TemplateParam> paras=new ArrayList<TemplateParam>();  
//	        paras.add(new TemplateParam("first","您约会已经确定，时间地点如下","#333"));  
//	        paras.add(new TemplateParam("keyword1", location.getLocName(),"#333"));
//	        paras.add(new TemplateParam("keyword2", location.getManager(),"#333"));
//	        paras.add(new TemplateParam("keyword3", location.getTelephone(),"#333"));
//	        paras.add(new TemplateParam("remark","点击查看预约详情","#333"));  
//	        tem.setTemplateParamList(paras);  
//	        tem.setToUser(customer.getOpenId());//用户openid
//	        //设置超链接
//	        tem.setUrl("http://www.ringfingerdating.cn/ring/wx/index/web/"+id);  
//	        return tem;
//	}
//	
//	public static Template inviteAccept(Location location  ,   User user ,Integer id ) {
//		 Template tem=new Template();  
//	        tem.setTemplateId(inviteAccept);  
//	        tem.setTopColor("#000000");  
//	        List<TemplateParam> paras=new ArrayList<TemplateParam>();  
//	        paras.add(new TemplateParam("first","有新的约会已经确定，时间地点如下","#333"));  
//	        paras.add(new TemplateParam("keyword1", location.getLocName(),"#333"));
//	        paras.add(new TemplateParam("keyword2", location.getManager(),"#333"));
//	        paras.add(new TemplateParam("keyword3", location.getTelephone(),"#333"));
//	        paras.add(new TemplateParam("remark","点击查看预约详情","#333"));  
//	        tem.setTemplateParamList(paras);  
//	        tem.setToUser(user.getRemark());//用户openid
//	        //设置超链接
//	        tem.setUrl("http://www.ringfingerdating.cn/ring/wx/index/web/"+id);  
//	        return tem;
//	}
//	
//	public static Template inviteUpdate(Location location  ,  Customer customer , Date date , Integer id ) {
//		Template tem=new Template();  
//        tem.setTemplateId(inviteUpdate);  
//        tem.setTopColor("#000000");  
//        List<TemplateParam> paras=new ArrayList<TemplateParam>();  
//        paras.add(new TemplateParam("first","您约会有信息发生变化，时间地点如下","#333"));  
//        paras.add(new TemplateParam("keyword1", customer.getNickName(),"#333"));
//        paras.add(new TemplateParam("keyword2", location.getLocName(),"#333"));
//        paras.add(new TemplateParam("keyword3", location.getAddress(),"#333"));
//        paras.add(new TemplateParam("keyword4", DateUtils.formatDate("yyyy年MM月dd日HH:00", date) ,"#333"));
//        paras.add(new TemplateParam("keyword5", "场所及相关服务","#333"));
//        paras.add(new TemplateParam("remark","点击查看详情","#333"));  
//        tem.setTemplateParamList(paras);  
//        tem.setToUser(customer.getOpenId());//用户openid
//        //设置超链接
//        tem.setUrl("http://www.ringfingerdating.cn/ring/wx/index/web/" + id );  
//        return tem;
//	}
//
//	public static Template registerReport(User u, Customer customer) {
//		Template tem=new Template();  
//        tem.setTemplateId(examineState);  
//        tem.setTopColor("#000000");  
//        List<TemplateParam> paras=new ArrayList<TemplateParam>();  
//        paras.add(new TemplateParam("first","有新用户待审核","#333"));  
//        paras.add(new TemplateParam("keyword1", "待审核","#333"));
//        paras.add(new TemplateParam("keyword2", customer.getChName(),"#333"));
//        paras.add(new TemplateParam("keyword3", DateUtils.getToday(),"#333"));
//        paras.add(new TemplateParam("remark","点击查看详情","#333"));  
//        tem.setTemplateParamList(paras);  
//        tem.setToUser(u.getRemark());//用户openid
//        //设置超链接
//        tem.setUrl("http://www.ringfingerdating.cn/ring/wx/index/web/0"  );    
//        return tem;
//	}
//	
//	public static Template registerNotice(User u, Customer customer) {
//		Template tem=new Template();  
//        tem.setTemplateId(examineState);  
//        tem.setTopColor("#000000");  
//        List<TemplateParam> paras=new ArrayList<TemplateParam>();  
//        if("1".equals(customer.getExamine())){
//        	paras.add(new TemplateParam("first","资料审核通过","#333"));  
//        	paras.add(new TemplateParam("keyword1", "已通过","#333"));
//        	paras.add(new TemplateParam("keyword2", customer.getChName(),"#333"));
//        	paras.add(new TemplateParam("keyword3", DateUtils.getToday(),"#333"));
//        	paras.add(new TemplateParam("remark","点击查看详情","#333"));  
//        }else{
//        	paras.add(new TemplateParam("first","资料审核未通过","#333"));  
//        	paras.add(new TemplateParam("keyword1", "未通过","#333"));
//        	paras.add(new TemplateParam("keyword2", customer.getChName(),"#333"));
//        	paras.add(new TemplateParam("keyword3", DateUtils.getToday(),"#333"));
//        	paras.add(new TemplateParam("remark","拒绝原因：" + customer.getRemark(),"#333"));  
//        }
//        tem.setTemplateParamList(paras);  
//        tem.setToUser(customer.getOpenId());//用户openid
//        //设置超链接
//        tem.setUrl("http://www.ringfingerdating.cn/ring/ring/login.jsp");  
//        return tem;
//	}
//	
//	public static Template examine( Customer customer , String flag , String remark) {
//		Template tem=new Template();  
//        tem.setTemplateId(examineState);  
//        tem.setTopColor("#000000");  
//        List<TemplateParam> paras=new ArrayList<TemplateParam>();  
//        if("1".equals(flag)){
//        	paras.add(new TemplateParam("first","审核通过","#333"));  
//        	paras.add(new TemplateParam("keyword1", "已通过","#333"));
//        	paras.add(new TemplateParam("keyword2", customer.getChName(),"#333"));
//        	paras.add(new TemplateParam("keyword3", DateUtils.getToday(),"#333"));
//        	paras.add(new TemplateParam("remark","点击查看详情","#333"));  
//        }else{
//        	paras.add(new TemplateParam("first","审核未通过","#333"));  
//        	paras.add(new TemplateParam("keyword1", "未通过","#333"));
//        	paras.add(new TemplateParam("keyword2", customer.getChName(),"#333"));
//        	paras.add(new TemplateParam("keyword3", DateUtils.getToday(),"#333"));
//        	paras.add(new TemplateParam("remark","拒绝原因：" + remark,"#333"));  
//        }
//        tem.setTemplateParamList(paras);  
//        tem.setToUser(customer.getOpenId());//用户openid
//        //设置超链接
//        tem.setUrl("http://www.ringfingerdating.cn/ring/ring/login.jsp");  
//        return tem;
//	}
//	
//	public static Template actExamineNotice(User u, Customer customer, ActDetail detail ) {
//		Template tem=new Template();  
//        tem.setTemplateId(examineState);  
//        tem.setTopColor("#000000");  
//        List<TemplateParam> paras=new ArrayList<TemplateParam>();  
//        if("2".equals(detail.getDetailState())){
//        	paras.add(new TemplateParam("first","审核通过","#333"));  
//        	paras.add(new TemplateParam("keyword1", "已通过","#333"));
//        	paras.add(new TemplateParam("keyword2", customer.getChName(),"#333"));
//        	paras.add(new TemplateParam("keyword3", DateUtils.getToday(),"#333"));
//        	paras.add(new TemplateParam("remark","点击查看详情","#333"));  
//        }else{
//        	paras.add(new TemplateParam("first","审核未通过","#333"));  
//        	paras.add(new TemplateParam("keyword1", "未通过","#333"));
//        	paras.add(new TemplateParam("keyword2", customer.getChName(),"#333"));
//        	paras.add(new TemplateParam("keyword3", DateUtils.getToday(),"#333"));
//        	paras.add(new TemplateParam("remark","拒绝原因：" + customer.getRemark(),"#333"));  
//        }
//        tem.setTemplateParamList(paras);  
//        tem.setToUser(customer.getOpenId());//用户openid
//        //设置超链接
//        tem.setUrl("http://www.ringfingerdating.cn/ring/web/customer?id=" + customer.getId() );  
//        return tem;
//	}
//	
//	public static Template msgReport(User u, Customer customer, Message msg) {
//		Template tem=new Template();  
//        tem.setTemplateId(examineState);  
//        tem.setTopColor("#000000");  
//        List<TemplateParam> paras=new ArrayList<TemplateParam>();  
//        paras.add(new TemplateParam("first","有用户新留言待审核","#333"));  
//        paras.add(new TemplateParam("keyword1", "待审核","#333"));
//        paras.add(new TemplateParam("keyword2", customer.getChName(),"#333"));
//        paras.add(new TemplateParam("keyword3", DateUtils.getToday(),"#333"));
//        paras.add(new TemplateParam("remark","点击查看详情","#333"));  
//        tem.setTemplateParamList(paras);  
//        tem.setToUser(u.getRemark());//用户openid
//        //设置超链接
//        tem.setUrl("http://www.ringfingerdating.cn/ring/message/examine?id=" + msg.getId() );    
//        return tem;
//	}
//	
//	public static Template msgSuccess(User u, Customer customer, Message msg) {
//		Template tem=new Template();  
//        tem.setTemplateId(examineState);  
//        tem.setTopColor("#000000");  
//        List<TemplateParam> paras=new ArrayList<TemplateParam>();  
//        paras.add(new TemplateParam("first","您的留言审核已通过","#333"));  
//        paras.add(new TemplateParam("keyword1", "审核通过","#333"));
//        paras.add(new TemplateParam("keyword2", customer.getChName(),"#333"));
//        paras.add(new TemplateParam("keyword3", DateUtils.getToday(),"#333"));
//        paras.add(new TemplateParam("remark","点击查看详情","#333"));  
//        tem.setTemplateParamList(paras);  
//        tem.setToUser(customer.getOpenId());//用户openid
//        //设置超链接
//        tem.setUrl("http://www.ringfingerdating.cn/ring/message/examine?id=" + msg.getId() );    
//        return tem;
//	}
//	
//	public static Template articleSuccess(User u, Customer customer, Article article) {
//		Template tem=new Template();  
//        tem.setTemplateId(examineState);  
//        tem.setTopColor("#000000");  
//        List<TemplateParam> paras=new ArrayList<TemplateParam>();  
//        paras.add(new TemplateParam("first","您的文章审核已通过","#333"));  
//        paras.add(new TemplateParam("keyword1", "审核通过","#333"));
//        paras.add(new TemplateParam("keyword2", article.getTitle(),"#333"));
//        paras.add(new TemplateParam("keyword3", DateUtils.getToday(),"#333"));
//        paras.add(new TemplateParam("remark","点击查看详情","#333"));  
//        tem.setTemplateParamList(paras);  
//        tem.setToUser(customer.getOpenId());//用户openid
//        //设置超链接
//        tem.setUrl("http://www.ringfingerdating.cn/ring/article/detail?id=" + article.getId() );    
//        return tem;
//	}
//	
//	public static Template msgNotice( Customer customer, Message msg) {
//		Template tem=new Template();  
//        tem.setTemplateId(examineState);  
//        tem.setTopColor("#000000");  
//        List<TemplateParam> paras=new ArrayList<TemplateParam>();  
//        paras.add(new TemplateParam("first","您的留言审核已通过","#333"));  
//        paras.add(new TemplateParam("keyword1", "审核通过","#333"));
//        paras.add(new TemplateParam("keyword2", customer.getChName(),"#333"));
//        paras.add(new TemplateParam("keyword3", DateUtils.getToday(),"#333"));
//        paras.add(new TemplateParam("remark","点击查看详情","#333"));  
//        tem.setTemplateParamList(paras);  
//        tem.setToUser(customer.getOpenId());//用户openid
//        //设置超链接
//        tem.setUrl("http://www.ringfingerdating.cn/ring/message/examine?id=" + msg.getId() );    
//        return tem;
//	}

}
