package shaders;

import org.lwjgl.opengl.GL20;

public class VertexShader extends Shader {
	protected int type = GL20.GL_VERTEX_SHADER;
	
	public VertexShader(String path) {
		super(path);
	}

}
