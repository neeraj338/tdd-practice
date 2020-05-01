package com.tdd.practice.ticktaktoe;

import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.*;
import org.junit.Test;

public class TicTackTowTest {

	@Test
	public void createGameResetTestTest() {
		TickTackGame g = new TickTackGame();
		boolean resetGame =  g.reset();
		Assert.assertThat(true, Matchers.is(resetGame));
	}
	
	@Test
	public void testPlayerMoveToPositionTest() {
		TickTackGame g = new TickTackGame();
		Player player1 = new Player("P1", g);
		boolean move = player1.move(0,1);
		Player player2 = new Player("P2", g);
		boolean move2 = player2.move(0,2);
		
		Assert.assertThat(true, Matchers.is(move));
		Assert.assertThat(true, Matchers.is(move2));
		Assert.assertThat("position is already set no move allow", false, Matchers.is(player2.move(0, 2)));
		
		Assert.assertThat(2, Matchers.is(g.getMoveCount()));
	}
	
	@Test
	public void testRepeastLastPlayerMove() {
		TickTackGame g = new TickTackGame();
		Player player1 = new Player("P1", g);
		boolean move = player1.move(0,1);
		Assert.assertThat(true, Matchers.is(move));
		Assert.assertThat("No continuous move allowed by same player ", false, Matchers.is(player1.move(0,2)));
		Assert.assertThat("No continuous move allowed by same player ", false, Matchers.is(player1.move(1,2)));
		Assert.assertThat(1, Matchers.is(g.getMoveCount()));
	}
	
	@Test
	public void testWinner() {
		TickTackGame g = new TickTackGame();
		Player player1 = new Player("P1", g);
		Player player2 = new Player("P2", g);
		player1.move(0,1);
		player2.move(1,1);
		//2:
		player1.move(0,2);
		player2.move(2,2);
		//3
		player1.move(2,0);
		player2.move(0,0);
		Assert.assertThat("P2", Matchers.containsString( g.getWinner()));
	}
	
	@Test(expected = RuntimeException.class)
	public void testWinnerMoveNotAlloweError() {
		TickTackGame g = new TickTackGame();
		Player player1 = new Player("P1", g);
		Player player2 = new Player("P2", g);
		player1.move(0,1);
		player2.move(1,1);
		//2:
		player1.move(0,2);
		player2.move(2,2);
		//3
		player1.move(2,0);
		player2.move(0,0);
		player2.move(2,1);
		//Assert.assertThat("P2", Matchers.containsString( g.getWinner()));
	}
	
}
