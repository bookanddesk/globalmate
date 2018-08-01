<%@ page import="com.globalmate.data.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>首页</title>
    <%@ include file="common.jsp"%>
</head>
<body>

<!----头部---->
<div class="manager-header">
	 <div class="manager-top">
		  <ul style="padding-right:156px;">
		   <li class="code_l"><i class="icon-user"></i>
		      <p style="display:none">
			      <span class="code"><img src="<%=request.getContextPath() %>/static/images/code_jiao.png"></span>
			      <span><a href="#" onclick="logout();">退出登录</a></span>
     		 </p>
  		  </li>
		   <li> 欢迎!&nbsp;&nbsp; ${user.nikename}</li>
		  </ul>
	 </div>
	 <div class="head_wai">
	   <div class="head">
	      <div class="logo">
	         <a href="#"><img src="<%=request.getContextPath() %>/static/images/logo.svg" /></a>
	      </div>
	      <div class="nav">
	      </div>
	   </div>
	 </div>
</div>


 <iframe name="content" class="ui-layout-center"
        src="${pageContext.request.contextPath}/index.jsp" frameborder="0" scrolling="auto" style="
    width: 80%;
    height: 80%;
"></iframe>
<div class="container_left_box">
    <div class="container_left">
        <div class="container_left_fist">
            <ul class="biaot" onClick="turnit(1,1);">
                <li><a onclick="javascript:void(0);">
                    <p>基础信息</p>
                    <i><img src="<%=request.getContextPath() %>/static/images/a.png" id="img1"></i> </a></li>
            </ul>
            <ul class="open" id="content1">
                <li class="hov_bg"><a href="<%=request.getContextPath()%>/rest/userQuery" target="content">用户信息</a></li>
                <li class="hov_bg"><a href="<%=request.getContextPath() %>/rest/evaluateQuery" target="content">评价信息</a></li>
                <li class="hov_bg"><a href="<%=request.getContextPath() %>/rest/cetifiyQuery" target="content">认证信息</a></li>
            </ul>
        </div>

</div>
    </div>


<script>
    $(function () {
        $(document).ready(function () {
        });
    });

    function turnit(ss,ii){
        for (i=1;i<=ss;i++){
            try{
                if (i==ii){
                    if (window.eval("content"+i).style.display=="none"){
                        window.eval("content"+i).style.display="";
                        window.eval("img"+i).src=webpath+"/static/images/b.png";
                    }else{
                        window.eval("content"+i).style.display="none";
                        window.eval("img"+i).src=webpath+"/static/images/a.png";
                    }
                }else{
                    window.eval("content"+i).style.display="none";
                    window.eval("img"+i).src=webpath+"/static/images/a.png";
                }
            }catch(e){}
        }
    }

    $('.open li a').click(function () {
        var _this = this;
        $('.open li a').each(function () {
            this.style.background = this == _this ? '#e4edf7' : '#fafbff';
        });
    });

    function logout(){
        window.location.href = "<%=request.getContextPath()%>/rest/logout"
    }


</script>
<jsp:include page="footer.jsp" flush="true"></jsp:include>
</body>
</html>