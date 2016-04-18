package com.sdi.presentation;
import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sdi.business.UsersService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.User;
import com.sdi.model.UserLogin;
import com.sdi.utilidades.Utilidades;

@ManagedBean(name = "registro")
@SessionScoped
public class BeanRegistro implements Serializable {
	private static final long serialVersionUID = 55556L;

	@ManagedProperty(value = "#{user}")
	private BeanUser user;

	public BeanUser getUser() {
		return user;
	}

	public void setUser(BeanUser user) {
		this.user = user;
	}
	
	@PostConstruct
	public void init() {
		System.out.println("BeanRegistro - PostConstruct");
		user = (BeanUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
	}

	@PreDestroy
	public void end() {
		System.out.println("BeanRegistro - PreDestroy");
	}

	
	public String registrar() {
		UsersService service;
		try {

			service = Factories.services.createUserService();
			
			user.setPassword(Utilidades.getStringMessageDigest(user.getPassword(), Utilidades.MD5));
			
			service.saveUser(user);
			
			User usuario = service.finByLogin(user.getLogin());
			UserLogin userLogin = new UserLogin(usuario.getLogin(), usuario.getName(), usuario.getId());
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("user");
			putUserInSession(userLogin);

			return "exito";
		}

		catch (Exception e) {
			return null;
		}

	}

	private void putUserInSession(UserLogin user) {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		session.put("LOGGEDIN_USER", user);
	}
}
