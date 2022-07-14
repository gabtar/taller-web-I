package ar.edu.unlam.tallerweb1.servicios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;

import ar.edu.unlam.tallerweb1.modelo.Alquiler;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlquiler;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioLocker;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service("servicioMercadoPago")
@Transactional
public class ServicioMercadoPagoImpl implements ServicioMercadoPago {
	private RepositorioAlquiler repositorioAlquiler;
   

    @Autowired
    public ServicioMercadoPagoImpl(RepositorioAlquiler repositorioAlquiler) {
        super();
        this.repositorioAlquiler = repositorioAlquiler;
    }
	@Override
	public Preference generarPago(Long alquilerId) {
		Alquiler alquiler= repositorioAlquiler.buscarAlquilerPorId(alquilerId);
		// Ac� va la clave privada(Access Token) que se genera en la cuenta de MercadoPago del vendedor
		MercadoPagoConfig.setAccessToken("APP_USR-1032568009922245-071219-223a7b5e2f6caaea96d5d50b59a1e889-1159572865");
		
		// Crea datos del cliente
		PreferenceClient client = new PreferenceClient();

		// Crea un �tem en la preferencia para el pago
		List<PreferenceItemRequest> items = new ArrayList<>();
		PreferenceItemRequest item =
		   PreferenceItemRequest.builder()
		       .title("Locker")
		       .quantity(1) 
		       .unitPrice(new BigDecimal(alquiler.getPrecio())) // Deber�a obtenerse del precio del alquiler. Queda harcodeado para pruebas
		       .build();
		
		items.add(item);
		
		/* Urls propias de mi app en spring a las que va a 
		redireccionar despues del pago si es exitoso o no */
		PreferenceBackUrlsRequest backUrls =
				   PreferenceBackUrlsRequest.builder()
				       .success("http://localhost:8080/proyecto-limpio-spring/payment/success/"+alquilerId)
				       //.pending("http://localhost:8080/prueba-spring-limpio-mercadopago/payment/pending")
				       .failure("http://localhost:8080/proyecto-limpio-spring/payment/failure")
				       .build();

		// Genera la petici�n para la preferencia
		PreferenceRequest request = PreferenceRequest.builder()
				.items(items)
				.backUrls(backUrls)
				.externalReference("alquilerId")
				.build();
		

		Preference preference = null;
		
		try {
			/* Creo que esto hace una petici�n post a un endpoint de mercadopago
			 para generar el pago en la cuenta del vendedor y devuelve el id y datos
			 de la preferencia. Despu�s desde el frontend se genera el bot�n del pago 
			 con ese id de preferencia. Con eso redirigo al usuario a un sitio de mercado
			 pago para que pague con su tarjeta el item solicitado */
			preference = client.create(request);
			// System.out.println(preference);
						
		} catch (MPException e) {
			e.printStackTrace();
		} catch (MPApiException e) {
			e.printStackTrace();
		}
		
		return preference;

	}

}
