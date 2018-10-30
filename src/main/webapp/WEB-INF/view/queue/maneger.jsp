<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../common/common.jsp">
    <jsp:param name="title" value="诊室"/>
</jsp:include>

<div class="page-header">
    <h1>
        叫号管理
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">
        <div style="border: 1px #999 solid;border-radius:5px;padding: 10px">
            <div>
                <p style="color: #6fb3e0 ;font-size: 18px">提示:</p>
                <p style="color:red;font-size: 16px">
                    系统每天晚上12点会自动清除当天所有排队叫号的数据，一般不需要手动执行该操作，考虑到服务器断电等异常情况导致排队叫号数据未能正常清除，可手动执行该操作，执行该操作期间请暂停使用排队叫号功能。
                </p>
                <button id="clearBtn" style="width: 100px;" class="btn btn-default btn-block">清除</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $('.page-content-area').ace_ajax('loadScripts', scripts, function () {
        jQuery(function ($) {
            $("#clearBtn").click(function () {
                $("#clearBtn").button('loading');
                $.post("${_path}/queue/clear", function (d) {
                    if (d) {
                        $("#clearBtn").button('reset');
                        $.gritter.add({text: "清除成功", sticky: false, time: '1000'});
                    }else{
                        $.gritter.add({text: "清除失败", sticky: false, time: '1000'});
                    }
                }, 'json');
            });
        });
    });
</script>
