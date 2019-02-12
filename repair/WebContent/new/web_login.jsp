<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html id="a1">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>用户登陆</title>
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
					<div><h3>会员登陆</h3></div>
					<form id="dataForm" action="${pageContext.request.contextPath}/web/checkUser">
					 <input  class="form-control" name="role" id="role"s type="hidden" value="${loginMember.role}"></input>
						<div class="form-group">
							<label for="recipient-name" class="control-label">登陆帐号:</label> <input
								type="text" class="form-control" name="username" id="username" value="${loginUser.username}">
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">登录密码:</label> <input
								type="password" class="form-control" name="password" id="password" value="${loginUser.password}">
						</div>
						<div style="color:red;">${msg }</div>
						<div class="form-group">
							
							<button type="submit" class="btn btn-primary" >提交</button>
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
		
		function checkin() {
			var path = "${pageContext.request.contextPath}/web/checkUser";
			
		}
	</script>
	
		
</html>