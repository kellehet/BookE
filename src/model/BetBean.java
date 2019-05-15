package model;

import java.io.Serializable;

public class BetBean implements Serializable {
	private int betId;
	private UserBean creator;
	private UserBean opponent;
	private String wager;
	private String desc;
	private UserBean winner;
	private UserBean loser;
	private String creatorOption;
	private String opponentOption;
	private boolean accepted=false;
	
	
	public BetBean() {}
	
	public BetBean(int betId,UserBean creator,UserBean opponent,  String wager, String desc, UserBean winner, UserBean loser, String creatorOption, String opponentOption, boolean accepted) {
		this.betId = betId;
		this.creator = creator;
		this.opponent = opponent;
		this.wager = wager;
		this.desc = desc;
		this.winner = winner;
		this.loser = loser;
		this.creatorOption=creatorOption;
		this.opponentOption=opponentOption;
		this.accepted=accepted;
	}
	
	public String getCreatorOption() {
		return creatorOption;
	}

	public void setCreatorOption(String creatorOption) {
		this.creatorOption = creatorOption;
	}

	public String getOpponentOption() {
		return opponentOption;
	}

	public void setOpponentOption(String opponentOption) {
		this.opponentOption = opponentOption;
	}

	public int getBetId() {
		return betId;
	}

	public void setBetId(int betId) {
		this.betId = betId;
	}
	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	
	public UserBean getCreator() {
		return creator;
	}

	public void setCreator(UserBean creator) {
		this.creator = creator;
	}

	public UserBean getOpponent() {
		return opponent;
	}

	public void setOpponent(UserBean opponent) {
		this.opponent = opponent;
	}

	public String getWager() {
		return wager;
	}
	public void setWager(String wager) {
		this.wager = wager;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public UserBean getWinner() {
		return winner;
	}

	public void setWinner(UserBean winner) {
		this.winner = winner;
	}

	public UserBean getLoser() {
		return loser;
	}

	public void setLoser(UserBean loser) {
		this.loser = loser;
	}

	
	
	
}
