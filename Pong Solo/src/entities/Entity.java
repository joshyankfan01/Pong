package entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import pong.Game;
import states.CPUState;
import states.State;

public class Entity {

	private Game game;
	
	private CPUState cpuState;

	// Ball
	private double ballX = 570, ballY = 420, ballVelocityX = 10, ballVelocityY = 7;
	private final int ballWidth = 30, ballHeight = 30;

	// Player
	private double playerX = 0, playerY = 350, playerVelocity = 8;
	private final int playerWidth = 25, playerHeight = 200;

	// CPU
	private double cpuX = 1175, cpuY = 350, cpuVelocity = 8;
	private final int cpuWidth = 25, cpuHeight = 200;
	
	//Scores
	private int playerScore = 0, cpuScore = 0;

	public Entity(Game game) {
		this.game = game;
	}

	public void update() {
		
		checkDifficulty();

		double playerCenter = playerY + (playerHeight / 2);
		double cpuCenter = cpuY + (cpuHeight / 2);

		// Ball
		if (ballX <= 0) {

			if (ballY > playerY && ballY < playerY + playerHeight) {
				if (ballVelocityX < 0) {
					ballVelocityX -= 0.5;
				} else {
					ballVelocityX += 0.5;
				}
				ballVelocityX = -ballVelocityX;

				if (ballY < playerCenter) {
					if (ballVelocityY > 0) {
						ballVelocityY = -ballVelocityY;
					}
				} else {
					if (ballVelocityY < 0) {
						ballVelocityY = -ballVelocityY;
					}
				}
			} else {
				reset();
				checkScores();
			}
		}

		if (ballX + ballWidth >= game.getWidth()) {

			if (ballY > cpuY && ballY < cpuY + cpuHeight) {
				if (ballVelocityX < 0) {
					ballVelocityX -= 0.4;
				} else {
					ballVelocityX += 0.4;
				}
				ballVelocityX = -ballVelocityX;

				if (ballY < cpuCenter) {
					if (ballVelocityY > 0) {
						ballVelocityY = -ballVelocityY;
					}
				} else {
					if (ballVelocityY < 0) {
						ballVelocityY = -ballVelocityY;
					}
				}
			} else {
				reset();
				checkScores();
			}
		}

		ballX += ballVelocityX;

		if (ballY <= 0 || ballY  + ballHeight >= game.getHeight()) {
			if (ballVelocityY < 0) {
				ballVelocityY -= 0.2;
			} else {
				ballVelocityY += 0.2;
			}
			ballVelocityY = -ballVelocityY;
		}

		ballY += ballVelocityY;

		// Player
		if (playerY <= 0) {
			game.getKeyManager().up = false;
		}

		if (playerY >= game.getHeight() - playerHeight) {
			game.getKeyManager().down = false;
		}

		if (game.getKeyManager().up) {
			playerY -= playerVelocity;
		}

		if (game.getKeyManager().down) {
			playerY += playerVelocity;
		}

		// CPU Movement
		
		if (ballY < cpuCenter - 50) {
			cpuY -= cpuVelocity;
		}
		if (ballY > cpuCenter + 50) {
			cpuY += cpuVelocity;
		}
		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.fillOval((int) ballX, (int) ballY, ballWidth, ballHeight);
		g.fillRect((int) playerX, (int) playerY, playerWidth, playerHeight);
		g.fillRect((int) cpuX, (int) cpuY, cpuWidth, cpuHeight);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		g.drawString(Integer.toString(playerScore), 300, 150);
		g.drawString(Integer.toString(cpuScore), game.getWidth() - 325, 150);

	}

	private void reset() {

		if (ballVelocityX < 0) {
			ballVelocityX = -10;
			cpuScore ++;
		} else {
			ballVelocityX = 10;
			playerScore++;
		}
		ballVelocityX = -ballVelocityX;
		
		if (ballVelocityY < 0) {
			ballVelocityY = -7;
		} else { 
			ballVelocityY = 7;
		}
		
		ballX = 570;
		ballY = 420;
		
		playerY = 350;
		cpuY = 350;
	}
	
	private void checkScores() {
		if (playerScore >= 5 || cpuScore >= 5) {
			State.setState(game.getMenuState());
			playerScore = 0;
			cpuScore = 0;
		}
	}
	
	@SuppressWarnings("static-access")
	private void checkDifficulty() {
		
		if (cpuState.getDifficulty() == "EASY") {
			cpuVelocity = 8;
		}
		
		if (cpuState.getDifficulty() == "MEDIUM") {
			cpuVelocity = 10;
		}
		
		if (cpuState.getDifficulty() == "HARD") {
			cpuVelocity = 12;
		}
	}
}
