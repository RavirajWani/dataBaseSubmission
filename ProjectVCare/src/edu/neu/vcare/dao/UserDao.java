package edu.neu.vcare.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.neu.vcare.bean.UserBean;
import edu.neu.vcare.entity.User;


public class UserDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProjectVCare");
	EntityManager em = null;
	
	public UserBean validateUser(UserBean user){
		
		UserDao dao = new UserDao();
		List<User> users = new ArrayList<User>();
		UserBean bean = new UserBean();
		bean.setValid(false);
		users = dao.fetchUserDetails(user.getUsername());
		for(User u : users){
			if(user.getUsername().equals(u.getUsername()))
			{
				bean.setId(u.getId());
				bean.setFirstName(u.getFirstName());
				bean.setLastName(u.getLastName());
				bean.setPassword(u.getPassword());
				bean.setValid(true);
			}
		}
		return bean;
	}
	
	@SuppressWarnings("unchecked")
	private List<User> fetchUserDetails(String username){
		em = factory.createEntityManager();
		Query queryUserByUserName = em.createQuery(
			    "SELECT OBJECT(user) FROM User user WHERE user.username = :username"
			);
		queryUserByUserName.setParameter("username", username);
		return queryUserByUserName.getResultList();
	}
	
	public UserBean createUser(UserBean userInfo){
		UserDao dao = new UserDao();
		User user = new User();
		user.setFirstName(userInfo.getFirstName());
		user.setLastName(userInfo.getLastName());
		user.setUsername(userInfo.getUsername());
		user.setPassword(userInfo.getPassword());	
		user.setCountryid(userInfo.getCountry());
		user.setStateid(userInfo.getState());
		user.setEmailId(userInfo.getEmailId());
		System.out.println("before call");
		dao.createUser(user);
		System.out.println("after call");
		return validateUser(userInfo);			
	}
	
	
	private void createUser(User userInfo){
		    em = factory.createEntityManager();
			em.getTransaction().begin();
			System.out.println("before persist");
			em.persist(userInfo);
			System.out.println("after persist");
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
	
	public static void main(String[] args) {
		UserDao dao = new UserDao();
		//List<User> users = new ArrayList<User>();
		//UserBean user = new UserBean();
		//user.setUserName("RF");
		//user.setPassword("1");
		//user.setFirstName("Roger");
		//user.setLastName("Federer");
		//user = dao.createUser(user);
		//System.out.println(user.isValid());
		
		/*User u = new User();
		u = dao.fetchUserById(1);
		System.out.println(u.getFirstName());
		System.out.println(u.getLastName());
		System.out.println(u.getId());
		System.out.println(u.getUsername());*/
		
		List<String> usernames = dao.fetchUserName();
		for (String u : usernames){
			System.out.println(u);
		}
		
		
	}

}
