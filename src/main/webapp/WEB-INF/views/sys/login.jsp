<%--
  Created by IntelliJ IDEA.
  User: Shilic
  Date: 2016/1/21
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>${basePath}</title>
</head>
<body>
  <form id="loginForm" class="form-signin" action="${basePath}/login" method="post">
    <label class="input-label" for="username">登录名</label>
    <input type="text" id="username" name="username" class="input-block-level required" value="${username}">
    <label class="input-label" for="password">密码</label>
    <input type="password" id="password" name="password" class="input-block-level required">
    <input class="btn btn-large btn-primary" type="submit" value="登 录">&nbsp;&nbsp;
    <label for="rememberMe" title="下次不需要再登录">
      <input type="checkbox" id="rememberMe"
             name="rememberMe" ${rememberMe ? 'checked' : ''}> 记住我</label>

  </form>
</body>
</html>
