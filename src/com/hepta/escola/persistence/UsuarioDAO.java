package com.hepta.escola.persistence;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.hepta.escola.entity.Usuario;


public class UsuarioDAO extends genericDAO<Usuario>{

	public UsuarioDAO(Class<Usuario> classe) {
		super(classe);
		// TODO Auto-generated constructor stub
	}
	public boolean verificaUsuario (Usuario usuario) {
		
		EntityManager em = HibernateUtil.getEntityManager();
		Query query = em.createQuery("select u from Usuario u where u.username = :pLogin and u.senha = :pSenha")
				.setParameter("pLogin", usuario.getUsername())
				.setParameter("pSenha", usuario.getSenha());

		boolean encontrado = !query.getResultList().isEmpty();

		return encontrado;
		
	}
}
