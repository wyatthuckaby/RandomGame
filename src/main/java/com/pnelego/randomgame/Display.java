package com.pnelego.randomgame;

//import org.lwjgl.*;

import org.lwjgl.glfw.*;
//import org.lwjgl.opengl.*;
//import org.lwjgl.system.*;



//import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
//import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Display {
	private long window;
	
	
	private void configGLFW() {
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); //if they see the window at this point its a little awkward
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); //we need to make sure the user can't change the window size without
		//changing them in the game settings
	}
	
	private int startWindow() {
		//we dont want to fullscreen unless the user wants it; and we dont share.
		window = glfwCreateWindow(800,600, "The Random Game", NULL, NULL);
		if (window == NULL) {
			System.err.println("Failed to create the window. This might actually be on us. ");
			return -1;
		}
		return 0;
	}
	public int init() {
		//set error callback to the default system cerr.
		//this is important because it allows the framework to throw
		//its own errors.
		GLFWErrorCallback.createPrint(System.err).set();
		
		//init the GL Framework (someone give these guys a cookie.)
		if (!glfwInit()) {
			//.... This is awkward
			System.err.println("Failed to init GLFW... This is NOT our fault");
			return -1;
		}
		
		configGLFW(); //set settings
		
		if (startWindow() == -1) {
			return -1;
		}
		

		// Get the resolution of the primary monitor
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

		// Center the window
		glfwSetWindowPos(window, vidmode.width() / 2, vidmode.height() / 2);
		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(window);
		return 0;
	}
	
	//still angry about the lack of a destructor.
	public void clean(){
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
	
	public long getWindow() {
		return window;
	}
}
