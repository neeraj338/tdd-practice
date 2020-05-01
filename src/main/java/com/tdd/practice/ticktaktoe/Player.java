package com.tdd.practice.ticktaktoe;

public class Player {
	private String playerName;
	private TickTackGame game = null;
	
	public Player(String playerName, TickTackGame g) {
		this.playerName = playerName;
		this.game = g;
	}

	public boolean move(int i, int j) {
		return game.mark(playerName, i, j);
	}

}
