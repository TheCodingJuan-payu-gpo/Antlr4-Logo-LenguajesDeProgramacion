package org.jpavlich.agent.model;

public interface IAgent {

	void forward(float amount);

	void rotate(float amount);

	float getRotation();

	float move(float dx, float dy);

	float getY();

	float getX();

	void color(float red, float green, float blue, float alpha);

	void right(float degrees);

	void left(float degrees);

	void backwards(float amount);

}
