package com.hepta.escola.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.hepta.escola.entity.Aluno;
import com.hepta.escola.persistence.AlunoDAO;


@Path("/alunos")
public class AlunoService {
	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;
	
	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	private Aluno aluno = new Aluno();
	private AlunoDAO dao;
	
	public AlunoService() {
		dao = new AlunoDAO(Aluno.class);
	}
	
	/**
	 * 
	 * @param aluno
	 * @return response 200 conseguiu adicionar o aluno
	 */
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response alunoCreate(Aluno aluno) {
		try {
			dao.save(aluno);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar alunos no sistema").build();
		}
		
		return Response.status(Status.OK).build();
	}
	/**
	 * Lista todos os alunos da escola
	 * 
	 * @return response 200 (OK) - Conseguiu listar
	 */
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response alunoRead() {
		List<Aluno> alunos = new ArrayList<>();
		try {
			alunos = dao.getAll();
		} catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar alunos").build();
		}
		
		GenericEntity<List<Aluno>> entity = new GenericEntity<List<Aluno>>(alunos) {};
		return Response.status(Status.OK).entity(entity).build();
	}
	/**
	 * Atualiza um aluno na escola
	 * 
	 * @param id: id do aluno
	 * @param aluno: Aluno atualizado
	 * @return response 200 (OK) - Conseguiu atualizar
	 */
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PUT
	public Response alunoUpdate(@PathParam("id") Integer id, Aluno aluno) {
		try {
			setAluno(dao.update(aluno));
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao alterar aluno").build();
		}
		return Response.status(Status.OK).build();
	}
	
	/**
	 * Remove um aluno do sistema
	 * 
	 * @param id: id do aluno
	 * @return response 200 (OK) - Conseguiu remover
	 */
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@DELETE
	public Response alunoDelete(@PathParam("id") Integer id) {
		try {
			dao.delete(id);
		}catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar aluno").build();
		}
		return Response.status(Status.OK).build();
	}
		
	
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	
}
