package edu.neu.vcare.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.neu.vcare.bean.BlogBean;
import edu.neu.vcare.bean.UserBean;
import edu.neu.vcare.controller.BlogDC;
import edu.neu.vcare.dao.BlogDao;

/**
 * Servlet implementation class BlogpostServlet
 */
@WebServlet("/BlogpostServlet")
public class BlogpostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogpostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{	    
			
			 System.out.println("In BlogPost Servlet");
		     HttpSession session = request.getSession(true);
		     UserBean user = new UserBean();
		     user = (UserBean) session.getAttribute("currentSessionUser");
		     System.out.println(user.getFirstName());
		     System.out.println(user.getId());
		     
		     BlogBean blog = new BlogBean();
		     blog.setContent(request.getParameter("content"));
		     blog.setDatePosted(new Date());
		     blog.setUserId(user.getId());
		     blog.setTitle(request.getParameter("title"));
		     
		     BlogDao bDao = new BlogDao();
		     
		     List<BlogBean> blogbeans = new ArrayList<BlogBean>();
		     
		     blogbeans = bDao.createBlogPost(blog);
		     System.out.println("Blogs Number for user" + blogbeans.size());
		     
		     session.setAttribute("blogCount", blogbeans.size());
		     session.setAttribute("blogs",blogbeans);
		     for (BlogBean b : blogbeans){
		    	 System.out.println("in blogservlet" + b.getContent());
		    	 System.out.println("in blogservlet" + b.getTitle());
		    	 System.out.println("in blogservlet" +  b.getDatePosted());
		     }
		     response.sendRedirect("home.jsp"); 
		     
	     //set in session
		   //  UserDao userDao = new UserDao();
		/*	 user =  userDao.validateUser(user);		    
		     if (user.isValid())
		     {
			      System.out.println("After Call");
		          HttpSession session = request.getSession(true);	    
		          session.setAttribute("currentSessionUser",user); 
		          response.sendRedirect("home.jsp"); //logged-in page      		
		     }
			        
		     else 
		          response.sendRedirect("Error.jsp"); //error page */
		} 
				
				
		catch (Throwable theException) 	    
		{
		     System.out.println(theException); 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("In Post Method of the servlet");
		System.out.println("Blog Id: " + request.getParameter("bid"));
		System.out.println("Blog Content: " + request.getParameter("updatedContent"));
		System.out.println(request.getParameter("operation"));
		System.out.println("Favourite" + request.getParameter("favourite"));
		
		BlogDC bdc = new BlogDC();
		if(request.getParameter("operation").equals("submit")){
			HttpSession session = request.getSession(true);
			UserBean user = new UserBean();
			user = (UserBean) session.getAttribute("currentSessionUser");
			System.out.println("Like from Servlet" + request.getParameter("like"));	
			System.out.println("Favourite" + (Integer.parseInt(request.getParameter("favourite"))));
			BlogBean blog = new BlogBean();
			blog.setId(Integer.parseInt(request.getParameter("bid")));
			blog.setContent(request.getParameter("updatedContent"));
			blog.setDatePosted(new Date());
			blog.setUserId(Integer.parseInt(request.getParameter("userId")));
			blog.setTitle(request.getParameter("title"));
			if("like".equals((String)request.getParameter("like"))){
				blog.setFavourite((Integer.parseInt(request.getParameter("favourite")) + 1));
			}
			else{
			blog.setFavourite(Integer.parseInt(request.getParameter("favourite")));
			}
			//blog.setFavourite(favourite);
			BlogBean bean = bdc.updateBlog(blog);
			session.setAttribute("title",bean.getTitle());
			session.setAttribute("id", bean.getId());
			session.setAttribute("content", bean.getContent());
			session.setAttribute("date", bean.getDatePosted());
			session.setAttribute("like", bean.getFavourite());
			session.setAttribute("userId", bean.getUserId());
			response.sendRedirect("viewBlogDetails.jsp");
		}
		else{
			System.out.println("For deletion of blog");
			bdc.deleteBlog(Integer.parseInt(request.getParameter("bid")));
			response.sendRedirect("home.jsp");
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		
		System.out.println("In delete Method of the servlet");
		System.out.println("Blog Id: " + request.getParameter("bid"));
		System.out.println("Blog Content: " + request.getParameter("updatedContent"));
		
			
		
	}
	
}
