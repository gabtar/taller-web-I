<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
	<%@ include file="vistaGenerales/head.jsp"%>
	</head>
<body>
	<%@ include file="vistaGenerales/navLogeado.jsp"%>
	<div class="w3-main " style="margin-left: 340px; margin-right: 40px">
		<div class="w3-container">
			<h2 class="w3-center">
				<p>Registro de Alquileres realizados</p>
			</h2>
			<c:choose>
				<c:when test="${not empty error}">
					<h1 class="w3-text-red w3-center w3-margin-bottom">Sin Alquileres previos.</h1>
				</c:when>
				<c:otherwise>
					<table class="w3-table-all w3-centered">
				    <thead>
				      <tr class="w3-red">
				        <th>Cod.</th>
				        <th>Fecha Inicio</th>
				        <th>Fecha Fin</th>
				        <th>Estado</th>
				        <th>Importe</th>
				        <th>Pagar</th>
				      </tr>
				    </thead>
					  <c:forEach var="alquiler" items="${registro}">	
					    <tr>
					      <td>${alquiler.id}</td>
					      <td>${alquiler.fechaInicio}</td>
					      <td>${alquiler.fechaFinalizacion}</td>
					      <td>
					      	<c:choose>
					      		<c:when test="${alquiler.estadoAlquiler == 'ACTIVO'}">
									<span class="w3-green w3-padding">${alquiler.estadoAlquiler}</span>
								</c:when>
								<c:when test="${alquiler.estadoAlquiler == 'FINALIZADO'}">
									<span class="w3-red w3-padding">${alquiler.estadoAlquiler}</span>
								</c:when>
								<c:otherwise>
									<span class="w3-indigo w3-padding">${alquiler.estadoAlquiler}</span>
								</c:otherwise>
					      	</c:choose>	
					      </td>
					      
					      <td>
					      	<c:if test="${alquiler.estadoAlquiler == 'FINALIZADO'}">
								$${alquiler.precio}
							</c:if> 	
					      </td>
					      <td>
							<c:if test="${alquiler.estadoAlquiler == 'FINALIZADO'}">
								<a href="${homeUrl}payment/${alquiler.id}" onclick="w3_close()" class="w3-button w3-indigo w3-round">Pagar</a>
							</c:if>
					      </td>
					    </tr>
					    <tr>
					    
					    </tr>
					  </c:forEach>
				  </table>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>