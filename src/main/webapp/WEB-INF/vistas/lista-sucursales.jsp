<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<!-- esta linea llama al jsp de la carpeta vistaGEnerales-->
<%@ include file="vistaGenerales/head.jsp"%>
</head>
<body>
	<!-- Sidebar/menu   de esta forma se llaman los jsp por partes-->
	<%@ include file="vistaGenerales/navComun.jsp"%>

	<!-- Top menu on small screens -->
	<header
		class="w3-container w3-top w3-hide-large w3-red w3-xlarge w3-padding">
		<a href="javascript:void(0)" class="w3-button w3-red w3-margin-right"
			onclick="w3_open()">☰</a> <span>Company Name</span>
	</header>

	<div class="w3-main " style="margin-left: 340px; margin-right: 40px">

		<form:form action="sucursales" method="GET" class="w3-margin-top">
			<p>
				<label>Buscá sucursales</label> <input class="w3-input w3-border"
					name="localidad" type="text">
			</p>
			<button class="w3-btn w3-blue-grey" type="submit">Buscar</button>
		</form:form>
		<div class="w3-container">

			<c:choose>
				<c:when test="${not empty error}">
					<h4>
						<span>${error}</span>
					</h4>
					<br>
				</c:when>
				<c:otherwise>
					<h2 class="w3-center">
						<p>Sucursales para: ${busqueda}</p>
					</h2>
					<table class="w3-table-all">
						<thead>
							<tr class="w3-red w3-center">
								<th class="w3-center">Sucursal nro</th>
								<th class="w3-center">Nombre</th>
								<th class="w3-center">Localidad</th>
							</tr>
						</thead>
						<c:forEach var="sucursal" items="${sucursales}">
							<tr>
								<td class="w3-center"><c:out value="${sucursal.id}" /></td>
								<td class="w3-center"><c:out value="${sucursal.nombre}" /></td>
								<td class="w3-center"><c:out value="${sucursal.localidad}" /></td>
							</tr>
						</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>
		</div>
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