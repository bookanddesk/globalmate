<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>GM管理平台</title>
    <%@ include file="common.jsp" %>
</head>
<body>

<div class="header" id="mask">
    <div class="top">
        <div class="li-logo"></div>
        <div class="li-login">
            <ul>
                <li class="code_l"><i class="icon-user"></i><a href="#" class="loginClass" onclick="showMask();">登录</a>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="main">

    <div class="big_banner">
        <div class="flexslider">
            <ul class="slides">
                <li style="height: 804px;background:url(${pageContext.request.contextPath}/static/images/bg.jpg) 50% 0 no-repeat;">
                </li>
            </ul>
        </div>

    </div>
    <div id="HBox" class="animated" style="display: none;height: 180px;">
        <form action="#" id="loginForm" method="post" onsubmit="return false;">
            <ul class="list">
                <li><strong>帐 号 <font color="#ff0000">*</font></strong>
                    <div class="fl">
                        <input type="text" name="loginname" value="" class="ipt nickname">
                    </div>
                </li>
                <li><strong>密 码 <font color="#ff0000">*</font></strong>
                    <div class="fl">
                        <input type="password" name="password" value="" class="ipt email">
                    </div>
                </li>
                <li><input type="submit" value="登录" class="submitBtn"></li>
            </ul>
        </form>
        <a id="HCloseBtn" title="关闭" onclick="closeDig();"
           style="width: 24px; height: 24px; line-height: 18px; display: inline-block; cursor: pointer; background-color: #ccc; color: #fff; font-size: 1.4em; text-align: center; position: absolute; top: 8px; right: 8px;">
            <span style="width: 24px; height: 24px; display: inline-block;">×</span></a>
    </div>

    <jsp:include page="footer.jsp" flush="true"></jsp:include>

</body>
<script type="text/javascript">
    $(document).ready(function () {

        $('.loginClass').hDialog({
            effect: 'fadeOut',
            title: '登录',
            width: 320,
            height: 220
        });

        $('.submitBtn').click(
            function () {
                var PhoneReg = /^0{0,1}(13[0-9]|15[0-9]|153|156|18[7-9])[0-9]{8}$/; //手机正则
                var $loginName = $('.nickname');
                var $password = $('.email');
                var encodePwd = "";

                if ($loginName.val() == '') {
                    $.tooltip('手机号还没填呢...');
                    $loginName.focus();
                } else if (!PhoneReg.test($loginName.val())) {
                    $.tooltip('手机号不合法...');
                    $loginName.focus();
                } else if ($password.val() == '') {
                    $.tooltip('密码还没填呢...');
                    $password.focus();
                } else {
                    var pwd = $password.val();
                    encodePwd = $.md5(pwd);
                    $('.email').val(encodePwd);

                    var loginURL = "<%=path %>/rest/user/login/" + $loginName.val() + '/' + pwd;
                    $.ajax({
                        async: false,
                        type: "GET",
                        url: loginURL,
                        modal: true,
                        showBusi: false,
                        success: function (data) {
                            if (data.success) {
                                window.location.href = "<%=path %>/rest/home?" + window.btoa('loginname=' + $loginName.val());
                            } else {
                                $.tooltip('登陆失败.');
                            }
                        },
                        error: function (data) {
                            $.tooltip('请求失败.');
                        }
                    });
                }
            });
    });

    function closeDig() {
        $('.loginClass').hDialog('close', {
            box: '#HBox'
        });
    }

    function showMask() {
        $("#HBox")[0].style.display = "block";
    }
</script>
</html>