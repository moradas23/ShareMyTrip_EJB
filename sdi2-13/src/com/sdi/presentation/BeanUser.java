package com.sdi.presentation;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;

import com.sdi.model.User;

@ManagedBean
public class BeanUser extends User implements Serializable {
	  private static final long serialVersionUID = 55556L;
	    
		@PostConstruct
		public void init() {
			System.out.println("BeanRegistro - PostConstruct");
		}

		@PreDestroy
		public void end() {
			System.out.println("BeanRegistro - PreDestroy");
		}
		
	
}
