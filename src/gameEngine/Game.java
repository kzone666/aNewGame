package gameEngine;

import org.lwjgl.opengl.GL20;

import shaders.FragmentShader;
import shaders.VertexShader;


/*
 * A GAME IS WHAT IS INSIDE IT : people animals etc
 * 
 */


public class Game {
	
	/*
	 * the fist member of my game ;)
	 * a triangle to be rendered
	 */
	 float[] vertices = {
			-1.0f,-1.0f,0.0f,
			1.0f,-1.0f,0.0f,
			0.0f,1.0f,0.0f
		};
		
	int[] indices = {
				0,1,3,
				3,1,2
		};

	private int shaderProgram;
		
	public Game() {
		loadRessources();
		loadProgram();		
	}
	
	private void loadProgram() {
		VertexShader vertexShader = new VertexShader("src/shaders/vertexShader.vert",GL20.GL_VERTEX_SHADER);
		FragmentShader fragmentShader = new FragmentShader("src/shaders/fragmentShader.frag",GL20.GL_FRAGMENT_SHADER);
		
		shaderProgram = GL20.glCreateProgram();
		
		GL20.glAttachShader(shaderProgram, vertexShader.getId_shader());
		GL20.glAttachShader(shaderProgram, fragmentShader.getId_shader());
		
		GL20.glBindAttribLocation(shaderProgram, 0, "position");
		
		GL20.glLinkProgram(shaderProgram); 
		int link_status = GL20.glGetProgrami(shaderProgram, GL20.GL_LINK_STATUS);
		if(link_status == 0) {
            System.err.println("Warning linking Shader code: " + GL20.glGetProgramInfoLog(shaderProgram, 1024));
		}
		
		GL20.glValidateProgram(shaderProgram);
		if (GL20.glGetProgrami(shaderProgram, GL20.GL_VALIDATE_STATUS) == 0) {
            System.err.println("Warning validating Shader code: " + GL20.glGetProgramInfoLog(shaderProgram, 1024));
		}
		

		GL20.glUseProgram(shaderProgram);
	
		
	}

	//load data into GPU
	private void loadRessources() {
		setLocalRessource();
	}
	private void setLocalRessource() {
		Loader.loadfrom(vertices,indices);
	}

	public  float[] getVertices() {
		return vertices;
		
	}
	
}
