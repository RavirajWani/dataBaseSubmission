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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{	  
			 BlogDC bDc = new BlogDC();
		     HttpSession session = request.getSession(true);
		     UserBean user = new UserBean();
		     user = (UserBean) session.getAttribute("currentSessionUser");
		     
		     BlogBean blog = new BlogBean();
		     blog.setContent(request.getParameter("content"));
		     blog.setDatePosted(new Date());
		     blog.setUserId(user.getId());
		     blog.setTitle(request.getParameter("title"));

		     List<BlogBean> blogbeans = new ArrayList<BlogBean>();
		     
		     blogbeans = bDc.createBlogPost(blog);
		     session.setAttribute("blogCount", blogbeans.size());
		     session.setAttribute("blogs",blogbeans);
		     response.sendRedirect("home.jsp"); 
		} 
		catch (Throwable theException) 	    
		{
		     System.out.println(theException); 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BlogDC bdc = new BlogDC();
		if(request.getParameter("operation").equals("submit")){
			HttpSession session = request.getSession(true);
			UserBean user = new UserBean();
			user = (UserBean) session.getAttribute("currentSessionUser");
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
			bdc.deleteBlog(Integer.parseInt(request.getParameter("bid")));
			response.sendRedirect("home.jsp");
		}
	}
	
}
