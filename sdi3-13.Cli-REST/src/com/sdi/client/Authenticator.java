package com.sdi.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.DatatypeConverter;

public class Authenticator implements ClientRequestFilter {
	private final String user;
	private final String password;

	private static final String REST_LOGIN_SERVICE_URL = "http://localhost:8280"
			+ "/sdi3-13.Web/rest/LoginServiceRs";

	public Authenticator(String user, String password) {
		this.user = user;
		this.password = password;
	}

	@Override
	public void filter(ClientRequestContext ctx) throws IOException {
		MultivaluedMap<String, Object> headers = ctx.getHeaders();
		final String basicAuthentication = getBasicAuthentication();
		headers.add("Authorization", basicAuthentication);
	}

	private String getBasicAuthentication() {
		String token = this.user + ":" + this.password;
		try {
			return "Basic "
					+ DatatypeConverter.printBase64Binary(token
							.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException ex) {
			throw new IllegalStateException("Cannot encode with UTF-8", ex);
		}

	}

}