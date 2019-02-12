<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html id="a1">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>新增工单</title>
<style type="text/css">
  .panel-body {
    padding: 0px !important; 
}
</style>	

</head>
<body id="a2">
	<div class="grid_3">
		<div class="container" >
			<div class="services">
				<div class="col-sm-12 col-xs-12 login_left">
					<div><h3>新增工单</h3></div>
					<form id="dataForm">
					<input  class="form-control" name="cardId" id="cardId" type="hidden"></input>
						<div class="form-group">
							<label for="recipient-name" class="control-label">工单单号:</label> <input
								type="text" class="form-control" name="orderNumber" id="orderNumber"  readonly="readonly" value="${orderNumber}">
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">卡号:</label> <input
								type="text" class="form-control" name="cardNumber" id="cardNumber"value="${cardNumber}" readonly="readonly" >
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">服务项目:</label>
							<input class="form-control"  id ="orderContent" name="orderContent"/>	
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">时长(小时):</label>
							<input type="text" class="form-control" name="duration" id="duration" >
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">金额:</label>
							<input class="form-control"  id ="orderMoney" name="orderMoney"/>	
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">备注:</label> <input
								type="text" class="form-control" name="remark" id="remark" >
						</div>
						<div class="form-group">
							<button type="button" class="btn btn-primary" onclick="subInfo()">提交</button>
							<button type="button" class="btn btn-primary" onclick="window.history.back()">返回</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
					
</body>
<script type="text/javascript">
		function subInfo(){
			subInfoAll("order");
		}
		
		function subInfoAll(name) {
			var path = "${pageContext.request.contextPath}/card/card_consume";
			$.ajax({
				url : path,
				type : 'post',
				data : $("#dataForm").serialize(),
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						alert(data.msg);
						window.location.href='${basePath}/new/person_center.jsp';
					} else {
						alert(data.msg);
					}
		
				},
				error : function(transport) {
					alert("系统产生错误,请联系管理员!");
				}
			});
		}
		
		 function getCardTypeMenu(){
	            var html = "";
	            $.ajax({
	                url : '${basePath}/card_type/getCardType',
	                type : 'post',
	                data : {},
	                dataType : 'json',
	                success : function(data) {
	                    if (data.success) {
	                        var dataInfo = data.dataInfo;
	                        html +="<option value=''>请选择卡类型</option>";
	                        for(var i = 0;i<dataInfo.length;i++){
	                            html+="<option value='"+dataInfo[i].typeId+"'>"+dataInfo[i].typeName+"</option>";
	                        }
	                        $("#cardType").html(html);
	                    } else {
	                        alert("发生异常，请联系管理员");
	                    }
	                },
	                error : function(transport) {
	                    alert(data.msg);
	                }
	            });
	        }
		 
		 $(function(){
			 getCardTypeMenu();
		 });
	</script>
	
		
</html>