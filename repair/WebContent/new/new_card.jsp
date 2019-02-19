<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html id="a1">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>用户管理</title>
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
					<div><h3>添加新卡</h3></div>
					<form id="dataForm">
					<input type="hidden" class="form-control" name="memberId" id="memberId" value="${loginMember.memberId}">
					<input  class="form-control" name="cardId" id="cardId" type="hidden"></input>
					<input type="hidden" name="cardType" value = "7">
						<div class="form-group">
							<label for="recipient-name" class="control-label">卡号(必填):</label> <input
								type="text" class="form-control" name="cardNumber" id="cardNumber" onchange="checkCard()" >
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">卡密码(必填):</label> <input
								type="text" class="form-control" name="pwd" id="pwd"  onchange="checkCard()"  >
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">卡名称:</label> <input
								type="text" class="form-control" name="cardName" id="cardName" >
						</div>
						<!-- <div class="form-group">
							<label for="message-text" class="control-label">卡类型:</label>
							<select class="form-control"  id ="cardType" name="cardType">
					        </select>	
						</div> -->
						<!-- <div class="form-group">
							<label for="message-text" class="control-label">状态:</label>
							<select class="form-control"  id ="cardStatus" name="cardStatus">
								<option value="">请选择状态</option>
								<option value="0">有效</option>
								<option value="1">无效</option>
							</select>
						</div> -->
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
			var cardno = $("#cardNumber").val() ; 
			 var pwd = $("#pwd").val();
			 if(cardno == '' || pwd == ''){
				  alert("卡号和密码必填，请提供正确的卡信息进行验证");
				  return false;
			 }
			subInfoAll("card");
		}
		
		function subInfoAll(name) {
			var path = "${pageContext.request.contextPath}/"+name+"/"+name+"_edit";
			$.ajax({
				url : path,
				type : 'post',
				data : $("#dataForm").serialize(),
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						alert(data.msg);
						window.location.href="${pageContext.request.contextPath}/web/repairCards" ;
					} else {
						alert(data.msg);
					}
		
				},
				error : function(transport) {
					alert("系统产生错误,请联系管理员!");
				}
			});
		}
		
		 function checkCard(){
			 var cardno = $("#cardNumber").val() ; 
			 var pwd = $("#pwd").val();
			 if(cardno != '' && pwd != ''){
	            $.ajax({
	                url : '${basePath}/card/checkCard',
	                type : 'post',
	                data : {cardno:cardno , pwd:pwd},
	                dataType : 'json',
	                success : function(data) {
	                    if (data.success) {
		                    	alert(data.msg);
		                    	$("#cardNumber").val("") ;
		                    	$("#pwd").val("");
		                    	return false;
	                    } 
	                },
	                error : function(transport) {
	                    alert(data.msg);
	                }
	            });
			 }
			 
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