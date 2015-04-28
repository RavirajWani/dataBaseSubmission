package edu.neu.vcare.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.neu.vcare.bean.UserBean;
import edu.neu.vcare.controller.UserController;

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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{	    
		     UserBean user = new UserBean();
		     user.setUserName(request.getParameter("un"));
		     user.setPassword(request.getParameter("pw"));
		     UserController ucontroller = new UserController();
			 user =  ucontroller.validateUser(user);		    
		     if (user.isValid())
		     {
		          HttpSession session = request.getSession(true);	    
		          session.setAttribute("currentSessionUser",user); 
		          session.setAttribute("blogCount", 0);
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

}