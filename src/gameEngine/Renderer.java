package gameEngine;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Stack;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengles.GLES32;
import org.lwjgl.system.MemoryStack;

import shaders.VertexShader;

import static org.lwjgl.system.MemoryStack.*;


/*
 * An OpenGL Object is an OpenGL construct that contains some state. 
 * When they are bound to the context, the state that they contain is mapped into the context's state. 
 * Thus, changes to context state will be stored in this object, and functions that act on this context state 
 * will use the state stored in the object.
 * OpenGL is defined as a "state machine". 
 * The various API calls change the OpenGL state, query some part of that state, 
 * or cause OpenGL to use its current state to render something.
 * Objects are always containers for state. 
 * Each particular kind of object is defined by the particular state that it contains. 
 * An OpenGL object is a way to encapsulate a particular group of state and change all of it in one function call.
 * 
 */

/*
 * Object Creation and Destruction
 * 
 * To create an object, you generate the object's name (an integer). 
 * This creates a reference to the object. However, this does not necessarily create the object's state data. 
 * For most object types, the object will only contain its default state when it is first bound to the context; 
 * until it is bound, attempting to use it will fail. 
 * The objects which do not behave this way are Program Pipeline Objects and Sampler Objects.
 * The functions to generate object names are of the form glGen*, 
 * where * is the object's type in plural form. All functions of this type have the same signature
 */

public class Renderer {
	
	
	
	public static void render() {
		//System.out.println("render window " + windowHandle);	
		//GL15.glEnable(GLES32.GL_DEBUG_OUTPUT);
		
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
		try (MemoryStack stack = MemoryStack.stackPush()) {
		    FloatBuffer vertices = stack.mallocFloat(3 * 6);
		    vertices.put(-0.6f).put(-0.4f).put(0f);
		    vertices.put(0.6f).put(-0.4f).put(0f);
		    vertices.put(0f).put(0.6f).put(0f);
		    vertices.flip();

		    int vbo = GL15.glGenBuffers();
		    GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		    GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertices, GL15.GL_STATIC_DRAW);
		}
		
		int vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);
		GL20.glEnableVertexAttribArray(0);
		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
		
		GL11.glDrawArrays(GL15.GL_TRIANGLES,0,3);
		
		
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		
		
		//VertexShader shader = new VertexShader("src/shader/vertexShader.ver");
		
	}
	
	public static void clearFrameBuffer() {
		
		GL20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
	}
}
