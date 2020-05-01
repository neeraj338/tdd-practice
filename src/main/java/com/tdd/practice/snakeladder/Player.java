package com.tdd.practice.snakeladder;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Player {
	
	private String playerName;
	
	private int postition;
	
	private LudoGame ludoGame;
	
	public Player(String playerName) {
		this.playerName = playerName;
	}

	public int move(int moveCount) {
		return ludoGame.move(this, moveCount);
	}

	public int getPlayerPosition() {
		return this.postition;
	}
	
	public void setPlayerPosition(int position) {
		this.postition = position;
	}
	public void initGame(LudoGame ludoGame) {
		postition = 0;
		this.ludoGame = ludoGame;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.playerName).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		
		return new EqualsBuilder().append(this.playerName, other.playerName).build();
	}
	
	
}
