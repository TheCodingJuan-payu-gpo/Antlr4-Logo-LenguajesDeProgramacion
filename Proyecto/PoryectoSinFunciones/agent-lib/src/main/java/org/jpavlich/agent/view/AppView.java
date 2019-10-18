package org.jpavlich.agent.view;

import org.jpavlich.agent.Executor;

import processing.core.PApplet;

/**
 * Representa la ventana donde se ejecutará el programa del agente. 
 * 
 * @author jaime.pavlich-mariscal
 * 
 */
public final class AppView extends PApplet {
	private LayerManager layers;

	private Executor executor;
	private long waitTime;
	private WorldView worldView;
	private Layer agentViews[];

	public AppView(Executor executor, long waitTime, WorldView worldView, Layer... agentViews) {
		this.waitTime = waitTime;
		this.executor = executor;
		this.worldView = worldView;
		this.agentViews = agentViews;
		
	}

	@Override
	public void settings() {
		size(800, 600);
	}

	public void setup() {
		background(0);
		layers = new LayerManager(this);
		layers.add(worldView);

		for (Layer av : agentViews) {
			layers.add(av);
		}

	}


	@Override
	public void draw() {
		executor.executeNext();
		layers.draw();
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void dispose() {
		super.dispose();
		System.exit(0);
	}
	

}