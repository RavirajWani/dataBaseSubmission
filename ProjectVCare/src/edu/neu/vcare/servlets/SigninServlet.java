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
	     UserBean user = new UserBean();
	     user.setUserName(request.getParameter("un"));
	     user.setPassword(request.getParameter("pw"));
	     user.setFirstName(request.getParameter("fn"));
	     user.setLastName(request.getParameter("ln"));
	     user.setCountry(Integer.parseInt(request.getParameter("country")));
	     user.setState(Integer.parseInt(request.getParameter("state")));
	     user.setEmailId(request.getParameter("em"));
	     
	     UserController uController = new UserController();
		 user =  uController.createUser(user);		    
	     if (user.isValid())
	     {
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
		String operation = request.getParameter("operation");
		if (operation.equals("countryChange")){
			List<State> states = new ArrayList<State>();
			int countryId = Integer.parseInt(request.getParameter("id"));
			StateDao state= new StateDao();
			states = state.fetchStateList(countryId);
			Gson gson = new Gson();
			response.setContentType("application/json");
			response.getWriter().print(gson.toJson(states));
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
		    UserController controller = new UserController();
		    user = controller.updateUserDetails(user);
	        session.setAttribute("currentSessionUser",user); 
	        response.sendRedirect("home.jsp"); //logged-in page
		}
		if(operation.equals("checkUserName")){
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