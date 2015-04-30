package edu.neu.vcare.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ViewInitiative
 */
@WebServlet("/ViewInitiative")
public class ViewInitiative extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewInitiative() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String)request.getParameter("id");
		String content = (String)request.getParameter("content");
		String title = (String)request.getParameter("title");
		String imgURL = (String)request.getParameter("imgurl");
		HttpSession session = request.getSession(true);
		session.setAttribute("id", id);
		session.setAttribute("content", content);
		session.setAttribute("title", title);
		session.setAttribute("imgURL", imgURL);
		response.sendRedirect("viewInitiative.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
