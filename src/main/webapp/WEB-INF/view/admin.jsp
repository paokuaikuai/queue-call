<%@ page language="java" pageEncoding="utf-8"%>
<%  
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);   
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>${_systemName}</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link type="images/x-icon" rel="shortcut icon" href="${_path}/custom/assets/favicon.ico">
		
		<!--[if !IE]> -->
		<link rel="stylesheet" href="${_path}/assets/css/pace.css" />
		<script data-pace-options='{ "ajax": true, "document": true, "eventLag": false, "elements": false }' src="${_path}/assets/js/pace.js"></script>
		<!-- <![endif]-->

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="${_path}/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="${_path}/assets/css/font-awesome.css" />

		<!-- text fonts -->
		<link rel="stylesheet" href="${_path}/assets/css/ace-fonts.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="${_path}/assets/css/ui.jqgrid.css" />
		<link rel="stylesheet" href="${_path}/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
		
		<!-- 自定义样式，表单多选框 -->
		<link rel="stylesheet" href="${_path}/custom/assets/ace.custom.css" />
		
		<link rel="stylesheet" href="${_path}/assets/css/my.css" />
		
		<!--[if lte IE 9]>
			<link rel="stylesheet" href="${_path}/assets/css/ace-part2.css" class="ace-main-stylesheet" />
		<![endif]-->

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${_path}/assets/css/ace-ie.css" />
		<![endif]-->

		<!-- ace settings handler -->
		<script src="${_path}/assets/js/ace-extra.js"></script>

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="${_path}/assets/js/html5shiv.js"></script>
		<script src="${_path}/assets/js/respond.js"></script>
		<![endif]-->
	</head>

	<body class="no-skin">
		<!-- #section:basics/navbar.layout -->
		<div id="navbar" class="navbar navbar-default">
			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

			<div class="navbar-container" id="navbar-container">
				<!-- #section:basics/sidebar.mobile.toggle -->
				<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
					<span class="sr-only">Toggle sidebar</span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>
				</button>

				<!-- /section:basics/sidebar.mobile.toggle -->
				<div class="navbar-header pull-left">
					<!-- #section:basics/navbar.layout.brand -->
					<a href="#" class="navbar-brand">
						<small>
							${_systemAdminName}
						</small>
					</a>

					<!-- /section:basics/navbar.layout.brand -->

					<!-- #section:basics/navbar.toggle -->

					<!-- /section:basics/navbar.toggle -->
				</div>

				<!-- #section:basics/navbar.dropdown -->
				<div class="navbar-buttons navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<!--  -->
						

						<!-- #section:basics/navbar.user_menu -->
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="${_path}/assets/avatars/user.jpg" alt="Jason's Photo" />
								<span class="user-info">
									<small>欢迎光临,</small>
									${userName}
								</span>

								<i class="ace-icon fa fa-caret-down"></i>
							</a>

							<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a id="_btnExit" href="#">
										<i class="ace-icon fa fa-power-off"></i>
										退出
									</a>
								</li>
							</ul>
						</li>

						<!-- /section:basics/navbar.user_menu -->
					</ul>
				</div>

				<!-- /section:basics/navbar.dropdown -->
			</div><!-- /.navbar-container -->
		</div>

		<!-- /section:basics/navbar.layout -->
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<!-- #section:basics/sidebar -->
			<div id="sidebar" class="sidebar                  responsive">
				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
				</script>

				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						<button class="btn btn-success">
							<i class="ace-icon fa fa-signal"></i>
						</button>

						<button class="btn btn-info">
							<i class="ace-icon fa fa-pencil"></i>
						</button>

						<!-- #section:basics/sidebar.layout.shortcuts -->
						<button class="btn btn-warning">
							<i class="ace-icon fa fa-users"></i>
						</button>

						<button class="btn btn-danger">
							<i class="ace-icon fa fa-cogs"></i>
						</button>

						<!-- /section:basics/sidebar.layout.shortcuts -->
					</div>

					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span>

						<span class="btn btn-info"></span>

						<span class="btn btn-warning"></span>

						<span class="btn btn-danger"></span>
					</div>
				</div><!-- /.sidebar-shortcuts -->

				<ul class="nav nav-list">
				</ul><!-- /.nav-list -->

				<!-- #section:basics/sidebar.layout.minimize -->
				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>

				<!-- /section:basics/sidebar.layout.minimize -->
				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
				</script>
			</div>

			<!-- /section:basics/sidebar -->
			<div class="main-content">
				<div class="main-content-inner">
					<!-- #section:basics/content.breadcrumbs -->
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="#">首页</a>
							</li>
						</ul><!-- /.breadcrumb -->

						<!-- #section:basics/content.searchbox -->

						<!-- /section:basics/content.searchbox -->
					</div>

					<!-- /section:basics/content.breadcrumbs -->
					<div class="page-content" id="page-content">
						<!-- #section:settings.box -->

						<!-- /section:settings.box -->
						<div class="page-content-area" data-ajax-content="true">
							<!-- ajax content goes here -->
						</div><!-- /.page-content-area -->
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

			<div class="footer">
				<div class="footer-inner">
					<!-- #section:basics/footer -->
					<div class="footer-content">
						<span class="bigger-120">
							<span class="blue bolder">${_systemAdminName}</span>
							 &copy; 2017
						</span>

						&nbsp; &nbsp;
						<span class="action-buttons">
							<a href="#">
								<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-facebook-square text-primary bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-rss-square orange bigger-150"></i>
							</a>
						</span>
					</div>

					<!-- /section:basics/footer -->
				</div>
			</div>

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${_path}/assets/js/jquery.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='${_path}/assets/js/jquery1x.js'>"+"<"+"/script>");
		</script>
		<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${_path}/assets/js/jquery.mobile.custom.js'>"+"<"+"/script>");
		</script>
		<script src="${_path}/assets/js/bootstrap.js"></script>

		<!-- ace scripts -->
		<script src="${_path}/assets/js/ace/elements.scroller.js"></script>
		<script src="${_path}/assets/js/ace/elements.colorpicker.js"></script>
		<script src="${_path}/assets/js/ace/elements.fileinput.js"></script>
		<script src="${_path}/assets/js/ace/elements.typeahead.js"></script>
		<script src="${_path}/assets/js/ace/elements.wysiwyg.js"></script>
		<script src="${_path}/assets/js/ace/elements.spinner.js"></script>
		<script src="${_path}/assets/js/ace/elements.treeview.js"></script>
		<script src="${_path}/assets/js/ace/elements.wizard.js"></script>
		<script src="${_path}/assets/js/ace/elements.aside.js"></script>
		<!-- 修改默认首页 -->
		<script id="_ace" src="${_path}/custom/assets/ace.js?v" data-path="${defaultPage}"></script>
		<!-- 切换菜单处理 -->
		<script id="_ajaxContent" src="${_path}/custom/assets/ace.ajax-content.js?v" data-path="${_path}"></script>
		<!-- 权限处理 -->
		<script id="_permission" src="${_path}/custom/jquery.permission.min.js?v" data="${sessionUserNoPermissions}"></script>
		<script src="${_path}/assets/js/ace/ace.touch-drag.js"></script>
		<script src="${_path}/assets/js/ace/ace.sidebar.js"></script>
		<script src="${_path}/assets/js/ace/ace.sidebar-scroll-1.js"></script>
		<script src="${_path}/assets/js/ace/ace.submenu-hover.js"></script>
		<script src="${_path}/assets/js/ace/ace.widget-box.js"></script>
		<script src="${_path}/assets/js/ace/ace.settings.js"></script>
		<script src="${_path}/assets/js/ace/ace.settings-rtl.js"></script>
		<script src="${_path}/assets/js/ace/ace.settings-skin.js"></script>
		<script src="${_path}/assets/js/ace/ace.widget-on-reload.js"></script>
		<script src="${_path}/assets/js/ace/ace.searchbox-autocomplete.js"></script>

		<link rel="stylesheet" href="${_path}/assets/js/bootstrap-table/bootstrap-table.min.css" />
		<script src="${_path}/assets/js/bootstrap-table/bootstrap-table.min.js"></script>
		<script src="${_path}/assets/js/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

		<script src="${_path}/custom/zq.fullscreen.js"></script>
		<script src="${_path}/custom/zq.utils.js"></script>
		<script src="${_path}/custom/zq.websocket.js"></script>
		<script src="${_path}/custom/zq.queue.list.js"></script>
		<script src="${_path}/custom/zq.queue.call.js"></script>
		<script src="${_path}/custom/zq.queue.screen.js"></script>

		<script type="text/javascript">
			jQuery(function ($) {
				$("#_btnExit").click(function(){
					window.location.href="${_path}/logout";
				});
			
				$.getJSON("${_path}/admin/admin/menu?v=" + Math.random(), function(d) {
   					$('.nav-list').append(tree(d.data));
				});
				
				var defaultPage = null;
				
				function tree(list) {
					var html = "";
					var data = null;
					for ( var i = 0; i < list.length; i++) {
						data = list[i];
						if (data.parentId == null || data.parentId == "") {
							
							html += '<li class="';
							
							if(defaultPage == null && data.url){
								if(window.location.href.indexOf("#") < 0){
									// 登录成功后首次加载，跳转到第一个页面
									defaultPage = data.url;
									window.location.href = "${_path}/admin/admin#" + defaultPage;
									
								}
								else if(data.url == window.location.href.split("#")[1]){
									// 如果跳转页，和当前菜单对应
									defaultPage = data.url;
									html += 'active';
								}
							}
						
							html += '">';
							
							var childrens = _getChildrens(list, data.id);
							
							if(data.url){
								html += '	<a data-url="' + data.url + '" href="#' + data.url + '" class="' + (childrens.length > 0 ? 'dropdown-toggle' : '') + '">';
							}
							else{
								html += '	<a href="#" class="' + (childrens.length > 0 ? 'dropdown-toggle' : '') + '">';
							}
							html += '		<i class="menu-icon fa ' + data.icon + '"></i>';
							html += '		<span class="menu-text">' + data.name + ' </span>';
							html += '		<b class="arrow' + (childrens.length > 0 ? ' fa fa-angle-down' : '') + '"></b>';
							html += '	</a>';
							
							html += '	<b class="arrow"></b>';
							
							if (childrens.length > 0) {
								html += buildTree(list, childrens);
							}
							html += '</li>';
						}
					}
					return html;
				}
				
				function buildTree(list, childrens) {
					var html = "";
					if (childrens.length > 0) {
						html += '	<ul class="submenu">';
						for ( var i = 0; i < childrens.length; i++) {
							data = childrens[i];
							
							if(defaultPage == null && data.url){
								if(window.location.href.indexOf("#") < 0){
									// 登录成功后首次加载，跳转到第一个页面
									defaultPage = data.url;
									window.location.href = "${_path}/admin/admin#" + defaultPage;
								}
							}
							
							html += '<li class="">';
							
							var tempChildrens = _getChildrens(list, data.id);
							if(data.url){
								html += '	<a data-url="' + data.url + '" href="#' + data.url + '" class="' + (tempChildrens.length > 0 ? 'dropdown-toggle' : '') + '">';
							}
							else{
								html += '	<a href="#" class="' + (tempChildrens.length > 0 ? 'dropdown-toggle' : '') + '">';
							}
							html += '		<i class="menu-icon fa ' + data.icon + '"></i>';
							html += '		<span class="menu-text">' + data.name + ' </span>';
							html += '		<b class="arrow' + (tempChildrens.length > 0 ? ' fa fa-angle-down' : '') + '"></b>';
							html += '	</a>';
							
							html += '	<b class="arrow"></b>';
							
							
							if (tempChildrens.length > 0) {
								html += buildTree(list, tempChildrens, data.id);
							}
							html += '</li>';
						}
						html += '	</ul>';
					}
					return html;
				}
				
				function _getChildrens(list, id) {
					var children = new Array();
					var child = null;
					for ( var i = 0; i < list.length; i++) {
						child = new Object();
						if (list[i].parentId == id) {
							child.id = list[i].id;
							child.parentId = list[i].parentId;
							child.name = list[i].name;
							child.url = list[i].url;
							child.icon = list[i].icon;
							children.push(child);
						}
					}
					return children;
				}
			});
		</script>
	</body>
</html>
