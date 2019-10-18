package co.edu.javeriana.logo.ast;

import co.edu.javeriana.interpreter.interpreta.Context;


public class VariableDeclare implements ASTNode {

	private String name;
	
	
	
	public VariableDeclare(String name) {
		super();
		this.name = name;
	}

	@Override
	public Object execute(Context symbolTable) 
	{
		symbolTable.put(name, new Object());
		return null;
	}

}
