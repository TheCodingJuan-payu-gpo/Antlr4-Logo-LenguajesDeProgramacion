package co.edu.javeriana.logo.ast;



import co.edu.javeriana.interpreter.interpreta.Context;

public class And implements ASTNode {

	private ASTNode operand1;
	private ASTNode operand2;
	
	
	public And(ASTNode operand1, ASTNode operand2) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
	}


	@Override
	public Object execute(Context symbolTable) {
		// TODO Auto-generated method stub
		return (Boolean)operand1.execute(symbolTable)&&(Boolean)operand2.execute(symbolTable);
	}

}
