<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common.jsp" %>
<html>
<head>
    <script type="text/javascript">
        function getConceretPageParams() {
            return {
                queryUrl: "${path}/rest/basicData/cetifiyQuery",
                uName: $('#userNameId').val()
            }
        };

        function pass(id, pass) {
            var queryUrl = "${path}/rest/basicData/cetifiyUpdate";
            var param =
                {
                    isEffective: pass ? 1 : 2,
                    id: id
                }
            $.ajax({
                async: false,
                type: "GET",
                url: queryUrl,
                data: param,
                success: function (data) {
                    $('#submitBtn').trigger('click')
                    $.tooltip('审核成功', 2000, true);
                },
                error: function (data) {
                    $.tooltip('审核失败');
                }
            });

        };
    </script>
</head>
<body>
<div class="main">
    <div class="container">
        <div class="container_right">
            <div class="reveal-modal" id="addInput">
            </div>

            <div class="Order_fist" style="width: 1000px;">
                <form action="${pageContext.request.contextPath}/rest/cetifiyQuery" method="get">
                    <ul>
                        <li class="ding_rig"><p>
                            <input type="text" id="userNameId" name="uName" class="din_input"
                                   value="${queryParam.uName}"
                                   placeholder="用户昵称">&nbsp;&nbsp;
                        </p>
                            <p>
                                <button id="submitBtn" type="submit">查询</button>
                            </p>
                        </li>
                    </ul>
                </form>

            </div>

            <div class="Order_table" style="/**width:1000px; height:500px;**/ overflow:scroll;">
                <table width="100%" border="0" align="center"><!-- width="1700px"-->
                    <tr class="tc_ord">
                        <td width="3%">昵称</td>
                        <td width="3%">认证方式</td>
                        <td width="5%">认证图片1</td>
                        <td width="5%">认证图片2</td>
                        <td width="3%">认证时间</td>
                        <td width="3%">认证状态</td>
                        <td width="5%">操作</td>
                    </tr>

                    <c:forEach items="${pageInfo.list}" var="cerObj" varStatus="index">
                        <tr align="center" valign="middle" class="dr_ord">
                            <td style="display: none;">${cerObj.id}</td>
                            <td>${cerObj.uName}</td>
                            <td>${cerObj.cetifyType}</td>
                            <c:set var="url" value="${cerObj.certifyPhoto}"/>
                            <c:set var="split" value="${fn:split(url, ';')}"/>
                            <td><img src="${split[0]}" width="128" height="128"></td>
                            <td><img src="${split[1]}" width="128" height="128"></td>
                            <td><fmt:formatDate value="${cerObj.certifyTime}" type="both"
                                                pattern="yyyy-MM-dd HH:mm"/></td>
                            <c:choose>
                                <c:when test="${cerObj.isEffective == 1}">
                                    <td>通过</td>
                                    <td>
                                        <a href="#" onclick="pass('${cerObj.id}', false)">不通过</a>
                                    </td>
                                </c:when>
                                <c:when test="${cerObj.isEffective == 2}">
                                    <td>不通过</td>
                                    <td>
                                        <a href="#" onclick="pass('${cerObj.id}', true)">通过</a>
                                    </td>
                                </c:when>
                                <c:when test="${cerObj.isEffective == 0}">
                                    <td>未审核</td>
                                    <td >
                                        <a href="#" onclick="pass('${cerObj.id}', true)">通过</a>
                                        <a href="#" onclick="pass('${cerObj.id}', false)">不通过</a>
                                    </td>
                                </c:when>
                            </c:choose>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <div class="main_C_fenye">
                <%@ include file="../page.jsp" %>
            </div>

        </div>
    </div>
</div>
</body>
<script src="https://www.js-css.cn/jscode/open/open15/js/jquery.hDialog.js"></script>
</html>
