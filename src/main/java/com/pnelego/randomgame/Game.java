package com.pnelego.randomgame;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Game {
	private long window;
	private void init(){
		System.out.println(GLFW_KEY_W);
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
		});
		
	}
	
	private void loop() {
		GL.createCapabilities();
		glClearColor(0,0,0,0);
		
		glfwSwapBuffers(window); // swap the color buffers

		// Poll for window events. The key callback above will only be
		// invoked during this call.
		glfwPollEvents();
	}
	public void run() {
		Display display = new Display();
		Keyboard keyboard = new Keyboard();

		display.init();
		keyboard.init();	

		window = display.getWindow(); //first
		init(); //not to be confused with init@diplay - Second
		while(!glfwWindowShouldClose(window)) {
			loop();
		}
		
		
		display.clean();
	}
}
