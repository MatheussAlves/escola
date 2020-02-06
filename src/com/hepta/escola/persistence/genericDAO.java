package com.hepta.escola.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;








public abstract class genericDAO<T> {
	
	private  Class<T> classe;
	
	
	public Class<T> getClasse() {
		return classe;
	}

	public void setClasse(Class<T> classe) {
		this.classe = classe;
	}

	public genericDAO(Class<T> classe) {
		setClasse(classe);
	}
	
	public void save(T classe) throws Exception{
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(classe);
			em.getTransaction().commit();
		}catch (Exception e){
			em.getTransaction().rollback();
			throw new Exception(e);
		}finally{
			em.close();
		}
	}
	public T update(T classe) throws Exception{
		EntityManager em = HibernateUtil.getEntityManager();
		T classeAtualizada = null;
		try {
			em.getTransaction().begin();
			classeAtualizada = em.merge(classe);
			em.getTransaction().commit();
		}catch(Exception e){
			throw new Exception(e);
		}finally {
			em.close();
		}
		return classeAtualizada;
	}
	public void delete(Integer id) throws Exception{
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			T instancia = em.find(classe, id);
			em.remove(instancia);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			em.close();
		}
		
	}
	public T find(Integer id) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		T instancia = null;
		try {
			instancia = em.find(classe, id);
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			em.close();
		}
		return instancia;
	}
	@SuppressWarnings("unchecked")
	public List<T> getAll() throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		List<T> instancia = new ArrayList<>();
		try {
			Query query = em.createQuery("FROM "+classe.getName()+" ");
			instancia = query.getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			em.close();
		}
		return instancia;
	}
	
}
