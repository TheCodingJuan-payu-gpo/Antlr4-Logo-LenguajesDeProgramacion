package co.edu.javeriana.logo.ast;


import co.edu.javeriana.interpreter.interpreta.Context;

public class Println implements ASTNode {
	private ASTNode data;
	
	
	public Println(ASTNode data) {
		super();
		this.data = data;
	}

	@Override
	public Object execute(Context symbolTable) {
		System.out.println(data.execute(symbolTable));
		return null;
	}

}
