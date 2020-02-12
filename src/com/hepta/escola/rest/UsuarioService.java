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

import com.hepta.escola.entity.Usuario;
import com.hepta.escola.persistence.UsuarioDAO;



@Path("/usuarios")
public class UsuarioService {
	
    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;
    
    protected void setRequest(HttpServletRequest request) {
        this.request = request;
    }
    
    
    private Usuario usuario = new Usuario();
    private UsuarioDAO userDao;
    
    
    public UsuarioService() {
    	userDao = new UsuarioDAO(Usuario.class);
    }
    
    
    
    /**
     * Cria um usuario no sistema
     * @param usuario
     * @return 200 conseguiu adicionar
     */
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response usuarioCreate(Usuario usuario) {
    	try {
    		userDao.save(usuario);
    	}catch(Exception e){
    		e.printStackTrace();
    		System.out.println("Erro");
    		return Response.status(Status.INTERNAL_SERVER_ERROR)
    				.entity("Erro ao criar usuario").build();
    		
    	}
    	return Response.status(Status.OK).build();
    }
    /**
     * Lista todos os usuarios do sistema
     * @return
     */
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response usuarioRead() {
    	List<Usuario> usuarios = new ArrayList<>();
    	try {
    		usuarios = userDao.getAll();
    	}catch(Exception e) {
    		return Response.status(Status.INTERNAL_SERVER_ERROR)
    				.entity("Erro ao buscar usuarios").build();
    	}
    	GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(usuarios) {};
    	return Response.status(Status.OK).entity(entity).build();
    }
    /**
     * 
     * @param id
     * @return
     */
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)	
    @DELETE
    public Response usuarioDelete(@PathParam("id") Integer id) {
    	try {
    		userDao.delete(id);
    	}catch(Exception e) {
    		return Response.status(Status.INTERNAL_SERVER_ERROR)
    				.entity("Erro ao deletar usuario").build();
    	}
    	return Response.status(Status.OK).build();
    }
    /**
     * Altera um usuario
     * @param id
     * @return
     */
    @Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@PUT
    public Response usuarioUpdate(@PathParam("id") Integer id) {
    	try {
    		setUsuario(userDao.update(usuario));
    	}catch(Exception e){
    		e.printStackTrace();
    		return Response.status(Status.INTERNAL_SERVER_ERROR)
    				.entity("Erro ao alterar usuario")
    				.build();
    	}
    	return Response.status(Status.OK).build();
    }
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response usuarioLogin(Usuario usuario) {
		Usuario login = new Usuario();
		try {
			login = userDao.verificaUsuario(usuario);
			System.out.println(login);
		}catch(Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro interno").build();
		}
		System.out.println("Usuario - > "+login.getUsername());
		System.out.println("Usuario - > "+login.getSenha());
		System.out.println("Usuario - > "+login.getTipo());
	
		return Response.status(Status.OK).entity(login).build();
	}
	
	@Path("/createUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response createUsuario(Usuario usuario) {
		Usuario user = new Usuario();
		Boolean existe;
		existe = userDao.verificaUsername(usuario); //verifica se retornou algum usuario já existente. //true não retornou false retornou
		System.out.println(existe);
		if(existe == true) {
			try {
				user = userDao.createUsuario(usuario);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro interno").build();
			}
		}else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro interno").build();
		}
		
		return Response.status(Status.OK).entity(user).build();
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
    
}