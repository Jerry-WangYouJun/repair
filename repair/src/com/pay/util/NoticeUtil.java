package com.pay.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.common.DateUtils;
import com.model.User;
import com.pay.msg.Template;
import com.pay.msg.TemplateParam;

public class NoticeUtil {
	
	//审核状态提醒 万能状态
	static String examineState="nuCSH18YyXXkB6kRS05srOChuo67zkCP79sQRP8oI1s";
	//会员资料审核提醒   审核失败
	static String examine ="BTlpSvWdbbdGeRP5zREEdqHeeH-OELkmQzaO6oFl-dQ";
	//注册成功通知
	static String registerSuccess = "d8w35wAWCN9cCxpcVZmtDPVWgERECjKJG6ItCZiBkYM";
	//邀请注册成功通知
	static String inviteRegister="uRAO2lglk5z47xROoFFBOzUAIw-QLg8gVjpHRT90X14";
	
	//名片交换提醒   点赞时推送
	static String infoExchange = "DltlRYvPQJd7vYSrADZyVwwSTehzRLs6CIjdK0OLk_k";
	
	//申请处理通知  - 邀约发起
	static String inviteInit = "SpEZI4pB8MhHFo-1zeIJd8od0ooIlMt1UafnQxUbTZw";
	//邀请已接受通知
	static String inviteConfirm = "_X4HhFarcwEb95SFkIZQaG8SKZ-vYeh6U09-4E4vz-E";
	//申请拒绝通知
	static String inviteRefuse = "lhB85KT6SNdvYct8ltjb1Q-mxlB5ljdDtpQO1jzdoiQ";
	//同意邀请通知
	static String inviteAccept = "rLBm4XmI2JOKebMEvVFzrF8HjzeLj5QkXYhi21aHGC8";
	//预约修改成功提醒
	static String inviteUpdate = "G6gzlxHwCEptgbtxhX5_QVVohfUEGQ-KWpOMjR3yEt0";
	//签到成功通知
	static String singIn = "FOH2WMFyoKN_k_ZZdTHAhqFYfoBenA238c_C2WbFrbU";
	
	//爽约提醒
	static String standUp ="VtAv8KJGTl8JwOEuWJS0QOdssGwAwokhgoLroD9_IHI";
	//客服服务投诉提醒   管理员接受客户举报
	static String standUpReport = "iQWH4jXV8l-ZKtGKI5WHEgExbSQn1XbtgMvckNvHbrU";
	
	//报名成功
	static String actSignUpAccess ="HbIIvAmBFe-vDtzHC7C4RE-0v7gCe_vPzaqPZg2bU4g";
	//报名不通过通知
	static String  singUpFailed = "cyRjxoAK0mxHIZUuqNhNVpsVM7kJS0wDYvSegz1_1JI";
	//报名状态通知 -  万能+活动
	static String  actState = "gCoFPhs6WejWFMelw_e3LEl4nMCCOzZO9B5BKKuZ6Po";
	
	//接收留言通知 - 客户留言通知
	static String messageReceiveReport="xEh4C38LiPxKSKSmOOtlF3OfqssNjlefPWzsB1pTox8";
	//留言提交成功通知
	static String messageSend="ltZDxE9jEWoHx4SAJDx0JYHv3m65wBAl-woM5Zealvc";
	
	//积分变动提醒
	static String pointUpdate ="IEHbFW5TDYZy-OXaYcEuY-4TkAUHnJTzCTcQe-8BeOQ";
	//连续签到
	static String singUpContinue="W7Cf_4cE--r0A5gCVCQb-xcy2IlDDcKFI8qrqpTSLOQ";
	
	
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
//	public static Template registerSuccess(Customer customer) {
//		 Template tem=new Template();  
//	        tem.setTemplateId(registerSuccess);  
//	        tem.setTopColor("#000000");  
//	                  
//	        List<TemplateParam> paras=new ArrayList<TemplateParam>();  
//	        paras.add(new TemplateParam("first","注册成功。","#333"));  
//	        paras.add(new TemplateParam("keyword1",customer.getChName(),"#333"));
//	        paras.add(new TemplateParam("keyword2", DateUtils.getToday(),"#333"));
//	        paras.add(new TemplateParam("keyword3","微信","#333"));
//	        paras.add(new TemplateParam("remark","感谢你对无名指之约的支持!!!","#333"));  
//	                  
//	        tem.setTemplateParamList(paras);  
//	        tem.setToUser(customer.getOpenId());//用户openid
//	        //设置超链接
//	        tem.setUrl("http://www.ringfingerdating.cn/ring/wx/index/web/0" );  
//	        return tem;
//	}
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
