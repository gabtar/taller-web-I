<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<!-- esta linea llama al jsp de la carpeta vistaGEnerales-->
<%@ include file="vistaGenerales/head.jsp"%>
</head>
<script>
function Confirmar(){
	var retVal = confirm("¿Desea Eliminar este locker de su alquiler?");
    if( retVal == true ){
        
        return true;
    }else{
        
        return false;
    }
}

document.addEventListener('DOMContentLoaded', () => {
	const botones = document.getElementsByClassName("loading");
	for(button of botones) {
		button.addEventListener('click', (e) => {
			console.log(button.innerHTML);
			e.target.innerHTML = "<i class='fa fa-spinner fa-spin'></i> Procesando";
		});
	}
	
});

</script>
<body>

	<!-- Sidebar/menu   de esta forma se llaman los jsp por partes-->
	<%@ include file="vistaGenerales/navLogeado.jsp"%>

	<!-- Top menu on small screens -->
	<header
		class="w3-container w3-top w3-hide-large w3-red w3-xlarge w3-padding">
		<a href="javascript:void(0)" class="w3-button w3-red w3-margin-right"
			onclick="w3_open()">â˜°</a> <span>Company Name</span>
	</header>

	<div class="w3-main " style="margin-left: 340px; margin-right: 40px">
		<div class="w3-container">
			<h2 class="w3-center">
				<p>Mis Lockers</p>
			</h2>
			<c:if test="${not empty error}">

				<div class="w3-panel w3-red w3-padding-16">${error}</div>
			</c:if>
			<c:if test="${not empty mensaje}">

				<div class="w3-panel w3-green w3-padding-16">${mensaje}</div>
			</c:if>
		</div>
		<div class="container">
			<c:if test="${empty listaAlquileres}">
				<div class="w3-container w3-center">
					<h1 class="w3-text-red w3-margin-bottom">No posee alquileres
						activos.</h1>
					<a href="${homeUrl}alquileres/buscar" style="text-decoration: none;">
						<button class="w3-button w3-indigo w3-round">Buscar Lockers</button>
					</a>
				</div>
			</c:if>

			<c:forEach var="locker" items="${listaAlquileres}">
				<table class="w3-table-all w3-margin-bottom">
					<thead>
						<tr>
							<td class="w3-center">Sucursal: <c:out
									value="${locker.sucursal.nombre}" />
							</td class="w3-center">
							<td class="w3-center">Número locker: <c:out
									value="${locker.id}" /></td>
							<td class="w3-center">Tipo de locker: <c:out
									value="${locker.tamanio.tamanio}" /></td>
							<td class="w3-center">Precio: $${locker.tamanio.precio}(por día)</td>
							<td class="w3-center"><a href="codigo/generar/${locker.id}"
								class="w3-button w3-light-blue loading">Codigo Producto</a></td>
							<td class="w3-center"><form:form
									action="cancelar-locker/${locker.id}" method="POST">
									<button class="w3-button w3-indigo" type="submit" onclick="Confirmar();">Eliminar</button>
									
								</form:form></td>
						</tr>
						<tr>
							<td colspan="6"></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="6"><label>Recordarorio</label> <form:form
									action="modificar-texto/${locker.id}" method="POST"
									modelAttribute="modificarTextoLocker">
									<form:input path="textoModificado" id="textoModificado"
										type="text" class="w3-input"
										placeholder="${locker.textoDelUsuario}" />
									<button
										class="w3-button w3-black w3-round w3-small w3-border w3-margin-top"
										type="submit">Modificar</button>
								</form:form></td>
						</tr>
						<tr>
							<td colspan="6"></td>
						</tr>
					</tbody>
				</table>

			</c:forEach>

		</div>

		<!-- Overlay effect when opening sidebar on small screens -->
		<div class="w3-overlay w3-hide-large" onclick="w3_close()"
			style="cursor: pointer" title="close side menu" id="myOverlay"></div>

	</div>