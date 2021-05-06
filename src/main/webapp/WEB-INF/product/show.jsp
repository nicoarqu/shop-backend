<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Usuario</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
</head>
<body>
	<div class="container col-md-6 mt-3">
		<nav
			style="-bs-breadcrumb-divider: url(&amp; amp; amp; #34; data: image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='8' height='8'%3E%3Cpath d='M2.5 0L1 1.5 3.5 4 1 6.5 2.5 8l4-4-4-4z' fill='currentColor'/%3E%3C/svg%3E&amp;amp;"
			aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/products">Productos</a></li>
				<li class="breadcrumb-item active" aria-current="page"><c:out
						value="${name}"></c:out></li>
			</ol>
		</nav>
		<div class="card text-center">
			<div class="card-body">
				<h5 class="card-title">
					<c:out value="${name}"></c:out>
				</h5>
				<p class="card-text">
					Código:
					<c:out value="${code}"></c:out>
				</p>
				<p class="card-text">
					<c:out value="${description}"></c:out>
				</p>
				<p>
					<c:forEach items="${productCategories}" var="categ">
						<span class="badge rounded-pill bg-primary">${categ.name}</span>
					</c:forEach>
				</p>
			</div>
			<div class="card-footer text-muted">
				Precio: $
				<c:out value="${price}"></c:out>
			</div>
		</div>
	</div>
</body>
</html>