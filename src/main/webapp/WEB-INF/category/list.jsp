<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Productos</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
</head>
<body>
	<div class="container col-md-10 mt-3">
		<h4>Categorías</h4>
		<div class="row">
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Nombre</th>
						<th scope="col">Opciones</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${allCategories}" var="entry">
						<tr>
							<th scope="row">${entry.id}</th>
							<td>${entry.name}</td>
							<td class="button-group" role="group"><a
								href="/admin/categories/delete/${entry.id}"
								class="btn btn-danger">Eliminar</a></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>

		<div class="row">
			<div class="col-md-4">
				<a href="/admin/categories/new" class="btn btn-success">Agregar categoría</a>
			</div>
		</div>
	</div>
</body>
</html>