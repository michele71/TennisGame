package com.java.cgi;


import java.util.Random;


public class Tennis implements TennisInterface 
{    
	/*
	 * The constructor
	*/
	public Tennis(String player1, String player2)
	{
		this.player1 = player1;
		this.player2 = player2;
		billBoard = new StringBuilder("Score: ");
	}
	
	
	/*
	 * Play a new point in a game between two tennis players
	*/
	public void newPoint()
	{
		Random random = new Random();
		int point = random.nextInt(2);
		
		if(point == 0)
		{
			scorePlayer1 ++;
		}
		else
		{
			scorePlayer2 ++;
		}
	}
	
	
	/*
	 * Format the numeric score in the language of the tennis
	*/
	public String getScore(int scorePlayer1, int scorePlayer2) 
	{
		String resPlayer1 = "";
		String resPlayer2 = "";
        
        if (scorePlayer1 == scorePlayer2 && scorePlayer1 < 4)
        {
            if (scorePlayer1 == 0)
            {
                score = "Love";
            }
            
            if (scorePlayer1 == 1)
            {
            	score = "Fifteen";
            }
            
            if (scorePlayer1 == 2)
            {
            	score = "Thirty";
            }
            
            score += "-All";
        }
        
        if (scorePlayer1 == scorePlayer2 && scorePlayer1 >= 3)
        {
            score = "Deuce";
        }
        
        if (scorePlayer1 > 0 && scorePlayer2 == 0)
        {
            if (scorePlayer1 == 1)
            {
            	resPlayer1 = "Fifteen";
            }
            
            if (scorePlayer1 == 2)
            {
            	resPlayer1 = "Thirty";
            }
            	
            if (scorePlayer1 == 3)
            {
            	resPlayer1 = "Forty";
            }
            
            resPlayer2 = "Love";
            score = resPlayer1 + "-" + resPlayer2;
        }
        
        if (scorePlayer2 > 0 && scorePlayer1 == 0)
        {
            if (scorePlayer2 == 1)
            {
                resPlayer2 = "Fifteen";
            }
                
            if (scorePlayer2 == 2)
            {
            	resPlayer2 = "Thirty";
            }
            
            if (scorePlayer2 == 3)
            {
            	resPlayer2 = "Forty";
            }
            
            resPlayer1 = "Love";
            score = resPlayer1 + "-" + resPlayer2;
        }
        
        if (scorePlayer1 > scorePlayer2 && scorePlayer1 < 4)
        {
            if (scorePlayer1 == 2)
            {	
                resPlayer1="Thirty";
            }
            
            if (scorePlayer1 == 3)
            {
            	resPlayer1="Forty";
            }
            
            if (scorePlayer2 == 1)
            {
            	resPlayer2="Fifteen";
            }
            
            if (scorePlayer2 == 2)
            {
            	resPlayer2="Thirty";
            }
            
            score = resPlayer1 + "-" + resPlayer2;
        }
        
        if (scorePlayer2 > scorePlayer1 && scorePlayer2 < 4)
        {
            if (scorePlayer2 == 2)
            {
                resPlayer2="Thirty";
        	}
        
            if (scorePlayer2 == 3)
            {
            	resPlayer2="Forty";
        	}
        
            if (scorePlayer1 == 1)
            {
            	resPlayer1="Fifteen";
        	}
        
            if (scorePlayer1 == 2)
            {
            	resPlayer1="Thirty";
            }
            
            score = resPlayer1 + "-" + resPlayer2;
        }
        
        if (scorePlayer1 > scorePlayer2 && scorePlayer2 >= 3)
        {
            score = "Advantage player1";
        }
        
        if (scorePlayer2 > scorePlayer1 && scorePlayer1 >= 3)
        {
            score = "Advantage player2";
        }
        
        if (scorePlayer1 >= 4 && scorePlayer2 >= 0 && (scorePlayer1-scorePlayer2) >= 2)
        {
            score = "Game for " + player1;
            setScorePlayer1 ++;
        }
        
        if (scorePlayer2 >= 4 && scorePlayer1 >= 0 && (scorePlayer2 - scorePlayer1) >= 2)
        {
            score = "Game for " + player2;
            setScorePlayer2 ++;
        }
                
        return score;
	}
	
	
	public String getBillBoard()
	{		
		return billBoard.toString();
	}
	
	
	/*
	 * Play a game between two players until one player's score
	 * is greater or equal than 40 and with a
	 * gap of at least 2 points from the other player
	 * and return the winner
	*/
	public String playGame()
	{	
		String result = "";
		
		while(!result.contains("Game "))
		{
			this.newPoint();		
			this.score = this.getScore(scorePlayer1, scorePlayer2);
			result = player1 + " - " + player2 + "\n" + this.score + "\n";
					
			System.out.println(result);
		}
		
		this.scorePlayer1 = 0;
		this.scorePlayer2 = 0;
		this.score = "";
		
		return (result);
	}
	
	
	/*
	 * Play a set between two players until one player's score
	 * is greater or equal than SET_SCORE and with a
	 * gap of at least 2 points from the other player
	 * and return the winner
	*/
	public String playSet()
	{
		String result = "";
		String tieBreakResult = "";
		
		while(!result.contains("Set"))
		{
			this.playGame();
			
	        if (setScorePlayer1 < SET_SCORE && setScorePlayer2 < SET_SCORE)
	        {   
	        	result = "Score: " + setScorePlayer1 + " - " + setScorePlayer2 + ";\n";
	        	System.out.println(result);	
	        }
	        else
	        {
	        	result = "Score: " + setScorePlayer1 + " - " + setScorePlayer2 + ";";	
	        	System.out.println(result);
	        	
	        	if (setScorePlayer1 > setScorePlayer2 && setScorePlayer1 - setScorePlayer2 >= 2)
	        	{
	        		result = "Set for " + player1 + "\n";
	        		setWonPlayer1 ++;
	        		
		        	System.out.println(result);
	        	}
	        	else if (setScorePlayer2 > setScorePlayer1 && setScorePlayer2 - setScorePlayer1 >= 2)
	        	{
	        		result = "Set for " + player2 + "\n";
	        		setWonPlayer2 ++;
	        		
		        	System.out.println(result);
	        	}
	        	else if (setScorePlayer1 == setScorePlayer2)
	        	{	
	        		tieBreakResult = this.playTieBreak();
	        		tieBreakResult = " " + tieBreakResult.substring(tieBreakResult.indexOf("("), tieBreakResult.indexOf(";"));
	        		
	        		if (scorePlayer1 > scorePlayer2)
	        		{
	        			result = "Set for " + player1 + "\n";
	        		}
	        		else
	        		{
	        			result = "Set for " + player2 + "\n";
	        		}
	        		
		        	System.out.println(result);
	        	}
	        }
		}
		
		billBoard.append(setScorePlayer1 + " - " + setScorePlayer2 + tieBreakResult + "; ");
		this.setScorePlayer1 = 0;
		this.setScorePlayer2 = 0;
		
		return (result); 
	}
	
	
	/*
	 * Play a tie-break between two players until one player's score
	 * is greater or equal than TIE_BREAK_SCORE and with a
	 * gap of at least 2 points from the other player
	 * and return the score
	*/
	public String playTieBreak()
	{
		String result = "";
		this.scorePlayer1 = 0;
		this.scorePlayer2 = 0;
		
		while((scorePlayer1 < TIE_BREAK_SCORE || scorePlayer1 - scorePlayer2 < 2) && 
			  (scorePlayer2 < TIE_BREAK_SCORE || scorePlayer2 - scorePlayer1 < 2))
		{
			this.newPoint();
		}
		
		result = "Tie-break: (" + this.scorePlayer1 + " - " + scorePlayer2 + ");\n";
		System.out.print(result);
		
		return result;
	}
	
	
	/*
	 * Play a whole tennis match between two players
	 * and return the winner
	*/
	public String playMatch()
	{
		String result = "";
		int set = 0;
		boolean won = false;
		
		while (set < SET_NUMBER && !won)
		{
			this.playSet();
			
			if (setWonPlayer1 > SET_NUMBER / 2 || setWonPlayer2 > SET_NUMBER / 2)
			{
				won = true;
			}
			
			set ++;
		}
		
		result = "Final " + billBoard.toString() + "\n";
		
		if (setWonPlayer1 > setWonPlayer2)
		{
			result += "Win for " + player1;
		}
		else
		{
			result += "Win for " + player2;
		}
		
		System.out.println(result);
		
		return result;
	}
	
	
	/*
	 * The main method
	*/
	public static void main(String[] args) 
	{	
		Tennis tennis = new Tennis("Novak Djokovic", "Roger Federer");
		
		tennis.playMatch();
	}
	

	private String player1;
	
	private String player2;
	
	private int scorePlayer1;
	
	private int scorePlayer2;
	
	private int setScorePlayer1;
	
	private int setScorePlayer2;
	
	private int setWonPlayer1;
	
	private int setWonPlayer2;
	
	private StringBuilder billBoard;
    
	private String score = "";
}
