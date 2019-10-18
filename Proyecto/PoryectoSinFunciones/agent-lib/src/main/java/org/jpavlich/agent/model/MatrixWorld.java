package org.jpavlich.agent.model;

import java.util.Random;

public class MatrixWorld implements World{
	private int data[][];
	private int width;
	private int height;
	private Random rnd = new Random(12345l);

	public MatrixWorld(int w, int h) {
		width = w;
		height = h;
		data = new int[w][h];
		initElements();
	}

	private void initElements() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (rnd.nextDouble() < 0.2) {
					data[i][j] = Math.abs(rnd.nextInt() % 3) + 1;
				} else {
					data[i][j] = 0;
				}
				
			}
			
		}
	}

	public synchronized int pick(int x, int y) {
		if (isInside(x, y)) {
			int picked = data[x][y];
			data[x][y] = 0;
			return picked;
		} else {
			return 0;
		}

	}

	private boolean isInside(int x, int y) {
		return x >= 0 && x < width && y >= 0 && y < height;
	}

	public synchronized boolean drop(int x, int y, int elem) {
		if (isInside(x, y) && data[x][y] == 0 && elem != 0) {
			data[x][y] = elem;
			return true;
		} else {
			return false;
		}

	}
	
	public synchronized int get(int x, int y) {
		return data[x][y];
	}
	

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}



}
