package com.sdi.util;

public class EstadoCliente {
	
	
	public static Long idUsuario;
	public static Long idViaje;
	
	public static Long getIdViaje() {
		return idViaje;
	}

	public static void setIdViaje(Long idViaje) {
		EstadoCliente.idViaje = idViaje;
	}

	public static void setIdUsuario(Long id){
		idUsuario = id;
	}
	
	public static Long getIdUsuario(){
		return idUsuario;
	}

}
