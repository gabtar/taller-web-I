<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
		<div class="w3-bar-item w3-button w3-hover-white ">Id usuario:
			${userId}</div>
		<a href="home" onclick="w3_close()"
			class="w3-bar-item w3-button w3-hover-white ">Home</a> <a
			href="sucursales" onclick="w3_close()"
			class="w3-bar-item w3-button w3-hover-white ">Buscar Sucursal</a> <a
			href="logout" onclick="w3_close()"
			class="w3-bar-item w3-button w3-hover-white ">Logout</a>
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