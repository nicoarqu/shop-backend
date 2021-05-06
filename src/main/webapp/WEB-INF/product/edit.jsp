<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
</head>
<body>
	<div class="container col-md-6 mt-3">
		<div class="row">
			<h3>Editar producto</h3>
		</div>
		<div class="row">
			<form:form class="form mt-3" action="/products/update/${id}"
				method="POST" modelAttribute="product">
				<form:hidden path="id" />
				<div class="mb-3">
					<form:label path="name" class="form-label">Nombre</form:label>
					<form:input path="name" type="text" class="form-control"
						value="${name}" />
				</div>
				<div class="mb-3">
					<form:label path="email" class="form-label">Codigo</form:label>
					<form:input path="code" type="text" class="form-control"
						value="${code}" />
				</div>
				<div class="mb-3">
					<form:label path="description" class="form-label">Descripcion</form:label>
					<form:input path="description" class="form-control"
						value="${description}" />
				</div>
				<div class="mb-3">
					<form:label path="password" class="form-label">Precio ($)</form:label>
					<form:input path="password" type="number" class="form-control"
						value="${price}" />
				</div>
				<div class="mb-3">
					<form:label path="categories" class="form-label">Categorías</form:label>
					<p>
						<c:forEach items="${productCategories}" var="categ">
							<span class="badge rounded-pill bg-primary">${categ.name}</span>
						</c:forEach>
					</p>
					<form:select path="categories" class="form-select" multiple>
						<option value="0">Seleccione categoria...</option>
						<c:forEach var="categ" items="${categories}">
							<option value="<c:out value="${categ.id}"></c:out>"><c:out
									value="${categ.name}"></c:out>
							</option>
						</c:forEach>
					</form:select>
				</div>


				<div class="form-group">
					<button type="submit" class="btn btn-primary btn-block">Enviar</button>
				</div>

			</form:form>
		</div>
	</div>
</body>
</html>