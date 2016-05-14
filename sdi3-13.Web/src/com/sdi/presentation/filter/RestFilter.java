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
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, urlPatterns = { "/rest/*" })
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
		
		String AuthorizationBase64  = req.getHeader("Authorization");
		
		String[] AuthorizationBase64Aux = AuthorizationBase64.split(" "); 
		
		String Authorization = decode(AuthorizationBase64Aux[1]);
		
		String[] datos = Authorization.split(":");
		
		String login = datos[0];
		
		String password = datos[1];
		
		UserLogin user = service.verify(login, password);
		
		if(user==null)
			return;
		
		chain.doFilter(request, res);
		
			
	}
	
    private static String radixBase64=
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
          + "abcdefghijklmnopqrstuvwxyz"
          + "0123456789"
          + "+/";
	
	 private static String decode(String string){
         String binary_string="";
         for(char c:string.toCharArray()){
             if(c=='=')
                 break;
             String char_to_binary = Integer.toBinaryString(radixBase64.indexOf(c));
             while(char_to_binary.length()<6)
                 char_to_binary="0"+char_to_binary;
             binary_string+=char_to_binary;
         }
         if(string.endsWith("=="))
             binary_string=binary_string.substring(0, binary_string.length()-4);
         else if(string.endsWith("="))
             binary_string=binary_string.substring(0, binary_string.length()-2);
         string="";
         for(int i=0;i<binary_string.length();i+=8){
             String eight_binary_digits = binary_string.substring(i, i+8);
             string+=(char)Integer.parseInt(eight_binary_digits,2);
         }
         return string;
     }

	
	

	
}
