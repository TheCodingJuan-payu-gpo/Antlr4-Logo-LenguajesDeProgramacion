package org.jpavlich.agent.view;

import org.jpavlich.agent.model.Agent;

import processing.core.PApplet;
import processing.core.PImage;

public class SpriteView<A extends Agent> extends Layer {

	public int stepSize;
	public int offset;
	String image;
	protected A agent;
	private PImage sprite;


	public SpriteView(A agent, int stepSize, String image) {
		super();
		this.image = image;
		this.agent = agent;
		this.stepSize = stepSize;
		this.offset = stepSize / 2;
	}

	@Override
	public void init(PApplet applet) {
		if (image != null && image.length() > 0) {
			this.sprite = applet.loadImage(image);
		}
	}

	@Override
	public void draw() {
//		g.background(0,255);
		g.clear();
		
		if (sprite != null) {
			float x = agent.getX() * stepSize + offset;
			float y = agent.getY() * stepSize + offset;
			g.translate(x, y);
			float angle = agent.getRotation();
			g.rotate(angle);

			g.image(sprite, -sprite.width / 2, -sprite.height / 2);
		}
	}

}
