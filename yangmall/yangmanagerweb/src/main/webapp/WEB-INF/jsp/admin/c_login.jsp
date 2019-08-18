<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录 — 商城后台管理系统 </title>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>

    <base href="<%=basePath%>">

    <link rel="icon" href="favicon.ico">
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/font-awesome.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/app.css" rel="stylesheet">
  </head>

  <body class="login">
    <div>
      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
            <form action="login.html" method="POST" role="form">
              <h1>管理员登录</h1>
                ${shiroLoginFailure}
                ${msg}
              <div>
                <input type="text" name="userName" class="form-control" placeholder="管理员登录名" required="">
              </div>
              <div>
                <input type="password" name="password" class="form-control" placeholder="管理员登录密码" required="">
              </div>
                <div>
                    <input type="text" name="captcha" class="form-control" placeholder="验证码" required="">
                </div>
                <img id="captcha" src="captcha"  alt="验证码加载失败" onclick="captcha(this)"/>

                <!--
                <div>
                <a class="btn btn-default submit">&nbsp;登&nbsp;录&nbsp;</a>
              </div>
              -->

                <!--
                <input type="submit" value="登录">
                -->

                <div>
                <button type="submit" class="btn btn-default">登录</button>
                </div>

              <div class="clearfix"></div>

              <div class="separator">
                <div class="clearfix"></div>
                <br>

              </div>
            </form>
          </section>
        </div>
      </div>
    </div>

    <script type="text/javascript">
        function captcha(e) {
            e.src="captcha?ss="+ Math.random();
        }
    </script>


  </body></html>