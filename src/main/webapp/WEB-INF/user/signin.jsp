<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Iniciar sesión</title>
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
			<h3>Iniciar sesión</h3>
		</div>
		<div class="row">
			<form class="form mt-3" action="/users/login" method="POST">
				<div class="mb-3">
					<label for="email" class="form-label">Correo</label> <input
						type="email" class="form-control" id="email" name="email"
						value="${email}">
				</div>
				<div class="mb-3">
					<label for="password" class="form-label">Contraseña</label> <input
						type="password" class="form-control" id="pass" name="password"
						value="${password}">
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary btn-block">Enviar</button>
				</div>

			</form>
			<c:out value="${errorMessage}"></c:out>
		</div>
	</div>
</body>
</html>