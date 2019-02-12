<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html id="a1">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<style type="text/css">
  .panel-body {
    padding: 0px !important; 
}
</style>	

</head>
<body id="a2">
	<div >
			<div >
				  <div class="panel-body" id="a3" style="display:block">
				  	    <table id="infoTable"> </table>
					<div id="toolbar" class="btn-group">  
			            <button id="btn_edit" type="button" class="btn btn-default" onclick="updateData()">  
			                <span class="glyphicon glyphicon-pencil" aria-hidden="true" ></span>修改  
			            </button>  
			            <button id="btn_delete" type="button" class="btn btn-default" onclick="delDish()">  
			                <span class="glyphicon glyphicon-remove" aria-hidden="true" ></span>删除  
			            </button>  
			            <button id="btn_delete" type="button" class="btn btn-default" onclick="addInfo()">  
			            	<span class="glyphicon glyphicon-plus" aria-hidden="true" ></span>新增
			            </button>
			        </div>  
				  </div>
			</div>
		</div>
		
	<div class="modal fade" id="myModal" tabindex="-2" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="height: ">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">用户管理</h4>
				</div>
				<div class="modal-body">
					<form id="dataForm">
					 <input  class="form-control" name="userId" id="userId" type="hidden"></input>
						<div class="form-group">
							<label for="recipient-name" class="control-label">登录名:</label> <input
								type="text" class="form-control" name="username" id="username" disabled>
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">密码:</label> <input
								type="texts" class="form-control" name="password" id="password">
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">用户名称:</label>
								<input type="text" class="form-control" name="name" id="name">
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">用户角色:</label>
							<select class="form-control"  id ="roleId" name="roleId">
					           <option value="1">管理员</option>
					           <option value="2">测试</option>
					           <option value="3">测试2</option>
					        </select>	
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="subInfo()">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</body>
<script type="text/javascript">
		function subInfo(){
			subInfoAll("user");
		}
			
		function delDish(){
			deleteDataById("user");
		}
		$(function(){
            getRoleType();
			$('#infoTable').bootstrapTable({
				url : '${basePath}/user/user_query', // 请求后台的URL（*）
				method : 'get', // 请求方式（*）
				toolbar : '#toolbar', // 工具按钮用哪个容器
				cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
				sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
				pagination : true, // 是否显示分页（*）
				pageNumber: 1,    //如果设置了分页，首页页码
				pageSize: 50,                       //每页的记录行数（*）
				pageList: [100,300,600],        //可供选择的每页的行数（*）
				queryParamsType:'',
				singleSelect    : false,
				showRefresh : true, // 是否显示刷新按钮
				clickToSelect : true, // 是否启用点击选中行
				showToggle : false, // 是否显示详细视图和列表视图的切换按钮
				search:true,   //是否启用搜索框

				columns : [ {
					checkbox : true
				},{
					field : 'userId', visible: false
				},{
					field : 'username',   title : '用户账号',  align: 'center',   valign: 'middle'
				},{
                    field : 'name',   title : '用户名称',   align: 'center', valign: 'middle'
                },{
					field : 'roleName',   title : '用户角色',  align: 'center',   valign: 'middle'}
					],
				silent : true, // 刷新事件必须设置
			});
		});


        function getRoleType(){
            var html = "";
            $.ajax({
                url : '${basePath}/user/getRoleType',
                type : 'post',
                data : {},
                dataType : 'json',
                success : function(data) {
                    if (data.success) {
                        var dataInfo = data.dataInfo;
                        html +="<option value=''>请选择角色</option>";
                        for(var i = 0;i<dataInfo.length;i++){
                            html+="<option value='"+dataInfo[i].roleId+"'>"+dataInfo[i].roleName+"</option>";
                        }
                        $("#roleId").html(html);
                    } else {
                        alert("发生异常，请联系管理员");
                    }
                },
                error : function(transport) {
                    alert(data.msg);
                }
            });
        }

        function deleteDataById(name) {
            var ids = "";
            for(var i in $("#infoTable").bootstrapTable('getSelections')){
                ids += ","+$("#infoTable").bootstrapTable('getSelections')[i].userId ;
            }
            if("" == ids || null == ids){
                alert("请选择想要删除的数据");
                return false;
            }
            ids=ids.substring(1);

            var del = confirm("确认删除？");
            if (!del) {
                return false;
            }

            if (ids != null && ids!="") {
                var path = "${basePath}/"+name+"/"+name+"_delete";
                $.ajax({
                    url : path,
                    type : 'post',
                    data:{'ids':ids},
                    dataType : 'json',
                    success : function(data) {
                        if (data.success) {
                            alert( data.msg);
                            $("#infoTable").bootstrapTable("refresh");
                        } else {
                            alert( data.msg);
                        }

                    },
                    error : function(transport) {
                        alert( "系统产生错误,请联系管理员!");
                    }
                });
            }
        }
		
		
	</script>
	
		
</html>