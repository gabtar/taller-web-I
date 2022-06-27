<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<!-- esta linea llama al jsp de la carpeta vistaGEnerales-->
<%@ include file="vistaGenerales/head.jsp"%>
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
					autocomplete="off" />
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
					</h2>
					<table class="w3-table-all">
						<thead>
							<tr class="w3-red w3-center">

								<th class="w3-center">Nro Sucursal</th>
								<th class="w3-center">Localidad</th>
								<th class="w3-center">Nombre de la Sucursal</th>
								<c:if test="${not empty sessionScope.userId}">
									<th class="w3-center">Lockers Disponibles</th>
								</c:if>
							</tr>
						</thead>
						<c:forEach var="sucursal" items="${sucursales}">
							<tr>
								<td class="w3-center"><c:out value="${sucursal.id}" /></td>
								<td class="w3-center"><c:out
										value="${sucursal.localidad.nombre}" /></td>
								<td class="w3-center"><c:out value="${sucursal.nombre}" /></td>
								<c:if test="${not empty sessionScope.userId}">
									<td class="w3-center">
										<a class="w3-button w3-black w3-small" href="alquileres/${sucursal.id}">Ver</a>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:otherwise>
		</c:choose>
	</div>


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