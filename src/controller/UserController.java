package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.BetDao;
import DAO.FriendDao;
import DAO.UserDao;
import model.BetBean;
import model.UserBean;


@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserController() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String action = request.getParameter("action");

		if (action.equals("Login")) {
			String username = request.getParameter("username");
			String pw = request.getParameter("pw");
			UserDao userData = new UserDao();
			FriendDao friend = new FriendDao();
			boolean valid = userData.canUserLogin(username, pw);
			if (valid) {
				UserBean user = userData.getUser(username);
				BetDao betData = new BetDao();
				List<BetBean> bets = betData.getBets(user);
				int size= bets.size();
				session.setAttribute("size", size);
				ArrayList<UserBean> users = userData.getUsers();
				ArrayList<UserBean> friendList = friend.friendList(username);
				session.setAttribute("friendList", friendList);
				session.setAttribute("users", users);
				session.setAttribute("bets", bets);
				session.setAttribute("user", user);
				session.setAttribute("wrongCredentials", false);
				getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
			} else {
				session.setAttribute("wrongCredentials", true);
				response.sendRedirect("login.jsp");
				System.out.println("incorrect username and/or password");
			}
		}
		else if(action.equals("Sign Up")) {
			UserBean user = new UserBean();
			UserDao userData = new UserDao();
			
			user.setUserName(request.getParameter("username"));
			user.setPassword(request.getParameter("pw"));
			user.setFirstName(request.getParameter("firstname"));
			user.setLastName(request.getParameter("lastname"));
		
			user.setEmail(request.getParameter("email"));
			
			userData.addUser(user);
			if(!user.isValidUsername()) {
				session.setAttribute("userCreate", user);
				session.setAttribute("userExists", true);
				getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
			}else {
			
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
		else if (action.equals("Sign-Out")) {
            session.removeAttribute("bets");
            session.removeAttribute("user");
            session.removeAttribute("users");
            session.removeAttribute("friendList");
            session.invalidate();
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
		
		else if (action.equals("changePassword")) {
            getServletContext().getRequestDispatcher("/changePassword.jsp").forward(request, response);
        }

        if (action.equals("Confirm New Password")) {
            String oldPassword = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");
            UserBean u = (UserBean) session.getAttribute("user");
            UserDao user = new UserDao();
            String userCurrentPassword = u.getPassword();
            if (!userCurrentPassword.equals(oldPassword)) {
                session.setAttribute("matchesOldPassword", false);
                getServletContext().getRequestDispatcher("/changePassword.jsp").forward(request, response);
            }else if (!newPassword.equals(confirmPassword)) {
                session.setAttribute("newPasswordMatches", false);
                getServletContext().getRequestDispatcher("/changePassword.jsp").forward(request, response);
            }else {
                user.changePassword(u, newPassword);
                getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);

            }
        }
		
	}
	

}
