package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.UserBean;
import util.DBUtil;

public class FriendDao {
	
	Connection conn = null;
	public void addFriend(String user1, String user2) {
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("insert into friends(user1,user2,friends,declined)" + 
					"values(?,?,?,?)");
			pStmt.setString(1, user1);
			pStmt.setString(2, user2);
			pStmt.setBoolean(3, false);
			pStmt.setBoolean(4, false);
			
			pStmt.executeUpdate();
			System.out.println("Friend Request Sent");
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConnection(conn);
		}
	}
	
	public void confirmFriend(String user1, String user2) {
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("UPDATE friends SET friends=?, declined=? WHERE user1=? AND user2=?");
			pStmt.setBoolean(1, true);
			pStmt.setBoolean(2, false);
			pStmt.setString(3, user1);
			pStmt.setString(4, user2);
			pStmt.executeUpdate();
			
			pStmt = conn.prepareStatement("UPDATE friends SET friends=?, declined=? WHERE user1=? AND user2=?");
			pStmt.setBoolean(1, true);
			pStmt.setBoolean(2, false);
			pStmt.setString(3, user2);
			pStmt.setString(4, user1);
			pStmt.executeUpdate();
			
			System.out.println("Friend Added");
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConnection(conn);
		}
	}
	
	public void declineFriend(String user1, String user2) {
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("delete from friends where user1=? AND user2=?");
			pStmt.setString(1, user1);
			pStmt.setString(2, user2);
			pStmt.executeUpdate();
			
			pStmt = conn.prepareStatement("delete from friends where user1=? AND user2=?");
			pStmt.setString(1, user2);
			pStmt.setString(2, user1);
			pStmt.executeUpdate();
			
			System.out.println("Friend Added");
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConnection(conn);
		}
	}
	
	public boolean[] isFriend (String user1, String user2) {
		boolean[] isFriend = {false,false};
		boolean reqAlreadySent = false;
		
		try {
			conn = DBUtil.getConnection();
			
			// Checking the friends table if both user1 and user2 are found in the same record and are already friends
			PreparedStatement pStmt = conn.prepareStatement("select * from friends where ((user1=? AND user2=?) OR (user1=? AND user2=?)) AND friends=true AND declined=false");
			pStmt.setString(1, user1);
			pStmt.setString(2, user2);
			pStmt.setString(3, user2);
			pStmt.setString(4, user1);
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				isFriend[0] = true;
			}
			
			// Checking to see if there is a pending user friend request
			pStmt = conn.prepareStatement("select * from friends where ((user1=? AND user2=?) OR (user1=? AND user2=?)) AND friends=false AND declined=false");
			pStmt.setString(1, user1);
			pStmt.setString(2, user2);
			pStmt.setString(3, user2);
			pStmt.setString(4, user1);
			ResultSet rs2 = pStmt.executeQuery();
			
			if (rs2.next()) {
				isFriend[1] = true;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConnection(conn);
		}
		
		return isFriend;
	}
	
	public boolean friendExists(String username) {
		boolean exists = false;
		
		try {
			conn = DBUtil.getConnection();
			// Checking the users table if the username exists
			PreparedStatement pStmt = conn.prepareStatement("select * from users where username=?");
			pStmt.setString(1, username);
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				exists = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		
		return exists;
	}
	
	public ArrayList<UserBean> friendList (String username){
		ArrayList<UserBean> friends = new ArrayList<UserBean>();
		UserDao userDao = new UserDao();
		
		try {
			conn = DBUtil.getConnection();
			// Checking the users table if the username exists
			PreparedStatement pStmt = conn.prepareStatement("select * from friends where user1=? AND friends=1");
			pStmt.setString(1, username);
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				String user2 = rs.getString("user2");
				UserBean user = userDao.getUser(user2);
				friends.add(user);
			}
			
			pStmt = conn.prepareStatement("select * from friends where user2=? AND friends=1");
			pStmt.setString(1, username);
			rs = pStmt.executeQuery();
			
			while (rs.next()) {
				String user1 = rs.getString("user1");
				UserBean user = userDao.getUser(user1);
				friends.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		
		return friends;
	}
	
	public ArrayList<UserBean> friendRequests (String username){
		ArrayList<UserBean> friends = new ArrayList<UserBean>();
		UserDao userDao = new UserDao();
		
		try {
			conn = DBUtil.getConnection();
			// Checking the users table if the username exists
			PreparedStatement pStmt = conn.prepareStatement("select * from friends where user2=? AND friends=0 AND declined=0");
			pStmt.setString(1, username);
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				String user1 = rs.getString("user1");
				UserBean user = userDao.getUser(user1);
				friends.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		
		return friends;
	}
	
	public ArrayList<UserBean> pendingRequests (String username){
		ArrayList<UserBean> friends = new ArrayList<UserBean>();
		UserDao userDao = new UserDao();
		
		try {
			conn = DBUtil.getConnection();
			// Checking the users table if the username exists
			PreparedStatement pStmt = conn.prepareStatement("select * from friends where user1=? AND friends=0 AND declined=0");
			pStmt.setString(1, username);
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				String user2 = rs.getString("user2");
				UserBean user = userDao.getUser(user2);
				friends.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		
		return friends;
	}
	
}
