<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
		<title>Produkty</title>
	</head>
	<body>
		<section>
			<div class="jumbotron">
				<div class="container">
					<h1><spring:message code="addProduct.form.h1.products"/></h1>
					<p><spring:message code="addProduct.form.p.addProducts"/></p>
				</div>
				<a href="<c:url value="/j_spring_security_logout" />" class="btn btn-danger btn-mini pull-right">
					<spring:message code="addProduct.form.button.logout"/>
				</a>
				<div class="pull-right" style="padding-right:50px">
					<a href="?language=pl" >Polski</a>|<a href="?language=en">English</a>|
					<a href="?language=de">Deutsch</a>
				</div>
				
			</div>
		</section>
		<section class="container">
			<form:form modelAttribute="newProduct" class="form-horizontal"
			enctype="multipart/form-data">
				<fieldset>
					<legend>
						<spring:message code="addProduct.form.fieldset.legend"/>
					</legend>
					<div class="form-group">
						<label class="control-label col-lg-2 col-lg-2" for="productId">
							<spring:message code="addProduct.form.productId.label"/>
						</label>
						<div class="col-lg-10">
							<form:input id="productId" path="productId" type="text" class="form:input-large"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-lg-2" for="name">
							<spring:message code="addProduct.form.name.label"/>
						</label>
						<div class="col-lg-10">
							<form:input id="name" path="name" type="text" class="form:input-large"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-lg-2" for="unitPrice">
						<spring:message code="addProduct.form.unitPrice.label"/>
						</label>
						<div class="col-lg-10">
							<div class="form:input-prepend">
								<form:input id="unitPrice" path="unitPrice" type="text" class="form:input-large"/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-lg-2" for="category">
							<spring:message code="addProduct.form.category.label"/>
						</label>
						<div class="col-lg-10">
							<form:input id="category" path="category" type="text" class="form:input-large"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-lg-2" for="manufacturer">
							<spring:message code="addProduct.form.manufacturer.label"/>
						</label>
						<div class="col-lg-10">
							<form:input id="manufacturer" path="manufacturer" type="text" class="form:input-large"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-lg-2" for="unitsInStock">
							<spring:message code="addProduct.form.unitsInStock.label"/>	
						</label>
						<div class="col-lg-10">
							<form:input id="unitsInStock" path="unitsInStock" type="text" class="form:input-large"/>
						</div>
					</div>					
					<div class="form-group">
						<label class="control-label col-lg-2" for="description">
							<spring:message code="addProduct.form.description.label"/>
						</label>
						<div class="col-lg-10">
							<form:textarea id="description" path="description" rows="2"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-lg-2" for="condition">
							<spring:message code="addProduct.form.condition.label"/>
						</label>
						<div class="col-lg-10">
							<form:radiobutton path="condition" value="New"/><spring:message code="addProduct.form.condition.radiobutton.new"/>
							<form:radiobutton path="condition" value="Old"/><spring:message code="addProduct.form.condition.radiobutton.used"/>
							<form:radiobutton path="condition" value="Refurbished"/><spring:message code="addProduct.form.condition.radiobutton.refurbished"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-lg-2" for="productImage">
							<spring:message code="addProduct.form.productImage.label"/>
						</label>
						<div class="col-lg-10">
							<form:input id="productImage" path="productImage" type="file"
							class="form:input-large"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-lg-2" for="productPdf">
							<spring:message code="addProduct.form.productPdf.label"/>
						</label>
						<div class="col-lg-10">
							<form:input id="productPdf" path="productPdf" type="file"
							class="form:input-large"/>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-lg-offset-2 col-lg-10">
							<input type="submit" id="btnAdd" class="btn btn-primary" 
							value="<spring:message code="addProduct.button.add"/>"/>
						</div>
					</div>
				</fieldset>
			</form:form>
		</section>
	</body>
</html>