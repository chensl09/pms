<%@page pageEncoding="utf-8" language="java"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<h2>Hello World!</h2>
<form:form id="form" method="post"
           modelAttribute="user">
<form:errors path="loginName" />
</form:form>
</body>
</html>
