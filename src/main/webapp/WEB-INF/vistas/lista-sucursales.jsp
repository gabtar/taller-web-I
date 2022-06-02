<!DOCTYPE html>
<html lang="en">
<head>
<title>Rent Lock</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Poppins">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
body, h1, h2, h3, h4, h5 {
	font-family: "Poppins", sans-serif
}

body {
	font-size: 16px;
}

.w3-half img {
	margin-bottom: -6px;
	margin-top: 16px;
	opacity: 0.8;
	cursor: pointer
}

.w3-half img:hover {
	opacity: 1
}
</style>
</head>
<body>

	<!-- Sidebar/menu -->
	<nav class="w3-sidebar w3-red w3-collapse w3-top w3-large w3-padding"
		style="z-index: 3; width: 300px; font-weight: bold;" id="mySidebar">
		<br> <a href="javascript:void(0)" onclick="w3_close()"
			class="w3-button w3-hide-large w3-display-topleft"
			style="width: 100%; font-size: 22px">Close Menu</a>
		<div class="w3-container">
			<h1 class="w3-padding-20">
				<b>Rent<br>Lock
				</b>
			</h1>
		</div>
		<div class="w3-bar-block ">
			<a href="home" onclick="w3_close()"
				class="w3-bar-item w3-button w3-hover-white ">Home</a> <a
				href="sucursales" onclick="w3_close()"
				class="w3-bar-item w3-button w3-hover-white ">Buscar Sucursal</a>
			<!-- 
    <a href="#showcase" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Showcase</a> 
    <a href="#services" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Services</a> 
    <a href="#designers" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Designers</a> 
    <a href="#packages" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Packages</a> 
    <a href="#contact" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Contact</a>
     -->
		</div>
	</nav>

	<!-- Top menu on small screens -->
	<header
		class="w3-container w3-top w3-hide-large w3-red w3-xlarge w3-padding">
		<a href="javascript:void(0)" class="w3-button w3-red w3-margin-right"
			onclick="w3_open()">☰</a> <span>Company Name</span>
	</header>

	<div class="w3-main " style="margin-left: 340px; margin-right: 40px">

		<form:form action="sucursales" method="GET">
			<p>
				<label>Buscá sucursales</label> <input class="w3-input w3-border"
					name="localidad" type="text">
			</p>
			<button class="w3-btn w3-blue-grey" type="submit">Buscar</button>
		</form:form>
		<div class="w3-container">
			<h2 class="w3-center">
				<p>Sucursales para ${busqueda}</p>
			</h2>

			<c:if test="${not empty error}">
				<h4>
					<span>${error}</span>
				</h4>
				<br>
			</c:if>

			<table class="w3-table-all">
				<thead>
					<tr class="w3-red w3-center">
						<th class="w3-center">Sucursal</th>
						<th class="w3-center">Tamano</th>
						<th class="w3-center">Seleccion</th>
					</tr>
				</thead>

				<c:forEach var="sucursal" items="${sucursales}">
					<tr>
						<td><c:out value="${sucursal.id}" /></td>
						<td><c:out value="${sucursal.nombre}" /></td>
						<td><c:out value="${sucursal.localidad}" /></td>
					</tr>
				</c:forEach>

			</table>
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