package co.edu.javeriana.logo.ast;


import co.edu.javeriana.interpreter.interpreta.Context;

public class VariableDeclareAssign implements ASTNode {

	private String name;
	private ASTNode expression;
	
	public VariableDeclareAssign(String name, ASTNode expression) {
		super();
		this.name = name;
		this.expression = expression;
	}



	@Override
	public Object execute(Context symbolTable) {
		symbolTable.put(name, expression.execute(symbolTable));
		return null;
	}

}
