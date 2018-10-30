<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../common/common.jsp">
	<jsp:param name="title" value="科室"/>
</jsp:include>

<div class="page-header">
	<h1>
		业务类型列表
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
									<label class="control-label" for="departId"> 科室： </label>
									<select id="departId" name="departId" class="form-control form-data search-data">
										<c:forEach items="${department}" var="s">
											<option value="${s.id}">${s.departName}</option>
										</c:forEach>
									</select>
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
    			url : "${_path}/admin/business/list",
    			formId : "_form",
				tools : [
					{text : '新增', clazz : 'btn-info', icon : 'fa fa-plus-circle blue', permission : '/admin/business/edit', handler : function(){
						$.aceRedirect("${_path}/admin/business/edit");
					}},
					{text : '删除', clazz : 'btn-danger', icon : 'fa fa-trash-o red', permission : '/admin/business/delete', handler : function(){
						$table.ajaxDelete({
							confirm : "确认要删除?",
							url : "${_path}/admin/business/delete"
						});
					}}
					
				],
				columns : [
			        {field:'id', hide : true},
			        {field:'name', title:'名称', align:'left'},
                    {field:'departName', title:'科室名称', align:'left', replace : function (d){
                        	return d.department.departName;
						}
                    }
				],
				operate : [
					{text : '修改', clazz : 'blue', icon : 'fa fa-pencil', permission : '/admin/business/edit', handler : function(d, i){
						$.aceRedirect("${_path}/admin/business/edit?id=" + d.id);
					}},
					{text : '删除', clazz : 'red', icon : 'fa fa-trash-o', permission : '/admin/business/delete', handler : function(d, i){
						$table.ajaxDelete({
							confirm : "确认要删除?",
							url : "${_path}/admin/business/delete"
						});
					}}
				],
				after : function(){
					// 权限处理
					$.permission();
				}
			});

    		$(".search-data").change(function(){
                $table.search();
			});
		});
	});
</script>
