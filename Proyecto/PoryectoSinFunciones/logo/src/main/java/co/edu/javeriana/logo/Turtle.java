package co.edu.javeriana.logo;

import org.jpavlich.agent.Executor;
import org.jpavlich.agent.model.Agent;
import org.jpavlich.agent.model.AgentProxy;

public class Turtle extends AgentProxy<Agent> implements ITurtle {

	public Turtle(Agent agent, Executor executor) {
		super(agent, executor);
	}



}
