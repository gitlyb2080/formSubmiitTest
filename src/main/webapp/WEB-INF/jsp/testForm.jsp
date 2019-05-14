<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">

<body>
    <h1>tes--form--submit</h1>

    <form action="http://localhost:8080/vaSubmiit" onsubmit="return dosubmit()" method="post">
    <%--使用EL表达式取出存储在session中的token，实际情况下，请用 hidden属性 隐藏--%>
    <input type="text" name="token" value="${token}"/> 
    
    userName:<input type="text" name="username">
    <input type="submit" value="提交" id="submit">
</form>


</body>

</html>