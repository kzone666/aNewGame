package gameEngine;

import org.lwjgl.opengl.GL20;

/*
 * renderer need to ....
 * 
 */
public class Renderer {
	
	
	public static void render() {
		//System.out.println("render window " + windowHandle);	
	}
	
	public static void clearFrameBuffer() {
		
		GL20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
	}
}
