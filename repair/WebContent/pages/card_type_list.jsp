<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html id="a1">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>卡分类管理</title>
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
					 <input  class="form-control" name="typeId" id="typeId" type="hidden"></input>
						<div class="form-group">
							<label for="recipient-name" class="control-label">类型名称:</label> <input
								type="text" class="form-control" name="typeName" id="typeName" >
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">类型代码:</label> <input
								type="text" class="form-control" name="typeCode" id="typeCode">
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">赠送金额:</label>
								<input type="text" class="form-control" name="bonus" id="bonus">
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
			subInfoAll("card_type");
		}
			
		function delDish(){
			deleteDataById("card_type");
		}
		$(function(){
			$('#infoTable').bootstrapTable({
				url : '${basePath}/card_type/card_type_query', // 请求后台的URL（*）
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
					field : 'typeId', visible: false
				},{
					field : 'typeName',   title : '类型名称',  align: 'center',   valign: 'middle'
				},{
                    field : 'typeCode',   title : '类型代码',   align: 'center', valign: 'middle'
                },{
					field : 'bonus',   title : '赠送金额',  align: 'center',   valign: 'middle'
                },{
                    field : 'remark',   title : '备注',  align: 'center',   valign: 'middle'}
					],
				silent : true, // 刷新事件必须设置
			});
		});


        function deleteDataById(name) {
            var ids = "";
            for(var i in $("#infoTable").bootstrapTable('getSelections')){
                ids += ","+$("#infoTable").bootstrapTable('getSelections')[i].typeId ;
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