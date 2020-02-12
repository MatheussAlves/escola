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

import com.hepta.escola.entity.Aluno;
import com.hepta.escola.entity.Usuario;
import com.hepta.escola.enums.Ensino;
import com.hepta.escola.enums.TipoUsuario;

public class EscolaServiceTest {
	private static WebTarget service;
	private static final String URL_LOCAL = "http://localhost:8080/escola/rs/alunos";
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		service = client.target( UriBuilder.fromUri(URL_LOCAL).build() );
	}
	
	
	public Aluno createAluno(){
		Aluno aluno = new Aluno();
		aluno.setNome("Matheus");
		aluno.setIdade(10);
		aluno.setSerie("5");
		aluno.setEnsino(Ensino.FUNDAMENTAL);
		
		return aluno;
	}
	
	@Test
	void testCadastraUsuarioAluno() {
		Aluno aluno = createAluno();
		Usuario usuario = new Usuario();
		usuario.setSenha("estudante");
		usuario.setUsername("estudante");
		usuario.setTipo(TipoUsuario.ESTUDANTE);
		
		
		
		WebTarget serv;
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		serv = client.target("http://localhost:8080/escola/rs/usuarios/createUsuario");
		
		Response response = serv.request().post(Entity.entity(usuario, MediaType.APPLICATION_JSON_TYPE));
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatusInfo().getStatusCode());
		
		aluno.setUsuario(usuario);
		
		response = service.request().post(Entity.entity(aluno, MediaType.APPLICATION_JSON_TYPE));
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatusInfo().getStatusCode());
	}
	
	//@Test
	void testGetAllAlunos() {
		
		Response response = service.request().get();
		assertEquals(Response.Status.OK.getStatusCode(),response.getStatusInfo().getStatusCode());
		
		
	}
	//@Test
	void testDeleteAlunos() {
		
		WebTarget serv;
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		
		serv = client.target("http://localhost:8080/escola/rs/alunos/"+2);
		Response response = serv.request().delete();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatusInfo().getStatusCode());
		
	}
	
}
