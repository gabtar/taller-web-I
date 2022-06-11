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
<header class="w3-container w3-top w3-hide-large w3-red w3-xlarge w3-padding">
  <a href="javascript:void(0)" class="w3-button w3-red w3-margin-right" onclick="w3_open()">☰</a>
  <span>Company Name</span>
</header>

<div class="w3-main " style="margin-left:340px;margin-right:40px">
	<div class="w3-container">
	<h2 class="w3-center"><p>Mis Alquileres</p></h2>
	  <table class="w3-table-all">
	    <thead>
	      <tr class="w3-red ">
	        <th class="w3-center">Sucursal</th>
	        <th class="w3-center">Tamano</th>
	        <th class="w3-center">Codigo de alquiler</th>
	      </tr>
	    </thead>
	   
	  </table>
	</div>
	
	<div class="w3-container">
	<h2 class="w3-center"><p>Lockers Disponibles</p></h2>
	  <table class="w3-table-all">
	    <thead>
	      <tr class="w3-red w3-center">
	        <th class="w3-center">Sucursal</th>
	        <th class="w3-center">Tamano</th>
	        <th class="w3-center">Seleccion</th>
	      </tr>
	    </thead>
	  	
        <c:forEach var="locker" items="${listaAlquileres}">
			<tr>
				<td><c:out value="${locker.idSucursal}" />
				</td>
				<td><c:out value="${locker.tamano}" /></td>
				<td>
				<a herf="#" class="w3-button w3-black">Elegir</a>
				
				</td>
			</tr>
		</c:forEach>
        
	  </table>
	</div>
</div>


<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->

  <!-- Header 
  <div class="w3-container" style="margin-top:80px" id="showcase">
    <h1 class="w3-jumbo"><b>Interior Design</b></h1>
    <h1 class="w3-xxxlarge w3-text-red"><b>Showcase.</b></h1>
    <hr style="width:50px;border:5px solid red" class="w3-round">
  </div>
  -->
  



