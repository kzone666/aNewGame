package shaders;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

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
	
	private final int id_shader;
	protected int type = GL20.GL_VERTEX_SHADER;
	protected CharSequence source;
	private int status;
	
	public Shader(String path){
		System.out.println("#################" + type);
		this.id_shader = GL20.glCreateShader(type);
		loadFromFile(path);
		source();
		compile();
	}
	
	private void source() {
		this.source();
	}
	
	private void compile() {
		this.compile();
	}

	public void checkCompileStatus() {
	    status = GL20.glGetShaderi(this.id_shader, GL20.GL_COMPILE_STATUS);
		if (status != GL20.GL_TRUE) {
		    throw new RuntimeException(GL20.glGetShaderInfoLog(this.id_shader));
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
		return id_shader;
	}
	
	public int getType() {
		return this.type;
	}
	
}