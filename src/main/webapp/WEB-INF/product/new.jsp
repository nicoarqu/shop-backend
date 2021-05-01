<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	<div class="container col-md-6">
		<div class="row">
			<h3>Registro de producto</h3>
		</div>
		<div class="row">
			<form class="form mt-3" action="/products/create" method="POST">
				<div class="mb-3">
					<label for="name" class="form-label">Nombre</label> <input
						type="text" class="form-control" id="name" name="name"
						value="${name}">
				</div>
				<div class="mb-3">
					<label for="email" class="form-label">Codigo</label> <input
						type="text" class="form-control" id="email" name="code"
						value="${code}">
				</div>
				<div class="mb-3">
					<label for="password" class="form-label">Precio</label> <input
						type="number" class="form-control" id="pass" name="price"
						value="${price}">
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary btn-block">Enviar</button>
				</div>

			</form>
		</div>
	</div>
</body>
</html>