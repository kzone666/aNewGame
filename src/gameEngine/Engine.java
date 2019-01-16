package gameEngine;

public class Engine implements Runnable {

	
	private Window window;
	private StackTraceElement[] threadTrace;
	private String mode;
	private final Thread threadLoopGame;
	
	public Engine(String mode) {
		this.mode = mode;
		threadLoopGame = new Thread(this,"GAME_LOOP_THREAD");
	}
	
	
	@Override
	public void run() {
		 threadTrace = Thread.currentThread().getStackTrace();
		 if("debug".equals(mode))
			 printStackThreadTrace();
		 System.out.println("#######################\n#  Thread MAIN run ...#\n#######################");
		 init();
		 System.out.println("Goes into loop game ...");
		 System.out.println("Close ?? " + window.isClosed());
		 
		 while(!window.isClosed()) {
			 loop();
		 }
		 terminate();
	}
	
	public  Thread getRunningMainLoopGameThread() {
		return this.threadLoopGame;
	}
	
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
		Renderer.render(window.getHandle());
	}
	
	
	private void terminate() {
		System.out.println("fin");
		window.close();
	}
	
	private void printStackThreadTrace() {
		for(int i = 0; i < threadTrace.length; i++)
			System.out.println(threadTrace[i]);
	}

}
