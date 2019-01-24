package gameEngine;

import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.opengl.GL11.GL_TRUE;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWErrorCallbackI;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengles.*;
public class Window {
	
	private long handle = 0;
	private int WIDTH = 0;
	private int HEIGHT = 0;
	private String TITLE;
	private boolean closed;
	private String ketPressed;
	protected GLFWKeyCallback keyCallback;
	
	public Window(int width, int height, String title) {
		this.WIDTH = width;
		this.HEIGHT = height;
		this.TITLE = title;
	}
	
	/*
	 * https://www.glfw.org/docs/latest/intro_guide.html#intro_init_init
	 * 
	 *Before most GLFW functions may be called, the library must be initialized. 
	 *This initialization checks what features are available on the machine, 
	 *enumerates monitors and joysticks, 
	 *initializes the timer and performs any required platform-specific initialization.
     Only the following functions may be called before the library has been successfully initialized, 
     and only from the main thread.

     glfwGetVersion
     glfwGetVersionString
     glfwSetErrorCallback
     glfwInit
     glfwTerminate
     Calling any other function before successful initialization will cause a GLFW_NOT_INITIALIZED error.
 
	 */
	
	public void init() { // initialize library GLFW 
		// MUST initialize some HINTS before create window handle
		// Setup an error callback. The default implementation
        // will print the error message in System.err.
		System.out.println("gonna initialize GLFW now ... ");
		System.out.println("ARE YOU IN THE THREAD MAIN ???");
		
		
		
	
        
        /*
         * Do not use the version string to parse the GLFW library version. 
         * The glfwGetVersion function already provides the version of the running library binary.
         * The format of the string is as follows:
         * 		The version of GLFW
         * 		The name of the window system API
         * 		The name of the context creation API
         * 		Any additional options or APIs
         * 
         */
        
        
        
        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if(!org.lwjgl.glfw.GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        
        else
        	//System.out.println("Heee ... you've a glfw init!");
        
        //System.out.println("GLFW compile-time version : " + GLFW. glfwGetVersionString());
        
        // modify default hints values
        updateHints();
        
        // get handle only when all init are ok
		createHandle();
		
		//System.out.println("attaxh a key callback to window");
		provideKeyCallBack();
		
		provideErrorCallBack();
		
		//GL15.glEnable(GLES32.GL_DEBUG_OUTPUT);
	/*
	 * TODO : put in a method
	 */
		// Make the OpenGL context current
        GLFW.glfwMakeContextCurrent(handle);

        /*
         * The swap interval indicates how many frames to wait until swapping the buffers, 
         * commonly known as vsync. By default, the swap interval is zero, meaning buffer 
         * swapping will occur immediately. On fast machines, many of those frames will never be seen, 
         * as the screen is still only updated typically 60-75 times per second, 
         * so this wastes a lot of CPU and GPU cycles.
         * Also, because the buffers will be swapped in the middle the screen update, leading to screen tearing.
         * 
         * For these reasons, applications will typically want to set the swap interval to one. 
         * It can be set to higher values, but this is usually not recommended, 
         * because of the input latency it leads to.
         */
        
        
        // Make the window visible
        GLFW.glfwShowWindow(handle);
		GL.createCapabilities();
		GLFW.glfwSwapInterval(1);
		// a red background
		GL20.glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
	}
	
	private void provideErrorCallBack() {
		
		/*GLFW.glfwSetErrorCallback(new GLFWErrorCallback() {*/
			GLFWErrorCallback errorCallback  = new GLFWErrorCallback() {
			@Override
			public void invoke(int error, long description) {
				System.out.println("error" + error);	
			}
		};	
		GLFW.glfwSetErrorCallback(errorCallback);
	}

	private void updateHints() {
		/*
		 * https://www.glfw.org/docs/latest/window_guide.html#window_hints
		 * 
		 * There are a number of hints that can be set before the creation of a window and context. 
		 * Some affect the window itself, others affect the framebuffer or context. 
		 * These hints are set to their default values each time the library is initialized with glfwInit, 
		 * can be set individually 
		 * with glfwWindowHint and reset all at once to their defaults with glfwDefaultWindowHints.
		 * Note that hints need to be set before the creation of the window and context you wish to have 
		 * the specified attributes.
		 * 
		 */
		System.out.println("update hints window");
		
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		
		/*
		 * To Check OpenGL Version,
		 * glxinfo | grep "OpenGL core profile version string:"
		 * OpenGL core profile version string: 4.5 (Core Profile) Mesa 18.0.5
		 */
		org.lwjgl.glfw.GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 4); 
		org.lwjgl.glfw.GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 5); 
		
		org.lwjgl.glfw.GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE,GLFW.GLFW_OPENGL_CORE_PROFILE); 
	}
	
	private void createHandle() {
		
		/*
		 * https://www.glfw.org/docs/latest/window_guide.html#window_creation
		 * 
		 * The GLFWwindow object encapsulates both a window and a context. 
		 * They are created with glfwCreateWindow and destroyed with glfwDestroyWindow, or glfwTerminate, 
		 * if any remain. As the window and context are inseparably linked, 
		 * the object pointer is used as both a context and window handle.
		 */
		handle = org.lwjgl.glfw.GLFW.glfwCreateWindow(WIDTH, HEIGHT, TITLE, 0, 0);
        if (handle == 0l) {
            throw new RuntimeException("Failed to create the GLFW window");
        }
	}
	
	public void close() {
		
		//GL15.glDisable(GLES32.GL_DEBUG_OUTPUT);
		GLFW.glfwSetKeyCallback(handle, keyCallback).free();
		GLFW.glfwDestroyWindow(handle);
		GLFW.glfwSetErrorCallback(null).free();
		
		 GLFW.glfwTerminate();
		 GL.setCapabilities(null);
		 
		 //errorCallback.free();
	}

	public long getHandle() {
		return handle;
	}

	public boolean isClosed() {
		if(GLFW.glfwWindowShouldClose(handle))
			closed = true;
		return closed;
	}
	
	public GLFWKeyCallback getKeyCallback() {
		return keyCallback;
	}

	public void setKeyCallback(GLFWKeyCallback keyCallback) {
		this.keyCallback = keyCallback;
	}

	private void provideKeyCallBack() {

		GLFWKeyCallback keyCallback = new GLFWKeyCallback() {
		    @Override
		    public void invoke(long window, int key, int scancode, int action, int mods) {
		    	
		    	/*
		    	 * Checking the window close flag
		    	 * Each window has a flag indicating whether the window should be closed.
		    	 * When the user attempts to close the window, either by pressing the close widget 
		    	 * in the title bar or using a key combination like Alt+F4, this flag is set to 1
		    	 */
		        if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_PRESS) {
		            GLFW.glfwSetWindowShouldClose(window, true);
		        }
		    }
		};
		
		GLFW.glfwSetKeyCallback(handle, keyCallback);
	}
	
	public void update() {
		// moteur de rendu du jeu 
				/*
				 * Swapping buffers
				 * 
				 * GLFW windows by default use double buffering. 
				 * That means that each window has two rendering buffers; 
				 * a front buffer and a back buffer. The front buffer is the one being displayed and the back buffer 
				 * the one you render to.
				 * When the entire frame has been rendered, 
				 * the buffers need to be swapped with one another, 
				 * so the back buffer becomes the front buffer and vice versa.
				 * 
				 */
		        GLFW.glfwSwapBuffers(handle); 
		        
		        
		        
		        // Poll for window events. The key callback  will only be
		        // invoked during this call.
		        GLFW.glfwPollEvents();
	}

}
