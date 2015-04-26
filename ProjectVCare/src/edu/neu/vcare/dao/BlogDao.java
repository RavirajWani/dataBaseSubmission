package edu.neu.vcare.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.neu.vcare.bean.BlogBean;
import edu.neu.vcare.entity.Blogpost;


public class BlogDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProjectVCare");
	EntityManager em = null;
	
	public List<BlogBean> createBlogPost(BlogBean blog)
	{
		
		Blogpost blogpost = new Blogpost();
		
		blogpost.setContent(blog.getContent());
		blogpost.setTitle(blog.getTitle());
		blogpost.setDate(new Date());
		blogpost.setUserId(blog.getUserId());
		
		createblog(blogpost);
		System.out.println("Blog Created");
		
		BlogBean bean = new BlogBean();
		List<BlogBean> blogbeans = new ArrayList<BlogBean>();
		List<Blogpost> blogs = new ArrayList<Blogpost>();
		
		blogs = fetchBlogDetails (blog.getUserId());
		System.out.println("Blogs after query: " + blogs.size());
		
		for (Blogpost b : blogs){
	    	 System.out.println(b.getContent());
	    	 System.out.println(b.getTitle());
	    	 System.out.println(b.getDate());
	     }
		
		//right now we are fetching the blog only for the user
		
		for(Blogpost b : blogs){
				BlogBean b2 = new BlogBean();
				b2.setId(b.getId());
				b2.setTitle(b.getTitle());
				b2.setUserId(b.getUserId());
				b2.setDatePosted(b.getDate());
				b2.setContent(b.getContent());
				blogbeans.add(b2);
		}
		System.out.println("Blogs from database : " + blogbeans.size());
		return blogbeans;		
	}

	@SuppressWarnings("unused")
	private void createblog(Blogpost blog){
	    em = factory.createEntityManager();
		em.getTransaction().begin();
		System.out.println("before persist");
		em.persist(blog);
		System.out.println("after persist");
		em.getTransaction().commit();
		em.close();	
    }
	
	@SuppressWarnings("unchecked")
	private List<Blogpost> fetchBlogDetails(int userId){
		em = factory.createEntityManager();
		Query queryUserByUserName = em.createQuery(
			    "SELECT OBJECT(blogpost) FROM Blogpost blogpost WHERE blogpost.userId = :userId"
			);
		queryUserByUserName.setParameter("userId", userId);
		return queryUserByUserName.getResultList();
	}
	
	
	public List<Blogpost> fetchAllBlog(){
		List<Blogpost> blogs = new ArrayList<Blogpost>();
		em = factory.createEntityManager();
		System.out.println("Before Query");
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
		System.out.println("In TempUpdate Blog");
		System.out.println("Blog Id" + blog.getId());
		System.out.println(blog.getUserId());
		System.out.println(blog.getContent());		
		updateBlog(blog);
		Blogpost updatedBlog  = fetchBlogInfo(blog.getId());
		System.out.println("After Update");
		System.out.println(blog.getId());
		System.out.println(blog.getFavourite());
		return updatedBlog;
	}
	
	
	public static void main(String[] args) {
		
		//blog.setContent("Hi. This is my first blog post");
		//blog.setDate(new Date());
		//blog.setTitle("First Blogpost");
		//blog.setUserId(1);
		//bDao.createblog(blog);
		BlogDao bDao = new BlogDao();
		List<Blogpost> blogs = new ArrayList<Blogpost>();
		BlogBean bean = new BlogBean();
		
		//bDao.deleteBlog(36);
		
		blogs = bDao.fetchAllBlog();
		//right now we are fetching the blog only for the user
		for(Blogpost b : blogs){
			System.out.println(b.getTitle());
			System.out.println(b.getContent());
			System.out.println(b.getDate());
			System.out.println(b.getUserId());
			System.out.println(b.getId());
			System.out.println(b.getFavourite());
			/*if(b.getId() == 1)
			{
				bean.setId(b.getId());
				bean.setTitle(b.getTitle());
				bean.setUserId(b.getUserId());
				bean.setDatePosted(b.getDate());
				bean.setContent(b.getContent());
				
			}*/
		
		}
		/*Blogpost blog = new Blogpost();
		
		blog.setId(2);
		blog.setUserId(1);
		blog.setTitle("Updated Second Blog");
		blog.setContent("This is my Updated second blog content");
		bDao.updateBlog(blog);
		blog = bDao.fetchBlogInfo(2);
		
		System.out.println(blog.getContent());
		System.out.println(blog.getTitle());*/
	}
}
