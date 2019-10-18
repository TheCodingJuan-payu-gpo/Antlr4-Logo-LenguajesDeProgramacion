package co.edu.javeriana.logo.ast;


import co.edu.javeriana.interpreter.interpreta.Context;

public class Constant implements ASTNode {
	
	private Object value;

	public Constant(Object value) {
		super();
		this.value = value;
	}



	@Override
	public Object execute(Context symbolTable) {

		return value;
	}

}
