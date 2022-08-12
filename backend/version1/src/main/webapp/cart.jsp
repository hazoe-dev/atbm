<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="includes/head.jsp"%>
<title>Giỏ hàng</title>
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

#input {
	margin: 0 auto;
}

.value-button {
	display: inline-block;
	border: 1px solid #ddd;
	margin: 0px;
	width: 40px;
	height: 40px;
	text-align: center;
	padding: 2px 0;
	background: #eee;
	-webkit-touch-callout: none;
	-webkit-user-select: none;
	-khtml-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

.value-button:hover {
	cursor: pointer;
}

form #decrease {
	margin-right: -4px;
	border-radius: 5px 0 0 5px;
	font-size: 20px;
}

form #increase {
	margin-left: -4px;
	border-radius: 0 5px 5px 0;
	font-size: 20px;
}

form #input-wrap {
	margin: 0px;
	padding: 0px;
}

input.number {
	text-align: center;
	border: none;
	border-top: 1px solid #ddd;
	border-bottom: 1px solid #ddd;
	margin: 0px;
	width: 50px;
	height: 40px;
}

input[type=number]::-webkit-inner-spin-button, input[type=number]::-webkit-outer-spin-button
	{
	-webkit-appearance: none;
	margin: 0;
}
</style>

</head>
<body>
	<%@include file="includes/header.jsp"%>
	<div class="container-fluid bg-light" style="min-height: 525px;">
		<div class="container">
			<h3 class="m-0 p-3">GIỎ HÀNG</h3>
			<div class="row">
				<div class="col-lg-9 p-4">
					<table class="table table-responsive align-middle">
						<thead>
							<tr>
								<th colspan="3" ">SẢN PHẨM</th>
								<th >LOẠI</th>
								<th >GIÁ</th>
								<th style=" font-size: 15px;">SỐ LƯỢNG</th>
								<th >TẠM TÍNH</th>
							</tr>
						</thead>
						<tbody>

							<c:choose>
								<c:when test="${products.size() > 0}">
									<c:forEach var="product" items="${products}">

										<tr>
											<td style="width: 5%"> <button class="border rounded-circle"><i class="fa-solid fa-x" onclick="location.href=`/remove-from-cart?productId=${product.productId}&typeId=${product.typeId}`"></i></button> </td>
											<td style="width: 10%">
												<a href="/product?id=${product.productId }">
													<img src="image/${product.urlImg}"  style="width: 45px; height: 45px" alt="">
												</a>
											</td>
											<td style="width: 30%">${product.name} 
												<c:if test="${product.priceKg !=null && product.priceKg != '0đ/ 1kg'}">• ${product.priceKg}</c:if>
											</td>
											<td style="width: 11%"> ${product.typeName} </td>
											<td style="width: 12%">
												<fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${product.price}" /> ₫
											</td>
											<td style="width: 20%">
												
												<form id="input">
												<%-- <div class="value-button" id="decrease" onclick="decreaseValue(${product.productId}, ${product.typeId})" value="Decrease Value">-</div> --%>
 													<div class="value-button" id="decrease" onclick="location.href='/change-quantity?action=dec&productId=${product.productId}&typeId=${product.typeId}'" >-</div>
													<input type="number" id="quantity-${product.productId}-${product.typeId}" min="1" value="${product.quantity}" maxlength="2" max="100" class="number" readonly />
													<div class="value-button" id="increase" onclick="location.href='/change-quantity?action=inc&productId=${product.productId}&typeId=${product.typeId}'" >+</div>
												<%--<div class="value-button" id="increase" onclick="increaseValue(${product.productId},${product.typeId})" value="Increase Value">+</div> --%>
												</form>
												<input type="hidden" value="${product.maxQuantity}" id="maxQuantity-${product.productId}">
											</td>
											<td style="width: 12%">
												<fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${product.tmpPrice}" /> ₫
											</td>
										</tr>
									</c:forEach>

								</c:when>
								<c:otherwise>
									<tr> <td colspan="7"> <p> ${msg}</p></td> </tr>
								</c:otherwise>
							</c:choose>
				
				
							 <c:choose>
				               <c:when test="${status == false}"> 
				                   <tr> <td colspan="7"> 
				                	<div class="alert alert-danger text-danger mt-3">
										<strong>Thất bại!</strong> ${msgQuantity }
									</div>
				                	</td> </tr>
				                </c:when>
				                <c:when test="${status == true}"> 
				                	<tr> <td colspan="7"> 
				                	<div class="alert alert-success text-success mt-3">
										<strong>Thành công!</strong> ${msgQuantity }
									</div>
				                	</td> </tr>
				                </c:when>
			                  	<c:otherwise>
									
								</c:otherwise>
			                  </c:choose>
							
						</tbody>
					</table>
					<div>
						<button
							class="btn btn-outline-success btn-md my-0 p waves-effect waves-light text-uppercase fw-bold"
							type="button" onclick="location.href='/list?group=tea'">
							<i class="fa-solid fa-left-long"></i> Tiếp tục xem sản phẩm
						</button>
						<button
							class="btn btn-success btn-md my-0 p waves-effect waves-light text-uppercase fw-bold"
							style="color: #f5f5f5;" type="button" onclick="location.href='/cart'">
							Cập nhật giỏ hàng <i class="fa-solid fa-arrow-rotate-left"></i>
						</button>
					</div>
				</div>
				<div class="col-lg-3 border-start p-4">
					<h5 class="fs-6 text-uppercase fw-bold border-bottom border-4 pb-3">Cộng
						giỏ hàng</h5>
					<div>
						Tạm tính 
						<span class="float-end">
							<fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${summary}" /> ₫
						</span>
					</div>
					<div class="border-bottom pb-3">
						Vận chuyển 
						<span class="float-end">
							<fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${shipCost}" /> ₫
						</span>
					</div>
					<div class="border-bottom border-4 pb-3">
						Tổng cộng 
						<span class="float-end">
							<fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${total}" /> ₫
						</span>
					</div>
					<div class="pt-3 pb-3">
						<button type="button" class="btn btn-success w-100"
						<c:if test="${cart==null || cart.size()==0}">disabled</c:if>
						onclick="location.href='/checkout'">TIẾN
							HÀNH THANH TOÁN</button>
					</div>
				</div>
			</div>
		</div>

	</div>

	<%@include file="includes/footer.jsp"%>
	<%@include file="includes/scripts.jsp"%>

	<script type="text/javascript">
		function increaseValue(productId,typeId) {
			var quantityId = '#quantity-'+productId+'-'+typeId;
			var value = $(quantityId).val();
			value = isNaN(value) ? 0 : value;
			var maxQuantityId = '#maxQuantity-'+productId;
			if (value < $(maxQuantityId).val()) {
				value++;
			}
			$(quantityId).val(value);
		}

		function decreaseValue(productId,typeId) {
			var quantityId = '#quantity-'+productId+'-'+typeId;
			var value = $(quantityId).val();
			value = isNaN(value) ? 0 : value;
			value < 1 ? value = 1 : '';
			if (value > 1) {
				value--;
			}
			$(quantityId).val(value);
		}
	</script>
</body>
</html>