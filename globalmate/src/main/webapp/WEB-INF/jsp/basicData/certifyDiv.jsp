<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common.jsp" %>
<html>
<head>
    <script type="text/javascript">
        function getConceretPageParams() {
            return {
                queryUrl : "${path}/rest/basicData/cetifiyQuery",
                uName :$('#userNameId').val()
            }
        };
        
        function pass(id) {
        	var queryUrl = "${path}/rest/basicData/cetifiyUpdate";
        	var  param =
        	{
        		isEffective : 1,
            	id : id
        	}
        	$.ajax({
                async : false,
                type : "GET",
                url : queryUrl,
                data : param,
                success: function (data) {
                	$.tooltip('校验成功');
                },
                error:function (data){
                    $.tooltip('校验失败');
                }
            });
        	
        };
        
        function notPass(id) {
        	var queryUrl = "${path}/rest/basicData/cetifiyUpdate";
        	var  param =
        	{
        		isEffective : 2,
            	id : id
        	}
        	$.ajax({
                async : false,
                type : "GET",
                url : queryUrl,
                data : param,
                success: function (data) {
                	$.tooltip('校验成功');
                },
                error:function (data){
                    $.tooltip('校验失败');
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
                        <td width="3%">认证方式</td>
                        <td width="5%">认证图片</td>
                        <td width="3%">认证时间</td>
                        <td width="3%">认证状态</td>
                        <td width="3%"></td>
                        <td width="3%"></td>
                    </tr>

                    <c:forEach items="${pageInfo.list}" var="cerObj" varStatus="index">
                        <tr align="center" valign="middle" class="dr_ord">
                            <td style="display: none;">${cerObj.id}</td>
                            <td>${cerObj.uName}</td>
                            <td>${cerObj.cetifyType}</td>
                            <td>${cerObj.certifyPhoto}</td>
                            <td><fmt:formatDate value="${cerObj.certifyTime}" type="both" pattern="yyyy-MM-dd HH:mm" /></td>
							<script type="text/javascript">
								if (${cerObj.isEffective} == 1) {
									document.write('<td>通过</td>');
									document.write('<td>通过</td>');
									document.write('<td>通过</td>');
								}
								else if(${cerObj.isEffective} == 2) {
									document.write('<td>不通过</td>');
								}
								else 
									document.write('<td>未校验</td>');
							</script>
                            <td> <button onclick="pass(${cerObj.id})" type="submit">通过</button></td>
                            <td> <button onclick="notPass(${cerObj.id})" type="submit">不通过</button></td>
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
