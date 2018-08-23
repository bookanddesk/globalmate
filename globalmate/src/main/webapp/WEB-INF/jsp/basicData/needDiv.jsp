<%@ page import="com.globalmate.data.entity.Need" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common.jsp" %>
<%
    Need need = (Need) request.getAttribute("queryParam");
    String enable = need.getEnable(), type = need.getType();
%>
<html>
<head>
    <script type="text/javascript">
        function getConceretPageParams() {
            return {
                queryUrl: "${path}/rest/basicData/needQuery",
                name: $('#schoolNameId').val(),
                enable: $('#enableId').val(),
                type: $('#typeId').val(),
                createTime: $('#createTimeId').val(),
                createTime2: $('#createTimeId2').val()
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

                <form action="${pageContext.request.contextPath}/rest/basicData/needQuery" method="get">
                    <ul>
                        <li class="ding_rig">
                            <p>
                                <input type="text" class="din_input" id="createTimeId"
                                       name="createTime" value="<fmt:formatDate value="${queryParam.createTime}" type="both"
                                                pattern="yyyy-MM-dd"/>"
                                       onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd',readOnly:true})"
                                       placeholder="起始时间">
                            </p>
                            <p>
                                <input type="text" class="din_input" id="createTimeId2"
                                       name="createTime2" value="<fmt:formatDate value="${createTime2}" type="both"
                                                pattern="yyyy-MM-dd"/>"
                                       onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd',readOnly:true})"
                                       placeholder="截止时间">
                            </p>
                            <p>
                                <select id="enableId" name="enable" class="din_input">
                                    <option value="">状态</option>
                                    <c:forEach items="${statusOptions}" var="statusObj" varStatus="index">
                                        <option value="${statusObj.value}" <c:if
                                                test='${statusObj.selected}'> selected </c:if>>${statusObj.text}
                                        </option>
                                    </c:forEach>
                                </select>
                            </p>
                            <p>
                                <select id="typeId" name="type" class="din_input">
                                    <option value="">类型</option>
                                    <c:forEach items="${typeOptions}" var="typeObj" varStatus="index">
                                        <option value="${typeObj.value}" <c:if
                                                test='${typeObj.selected}'> selected </c:if>>${typeObj.text}
                                        </option>
                                    </c:forEach>
                                </select>
                            </p>
                            <p>
                                <input type="text" id="userNameId" name="userName" class="din_input"
                                       value="${queryParam.userName}"
                                       placeholder="需求人昵称">&nbsp;&nbsp;
                            </p>
                            <p>
                                <button type="submit">查询</button>
                            </p>
                        </li>
                    </ul>
                </form>

            </div>

            <div class="Order_table" style="/**width:1000px; height:500px;**/ overflow:scroll;">
                <table width="100%" border="0" align="center"><!-- width="1700px"-->
                    <tr class="tc_ord">
                        <td width="3%">状态</td>
                        <td width="3%">类型</td>
                        <td width="5%">需求人</td>
                        <td width="5%">地点</td>
                        <td width="5%">创建时间</td>
                    </tr>

                    <c:forEach items="${pageInfo.list}" var="needObj" varStatus="index">
                        <tr align="center" valign="middle" class="dr_ord">
                            <td style="display: none;">${needObj.id}</td>
                            <td>${needObj.enable}</td>
                            <td>${needObj.type}</td>
                            <td>${needObj.userName}</td>
                            <td>${needObj.where}</td>
                            <td><fmt:formatDate value="${needObj.createTime}" type="both"
                                                pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
