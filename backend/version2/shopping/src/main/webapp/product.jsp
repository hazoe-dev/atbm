<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

	<%@include file="includes/head.jsp" %>
	<link
      rel="stylesheet"
      href="static/swiper-bundle.min.css"
    />
	<title>Chi tiết sản phẩm</title>
    
    <style>
      .comment a{
          text-decoration: none;
          padding-left: 10px;
          padding-right: 10px;
          border-right: 1px solid gray;
      }
      .rating {
        color: rgb(199, 199, 199);
      }
      .rating .checked {
        color: orange;
      }
      .green-tea{
        color: #008000 !important;
      }
        .header{
            font-size: 14px;
            font-weight: 600;
            text-transform: uppercase;
            background-color: #004315;
        }
        .category-nav{
            border-top: #004315 2px solid;
        }
        .item-cart{
            transition: opacity 0.3s, transform 0.3s, background-color 0.3s, -webkit-transform 0.3s;
        }
        .item-cart .card{
            box-shadow: rgba(50, 50, 105, 0.15) 0px 2px 5px 0px, rgba(0, 0, 0, 0.05) 0px 1px 1px 0px;
        }
        .item-cart .card img{
            height: 250px;
        }
        .item-cart:hover{
            transform: scale(1) translateZ(0) translateY(0) !important;
            margin-top: -10px;
        }

      .swiper-slide {
        text-align: center;
        font-size: 18px;
        background: #fff;

        /* Center slide text vertically */
        display: -webkit-box;
        display: -ms-flexbox;
        display: -webkit-flex;
        display: flex;
        -webkit-box-pack: center;
        -ms-flex-pack: center;
        -webkit-justify-content: center;
        justify-content: center;
        -webkit-box-align: center;
        -ms-flex-align: center;
        -webkit-align-items: center;
        align-items: center;
      }

      .swiper-slide img {
        display: block;
        width: 100%;
        height: 100%;
        object-fit: cover;
      }

      .swiper {
        width: 100%;
        height: 300px;
        margin-left: auto;
        margin-right: auto;
      }

      .swiper-slide {
        background-size: cover;
        background-position: center;
      }

      .mySwiper2 {
        height: 80%;
        width: 100%;
      }

      .mySwiper {
        height: 20%;
        box-sizing: border-box;
        padding: 10px 0;
      }

      .mySwiper .swiper-slide {
        width: 25%;
        height: 100%;
        opacity: 0.4;
      }

      .mySwiper .swiper-slide-thumb-active {
        opacity: 1;
      }

      .swiper-slide img {
        display: block;
        width: 100%;
        height: 100%;
        object-fit: cover;
      }
    </style>
</head>
<body>
	<%@include file="includes/header.jsp" %>
	 <div class="container-fluid bg-light">
        <div class="container pt-2 pb-2">
            <nav aria-label="breadcrumb" >
                <ol class="breadcrumb ">
                  <li class="breadcrumb-item"><a href="/home" class="link-secondary">Trang chủ</a></li>
                  <li class="breadcrumb-item"><a href="/list?group=${product.group }" class="link-secondary">${product.group }</a></li>
                  <li class="breadcrumb-item active" aria-current="page">${product.name }</li>
                </ol>
              </nav>
        </div>
    </div>
    <div class="container-fluid bg-light pt-2 pb-5">
      <div class="container bg-white rounded row m-auto ">
        <div class="col-lg-5 p-3">
          <div
          style="--swiper-navigation-color: #fff; --swiper-pagination-color: #fff"
          class="swiper mySwiper2"
        >
          <div class="swiper-wrapper">
				<c:forEach var = "urlImg" items = "${product.urlImgs}">
					<div class="swiper-slide">
		              <img src="image/${urlImg }" />
		            </div>					    
				</c:forEach>
            
            <!-- <div class="swiper-slide">
              <img src="image/tra-moc-cau-dac-biet-loc-tan-cuong-3-2.jpg" />
            </div>
            <div class="swiper-slide">
              <img src="image/tra-moc-cau-dac-biet-loc-tan-cuong-3-3.jpg" />
            </div>
            <div class="swiper-slide">
              <img src="image/tra-moc-cau-dac-biet-loc-tan-cuong-3-1.jpg" />
            </div> -->
          </div>
          <div class="swiper-button-next"></div>
          <div class="swiper-button-prev"></div>
        </div>
        <div thumbsSlider="" class="swiper mySwiper">
          <div class="swiper-wrapper">
          		<c:forEach var = "urlImg" items = "${product.urlImgs}">
					<div class="swiper-slide">
		              <img src="image/${urlImg }" />
		            </div>					    
				</c:forEach>
            <!-- <div class="swiper-slide">
              <img src="image/tra-moc-cau-dac-biet-loc-tan-cuong-3-1.jpg" />
            </div>
            <div class="swiper-slide">
              <img src="image/tra-moc-cau-dac-biet-loc-tan-cuong-3-2.jpg" />
            </div>
            <div class="swiper-slide">
              <img src="image/tra-moc-cau-dac-biet-loc-tan-cuong-3-3.jpg" />
            </div>
            <div class="swiper-slide">
              <img src="image/tra-moc-cau-dac-biet-loc-tan-cuong-3-1.jpg" />
            </div> -->
          </div>
        </div>
        </div>
        <div class="col-lg-7 p-3 detail">
          <h2>${product.name} 
		      <c:if test = "${product.priceKg !=null && product.priceKg != '0đ/ 1kg'}">• ${product.priceKg}</c:if>
		  </h2>
          <div class="rating">
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star"></span>
            <span class="fa fa-star"></span>
            <span class="text-dark"> &nbsp; (1 đánh giá của khách)</span>
          </div>
          <hr>
          <div class="row">
            <div class="col-lg-7">
              	<c:if test = "${product.aboutPrice !=null && product.group == 'Sản phẩm trà'}">
		              <div class="mb-2" style="display: inline-block;width: 100%">
		               Giá bán: <strong class="text-danger"><h5 style="display: inline-block;">${product.aboutPrice}</h5></strong> 
		               <hr>
		              </div>
				</c:if>
               <div class="mb-2">
                <h5 class="fs-6">MỜI BẠN CHỌN QUY CÁCH ĐÓNG GÓI</h5>
                <div class="row align-middle mb-2">
                  <div class="col-4 fs-6 pt-2">ĐÓNG GÓI <hr></div>
                  <div class="col-8 ">
                    <select class="form-select" style="width:100%" id="order">
                    	<option selected disabled="disabled">Chọn cách đóng gói</option>
	                    <c:forEach var="type" items="${product.types}" varStatus="loop">
						    <option value="${type.id}"  >
						    	${type.name}
						    </option>
						</c:forEach>
                     <!--  <option selected>100g</option>
                      <option value="1">200g</option>
                      <option value="2">300g</option>
                      <option value="3">1kg</option> -->
                    </select>
                    <c:forEach var="type" items="${product.types}" varStatus="loop">
                    	<div>
	                    	<input type="hidden" id="type-price-${type.id}" value="${type.price}">
							<input type="hidden" id="type-quantity-${type.id}" value="${type.quantity}">
                    	</div>
                    	
                    </c:forEach>
                  </div>
                </div>
                <div>
                  <form class="d-flex justify-content-left" action="/add-to-cart" method="get">
                  	<div class ="row">
                  		<div class="col-lg-12 price mt-2 mb-4">
                  			 Giá cụ thể: <span id="specialPrice"></span>
                  			<input type="hidden" name="typeId" id="typeIdForm" value="-1" >
                  			<input type="hidden" name="productId" value="${product.id}">
                  		</div>
                  		<div class="col-lg-12" style="display: inline-block;height: 40px;">
		                    <input name="quantity" type="number" min="1" value="1" class="form-control me-3" style="width: 100px;display: inline-block; height: 40px; vertical-align: middle;">
		                    <button class="btn btn-success btn-md my-0 p waves-effect waves-light text-uppercase fw-bold" 
		                    		style="color: #f5f5f5;display: inline-block;height: 40px;" type="submit"
		                    		>
		                    	Thêm vào giỏ hàng
		                      <i class="fas fa-shopping-cart ml-1"></i>
		                    </button>
                    	</div>
                  	</div>
                  </form>
                </div>
                <c:choose>
	               <c:when test="${status == false}"> 
	                   <div class="alert alert-danger text-danger mt-3">
	                  		<strong>Thất bại!</strong> ${msgCart }
	                   </div>
	                </c:when>
	                <c:when test="${status == true}"> 
	                   <div class="alert alert-success text-success mt-3">
							<strong>Thành công!</strong> ${msgCart }
						</div>
	                </c:when>
                  	<c:otherwise>
						
					</c:otherwise>
                  </c:choose>
              </div>
            </div>
            <div class="col-lg-5 border rounded p-4">
              <div class="col-inner">
                <div class="is-border" style="border-radius:5px;border-width:1px 1px 1px 1px;">
                </div>
                <div id="text-2530259630" class="text">
                <p style="text-align: left;"><a href="https://loctancuong.vn/wp-content/uploads/2021/09/icon-checked.png"><img class="alignnonewp-image-10374" src="https://loctancuong.vn/wp-content/uploads/2021/09/icon-checked.png" alt="" width="20" height="20"></a>&nbsp;<span style="color: #000000; font-family: helvetica, arial, sans-serif;"><span style="font-size: 14px;">Trà không chất bảo quản, hương liệu, phẩm màu</span></span></p>
                <p style="text-align: left;"><span style="color: #000000; font-family: helvetica, arial, sans-serif;"><span style="font-size: 14px;"><a href="https://loctancuong.vn/wp-content/uploads/2021/09/icon-checked.png"><img class="alignnonewp-image-10374" src="https://loctancuong.vn/wp-content/uploads/2021/09/icon-checked.png" alt="" width="20" height="20"></a>&nbsp;Dụng cụ trà chính hãng</span></span></p>
                <p style="text-align: left;"><img class="alignnonewp-image-10375" src="https://loctancuong.vn/wp-content/uploads/2021/09/icon-money-back.png" alt="" width="24" height="24">&nbsp;<span style="font-family: helvetica, arial, sans-serif; color: #000000; font-size: 14px;">Đổi trả - Hoàn tiền 100% nếu bạn không hài lòng</span></p>
                <p style="text-align: left;"><img class="alignnonewp-image-10376" src="https://loctancuong.vn/wp-content/uploads/2021/09/icon-fast-delivery.png" alt="" width="23" height="23"> <span style="color: #000000; font-family: helvetica, arial, sans-serif; font-size: 14px;">Giao hàng nhanh 1 giờ nội thành Hà Nội &amp; TP.HCM</span></p>
                <style>
                #text-2530259630 {
                line-height: 1.25;
                }
                </style>
                </div>
                </div>
            </div>
          </div>
       
          <div class="bg-light border rounded p-2 m-2">
            <div class="col-inner">
              <span style="font-size: 90%;"><strong><span style="text-align: justify;"><span style="color: #000000; font-family: helvetica, arial, sans-serif;">THÔNG TIN SẢN PHẨM</span></span></strong></span>
              <div class="product-short-description">
              <p style="text-align: justify;"><span style="font-family: 'times new roman', times, serif; color: #000000; font-size: 95%;"><strong><img class="alignnone wp-image-10344" src="https://loctancuong.vn/wp-content/uploads/2021/09/icon-657.png" alt="" width="10" height="10"> Thương hiệu</strong>: Lộc Tân Cương</span><br>
              <span style="font-family: 'times new roman', times, serif; font-size: 95%; color: #000000;"><strong><img class="alignnone wp-image-10344" src="https://loctancuong.vn/wp-content/uploads/2021/09/icon-657.png" alt="" width="10" height="10"> Vùng nguyên liệu</strong>: Tân Cương – Thái Nguyên</span><br>
              <span style="font-family: 'times new roman', times, serif; font-size: 95%; color: #000000;"><strong><img class="alignnone wp-image-10344" src="https://loctancuong.vn/wp-content/uploads/2021/09/icon-657.png" alt="" width="10" height="10"> Hương vị</strong>: Thơm cốm tự nhiên, chát dịu, hậu ngọt sâu</span><br>
              <span style="font-family: 'times new roman', times, serif; font-size: 95%; color: #000000;"><strong><img class="alignnone wp-image-10344" src="https://loctancuong.vn/wp-content/uploads/2021/09/icon-657.png" alt="" width="10" height="10"> Cách hái</strong>: 1 đọt + 1 – 2 lá non</span><br>
              <span style="font-family: 'times new roman', times, serif; font-size: 95%; color: #000000;"><strong><img class="alignnone wp-image-10344" src="https://loctancuong.vn/wp-content/uploads/2021/09/icon-657.png" alt="" width="10" height="10"> Quy cách đóng gói</strong>: 100g, 250g, 250g tép 500g</span><br>
              <span style="font-family: 'times new roman', times, serif; font-size: 95%; color: #000000;"><strong><a href="https://loctancuong.vn/wp-content/uploads/2021/09/icon-657.png"><img class="alignnone wp-image-10344" src="https://loctancuong.vn/wp-content/uploads/2021/09/icon-657.png" alt="" width="10" height="10"></a> Hạn sử dụng</strong>: 24 tháng kể từ ngày sản xuất</span></p>
              </div>
              </div>
          </div>
        </div>
      </div>
    </div>
   
    <div class="container-fluit bg-light pt-4 pb-5">
      <div class="container bg-white rounded row m-auto p-3">
        <h4 class="fs-5 fw-bold text-capitalize green-tea text-center" >Sản phẩm tương tự</h4>
            <div class="row mt-3 mb-3 ">
            
             <c:if test="${list.size() > 0}">
				<c:forEach var = "item" items = "${list}">
					 <div class="col-lg-3 col-sm-6 item-cart mb-4">
		                  <a href="/product?id=${item.id }" class="text-decoration-none link-dark text-center">
		                      <div class="card">
		                          <img src="image/${item.urlImg}" class="card-img-top" alt="...">
		                          <div class="card-body">
		                            <h5 class="card-title text-wrap fs-6">${item.name} 
		                                  <c:if test = "${item.priceKg !=null && item.priceKg != '0đ/ 1kg'}">• ${item.priceKg}</c:if></h5>
		                          </div>
		                        </div>
		                  </a>
		              </div>
				</c:forEach>
			</c:if>
            
             
              <!-- <div class="col-lg-3 col-sm-6 item-cart ">
                  <a href="#" class="text-decoration-none link-dark text-center">
                      <div class="card">
                          <img src="image/tra-moc-cau-dac-biet-loc-tan-cuong-3-1.jpg" class="card-img-top" alt="...">
                          <div class="card-body">
                            <h5 class="card-title text-wrap fs-6">Trà Móc Câu Đặc Biệt • 600.000đ / 1kg</h5>
                          </div>
                        </div>
                  </a>
              </div> -->
            </div>
      </div>
    </div>

    <div class="container-fluit bg-light pt-4 pb-5">
      <div class="container bg-white rounded row m-auto pt-3">
        <h5>Nhận xét</h5>
        <div class="comment ">
          <a href="#">
            <i class="fa-solid fa-star"></i>
            <i class="fa-solid fa-star"></i>
            <i class="fa-solid fa-star"></i>
            <i class="fa-solid fa-star"></i>
            <i class="fa-solid fa-star"></i>
            Rất tốt
          </a>
          <a href="#">
            <i class="fa-solid fa-star"></i>
            <i class="fa-solid fa-star"></i>
            <i class="fa-solid fa-star"></i>
            <i class="fa-solid fa-star"></i>
            Tốt
          </a>
          <a href="#">
            <i class="fa-solid fa-star"></i>
            <i class="fa-solid fa-star"></i>
            <i class="fa-solid fa-star"></i>
            Trung bình
          </a>
          <a href="#">
            <i class="fa-solid fa-star"></i>
            <i class="fa-solid fa-star"></i>
            Không tệ
          </a>
          <a href="#">
            <i class="fa-solid fa-star"></i>
            Rất tệ
          </a>
          
          <form action="#" class="row mt-2 mb-2">
            <input type="hidden" name="rating" value="" >
            <div class="col-6">
              <input type="text" name="name" placeholder="Họ tên" required class="form-control mb-2">
            </div>
            <div class="col-6">
              <input type="email" name="email" placeholder="Email" required class="form-control mb-2">
            </div>
            <div class="col-12">
              <textarea name="comment" id="" cols="20" rows="3" placeholder="Viết nhận xét ..." required class="col-8 form-control mb-2"></textarea>
            </div>
            <div class="col-12 text-end">
              <button type="submit" class="btn btn-primary">Gửi</button>
            </div>
          </form>
        </div>
      </div>
    </div>
    <script src="static/swiper-bundle.min.js"></script>
	<%@include file="includes/footer.jsp" %>
	<%@include file="includes/scripts.jsp" %>


    <script>
      var swiper = new Swiper(".mySwiper", {
        spaceBetween: 10,
        slidesPerView: 4,
        freeMode: true,
        watchSlidesProgress: true,
      });
      var swiper2 = new Swiper(".mySwiper2", {
        spaceBetween: 10,
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev",
        },
        thumbs: {
          swiper: swiper,
        },
      });
    </script>
    
    <script>
    function format(value){
    	return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value);
    }
    $(document).ready(function(){
		$('#order').change(function() {
			let idTagPrice = '#type-price-'+$(this).val();
			let price = $(idTagPrice).val();
			
			let idTagQuantity = '#type-quantity-'+$(this).val();
			let quantity = $(idTagQuantity).val();
			
			let idTypeIdForm= '#typeIdForm';
			let typeId = $(this).val();
			$(idTypeIdForm).val(typeId);
			
			$('#specialPrice').html('<strong class ="text-danger" style="font-size:25px"> &nbsp;'+format(price)+'&nbsp;</strong> (Còn '+quantity +' sản phẩm)')
		});
		
	});
    </script>
</body>
</html>