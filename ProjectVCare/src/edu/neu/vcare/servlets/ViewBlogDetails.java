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
import com.google.gson.Gson;
import edu.neu.vcare.bean.CommentBean;
import edu.neu.vcare.bean.UserBean;
import edu.neu.vcare.controller.CommentController;

/**
 * Servlet implementation class ViewBLogDetails
 */
@WebServlet("/ViewBlogDetails")
public class ViewBlogDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBlogDetails() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String)request.getParameter("id");
		String content = (String)request.getParameter("content");
		String title = (String)request.getParameter("title");
		String date = (String)request.getParameter("date");
		int favourite = (Integer.parseInt((String)request.getParameter("like")));
		int userId = (Integer.parseInt((String)request.getParameter("userId")));
		List<CommentBean> commentbeans = new ArrayList<CommentBean>();
		CommentController control = new CommentController();
		commentbeans = control.fetchComment(Integer.parseInt(id));
		HttpSession session = request.getSession(true);
		session.setAttribute("id", id);
		session.setAttribute("content", content);
		session.setAttribute("title", title);
		session.setAttribute("date", date);
		session.setAttribute("comments",commentbeans);
		session.setAttribute("like", favourite);
		session.setAttribute("userId", userId);
        response.sendRedirect("viewBlogDetails.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
	    UserBean user = new UserBean();
	    user = (UserBean) session.getAttribute("currentSessionUser");
		CommentBean comm = new CommentBean();
		comm.setContent(request.getParameter("content"));
		comm.setDate( new Date());
		comm.setBlogId(Integer.parseInt(request.getParameter("id")));
		comm.setUserId(user.getId());
		CommentController controller = new CommentController();
		List<CommentBean> comments = new ArrayList<CommentBean>();
		comments = controller.createComment(comm);
		Gson gson = new Gson();
		response.setContentType("application/json");
        response.getWriter().print(gson.toJson(comments));
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		CommentBean comm = new CommentBean();
		String commentid = request.getParameter("id");
		int index = commentid.indexOf("t");
		String blogindex = commentid.substring(index+1);
		comm.setId(Integer.parseInt(blogindex));
		comm.setBlogId(Integer.parseInt(request.getParameter("bid")));
		CommentController controller = new CommentController();
		List<CommentBean> comments = new ArrayList<CommentBean>();
		comments = controller.deleteComment(comm);
		Gson gson = new Gson();
		response.setContentType("application/json");
        response.getWriter().print(gson.toJson(comments)); 
	}

}