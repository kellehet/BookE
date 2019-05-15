package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.UserBean;
import model.BetBean;
import util.DBUtil;

public class BetDao {
	Connection conn = null;

	public void addBet(BetBean bet) {
		Connection conn = DBUtil.getConnection();
		try {
			PreparedStatement pStmt = conn.prepareStatement(
					"insert into bet(creator,challenger,description, wager, accepted, creatorsOption ,opponentsOption)" + " values(?,?,?,?,?,?,?)");
			pStmt.setString(1, bet.getCreator().getUserName());
			pStmt.setString(2, bet.getOpponent().getUserName());
			pStmt.setString(3, bet.getDesc());
			pStmt.setString(4, bet.getWager());
			pStmt.setBoolean(5, bet.isAccepted());
			pStmt.setString(6, bet.getCreatorOption());
			pStmt.setString(7, bet.getOpponentOption());
			pStmt.executeUpdate();
			System.out.println("bet added");
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}

	}

	public BetBean getBet(int betId) {
		UserDao userData = new UserDao();
		Connection conn = DBUtil.getConnection();
		BetBean bet = new BetBean();
		try {

			PreparedStatement pStmt = conn.prepareStatement("select*from bet where betId = ?");
			pStmt.setInt(1, betId);

			ResultSet results = pStmt.executeQuery();
			while (results.next()) {
				bet.setBetId(results.getInt("betId"));
				bet.setCreator(userData.getUser(results.getString("creator")));
				bet.setOpponent(userData.getUser(results.getString("challenger")));
				bet.setDesc(results.getString("description"));
				bet.setWager(results.getString("wager"));
				bet.setWinner(userData.getUser(results.getString("winner")));
				bet.setWinner(userData.getUser(results.getString("loser")));
				bet.setAccepted(results.getBoolean("accepted"));
				bet.setCreatorOption(results.getString("creatorsOption"));
				bet.setOpponentOption(results.getString("opponentsOption"));
			}
		} catch (SQLException e) {

		} finally {
			DBUtil.closeConnection(conn);
		}
		return bet;
	}

	public List<BetBean> getBets(UserBean user) {
		List<BetBean> bets = new ArrayList<BetBean>();
		Connection conn = DBUtil.getConnection();
		UserDao userData = new UserDao();
		userData.getUser(user.getUserName());
		try {

			PreparedStatement pStmt = conn.prepareStatement("select*from bet where creator = ? or challenger = ?");
			pStmt.setString(1, user.getUserName());
			pStmt.setString(2, user.getUserName());
			ResultSet results = pStmt.executeQuery();
			while (results.next()) {
				BetBean bet = new BetBean();
				bet.setBetId(results.getInt("betId"));
				bet.setCreator(userData.getUser(results.getString("creator")));
				bet.setOpponent(userData.getUser(results.getString("challenger")));
				bet.setLoser(userData.getUser(results.getString("loser")));
				bet.setWinner(userData.getUser(results.getString("winner")));
				bet.setDesc(results.getString("description"));
				bet.setWager(results.getString("wager"));
				bet.setCreatorOption(results.getString("creatorsOption"));
				bet.setOpponentOption(results.getString("opponentsOption"));
				bet.setAccepted(results.getBoolean("accepted"));
				bets.add(bet);
			}
		} catch (SQLException e) {

		} finally {
			DBUtil.closeConnection(conn);
		}
		return bets;
	}

	public void deleteBet(int betId) {
		Connection conn = DBUtil.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("delete from bet where betId = ?");
			pstmt.setInt(1, betId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public void creatorUpdateBet(BetBean bet) {
		Connection conn = DBUtil.getConnection();

		try {
			PreparedStatement pStmt = conn.prepareStatement(
					"update bet set creator = ?, challenger = ?, description = ?, wager = ?, accepted = ?, creatorsOption= ?,  opponentsOption= ? where betId = ?");
			pStmt.setString(1, bet.getCreator().getUserName());
			pStmt.setString(2, bet.getOpponent().getUserName());
			pStmt.setString(3, bet.getDesc());
			pStmt.setString(4, bet.getWager());
			pStmt.setBoolean(5, false);
			pStmt.setString(6, bet.getCreatorOption());
			pStmt.setString(7, bet.getOpponentOption());
			pStmt.setInt(8, bet.getBetId());
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}

	}

	public void opponentUpdateBet(BetBean bet) {
		Connection conn = DBUtil.getConnection();

		try {
			PreparedStatement pStmt = conn.prepareStatement(
					"update bet set creator = ?, challenger = ?, description = ?, wager = ?, accepted = ?, creatorsOption= ?,  opponentsOption= ? where betId = ?");
			pStmt.setString(1, bet.getCreator().getUserName());
			pStmt.setString(2, bet.getOpponent().getUserName());
			pStmt.setString(3, bet.getDesc());
			pStmt.setString(4, bet.getWager());
			pStmt.setBoolean(5, false);
			pStmt.setString(6, bet.getCreatorOption());
			pStmt.setString(7, bet.getOpponentOption());
			pStmt.setInt(8, bet.getBetId());
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public void acceptBet(BetBean bet) {
		Connection conn = DBUtil.getConnection();
		try {
			PreparedStatement pStmt = conn.prepareStatement("update bet set accepted = ? where betId = ?");
			pStmt.setBoolean(1, true);
			pStmt.setInt(2, bet.getBetId());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}
	public void setWinnerLoser(BetBean bet) {
		Connection conn = DBUtil.getConnection();
		try {
			PreparedStatement pStmt = conn.prepareStatement("update bet set winner = ?, loser = ? where betId = ?");
			pStmt.setString(1, bet.getWinner().getUserName());
			pStmt.setString(2, bet.getLoser().getUserName());
			pStmt.setInt(3, bet.getBetId());
			pStmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}
}
