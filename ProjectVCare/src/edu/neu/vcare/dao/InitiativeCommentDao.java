package edu.neu.vcare.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.neu.vcare.entity.InitiativeComment;

public class InitiativeCommentDao {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestProject");
	EntityManager em = null;
	
	public void createComment(InitiativeComment comment){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		System.out.println("before persist");
		em.persist(comment);
		System.out.println("after persist");
		em.getTransaction().commit();
		em.close();	
	}
	
	public void deleteComment(int commentId){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		InitiativeComment site = em.find(InitiativeComment.class, commentId);
		em.remove(site);
		em.getTransaction().commit();
		em.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<InitiativeComment> fetchInitiative(int initiativeId){
		em = factory.createEntityManager();
		Query queryUserByUserName = em.createQuery(
			    "SELECT OBJECT(initiativeComment) FROM InitiativeComment initiativeComment WHERE initiativeComment.initiativeId = :initiativeId"
			);
		queryUserByUserName.setParameter("initiativeId", initiativeId);
		return queryUserByUserName.getResultList();
	}
	
	/*public static void main(String[] args) {
		//InitiativeComment comm = new InitiativeComment();
		//comm.setInitiativeId(1);
		//comm.setContent("2nd Comment");
		//comm.setDate(new Date());
		InitiativeCommentDao iDao = new InitiativeCommentDao();
		//iDao.createComment(comm);
		
		iDao.deleteComment(1);
		
		List<InitiativeComment> comments = new ArrayList<InitiativeComment>();
		comments = iDao.fetchInitiative(1);
		for(InitiativeComment i : comments){
			System.out.println(i.getContent());
			System.out.println(i.getDate());
		}
	}*/
	
}
