<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%@include file="includes/head.jsp" %>
	<title>Danh sách sản phẩm</title>
    <style>
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
    </style>
</head>
<body>		
	<%@include file="includes/header.jsp" %>
	<div class="container-fluid bg-light">
        <div class="container pt-2 pb-5">
            <nav aria-label="breadcrumb" class="row">
                <ol class="breadcrumb col-lg-7">
                  <li class="breadcrumb-item"><a href="/home" class="link-secondary">Trang chủ</a></li>
                  <c:choose>
                  	<c:when test="${group !=null && group == 'tea'}"> 
                  		<li class="breadcrumb-item active" aria-current="page">Sản phẩm trà</li>
                  	</c:when>
                  	<c:otherwise>
                  		<li class="breadcrumb-item active" aria-current="page">Dụng cụ trà</li>
                  	</c:otherwise>
                  </c:choose>
                  
                </ol>
                <div class="col-lg-2">Hiển thị tất cả ${products.size()} kết quả</div>
                <div class="col-lg-3">
                  <select value="${order }" class="form-select w-100" id="order" >
                    <option id="order-0" value="0" >Thứ tự:</option>
                    <option id="order-1" value="1">Giá thấp - cao</option>
                    <option id="order-2" value="2">Giá cao - thấp</option>
                    <option id="order-3" value="3">Bán chạy</option>
                  </select>
                </div>
              </nav>

              <div class="row mt-5">
                <div class="col-lg-9"> 
                  <c:choose>
                  	<c:when test="${group !=null && group == 'tea'}"> 
                  		<h4 class="fs-4 fw-bold text-capitalize green-tea text-center" >Sản phẩm trà</h4>
                  	</c:when>
                  	<c:otherwise>
                  		<h4 class="fs-4 fw-bold text-capitalize green-tea text-center" >Dụng cụ trà</h4>
                  	</c:otherwise>
                  </c:choose>
                  <div class="row mt-3 mb-3 ">
                  
	                  <c:choose>
					  <c:when test="${products.size() > 0}">
					  	<c:forEach var = "product" items = "${products}">
							<div class="col-lg-4 col-sm-6 item-cart mb-4">
		                        <a href="/product?id=${product.id }" class="text-decoration-none link-dark text-center">
		                            <div class="card">
		                                <img src="image/${product.urlImg}" class="card-img-top" alt="...">
		                                <div class="card-body">
		                                  <h5 class="card-title text-wrap fs-6">${product.name} 
		                                  <c:if test = "${product.priceKg !=null && product.priceKg != '0đ/ 1kg'}">• ${product.priceKg}</c:if> </h5>
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
                  
                   
                  </div>
                </div>
                <div class="col-lg-3">
                  <h5 >Danh mục sản phẩm </h5>
                  <div><span class="border-top border-4 ps-5 "></span></div>
                  <ul class="list-group">
                  	<c:if test="${categories.size() > 0}">
                  		<c:forEach var = "category" items = "${categories}">
                  		<li class="list-group-item"><a class="dropdown-item" href="list?group=${group}&category=${category} ">${category}</a></li>
                  		</c:forEach>
                  	</c:if>
                  </ul>
                  
                </div>
              </div>
        </div>
        <input type="hidden" value="${uri}" id ="uri">
        
    </div>
	<%@include file="includes/footer.jsp" %>
	<%@include file="includes/scripts.jsp" %>
	<script type="text/javascript">
	$(document).ready(function(){
		$('#order').change(function() {
			let text = document.URL;

			if(text.search("&order=")>0){
				let index = text.indexOf("&order=");
				let result = text.slice(0,index);
				 window.location.href = result+"&order="+$(this).val();
			}else{
			    window.location.href = document.URL+"&order="+$(this).val();
			}
		});
		
	});
	
	</script>
</body>
</html>