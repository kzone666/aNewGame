package gameEngine;

public class Main {

	public static void main(String[] args) {
		String mode = "debug";// or not
		Engine engine = new Engine(mode);
		engine.run();
	}

}
