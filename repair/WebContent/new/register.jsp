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
					<div><h3>会员信息注册</h3></div>
					<form id="dataForm">
					<input type="hidden" class="form-control" name="memberId" id="memberId" value="${loginMember.memberId}">
					<input type="hidden" class="form-control" name="openId" id="openId" value="${openid}">
					 <input  class="form-control" name="role" id="role" value="3" type="hidden" value="${loginMember.role}"></input>
						<div class="form-group">
							<label for="recipient-name" class="control-label">登陆帐号:</label> <input
								type="text" class="form-control" name="username" id="username" value="${loginUser.username}">
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">登录密码:</label> <input
								type="text" class="form-control" name="password" id="password" value="${loginUser.password}">
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">会员名称:</label> <input
								type="text" class="form-control" name="name" id="name" value="${loginMember.name}">
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">手机号:</label> <input
								type="text" class="form-control" name="phone" id="phone" value="${loginMember.phone}">
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">身份证:</label>
							<input type="text" class="form-control" name="idCard" id="idCard" value="${loginMember.idCard}">
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">备注:</label>
							<input type="text" class="form-control" name="remark" id="remark" value="${loginMember.remark}">
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
			subInfoAll("member");
		}
		
		function subInfoAll(name) {
			var path = "${pageContext.request.contextPath}/"+name+"/"+name+"_edit";
			var openid = $("#openId").val();
			$.ajax({
				url : path,
				type : 'post',
				data : $("#dataForm").serialize(),
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						alert(data.msg);
						window.location.href="${pageContext.request.contextPath}/web/checkUser?username="
								+ $("#username").val() + "&password=" + $("#password").val() ;
					} else {
						alert(data.msg);
					}
				},
				error : function(transport) {
					alert("系统产生错误,请检查填写的数据是否正确，或者联系管理员!");
				}
			});
		}
	</script>
	
		
</html>