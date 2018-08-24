<%@ page language="java" pageEncoding="utf-8" %>
<html>
<head>
<title>分页公共页面</title>
	<script type="text/javascript">
        function goPage(currentPage){
            var PAGE_SIZE = parseInt($("#pageSizeNum").text());
            var yeshu=$("#pageNo").val();
            if(!!yeshu){
                currentPage=yeshu;
            }
            var test = /^[0-9]*$/.test(yeshu);
            if(!test || yeshu > PAGE_SIZE || currentPage<1) {
                $.tooltip('请输入正确页码');
                $("#pageNo").val("");
                return false;
            }
            var pageNum = currentPage;
            var pageSize = ${pageInfo.pageSize};

            divQuery(pageNum,pageSize);
        }

        function divQuery(pageNum,pageSize){
            var param = getConceretPageParams();
            console.log(param)
            param.pageNum = pageNum;
            param.pageSize = pageSize;
            $.ajax({
                async : false,
                type : "GET",
                url : param.queryUrl,
                modal : true,
                showBusi : false,
                data : param,
                success: function (data) {
                    $("body").empty();
                    $("body").html(data);
                },
                error:function (data){
                    $.tooltip('数据查询失败');
                }
            });
        }

	</script>
</head>
<body>
${pageInfo.pageSize}条/页 共${pageInfo.total}条
&lt;&lt;
<c:choose>
	<c:when test="${pageInfo.pageNum >1}">
		<a href="#" onclick="goPage('1');">首页</a>
	</c:when>
	<c:otherwise>
		<a >首页</a>
	</c:otherwise>
</c:choose>
&lt; 
<c:choose>
	<c:when test="${pageInfo.pageNum >1}">
		<a href="#" onclick="goPage('<c:out value="${pageInfo.pageNum -1}"/>');">上一页</a>
	</c:when>
	<c:otherwise>
		<a >上一页</a>
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${pageInfo.pageNum < pageInfo.pages}">
		<a href="#" onclick="goPage('<c:out value="${pageInfo.pageNum +1}"/>');">下一页</a>
	</c:when>
	<c:otherwise>
		<a >下一页</a>      
	</c:otherwise>
</c:choose>
&gt; 
<c:choose>
	<c:when test="${pageInfo.pageNum<pageInfo.pages}">
		<a href="#" onclick="goPage('<c:out value="${pageInfo.pages}"/>');">尾页</a>
	</c:when>
	<c:otherwise>
		<a >尾页</a>
	</c:otherwise>
</c:choose>
&gt;&gt;  
第 <c:out value="${pageInfo.pageNum}"/>/<span id="pageSizeNum"><c:out value="${pageInfo.pages}"/></span>页
 到<input name="pageInfo.pageNum" type="text" id="pageNo" size="3" />页<label>
<input type="button" name="button4" onclick="goPage('');" id="button4" value="确定" /></label>
</body>
</html>