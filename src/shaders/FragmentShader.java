package shaders;

import org.lwjgl.opengl.GL20;

public class FragmentShader extends Shader {
	protected int type = GL20.GL_FRAGMENT_SHADER;


	public FragmentShader(String path) {
		super(path);
		System.out.println("###### FRAGMENT SHADER TYPE ##########" + type);
	}

}
