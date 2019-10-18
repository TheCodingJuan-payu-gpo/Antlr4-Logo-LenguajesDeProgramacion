package org.jpavlich.agent.model;

public class SimpleWorld implements World {

	private int height;
	private int width;

	
	public SimpleWorld(int width, int height) {
		super();
		this.height = height;
		this.width = width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

}
