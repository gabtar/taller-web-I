<%--
  Created by IntelliJ IDEA.
  User: Surface Book
  Date: 6/28/2022
  Time: 9:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <%@ include file="vistaGenerales/head.jsp"%>
    <title>Agregar producto</title>
</head>
<style>
    input[type=text], select {
        width: 100%;
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }

    input[type=submit] {
        width: 100%;
        background-color: #4CAF50;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    input[type=submit]:hover {
        background-color: #45a049;
    }

    div {
        border-radius: 5px;
        background-color: #f2f2f2;
        padding: 20px;
    }
</style>
<body>

<div class="w3-center">
	<h2>Rent-Lock</h2>
	<h3>Ingrese los datos para abrir el locker</h3>
</div>

<div>
    <%--@elvariable id="validarCodigo" type=""--%>
	<c:if test="${not empty error}">
            <div class="w3-panel w3-red w3-padding-16">${error}</div>
     </c:if>
     <c:if test="${not empty mensaje}">
            <div class="w3-panel w3-green w3-padding-16">${mensaje}</div>
     </c:if>
    
    <form:form action="validar" method="POST"
               modelAttribute="validarCodigo"
               class="w3-container w3-card-4 w3-center w3-padding-32"
               style="width: 60%; margin: auto;">

        
            <form:input path="nombre" id="nombre" type="email" class="w3-input w3-round w3-border"
                        placeholder="Email" required="true" style="padding: 12px 20px" />
       
        
            <form:input path="codigo" type="text" id="codigo"
                        class="w3-input w3-border" placeholder="Código" required="true"/>
       
        
            <form:input path="lockerId" type="number"
                        id="lockerId" class="w3-input w3-border w3-round"
                        placeholder="Número de Locker" required="true" style="padding: 12px 20px" />
        
            <button class="w3-btn w3-section w3-red w3-ripple w3-block w3-margin-top"
                    Type="Submit">Enviar</button>
    </form:form>
</div>

</body>
</html>
