<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listado de sucursales</title>
</head>
<body>

	Resultados para: ${busqueda}
	<br>

	<c:if test="${not empty error}">
		<h4>
			<span>${error}</span>
		</h4>
		<br>
	</c:if>
	
	<br>

	<table>
		<tr>
			<th>ID</th>
			<th>Nombre</th>
			<th>Localidad</th>
		</tr>
		<c:forEach var="sucursal" items="${sucursales}">
			<tr>
				<td><c:out value="${sucursal.id}" />
				</td>
				<td><c:out value="${sucursal.nombre}" /></td>
				<td><c:out value="${sucursal.localidad}" /></td>
			</tr>
		</c:forEach>

	</table>



</body>
</html>