<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>查询卡号</title>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${basePath}/css/style.css" />
<script src="${basePath}/js/jquery-3.1.1.min.js"></script>
<script src="${basePath}/js/bootstrap.min.js"></script>
<script type="text/javascript">
	

    //扫描二维码  
    function scan() {  
        wx.scanQRCode({  
            needResult : 0, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，  
            scanType : [ "qrCode", "barCode" ], // 可以指定扫二维码还是一维码，默认二者都有  
            success : function(res) {  
                var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果  
                document.getElementById("wm_id").value = result;//将扫描的结果赋予到jsp对应值上  
                alert("扫描成功::扫描码=" + result);  
            }  
        });  
    };//end_document_scanQRCode  
      
</script> 
</head>
<body>
		<div class="container-fluid">
			<div class="row">
				<!-- Tab panes -->
				<form  id="dataForm" action = "${basePath}/card/querySingle" method="post">
					<div class="col-lg-12">
						<div class="input-group">
							<input  name ="cardNumber"  id="cardNumber" type="text" class="form-control input"   required>
							<a class="clear" onclick="cleardiv()"></a>
							<span class="input-group-btn">
								<button class="btn btn-default" type="button" onclick="singleQuery()">查询卡号</button>
							</span>
						</div>
						<!-- /input-group -->
					</div>
				</form>
				<!-- /.col-lg-6 -->
			</div>
			<!-- /.row -->
			<c:forEach items="${cardList}" var="card">
					<div class="row container"  id="cardInfo" onclick="newOrder('${card.cardNumber}')" >
						 <input  type="hidden" id="cnumber">
							<section class="panel">
								<div class="twt-feed blue-bg">
									<h1 > 卡号：${card.cardNumber}  </h1>
									<div class="weather-category twt-category">
										<ul>
											<li class="active">
												<h5 id="cardBalance"> 卡内余额： ${card.cardBalance }</h5>
												 
											</li>
											<li class="active">
												<h5 id="memberName"> 持卡人： ${card.memberName }</h5>
												 
											</li>
										</ul>
									</div>
								</div>
							</section>
						</div>
			</c:forEach>
				<div style="margin-top: 20px">
					<button style="border: none;" type="button" class="btn btn-primary col-xs-2" onclick="window.history.back()">返回</button>
				</div>
		</div>

		<script >
		
		function  newOrder(){
			var cardNumber =  $("#cnumber").val();
			window.location.href='${basePath}/web/orderInit?cardNumber=' + cardNumber;
		}
		function  newOrder(cardNumber){
			window.location.href='${basePath}/web/orderInit?cardNumber=' + cardNumber;
		}
			function cleardiv(){
				
				document.getElementById("password").value="";
			}
			
			function singleQuery(){
				var cardNumber =  $("#cardNumber").val();
				if(cardNumber ==''){
					 alert("卡号不能为空");
					 return false ;
				}
				window.location.href = "${basePath}/web/cardQuery?cardNumber=" + cardNumber;
			}
		</script>
	</body>
</html>