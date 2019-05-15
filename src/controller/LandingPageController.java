package controller;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/LandingPageController")
public class LandingPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LandingPageController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		BetBean bet = (BetBean) session.getAttribute("bet");

		UserDao userData = new UserDao();
		BetDao betData = new BetDao();
		FriendDao friend = new FriendDao();
		List<BetBean> bets; 
		
		session.setAttribute("user", user);
		String action = request.getParameter("action");
		request.setAttribute("action", action);

		if (action.equals("Create Bet")) {
			ArrayList<UserBean> friendList = friend.friendList(user.getUserName());
			session.setAttribute("friendList", friendList);
			getServletContext().getRequestDispatcher("/makeBet.jsp").forward(request, response);
			return;
		}
		
		else if (action.equals("Accept")) {
			int betId = Integer.parseInt(request.getParameter("betId"));
			System.out.println(betId);
			bet = new BetBean();
			bet = betData.getBet(betId);
			if (bet.getOpponent().getUserName().equals(user.getUserName())) {
				bet.setAccepted(true);
				betData.acceptBet(bet);

			} else {
				System.out.println("Creator cannot accept bet");

			}
			bets= betData.getBets(user);
			session.setAttribute("bets", bets);
			getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
			return;
		}

		else if (action.equals("Confirm Bet")) {
			int betId = Integer.parseInt(request.getParameter("selected"));
			bet = new BetBean();
			System.out.println(betId);
			bet = betData.getBet(betId);
			if(bet.isAccepted()) {
				boolean isConfirmed = true;
				request.setAttribute("isConfirmed", isConfirmed);
				getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
				return;
			}
			
			session.setAttribute("bet", bet);
			getServletContext().getRequestDispatcher("/makeBet.jsp").forward(request, response);
			return;
		}

		else if (action.equals("Send Bet")) {
			UserBean opponent = userData.getUser(request.getParameter("opponent"));
			bet = new BetBean();
			bet.setCreator(user);
			bet.setOpponent(opponent);
			bet.setDesc(request.getParameter("desc"));
			bet.setWager(request.getParameter("wager"));
			bet.setCreatorOption(request.getParameter("cc"));
			bet.setOpponentOption(request.getParameter("oc"));
			bet.setWager(request.getParameter("wager"));
			bet.setAccepted(false);
			betData.addBet(bet);
		}

		else if (action.equals("Reject")) {
			int betId = Integer.parseInt(request.getParameter("betId"));
			bet = new BetBean();
			bet = betData.getBet(betId);
			betData.deleteBet(bet.getBetId());

		}

		else if (action.equals("Update/Counter")) {
			int betId = Integer.parseInt(request.getParameter("betId"));
			bet = new BetBean();
			bet = betData.getBet(betId);
			if (bet.getOpponent().getUserName().equals(user.getUserName())) {
				bet.setDesc(request.getParameter("desc"));
				bet.setWager(request.getParameter("wager"));
				bet.setCreatorOption(request.getParameter("cc"));
				bet.setOpponentOption(request.getParameter("oc"));
				bet.setCreator(userData.getUser(request.getParameter("opponent")));
				bet.setOpponent(userData.getUser(request.getParameter("creator")));
				betData.opponentUpdateBet(bet);

			} else {
				bet.setDesc(request.getParameter("desc"));
				bet.setWager(request.getParameter("wager"));
				bet.setCreatorOption(request.getParameter("cc"));
				bet.setOpponentOption(request.getParameter("oc"));
				betData.creatorUpdateBet(bet);

			}

		} else if(action.equals("Who Won")) { 
			
			int betId = Integer.parseInt(request.getParameter("selected"));
			
			bet = betData.getBet(betId);
			System.out.println(bet.getWinner().getUserName());
			if(!bet.isAccepted()) {
				System.out.println("bet has not been accpted");
				boolean isAccepted = true;
				request.setAttribute("isAccepted", isAccepted);
				getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
				return;
			}try {
			if(bet.getWinner().getUserName()!=null) {
				System.out.println("Winner has been assigned");
				boolean isWinnerDecided=true;
				request.setAttribute("isWinnerDecided", isWinnerDecided);
				getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
				System.out.println("in if");
				return;
				
			}
			}catch(NullPointerException e) {
				session.setAttribute("bet", bet);
				getServletContext().getRequestDispatcher("/makeBet.jsp").forward(request, response);
				System.out.println("in catch");
				return;
			}
			session.setAttribute("bet", bet);
			getServletContext().getRequestDispatcher("/makeBet.jsp").forward(request, response);
			System.out.println("in other");
			return;
		
		} else if(action.equals("winner")){
			int betId = Integer.parseInt(request.getParameter("betId"));
			bet = betData.getBet(betId);
			
			String player = request.getParameter("player");
			
			if(player.equals(bet.getCreator().getUserName())) {
				String winner =bet.getCreator().getUserName();
				String loser = bet.getOpponent().getUserName();
				bet.setWinner(userData.getUser(winner));
				bet.setLoser(userData.getUser(loser));
				
				UserBean winningUser = userData.getUser(winner);
				UserBean losingUser = userData.getUser(loser);
				winningUser.setWins(winningUser.getWins()+1);
				losingUser.setLoses(losingUser.getLoses()+1);
				System.out.println(winningUser.getWins());
				System.out.println(winningUser.getLoses());
				
			
				winningUser.setWinRatio();	
				losingUser.setWinRatio();	
				
				
				
				betData.setWinnerLoser(bet);
				userData.updateRecord(losingUser);
				userData.updateRecord(winningUser);
				System.out.println("creator won");
			}
			else {
				String loser =bet.getCreator().getUserName();
				String winner = bet.getOpponent().getUserName();
				bet.setLoser(userData.getUser(loser));
				bet.setWinner(userData.getUser(winner));
				UserBean winningUser = userData.getUser(winner);
				UserBean losingUser = userData.getUser(loser);
				
				winningUser.setWins(winningUser.getWins()+1);
				losingUser.setLoses(losingUser.getLoses()+1);
				
				
				winningUser.setWinRatio();	
				
				
				
				
				losingUser.setWinRatio();
				betData.setWinnerLoser(bet);
				userData.updateRecord(losingUser);
				userData.updateRecord(winningUser);
				System.out.println("opponent won");
			}
	
		}
		
		else {
			System.out.println("not found");
		}
		ArrayList<UserBean> users = userData.getUsers();
		bets= betData.getBets(user);
		user=userData.getUser(user.getUserName());
		session.setAttribute("bets", bets);
		session.setAttribute("users", users);
		session.setAttribute("user", user);
		getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);

	}

}
