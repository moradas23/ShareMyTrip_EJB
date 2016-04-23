package com.sdi.presentation;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sdi.business.LoginService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.UserLogin;
import com.sdi.utilidades.Timers.*;

@ManagedBean(name = "login")
@SessionScoped
public class BeanLogin implements Serializable {
	private static final long serialVersionUID = 6L;
	private String name = "";
	private String password = "";
	private String result = "login_form_result_valid";

	public BeanLogin() {
		System.out.println("BeanLogin - No existia");
		
		TimerBBDD.mantenimientoBBDD();
	}

	public String verify() {
		setPassword(password);
		LoginService login = Factories.services.createLoginService();
		UserLogin user = login.verify(name, password);
		if (user != null) {
			putUserInSession(user);
			return "exito";
		}
		setResult("login_form_result_error");
		return "fallo";
	}

	public String closeSession(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "cerrarSesion";
	}
	
	private void putUserInSession(UserLogin user) {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		session.put("LOGGEDIN_USER", user);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
