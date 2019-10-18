package org.jpavlich.agent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Executor {
	private BlockingQueue<FutureTask<?>> actions = new LinkedBlockingQueue<FutureTask<?>>();

	public <T> T submit(Runnable action) {
		try {
			FutureTask<T> future = new FutureTask<T>(action, null);
			actions.put(future);
			
			return (T) future.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public <T> T submit(Callable<T> action) {
		try {
			FutureTask<T> future = new FutureTask<T>(action);
			actions.put(future);
			
			return (T) future.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void executeNext() {
		FutureTask<?> nextAction;
		try {
			nextAction = actions.poll(1000, TimeUnit.MILLISECONDS);
//			nextAction = actions.take();
			if (nextAction!=null) {
				nextAction.run();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
