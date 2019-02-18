<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${basePath}/css/style.css" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${basePath}/js/jquery-3.1.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${basePath}/js/bootstrap.min.js"></script>
<style type="text/css">
.taocan{
	 margin:5px;
	 align-content: center;
}

</style>
</head>
<body>
<div class="container-fluid">
			<div role="tabpanel" class="tab-pane active" style="margin-top: 10px;">
				<div class="taocan  col-xs-5" style="margin-right: 10%;" onclick=" doWeixinPay(100)">
					<h3> 100元</h3>
				</div>
				<div class="taocan  col-xs-5" onclick=" doWeixinPay(200)">
					<h3> 200元</h3>
				</div>
				<div class="taocan  col-xs-5" style="margin-right: 10%;" onclick=" doWeixinPay(300)">
					<h3>300元</h3>
				</div>
				<div class="taocan  col-xs-5" onclick=" doWeixinPay(500)">
					<h3>500元</h3>
				</div>
				<div class="taocan  col-xs-2" onclick="window.history.back()">
					<h3>返回</h3>
				</div>
			</div>

		</div>
	 <script type="text/javascript">
	    function doWeixinPay(totalFee){
	    		window.location.href="${basePath}/wx/userAuth?totalFee="+ totalFee+ "&cardNumber=${cardNumber}"  ;
	    }
	 </script>

</body>
</html>
