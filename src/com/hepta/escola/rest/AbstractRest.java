package com.hepta.escola.rest;

import javax.ws.rs.Path;

@Path("/")
public abstract class AbstractRest<T> {
	
	private  Class<T> classe;

	
	
	
	
	public Class<T> getClasse() {
		return classe;
	}

	public void setClasse(Class<T> classe) {
		this.classe = classe;
	}
	
	
	
	
}
