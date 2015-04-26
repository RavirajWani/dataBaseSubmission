package edu.neu.vcare.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.neu.vcare.entity.Comment;

public class CommentDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProjectVCare");
	EntityManager em = null;
	
	@SuppressWarnings("unchecked")
	public List<Comment> fetchComment(int blogId){
		em = factory.createEntityManager();
		Query queryUserByUserName = em.createQuery(
			    "SELECT OBJECT(comment) FROM Comment comment WHERE comment.blogId = :blogId"
			);
		queryUserByUserName.setParameter("blogId", blogId);
		return queryUserByUserName.getResultList();
	}
	
	public void createComment(Comment comment){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		System.out.println("before persist");
		em.persist(comment);
		System.out.println("after persist");
		em.getTransaction().commit();
		em.close();	
	}
	
	public List<Comment> createTemporaryComments(Comment comment){
		CommentDao cDao = new CommentDao();
		cDao.createComment(comment);
		List<Comment> comments = new ArrayList<Comment>();
		comments = cDao.fetchComment(comment.getBlogId());
		for(Comment c : comments){
			System.out.println("comment id in comment DAo:" + c.getId());
			System.out.println("blog id in comment DAo:" + c.getBlogId());
			System.out.println("content in comment DAo:" + c.getContent());
		}
		
		return comments;
	}
	
	public void deleteComment(int commentId){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Comment comment = em.find(Comment.class, commentId);
		em.remove(comment);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Comment> deleteTemporaryComments(Comment comment){
		CommentDao cDao = new CommentDao();
		cDao.deleteComment(comment.getId());
		List<Comment> comments = new ArrayList<Comment>();
		comments = cDao.fetchComment(comment.getBlogId());
		return comments;
	}
	
	
	public static void main(String[] args) {
		Comment comm = new Comment();
		//comm.setBlogId(1);
		//comm.setContent("2nd Comment");
		//comm.setDate(new Date());
		CommentDao cDao = new CommentDao();
		//cDao.createComment(comm);
		List<Comment> comments = new ArrayList<Comment>();
		comments = cDao.fetchComment(1);
		for(Comment c : comments){
			System.out.println(c.getBlogId());
			System.out.println(c.getContent());
		}
		
	}
}
