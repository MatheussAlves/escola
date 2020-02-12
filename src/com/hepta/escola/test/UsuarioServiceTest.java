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
		usuario.setUsername("admin");
		
		WebTarget serv;
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		
		Boolean verificaExiste;
		
		serv = client.target("http://localhost:8080/escola/rs/usuarios/createUsuario");
		
		Response response = serv.request().post(Entity.entity(usuario,
				MediaType.APPLICATION_JSON_TYPE));
		
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatusInfo().getStatusCode());
		
		//response.bufferEntity();
		//verificaExiste = response.readEntity(Boolean.class);
		
		//assertEquals(verificaExiste, true);
		
	}
	//@Test
	void testCadastroUsuarioRepetido() {
		Usuario usuario = usuarioCreate();
		usuario.setUsername("Matheus");
		
		WebTarget serv;
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		
		Boolean verificaExiste;
		
		serv = client.target("http://localhost:8080/escola/rs/usuarios/createUsuario");
		
		Response response = serv.request().post(Entity.entity(usuario,
				MediaType.APPLICATION_JSON_TYPE));
		
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatusInfo().getStatusCode());
		
		response.bufferEntity();
		verificaExiste = response.readEntity(Boolean.class);
		
		assertEquals(verificaExiste, false);
	}
	//@Test
	void testaLoginUsuario() {
		Usuario verificaLogin = new Usuario();
		
		Usuario usuario = new Usuario();
		usuario.setSenha("admin");
		usuario.setUsername("admin");
		
		WebTarget serv;
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		
		serv = client.target("http://localhost:8080/escola/rs/usuarios/login");
		Response response = serv.request().post(Entity.entity(usuario,
				MediaType.APPLICATION_JSON_TYPE));
		
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatusInfo().getStatusCode());
		
		response.bufferEntity();
		verificaLogin = response.readEntity(Usuario.class);
		
		assertEquals((verificaLogin!=null), true);
		
		
	}
	//@Test
	void testaUsuarioNaoAutenticato() {
		Usuario verificaLogin = new Usuario();
		
		Usuario usuario = new Usuario();
		usuario.setUsername("a");
		usuario.setSenha("a");
		
		WebTarget serv;
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		
		serv = client.target("http://localhost:8080/escola/rs/usuarios/login");
		Response response = serv.request().post(Entity.entity(usuario, MediaType.APPLICATION_JSON_TYPE));
		
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatusInfo().getStatusCode());
		
		response.bufferEntity();
		verificaLogin = response.readEntity(Usuario.class);
		
		assertEquals((verificaLogin.getUsername()==null), true);
	}

	
}
