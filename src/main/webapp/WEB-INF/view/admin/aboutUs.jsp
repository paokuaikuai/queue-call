<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="../common/common.jsp">
    <jsp:param name="title" value="关于我们"/>
</jsp:include>

<div class="row">
    <div class="col-sm-12">
        <div class="widget-box">
            <div class="widget-header">
                <span style="line-height:37px; font-size:18px;">关于我们</span>
            </div>
            <div class="widget-body">
                <div class="widget-main">
                    <div class="row">
                    <p style="margin-left: 10px">
						产品机构信息
					<p/>
					<p style="margin-left: 10px">
						生产企业名称：
						武汉中旗生物医疗电子有限公司
					<p/>
					<p style="margin-left: 10px">
						生产注册地址：
						湖北省武汉市东湖新技术开发区高新二路380号
					<p/>
					<p style="margin-left: 10px">
						生产地址：
						湖北省武汉市东湖新技术开发区高新二路380号
					<p/>
					<p style="margin-left: 10px">
						产品名称：
						叫号系统
					<p/>
					<p style="margin-left: 10px">
						版权所有(C) 武汉中旗生物医疗电子有限公司
					<p/>
					<p style="margin-left: 10px">
					Wuhan Zoncare Bio-Medical Electronics CO., LTD
					<p/>
					<p style="margin-left: 10px">
						全国免费服务电话：
						 4000-400-499
					<p/>
					<p style="margin-left: 10px">
						发布版本：1
					<p/>
					<p style="margin-left: 10px">
						完整版本：1.0
					<p/>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript">
    $('.page-content-area').ace_ajax('loadScripts', scripts, function () {
        jQuery(function ($) {
        });
    });
</script>