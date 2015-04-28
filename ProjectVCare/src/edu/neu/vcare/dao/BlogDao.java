package edu.neu.vcare.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import edu.neu.vcare.entity.Blogpost;

public class BlogDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProjectVCare");
	EntityManager em = null;

	public void createblog(Blogpost blog){
	    em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(blog);
		em.getTransaction().commit();
		em.close();	
    }
	
	@SuppressWarnings("unchecked")
	public List<Blogpost> fetchBlogDetails(int userId){
		em = factory.createEntityManager();
		Query queryUserByUserName = em.createQuery(
			    "SELECT OBJECT(blogpost) FROM Blogpost blogpost WHERE blogpost.userId = :userId"
			);
		queryUserByUserName.setParameter("userId", userId);
		return queryUserByUserName.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Blogpost> fetchAllBlog(){
		List<Blogpost> blogs = new ArrayList<Blogpost>();
		em = factory.createEntityManager();
		Query query = em.createQuery("select blogpost from Blogpost blogpost");
		blogs = (List<Blogpost>) query.getResultList();
		return blogs;
	}
	
	
	public Blogpost fetchBlogInfo(int blogId){
		em = factory.createEntityManager();
		return em.find(Blogpost.class, blogId);
	}
	
	public void updateBlog(Blogpost blog){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(blog);
		em.getTransaction().commit();
		em.close();
	}
	
	public void deleteBlog(int blogId){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Blogpost blog = em.find(Blogpost.class, blogId);
		em.remove(blog);
		em.getTransaction().commit();
		em.close();
	}
	
	public Blogpost TempUpdateBlog(Blogpost blog){
		updateBlog(blog);
		Blogpost updatedBlog  = fetchBlogInfo(blog.getId());
		return updatedBlog;
	}
	
}