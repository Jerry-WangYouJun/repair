<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html id="a1">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>消费管理</title>
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
							<div style="margin-bottom: 1px;">
								<input type='text' class="form-control" id='searchOrderDate' placeholder="请选择日期" readonly="readonly"/>
								<input type="text" class="form-control" id="searchOrderNumber"
									   placeholder="请输入工单号">
								<input type="text" class="form-control" id="searchCardNumber"
									   placeholder="请输入卡号">
								<input type="text" class="form-control" id="searchOrderContent"
									   placeholder="请输入服务项目">
								<input type="text" class="form-control" id="searchName"
									   placeholder="请输入会员名">
								<select class="form-control "  id="orderTypes"  name="orderTypes" style="display: inline">
									<option value="">全部状态</option>
									<option value="consume">消费</option>
									<option value="recharge">充值</option>
								</select>
								<button id="btn_edit" type="button" class="btn btn-default" onclick="queryData()">
									查询
								</button>
							</div>

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
								type="text" class="form-control" name="phone" id="phone">
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">身份证:</label>
							<input type="text" class="form-control" name="idCard" id="idCard">
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
            //getRoleType();
			$('#infoTable').bootstrapTable({
				url : '${basePath}/record/record_query', // 请求后台的URL（*）
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
                    params.searchCardNumber = $("#searchCardNumber").val();
                    params.searchOrderNumber = $("#searchOrderNumber").val();
                    params.searchOrderDate = $("#searchOrderDate").val();
                    params.searchOrderContent = $("#searchOrderContent").val();
                    params.searchName = $("#searchName").val();
                    params.orderTypes = $("#orderTypes").val();
                    return params;
                },
				singleSelect    : false,
				showRefresh : true, // 是否显示刷新按钮
				clickToSelect : true, // 是否启用点击选中行
				showToggle : false, // 是否显示详细视图和列表视图的切换按钮

				columns : [ {
					checkbox : true
				},{  
				    title: '序号',  
				    field: '',  
				    align: 'center',
				    formatter: function (value, row, index) {  
				        return index+1;  
				    }  
				},{
					field : 'recordId', visible: false
				},{
                    field : 'orderNumber',   title : '工单编号',   align: 'center', valign: 'middle'
                },{
                    field : 'cardNumber',   title : '卡号',   align: 'center', valign: 'middle'
                },{
                    field : 'orderContent',   title : '服务项目',  align: 'center',   valign: 'middle',
                    formatter : function(value, row, index) {
                        if (row.purchaseType == 'consume') {
                            return value;
                        } else if (row.purchaseType == 'recharge') {
                            return "会员卡充值";
                        }
                    }
                },{
					field : 'orderDate',   title : '日期',  align: 'center',   valign: 'middle'
                },{
                    field : 'purchaseMoney',   title : '金额',   align: 'center', valign: 'middle'
                },{
                    field : 'name',   title : '会员名称',   align: 'center', valign: 'middle'
                },{
                    field : 'balance',   title : '余额',   align: 'center', valign: 'middle'
                },{
                    field : 'purchaseType',   title : '类型',   align: 'center', valign: 'middle',
                    formatter : function(value, row, index) {
                        if (value == 'consume') {
                            return "消费";
                        } else if (value == 'recharge') {
                            return "充值";
                        }
                    }
				},{
					field : 'remark',   title : '备注',  align: 'center',   valign: 'middle'}
					],
				silent : true, // 刷新事件必须设置
			});
		});

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

        $('#searchOrderDate').datetimepicker({
            format: 'yyyy-mm-dd',
            language:"zh-CN",
            autoclose :true ,
            todayHighlight : true,
            todayBtn : true,
            minuteStep: 0,
            minView : 2,
            clearBtn:true,
            initialDate:new Date()
        });

        function queryData(){
            $("#infoTable").bootstrapTable("refresh");
        }
		
		
	</script>
	
		
</html>