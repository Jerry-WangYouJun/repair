<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
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
	    
	    function queryCardRecord(cardNumber){
	   	 	window.location.href='${basePath}/web/carRecords?cardNumber=' + cardNumber;
	    }
</script>
</head>
<body>
		<div class="container-fluid">
			<div class="row">
				<div class="user-head">
						<p style="float: left;color:black;">我的维修卡</p>
					<button type="button" class=" pull-right"  onclick="window.history.back()">返回</button>
				</div>
			</div>
			<c:forEach items="${cardList}" var = "card">
				<div class="row container">
					<section class="panel">
						<div class="twt-feed blue-bg" onclick="queryCardRecord('${card.cardNumber}')">
							<h1> 卡号： 
								<c:if test="${empty card.cardName }">
									${card.cardNumber }
								</c:if>
								<c:if test="${not empty card.cardName }">
									${card.cardName }
								</c:if>
							 </h1>
							<div class="weather-category twt-category">
								<ul>
									<li class="active">
										<h5> 卡内余额： ${card.cardBalance } 
										</h5>
									</li>
								</ul>
							</div>
						</div>
					</section>
				</div>
			</c:forEach>
			<div class="row container">
					<section class="panel">
						<div class="twt-feed blue-bg " onclick="window.location.href='${basePath}/new/new_card.jsp'">
							<h1> 点我 添加新卡 </h1>
						</div>
					</section>
				</div>
		</div>

</body>
</html>