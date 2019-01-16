package gameEngine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL20;

/*
 * renderer need to ....
 * 
 */
public class Renderer {
	
	
	public static void render(long windowHandle) {
		System.out.println("render window " + windowHandle);
		GL20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT); // clear the framebuffer
		
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
        GLFW.glfwSwapBuffers(windowHandle); 
        
        
        
        // Poll for window events. The key callback  will only be
        // invoked during this call.
        GLFW.glfwPollEvents();
	}
}
