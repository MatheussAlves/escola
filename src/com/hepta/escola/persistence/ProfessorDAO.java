package com.hepta.escola.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.hepta.escola.entity.Professor;



public class ProfessorDAO extends genericDAO<Professor>{

	public ProfessorDAO(Class<Professor> classe) {
		super(classe);
		// TODO Auto-generated constructor stub
	}

	/*public void save(Professor professor) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(professor);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			em.close();
		}
	}

	public Professor update(Professor professor) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		Professor professorAtualizado = null;
		try {
			em.getTransaction().begin();
			professorAtualizado = em.merge(professor);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			em.close();
		}
		return professorAtualizado;
	}

	public void delete(Integer id) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			Professor professor = em.find(Professor.class, id);
			em.remove(professor);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			em.close();
		}

	}

	public Professor find(Integer id) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		Professor professor = null;
		try {
			professor = em.find(Professor.class, id);
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			em.close();
		}
		return professor;
	}

	@SuppressWarnings("unchecked")
	public List<Professor> getAll() throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		List<Professor> professores = new ArrayList<>();
		try {
			Query query = em.createQuery("FROM Produto");
			professores = query.getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			em.close();
		}
		return professores;
	}*/
}
