<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="stylesheet" href="${pageContext.request.contextPath}/new/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/new/css/style.css">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/new/css/index-info.css">
<script src="${pageContext.request.contextPath}/new/js/jquery-1.10.2.js"></script>
<script src="${pageContext.request.contextPath}/new/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/new/js/public.js"></script>
<title>个人中心</title>
<script type="text/javascript">
</script>
</head>
<body>
<div class="vue_my_container">
    <div class="popToast2 showToast-transition" style="display:none">
            <span class="icon_popToast" style="display:none">
            </span>
        <span class="txt_popToast"></span>
    </div>
    <div class="info_mc borderB">
        <div class="other_info_mc">
            <div class="tel_other_imc" onclick="webLogin()">
                <span class="major_title general_title">登录</span>

            </div>
        </div>
        <div>
        <a  href="${pageContext.request.contextPath}/web/register?openid=${openid}">
	            <span class="registration">注册 &gt;</span>
        </a>
        </div>
    </div>
    <div class="article_review">
    		<div class="article1" >
            <span class="article1-bg bg join-act"></span>
            <span class="art_rev_tit">我的工单</span>
       </div>
       <div class="article1" >
            <span class="article1-bg bg"></span>
            <span class="art_rev_tit">维修卡信息</span>
    		</div>
    </div>
    
    <div class="new_my_center">
	        <div class="leaf_old_mc borderT mySpace borderB" style="position:relative" >
	            <div class="ic_rep_space1 im" style="position:relative;"></div>
	            <div class="txt_leaf_omc ">消费历史</div>
	            <!-- <div class="sponsor-act"><span>5</span>个</div> -->
	        </div>
	</div>
	<div class="old_my_center my_message">
	        <div class="leaf_old_mc">
	            <div class="ic_rep_message im"></div>
	            <div class="txt_leaf_omc">充值历史</div>
	
	        </div>
    </div>
</div>

</body>
<script type="text/javascript">
	function indexPage(){
		window.location.href="${pageContext.request.contextPath}/web/index" ;
	}
	
	function webLogin(){
		window.location.href="${pageContext.request.contextPath}/new/web_login.jsp" ;
	}
</script>
</html>