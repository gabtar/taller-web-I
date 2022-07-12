<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pago Locker</title>
</head>
<body>

	// Prueba SDK MercadoPago.js V2
	<br>
	<!-- Renderiza el botón de pago en la etiqueta que tenga la clase especificada en el script js -->
	<a class="cho-container"></a>
	
	<script src="https://sdk.mercadopago.com/js/v2"></script>
	<script>
		// Agrega credenciales de SDK
		// Acá va la Public Key de la cuenta de mercadopago que voy a usar como
		// vendedor
		const mp = new MercadoPago("TEST-6b0dd4e9-da61-4a95-9f3d-927610acd8bf", {
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