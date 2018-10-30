<%@ page language="java" pageEncoding="utf-8" %>

<jsp:include page="../common/common.jsp">
    <jsp:param name="title" value="屏幕"/>
</jsp:include>

<style>
    .pagination-detail, .pagination{
        display: none;
    }
</style>

<div class="row" id="screenfunc">
    <div class="col-sm-12" style="height:100%">
        <div class="widget-box">
            <div class="widget-header widget-header-flat" style="direction: rtl">
                <a href="javascript:;"><img src="${_path}/custom/img/full.JPG" onclick="fullScreen(document.getElementById('page-content'))"></a>
            </div>
            <div class="widget-header widget-header-flat">
                <div class="row" style="min-height:38px;line-height:38px">
                    <div class="col-sm-4">
                        <div>${hospital}</div>
                    </div>
                    <div class="col-sm-4">
                        <div>${department.departName}</div>
                    </div>
                    <div class="col-sm-4">
                        <div class="screenfunc_time"></div>
                    </div>
                </div>
            </div>
            <div class="widget-body">
                <div class="widget-main">
                    <table id="screenTable">
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="${_path}/custom/data.js"></script>
<script type="text/javascript">
    $('.page-content-area').ace_ajax('loadScripts', scripts, function () {
        jQuery(function ($) {

            QueueScreen.newInstance().start();

            function showTime() {
                $('.screenfunc_time').html(dateFun.getnowFull());
                window.setTimeout(showTime, 1000);
            }

            showTime();
            $("#scaling").click(function () {
                $("#screenMaskfunc").show();
            })
            $("#scalingMask").click(function () {
                $("#screenMaskfunc").hide();
            })
        });
    });
</script>
