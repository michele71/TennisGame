package com.java.cgi.tests;


import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import com.java.cgi.Tennis;


public class TennisTest {
	
	private Tennis tennis;
	
		
	@Before
	public void setUp() throws Exception 
	{
		tennis = new Tennis("Andy Murray", "Stan Wawrinka");
	}

	
	/*
	 * Test the method playGame()
	*/
	@Test
	public void testGame() 
	{	
		String message = tennis.playGame();
		
		assertTrue(message.contains("Game for"));
	}


	/*
	 * Test the method playSet()
	*/
	@Test
	public void testSet() 
	{
		String message = tennis.playSet();

		assertTrue(message.contains("Set for"));
	}
	
	
	/*
	 * Test the method playTieBreak()
	*/
	@Test
	public void testTieBreak() 
	{
		String message = tennis.playTieBreak();

		assertTrue(message.contains("Tie-break"));		
	}
	
	
	/*
	 * Test the method playMatch()
	*/
	@Test
	public void testMatch() 
	{
		String message = tennis.playMatch();

		assertTrue(message.contains("Win for"));		
	}
}
