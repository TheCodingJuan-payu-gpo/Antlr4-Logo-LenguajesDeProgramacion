package co.edu.javeriana.logo;

public interface ITurtle {
	void color(float red, float green, float blue, float alpha);

	void forward(float amount);

	void backwards(float amount);

	void left(float degrees);
	
	void right(float degrees);
}
