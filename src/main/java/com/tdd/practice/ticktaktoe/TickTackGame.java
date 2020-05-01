package com.tdd.practice.ticktaktoe;

import java.util.Arrays;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TickTackGame {
	private int moveCount = 0;
	private String[][] gameBoard= null;
	private String lastPlayerAction;
	private String winner;
	public TickTackGame() {
		reset();
	}
	public boolean  reset() {
		lastPlayerAction = null;
		winner = null;
		moveCount = 0;
		gameBoard = new String[][] {
				  {"-1","-1", "-1"}
				, {"-1","-1", "-1"}
				, {"-1","-1", "-1"}};
		return true;
	}
	
	public int getMoveCount() {
		return this.moveCount;
	}
	
	public boolean mark(String playerName, int i, int j) throws RuntimeException {
		if (winner != null) {
			throw new RuntimeException("Game is wone by "+winner);
		}
		if(!playerName.equals(lastPlayerAction) 
				&& this.gameBoard[i][j].contains("-1")) {
			lastPlayerAction = playerName;
			this.gameBoard[i][j] = playerName;
			moveCount++;
			if(moveCount >=5 ) {
				checkForWinner();
			}
			return true;
		}
		return false;
	}
	
	private void checkForWinner() {
		//rows
		if(gameBoard[0][0].equals(gameBoard[0][1]) && (gameBoard[0][1].equals(gameBoard[0][2]))){
			winner = gameBoard[0][0];
		}else if(gameBoard[1][0].equals(gameBoard[1][1]) && (gameBoard[1][1].equals(gameBoard[1][2]))){
			winner = gameBoard[1][0];
		}else if(gameBoard[2][0].equals(gameBoard[2][1]) && (gameBoard[2][1].equals(gameBoard[2][2]))){
			winner = gameBoard[1][0];
		}
		//cols
		if(gameBoard[0][0].equals(gameBoard[1][0]) && (gameBoard[1][0].equals(gameBoard[2][0]))){
			winner = gameBoard[0][0];
		}else if(gameBoard[0][1].equals(gameBoard[1][1]) && (gameBoard[1][1].equals(gameBoard[2][1]))){
			winner = gameBoard[0][1];
		}else if(gameBoard[0][2].equals(gameBoard[1][2]) && (gameBoard[1][2].equals(gameBoard[2][2]))){
			winner = gameBoard[0][2];
		}
		//diagolas
		if(gameBoard[0][0].equals(gameBoard[1][1]) && (gameBoard[1][1].equals(gameBoard[2][2]))){
			winner = gameBoard[0][0];
		}else if(gameBoard[0][2].equals(gameBoard[1][1]) && (gameBoard[1][1].equals(gameBoard[2][0]))){
			winner = gameBoard[0][2];
		}
		if(winner != null) {
			System.out.println(this);
		}
	}
	public String getWinner() {
		return this.winner;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("\n Game Position:: ")
				.append("\n")
				.append(Arrays.asList(gameBoard[0]))
				.append("\n")
				.append(Arrays.asList(gameBoard[1]))
				.append("\n")
				.append(Arrays.asList(gameBoard[2]))
				.append("\n")
				.append("total moves :: ")
				.append(this.moveCount)
				.toString();
	}
}
