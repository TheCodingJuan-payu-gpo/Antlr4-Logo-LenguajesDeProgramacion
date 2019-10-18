package org.jpavlich.agent.model;

public class Agent implements IAgent {
	private float x = 0;
	private float y = 0;
	private float rotation = 0;
	private float prevRotation = 0;

	private float prevX;
	private float prevY;
	private World world;
	private float red;
	private float green;
	private float blue;
	private float alpha;
	private boolean colorChanged;
	private boolean posChanged;

	public Agent(World world) {
		this.world = world;
	}

	@Override
	public synchronized float getX() {
		return x;
	}

	@Override
	public synchronized float getY() {
		return y;
	}

	public synchronized float getPrevX() {
		return prevX;
	}

	public synchronized float getPrevY() {
		return prevY;
	}

	public synchronized void setPos(float x, float y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public synchronized float move(float dx, float dy) {
		prevX = x;
		prevY = y;
		x += dx;
		y += dy;
		if (x < 0) {
			x = 0;
		}
		if (x >= world.getWidth()) {
			x = world.getWidth() - 1;
		}

		if (y < 0) {
			y = 0;
		}
		if (y >= world.getHeight()) {
			y = world.getHeight() - 1;
		}
		this.posChanged = true;
		return Math.max(Math.abs(x - prevX), Math.abs(y - prevY));

	}

	@Override
	synchronized public float getRotation() {
		return rotation;
	}

	public float getPrevRotation() {
		return prevRotation;
	}

	public void setRotation(float rotation) {
		this.prevRotation = this.rotation;
		this.rotation = rotation;
	}

	@Override
	synchronized public void rotate(float rad) {
		this.setRotation(rotation + rad);
	}

	@Override
	synchronized public void left(float degrees) {
		rotate(-degrees * (float) Math.PI / 180);
	}

	@Override
	synchronized public void right(float degrees) {
		rotate(degrees * (float) Math.PI / 180);
	}

	@Override
	synchronized public void forward(float amount) {
		move((float) Math.cos(rotation) * amount, (float) Math.sin(rotation) * amount);
	}

	synchronized public void backwards(float amount) {
		forward(-amount);
	}

	synchronized public void color(float red, float green, float blue, float alpha) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
		this.colorChanged = true;
	}

	public float getRed() {
		return red;
	}

	public float getGreen() {
		return green;
	}

	public float getBlue() {
		return blue;
	}

	public float getAlpha() {
		return alpha;
	}

	@Override
	public String toString() {
		return "Agent [x=" + x + ", y=" + y + ", rotation=" + rotation + ", red=" + red + ", green=" + green + ", blue="
				+ blue + ", alpha=" + alpha + "]";
	}

	public boolean isColorChanged() {
		return colorChanged;
	}

	public boolean isPosChanged() {
		return posChanged;
	}

	public void setColorChanged(boolean colorChanged) {
		this.colorChanged = colorChanged;
	}

	public void setPosChanged(boolean posChanged) {
		this.posChanged = posChanged;
	}

	
	
	
}
