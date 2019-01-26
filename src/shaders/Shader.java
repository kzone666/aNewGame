package shaders;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.lwjgl.opengl.GL20;

/*
 * glCreateShader creates an empty shader object and returns a non-zero value
 *  by which it can be referenced. 
 *  A shader object is used to maintain the source code strings that define a shader. 
 *  shaderType indicates the type of shader to be created. 
 *  Five types of shader are supported. 
 *  
 *  A shader of type GL_COMPUTE_SHADER is a shader that is intended to run on the programmable compute processor. 
 *  A shader of type GL_VERTEX_SHADER is a shader that is intended to run on the programmable vertex processor. 
 *  A shader of type GL_TESS_CONTROL_SHADER is a shader that is intended to run on 
 *  the programmable tessellation processor in the control stage. 
 *  A shader of type GL_TESS_EVALUATION_SHADER is a shader that is intended to run on 
 *  the programmable tessellation processor in the evaluation stage. 
 *  A shader of type GL_GEOMETRY_SHADER is a shader that is intended to run on 
 *  the programmable geometry processor.
 *  A shader of type GL_FRAGMENT_SHADER is a shader that is intended to run on the programmable fragment processor.
 * 
 */
public class Shader {
	
	private final int vertexShader;
	//protected int type;// = GL20.GL_VERTEX_SHADER;
	protected CharSequence source;
	
	public Shader(String path,int type){
		System.out.println("############ type = " + type + "###########");
		vertexShader = GL20.glCreateShader(type);
		loadFromFile(path);
		source();
		compile();
		
	}
	
	private void source() {
		//this.source();
		GL20.glShaderSource(vertexShader, source);
	}
	
	private void compile(){
		GL20.glCompileShader(vertexShader);
		checkCompileStatus();
	}

	public void checkCompileStatus() {
	    int status = GL20.glGetShaderi(vertexShader, GL20.GL_COMPILE_STATUS);
		if (status != GL20.GL_TRUE) {
			System.err.println("Warning compile Shader code: " + GL20.glGetShaderInfoLog(vertexShader, 1024));
		    throw new RuntimeException(GL20.glGetShaderInfoLog(vertexShader));
		}
	}
	
	
	public void loadFromFile(String path) {
		StringBuilder builder = new StringBuilder();

        try (InputStream in = new FileInputStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load a shader file!"
                                       + System.lineSeparator() + ex.getMessage());
        }
        source = builder.toString();
	}

	public int getId_shader() {
		return vertexShader;
	}
	
}
