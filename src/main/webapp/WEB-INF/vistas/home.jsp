<%--
  Created by IntelliJ IDEA.
  User: Gabriel
  Date: 9/6/2022
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
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
	<main>
		<div class="w3-main " style="margin-left: 340px; margin-right: 40px">
			<div class="w3-container">
				<img class="mySlides w3-animate-fading w3-image"
					src="img/home01.png"> <img
					class="mySlides w3-animate-fading w3-image" src="img/home02.png">
				<img class="mySlides w3-animate-fading w3-image"
					src="img/home03.png">
			</div>

			<div class="w3-container">
				<h2 class="w3-center">
					<p>Nuestras sucursales</p>
				</h2>
				<table class="w3-table-all">
					<thead>
						<tr class="w3-red w3-center">
							<th class="w3-center">Sucursal nro</th>
							<th class="w3-center">Nombre</th>
							<th class="w3-center">Localidad</th>
						</tr>
					</thead>
					<c:forEach var="Sucursal" items="${listaSucursales}">
						<tr>
							<td class="w3-center"><c:out value="${Sucursal.id}" /></td>
							<td class="w3-center"><c:out value="${Sucursal.nombre}" /></td>
							<td class="w3-center"><c:out value="${Sucursal.localidad.nombre}" /></td>
						</tr>
					</c:forEach>
					<!-- esto tambien, capas los puedo meter en otro carrucel?-->
				</table>
			</div>
		</div>
		<!-- Overlay effect when opening sidebar on small screens -->
		<div class="w3-overlay w3-hide-large" onclick="w3_close()"
			style="cursor: pointer" title="close side menu" id="myOverlay"></div>
	</main>
	<footer> </footer>
	<script>
		var myIndex = 0;
		carousel();
		function carousel() {
			var i;
			var x = document.getElementsByClassName("mySlides");
			for (i = 0; i < x.length; i++) {
				x[i].style.display = "none";
			}
			myIndex++;
			if (myIndex > x.length) {
				myIndex = 1
			}
			x[myIndex - 1].style.display = "block";
			setTimeout(carousel, 5000);
		}
	</script>

</body>
</html>

