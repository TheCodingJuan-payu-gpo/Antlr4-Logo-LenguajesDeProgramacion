package co.edu.javeriana.logo.ast;

import java.util.List;

import co.edu.javeriana.interpreter.interpreta.Context;

public class If implements ASTNode {
	private ASTNode condition;
	private List<ASTNode> body;
	private List<ASTNode> elseBody;

	
	
	public If(ASTNode condition, List<ASTNode> body, List<ASTNode> elseBody) {
		super();
		this.condition = condition;
		this.body = body;
		this.elseBody = elseBody;
	}



	@Override
	public Object execute(Context symbolTable) 
	{
		if((boolean)condition.execute(symbolTable)) 
		{
			for(ASTNode n: body) {
				n.execute(symbolTable);
			}
		}
		else {
			for(ASTNode n: elseBody) {
				n.execute(null);
			}
		}
		return null;
	}

}