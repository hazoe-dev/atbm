<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="includes/head.jsp"%>
<title>Trang chủ</title>
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
	.active{
		color:black !important;
	}
	</style>
</head>
<body>
	<%@include file="includes/header.jsp"%>
	<div class="container-fluid bg-light">
		<div class="container pt-2">
			<!-- <nav aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="/" class="link-secondary">Trang
							chủ</a></li>
					<li class="breadcrumb-item"><a href="#" class="link-secondary">Sản
							phẩm trà</a></li>
					<li class="breadcrumb-item active" aria-current="page">Trà sen</li>
				</ol>
			</nav> -->

			<div id="carouselExampleCaptions" class="carousel slide"
				data-bs-ride="carousel">
				<div class="carousel-indicators">
					<button type="button" data-bs-target="#carouselExampleCaptions"
						data-bs-slide-to="0" class="active" aria-current="true"
						aria-label="Slide 1"></button>
					<button type="button" data-bs-target="#carouselExampleCaptions"
						data-bs-slide-to="1" aria-label="Slide 2"></button>
					<button type="button" data-bs-target="#carouselExampleCaptions"
						data-bs-slide-to="2" aria-label="Slide 3"></button>
				</div>
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img src="image/giao-hang.jpg" class="d-block w-100" alt="...">
						<div class="carousel-caption d-none d-md-block">
							<h5>
								<i class="fa-solid fa-leaf"></i>&nbsp; Trà Vấp
							</h5>
							<p>Một tách trà - một chút nhâm nhi cho tâm hồn tươi mới</p>
						</div>
					</div>
					<div class="carousel-item">
						<img src="image/banner-doi-tra-hoan-tien.png"
							class="d-block w-100" alt="...">
						<div class="carousel-caption d-none d-md-block">
							<h5>Second slide label</h5>
							<p style="color: black;">Tận tâm và cam kết</p>
						</div>
					</div>
					<div class="carousel-item">
						<img src="image/banner-freeship.png" class="d-block w-100"
							alt="...">
						<div class="carousel-caption d-none d-md-block">
							<h5>Third slide label</h5>
							<p style="color: black;">Đặt là liên hệ ngay</p>
						</div>
					</div>
				</div>
				<button class="carousel-control-prev" type="button"
					data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>

			<div id="carouselExampleSlidesOnly" class="carousel slide mt-4"
				data-bs-ride="carousel">
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img src="image/services.jpg" class="d-block w-100" alt="...">
					</div>
				</div>
			</div>
		</div>
		<div class="container bg-white rounded-3 p-4 mt-4 mb-4 m-auto">
			<h4 class="fs-5 fw-bold">SẢN PHẨM TRÀ</h4>
			<ul class="nav category-nav">
				<li class="nav-item"><a
					class="nav-link 
					<c:if test="${cat==0 }">active</c:if>
					link-secondary fs-6 fw-bold d-inline"
					aria-current="page" href="/home">Bán chạy nhất </a> <span
					class="border-end "></span></li>
				<li class="nav-item"><a
					class="nav-link 
					<c:if test="${cat==1 }">active</c:if>
					link-secondary fs-6 fw-bold d-inline" href="/home?category=Trà Thái Nguyên&cat=1">Trà
						Thái Nguyên</a> <span class="border-end "></span></li>
				<li class="nav-item"><a
					class="nav-link 
					<c:if test="${cat==2 }">active</c:if>
					link-secondary fs-6 fw-bold d-inline" href="/home?category=Trà ướp hoa&cat=2">Trà
						ướp hoa</a> <span class="border-end "></span></li>
				<li class="nav-item"><a
					class="nav-link 
					<c:if test="${cat==3 }">active</c:if>
					link-secondary fs-6 fw-bold d-inline" href="/home?category=Trà ô long&cat=3">Trà
						ôlong</a> <span class="border-end "></span></li>
			</ul>

			<div class="row mt-3 mb-3 g-0 ">
			
				<c:choose>
					  <c:when test="${products.size() > 0}">
					  	<c:forEach var = "product" items = "${products}">
							<div class="col-lg-4 col-sm-6 item-cart ">
								<a href="/product?id=${product.id }" class="text-decoration-none link-dark text-center">
									<div class="card">
										<img src="image/${product.urlImg}"
											class="card-img-top" alt="...">
										<div class="card-body">
											<h5 class="card-title text-wrap fs-6">${product.name} 
		                                  <c:if test = "${product.priceKg !=null && product.priceKg != '0đ/ 1kg'}">• ${product.priceKg}</c:if></h5>
										</div>
									</div>
								</a>
							</div>			    
	                    </c:forEach>
					   	 
					  </c:when>
					  <c:otherwise>
					    ${msg}
					  </c:otherwise>
					</c:choose>	
				
				<!-- <div class="col-lg-4 col-sm-6 item-cart ">
					<a href="#" class="text-decoration-none link-dark text-center">
						<div class="card">
							<img src="image/tra-moc-cau-dac-biet-loc-tan-cuong-3-1.jpg"
								class="card-img-top" alt="...">
							<div class="card-body">
								<h5 class="card-title text-wrap fs-6">Trà Móc Câu Đặc Biệt
									• 600.000đ / 1kg</h5>
							</div>
						</div>
					</a>
				</div> -->

			</div>
		</div>

		<div class="container bg-white rounded-3 p-4 mt-4 mb-4 m-auto">
			<h4 class="fs-5 fw-bold">DỤNG CỤ TRÀ</h4>
			<div class="row mt-3 mb-3 g-0 ">
				<div class="col-lg-4 col-sm-6 item-cart ">
					<a href="/list?group=utensil&category=Ấm%20chén%20thưởng%20trà" class="text-decoration-none link-dark text-center">
						<div class="card">
							<img src="image/am-chen-uong-tra-1256.jpg" class="card-img-top"
								alt="...">
							<div class="card-body">
								<h5 class="card-title text-wrap fs-6">Ấm chén thưởng trà</h5>
							</div>
						</div>
					</a>
				</div>
				<div class="col-lg-4 col-sm-6 item-cart ">
					<a href="/list?group=utensil&category=Bàn%20trà%20-%20khay%20trà" class="text-decoration-none link-dark text-center">
						<div class="card">
							<img src="image/khay-tra-tra-3456-e1633664712888.jpg"
								class="card-img-top" alt="...">
							<div class="card-body">
								<h5 class="card-title text-wrap fs-6">Bàn trà - Khay trà</h5>
							</div>
						</div>
					</a>
				</div>
				<div class="col-lg-4 col-sm-6 item-cart ">
					<a href="/list?group=utensil&category=Dụng%20cụ%20khác" class="text-decoration-none link-dark text-center">
						<div class="card">
							<img src="image/chuyen-tong-tra-thuy-tinh.jpg"
								class="card-img-top" alt="...">
							<div class="card-body">
								<h5 class="card-title text-wrap fs-6">Dụng cụ trà khác</h5>
							</div>
						</div>
					</a>
				</div>
			</div>
		</div>
		<%@include file="includes/footer.jsp"%>
		<%@include file="includes/scripts.jsp"%>
</body>
</html>