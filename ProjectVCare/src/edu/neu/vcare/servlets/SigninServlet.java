package edu.neu.vcare.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import edu.neu.vcare.bean.UserBean;
import edu.neu.vcare.controller.UserController;
import edu.neu.vcare.dao.StateDao;
import edu.neu.vcare.dao.UserDao;
import edu.neu.vcare.entity.State;

/**
 * Servlet implementation class SigninServlet
 */
@WebServlet("/SigninServlet")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SigninServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In Signing Servlet");
	     UserBean user = new UserBean();
	     user.setUserName(request.getParameter("un"));
	     user.setPassword(request.getParameter("pw"));
	     user.setFirstName(request.getParameter("fn"));
	     user.setLastName(request.getParameter("ln"));
	     user.setCountry(Integer.parseInt(request.getParameter("country")));
	     user.setState(Integer.parseInt(request.getParameter("state")));
	     user.setEmailId(request.getParameter("em"));
	     System.out.println(request.getParameter("country"));
	     System.out.println(request.getParameter("state"));
	     System.out.println("after User");
	     UserDao userDao = new UserDao();
		 user =  userDao.createUser(user);		    
	     if (user.isValid())
	     {
		      System.out.println("Adter Call");
	          HttpSession session = request.getSession(true);	    
	          session.setAttribute("currentSessionUser",user); 
	          response.sendRedirect("home.jsp"); //logged-in page      		
	     }
		        
	     else 
	          response.sendRedirect("Error.jsp"); //error page 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("In Do pOst..");
		
		String operation = request.getParameter("operation");
		if (operation.equals("countryChange")){
		
		List<State> states = new ArrayList<State>();
		int countryId = Integer.parseInt(request.getParameter("id"));
		StateDao state= new StateDao();
		states = state.fetchStateList(countryId);
		for (State s : states)
		{
			System.out.println(s.getId());
			System.out.println(s.getName());
		}
		System.out.println("Before sessin set");
		
		//HttpSession session = request.getSession(true);	    
        //session.setAttribute("stateList",states); 
		Gson gson = new Gson();
		response.setContentType("application/json");
        response.getWriter().print(gson.toJson(states));
       //request.setAttribute("stateList", states);   
        System.out.println("Before Redirect");
       // response.sendRedirect("signin.jsp");
		}
		if (operation.equals("editUserProfile")){
			HttpSession session = request.getSession(true);	    
	        UserBean userInfo = (UserBean)session.getAttribute("currentSessionUser"); 
			UserBean user = new UserBean();
			user.setId(userInfo.getId());
		    user.setUserName(request.getParameter("un"));
		    user.setPassword(request.getParameter("pw"));
		    user.setFirstName(request.getParameter("fn"));
		    user.setLastName(request.getParameter("ln"));
		    user.setCountry(Integer.parseInt(request.getParameter("country")));
		    user.setState(Integer.parseInt(request.getParameter("state")));
		    user.setEmailId(request.getParameter("em"));
		    System.out.println(user.getUsername());
		    System.out.println(user.getFirstName());
		    System.out.println(user.getState());
		    System.out.println(user.getId());
		    System.out.println(user.getPassword());
		    System.out.println(user.getEmailId());
		    UserController controller = new UserController();
		    user = controller.updateUserDetails(user);
	        session.setAttribute("currentSessionUser",user); 
	        response.sendRedirect("home.jsp"); //logged-in page
		}
		if(operation.equals("checkUserName")){
			System.out.println("In Servlet to check userName availability");
			String userName = (String)request.getParameter("uname");
			UserController controller = new UserController();
			Boolean isPresent = controller.fetchUserName(userName);
			String msg = null;
			if(isPresent){
				 msg = "Username not available. Select another username";
			}
			else
			{
				msg = "Username is available";
			}
			PrintWriter out = response.getWriter();
			out.print(msg);
		}
		
	}

}
