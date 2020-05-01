package com.tdd.practice.snakeladder;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;


public class SnakeAndLadderGameTest {

	@Test
	public void testPlayerCurrentPosition() {
		Player p = new Player("P1");
		LudoGame ludo = new LudoGame();
		ludo.addPlayerToGame(p);
		p.move(3);
		Assert.assertThat(3, Matchers.is(p.getPlayerPosition()));
	}
	
	@Test
	public void testAddplayerToGame() {
		Player p1 = new Player("P1");
		LudoGame ludo = new LudoGame();
		Assert.assertThat(0, Matchers.is(ludo.addPlayerToGame(p1)));
		Player p2 = new Player("P2");
		Assert.assertThat(1, Matchers.is(ludo.addPlayerToGame(p2)));
	}
	
	@Test
	public void testPlayerMoveWithSnakeAndLadder() {
		Player p1 = new Player("P1");
		LudoGame ludo = new LudoGame();
		ludo.setSnakePosition(33, 2);
		ludo.setLadderPosition(31, 50);
		Assert.assertThat(0, Matchers.is(ludo.addPlayerToGame(p1)));
		Player p2 = new Player("P2");
		Assert.assertThat(1, Matchers.is(ludo.addPlayerToGame(p2)));
		
		Assert.assertThat(0, Matchers.is(p1.getPlayerPosition()));
		
		Assert.assertThat(2, Matchers.is(p1.move(33)));
		Assert.assertThat(50, Matchers.is(p2.move(31)));
	}
	
	@Test
	public void testPlayerGotSIXwillGetAnotherchance() {
		Player p1 = new Player("P1");
		LudoGame ludo = new LudoGame();
		ludo.setSnakePosition(33, 2);
		ludo.setSnakePosition(93, 80);
		ludo.setLadderPosition(31, 50);
		Assert.assertThat(0, Matchers.is(ludo.addPlayerToGame(p1)));
		Player p2 = new Player("P2");
		Assert.assertThat(1, Matchers.is(ludo.addPlayerToGame(p2)));
		
		Assert.assertThat(0, Matchers.is(p1.getPlayerPosition()));
		
		Assert.assertThat(6, Matchers.is(p1.move(6)));
		Assert.assertThat(0, Matchers.is(p2.move(31)));
		Assert.assertThat(11, Matchers.is(p1.move(5)));
	}
	
	
	@Test
	public void testForFirstWinner() {
		Player p1 = new Player("P1");
		LudoGame ludo = new LudoGame();
		ludo.setSnakePosition(33, 2);
		ludo.setSnakePosition(93, 80);
		ludo.setLadderPosition(31, 50);
		Assert.assertThat(0, Matchers.is(ludo.addPlayerToGame(p1)));
		Player p2 = new Player("P2");
		Assert.assertThat(1, Matchers.is(ludo.addPlayerToGame(p2)));
		
		Assert.assertThat(0, Matchers.is(p1.getPlayerPosition()));
		
		Assert.assertThat(92, Matchers.is(p1.move(92)));
		Assert.assertThat(50, Matchers.is(p2.move(31)));
		Assert.assertThat(80, Matchers.is(p1.move(1)));
		Assert.assertThat(80, Matchers.is(p1.getPlayerPosition()));
		Assert.assertThat(51, Matchers.is(p2.move(1)));
		Assert.assertThat(86, Matchers.is(p1.move(6)));
		Assert.assertThat(92, Matchers.is(p1.move(6)));
		Assert.assertThat(98, Matchers.is(p1.move(6)));
		Assert.assertThat(100, Matchers.is(p1.move(2)));
		Assert.assertThat(p1, Matchers.is(Matchers.equalTo(ludo.getWinnerByPosition(1))));
		Assert.assertThat(null, Matchers.is(Matchers.equalTo(ludo.getWinnerByPosition(2))));
		
		Assert.assertThat(52, Matchers.is(p2.move(1)));
		Assert.assertThat(53, Matchers.is(p2.move(1)));
	}
	
}
