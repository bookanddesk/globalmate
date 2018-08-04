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
                        <td width="5%">认证图片1</td>
						<td width="5%">认证图片2</td>
                        <td width="3%">认证时间</td>
                        <td width="3%">认证状态</td>
                        <td width="1%"></td>
                        <td width="1%"></td>
                    </tr>

                    <c:forEach items="${pageInfo.list}" var="cerObj" varStatus="index">
                        <tr align="center" valign="middle" class="dr_ord">
                            <td style="display: none;">${cerObj.id}</td>
                            <td>${cerObj.uName}</td>
							<script type="text/javascript">
								if ('${cerObj.cetifyType}' == 'IDCARD') {
									document.write('<td>身份证</td>');
								}
								else if('${cerObj.cetifyType}' == 'PASSPORT') {
									document.write('<td>护照</td>');
								}
								else if('${cerObj.cetifyType}' == 'STUDENTID') {
									document.write('<td>学生证</td>');
								}
								else if('${cerObj.cetifyType}' == 'ALIPAYID') {
									document.write('<td>支付宝</td>');
								}
								var photoUrl='${cerObj.certifyPhoto}';
								if (photoUrl) {
									var photos= new Array(); 
									photos=photoUrl.split(";"); 
									document.write('<td><img src='+photos[0]+' width="128" height="128"></td>');
									document.write('<td><img src='+photos[1]+' width="128" height="128"></td>');
									
								}
							</script>

                            <td><fmt:formatDate value="${cerObj.certifyTime}" type="both" pattern="yyyy-MM-dd HH:mm" /></td>
							<script type="text/javascript">
								if (${cerObj.isEffective} == 1) {
									document.write('<td>通过</td>');
								}
								else if(${cerObj.isEffective} == 2) {
									document.write('<td>不通过</td>');
								}
								else 
									document.write('<td>未校验</td>');
							</script>
                            <td> <button onclick="pass('${cerObj.id}')" style="color: red; width: 100%;" class="btn btn-block btn-danger" >通过</button></td>
                            <td> <button onclick="notPass('${cerObj.id}')" style="color: red; width: 100%;" class="btn btn-block btn-danger">不通过</button></td>
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
