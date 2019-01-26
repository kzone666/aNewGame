package gameEngine;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryStack;

import model3D.Model;
import opengl.VertexArrayObject;
import opengl.VertexBufferObject;

public class Loader {
	
	
	private static int vao;
	private static int indices_length;
	private static int vaoId;
	
	/*
	 * The try-with-resources Statement
	 * 
	 * The try-with-resources statement is a try statement that declares one or more resources. 
	 * A resource is an object that must be closed after the program is finished with it. 
	 * The try-with-resources statement ensures that each resource is closed at the end of the statement. 
	 * Any object that implements java.lang.AutoCloseable, which includes all objects which implement java.io.Closeable,
	 * can be used as a resource.
	 * 
	 * ##### MUST READ ######
	 * read more about buffer & lwjgl : https://github.com/LWJGL/lwjgl3-wiki/wiki/1.3.-Memory-FAQ
	 */

	public static int getVao() {
		return vaoId;
	}


	public static int getIndices_length() {
		return indices_length;
	}

	public static void loadfrom(float[] vertices, int[] indices) {
		try (MemoryStack stack = MemoryStack.stackPush()) {
		    FloatBuffer floatBuffer = stack.mallocFloat(vertices.length);
		    floatBuffer.put(vertices);
		    floatBuffer.flip();
		    
		    IntBuffer intBuffer = stack.mallocInt(indices.length);
		    intBuffer.put(indices);
		    intBuffer.flip();
		    
		    vaoId = GL30.glGenVertexArrays();
		    GL30.glBindVertexArray(vaoId);
		    
		    int vbo = GL15.glGenBuffers();
		    GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		    GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertices, GL15.GL_STATIC_DRAW);
		    GL20.glVertexAttribPointer(0, 3, GL20.GL_FLOAT, false, 0, 0);
		    
		    int vboI = GL15.glGenBuffers();
		    GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboI);
		    GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);
		       
		    
		    
		 // Unbind the VBO & VAO
		    GL20.glBindBuffer(GL20.GL_ARRAY_BUFFER, 0);
		    GL30.glBindVertexArray(0);
		}
	}
}
