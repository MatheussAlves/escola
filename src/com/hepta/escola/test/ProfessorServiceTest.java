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


import com.hepta.escola.entity.Professor;
import com.hepta.escola.entity.Usuario;
import com.hepta.escola.enums.TipoUsuario;
import com.hepta.escola.enums.Turno;

public class ProfessorServiceTest {
	private static WebTarget service;
	private static final String URL_LOCAL = "http://localhost:8080/escola/rs/professores";
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		service = client.target( UriBuilder.fromUri(URL_LOCAL).build() );
	}
	
	public Professor professorCreate() {
		
		Professor professor = new Professor();
		professor.setMateria("Historia");
		professor.setNome("Julia");
		professor.setTurno(Turno.MATUTINO);
		
		return professor;
	}
	@Test
	void testCadastraProfessor() {
		Professor professor = professorCreate();
		Integer criouUsuario;
		Integer criouProfessor;
		Usuario usuario = new Usuario();
		usuario.setSenha("professor");
		usuario.setUsername("professor");
		usuario.setTipo(TipoUsuario.DOCENTE);
		
		WebTarget serv;
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		serv = client.target("http://localhost:8080/escola/rs/usuarios/createUsuario");
		
		Response response = serv.request().post(Entity.entity(usuario, MediaType.APPLICATION_JSON_TYPE));
		criouUsuario = response.getStatusInfo().getStatusCode();
		
		response.bufferEntity();
		usuario = response.readEntity(Usuario.class);
		
		professor.setUsuario(usuario);
		
		response = service.request().post(Entity.entity(professor, MediaType.APPLICATION_JSON_TYPE));
		criouProfessor = response.getStatusInfo().getStatusCode();
		assertEquals(criouUsuario,criouProfessor);
	}
	
	//@Test
	void testGetAllProfessores() {
		Response response = service.request().get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatusInfo().getStatusCode());
	}
	
	//@Test
	void testDeleteProfessor() {
		
		WebTarget serv;
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		
		serv = client.target("http://localhost:8080/escola/rs/professores/"+2);
		Response response = serv.request().delete();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatusInfo().getStatusCode());
		
	}
}
