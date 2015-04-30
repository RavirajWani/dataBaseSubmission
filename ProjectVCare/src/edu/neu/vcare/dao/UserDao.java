package edu.neu.vcare.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import edu.neu.vcare.entity.User;


public class UserDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProjectVCare");
	EntityManager em = null;
	
	
	@SuppressWarnings("unchecked")
	public List<User> fetchUserDetails(String username){
		em = factory.createEntityManager();
		Query queryUserByUserName = em.createQuery(
			    "SELECT OBJECT(user) FROM User user WHERE user.username = :username"
			);
		queryUserByUserName.setParameter("username", username);
		return queryUserByUserName.getResultList();
	}
	
	public void createUser(User userInfo){
		    em = factory.createEntityManager();
			em.getTransaction().begin();
			em.persist(userInfo);
			em.getTransaction().commit();
			em.close();	
	}
	
	
	public User fetchUserById(int userId){
		em = factory.createEntityManager();
		return em.find(User.class, userId);
	}
	
	public User updateUser(User user){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
		em.close();
		return fetchUserById(user.getId());
	}
	
	
	@SuppressWarnings("unchecked")
	public List<String> fetchUserName(){
		em = factory.createEntityManager();
		Query queryUserName = em.createQuery(
			    "SELECT user.username FROM User user"
			);
		return queryUserName.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<User> fetchAllUser(){
		List<User> users = new ArrayList<User>();
		em = factory.createEntityManager();
		Query query = em.createQuery("select user from User user");
		users = (List<User>) query.getResultList();
		return users;
	}
	
	public void deleteUser(int userId){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		User user = em.find(User.class, userId);
		em.remove(user);
		em.getTransaction().commit();
		em.close();
	}
	
	public int fetchUserBlogCount(int userId){
		em = factory.createEntityManager();
		Query query = em.createQuery("SELECT OBJECT(blogpost) FROM Blogpost blogpost WHERE blogpost.userId = :userId");
		query.setParameter("userId", userId);
		return query.getResultList().size();
	}
	
}
