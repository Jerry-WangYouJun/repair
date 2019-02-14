<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
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
        <div class="helper_headCover">
            <div class="head_info_mc" >
	    			 <img src="${headImg}" width ="56px"   height="56px" />
            </div>
        </div>

        <div class="other_info_mc">
            <div class="name_other_imc">${loginMember.name}</div>
            <div class="tel_other_imc">
                <span class="major_icon general_icon"></span>
                <span class="major_title general_title">${loginUser.roleName}</span>

            </div>
        </div>
        <div>
        <a  href="${pageContext.request.contextPath}/web/updateMember?id=${loginUser.userId}">
	            <span class="registration">修改信息 &gt;</span>
        </a>
        </div>
    </div>
    <c:choose>
    		<c:when test="${loginUser.roleId eq '3'}">
	    		<div class="article_review">
		        <a class="article1"  href="${pageContext.request.contextPath}/web/custNotOverList">
		            <span class="article1-bg bg join-act"></span>
		            <span class="art_rev_tit">未完成订单</span>
		        </a>
		        <a class="article1" href="${pageContext.request.contextPath}/web/repairCards">
		            <span class="article1-bg bg"></span>
		            <span class="art_rev_tit">我的维修卡</span>
		        </a>
		    </div>
		    <div class="old_my_center my_message">
				<a href="${pageContext.request.contextPath}/web/custOrderList">
			        <div class="leaf_old_mc">
			            <div class="ic_rep_message im"></div>
			            <div class="txt_leaf_omc">消费记录</div>
			
			        </div>
		        </a>
		    </div>
    		</c:when>
    		<c:when test="${loginUser.roleId eq '2'}">
    			<div class="article_review">
		        <a class="article1"  href="${pageContext.request.contextPath}/web/notOverList">
		            <span class="article1-bg bg join-act"></span>
		            <span class="art_rev_tit">未完成的订单</span>
		        </a>
		        <a class="template1" href="${pageContext.request.contextPath}/new/query_card.jsp">
		            <span class="review1-bg bg"></span>
		            <span class="art_rev_tit" >卡号查询</span>
		        </a>
		    </div>
			<div class="old_my_center my_message">
				<a href="${pageContext.request.contextPath}/web/workList">
			        <div class="leaf_old_mc">
			            <div class="ic_rep_message im"></div>
			            <div class="txt_leaf_omc">历史工单<div>
			
			        </div>
		        </a>
		    </div>
    		</c:when>
    </c:choose>
    
    
</div>

</body>
<script type="text/javascript">
	function indexPage(){
		window.location.href="${pageContext.request.contextPath}/web/index" ;
	}
</script>
</html>