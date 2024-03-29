package com.sdi.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "userLogin")
public class UserLogin implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public UserLogin(String login, String name, Long id) {
		super();
		this.login = login;
		this.name = name;
		this.setId(id);
	}
	private String login;
	private String name;
	private Long id;
	
	public UserLogin(){
		
	}
	
	@XmlElement
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
}
