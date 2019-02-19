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
						<form class="form-inline" role="form">
							<input type="text" class="form-control" id="searchName"
								   placeholder="请输入姓名">
							<input type="text" class="form-control" id="searchPhone"
								   placeholder="请输入手机号">
							<select class="form-control "  id="memberRole"  name="memberRole" style="display: inline">
								<option value="">全部角色</option>
							</select>
							<button id="btn_edit" type="button" class="btn btn-default" onclick="queryData()">
								查询
							</button>
							<button id="btn_edit" type="button" class="btn btn-default" onclick="updateData()">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true" ></span>修改
							</button>
							<button id="btn_delete" type="button" class="btn btn-default" onclick="delDish()">
								<span class="glyphicon glyphicon-remove" aria-hidden="true" ></span>删除
							</button>
							<button id="btn_delete" type="button" class="btn btn-default" onclick="addInfo()">
								<span class="glyphicon glyphicon-plus" aria-hidden="true" ></span>新增
							</button>
						</form>
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
					<h4 class="modal-title" id="myModalLabel">会员管理</h4>
				</div>
				<div class="modal-body">
					<form id="dataForm">
					 <input  class="form-control" name="memberId" id="memberId" type="hidden"></input>
						<div class="form-group">
							<label for="recipient-name" class="control-label">会员名称:</label> <input
								type="text" class="form-control" name="name" id="name">
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">会员角色:</label>
							<select class="form-control"  id ="role" name="role">
					           <option value="1">管理员</option>
					           <option value="2">测试</option>
					           <option value="3">测试2</option>
					        </select>	
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">手机号:</label> <input
								type="text" class="form-control" name="phone" id="phone" onchange="checkTelephone()">
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">身份证:</label>
							<input type="text" class="form-control" name="idCard" id="idCard" onchange="isCardNo()">
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">备注:</label>
							<input type="text" class="form-control" name="remark" id="remark">
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
			subInfoAll("member");
		}
			
		function delDish(){
			deleteDataById("member");
		}
		$(function(){
            getRoleType();
            getRoleTypeMenu();
			$('#infoTable').bootstrapTable({
				url : '${basePath}/member/member_query', // 请求后台的URL（*）
				method : 'get', // 请求方式（*）
				toolbar : '#toolbar', // 工具按钮用哪个容器
				cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
				sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
				pagination : true, // 是否显示分页（*）
				pageNumber: 1,    //如果设置了分页，首页页码
				pageSize: 50,                       //每页的记录行数（*）
				pageList: [100,300,600],        //可供选择的每页的行数（*）
				queryParamsType:'',
                queryParams : function(params) {
                    params.searchName = $("#searchName").val();
                    params.searchPhone = $("#searchPhone").val();
                    params.memberRole = $("#memberRole").val();
                    return params;
                },
				singleSelect    : false,
				showRefresh : true, // 是否显示刷新按钮
				clickToSelect : true, // 是否启用点击选中行
				showToggle : false, // 是否显示详细视图和列表视图的切换按钮
				search:true,   //是否启用搜索框

				columns : [ {
					checkbox : true
				},{
					field : 'memberId', visible: false
				},{
                    field : 'role',   visible: false
                },{
                    field : 'memberCode',   title : '会员编号',   align: 'center', valign: 'middle'
                },{
                    field : 'name',   title : '姓名',   align: 'center', valign: 'middle'
                },{
					field : 'roleName',   title : '角色',  align: 'center',   valign: 'middle'
                },{
                    field : 'phone',   title : '手机号',   align: 'center', valign: 'middle'
                },{
                    field : 'idCard',   title : '身份证',   align: 'center', valign: 'middle'
                },{
                    field : 'openId',   title : '微信openId',   align: 'center', valign: 'middle'
				},{
                //     field : 'consumeDetaile',   title : '最近一次消费',   align: 'center', valign: 'middle'
                // },{
					field : 'remark',   title : '备注',  align: 'center',   valign: 'middle'}
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
                        $("#role").html(html);
                    } else {
                        alert("发生异常，请联系管理员");
                    }
                },
                error : function(transport) {
                    alert(data.msg);
                }
            });
        }

        function getRoleTypeMenu(){
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
                        $("#memberRole").html(html);
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
                ids += ","+$("#infoTable").bootstrapTable('getSelections')[i].memberId ;
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

        function queryData(){
            $("#infoTable").bootstrapTable("refresh");
        }
		

		// 验证身份证
		function isCardNo() {
			var card = $("#idCard").val();
		    var pattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
		    if (! pattern.test(card)) {
         		alert("身份证不合法,请重填");
         		$("#idCard").val("")
             return false;
        		 } 
		    check_unique('idCard');
		}
		
		function checkTelephone(){
			 var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
	         if (!myreg.test($("#phone").val())) {
	         		alert("手机号不合法,请重填");
	         		$("#phone").val("")
	             return false;
	         } 
	         check_unique('phone');
		}
		
		function check_unique(filed){
			var  value = $("#"+filed).val();
			var path="${basePath}/web/check_unique"
			 $.ajax({
				 url : path,
					type : 'post',
					data : { value:value  , colName :filed},
					dataType : 'json',
					success : function(data) {
						if (!data.success)  {
							alert(data.msg);
							$("#"+filed).val("");
						}
					},
					error : function(transport) {
						alert("系统产生错误,请联系管理员!");
					}
			 })
		}
	</script>
	
		
</html>