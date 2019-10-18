package org.jpavlich.agent.view;

import org.jpavlich.agent.model.Agent;

import processing.core.PApplet;

public class DrawingView extends Layer {

	private Agent agent;

	public DrawingView(Agent agent) {
		super();
		this.agent = agent;
	}

	@Override
	public void init(PApplet applet) {

	}

	@Override
	public void draw() {

		if (agent.isColorChanged()) {
			g.stroke(agent.getRed(), agent.getGreen(), agent.getBlue(), agent.getAlpha());	
			agent.setColorChanged(false);
			
		}
		if (agent.isPosChanged()) {
			g.line(agent.getPrevX(), agent.getPrevY(), agent.getX(), agent.getY());
			agent.setPosChanged(false);
		}
	}

}
