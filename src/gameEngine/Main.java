package gameEngine;

/*
 * The API provided by the JVM to Java programs and the calling conventions 
 * of those methods is obviously something completely different compared to a native library such as OpenGL. 
 * First of all, when code such as `System.out.println()` is executed, 
 * that Java code is first translated to JVM bytecode which is then either interpreted 
 * or JIT-compiled to native code and linked to the called target method at runtime when the class is first loaded. 
 * Explaining everything that happens there would definitely completely 
 * blow the scope of a single forum post. I highly recommend reading the Java Virtual Machine Specification 
 * if you want to know all of that in-depth. 
 * 
 * What this distilles to is that the JVM actually is a platform with its own Instruction Set Architecture (ISA), 
 * its own application binary interface (ABI) if you will - which would be the JVM bytecode - 
 * and its own linking procedure for resolving classes, methods etc.
 * 
 * Calling an external native library such as OpenGL is something completely different, 
 * which also would completely blow the scope of a single forum post.
 * You have to know about C function linking, 
 * shared object file formats, the dynamic linker of the operating system, etc. etc. etc. 
 * to get all of this into one picture.
 * 
 * Just take away that LWJGL effectively calls JNI (Java Native Interface) methods - 
 * which is the JVM's way to interact with non-JVM external native functions - 
 * to call OpenGL functions. 
 * And all of such functions are accessible via a function address inside of the processe's address space.
 * And in order to obtain such an address, 
 * there are multiple ways: either dynamic linking, 
 * in which case the dynamic loader of the OS will resolve the function addresses 
 * at load time or call time of the function or via copying the function 
 * into the own executable when statically linking the code, 
 * or via providing an actual API to obtain such function addresses, which is OpenGL's way.
 */

public class Main {

	public static void main(String[] args) {
		String mode = "debug";// or not
		Engine engine = new Engine(mode);
		engine.run();
	}

}
