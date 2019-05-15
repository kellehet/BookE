package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.UserBean;
import util.DBUtil;

public class UserDao {
	Connection conn = null;

	public void addUser(UserBean user) {
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement(
					"insert into users(username,password,firstname,lastname,email)" + "Values(?,?,?,?,?)");
			pStmt.setString(1, user.getUserName());
			pStmt.setString(2, user.getPassword());
			pStmt.setString(3, user.getFirstName());
			pStmt.setString(4, user.getLastName());
			pStmt.setString(5, user.getEmail());

			pStmt.executeUpdate();
			System.out.println("user added");

		} catch (SQLException e) {

			user.setValidUsername(false);
			System.out.println("username already in use");
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public boolean canUserLogin(String username, String password) {
		conn = DBUtil.getConnection();
		boolean valid = false;
		try {
			PreparedStatement pStat = conn.prepareStatement("select * from users where username=? and password=?");
			pStat.setString(1, username);
			pStat.setString(2, password);
			ResultSet result = pStat.executeQuery();

			if (result.next()) {
				valid = true;
			} else {
				System.out.println("fail");
				valid = false;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return valid;
	}

	public UserBean getUser(String username) {
		conn = DBUtil.getConnection();
		UserBean user = new UserBean();
		try {
			PreparedStatement pStat = conn.prepareStatement("select * from users where username=?");
			pStat.setString(1, username);

			ResultSet result = pStat.executeQuery();
			if (result.next()) {
				user.setUserName(result.getString("username"));
				user.setPassword(result.getString("password"));
				user.setFirstName(result.getString("firstname"));
				user.setLastName(result.getString("lastname"));
				user.setEmail(result.getString("email"));
				user.setWins(result.getInt("wins"));
				user.setLoses(result.getInt("loses"));
				user.setWinRatio();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return user;
	}

	public void updateRecord(UserBean user) {
		Connection conn = DBUtil.getConnection();
		try {
			PreparedStatement pStat = conn.prepareStatement("update users set wins = ?, loses = ?, winRatio = ? where username=?");
			pStat.setInt(1, user.getWins());
			pStat.setInt(2, user.getLoses());
			pStat.setDouble(3, user.getWinRatio());
			pStat.setString(4, user.getUserName());
			pStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public ArrayList<UserBean> getUsers() {
		Connection conn = DBUtil.getConnection();
		ArrayList<UserBean> users = new ArrayList<UserBean>();
		try {
			PreparedStatement pStat = conn.prepareStatement("select * from users order by winRatio desc limit 10");
			ResultSet result = pStat.executeQuery();
			
			while (result.next()) {
				UserBean user = new UserBean();
				user.setUserName(result.getString("username"));
				user.setPassword(result.getString("password"));
				user.setFirstName(result.getString("firstname"));
				user.setLastName(result.getString("lastname"));
				user.setEmail(result.getString("email"));
				user.setWins(result.getInt("wins"));
				user.setLoses(result.getInt("loses"));
				user.setWinRatio();
				users.add(user);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return users;

	}
	public void changePassword(UserBean user, String pass) {
        try {
            conn = DBUtil.getConnection();
            PreparedStatement pStmt = conn.prepareStatement("update users set password=? where username=?");
            pStmt.setString(1, pass);
            pStmt.setString(2, user.getUserName());
            pStmt.executeUpdate();
            System.out.println("Password Changed!");
        }catch(SQLException e) {
            user.setValidUsername(false);
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection(conn);
        }

    }
	
	
	
}
