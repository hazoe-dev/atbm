<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="includes/head.jsp"%>
<title>Thanh toán</title>
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
.my-border {
	border: 1px solid #dee2e6;
    border-top: 2px solid #000900;
}
</style>

</head>
<body>
	<%@include file="includes/header.jsp"%>
	<div class="container-fluid bg-light" style="min-height: 525px;">
		<div class="container pb-4">
			<h3 class="m-0 p-3 text-uppercase">Thanh toán</h3>
			<form class="row" action="/place-order" method="get" accept-charset="UTF-8">
				<div class="col-lg-6 col-sm-12 p-4">
				 <c:choose>
				      <c:when test="${msgStatus == false}"> 
				            <div class="alert alert-danger text-danger mt-3"> <strong>Lỗi!</strong> ${msgCheckout } </div>
				      </c:when>
				      <c:when test="${msgStatus == true}"> 
				            <div class="alert alert-danger text-danger mt-3"> <strong>Thất bại!</strong> ${msgCheckout } </div>
				      </c:when>
					<c:otherwise></c:otherwise>
			      </c:choose>
					<div class="form-group mb-3">
						<label for="name" class="mb-2"> <small>Họ & Tên *</small> </label> 
						<input type="text" class="form-control" id="name" name="fullname" 
						<c:if test="${fullname !=null && fullname != ''}">value = "${fullname}"</c:if>
						 required>
					</div>
					<div class="form-group mb-3">
						<label for="address" class="mb-2"><small>Địa chỉ *</small></label> 
						<input type="text" class="form-control" id="address" name="address"
							<c:if test="${address !=null && address != ''}">value = "${address}"</c:if>
							required>
					</div>
					<div class="form-group mb-3">
						<label for="phone" class="mb-2"><small>Số điện thoại *</small></label> 
						<input type="text" class="form-control" id="phone" name="phone" 
							<c:if test="${phone !=null && phone != ''}">value = "${phone}"</c:if>
						required>
					</div>
					<div class="form-group mb-3">
						<label for="note" class="mb-2"> <small>Ghi chú đơn hàng (tuỳ chọn)</small> </label>
						<textarea type="text" class="form-control" id="note" name="note"
							cols="20" rows="3"
							placeholder="Ghi chú về đơn hàng, ví dụ: thời gian hay chỉ dẫn địa điểm giao hàng chi tiết hơn."></textarea>
					</div>
				</div>
				<div class="col-lg-6 col-sm-12 p-4 order mb-5">
					<h5 class="fs-6 text-uppercase fw-bold border-bottom border-4 pb-3">ĐƠN
						HÀNG CỦA BẠN</h5>
					<div>
						<table class="table table-responsive align-middle">
							<thead>
								<tr>
									<th style="width: 40%;">SẢN PHẨM</th>
									<th style="width: 15%;"><small>LOẠI</small></th>
									<th style="width: 15%;"><small>GIÁ</small></th>
									<th style="width: 15%;"><small>SỐ LƯỢNG</small></th>
									<th style="width: 15%"><small>TẠM TÍNH</small></th>
								</tr>
							</thead>
							<tbody>
								
								<c:choose>
								<c:when test="${products.size() > 0}">
									<c:forEach var="product" items="${products}">
										<tr>
											<td>${product.name} <c:if test="${product.priceKg !=null && product.priceKg != '0đ/ 1kg'}">• ${product.priceKg}</c:if></td>
											<td style="white-space: nowrap;">${product.typeName}</td>
											<td><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${product.price}" /> ₫</td>
											<td style="text-align: center;">x ${product.quantity}</td>
											<td><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${product.tmpPrice}" /> ₫</td>
										</tr>
									</c:forEach>

								</c:when>
								<c:otherwise>
									<tr> <td colspan="5"> <p> "Không tìm thấy sản phẩm"</p></td> </tr>
								</c:otherwise>
							</c:choose>
								
							</tbody>
						</table>
					</div>
					<div class="pt-3">
						<span class="fw-bold">• Tạm tính</span> <span class="float-end pe-3"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${summary}" /> ₫</span>
					</div>
					<div class=" pb-3">
						<span class="fw-bold">• Vận chuyển</span> <span class="float-end pe-3"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${shipCost}" /> ₫</span>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio"
							name="flexRadioDefault" id="flexRadioDefault2" checked> <label
							class="form-check-label" for="flexRadioDefault2"> Thanh
							toán khi giao hàng </label>
					</div>
					<div class="my-border p-3 bg-white mb-3 mt-3">
						<span class="fw-bold"> Tổng cộng</span> <span class="float-end "><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${total}" /> ₫</span>
					</div>
					
					<div class="pt-3 text-center">
						<button type="submit" class="btn btn-success"  style="width: 90%">ĐẶT HÀNG</button>
					</div>
				</div>
			</form>
		</div>

	</div>
	<%@include file="includes/footer.jsp"%>
	<%@include file="includes/scripts.jsp"%>
</body>
</html>