<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<%@include file="includes/head.jsp"%>
<title>Đơn hàng</title>
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

.w-200 {
	min-width: 120px;
}
.info {
	background: white;
    border: 2px solid #00bd00;
    border-radius: 3px;
}
.bill{
	border: 2px solid #00bd00;
	background: #fefeff;
}
.list {
	border-right: 2px solid #00bd00;
	background: #fefeff;
}

.total {
	border-left : 2px solid #00bd00;
	background: #fefeff;
}

</style>
</head>
<body>
	<%@include file="includes/header.jsp"%>
	<div class="container-fluid bg-light" style="min-height: 525px;">
		<div class="container pb-5">
			<h3 class="m-0 ps-3 pe-3 pt-5 mb-3">ĐƠN HÀNG - Số ${order.id } - ${order.status }</h3>
			<div class="row info ">
				<div class="col-lg-12  m-0 p-3 row">
					<div class="col-lg-4">
						<div class="form-group ">
							<label for="name" class="mb-2 w-200">Họ & Tên: </label> <strong>${auth.fullname }</strong>
						</div>
						<div class="form-group ">
							<label for="phone" class="mb-2 w-200">Số điện thoại: </label> <strong>${auth.phone }</strong>
						</div>
					</div>
					<div class="col-lg-8">
						<div class="form-group ">
							<label for="address" class="mb-2 w-200">Địa chỉ: </label> <strong>${auth.address }n</strong>
						</div>
						<div class="form-group ">
							<label for="note" class="mb-2 w-200">Ghi chú: </label> <strong>${order.note }</strong>
						</div>
					</div>
				</div>
			</div>
			<h4 class="m-0 ps-3 pe-3 pt-5 mb-2">Danh sách sản phẩm</h4>
			<div class="row bill mt-3" >
				<div class="col-lg-8 p-4 list">
					<table class="table table-responsive align-middle">
						<thead>
							<tr>
								<th colspan="2" style="width: 40%;">SẢN PHẨM</th>
								<th style="width: 15%">LOẠI</th>
								<th style="width: 15%">GIÁ</th>
								<th style="width: 15%; font-size: 15px;white-space: nowrap;">SỐ LƯỢNG</th>
								<th style="width: 15%;white-space: nowrap;">TẠM TÍNH</th>
							</tr>
						</thead>
						<tbody>
							
							<c:choose>
								<c:when test="${details.size() > 0}">
									<c:forEach var="detail" items="${details}">
										<tr>
											<td style="width: 10%"><a href="/product?id=${detail.productId }">
												<img src="image/${detail.urlImg }" style="width: 45px; height: 45px" alt=""></a></td>
											<td>${detail.name} 
												<c:if test="${detail.priceKg !=null && detail.priceKg != '0đ/ 1kg'}">• ${detail.priceKg}</c:if></td>
											<td style="width: 15%">${product.typeName}</td>
											<td style="width: 15%"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${detail.price}" /> ₫</td>
											<td style="width: 15%; text-align: center;">x${detail.quantity}</td>
											<td style="width: 15%"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${product.price*detail.quantity}" /> ₫</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr> <td colspan=""> <p> Không tìm thấy sản phẩm</p></td> </tr>
								</c:otherwise>
							</c:choose>
							
							<!-- <tr>
								<td style="width: 10%"><a href="#"><img
										src="image/tra-moc-cau-dac-biet-loc-tan-cuong-3-1.jpg"
										style="width: 45px; height: 45px" alt=""></a></td>
								<td>Trà Móc Câu Đặc Biệt • 600.000đ / 1kg</td>
								<td style="width: 15%">92.000₫</td>
								<td style="width: 15%; text-align: center;">x1</td>
								<td style="width: 15%">92.000₫</td>
							</tr> -->
							
						</tbody>
					</table>
				</div>
				<div class="col-lg-4 border-start p-4 total">
					<h5 class="fs-6 text-uppercase fw-bold border-bottom border-4 pb-3">Thành
						tiền</h5>
					<div>
						Tạm tính <span class="float-end"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${order.total - order.shipCost}" /> ₫</span>
					</div>
					<div class="border-bottom pb-3">
						Vận chuyển <span class="float-end"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${order.shipCost}" /> ₫</span>
					</div>
					<div class="border-bottom border-4 pb-3">
						Tổng cộng <span class="float-end"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${order.total}" /> ₫</span>
					</div>
					<div class="pt-3 pb-3">
						<!--            <button type="button" class="btn btn-success w-100">TIẾN HÀNH THANH TOÁN</button>
 -->
					</div>
				</div>
			</div>			
		</div>

	</div>
	<%@include file="includes/footer.jsp"%>
	<%@include file="includes/scripts.jsp"%>
</body>
</html>