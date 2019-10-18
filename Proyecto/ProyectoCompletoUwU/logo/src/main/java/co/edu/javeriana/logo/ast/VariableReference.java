package co.edu.javeriana.logo.ast;


import co.edu.javeriana.interpreter.interpreta.Context;

public class VariableReference implements ASTNode {

	private String name;
	
	public VariableReference(String name) {
		super();
		this.name = name;
	}

	@Override
	public Object execute(Context symbolTable) {
		return symbolTable.get(name);
	}

}
