package states;

import java.awt.Color;
import java.awt.Graphics;

import entities.Entity;
import pong.Game;

public class GameState extends State{
	
	private Entity entity;

	public GameState(Game game) {
		super(game);
		entity = new Entity(game);
	}

	@Override
	public void update() {
		entity.update();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, game.getWidth(), game.getHeight());
		entity.render(g);
	}

}
