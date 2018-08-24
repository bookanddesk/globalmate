<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common.jsp" %>
<html>
<head>
    <script type="text/javascript">
        function getConceretPageParams() {
            return {
                queryUrl: "${path}/rest/basicData/dealQuery",
                uNeederName: $('#uNeederNameId').val(),
                assistCreateTime: $('#assistCreateTimeId').val(),
                assistCreateTime2: $('#assistCreateTimeId2').val(),
                assistStatus: $('#assistStatusId').val()
            }
        }
    </script>
</head>
<body>
<div class="main">
    <div class="container">
        <div class="container_right">
            <div class="reveal-modal" id="addInput">
            </div>

            <div class="Order_fist" style="width: 1000px;">

                <form action="${pageContext.request.contextPath}/rest/basicData/dealQuery" method="get">
                    <ul>
                        <li class="ding_rig">
                            <p>
                                <input type="text" class="din_input" id="assistCreateTimeId"
                                       name="assistCreateTime" value="<fmt:formatDate value="${queryParam.assistCreateTime}" type="both"
                                                pattern="yyyy-MM-dd"/>"
                                       onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd',readOnly:true})"
                                       placeholder="起始时间">
                            </p>
                            <p>
                                <input type="text" class="din_input" id="assistCreateTimeId2"
                                       name="assistCreateTime2" value="<fmt:formatDate value="${assistCreateTime2}" type="both"
                                                pattern="yyyy-MM-dd"/>"
                                       onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd',readOnly:true})"
                                       placeholder="截止时间">
                            </p>
                            <p>
                                <select id="assistStatusId" name="assistStatus" class="din_input">
                                    <option value="">状态</option>
                                    <c:forEach items="${actionOptions}" var="actionObj" varStatus="index">
                                        <option value="${actionObj.value}" <c:if
                                                test='${actionObj.selected}'> selected </c:if>>${actionObj.text}
                                        </option>
                                    </c:forEach>
                                </select>
                            </p>
                            <p>
                                <input type="text" id="uNeederNameId" name="uNeederName" class="din_input"
                                       value="${queryParam.uNeederName}"
                                       placeholder="需求人">&nbsp;&nbsp;
                            </p>
                            <p>
                                <button type="submit">查询</button>
                            </p>
                        </li>
                    </ul>
                </form>

            </div>

            <div class="Order_table" style="/**width:1000px; height:500px;**/ overflow:scroll;">
                <table width="100%" border="0" align="center">
                    <tr class="tc_ord">
                        <td width="3%">创建时间</td>
                        <td width="3%">状态</td>
                        <td width="5%">需求人</td>
                        <td width="5%">帮助人</td>
                        <td width="5%">更新时间</td>
                        <td width="5%">评价信息</td>
                    </tr>

                    <c:forEach items="${pageInfo.list}" var="dealObj" varStatus="index">
                        <tr align="center" valign="middle" class="dr_ord">
                            <td style="display: none;">${dealObj.id}</td>
                            <td><fmt:formatDate value="${dealObj.assistCreateTime}" type="both"
                                                pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>${dealObj.assistStatus}</td>
                            <td>${dealObj.uNeederName}</td>
                            <td>${dealObj.uProviderName}</td>
                            <td><fmt:formatDate value="${dealObj.assistModifyTime}" type="both"
                                                pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>${dealObj.assistEvaluation}</td>
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
</html>
