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
	</div>
	<div class="container">
		<table class="w3-table-all">
			<c:forEach var="locker" items="${listaAlquileres}">
				<thead>
				<tr>
					<td>Sucursal: <c:out value="${locker.idSucursal}" /> </td>
					<td>Tipo de locker: <c:out value="${locker.id}" /></td>
					<td>Tipo de locker: <c:out value="${locker.tamano}" /></td>
					<td> $0.0</td>
					<td><a herf="#" class="w3-button w3-light-blue">Codigo Apertura</a></td>
					<td><a herf="#" class="w3-button w3-light-blue">CerrarLocker</a></td>
				</tr>
				<tr>
					<td colspan="6">

										</td>
				</tr>
				</thead>
				<p></p>
			</c:forEach>
		</table>
		<p><label>Recordarorio</label>
			<form:form action="MetodoModificarTexto" method="POST" modelAttribute="modificarTextoLocker">
				<form:input path="textoModificado" id="textoModificado" type="text" class="w3-input" placeholder="123" />
				<form:input path="textoModificado" id="textoModificado" type="email" class="w3-input" placeholder="Email" />
				<input path="texto" type="text" class="w3-input" placeholder="${locker.textoDelUsuario}" aria-label="Recipient's username" aria-describedby="button-addon2">
				<button class="btn btn-outline-secondary" type="submit">Modificar</button>
			</form:form>
			${modificarTextoLocker}
		</p>
	</div>


	
	<div class="w3-container">
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




