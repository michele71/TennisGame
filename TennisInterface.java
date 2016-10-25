package com.java.cgi;

public interface TennisInterface {
	public final int SET_SCORE = 6;
	public final int TIE_BREAK_SCORE = 7;
	public final int SET_NUMBER = 3;

	public String getScore(int scorePlayer1, int scorePlayer2);
	
	public void newPoint();
}
