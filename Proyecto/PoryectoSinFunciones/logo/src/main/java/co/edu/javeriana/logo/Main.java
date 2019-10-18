
package co.edu.javeriana.logo;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.jpavlich.agent.App;
import org.jpavlich.agent.Executor;
import org.jpavlich.agent.model.Agent;
import org.jpavlich.agent.model.SimpleWorld;
import org.jpavlich.agent.model.World;
import org.jpavlich.agent.view.AppView;
import org.jpavlich.agent.view.DrawingView;
import org.jpavlich.agent.view.SpriteView;
import org.jpavlich.agent.view.WorldView;

public class Main {

	private static final String EXTENSION = "logo";

	public static void main(String[] args) {
		App<World, Turtle> app = initTurtle();
		app.run(Main::program);

	}

	private static void program(World world, Turtle turtle) {
		try {
			String program = "test/test." + EXTENSION;

			System.out.println("Interpreting file " + program);

			LogoLexer lexer = new LogoLexer(new ANTLRFileStream(program));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			LogoParser parser = new LogoParser(tokens, turtle);
			LogoParser.ProgramContext tree = parser.program();
			LogoCustomVisitor visitor = new LogoCustomVisitor();
			visitor.visit(tree);
			System.out.println("Interpretation finished");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void program2(World world, Turtle turtle) {
		turtle.color(255, 0, 0, 255);
		turtle.forward(200);
		turtle.right(90);
		turtle.color(0, 255, 0, 255);
		turtle.forward(200);
		turtle.right(90);
		turtle.color(0, 0, 255, 255);
		turtle.forward(200);
		turtle.right(90);
		turtle.color(255, 255, 0, 255);
		turtle.forward(200);
		turtle.right(90);
		turtle.color(0, 255, 255, 255);
	}

	private static App<World, Turtle> initTurtle() {
		Executor executor = new Executor();
		SimpleWorld world = new SimpleWorld(800, 600);
		Agent agent = new Agent(world);
		Turtle turtle = new Turtle(agent, executor);
		WorldView worldView = new WorldView();
		DrawingView drawing = new DrawingView(agent);
		SpriteView<Agent> agentView = new SpriteView<>(agent, 1, "./images/turtle.png");
		AppView applet = new AppView(executor, 150, worldView, drawing, agentView);
		return new App<>(world, turtle, applet);
	}


}
