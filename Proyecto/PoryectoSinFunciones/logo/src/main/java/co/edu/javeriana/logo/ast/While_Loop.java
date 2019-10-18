package co.edu.javeriana.logo.ast;

import java.util.List;

import co.edu.javeriana.interpreter.interpreta.Context;

public class While_Loop implements ASTNode {
	private ASTNode condition;
	private List<ASTNode> body;

	
	
	public While_Loop(ASTNode condition, List<ASTNode> body) {
		super();
		this.condition = condition;
		this.body = body;
	}



	@Override
	public Object execute(Context symbolTable) {
		while((boolean)condition.execute(symbolTable)) {
			for(ASTNode n: body) {
				n.execute(symbolTable);
			}
		}
		return null;
	}

}
