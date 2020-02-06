package com.hepta.escola.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.hepta.escola.entity.Aluno;

public class AlunoDAO extends genericDAO<Aluno>{

	public AlunoDAO(Class<Aluno> aluno) {
		super(aluno);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	/*public void save(Aluno aluno) throws Exception{
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(aluno);
			em.getTransaction().commit();
		}catch (Exception e){
			em.getTransaction().rollback();
			throw new Exception(e);
		}finally{
			em.close();
		}
	}
	public Aluno update(Aluno aluno) throws Exception{
		EntityManager em = HibernateUtil.getEntityManager();
		Aluno alunoAtualizado = null;
		try {
			em.getTransaction().begin();
			alunoAtualizado = em.merge(aluno);
			em.getTransaction().commit();
		}catch(Exception e){
			throw new Exception(e);
		}finally {
			em.close();
		}
		return alunoAtualizado;
	}
	public void delete(Integer id) throws Exception{
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			Aluno aluno = em.find(Aluno.class, id);
			em.remove(aluno);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			em.close();
		}
		
	}
	
	public Aluno find(Integer id) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		Aluno aluno = null;
		try {
			aluno = em.find(Aluno.class, id);
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			em.close();
		}
		return aluno;
	}
	
	@SuppressWarnings("unchecked")
	public List<Aluno> getAll() throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		List<Aluno> alunos = new ArrayList<>();
		try {
			Query query = em.createQuery("FROM Aluno");
			alunos = query.getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			em.close();
		}
		return alunos;
	}
	*/
}
