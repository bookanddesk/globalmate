<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common.jsp" %>
<html>
<head>
    <script type="text/javascript">
        function getConceretPageParams() {
            return {
                queryUrl : "${path}/rest/basicData/schoolQuery",
                name :$('#schoolNameId').val()
            }
        }

        function exportExcelTemplate(){
            window.location.href = "<%=path%>/rest/basicData/exportSchool";
        }

        function importExcel() {
            var queryUrl = "<%=path%>/rest/basicData/importSchool";
            var file = new FormData(document.getElementById("fileForm"))
            $.ajax({
                type: 'POST',
                url: queryUrl,
                data: file,
                processData: false,
                contentType: false,
                success: function (data) {
                    if ('success' === data.success) {
                        $('#contractFile').val(data.detailMsg.data.fileName);
                        $('#contractFilePath').val(data.detailMsg.data.filePath)
                        $.tooltip(data.detailMsg.data, 2000, true);
                    } else {
                        $.tooltip('导入失败！');
                    }
                },
                error: function (e) {
                    $.tooltip('导入失败！')
                }
            });
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

                <form action="${pageContext.request.contextPath}/rest/basicData/schoolQuery" method="get">
                    <ul>
                        <li class="ding_rig">
                            <p>
                            <input type="text" id="schoolNameId" name="name" class="din_input"
                                   value="${queryParam.name}"
                                   placeholder="学校名称">&nbsp;&nbsp;
                            </p>
                            <p>
                                <button type="submit">查询</button>
                            </p>
                            <p>
                                <button type="button" onclick="exportExcelTemplate()">导出模板</button>
                            </p>
                        </li>
                    </ul>
                </form>

                <p>
                <form id="fileForm" action="<%=path%>/rest/basicData/importSchool"
                      method="post" enctype="multipart/form-data">
                    选择文件:<input type="file" name="file"/>
                    <input type="submit" value="导入"/>
                </form>
                </p>
            </div>

            <div class="Order_table" style="/**width:1000px; height:500px;**/ overflow:scroll;">
                <table width="100%" border="0" align="center"><!-- width="1700px"-->
                    <tr class="tc_ord">
                        <td width="3%">学校名称</td>
                        <td width="3%">所在地</td>
                        <td width="5%">校徽</td>
                    </tr>

                    <c:forEach items="${pageInfo.list}" var="schoolObj" varStatus="index">
                        <tr align="center" valign="middle" class="dr_ord">
                            <td style="display: none;">${schoolObj.id}</td>
                            <td>${schoolObj.name}</td>
                            <td>${schoolObj.location}</td>
                            <td><img src=${schoolObj.ext3}></td>
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
