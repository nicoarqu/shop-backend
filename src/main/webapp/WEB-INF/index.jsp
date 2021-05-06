<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">

<title>Ejercicio 1</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript" src="js/everis.js"></script>
</head>
<body>
	<div class="container mt-3">
		<h2 class="h2">Tienda virtual</h2>
		<div class="container mt-3">
		<a href="/users/signin" class="btn btn-secondary">Iniciar sesión</a>
		<c:if test="${sessionScope.userLogged == 0}">
			<a href="/users/new" class="btn btn-primary">Registrarme</a>
		</c:if>
		</div>
		
	</div>

</body>
</html>