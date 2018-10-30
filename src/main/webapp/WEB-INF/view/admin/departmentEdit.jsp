<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../common/common.jsp">
	<jsp:param name="title" value="科室"/>
</jsp:include>

<div class="page-header">
	<h1>
		${empty user.id ? '添加' : '修改'}科室
	</h1>
</div>

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<form id="_editForm" class="form-horizontal" role="form" 
			validate="true">
			<input type="hidden" name="id" value="${department.id}">
			
			<div class="form-group">
				<label for="_departName" class="col-sm-3 control-label no-padding-right"><span class="form-star">*</span>名称</label>

				<div class="col-sm-6">
					<div class="clearfix help-validate">
						<input id="_departName" name="departName" type="text" value="${department.departName}" class="form-data col-xs-10 col-sm-5" placeholder="名称"
							required="true" maxlength="64"/>
					</div>
				</div>
				
			</div>
			<div class="form-group">
				<label for="_departType" class="col-sm-3 control-label no-padding-right"><span class="form-star">*</span>类型</label>

				<div class="col-sm-6">
				 <div class="clearfix help-validate">
					<select id="_departType" name="departType" class="form-data col-xs-10 col-sm-5"
						required="true">
							<option value="1">功能叫号</option>
							<option value="2">门诊叫号</option>
							<option value="3">药房叫号</option>
					</select>
					</div>
				</div>
				
			</div>
			<div class="form-group">
				<label for="_firstNum" class="col-sm-3 control-label no-padding-right"><span class="form-star">*</span>首字母</label>

				<div class="col-sm-6">
					<div class="clearfix help-validate">
						<input id="_firstNum" name="firstNum" type="text" value="${department.firstNum}" class="form-data col-xs-10 col-sm-5" placeholder="首字母"
							required="true" maxlength="64"/>
					</div>
				</div>
				
			</div>
			
			<div class="form-group">
				<label for="_remark" class="col-sm-3 control-label no-padding-right">备注</label>
				
				<div class="col-sm-9">
					<div class="clearfix help-validate">
						<div class="input-group">
							<textarea id="_remark" name="remark" class="autosize-transition form-control"  placeholder="备注"
								 rows="6" cols="60">${department.remark}</textarea>
						</div>
					</div>
				</div>
			</div>
			
			
			<div class="clearfix form-actions">
				<div class="col-md-offset-3 col-md-9">
					<button id="_submit" type="button" class="btn btn-info" data-loading-text="正在提交...">
						<i class="ace-icon fa fa-check bigger-110"></i>
						提交
					</button>

					&nbsp; &nbsp; &nbsp;
					<button id="_cancle" class="btn" type="reset">
						<i class="ace-icon fa  fa-times bigger-110"></i>
						取消
					</button>
				</div>
			</div>
		</form>

	</div>
</div>


<script type="text/javascript">
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		jQuery(function($) {
			//焦点
			$("#_name").focus();
			
			//帮助查看
			$('[data-rel=tooltip]').tooltip({container:'body'});
			
			// 提交
			$("#_submit").click(function(){
				if($('#_editForm').validate()){
					var btn = $(this);
					btn.button('loading');
					$.post("${_path}/admin/department/save", $.formJson('_editForm'),function(d) {
						if(d){
							btn.button('reset');
							if(d.code == 1){
								$.aceRedirect("${_path}/admin/department");
							}
							else {
								$.gritter.add({text: d.message});
							}
						}
			        },'json');
				}
			});
			
			// 取消
			$("#_cancle").click(function(){
				$.aceRedirect("${_path}/admin/department");
			});
			
			// 回车绑定
			$(".form-data").bind('keypress',function(event){
                if(event.keyCode == "13"){
                	event.preventDefault();
                	$("#_submit").click();
                }
            });
		});
	});
</script>
