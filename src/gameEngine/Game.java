package gameEngine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL20;


/*
 * A GAME IS WHAT IS INSIDE IT : people animals etc
 * 
 */


public class Game {
	
	/*
	 * the fist member of my game ;)
	 * a triangle to be rendered
	 */
	static float[] vertices = {
			-0.5f,0.5f,0f,
			-0.5f,-0.5f,0f,
			0.5f,-0.5f,0f,
			0.5f,-0.5f,0f,
			0.5f,0.5f,0f,
			-0.5f,0.5f,0f
		};
		
	int[] indices = {
				0,1,3,
				3,1,2
		};
		
	public static float[] getVertices() {
		return vertices;
		
	}
	
}
