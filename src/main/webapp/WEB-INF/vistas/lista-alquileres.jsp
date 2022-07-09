<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><%@ taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<html lang="en">
<script>
function Confirmar(){
	var retVal = confirm("�Desea alquilar este locker?");
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
			onclick="w3_open()">☰</a> <span>Company Name</span>
	</header>
	<div class="w3-main " style="margin-left: 340px; margin-right: 40px">
		<div class="w3-container">
			<h2 class="w3-center">
				Lockers Disponibles
			</h2>
			<div class="w3-row">
				<form:form action="${homeUrl}alquileres/buscar?localidad=${localidad}" method="GET" class="w3-margin-top">
				<p>
					<label>Ingrese una localidad: </label>
				</p>
				<p>
					<input class="w3-input" list="localidad" name="localidad"
						autocomplete="off" value="${param.localidad}" placeholder="Ingrese localidad o dejar vac�o para todos los registros" />
					<datalist id="localidad">
						<c:forEach var="localidad" items="${localidades}">
							<option value="${localidad.nombre}">
						</c:forEach>
					</datalist>
				</p>
				<div class="w3-row">
				  <div class="w3-half">
				    <label>Tama�o</label>
				    <!-- TODO Tama�os fijos harcodeados, deber�an venir de la bd -->
				    <select class="w3-select" name="tamanio" style="width: fit-content;">
					  <option value="" <c:if test="${empty param.tamanio}">selected</c:if> >Todos</option>
					  <option value="60x50x50" <c:if test='${param.tamanio eq "60x50x50" }'>selected</c:if>>60x50x50</option>
					  <option value="90x80x80" <c:if test='${param.tamanio eq "90x80x80" }'>selected</c:if>>90x80x80</option>
					  <option value="120x100x100" <c:if test='${param.tamanio eq "120x100x100" }'>selected</c:if>>120x100x100</option>
					</select>
				  
				  </div>
				  <div class="w3-half w3-right-align">
				     <button class="w3-button w3-indigo w3-border w3-round" type="submit">Buscar</button>
				  </div>
				</div>
				</form:form>
			  <br>
					
			</div>
		
			<c:choose>
				<c:when test="${not empty error}">
					<div class="w3-panel w3-red w3-padding-16">${error}</div>
				</c:when>
				<c:otherwise>
				<table class="w3-table-all">
					<thead>
						<tr class="w3-red w3-center">
							<th class="w3-center">N�mero Locker</th>
							<th class="w3-center">Sucursal</th>
							<th class="w3-center">Localidad</th>
							<th class="w3-center">Tama�o</th>
							<th class="w3-center">Alquilar</th>
						</tr>
					</thead>

					<c:forEach var="locker" items="${alquileres}">
						<tr>
							<td class="w3-center"><c:out value="${locker.id}" /></td>
							<td class="w3-center"><c:out value="${locker.sucursal.nombre}" /></td>
							<td class="w3-center"><c:out value="${locker.sucursal.localidad.nombre}" /></td>
							<td class="w3-center"><c:out value="${locker.tamanio.tamanio}" /></td>
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
		<!-- Overlay effect when opening sidebar on small screens -->
		<div class="w3-overlay w3-hide-large" onclick="w3_close()"
			style="cursor: pointer" title="close side menu" id="myOverlay">

		</div>
	</div>