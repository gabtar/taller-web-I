<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><%@ taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<html lang="en">
<script>
function Confirmar(){
	var retVal = confirm("¿Desea alquilar este locker?");
    if( retVal == true ){
        
        return true;
    }else{
        
        return false;
    }
}
</script>

<head>
<!-- esta linea llama al jsp de la carpeta vistaGEnerales-->
<%@ include file="vistaGenerales/head.jsp"%></head>
<body>
	<!-- Sidebar/menu   de esta forma se llaman los jsp por partes-->
	<%@ include file="vistaGenerales/navLogeado.jsp"%><!-- Top menu on small screens -->
	<header
		class="w3-container w3-top w3-hide-large w3-red w3-xlarge w3-padding">
		<a href="javascript:void(0)" class="w3-button w3-red w3-margin-right"
			onclick="w3_open()">â˜°</a> <span>Company Name</span>
	</header>
	<div class="w3-main " style="margin-left: 340px; margin-right: 40px">
		<div class="w3-container">
			<h2 class="w3-center">
				Lockers Disponibles
			</h2>
			<div class="w3-row">
				<div class="w3-col m8 l8">
				   <p>Sucursal: ${sucursal.nombre}</p>
				</div>
				<div class="w3-col m4 l4  w3-right-align">
				<form:form action="${homeUrl}alquileres/${sucursal.id}" method="GET" class="w3-margin-top">
				<div class="w3-row-padding">
				  <div class="w3-half">
				    <select class="w3-select" name="tamanio">
					  <option value="" disabled selected>Tamaño</option>
					  <option value="60x50x50">60x50x50</option>
					  <option value="90x80x80">90x80x80</option>
					  <option value="120x100x100">120x100x100</option>
					</select>
				  </div>
				  <div class="w3-half">
				    <button class="w3-button w3-white w3-border" type="submit">Filtrar</button>
				  </div>
				</div>
				</form:form>
				</div>		
			</div>
		
			<c:choose>
				<c:when test="${not empty error}">
					<div class="w3-panel w3-red w3-padding-16">${error}</div>
				</c:when>
				<c:otherwise>
				<table class="w3-table-all">
					<thead>
						<tr class="w3-red w3-center">
							<th class="w3-center">Número Locker</th>
							<th class="w3-center">Tamaño</th>
							<th class="w3-center">Alquilar</th>
						</tr>
					</thead>

					<c:forEach var="locker" items="${alquileres}">
						<tr>
							<td class="w3-center"><c:out value="${locker.id}" /></td>
							<td class="w3-center"><c:out value="${locker.tamano}" /></td>
							<td class="w3-center"><form:form action="${homeUrl}modificar-locker/${locker.id}"
									method="POST">
									<button class="w3-button w3-indigo" type="submit" onclick="Confirmar();">Elegir</button>
								</form:form></td>
						</tr>
					</c:forEach>
				</table>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="w3-container w3-center w3-margin-top">
			<a href="${homeUrl}sucursales" class="w3-button w3-indigo">Volver</a>
		</div>
		<!-- Overlay effect when opening sidebar on small screens -->
		<div class="w3-overlay w3-hide-large" onclick="w3_close()"
			style="cursor: pointer" title="close side menu" id="myOverlay">

		</div>
	</div>