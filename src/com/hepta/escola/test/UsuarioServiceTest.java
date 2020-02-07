package com.hepta.escola.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.hepta.escola.entity.Usuario;
import com.hepta.escola.enums.TipoUsuario;

public class UsuarioServiceTest {
	private static WebTarget service;
	private static final String URL_LOCAL = "http://localhost:8080/escola/rs/usuarios";
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		service = client.target(UriBuilder.fromUri(URL_LOCAL).build());
	}
	
	public Usuario usuarioCreate() {
		Usuario usuario = new Usuario();
		usuario.setUsername("admin");
		usuario.setSenha("admin");
		usuario.setTipo(TipoUsuario.ADMINISTRADOR);
		
		
		return usuario;
	}
	@Test	
	void testCadastroUsuario() {
		Usuario usuario = usuarioCreate();
		Response response = service.request().post(Entity.entity(usuario,
				MediaType.APPLICATION_JSON_TYPE));
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatusInfo().getStatusCode());
		
	}
	
	
	
}
