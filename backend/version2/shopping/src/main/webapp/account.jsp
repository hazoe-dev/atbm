<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="includes/head.jsp"%>
	<title>Tài khoản</title>
	<style>
	.order {
		border: #004315 2px solid;
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
		height: 300px;
	}
	
	.item-cart:hover {
		transform: scale(1) translateZ(0) translateY(0) !important;
		margin-top: -10px;
	}
	.h-500{
		max-height: 500px;
	}
	</style>
</head>
<body>
	<%@include file="includes/header.jsp"%>
	<div class="container-fluid bg-light" style="min-height: 525px;">
		<div class="container">
			<h3 class="m-0 p-3 text-uppercase">Tài khoản</h3>
			<div class="row">
						<c:if test="${not empty mgsUpdate}">
							<div class="form-group mt-2 mb-2" style="color: red; font-size: 14px; text-align: center; height: 40px;">
								${mgsUpdate}
							</div>
						</c:if>
				<div class="col-6 p-4">
					<div class="form-group mb-3">
						<label for="name" class="mb-2">Họ & Tên: </label> 
						<input type="text" class="form-control" id="namefull" name="name" value="${sessionScope.auth.fullname }">
					</div>
					<div class="form-group mb-3">
						<label for="address" class="mb-2">Địa chỉ: </label> 
						<input type="text" class="form-control" id="address" name="address" value="${sessionScope.auth.address }">
					</div>
					<div class="form-group mb-3">
						<label for="phone" class="mb-2">Số điện thoại: </label> 
						<input type="text" class="form-control" id="phone" name="phone" value="${sessionScope.auth.phone }">
					</div>
					<div class="pt-3 ">
						<button class="btn btn-success" onclick="updateAccount()">Cập nhật tài khoản</button>
					</div>
				</div>
				<div class="col-6 p-4 order mb-5 overflow-auto h-500">
					<h5 class="fs-6 text-uppercase fw-bold border-bottom border-4 pb-3">Danh sách đơn hàng</h5>
					<div>
					<c:choose>
					  <c:when test="${orders.size() > 0}">
					  	<c:forEach var = "order" items = "${orders}">
							<div class="list-group">
								<a href="/order?id=${order.id }" class="list-group-item list-group-item-action">Đơn hàng số ${order.id } - Ngày ${order.date }</a> 
							</div>				    
	                    </c:forEach>
					  </c:when>
					  <c:otherwise>
					    <p>Bạn chưa có đơn hàng</p>
					  </c:otherwise>
					</c:choose>	
					</div>
					<div class="pt-3 ">
						<button type="button" class="btn btn-success" onclick="location.href='/list?group=tea'">Tiếp tục xem sản phẩm</button>
					</div>
				</div>
			</div>
		</div>

	</div>

	<%@include file="includes/footer.jsp"%>
	<%@include file="includes/scripts.jsp"%>
	<script type="text/javascript">
	function updateAccount() {
		 const name = $('#namefull').val();
		 const address = $('#address').val();
		 const phone = $('#phone').val();
		 window.location.href="/update-account?name="+name+"&address="+address+"&phone="+phone;
	}
	
	</script>
</body>
</html>