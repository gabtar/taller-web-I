package ar.edu.unlam.tallerweb1.session;

import static org.junit.Assert.*;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.io.IOException;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import ar.edu.unlam.tallerweb1.controladores.ControladorHome;
import ar.edu.unlam.tallerweb1.servicios.ServicioHome;

public class FiltroSessionTest {

	private FiltroSession filtroSession = new FiltroSession();
	private HttpServletRequest request;
	private HttpServletResponse response;
	private FilterChain filterChain;
	private HttpSession session;

	@Before
	public void setUp() throws Exception {
		session = mock(HttpSession.class);
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		filterChain = mock(FilterChain.class);
	}

	@Test
	public void testQueSiLaRutaEsPermitidaSinSessionNoMeRedirija() throws Exception {

		when(request.getServletPath()).thenReturn("/login");
		filtroSession.doFilter(request, response, filterChain);

		verify(filterChain, times(1)).doFilter(request, response);
	}

	@Test
	public void testQueSiLaRutaNoEsPermitidaSinSessionMeRedirijaAlLogin() throws Exception {

		when(request.getServletPath()).thenReturn("/home");
		when(request.getRequestURI()).thenReturn(".html");
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("userId")).thenReturn(null);
		filtroSession.doFilter(request, response, filterChain);

		verify(response, times(1)).sendRedirect("login");
	}

	@Test
	public void testQueSiEstaLaSesionIniciadaYLaRutaRequiereEstarLogueadoMeDejeIrALaRuta() throws Exception {

		when(request.getServletPath()).thenReturn("/home");
		when(request.getRequestURI()).thenReturn(".html");
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("userId")).thenReturn("4");
		filtroSession.doFilter(request, response, filterChain);

		verify(filterChain, times(1)).doFilter(request, response);
	}

}
