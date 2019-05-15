package model;

import java.io.Serializable;
import java.util.Date;


public class UserBean implements Serializable {
	
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private double winRatio;
	private int wins = 0;
	private int loses = 0;
	private boolean validUsername = true;
	
	
	public UserBean() {}

	public UserBean( String userName, String password, String firstName, String lastName, String email, 
			double winRatio, int wins, int loses, boolean validUsername) {
		
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.winRatio=winRatio;
		this.wins = wins;
		this.loses = loses;
		this.validUsername=validUsername;	
	}

	public double getWinRatio() {
		return winRatio;
	}

	public void setWinRatio() {
		if (wins==0&&loses==0) {
			this.winRatio=0;
		}else {
			
		
        this.winRatio = this.calcWinRatio(wins, loses);
        System.out.println("Win Ratio Updated for user "+userName+" whose wins are "+wins+" Loses are "+loses+" and the win ratio is "+winRatio);
		}
    }

	public boolean isValidUsername() {
		return validUsername;
	}

	public void setValidUsername(boolean validUsername) {
		this.validUsername = validUsername;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLoses() {
		return loses;
	}

	public void setLoses(int loses) {
		this.loses = loses;
	}
	
	public double calcWinRatio(int wins, int loses) {
        double win = wins;
        double losess = loses;
        double returnRatio = win/(win+losess);
        return returnRatio;
    }

}
