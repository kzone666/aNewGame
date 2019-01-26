package gameEngine;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryStack;

import shaders.FragmentShader;
import shaders.VertexShader;


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

public abstract class Renderer {
	
	
	
	public static void render() {
		//System.out.println("render window " + windowHandle);	
		//GL15.glEnable(GLES32.GL_DEBUG_OUTPUT);
		
		System.out.println("########### vao " + Loader.getVao());
		GL30.glBindVertexArray(Loader.getVao());
		GL20.glEnableVertexAttribArray(0);
		
		GL11.glDrawElements(GL15.GL_TRIANGLES,3,GL20.GL_UNSIGNED_INT,0);
		
		
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		
	}
}
