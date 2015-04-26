package edu.neu.vcare.controller;

import java.util.ArrayList;
import java.util.List;

import edu.neu.vcare.bean.BlogBean;
import edu.neu.vcare.dao.BlogDao;
import edu.neu.vcare.entity.Blogpost;

public class BlogDC {
	
	
	
	public List<Blogpost> fetchAllBlog(int userId){
		BlogDao bDao = new BlogDao();
		List<Blogpost> allBlogs = new ArrayList<Blogpost>();
		allBlogs = bDao.fetchAllBlog();
		List<Blogpost> usersBlogs = new ArrayList<Blogpost>();
		for(Blogpost b : allBlogs){
			if(b.getUserId() == userId){
				usersBlogs.add(b);
			}
		}
		allBlogs.removeAll(usersBlogs);
		return allBlogs;
	}
	
	public List<Blogpost> fetchUserBlog(int userId){
		BlogDao bDao = new BlogDao();
		List<Blogpost> allBlogs = new ArrayList<Blogpost>();
		allBlogs = bDao.fetchAllBlog();
		List<Blogpost> usersBlogs = new ArrayList<Blogpost>();
		for(Blogpost b : allBlogs){
			if(b.getUserId() == userId){
				usersBlogs.add(b);
			}
		}
		allBlogs.removeAll(usersBlogs);
		return usersBlogs;
	}
	
	public BlogBean updateBlog(BlogBean blog){
		BlogDao bDao = new BlogDao();
		
		//conversion from BlogBean to BlogPost
		Blogpost blogpost = new Blogpost();
		blogpost.setContent(blog.getContent());
		blogpost.setDate(blog.getDatePosted());
		blogpost.setId(blog.getId());
		blogpost.setTitle(blog.getTitle());
		blogpost.setUserId(blog.getUserId());
		blogpost.setFavourite(blog.getFavourite());
		
		//calling update method og blogDao
		Blogpost updatedBlog = bDao.TempUpdateBlog(blogpost);
		BlogBean bean = new BlogBean();
		bean.setContent(updatedBlog.getContent());
		bean.setDatePosted(updatedBlog.getDate());
		bean.setId(updatedBlog.getId());
		bean.setTitle(updatedBlog.getTitle());
		bean.setUserId(updatedBlog.getUserId());
		bean.setFavourite(updatedBlog.getFavourite());
		return bean;
	}
	
	public void deleteBlog(int blogId){
		BlogDao bDao = new BlogDao();
		bDao.deleteBlog(blogId);
	}
}
