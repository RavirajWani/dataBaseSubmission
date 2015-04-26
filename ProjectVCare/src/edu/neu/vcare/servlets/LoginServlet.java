package edu.neu.vcare.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.neu.vcare.bean.UserBean;
import edu.neu.vcare.dao.UserDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{	    
			
			System.out.println("In Servlet");
		     UserBean user = new UserBean();
		     user.setUserName(request.getParameter("un"));
		     user.setPassword(request.getParameter("pw"));
		     System.out.println("after User");
		     UserDao userDao = new UserDao();
			 user =  userDao.validateUser(user);		    
		     if (user.isValid())
		     {
			      System.out.println("Adter Call");
		          HttpSession session = request.getSession(true);	    
		          session.setAttribute("currentSessionUser",user); 
		          session.setAttribute("blogCount", 0);
				  //session.setAttribute("blogs",null);
		          response.sendRedirect("home.jsp"); //logged-in page      		
		     }
			        
		     else 
		          response.sendRedirect("Error.jsp"); //error page 
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
	}

}
