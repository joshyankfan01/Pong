package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import pong.Game;

public class CPUState extends State{
	
	private static String difficulty = "EASY";
	
	public CPUState(Game game) {
		super(game);
	}

	@Override
	public void update() {
		
		if (game.getKeyManager().easy) {
			difficulty = "EASY";
			System.out.println(difficulty);
			State.setState(game.getGameState());
		}
		
		if (game.getKeyManager().medium) {
			difficulty = "MEDIUM";
			System.out.println(difficulty);
			State.setState(game.getGameState());
		}
		
		if (game.getKeyManager().hard) {
			difficulty = "HARD";
			System.out.println(difficulty);
			State.setState(game.getGameState());
		}
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, game.getWidth(), game.getHeight());
		
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.BOLD, 100));
		g.drawString("Choose the Difficulty", 175, 100);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 75));
		g.drawString("Easy --> 1", 425, 250);
		g.drawString("Medium --> 2", 375, 350);
		g.drawString("Hard --> 3", 425, 450);
	}
	
	public static String getDifficulty() {
		return difficulty;
	}

}
