<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js"></script>
		<script src="/webstore/resource/js/products.js"></script>
		<title>Produkty</title>
	</head>
	<body>
		<section class="container" ng-app="productsApp">
			<div class="row" ng-controller="productsCtrl" ng-init="showProducts()">
				<c:forEach items="${databaseProducts}" var="product">
					<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
						<div class="thumbnail">
							<div class="caption">
								<h3>${product.name}</h3>
								<p>${product.description}</p>
								<p>${product.unitPrice} PLN</p>
								<p>Liczba sztuk w magazynie: ${product.unitsInStock}</p>
								<p>
								<a href="<spring:url value="/products/product?id=${product.productId}"/>
								 " class="btn btn-primary">
								 <span class="glyphicon-info-sign glyphicon"></span>Szczegóły
								</a>
							</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</section>
	</body>
</html>