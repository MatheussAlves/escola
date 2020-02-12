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

import com.hepta.escola.entity.Professor;
import com.hepta.escola.persistence.ProfessorDAO;



@Path("/professores")
public class ProfessorService {
    
   
        
        @Context
        private HttpServletRequest request;
    
        @Context
        private HttpServletResponse response;
        
        private Professor professor = new Professor();
        private ProfessorDAO dao;
        
        public ProfessorService() {
            dao = new ProfessorDAO(Professor.class);
        }
        
        protected void setRequest(HttpServletRequest request) {
            this.request = request;
        }
        
        
        
        /**
         * Adiciona novo professor no mercado
         * 
         * @param professor: Novo professor
         * @return response 200 (OK) - Conseguiu adicionar
         */
        @Path("/")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        @POST
        public Response professorCreate(Professor professor) {
            try {
                dao.save(professor);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return Response.status(Status.INTERNAL_SERVER_ERROR).build();
            }
            return Response.status(Status.OK).build();
        }
        /**
         * Lista os professores da escola
         * @return lista de professores
         */
    	@Path("/")
    	@Produces(MediaType.APPLICATION_JSON)
    	@GET
        public Response ProfessorRead() {
        	List<Professor> professores = new ArrayList<>();
        	try {
        		professores = dao.getAll();
    		} catch(Exception e) {
    			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar professores").build();
    		}
    		
    		GenericEntity<List<Professor>> entity = new GenericEntity<List<Professor>>(professores) {};
    		return Response.status(Status.OK).entity(entity).build();
        }
        @Path("/{id}")
        @Produces(MediaType.APPLICATION_JSON)
        @DELETE
        public Response professorDelete(@PathParam("id") Integer id) {
        	try {
        		dao.delete(id);
        	}catch(Exception e) {
        		return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar produto").build();
        	}
        	return Response.status(Status.OK).build();
        }
        @Path("/{id}")
    	@Consumes(MediaType.APPLICATION_JSON)
    	@Produces(MediaType.APPLICATION_JSON)
    	@PUT
        public Response professorUpdate(@PathParam("id") Integer id) {
    		try {
    			setProfessor(dao.update(professor));
    		}catch(Exception e) {
    			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro ao alterar professor").build();
    		}
    		return Response.status(Status.OK).build();
        }

		public Professor getProfessor() {
			return professor;
		}

		public void setProfessor(Professor professor) {
			this.professor = professor;
		}
        
        
}
