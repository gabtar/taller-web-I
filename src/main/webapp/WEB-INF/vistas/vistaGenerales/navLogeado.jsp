<nav class="w3-sidebar w3-red w3-collapse w3-top w3-large w3-padding"
	style="z-index: 3; width: 300px; font-weight: bold;" id="mySidebar">
	<br> <a href="javascript:void(0)" onclick="w3_close()"
		class="w3-button w3-hide-large w3-display-topleft"
		style="width: 100%; font-size: 22px">Close Menu</a>

	<div class="w3-row">
		<div class="w3-col s6 w3-center">
			<h1>
				<b>Rent<br>Lock
				</b>
			</h1>
		</div>
		<div class="w3-col s6 w3-center">
			<p>
				<img src="${homeUrl}img/logo.png" alt="logo rent lock" />
			</p>
		</div>
	</div>
	<div class="w3-bar-block ">
		<p>Bienvenido ${nombreUsuario}</p>
		<a href="${homeUrl}homeLogeado" onclick="w3_close()"
			class="w3-bar-item w3-button w3-hover-white ">Mis Lockers</a> <a
			href="${homeUrl}alquileres/registro" onclick="w3_close()"
			class="w3-bar-item w3-button w3-hover-white ">Registro Alquileres</a> <a
			href="${homeUrl}alquileres/buscar" onclick="w3_close()"
			class="w3-bar-item w3-button w3-hover-white ">Buscar Lockers</a> <a
			href="${homeUrl}sucursales" onclick="w3_close()"
			class="w3-bar-item w3-button w3-hover-white ">Buscar Sucursales</a> <a
			href="${homeUrl}logout" onclick="w3_close()"
			class="w3-bar-item w3-button w3-hover-white ">Desconectarse</a>
			<br>
			<br>
			<br>
			<br>
			<a href="${homeUrl}codigo/validar" target="_blank" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white ">
				Menu En Sucursal
			</a>
	</div>
</nav>
