package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import pong.Game;

public class MenuState extends State{

	public MenuState(Game game) {
		super(game);
	}

	private static BufferedImage LoadImage(String path) {
		
		try{
			return ImageIO.read(MenuState.class.getResource(path));
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
	
	@Override
	public void update() {
		if (game.getKeyManager().enter) {
			State.setState(game.getCPUState());
		}
		
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, game.getWidth(), game.getHeight());
		
		BufferedImage pong = LoadImage("/pong.jpg");
		g.drawImage(pong, 150, 50, null);
		
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		g.drawString("Press Enter to Continue", 350, 600);
		
	}

}
