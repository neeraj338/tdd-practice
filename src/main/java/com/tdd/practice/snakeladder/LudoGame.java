package com.tdd.practice.snakeladder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LudoGame {
	
	private List<Player> players = new ArrayList<>();
	private Map<Integer, Integer> snakeLadeerPositionMap = new HashMap<>();
	private List<Player> winners = new ArrayList<>();
	
	private Player whosNext = null;
	
	public int addPlayerToGame(Player player) {
		players.add(player);
		player.initGame(this);
		winners.remove(player);
		return players.size()-1;
	}

	public void setSnakePosition(int snakeHead, int snakeTail) {
		snakeLadeerPositionMap.put(snakeHead, snakeTail);
	}

	public void setLadderPosition(int start, int end) {
		snakeLadeerPositionMap.put(start, end);
	}

	public int move(int postition) {
		return snakeLadeerPositionMap.getOrDefault(postition, postition);
	}

	public int move(Player player, int moveCount) {
		if(null != whosNext && !whosNext.equals(player)) {
			return player.getPlayerPosition();
		}
		if(6== moveCount) {
			this.whosNext = player;
		}else{
			int indexOf = players.indexOf(player);
			if(-1 == indexOf) {
				whosNext = null;
			}else if(indexOf < players.size()-1) {
				whosNext = players.get(indexOf+1);
			}else {
				whosNext = players.get(0);
			}
		}
		int finalPosition = player.getPlayerPosition() + moveCount;
		if(finalPosition > 100) {
			return player.getPlayerPosition();
		}
		player.setPlayerPosition(snakeLadeerPositionMap.getOrDefault(finalPosition, finalPosition)); 
		if(player.getPlayerPosition()== 100)
		{
			addtoWinner(player);
		}
		return player.getPlayerPosition();
	}

	public Player getWinnerByPosition(int position) {
		position = position -1;
		if(position > winners.size()-1) {
			return null;
		}
		return this.winners.get(position);
	}
	
	private void addtoWinner(Player p) {
		if(this.winners.contains(p)) {
			return;
		}
		 players.remove(p);
		 this.winners.add(p);
	}
}
