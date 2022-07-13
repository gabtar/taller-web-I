<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="vistaGenerales/head.jsp"%>
<meta charset="ISO-8859-1">
<title>Pago Locker</title>
</head>
<body>
<%@ include file="vistaGenerales/navLogeado.jsp"%>
	<div class="w3-row w3-margin-top">
		<div class="w3-col s3 w3-center">
			<p>
				<br>.
			</p>
		</div>
		<div class="w3-col s9 w3-padding-top-64 w3-center w3-">
		
			<a class="cho-container w3-btn w3-blue"></a>
			<img src="${homeUrl}/img/mercadoPago.png" alt="mpz" />
		</div>
	</div>
	<br>
	<!-- Renderiza el botón de pago en la etiqueta que tenga la clase especificada en el script js -->
	
	<script src="https://sdk.mercadopago.com/js/v2"></script>
	<script>
		// Agrega credenciales de SDK
		// Acá va la Public Key de la cuenta de mercadopago que voy a usar como
		// vendedor
		const mp = new MercadoPago("APP_USR-83a82121-8efa-4078-9db0-7f3e4082fdd7", {
			locale : "es-AR",
		});

		// Inicializa el checkout
		mp.checkout({
			preference : {
				// Se le pasa el id de la preferencia de pago generada con el backend
				// desde spring
				id : '<c:out value="${preference.id}"/>',
			},
			render : {
				container : ".cho-container", // Indica el nombre de la clase donde se mostrará el botón de pago
				label : "Pagar", // Cambia el texto del botón de pago (opcional)
			},
		});
	</script>
</body>
</html>