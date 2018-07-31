<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common.jsp" %>
<html>
<head>
    <script type="text/javascript">
        function getConceretPageParams() {
            return {
                queryUrl : "${path}/rest/basicData/evaluateQuery",
                uEvluatorName :$('#userNameId').val()
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
                <form action="${pageContext.request.contextPath}/rest/evaluateQuery" method="get">
                    <ul>
                        <li class="ding_rig"><p>
                            <input type="text" id="userNameId" name="uEvluatorName" class="din_input"
                                   value="${queryParam.uEvluatorName}"
                                   placeholder="用户昵称">&nbsp;&nbsp;
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
                        <td width="3%">昵称</td>
                        <td width="3%">时间</td>
                        <td width="5%">内容</td>
                    </tr>

                    <c:forEach items="${pageInfo.list}" var="evlObj" varStatus="index">
                        <tr align="center" valign="middle" class="dr_ord">
                            <td style="display: none;">${evlObj.id}</td>
                            <td>${evlObj.uEvluatorName}</td>
                            <td><fmt:formatDate value="${evlObj.createTime}" type="both" pattern="yyyy-MM-dd HH:mm" /></td>
                            <td>${evlObj.content}</td>
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
