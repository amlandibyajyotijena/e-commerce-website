<%@page import="com.mycart.entities.User"%>
<%
User user = (User) session.getAttribute("current_user");
if(user==null){
	session.setAttribute("messege", "you are not logged in log in first");
	response.sendRedirect("login.jsp");
	return;
}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ShopNow</title>
</head>
<body>
<%@include file="index.jsp" %>
</body>
</html>