<%@page import="exam.edu.connection.DbCon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%-- <!DOCTYPE html>
<html>
<head>
	<%@include file="includes/head.jsp" %>
	<title>Insert title here</title>
</head>
<body>
	<%@include file="includes/header.jsp" %>
	<% DbCon.getConnection(); %>
	<%@include file="includes/footer.jsp" %>
	<%@include file="includes/scripts.jsp" %>
</body>
</html> --%>

<c:redirect url = "/home"/>