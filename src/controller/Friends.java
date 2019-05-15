package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.FriendDao;
import model.UserBean;

/**
 * Servlet implementation class Friends
 */
@WebServlet("/Friends")
public class Friends extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Friends() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute("user");
		String username = user.getUserName();
		FriendDao friend = new FriendDao();
		String option=" ";
		 option = request.getParameter("option");
		String friendrequest = "";
		
		try {
			friendrequest = request.getParameter("selected");
			
			if (option.equals("Remove Friend") || option.equals("Cancel") || option.equals("Decline")) {
				friend.declineFriend(username, friendrequest);
			} else if (option.equals("Accept")) {
				friend.confirmFriend(username, friendrequest);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		
			// Getting current friends
			ArrayList<UserBean> friendList = friend.friendList(username);
			session.setAttribute("friendList", friendList);
			
			// Getting Friend requests that need to be replied to
			ArrayList<UserBean> friendRequests = friend.friendRequests(username);
			session.setAttribute("friendRequests", friendRequests);
			
			// Getting requests awaiting other users response
			ArrayList<UserBean> pendingRequests = friend.pendingRequests(username);
			session.setAttribute("pendingRequests", pendingRequests);
			
			response.sendRedirect("Friends.jsp");
		}
	}

}
