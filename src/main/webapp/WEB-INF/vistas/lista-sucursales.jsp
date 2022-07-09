<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<!-- esta linea llama al jsp de la carpeta vistaGEnerales-->
<%@ include file="vistaGenerales/head.jsp"%>
<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.8.0/dist/leaflet.css" />
<script src="https://unpkg.com/leaflet@1.8.0/dist/leaflet.js"></script>
<style>
html, body, #map {
    height: 100%;
}
</style>
</head>
<body>
	<!-- Sidebar/menu   de esta forma se llaman los jsp por partes-->
	<c:choose>
		<c:when test="${not empty sessionScope.userId}">
			<%@ include file="vistaGenerales/navLogeado.jsp"%>
		</c:when>
		<c:otherwise>
			<%@ include file="vistaGenerales/navComun.jsp"%>
		</c:otherwise>
	</c:choose>

	<!-- Top menu on small screens -->
	<header
		class="w3-container w3-top w3-hide-large w3-red w3-xlarge w3-padding">
		<a href="javascript:void(0)" class="w3-button w3-red w3-margin-right"
			onclick="w3_open()">☰</a> <span>RentLock</span>
	</header>

	<div class="w3-main w3-margin-top"
		style="margin-left: 340px; margin-right: 40px">

		<c:if test="${not empty sessionScope.userId}">
			<h2 class="w3-center">Buscar Lockers disponibles por Sucursal</h2>
		</c:if>

		<form:form action="sucursales" method="GET" class="w3-margin-top">
			<p>
				<label>Ingrese una localidad: </label>
			</p>
			<p>
				<input class="w3-input" list="localidad" name="localidad"
					autocomplete="off" value="${param.localidad}"
					placeholder="Ingrese localidad o dejar vacío para todos los registros" />
				<datalist id="localidad">
					<c:forEach var="localidad" items="${localidades}">
						<option value="${localidad.nombre}">
					</c:forEach>
				</datalist>
			</p>
			<button class="w3-btn w3-blue-grey" type="submit">Buscar</button>
		</form:form>
		<c:choose>
			<c:when test="${not empty error}">
				<h4>
					<span>${error}</span>
				</h4>
				<br>
			</c:when>
			<c:otherwise>
				<div class="w3-container">
					<h2 class="w3-center">
						<c:choose>
							<c:when test="${empty busqueda}">
								<p>Mostrando todas las sucursales</p>
							</c:when>
							<c:otherwise>
								<p>Sucursales para ${busqueda}</p>
							</c:otherwise>
						</c:choose>
						<div id="map"></div>
						<script>
							// Creo un array de objetos en js con los valores de latitud, longitud y datos de las sucursales
							var listaSucursales = []; 
							<c:forEach var="sucursal" items="${sucursales}">
								listaSucursales.push({
									id: '<c:out value="${sucursal.id}"/>',
									nombre : '<c:out value="${sucursal.nombre}"/>',
							 		latitud : '<c:out value="${sucursal.latitud}"/>',
							 		longitud : '<c:out value="${sucursal.longitud}"/>'
								});
    						</c:forEach>
						
							// Calculo del centro del mapa
							const centro = listaSucursales.reduce(
  								(sum, suc) => [sum[0] + parseFloat(suc.latitud), sum[1] + parseFloat(suc.longitud)],
  								[0,0]
							)
              				.map((num) => num/listaSucursales.length);
							
							var map = L.map('map').setView([ centro[0], centro[1] ],
									14);

							var tiles = L
									.tileLayer(
											'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
											{
												maxZoom : 19,
												attribution : '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
											}).addTo(map);

							// Agrego todas las sucursales encontradas al mapa							
							listaSucursales.forEach((suc) => {
								// No toma bien las template strings de js
								let popupInfo = '<b>#' + suc.id + ': ' + suc.nombre + '</b>';
								L.marker([ suc.latitud, suc.longitud ])
								.addTo(map)
								.bindPopup(popupInfo);
							});
					
						</script>
					</h2>
					<table class="w3-table-all">
						<thead>
							<tr class="w3-red w3-center">

								<th class="w3-center">Nro Sucursal</th>
								<th class="w3-center">Localidad</th>
								<th class="w3-center">Nombre de la Sucursal</th>
							</tr>
						</thead>
						<c:forEach var="sucursal" items="${sucursales}">
							<tr>
								<td class="w3-center"><c:out value="${sucursal.id}" /></td>
								<td class="w3-center"><c:out
										value="${sucursal.localidad.nombre}" /></td>
								<td class="w3-center"><c:out value="${sucursal.nombre}" /></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<br>


	<!-- Overlay effect when opening sidebar on small screens -->
	<div class="w3-overlay w3-hide-large" onclick="w3_close()"
		style="cursor: pointer" title="close side menu" id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->

	<!-- Header 
  <div class="w3-container" style="margin-top:80px" id="showcase">
    <h1 class="w3-jumbo"><b>Interior Design</b></h1>
    <h1 class="w3-xxxlarge w3-text-red"><b>Showcase.</b></h1>
    <hr style="width:50px;border:5px solid red" class="w3-round">
  </div>
  -->