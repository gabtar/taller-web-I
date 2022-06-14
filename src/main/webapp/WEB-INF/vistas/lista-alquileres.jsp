<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<html lang="en">
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
            <p>Mis Alquileres</p>
        </h2>
    </div>
    <div class="w3-container">
    </div>
    <!-- Overlay effect when opening sidebar on small screens -->
    <div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay">

    </div>
</div>