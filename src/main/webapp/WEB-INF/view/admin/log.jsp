<%@ page language="java" pageEncoding="utf-8"%>

<jsp:include page="../common/common.jsp">
	<jsp:param name="title" value="日志"/>
</jsp:include>

<div class="page-header">
	<h1>
		日志列表
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
									<label class="control-label" for="form-field-1"> 名称： </label>
									<input name="name" type="text" class="form-data input-medium search-data">
								</label>
								<!-- 
								<button id="_search" type="button" class="btn btn-info btn-sm">
									<i class="ace-icon fa fa-search bigger-110"></i>搜索
								</button>
								 -->
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
    			url : "${_path}/admin/log/list",
    			formId : "_form",
				columns : [
			        {field:'logId', hide : true},
			        {field:'userId', title:'操作人', align:'left'},
			        {field:'operateDate', title:'操作时间', replace : function (d){
				        	return   formatDateTime(d.operateDate) ;
			        }},
			        {field:'title', title:'操作内容', align:'left'},
			        {field:'remoteAddr', title:'请求IP', align:'left'},
			        {field:'requestUri', title:'请求地址', align:'left'},
			        {field:'type', title:'日志等级', align:'left',replace : function (d){
			        	 if(d.type!="error")
			        		 return  d.type ;
				        	else
				        		return "<span class='label label-sm label-warning'>" + d.type + "</span>";
			        }},
			        {field:'timeout', title:'耗时', align:'left'},
			        {field:'exception', title:'异常', align:'left'},
			        
				],
				after : function(){
					// 权限处理
					$.permission();
				}
			});
			
			/**
			// 搜索
			$("#_search").click(function () {
				$table.search();
			});
			
			// 回车绑定
			$(".form-data").bind('keypress',function(event){
			    if(event.keyCode == "13"){
			    	event.preventDefault();
			    	$table.search();
			    }
			});
			 */
			
			// 搜索
			$(".search-data").keyup(function () { 
				$table.search();
			});
		});
	});
    function formatDateTime(inputTime) {    
        var date = new Date(inputTime);  
        var y = date.getFullYear();    
        var m = date.getMonth() + 1;    
        m = m < 10 ? ('0' + m) : m;    
        var d = date.getDate();    
        d = d < 10 ? ('0' + d) : d;    
        var h = date.getHours();  
        h = h < 10 ? ('0' + h) : h;  
        var minute = date.getMinutes();  
        var second = date.getSeconds();  
        minute = minute < 10 ? ('0' + minute) : minute;    
        second = second < 10 ? ('0' + second) : second;   
        return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;    
    };  
</script>
