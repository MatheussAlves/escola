package com.hepta.escola.persistence;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.hepta.escola.entity.Usuario;


public class UsuarioDAO extends genericDAO<Usuario>{

	public UsuarioDAO(Class<Usuario> classe) {
		super(classe);
		// TODO Auto-generated constructor stub
	}
	public Usuario verificaUsuario (Usuario usuario) {
		
		Usuario userAuth = new Usuario();
		EntityManager em = HibernateUtil.getEntityManager();
		
		Query query = em.createQuery("select u from Usuario u where u.username = :pLogin and u.senha = :pSenha")
					.setParameter("pLogin", usuario.getUsername())
					.setParameter("pSenha", usuario.getSenha());
		
		boolean encontrado = !query.getResultList().isEmpty();
		
		if(encontrado) {
			return (Usuario) em.createQuery("select u from Usuario u where u.username = :pLogin and u.senha = :pSenha")
					.setParameter("pLogin", usuario.getUsername())
					.setParameter("pSenha", usuario.getSenha())
					.getSingleResult();
		}else {
			return userAuth;
		}
	}
	/**
	 * 
	 * @param usuario
	 * @return
	 */
	public Boolean verificaUsername(Usuario usuario) {
		EntityManager em = HibernateUtil.getEntityManager();
		Query query = em.createQuery("select u from Usuario u where lower(u.username) = lower(:pLogin)")
				.setParameter("pLogin", usuario.getUsername());
		
		return query.getResultList().isEmpty();
	}
	public Usuario createUsuario(Usuario usuario) throws Exception {
		Boolean checkExists;
		checkExists = verificaUsername(usuario);
		Usuario user = new Usuario();
		System.out.println(checkExists);
		if(checkExists) {
			EntityManager em = HibernateUtil.getEntityManager();
			try {
				em.getTransaction().begin();
				em.persist(usuario);
				//em.refresh(usuario);
				em.flush();
				em.getTransaction().commit();
			}catch (Exception e){
				em.getTransaction().rollback();
				throw new Exception(e);
			}finally{
				em.close();
			}
		}
		return usuario;
	}
		/*userAuth = (Usuario)query.getSingleResult();
		System.out.println("...");
		System.out.println("Usuario - > "+userAuth.getUsername());
		System.out.println("Usuario - > "+userAuth.getSenha());
		System.out.println("Usuario - > "+userAuth.getTipo());
		
		System.out.println("...");
		return userAuth;*/
		
}

