<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../common/common.jsp">
	<jsp:param name="title" value="管理员"/>
</jsp:include>

<div class="page-header">
	<h1>
		${empty user.id ? '添加' : '修改'}管理员
	</h1>
</div>

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<form id="_editForm" class="form-horizontal" role="form" 
			validate="true">
			<input type="hidden" name="id" value="${user.id}">
			
			<div class="form-group">
				<label for="_account" class="col-sm-3 control-label no-padding-right"><span class="form-star">*</span>登录名</label>

				<div class="col-sm-6">
					<div class="clearfix help-validate">
							<input id="_account" name="account" type="text" value="${user.account}" class="form-data col-xs-10 col-sm-5"
								ajax="{url : '${_path}/admin/user/validateAccount', dataId : '_editForm'}"
								required="true" minlength = '4' maxlength = '64'/>
					</div>
				</div>
				
			</div>
			
			<div class="form-group">
				<label for="_password" class="col-sm-3 control-label no-padding-right">${empty user.id ? '<span class="form-star">*</span>' : ''}密码</label>
				
				<div class="col-sm-6">
					<div class="clearfix help-validate">
							<input id="_password" name="password" type="password" value="" class="form-data col-xs-10 col-sm-5"
								${!empty user.id ? 'data-rel="tooltip" title="不修改请留空"' : ''}
								${empty user.id ? 'required="true"' : ''} minlength = '6' maxlength = '16'/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="_realname" class="col-sm-3 control-label no-padding-right"><span class="form-star">*</span>真名</label>

				<div class="col-sm-6">
					<div class="clearfix help-validate">
						<input id="_realname" name="realname" type="text" value="${user.realname}" class="form-data col-xs-10 col-sm-5" placeholder="真名"
							required="true" maxlength="64"/>
					</div>
				</div>
				
			</div>
			<div class="form-group">
				<label for="_telephone" class="col-sm-3 control-label no-padding-right"><span class="form-star">*</span>电话</label>

				<div class="col-sm-6">
					<div class="clearfix help-validate">
						<input id="_telephone" name="telephone" type="text" value="${user.telephone}" class="form-data col-xs-10 col-sm-5" placeholder="电话"
							required="true" maxlength="64"/>
					</div>
				</div>
				
			</div>
			<div class="form-group">
				<label for="_position" class="col-sm-3 control-label no-padding-right"><span class="form-star">*</span>职位</label>

				<div class="col-sm-6">
					<div class="clearfix help-validate">
						<input id="_position" name="position" type="text" value="${user.position}" class="form-data col-xs-10 col-sm-5" placeholder="职位"
							required="true" maxlength="64"/>
					</div>
				</div>
				
			</div>
			<div class="form-group">
				<label for="_departId" class="col-sm-3 control-label no-padding-right"><span class="form-star">*</span>科室</label>

				<div class="col-sm-6">
				 <div class="clearfix help-validate">
					<select id="_departId" name="departId" class="form-data col-xs-10 col-sm-5"
						required="true">
						<c:forEach items="${list}" var="s">
							<option value="${s.id}" ${user.departId == s.id ? "selected" : ""}>${s.departName}</option>
						</c:forEach>
					</select>
					</div>
				</div>
				
			</div>
			<div class="form-group">
				<label for="_roomId" class="col-sm-3 control-label no-padding-right"><span class="form-star">*</span>诊室</label>

				<div class="col-sm-6">
				 <div class="clearfix help-validate">
					<select id="_roomId" name="roomId" class="form-data col-xs-10 col-sm-5"
						required="true">
						<c:forEach items="${listRoom}" var="s">
							<option value="${s.id}" ${user.roomId == s.id ? "selected" : ""}>${s.roomName}</option>
						</c:forEach>
					</select>
					</div>
				</div>
				
			</div>
			
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"><span class="form-star">*</span>是否启用</label>

				<div class="col-xs-12 col-sm-9">
					<div class="clearfix help-validate">
						<div>
							<label class="line-height-1 blue">
								<input name="isEnable" value="true" type="radio" class="ace" ${user.isEnable ? 'checked="checked"' : ''}/>
								<span class="lbl"> 是</span>
							</label>
						</div>
	
						<div>
							<label class="line-height-1 blue">
								<input name="isEnable" value="false" type="radio" class="ace" ${!user.isEnable ? 'checked="checked"' : ''}/>
								<span class="lbl"> 否</span>
							</label>
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
			$("#_account").focus();
			
			//帮助查看
			$('[data-rel=tooltip]').tooltip({container:'body'});
			
			// 提交
			$("#_submit").click(function(){
				if($('#_editForm').validate()){
					var btn = $(this);
					btn.button('loading');
					$.post("${_path}/admin/user/save", $.formJson('_editForm'),function(d) {
						if(d){
							btn.button('reset');
							if(d.code == 1){
								$.aceRedirect("${_path}/admin/user");
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
				$.aceRedirect("${_path}/admin/user");
			});
			
			// 回车绑定
			$(".form-data").bind('keypress',function(event){
                if(event.keyCode == "13"){
                	event.preventDefault();
                	$("#_submit").click();
                }
            });
			
			//科室诊室联动
			$("#_departId").change(function(){
				 $.ajax({ 
				     type : "GET", 
				     url : "${_path}/admin/user/listRoom",
				     data : { 
				      "departId" : $("#_departId").val()
				     }, 
				     success : function(data) {
				       if(data.code==1){
				    	   $("#_roomId").empty();
				    	   $.each(data.data, function(i){     
				    		   $("#_roomId").append("<option value="+data.data[i].id+" >"+data.data[i].roomName+"</option>");
				    	   });    
				       }
				    	 
				     } 
				    }); 
			})
			
		});
	});
</script>
