<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/media/css/style-metro.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/media/css/style.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/media/css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="${pageContext.request.contextPath}/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/media/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/media/css/daterangepicker.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/media/css/fullcalendar.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/media/css/jqvmap.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="${pageContext.request.contextPath}/media/css/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>
	<script src="${pageContext.request.contextPath}/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<title>Insert title here</title>
<script type="text/javascript">
		function getTreeData(datas){
			var htmlStr = '' ;
			for(var childNode in datas){
				if(datas[childNode].children.length>0){
					  htmlStr += '<li><a ' 
					  + getTabData(datas[childNode])
					  + 'style="padding-left:25px" href="javascript:;">' + datas[childNode].menu
					  + '<span class="arrow"></span></a>'
					  +'<ul class="sub-menu">' +  getTreeData(datas[childNode].children)
					  +'</ul></li>';
				}else{
					 htmlStr += '<li><a ' 
					  + getTabData(datas[childNode])
					  + ' style="padding-left:25px" href="javascript:;">' + datas[childNode].menu
					  + '</a></li>';
				}
			  }
			return htmlStr ;
		}
		
		function getTabData(node){
			return 'onclick="addTab(\''+node.attributes.urlType +'_' + node.attributes.agentId + '\',\''
			  +   node.attributes.priUrl + '\',\'' + node.text +'\')"'
		}
		
		function getMenu(name){
			$.ajax( {
				url : "${pageContext.request.contextPath}/treeindex/" + name,
				type : 'post',
				dataType : 'json',
				success : function(data) {
					 var htmlStr = getTreeData(data[0].children);
					 $("#" + name).html(htmlStr);
				},
				error : function(transport) {
					$.messager.alert('提示', "系统产生错误,请联系管理员!", "error");
				}
			});
		}
		$(function(){
			if("${groupId}"=="1" || "${groupId}"=="3"){
				getMenu("card");
				getMenu("kickback");
			}
			if("${groupId}"=="2" || "${groupId}"=="3"){
				getMenu("card_unicom");
				getMenu("kickback_unicom");
			}
		});
</script>
</head>
<body class="page-header-fixed">
	<!-- BEGIN HEADER -->
	<div class="header navbar navbar-inverse navbar-fixed-top">
		<!-- BEGIN TOP NAVIGATION BAR -->
		<div class="navbar-inner">
			<div class="container-fluid">
				<!-- BEGIN LOGO -->
				<a class="brand" href="#">
					 <span style="color:white;">  青岛丰宁贸易</span>
				</a>

				<a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
					<img src="${pageContext.request.contextPath}/media/image/menu-toggler.png" alt="" />
				</a>          

				<!-- END RESPONSIVE MENU TOGGLER -->            

				<!-- BEGIN TOP NAVIGATION MENU -->              
				<ul class="nav pull-right">
					<li class="dropdown user">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<img alt="" src="${pageContext.request.contextPath}/media/image/avatar1_small.jpg" />
						<span class="username">${sessionScope.user}</span>
						<i class="icon-angle-down"></i>
						</a>
						<ul class="dropdown-menu">
							<li><a href="#"><i class="icon-tasks"></i>待办事项</a></li>
							<li class="divider"></li>
							<li><a href="${pageContext.request.contextPath}/loginOut"><i class="icon-key"></i>注销用户</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
		<!-- END TOP NAVIGATION BAR -->
	</div>
	<!-- END HEADER -->

	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN SIDEBAR -->
		<div class="page-sidebar nav-collapse collapse">
			<!-- BEGIN SIDEBAR MENU -->        
			<ul class="page-sidebar-menu">
				<li>
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<div class="sidebar-toggler hidden-phone"></div>
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
				</li>

				<li class="">
					<a href="javascript:;">
						<span class="title">基本信息管理</span>
						<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li >
							<a href="#" style="padding-left:25px" onclick='addTab("user","${pageContext.request.contextPath}/pages/user_list.jsp","代理商管理")'>
							代理商管理</a>
						</li>
						<li >
							<a href="#" style="padding-left:25px" onclick='addTab("pac","${pageContext.request.contextPath}/pages/pac_list.jsp","套餐管理")'>
							套餐管理</a>
						</li>
					</ul>
				</li>

				<li >
					<a class="active" href="javascript:;">
					<!-- <i class="icon-sitemap"></i>  -->
					<span class="title">移动卡信息</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu" id="card">
						  
					</ul>
				</li>
				<li>
					<a class="active" href="javascript:;">
					<!-- <i class="icon-sitemap"></i>  -->
					<span class="title">移动返佣信息</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu" id="kickback">
						  
					</ul>	
				</li>
				<li >
					<a class="active" href="javascript:;">
					<!-- <i class="icon-sitemap"></i>  -->
					<span class="title">联通卡信息</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu" id="card_unicom">
						  
					</ul>
				</li>
				<li>
					<a class="active" href="javascript:;">
					<!-- <i class="icon-sitemap"></i>  -->
					<span class="title">联通返佣信息</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu" id="kickback_unicom">
						  
					</ul>	
				</li>
			</ul>

			<!-- END SIDEBAR MENU -->

		</div>

		<!-- END SIDEBAR -->

		<!-- BEGIN PAGE -->

		<div class="page-content">

			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->

			<div id="portlet-config" class="modal hide">

				<div class="modal-header">

					<button data-dismiss="modal" class="close" type="button"></button>

					<h3>Widget Settings</h3>

				</div>

				<div class="modal-body">

					Widget settings form goes here

				</div>

			</div>

			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->

			<!-- BEGIN PAGE CONTAINER-->

			<div class="container-fluid">


				<div id="dashboard">

					<div class="row-fluid">
							<div class="span12">
									<ul class="nav nav-tabs" style="margin:0px" role="tablist" id="deviceulid">
							
									</ul>
									<!-- 面板区 -->
									<div  id ="home" class="tab-content">
									
									</div>
									<!-- Advanced Tables -->
							</div>

					</div>

					<div class="clearfix"></div>


				</div>

			</div>

			<!-- END PAGE CONTAINER-->    

		</div>

		<!-- END PAGE -->

	</div>

	<!-- END CONTAINER -->

	<!-- BEGIN FOOTER -->

	<div class="footer">

		<div class="footer-inner">

			2018 &copy; 青岛丰宁贸易 

		</div>

		<div class="footer-tools">

			<span class="go-top">

			<i class="icon-angle-up"></i>

			</span>

		</div>

	</div>

	<!-- END FOOTER -->

	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<script src="${pageContext.request.contextPath}/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="${pageContext.request.contextPath}/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      
	<script src="${pageContext.request.contextPath}/media/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/media/js/jquery.blockui.min.js" type="text/javascript"></script>  
	<script src="${pageContext.request.contextPath}/media/js/jquery.cookie.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/media/js/jquery.uniform.min.js" type="text/javascript" ></script>
	<!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="${pageContext.request.contextPath}/media/js/jquery.vmap.js" type="text/javascript"></script>   
	<script src="${pageContext.request.contextPath}/media/js/jquery.vmap.russia.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/media/js/jquery.vmap.world.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/media/js/jquery.vmap.europe.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/media/js/jquery.vmap.germany.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/media/js/jquery.vmap.usa.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/media/js/jquery.vmap.sampledata.js" type="text/javascript"></script>  
	<script src="${pageContext.request.contextPath}/media/js/jquery.flot.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/media/js/jquery.flot.resize.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/media/js/jquery.pulsate.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/media/js/date.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/media/js/daterangepicker.js" type="text/javascript"></script>     
	<script src="${pageContext.request.contextPath}/media/js/jquery.gritter.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/media/js/fullcalendar.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/media/js/jquery.easy-pie-chart.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/media/js/jquery.sparkline.min.js" type="text/javascript"></script>  

	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN PAGE LEVEL SCRIPTS -->

	<script src="${pageContext.request.contextPath}/media/js/app.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/media/js/index.js" type="text/javascript"></script>        

	<!-- END PAGE LEVEL SCRIPTS -->  

	<script>
	
		

		jQuery(document).ready(function() {    

		   App.init(); // initlayout and core plugins
		   Index.init();
		   Index.initCalendar(); // init index page's custom scripts
		   Index.initCharts(); // init index page's custom scripts
		   Index.initChat();
		   Index.initMiniCharts();
		   Index.initDashboardDaterange();
		   Index.initIntro();
		});
		
		/**
         * 增加标签页
         */
        function addTab(  tabName ,tabUrl , title) {
            //option:
            //tabMainName:tab标签页所在的容器
            //tabName:当前tab的名称
            //tabTitle:当前tab的标题
            //tabUrl:当前tab所指向的URL地址
            var exists = checkTabIsExists('deviceulid', tabName);
            if(exists){
                $("#tab_a_"+tabName).click();
            } else {
                $("#deviceulid").append('<li id="tab_li_'+tabName+'"><a href="#tab_content_'+tabName+'" data-toggle="tab" id="tab_a_'+tabName+'" ><button class="close closeTab" type="button" onclick="closeTab(this);"></button>'+title+'</a></li>');
                //固定TAB中IFRAME高度
                mainHeight = $(document.body).height() - 5;
                var content = '';
                if(content){
                    content = option.content;
                } else {
                    content = '<iframe id="myframe_'+tabName+'" src="' + tabUrl + '" width="100%"  height="780px"  frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe>';
                }
                $("#home").append('<div id="tab_content_'+tabName+'" role="tabpanel" class="tab-pane" id="'+ tabName+'">'+content+'</div>');
                $("#tab_a_"+tabName).click();
            }
        }
         function toggleTab(obj){
      		   $("#tab_content_"+obj).click(function (e) {
      			   e.preventDefault();
      			   $("#tab_a_" + obj).tab('show');
      	    	  t
         		});
         }
        
         
        /**
         * 关闭标签页
         * @param button
         */
        function closeTab (button) {
             
            //通过该button找到对应li标签的id
            var li_id = $(button).parent().parent().attr('id');
            var id = li_id.replace("tab_li_","");
             
            //如果关闭的是当前激活的TAB，激活他的前一个TAB
            if ($("li.active").attr('id') == li_id) {
                $("li.active").prev().find("a").click();
            }
             
            //关闭TAB
            $("#" + li_id).remove();
            $("#tab_content_" + id).remove();
        };
         
        /**
         * 判断是否存在指定的标签页
         * @param tabMainName
         * @param tabName
         * @returns {Boolean}
         */
        function checkTabIsExists(tabMainName, tabName){
            var tab = $("#"+tabMainName+" > #tab_li_"+tabName);
            //console.log(tab.length)
            return tab.length > 0;
        }

	</script>

	<!-- END JAVASCRIPTS -->

</body>
</html>