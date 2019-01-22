package gameEngine;

public class Engine  {

	
	private Window window;
	public Engine(String mode) {
	}
	
	
	
	public void run() {
		
		 System.out.println("#######################\n#  Thread MAIN run ...#\n#######################");
		 init();
		 System.out.println("Goes into loop game ...");
		 System.out.println("Close ?? " + window.isClosed());
		 
		 while(!window.isClosed()) {
			 loop();
		 }
		 terminate();
	}
	
	//public  Thread getRunningMainLoopGameThread() {
	//	return this.threadLoopGame;
	//}
	
	private void init() {
		// create a an  application window
		// create an OpenGl context
		// handle user input from window
		window = new Window(1280, 600, "my window game");
		window.init();
		
		if(window.getHandle() != 0l)
			System.out.println("window "  + window.getHandle() + "& context initialize with success");
		
	}
	
	private void loop() {
		//  get input user
		// update game 
		// render 
		Renderer.render();
		window.update();
	}
	
	
	private void terminate() {
		System.out.println("fin");
		window.close();
	}

}
