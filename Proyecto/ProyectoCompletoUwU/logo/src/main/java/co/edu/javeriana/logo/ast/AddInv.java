package co.edu.javeriana.logo.ast;

import co.edu.javeriana.interpreter.interpreta.Context;

public class AddInv implements ASTNode
{
	private ASTNode operand1;
	
	public AddInv(ASTNode x)
	{
		super();
		this.operand1=x;
	}
	
	@Override
	public Object execute(Context symbolTable)
	{
		return (float)operand1.execute(symbolTable);
	}
}
