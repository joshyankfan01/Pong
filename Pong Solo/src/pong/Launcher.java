package pong;

public class Launcher {

	public static void main(String[] args) {
		Game game = new Game(1200, 900, "Pong");
		game.start();
	}
}
