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
</head>
<body>
<div class="container-fluid">
			<div role="tabpanel" class="tab-pane active" style="margin-top: 10px;">
				<div class="taocan">
					<h3>冲值金额</h3>
					<div> <input  id="money"></div>
				</div>
			</div>

			<div class="row dingwei">
				<p class="text-center">
					<button style="border: none;" type="button" class="btn btn-primary btn-lg  col-xs-6" onclick="doWeixinPay();">提交订单</button>
				</p>
			</div>
		</div>
	 <script type="text/javascript">
	    function doWeixinPay(){
	    		window.location.href="${basePath}/wx/userAuth?totalFee="+ 18 + "&iccid=${iccid}";
	    }
	 </script>

</body>
</html>
