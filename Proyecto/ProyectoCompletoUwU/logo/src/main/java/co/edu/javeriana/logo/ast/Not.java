package co.edu.javeriana.logo.ast;


import co.edu.javeriana.interpreter.interpreta.Context;

public class Not implements ASTNode {

	private ASTNode operand1;
	
	
	public Not(ASTNode operand1) {
		super();
		this.operand1 = operand1;
	}


	@Override
	public Object execute(Context symbolTable) {
		// TODO Auto-generated method stub
		
		return !(Boolean)operand1.execute(symbolTable);
	}

}
