package org.jpavlich.agent.view;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PGraphics;

public class LayerManager {

	private PApplet applet;
	private List<Layer> layers = new ArrayList<>();

	

	public LayerManager(PApplet applet) {
		super();
		this.applet = applet;
	}

	public void add(Layer layer) {
		PGraphics graphics = applet.createGraphics(applet.width, applet.height);
		layer.setGraphics(graphics);
		layer.init(applet);
		layers.add(layer);
	}

	public void draw() {
		applet.clear();
		for (Layer layer : layers) {
			layer.beginDraw();
			layer.draw();
			layer.endDraw();
			applet.image(layer.getGraphics(), 0, 0);
		}
	}

	

}
