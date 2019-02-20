<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${basePath}/css/style.css" />
<script src="${basePath}/js/jquery-3.1.1.min.js"></script>
<script src="${basePath}/js/bootstrap.min.js"></script>
<script type="text/javascript">

	    function getPrepay(){
	    	 if('${info.flag}' == '1'){
	    	  	  alert("此号码不支持充值，请换卡或联系管理员！");
	    	  	  return false;
	   		  }
		    	if('${info.ICCID }' == ''){
		    		  alert('无效的iccid，请点击【切换】按钮获得iccid');
		    		  return false ;
		    	}
		    	window.location.href='${basePath}/card/xinfu_wechat_pay?iccid=${info.ICCID}';
	    }
	    
	    function updateState(orderNumber , state){
	   	 	window.location.href='${basePath}/web/updateOrderState?orderNumber='+ orderNumber+"&state=" +state ;
	    }
	    
	    function payCard(cardNumber){
	    	event.stopPropagation();
	    		window.location.href='${basePath}/web/pay?cardNumber=' + cardNumber;
	    }
</script>
</head>
<body>
		<div class="container-fluid">
				<div class="user-head">
					<p style="float: left;color:black;">订单列表</p>
					<button type="button" class="btn btn-info pull-right"  onclick="window.history.back()">返回</button>
				</div>
			<c:forEach items="${list}" var = "order">
				<div class="row container">
					<section class="panel">
						<div class="twt-feed blue-bg" style="text-align: left;">
							<p>工单编号：${order.orderNumber }</p>
							<p>服务项目：${order.orderContent }</p>
							<p>服务金额：${order.orderMoney }</p>
							<p>卡号：${order.cardNumber }</p>
							<p>经手人：${order.brokerage }</p>
							<p>订单时间：${order.orderDate }</p>
							<p>工时：${order.duration }</p>
							<c:if test="${loginUser.roleId eq '3' }">
								<c:if test="${order.state eq '待付款' }">
									<button style="color:black;" onclick="updateState('${order.orderNumber }','已付款')">确认付款</button>
									 <c:if test="${order.orderNumber eq orderNow }">
										  ${msg}<button style="color:black;" onclick="payCard('${order.cardNumber}')">去充值</button>
									 </c:if>
								</c:if>
							</c:if>
							
						</div>
	
					</section>
				</div>
			</c:forEach>
		</div>
</body>
</html>