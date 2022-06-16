<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="vistaGenerales/head.jsp"%>
</head>
<body>
	<header>
		<!-- Sidebar/menu   de esta forma se llaman los jsp por partes-->
		<%@ include file="vistaGenerales/navComun.jsp"%>
	</header>

	<div class="w3-row w3-margin-top">
		<div class="w3-col s3 w3-center">
			<p>
				<br>.
			</p>
		</div>
		<div class="w3-col s9 w3-center">
			<form:form action="validar-login" method="POST"
				modelAttribute="datosLogin"
				class="w3-container w3-card-4 w3-center w3-padding-16"
				style="width: 60%; margin: auto;">

				<p class="w3-center w3-margin-top">
					<form:input path="email" id="email" type="email" class="w3-input"
						placeholder="Email" />
				</p>
				<p class="w3-center w3-margin-top">
					<form:input path="password" type="password" id="password"
						class="w3-input" placeholder="Contraseña" />
				</p>
				<c:if test="${not empty error}">
					<div class="w3-panel w3-red w3-padding-16">${error}</div>
				</c:if>
				<p>
					<button class="w3-btn w3-section w3-red w3-ripple w3-block"
						Type="Submit">Login</button>
				</p>
				<p>
					<a href="registro" class="w3-text-red">Registrarme</a>
				</p>
			</form:form>
		</div>
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
