package edu.neu.vcare.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.neu.vcare.entity.Initiative;



public class InitiativeDao {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProjectVCare");
	EntityManager em = null;
	
	
	
	public void createInitiatve(Initiative initative){
	    em = factory.createEntityManager();
		em.getTransaction().begin();
		System.out.println("before persist");
		em.persist(initative);
		System.out.println("after persist");
		em.getTransaction().commit();
		em.close();	
    }
	
	@SuppressWarnings("unchecked")
	public List<Initiative> fetchInitativeDetails(int userId){
		em = factory.createEntityManager();
		Query queryUserByUserName = em.createQuery(
			    "SELECT OBJECT(initiative) FROM Initiative initiative WHERE initiative.userId = :userId"
			);
		queryUserByUserName.setParameter("userId", userId);
		return queryUserByUserName.getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Initiative> fetchAllInitiative(){
		List<Initiative> initatives = new ArrayList<Initiative>();
		em = factory.createEntityManager();
		System.out.println("Before Query");
		Query query = em.createQuery("select initiative from Initiative initiative");
		initatives = (List<Initiative>) query.getResultList();
		return initatives;
	}
	
	
	public Initiative fetchInitiativeInfo(int initiativeId){
		em = factory.createEntityManager();
		return em.find(Initiative.class, initiativeId);
	}
	
	public static void main(String[] args) {
		InitiativeDao iDao = new InitiativeDao();
		Initiative init = new Initiative();
		/*init.setUserId(1);
		init.setContent("My 2nd Initiative");
		init.setDate(new Date());
		init.setTitle("2 Initiative");
		iDao.createInitiatve(init);*/
		
		List<Initiative> userIntiatives = new ArrayList<Initiative>();
		userIntiatives = iDao.fetchAllInitiative();
		for(Initiative i : userIntiatives){
			System.out.println(i.getId());
			System.out.println(i.getTitle());
			System.out.println(i.getContent());
		}
		init = iDao.fetchInitiativeInfo(1);
		System.out.println(init.getTitle());
		System.out.println(init.getContent());
		System.out.println(init.getDate());
	}
}
