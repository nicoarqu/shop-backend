<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
			style="-bs-breadcrumb-divider: url(&amp; amp; #34; data: image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='8' height='8'%3E%3Cpath d='M2.5 0L1 1.5 3.5 4 1 6.5 2.5 8l4-4-4-4z' fill='currentColor'/%3E%3C/svg%3E&amp;amp;#34;);"
			aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/sales">Ventas</a></li>
				<li class="breadcrumb-item active" aria-current="page"><c:out
						value="${buyer}"></c:out></li>
			</ol>
		</nav>
		<div class="card text-center">
			<div class="card-body">
				<h5 class="card-title">
					<c:out value="${buyer}"></c:out>
				</h5>
				<p class="card-text">
					Creada en:
					<fmt:formatDate type = "both" value = "${createdAt}" /></p>
				</p>
			</div>
			<div class="card-footer text-muted">
				Precio total: $ <c:out value="${total}"></c:out>
			</div>
		</div>
	</div>
</body>
</html>