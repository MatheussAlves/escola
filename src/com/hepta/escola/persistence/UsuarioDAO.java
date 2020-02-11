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
		/*userAuth = (Usuario)query.getSingleResult();
		System.out.println("...");
		System.out.println("Usuario - > "+userAuth.getUsername());
		System.out.println("Usuario - > "+userAuth.getSenha());
		System.out.println("Usuario - > "+userAuth.getTipo());
		
		System.out.println("...");
		return userAuth;*/
		
}

