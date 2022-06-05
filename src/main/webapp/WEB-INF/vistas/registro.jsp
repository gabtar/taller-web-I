<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
<!-- w3 theme -->
<link href="css/w3.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Poppins">
<style>
body, h1 {
	font-family: "Poppins", sans-serif
}
</style>
</head>
<body>

	<header class="w3-container w3-red">
		<h1 class="w3-padding-20">Rent Lock: Registro</h1>
	</header>

	<div class="w3-container w3-center w3-margin-top">
		<form:form action="validar-registro" method="POST"
			modelAttribute="datosRegistro"
			class="w3-container w3-card-4 w3-center w3-padding-16"
			style="width: 60%; margin: auto;">

			<p class="w3-center w3-margin-top">
				<form:input path="email" id="email" type="email" class="w3-input"
					placeholder="Email" />
			</p>
			<p class="w3-center w3-margin-top">
				<form:input path="contrasenia" type="password" id="contrasenia"
					class="w3-input" placeholder="Contraseña" />

			</p>

			<p class="w3-center w3-margin-top">
				<form:input path="repetirContrasenia" type="password"
					id="repetirContrasenia" class="w3-input"
					placeholder="Repetir Contraseña" />
			</p>

			<c:if test="${not empty error}">
				<div class="w3-panel w3-red w3-padding-16">${error}</div>
			</c:if>

			<p>
				<button class="w3-btn w3-section w3-red w3-ripple w3-block"
					Type="Submit">Registarme</button>
			</p>
		</form:form>
		${msg}

	</div>
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>