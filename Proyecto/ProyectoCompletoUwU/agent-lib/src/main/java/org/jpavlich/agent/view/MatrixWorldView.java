package org.jpavlich.agent.view;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;

import org.jpavlich.agent.model.MatrixWorld;

import processing.core.PApplet;
import processing.core.PImage;

public class MatrixWorldView extends WorldView {

	private PImage[] sprites;
	private MatrixWorld world;
	private String defaultSprite;
	private int stepSize;

	public MatrixWorldView(MatrixWorld world, int stepSize, String defaultSprite) {
		this.world = world;
		this.stepSize = stepSize;
		this.defaultSprite = defaultSprite;
	}

	private void loadSprites(PApplet applet, String defaultSprite) {
		File imageDir = new File("images");
		File[] spriteFiles = imageDir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().matches("[0-9]+\\.png");
			}
		});
		Arrays.sort(spriteFiles);
		sprites = new PImage[spriteFiles.length + 1];
		sprites[0] =  applet.loadImage(defaultSprite);
		for (int i = 0; i < spriteFiles.length; i++) {
			try {
				sprites[i + 1] = applet.loadImage(spriteFiles[i].getCanonicalPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void init(PApplet applet) {
		loadSprites(applet, defaultSprite);
		applet.draw();
	}

	@Override
	public void draw() {
		g.background(0, 0);
		int w = world.getWidth() * stepSize;
		int h = world.getHeight()* stepSize;
		for (int x = 0; x < w; x += stepSize) {
			g.stroke(128, 128, 128, 255);
			g.line(x, 0, x, g.height);
			for (int y = 0; y < h; y += stepSize) {
				g.stroke(128, 128, 128, 255);
				g.line(0, y, g.width, y);
				int elem = world.get(x / stepSize, y / stepSize);
				if (elem == 0) {
					g.stroke(0, 0, 0, 255);
					g.fill(0, 0, 0, 255);
					g.rect(x + 1, y + 1, stepSize - 2, stepSize - 2);
				} else {
					if (elem < sprites.length) {
						g.image(sprites[elem], x, y);
						g.stroke(255, 255, 255, 255);
						g.fill(255, 255, 255, 255);
						g.text(elem, x+5, y+15);
					} else {
						g.image(sprites[0], x, y);
						g.stroke(128, 128, 128, 255);
						g.text(elem, x+5, y+15);
					}
				}
			}
		}
	}

}
