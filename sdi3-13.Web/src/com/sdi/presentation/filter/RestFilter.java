package com.sdi.presentation.filter;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;



import org.apache.commons.codec.binary.Base64;

import com.sdi.business.LoginService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.UserLogin;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, urlPatterns = { "/rest/*" }, initParams = { @WebInitParam(name = "LoginParam", value = "/login.xhtml") })
public class RestFilter implements Filter  {
	// Necesitamos acceder a los parámetros de inicialización en
	// el método doFilter por lo que necesitamos la variable
	// config que se inicializará en init()
	FilterConfig config = null;
	
	LoginService service = Factories.services.getLoginService();

	/**
	 * Default constructor.
	 */
	public RestFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		// Iniciamos la variable de instancia config
		config = fConfig;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// Si no es petición HTTP nada que hacer
		if (!(request instanceof HttpServletRequest)) {
			chain.doFilter(request, response);
			return;
		}
		// En el resto de casos se verifica que se haya hecho login previamente
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		String AuthorizationBase64  = req.getHeader("Authorization");
		  
	    byte[] decodedPhraseAsBytes = Base64.decode(AuthorizationBase64);

	    String phraseDecodedToString = new String(decodedPhraseAsBytes, "utf-8");
	
	    DatatypeConverter.
		
	//	UserLogin user = service.verify(login, password);
		
		//if(user!=null)
			chain.doFilter(request, response);
		
			
	}
	
	

	
}
