<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="includes/head.jsp"%>
<title>Đăng nhập</title>
<style>
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
	height: 300px;
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

.card {
	width: 400px;
	box-shadow: rgb(0 0 0/ 7%) 0px 1px 2px, rgb(0 0 0/ 7%) 0px 2px 4px,
		rgb(0 0 0/ 7%) 0px 4px 8px, rgb(0 0 0/ 7%) 0px 8px 16px,
		rgb(0 0 0/ 7%) 0px 16px 32px, rgb(0 0 0/ 7%) 0px 32px 64px;
}
</style>
</head>
<body>
	<%@include file="includes/header.jsp"%>
	<div class="bg-login">
		<div class="container login pt-5">
			<div class="card mx-auto ">
				<div class="card-header text-center fw-bold">Đăng nhập</div>
				<div class="card-body">
					<form action="user-login" method="post">
						<c:if test="${not empty error}">
							<div class="form-group mt-2 mb-2" style="color: red; font-size: 14px; text-align: center; height: 40px;">
								${sessionScope.error}
							</div>
						</c:if>
						<div class="form-group mt-2 mb-2">
							<label>Email address</label> <input type="email"
								name="login-email" class="form-control"
								placeholder="Enter email">
						</div>
						<div class="form-group mt-4 mb-2">
							<label>Password</label> <input type="password"
								name="login-password" class="form-control"
								placeholder="Password">
						</div>
						<div class="text-center mt-4">
							<button type="submit" class="btn btn-primary">Login</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<%@include file="includes/footer.jsp"%>
	<%@include file="includes/scripts.jsp"%>
</body>
</html>