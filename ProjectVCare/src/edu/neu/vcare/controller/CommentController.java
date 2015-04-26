package edu.neu.vcare.controller;

import java.util.ArrayList;
import java.util.List;

import edu.neu.vcare.bean.CommentBean;
import edu.neu.vcare.dao.CommentDao;
import edu.neu.vcare.dao.UserDao;
import edu.neu.vcare.entity.Comment;
import edu.neu.vcare.entity.User;

public class CommentController {
	
	
	public List<CommentBean> fetchComment(int blogId){
		CommentDao cDao = new CommentDao();
		List<Comment> blogComments = new ArrayList<Comment>();
		blogComments = cDao.fetchComment(blogId);
		
		List<CommentBean> comments = new ArrayList<CommentBean>();
		for(Comment bean : blogComments)
		{
			CommentBean comment = new CommentBean();
			System.out.println("Comment Id in Comment Controller : " + bean.getId());
			comment.setId(bean.getId());
			comment.setBlogId(bean.getBlogId());
			comment.setContent(bean.getContent());
			comment.setDate(bean.getDate());
			comment.setUserId(bean.getUserId());
			comment.setUserName(fetchUserName(bean.getUserId()));
			comments.add(comment);
		}
		return comments;
	}
	
	private String fetchUserName(int userId){
		UserDao uDao = new UserDao();
		User user = uDao.fetchUserById(userId);
		System.out.println(user.getFirstName());
		System.out.println(user.getLastName());
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		
		return (user.getFirstName() + user.getLastName());
		//return "hi";
	}
	
	public static void main(String[] args) {
		CommentController con = new CommentController();
		System.out.println(con.fetchUserName(1));
	}
	
	public List<CommentBean> createComment(CommentBean comment){
		Comment comm = new Comment();
		comm.setBlogId(comment.getBlogId());
		comm.setContent(comment.getContent());
		comm.setDate(comment.getDate());
		comm.setUserId(comment.getUserId());
		
		CommentDao cDao = new CommentDao();
		cDao.createComment(comm);
		List<Comment> blogComments = new ArrayList<Comment>();
		blogComments = cDao.fetchComment(comment.getBlogId());
		List<CommentBean> comments = new ArrayList<CommentBean>();
		for(Comment bean : blogComments)
		{
			CommentBean commBean = new CommentBean();
			System.out.println("Comment Id in Comment Controller : " + bean.getId());
			commBean.setId(bean.getId());
			commBean.setBlogId(bean.getBlogId());
			commBean.setContent(bean.getContent());
			commBean.setDate(bean.getDate());
			commBean.setUserId(bean.getUserId());
			commBean.setUserName(fetchUserName(bean.getUserId()));
			comments.add(commBean);
		}
		return comments;
	}

	public List<CommentBean> deleteComment(CommentBean comment){
		CommentDao cDao = new CommentDao();
		cDao.deleteComment(comment.getId());
		List<Comment> blogComments = new ArrayList<Comment>();
		blogComments = cDao.fetchComment(comment.getBlogId());
		List<CommentBean> comments = new ArrayList<CommentBean>();
		for(Comment bean : blogComments)
		{
			CommentBean commBean = new CommentBean();
			System.out.println("Comment Id in Comment Controller : " + bean.getId());
			commBean.setId(bean.getId());
			commBean.setBlogId(bean.getBlogId());
			commBean.setContent(bean.getContent());
			commBean.setDate(bean.getDate());
			commBean.setUserId(bean.getUserId());
			commBean.setUserName(fetchUserName(bean.getUserId()));
			comments.add(commBean);
		}
		return comments;
	}
}
