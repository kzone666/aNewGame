package model3D;

import java.nio.FloatBuffer;
import java.util.Arrays;

public class Vertex {
	
	private FloatBuffer buffer; 
	
	public Vertex(Arrays rawData) {
		this.buffer = sourceRawDataToBuffer(rawData);
	}
	
	public FloatBuffer sourceRawDataToBuffer(Arrays rawData) {
		
		return buffer;
	}
}
