package org.jpavlich.agent.model;

import java.util.concurrent.Callable;

import org.jpavlich.agent.Executor;

public class AgentProxy<A extends IAgent> implements IAgent {

	protected A agent;
	private Executor executor;
	
	
	
	public AgentProxy(A agent, Executor executor) {
		super();
		this.agent = agent;
		this.executor = executor;
	}



	public void execute(Runnable action) {
		executor.submit(action);
	}
	
	public <T> T execute(Callable<T> action) {
		return executor.submit(action);
	}
	
	@Override
	public synchronized float move(float dx, float dy) {
		return execute(() -> agent.move(dx, dy));
	}

	@Override
	public synchronized void forward(float amount) {
		execute(() -> agent.forward(amount));
		
	}
	

	@Override
	public synchronized void backwards(float amount) {
		execute(() -> agent.backwards(amount));
		
	}

	@Override
	public synchronized void rotate(float amount) {
		execute(() -> agent.rotate(amount));
		
	}

	@Override
	public synchronized float getRotation() {
		return execute(() -> agent.getRotation());
	}

	@Override
	public synchronized float getY() {
		return execute(() -> agent.getY());
	}

	@Override
	public synchronized float getX() {
		return execute(() -> agent.getX());
	}
	
	@Override
	public synchronized void color(float red, float green, float blue, float alpha) {
		execute (()->agent.color(red,green,blue,alpha));
	}
	

	@Override
	public synchronized void left(float amount) {
		execute(() -> agent.left(amount));
		
	}
	

	@Override
	public synchronized void right(float amount) {
		execute(() -> agent.right(amount));
		
	}
	

}
