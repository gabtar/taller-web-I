<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<!-- esta linea llama al jsp de la carpeta vistaGEnerales-->
<%@ include file="vistaGenerales/head.jsp"%>
</head>
<body>
	<!-- Sidebar/menu   de esta forma se llaman los jsp por partes-->
	<c:choose>
		<c:when test="${not empty sessionScope.userId}">
			<%@ include file="vistaGenerales/navLogeado.jsp"%>
		</c:when>
		<c:otherwise>
			<%@ include file="vistaGenerales/navComun.jsp"%>
		</c:otherwise>
	</c:choose>

	<!-- Top menu on small screens -->
	<header
		class="w3-container w3-top w3-hide-large w3-red w3-xlarge w3-padding">
		<a href="javascript:void(0)" class="w3-button w3-red w3-margin-right"
			onclick="w3_open()">☰</a> <span>RentLock</span>
	</header>

	<div class="w3-main w3-margin-top"
		style="margin-left: 340px; margin-right: 40px">
		
		<c:if test="${not empty sessionScope.userId}">
			<h2 class="w3-center">Buscar Lockers disponibles por Sucursal</h2>
		</c:if>

		<form:form action="sucursales" method="GET" class="w3-margin-top">
			<p>
				<label>Ingrese una localidad: </label>
			</p>
			<p>
				<input class="w3-input" list="localidad" name="localidad"
					autocomplete="off" />
				<datalist id="localidad">
					<c:forEach var="localidad" items="${localidades}">
						<option value="${localidad.nombre}">
					</c:forEach>
				</datalist>
			</p>
			<button class="w3-btn w3-blue-grey" type="submit">Buscar</button>
		</form:form>
		<c:choose>
			<c:when test="${not empty error}">
				<h4>
					<span>${error}</span>
				</h4>
				<br>
			</c:when>
			<c:otherwise>
				<div class="w3-container">
					<h2 class="w3-center">
						<c:choose>
							<c:when test="${empty busqueda}">
								<p>Mostrando todas las sucursales</p>
							</c:when>
							<c:otherwise>
								<p>Sucursales para ${busqueda}</p>
							</c:otherwise>
						</c:choose>
						  <div id="mapdiv"></div>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/openlayers/2.11/lib/OpenLayers.js"></script> 
  <script type="text/javascript">
    map = new OpenLayers.Map("mapdiv");
    map.addLayer(new OpenLayers.Layer.OSM());
    
    epsg4326 =  new OpenLayers.Projection("EPSG:4326"); //WGS 1984 projection
    projectTo = map.getProjectionObject(); //The map projection (Spherical Mercator)
   
    // Centro fijo Cerca de Ramos
    var lonLat = new OpenLayers.LonLat( -58.54906, -34.64014 ).transform(epsg4326, projectTo);
          
    var zoom=14;
    map.setCenter (lonLat, zoom);

    var vectorLayer = new OpenLayers.Layer.Vector("Overlay");
    
    // Por cada sucursal encontrala la agrega al mapa
    <c:forEach var="sucursal" items="${sucursales}">
        var feature = new OpenLayers.Feature.Vector(
                new OpenLayers.Geometry.Point( '<c:out value="${sucursal.longitud}"/>', '<c:out value="${sucursal.latitud}"/>'  ).transform(epsg4326, projectTo),
                {description:'<c:out value="${sucursal.nombre}"/>'} ,
                {externalGraphic: 'img/location.png', graphicHeight: 25, graphicWidth: 21, graphicXOffset:-12, graphicYOffset:-25  }
            );    
        vectorLayer.addFeatures(feature);
    </c:forEach>
   
    map.addLayer(vectorLayer);
 
    //Add a selector control to the vectorLayer with popup functions
    var controls = {
      selector: new OpenLayers.Control.SelectFeature(vectorLayer, { onSelect: createPopup, onUnselect: destroyPopup })
    };

    function createPopup(feature) {
      feature.popup = new OpenLayers.Popup.FramedCloud("pop",
          feature.geometry.getBounds().getCenterLonLat(),
          null,
          '<div class="markerContent">'+feature.attributes.description+'</div>',
          null,
          true,
          function() { controls['selector'].unselectAll(); }
      );
      //feature.popup.closeOnMove = true;
      map.addPopup(feature.popup);
    }

    function destroyPopup(feature) {
      feature.popup.destroy();
      feature.popup = null;
    }
    
    map.addControl(controls['selector']);
    controls['selector'].activate();
      
  </script>
						
						
					</h2>
					<table class="w3-table-all">
						<thead>
							<tr class="w3-red w3-center">

								<th class="w3-center">Nro Sucursal</th>
								<th class="w3-center">Localidad</th>
								<th class="w3-center">Nombre de la Sucursal</th>
								<c:if test="${not empty sessionScope.userId}">
									<th class="w3-center">Lockers Disponibles</th>
								</c:if>
							</tr>
						</thead>
						<c:forEach var="sucursal" items="${sucursales}">
							<tr>
								<td class="w3-center"><c:out value="${sucursal.id}" /></td>
								<td class="w3-center"><c:out
										value="${sucursal.localidad.nombre}" /></td>
								<td class="w3-center"><c:out value="${sucursal.nombre}" /></td>
								<c:if test="${not empty sessionScope.userId}">
									<td class="w3-center">
										<a class="w3-button w3-black w3-small" href="alquileres/${sucursal.id}">Ver</a>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<br>


	<!-- Overlay effect when opening sidebar on small screens -->
	<div class="w3-overlay w3-hide-large" onclick="w3_close()"
		style="cursor: pointer" title="close side menu" id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->

	<!-- Header 
  <div class="w3-container" style="margin-top:80px" id="showcase">
    <h1 class="w3-jumbo"><b>Interior Design</b></h1>
    <h1 class="w3-xxxlarge w3-text-red"><b>Showcase.</b></h1>
    <hr style="width:50px;border:5px solid red" class="w3-round">
  </div>
  -->