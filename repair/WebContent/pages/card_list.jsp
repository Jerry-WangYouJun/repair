<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html id="a1">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>卡管理</title>
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
								<input type="text" class="form-control" id="searchCardNumber"
									   placeholder="请输入卡号">
								<input type="text" class="form-control" id="searchCardName"
									   placeholder="请输入卡名称">
								<input type="text" class="form-control" id="searchName"
									   placeholder="请输入持卡人姓名">
							</div>
							<br>
							<select class="form-control "  id="cardTypes"  name="cardTypes" style="display: inline">
								<option value="">全部卡类型</option>
							</select>
							<select class="form-control "  id="cardStatuss"  name="cardStatuss" style="display: inline">
								<option value="">全部状态</option>
								<option value="0">有效</option>
								<option value="1">无效</option>
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
							<button id="btn_delete" type="button" class="btn btn-default" onclick="moveData()">
								<span class="glyphicon glyphicon-forward" aria-hidden="true" ></span>分配
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
					<h4 class="modal-title" id="myModalLabel">卡管理</h4>
				</div>
				<div class="modal-body">
					<form id="dataForm">
					 <input  class="form-control" name="cardId" id="cardId" type="hidden"></input>
						<div class="form-group">
							<label for="recipient-name" class="control-label">卡名称:</label> <input
								type="text" class="form-control" name="cardName" id="cardName" >
						</div>

						<div class="form-group">
							<label for="message-text" class="control-label">卡类型:</label>
							<select class="form-control"  id ="cardType" name="cardType">
					           <option value="1">管理员</option>
					           <option value="2">测试</option>
					           <option value="3">测试2</option>
					        </select>	
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">状态:</label>
							<select class="form-control"  id ="cardStatus" name="cardStatus">
								<option value="">请选择状态</option>
								<option value="0">有效</option>
								<option value="1">无效</option>
							</select>
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">备注:</label> <input
								type="text" class="form-control" name="remark" id="remark" >
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


	<div class="modal fade" id="myModal-Move" tabindex="-2" role="dialog"
		 aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="">
			<div class="modal-content">
				<div class="modal-body">
					<table id="memberTable"> </table>
					<div id="memberToolbar" class=" form-inline"  >
						<button id="btn_edit" type="button" class="btn btn-default " onclick="moveCardByMember()" style="display: inline">
							<span class="glyphicon glyphicon-pencil" aria-hidden="true" ></span>分配
						</button>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>




	<div class="modal fade" id="myModal-Consume" tabindex="-2" role="dialog"
		 aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="height: ">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalConsumeLabel">消费管理</h4>
				</div>
				<div class="modal-body">
					<form id="dataConsumeForm">
						<input type="hidden" class="form-control" name="moneyBalance" id="moneyBalanceConsume">
						<div class="form-group">
							<label for="recipient-name" class="control-label">卡号:</label> <input
								type="text" class="form-control" name="cardNumber" id="cardNumber">
						</div>

						<div class="form-group">
							<label for="message-text" class="control-label">消费金额:</label>
							<input type="text" class="form-control" name="orderMoney" id="orderMoney">
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">服务内容:</label>
							<input type="text" class="form-control" name="orderContent" id="orderContent" >
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">时长(小时):</label>
							<input type="text" class="form-control" name="duration" id="duration" >
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">备注:</label> <input
								type="text" class="form-control" name="remark" id="remarkConsume" >
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="subConsumeInfo()">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>


	<div class="modal fade" id="myModal-Recharge" tabindex="-2" role="dialog"
		 aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="height: ">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalRechargeLabel">充值管理</h4>
				</div>
				<div class="modal-body">
					<form id="dataRechargeForm">
						<input type="hidden" class="form-control" name="moneyBalance" id="moneyBalanceRecharge">
						<div class="form-group">
							<label for="recipient-name" class="control-label">卡号:</label> <input
								type="text" class="form-control" name="cardNumber" id="cardNumberRecharge" readonly="readonly">
						</div>

						<div class="form-group">
							<label for="message-text" class="control-label">充值金额:</label>
							<input type="text" class="form-control" name="orderMoney" id="orderMoneyRecharge" >
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">服务内容:</label>
							<input type="text" class="form-control" name="orderContent" id="orderContentRecharge" value="会员卡充值">
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">备注:</label> <input
								type="text" class="form-control" name="remark" id="remarkConsumeRecharge" >
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="subRechargeInfo()">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>







</body>
<script type="text/javascript">

		function queryData(){
			$("#infoTable").bootstrapTable("refresh");
		}


		function AddFunctionAlty(value,row,index) {
			return [
				'<button   type="button"  class="consume  btn btn-default  btn-sm" >扣费</button>'+"&nbsp;&nbsp;&nbsp;"+
				'<button   type="button"  class="recharge btn btn-default  btn-sm" >续费</button>'
			];
		}
		window.operateEvents = {
			"click .consume":function (e,value,row,index) {
				console.log(row.cardNumber);
                $("#moneyBalanceConsume").val(row.cardBalance);
				$("#cardNumber").val(row.cardNumber);
                $("#myModal-Consume").modal("show");
			},
			"click .recharge":function (e,value,row,index) {
                console.log(row.cardNumber);
                $("#moneyBalanceRecharge").val(row.cardBalance);
                $("#cardNumberRecharge").val(row.cardNumber);
                $("#orderContentRecharge").val("会员卡充值");
                $("#myModal-Recharge").modal("show");
			}
		};

		function subConsumeInfo() {
			var moneyAfter = $("#moneyBalanceConsume").val();
            var money = $("#orderMoney").val();
            if(moneyAfter<money){
                alert("余额不足，请充值");
                return false;
			}
            var url = "${basePath}/card/card_consume_admin";
            $.ajax({
                url : url,
                type : 'post',
                data : $("#dataConsumeForm").serialize(),
                dataType : 'json',
                success : function(data) {
                    if (data.success) {
                        alert(data.msg);
                        $("#infoTable").bootstrapTable("refresh");
                        $("#myModal-Consume").modal("hide");
                        $(':input', '#dataConsumeForm').not(':button,:submit,:reset').val(
                            '').removeAttr('checked').removeAttr('checked');
                    } else {
                        alert(data.msg);
                    }
                },
                error : function(transport) {
                    alert(data.msg);
                }
            });
        }


        function subRechargeInfo() {
            var url = "${basePath}/card/card_recharge";
            $.ajax({
                url : url,
                type : 'post',
                data : $("#dataRechargeForm").serialize(),
                dataType : 'json',
                success : function(data) {
                    if (data.success) {
                        alert(data.msg);
                        $("#infoTable").bootstrapTable("refresh");
                        $("#myModal-Recharge").modal("hide");
                        $(':input', '#dataRechargeForm').not(':button,:submit,:reset').val(
                            '').removeAttr('checked').removeAttr('checked');
                    } else {
                        alert(data.msg);
                    }
                },
                error : function(transport) {
                    alert(data.msg);
                }
            });
        }


		function subInfo(){
			subInfoAll("card");
		}
			
		function delDish(){
			deleteDataById("card");
		}
		$(function(){
            getCardType();
            getCardTypeMenu();
			$('#infoTable').bootstrapTable({
				url : '${basePath}/card/card_query', // 请求后台的URL（*）
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
                    params.searchCardName = $("#searchCardName").val();
                    params.searchName = $("#searchName").val();
                    params.cardTypes = $("#cardTypes").val();
                    params.cardStatuss = $("#cardStatuss").val();
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
					field : 'cardId', visible: false
				},{
                    field : 'cardNumber',   title : '卡号',   align: 'center', valign: 'middle'
				},{
                    field : 'cardName',   title : '卡名称',  align: 'center',   valign: 'middle'
                },{
                    field : 'typeName',   title : '类型',   align: 'center', valign: 'middle'
                },{
                    field : 'cardBalance',   title : '余额',  align: 'center',   valign: 'middle'
                },{
                    field : 'memberName',   title : '持卡人',   align: 'center', valign: 'middle'
                },{
                    field : 'cardStatus',   title : '状态',   align: 'center', valign: 'middle',
                    formatter : function(value, row, index) {
                        if (value == '0') {
                            return "有效";
                        } else if (value == '1') {
                            return "无效";
                        }
                    }
                },{
                    field : 'remark',   title : '备注',   align: 'center', valign: 'middle'
				},{
                    field : 'operate',   title : '操作',   align: 'center', valign: 'middle',events:operateEvents,formatter:AddFunctionAlty
				}
					],
				silent : true, // 刷新事件必须设置
			});
		});





        function moveData() {
            var selectRow =  $("#infoTable").bootstrapTable('getSelections')[0];
            console.info($("#infoTable").bootstrapTable('getSelections').length);
            if($("#infoTable").bootstrapTable('getSelections').length  == 0){
                alert("选择要分配的卡！");
                return false;
            }
            $("#myModal-Move").modal("show");
        }


        function moveCardByMember(){
            var ids = "";
            for(var i in $("#infoTable").bootstrapTable('getSelections')){
                ids +=  ","+$("#infoTable").bootstrapTable('getSelections')[i].cardId ;
            }
            if(ids == ""){
                alert("请选择想要转移的会员！");
                return false;
			}
            ids=ids.substring(1);
            var del = confirm("确认转移？");
            if (!del) {
                return false;
            }
            var id = $("#memberTable").bootstrapTable('getSelections')[0].memberId;
            if (id > 0) {
                var url = "${basePath}/card/card_move";
                $.ajax({
                    url : url,
                    type : 'post',
                    data : {cardIds: ids , memberId : id },
                    dataType : 'json',
                    success : function(data) {
                        if (data.success) {
                            alert(data.msg);
                            $("#infoTable").bootstrapTable("refresh");
                            $("#myModal-Move").modal("hide");
                        } else {
                            alert(data.msg);
                        }
                    },
                    error : function(transport) {
                        alert(data.msg);
                    }
                });

            }
        }

        function closeMoveModel() {
            $("#myModal-Move").modal("hide");
            //$(':input', '#dataForm').not(':button,:submit,:reset').val(
            //                '').removeAttr('checked').removeAttr('checked');

        }

        $(function(){
            $('#memberTable').bootstrapTable({
                url : '${basePath}/member/member_query', // 请求后台的URL（*）
                method : 'get', // 请求方式（*）
                toolbar : '#memberToolbar', // 工具按钮用哪个容器
                cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
                pagination : true, // 是否显示分页（*）
                pageNumber: 1,    //如果设置了分页，首页页码
                pageSize: 50,                       //每页的记录行数（*）
                pageList: [100,300,600],        //可供选择的每页的行数（*）
                queryParamsType:'',
                singleSelect    : true,
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
                }
                ],
                silent : true, // 刷新事件必须设置
            });
        });


        function deleteDataById(name) {
            var ids = "";
            for(var i in $("#infoTable").bootstrapTable('getSelections')){
                ids += ","+$("#infoTable").bootstrapTable('getSelections')[i].cardId ;
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

        function getCardType(){
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
                        $("#cardTypes").html(html);
                    } else {
                        alert("发生异常，请联系管理员");
                    }
                },
                error : function(transport) {
                    alert(data.msg);
                }
            });
        }


		
		
	</script>
	
		
</html>