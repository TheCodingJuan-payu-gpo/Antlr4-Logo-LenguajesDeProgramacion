package org.jpavlich.agent.view;

import processing.core.PApplet;
import processing.core.PGraphics;

public abstract class Layer {

	PGraphics g;
	

	public abstract void init(PApplet applet);

	public abstract void draw();

	public void beginDraw() {
		g.beginDraw();
		
	}

	public void endDraw() {
		g.endDraw();
		
	}
	
	public PGraphics getGraphics() {
		return g;
	}
	
	public void setGraphics(PGraphics g) {
		this.g = g;
	}

}
