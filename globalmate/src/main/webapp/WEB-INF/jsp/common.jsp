<%@ page import="com.globalmate.uitl.GMConstant" %>
<%
	String path = request.getContextPath();

	request.setAttribute("path", path);

	response.setHeader("Cache-Control","no-cache");   
	response.setDateHeader("Expires",0);   
	response.setHeader("Pragma","No-cache");
	
	Object sessionObj = request.getSession().getAttribute(GMConstant.SESSION);
	boolean isLogin = false;
	
	if(sessionObj!=null){
		isLogin = true;
	}
	request.setAttribute("isLogin", isLogin);
%>

<link href="${pageContext.request.contextPath}/static/css/font-awesome.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/static/css/head-foot-css.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/static/css/busi.css" rel="stylesheet" type="text/css"/>

<link href="${pageContext.request.contextPath}/static/css/common/common.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/static/css/register.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/static/css/reveal.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/static/css/common/districtDiv_modal.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/static/css/common/simpleAlert.css" rel="stylesheet"/>

<link rel="stylesheet" href="https://www.js-css.cn/jscode/open/open15/css/common.css"/><!-- 基本样式 -->
<link rel="stylesheet" href="https://www.js-css.cn/jscode/open/open15/css/animate.min.css"/> <!-- 动画效果 -->

<script src="${pageContext.request.contextPath}/static/js/jquery.min.js" type="text/javascript"></script>
<script src="<%=path %>/static/js/xialjs/city.js" type="text/javascript"></script>
<script src="<%=path %>/static/js/xialjs/nav.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.md5.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.hDialog.js"></script>
