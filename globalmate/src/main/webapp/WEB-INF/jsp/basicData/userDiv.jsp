<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common.jsp" %>
<html>
<head>
    <script type="text/javascript">

        function getConceretPageParams() {
            return {
                queryUrl : "${path}/rest/basicData/userQuery",
                nikename :$('#userNameId').val()
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
                <form action="${pageContext.request.contextPath}/rest/userQuery" method="get">
                    <ul>
                        <li class="ding_rig"><p>
                            <input type="text" id="userNameId" name="nikename" class="din_input"
                                   value="${queryParam.nikename}"
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
                        <td width="3%">电话</td>
                        <td width="5%">邮箱</td>
                        <td width="5%">国家</td>
                        <td width="5%">加入时间</td>
                        <td width="5%">学校</td>
                        <td width="5%">好人值</td>
                    </tr>

                    <c:forEach items="${pageInfo.list}" var="userObj" varStatus="index">
                        <tr align="center" valign="middle" class="dr_ord">
                            <td style="display: none;">${userObj.id }</td>
                            <td>${userObj.nikename }</td>
                            <td>${userObj.phone }</td>
                            <td>${userObj.email }</td>
                            <td>${userObj.country}</td>
                            <td><fmt:formatDate value="${userObj.createTime}" type="both" pattern="yyyy-MM-dd HH:mm" /></td>
                            <td>${userObj.school}</td>
                            <td>${userObj.nice}</td>
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
