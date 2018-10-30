<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../common/common.jsp">
	<jsp:param name="title" value="科室"/>
</jsp:include>

<div class="page-header">
	<h1>
		科室列表
	</h1>
</div>

<div class="row">
	<div class="col-xs-12">
		<div class="row">
			<div class="col-xs-12">
				<div class="widget-box">
					<div class="widget-header widget-header-small">
						<h5 class="widget-title lighter">搜索栏</h5>
					</div>

					<div class="widget-body">
						<div class="widget-main">
							<form id="_form" class="form-inline">
								<label>
									<label class="control-label" for="form-field-1"> 科室名： </label>
									<input name="name" type="text" class="form-data input-medium search-data">
								</label>
							</form>
						</div>
					</div>
				</div>

				<div>
					<div class="dataTables_wrapper form-inline no-footer">
						<table id="_table" class="table table-striped table-bordered table-hover dataTable no-footer">
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		jQuery(function($) {
			// 列表
    		var $table = $("#_table").table({
    			url : "${_path}/admin/department/list",
    			formId : "_form",
				tools : [
					{text : '新增', clazz : 'btn-info', icon : 'fa fa-plus-circle blue', permission : '/admin/department/edit', handler : function(){
						$.aceRedirect("${_path}/admin/department/edit");
					}},
					{text : '删除', clazz : 'btn-danger', icon : 'fa fa-trash-o red', permission : '/admin/department/delete', handler : function(){
						$table.ajaxDelete({
							confirm : "确认要删除?",
							url : "${_path}/admin/department/delete"
						});
					}}
					
				],
				columns : [
			        {field:'id', hide : true},
			        {field:'departName', title:'名称', align:'left'},
			        {field:'firstNum', title:'首字母', align:'left', mobileHide : true},
			        {field:'departType', title:'类型', align:'left', replace : function (d){
				        if(d.departType==1)
				        	return "功能叫号";
			        	else if(d.departType==2)
			        		return "门诊叫号";
			        	else
			        		return "药房叫号";
			        }},
			        {field:'remark', title:'备注', align:'left', mobileHide : true},
			        {field:'createTime', title:'创建时间', align:'left', mobileHide : true},
				],
				operate : [
					{text : '修改', clazz : 'blue', icon : 'fa fa-pencil', permission : '/admin/department/edit', handler : function(d, i){
						$.aceRedirect("${_path}/admin/department/edit?id=" + d.id);
					}},
					{text : '删除', clazz : 'red', icon : 'fa fa-trash-o', permission : '/admin/department/delete', handler : function(d, i){
						$table.ajaxDelete({
							confirm : "确认要删除?",
							url : "${_path}/admin/department/delete"
						});
					}}
				],
				after : function(){
					// 权限处理
					$.permission();
				}
			});
		});
	});
</script>
