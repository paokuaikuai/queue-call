<%@ page language="java" pageEncoding="utf-8"%>

<jsp:include page="../common/common.jsp">
	<jsp:param name="title" value="呼号"/>
</jsp:include>
<style type="text/css">
		/*表示当屏幕大于768px是执行下面的css*/
		@media (min-width: 768px){
	   /* @media screen and (min-width: 768px){*/
    		#callfunc .widget-header .row>.col-sm-6:nth-child(2){text-align:right}
    	} 
    	.test1{
			height: 200px;
		}
</style>
<!--  <div class="page-header">
	<h1>
		功能呼号开发中...
	</h1>
</div>
-->
<div class="row" id="callfunc">
	<div class="col-xs-offset-3 col-xs-6">
		<div class="widget-box">
			<div class="widget-header header-color-blue2">
				
				<div class="row">
					<div class="col-sm-4">
						<h4 class="lighter smaller">当前呼号</h4>
					</div>
					<div class="col-sm-3">
						<h4 class="lighter smaller">当前诊室:${roomName}</h4>
					</div>
					<div class="col-sm-5">
						<h4 class="lighter smaller screenfunc_time">呼号</h4>
					</div>
				</div>
			</div>

			<div class="widget-body">
				<div class="widget-main padding-8">
					<div class="row">
						<div class="col-sm-8">
							<div class="form-group">
								<label class="col-sm-4 control-label" for="number">排队号</label>
								<div class="col-sm-8">
									<input type="text" id="number" name="number" value="" class="form-control" disabled="disabled"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="patientCard">卡号</label>
								<div class="col-sm-8">
									<input type="text" id="patientCard" name="patientCard" value="" class="form-control" disabled="disabled"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="patientName">名字</label>
								<div class="col-sm-8">
									<input type="text" id="patientName" name="patientName" value="" class="form-control" disabled="disabled"/>
								</div>
							</div>
								<div class="form-group" style="margin-top:5px">
								<div class="col-sm-4">已诊人数:<span id="diagnosed">0</span></div>
								<div class="col-sm-4">等待人数:<span id="waiting">0</span></div>
							</div>
						</div>
						<div class="col-sm-4">
							<button id="callBtn" class="btn btn-default btn-block">呼号</button>
							<button id="reCallBtn" class="btn btn-info btn-block">重呼</button>
							<button id="crossBtn" class="btn btn-danger btn-block">过号</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="${_path}/custom/data.js"></script>
<script type="text/javascript">
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		jQuery(function($) {
			//呼号
            QueueCall.newInstance().start();

			function showTime(){
				$('.screenfunc_time').html(dateFun.getnowFull());
				//console.log(dateFun.getnowFull());
				window.setTimeout(showTime,1000);
			}
			showTime();
		});
	});
</script>
