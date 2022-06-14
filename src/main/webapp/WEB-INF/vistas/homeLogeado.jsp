<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<!-- esta linea llama al jsp de la carpeta vistaGEnerales-->
	<%@ include file="vistaGenerales/head.jsp"%>
</head>
<body>

<!-- Sidebar/menu   de esta forma se llaman los jsp por partes-->
<%@ include file="vistaGenerales/navLogeado.jsp"%>

<!-- Top menu on small screens -->
<header
		class="w3-container w3-top w3-hide-large w3-red w3-xlarge w3-padding">
	<a href="javascript:void(0)" class="w3-button w3-red w3-margin-right"
	   onclick="w3_open()">☰</a> <span>Company Name</span>
</header>

<div class="w3-main " style="margin-left: 340px; margin-right: 40px">
	<div class="w3-container">
		<h2 class="w3-center">
			<p>Mis Alquileres</p>
		</h2>
	</div>
	<div class="container">
		<table class="w3-table-all">
			<c:forEach var="locker" items="${listaAlquileres}">
				<thead>
				<tr>
					<td>Sucursal: <c:out value="${locker.idSucursal}" />
					</td>
					<td>Tipo de locker: <c:out value="${locker.id}" /></td>
					<td>Tipo de locker: <c:out value="${locker.tamano}" /></td>
					<td>$0.0</td>
					<td><a herf="#" class="w3-button w3-light-blue">Codigo
						Apertura</a></td>
					<td><a herf="#" class="w3-button w3-light-blue">CerrarLocker</a></td>
				</tr>
				<tr>
					<td colspan="6"></td>
				</tr>
				</thead>
				<tbody>

				<tr>
					<td colspan="6"><label>Recordarorio</label>
						<form:form
								action="modificar-texto/${locker.id}" method="POST"
								modelAttribute="modificarTextoLocker">
							<form:input path="textoModificado" id="textoModificado"
										type="text" class="w3-input"
										placeholder="${locker.textoDelUsuario}" />
							<button class="btn btn-outline-secondary" type="submit">Modificar</button>
						</form:form></td>
				</tr>
				<tr>
					<td colspan="6"></td>
				</tr>
				</tbody>
			</c:forEach>
		</table>

	</div>


	
	<div class="w3-container">
</div>
<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

</div>