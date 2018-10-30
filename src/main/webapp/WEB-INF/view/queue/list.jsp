<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../common/common.jsp">
    <jsp:param name="title" value="排队"/>
</jsp:include>


<div class="row">
    <div class="col-xs-3">
        <form id="_addForm" class="form-horizontal" role="form" validate="true">
            <legend>新增列队</legend>
            <div class="form-group">
                <label class="col-md-4 " for="patientCard"><span class="form-star">*</span>病人卡号</label>
                <div class="col-md-5">
                    <div class="clearfix help-validate">
                        <input type="text" id="patientCard" name="patientCard"
                               class="form-control" required="true"/>
                    </div>
                </div>
                <div class="col-md-3">
                    <button class="btn btn-info btn-xs ">查询</button>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4" for="patientName"><span class="form-star">*</span>病人名字</label>
                <div class="col-md-8">
                    <div class="clearfix help-validate">
                        <input type="text" id="patientName" name="patientName"
                               class="form-control" required="true"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 " for="department"><span class="form-star">*</span>科室名称</label>
                <div class="col-md-8">
                    <input type="text" id="department" name="department"
                           value="${departmentName }" class="form-control" disabled="disabled"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4" for="type"><span class="form-star">*</span>业务类型</label>
                <div class="col-md-8">
                    <div class="clearfix help-validate">
                        <select id="type" name="type" class="form-control">
                            <c:forEach items="${business}" var="b">
                                <option value="${b.id }">${b.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <%--<div class="form-group">
                <label class="col-md-4" for="room"><span class="form-star">*</span>诊室名称</label>
                <div class="col-md-8">
                    <select id="room" name="roomId" class="form-control">
                    <c:forEach items="${listRoom }" var="room">
                    <option value="${room.id }">${room.roomName }</option>
                    </c:forEach>
                    </select>
                </div>
            </div>--%>
            <div class="form-group">
                <div class="col-md-12">
                    <button id="printBtn" type="button" class="btn btn-default">打印排队号</button>
                </div>
            </div>
        </form>
        <!--
        <div class="row">
        <div class="col-md-6">
            <button class="btn btn-default btn-block">呼号</button>
        </div>
        <div class="col-md-6">
            <button class="btn btn-info btn-block">重呼</button>
        </div>
        </div>
        <div class="row">&nbsp;</div>
        <div class="row">
        <div class="col-md-6 ">
            <button class="btn btn-primary btn-block">特呼</button>
        </div>
        <div class="col-md-6">
            <button class="btn btn-danger btn-block">过号</button>
        </div>
        </div>
        -->
    </div>
    <div class="col-xs-9">
        <div class="row">
            <div class="col-xs-12">
                <h4 class="lighter smaller blue">待就诊</h4>
                <div>
                    <form id="_form1" class="form-inline">
                        <input name="status" type="hidden" value="1">
                        <div class="dataTables_wrapper form-inline no-footer">
                            <table id="_table1">
                            </table>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <h4 class="lighter smaller blue">已就诊</h4>
                <div>
                    <form id="_form" class="form-inline">
                        <input name="status" type="hidden" value="3">
                        <div class="dataTables_wrapper form-inline no-footer">
                            <table id="_table" class="">
                            </table>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $('.page-content-area').ace_ajax('loadScripts', scripts, function () {
        jQuery(function ($) {
            QueueMessage.newInstance().start();
        });
    });
</script>
