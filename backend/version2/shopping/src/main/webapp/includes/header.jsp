<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<div class="container-fluid header">
	<div class="container">
		<header
			class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 border-bottom pe-2 ps-2">
			<a href="/home"
				class="d-flex align-items-center col-lg-1 col-sm-0 col-xs-0 mb-2 mb-md-0 text-white text-decoration-none">
				<i class="fa-solid fa-leaf"></i>&nbsp; Trà Vấp
			</a>

			<ul
				class="nav col-lg-6 col-sm-12 col-xs-12 col-md-auto mb-2 justify-content-center mb-md-0 m-auto">
				<li><a href="/home" class="nav-link px-2 link-light">Trang
						chủ</a></li>
				<li><a href="list?group=tea" class="nav-link px-2 link-light">Sản phẩm
						trà</a></li>
				<li><a href="list?group=utensil" class="nav-link px-2 link-light">Dụng cụ
						trà</a></li>
			</ul>

			<form class=" col-lg-2 col-sm-6 col-xs-6 form-inline md-form form-sm d-flex " >
				<input class="form-control form-control-sm ml-3 w-75 me-2"
					type="search" placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success" type="submit">
					<i class="fas fa-search"></i>
				</button>
			</form>

			<div class="col-lg-3 col-sm-6 col-xs-6 text-end">
				<a	href="/cart"
					class="nav-link waves-effect text-white position-relative d-inline pe-0 me-5"
					title="Giỏ hàng"> 
						
					<c:if test="${cart !=null && cart.size() > 0}">
						<span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
						 ${cart.size() }
						</span>
					</c:if>
						 
					 <i class="fas fa-shopping-cart"
					style="font-size: 20px; vertical-align: middle;"></i>
				</a>
				
				<c:choose>
				  <c:when test="${not empty auth}">
				  	<button  onclick="location.href='/key'" type="button" class="btn btn-outline-light me-2" title="Quản lý khóa"> <i class="fa-solid fa-key"></i> </button>
				    <button  onclick="location.href='/account'" type="button" class="btn btn-outline-light me-2" title="Tài khoản"> <i class="fa-regular fa-user"></i> </button>
				    <button onclick="location.href='logout'" type="button" class="btn btn-outline-light me-2">Đăng xuất</button>
				  </c:when>
				  <c:otherwise>
				    <button type="button" class="btn btn-outline-light me-2" onclick="location.href='/user-login'">Đăng nhập</button>
				  </c:otherwise>
				</c:choose>				
                <!-- <button type="button" class="btn btn-secondary">Đăng kí</button> -->
			</div>
		</header>
	</div>
</div>
