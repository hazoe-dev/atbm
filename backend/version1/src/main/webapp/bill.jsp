<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="includes/head.jsp"%>
	<title>Hóa đơn</title>
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
	</style>
</head>
<body>
	<%@include file="includes/header.jsp" %>
	<div class="container-fluid bg-light" style="min-height: 525px;">
		<div class="container">
			<h3 class="m-0 p-3 text-uppercase">Hóa đơn</h3>
			<div class="row">
				<div class="col-6 p-4">
					<div class="form-group mb-3">
						<label for="name" class="mb-2">Họ & Tên: </label> <input
							type="text" class="form-control" id="name" name="name" readonly>
					</div>
					<div class="form-group mb-3">
						<label for="address" class="mb-2">Địa chỉ: </label> <input
							type="text" class="form-control" id="address" name="address"
							readonly>
					</div>
					<div class="form-group mb-3">
						<label for="phone" class="mb-2">Số điện thoại: </label> <input
							type="text" class="form-control" id="phone" name="phone" readonly>
					</div>
					<div class="form-group mb-3">
						<label for="createdDate" class="mb-2">Ngày tạo: </label> <input
							type="text" class="form-control" id="createdDate"
							name="createdDate" readonly>
					</div>
					<div class="form-group mb-3">
						<label for="note" class="mb-2">Ghi chú đơn hàng (tuỳ chọn)</label>
						<textarea type="text" class="form-control" id="note" name="note"
							cols="20" rows="3"
							placeholder="Ghi chú về đơn hàng, ví dụ: thời gian hay chỉ dẫn địa điểm giao hàng chi tiết hơn."
							readonly></textarea>
					</div>
				</div>
				<div class="col-6 p-4 order mb-5">
					<h5 class="fs-6 text-uppercase fw-bold border-bottom border-4 pb-3">ĐƠN
						HÀNG CỦA BẠN</h5>
					<div>
						<table class="table table-responsive align-middle">
							<thead>
								<tr>
									<th style="width: 55%;">SẢN PHẨM</th>
									<th style="width: 10%;"><small>LOẠI</small></th>
									<th style="width: 15%;"><small>SỐ LƯỢNG</small></th>
									<th style="width: 20%"><small>TẠM TÍNH</small></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Trà Móc Câu Đặc Biệt • 600.000đ / 1kg</td>
									<td>100g</td>
									<td style="text-align: center;">x 1</td>
									<td>92.000₫</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="pt-3">
						<span class="fw-bold">• Tạm tính</span> <span class="float-end">92.000
							đ</span>
					</div>
					<div class=" pb-3">
						<span class="fw-bold">• Vận chuyển</span> <span class="float-end">25.000
							đ</span>
					</div>
					<div class="border p-3 bg-white mb-3">
						<span class="fw-bold"> Tổng cộng</span> <span class="float-end">117.000
							đ</span>
					</div>
					<div class="border p-3 bg-white mb-3">
						<span class="fw-bold">Thanh toán</span> <span class="float-end">Chuyển
							khoản</span>
					</div>
					<div class="pt-3 ">
						<button type="button" class="btn btn-success">Tiếp tục
							xem sản phẩm</button>
					</div>
				</div>
			</div>
		</div>

	</div>

	<%@include file="includes/footer.jsp" %>
	<%@include file="includes/scripts.jsp"%>
</body>
</html>