package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.FriendDao;
import DAO.UserDao;
import model.UserBean;

/**
 * Servlet implementation class findFriend
 */
@WebServlet("/findFriend")
public class FindFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindFriend() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// Getting session
    			HttpSession session = request.getSession();
    			
    			// Creating a FriendDao object to access all friend relationship methods
    			FriendDao friendDao = new FriendDao();
    			
    			// Getting logged in user and friend to add names
    			// Getting username of current logged in user
    			UserBean user = (UserBean)session.getAttribute("user");
    			String loggedInUser = user.getUserName(); 
    			String requestName = (String)session.getAttribute("friendToFind");
    			
    			friendDao.addFriend(loggedInUser, requestName);
    			
    			RequestDispatcher rd = request.getRequestDispatcher("RequestSent.html");
    			rd.forward(request, response);
    }
    	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Getting session
		HttpSession session = request.getSession();
		
		// Creating a FriendDao object to access all friend relationship methods
		FriendDao friendDao = new FriendDao();
		
		// Creating String that will be passed to the next page that will be acted upon based on the String value
		String result = "";
		
		// Get parameter for the person the user is searching for
		String userToFind = request.getParameter("username");
		
		// Getting username of current logged in user
		UserBean user = (UserBean)session.getAttribute("user");
		String loggedInUser = user.getUserName();
		
		// Checking if the username entered exists
		boolean userExists = friendDao.friendExists(userToFind);
		
		if (userExists) {
			if (!(loggedInUser.equals(userToFind))) {
				// Checking if the users are already friends 
				if (friendDao.isFriend(loggedInUser, userToFind)[0]) {
					System.out.println("Already a Friend");
					result="alreadyFriends";
				}
				// Checking is a friend request is still pending
				else if (friendDao.isFriend(loggedInUser, userToFind)[1]) {
					System.out.println("Friend Request Pending");
					result="friendRequestPending";
				} else {
					System.out.println("Confirm Request");
					result="confirmRequest";
				}
				
				
			} else {
				// This result is if the user tries to add them self as a friend
				result = "sameUser";
			}
		} else {
			result="UserDoesNotExist";
		}
		
		session.setAttribute("friendResult", result);
		session.setAttribute("friendToFind", userToFind);
		RequestDispatcher rd = request.getRequestDispatcher("findFriend.jsp");
		rd.forward(request, response);
	}

}
