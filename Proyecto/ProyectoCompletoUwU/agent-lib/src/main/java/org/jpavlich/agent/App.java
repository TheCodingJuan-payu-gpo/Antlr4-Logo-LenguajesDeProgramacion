package org.jpavlich.agent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;

import org.jpavlich.agent.model.IAgent;
import org.jpavlich.agent.model.World;
import org.jpavlich.agent.view.AppView;

import processing.core.PApplet;

public class App<W extends World, P extends IAgent> {
	private AppView applet;
	private P proxy;
	private W world;

	public App(W world, P proxy, AppView applet) {
		this.world = world;
		this.proxy = proxy;
		this.applet = applet;
	}

	public void run(BiConsumer<W, P> script) {
		ExecutorService e = Executors.newFixedThreadPool(1);
		e.execute(() -> {
			try {
				script.accept(world, proxy);
			} catch (Throwable t) {
				t.printStackTrace();
			}
		});
		PApplet.runSketch(new String[] { AppView.class.getName() }, applet);
	}

	

}
