package ar.edu.unlam.tallerweb1.session;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FiltroSession implements Filter {

	protected static final List<String> RUTAS_PERMITIDAS = Arrays.asList("/login", "/validar-login", "/registro",
			"/validar-registro");

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		String servletPath = req.getServletPath();
		String url = (req.getRequestURI());
		HttpSession session = req.getSession();

		// Se puede acceder por cualquiera
		if (RUTAS_PERMITIDAS.contains(servletPath) || url.endsWith(".css") || url.endsWith(".js")
				|| url.endsWith(".png") || url.endsWith(".jpg") || url.endsWith(".jpeg") || url.endsWith(".ttf")
				|| url.endsWith(".woff")) {
			chain.doFilter(req, resp);
			return;
		}

		// Cualquier otra ruta necesita sesion.
		if (session.getAttribute("userId") != null) {
			// User is logged in.
			chain.doFilter(req, resp);
			return;
		}

		// 
		resp.sendRedirect("login");
	}

	@Override
	public void destroy() {

	}
}