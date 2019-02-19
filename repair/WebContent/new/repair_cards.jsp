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

	    
	    function queryCardRecord(cardNumber){
	   	 	window.location.href='${basePath}/web/carRecords?cardNumber=' + cardNumber;
	    }
	    
	    function payCard(cardNumber){
	    	event.stopPropagation();
	    		window.location.href='${basePath}/web/pay?cardNumber=' + cardNumber;
	    }
	    
	    function deleteCard(id){
	   	 	window.location.href='${basePath}/web/deleteCard?ids=' + id;
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
						<div class="twt-feed blue-bg" >
							 <h1 onclick="queryCardRecord('${card.cardNumber}')">  
									${card.cardName }
							 </h1>
							<h1 onclick="queryCardRecord('${card.cardNumber}')"> 卡号： 
									${card.cardNumber }
							 </h1>
							<div class="weather-category twt-category">
								<ul>
									<li class="active" onclick="queryCardRecord('${card.cardNumber}')">
										<h5> 卡内余额： ${card.cardBalance } 
										</h5>
									</li>
									<li class="active">
										<h1>  <button style="background-color: #6a74ec" type="button" onclick="payCard('${card.cardNumber}')">充值</button>
										</h1>
									</li>
									<li class="active">
										<h1>  <button style="background-color: #6a74ec" type="button" onclick="deleteCard('${card.cardId}')">删除卡</button>
										</h1>
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