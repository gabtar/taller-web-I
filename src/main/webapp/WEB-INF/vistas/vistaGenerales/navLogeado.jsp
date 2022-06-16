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
				<img src="img/logo.png" alt="logo rent lock" />
			</p>
		</div>
	</div>
	<div class="w3-bar-block ">
		<p>Bienvenido ${nombreUsuario}</p>
		<a href="homeLogeado" onclick="w3_close()"
			class="w3-bar-item w3-button w3-hover-white ">Mis Alquileres</a> <a
			href="alquileres" onclick="w3_close()"
			class="w3-bar-item w3-button w3-hover-white ">Nuevo Alquiler</a> <a
			href="" onclick="w3_close()"
			class="w3-bar-item w3-button w3-hover-white ">Perfil</a> <a
			href="logout" onclick="w3_close()"
			class="w3-bar-item w3-button w3-hover-white ">Desconectarse</a>
	</div>
</nav>
