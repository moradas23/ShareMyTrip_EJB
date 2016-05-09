package com.sdi.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/security_check"})

public class AuthenticationServlet extends HttpServlet {

 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
/*
	 request.login(userName, userPassword);

	 StoreInCookieMethod(request.getUserPrincipal().getName(), userPassword);

	 response.sendRedirect(request.getContextPath() + "/protectedResourceUrlPattern/");
*/
    } 

}