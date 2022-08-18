<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="includes/head.jsp"%>
<title>Quản lý khóa</title>
<style>
.green-tea {
	color: #008000 !important;
}

.header {
	font-size: 14px;
	font-weight: 600;
	text-transform: uppercase;
	background-color: #004315;
}

.category-nav {
	border-top: #004315 2px solid;
}

.item-cart {
	transition: opacity 0.3s, transform 0.3s, background-color 0.3s,
		-webkit-transform 0.3s;
}

.item-cart .card {
	box-shadow: rgba(50, 50, 105, 0.15) 0px 2px 5px 0px, rgba(0, 0, 0, 0.05)
		0px 1px 1px 0px;
}

.item-cart .card img {
	height: 250px;
}

.item-cart:hover {
	transform: scale(1) translateZ(0) translateY(0) !important;
	margin-top: -10px;
}

.bg-login {
	background-color: #e7e7e7;
}

.login {
	min-height: 450px;
}
</style>
</head>
<body>
	<%@include file="includes/header.jsp"%>
	<div class="container-fluid bg-light bg-login">
		<div class="container login pt-5"></div>
	</div>
	<%@include file="includes/footer.jsp"%>
	<%@include file="includes/scripts.jsp"%>
</body>
</html>