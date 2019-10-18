package co.edu.javeriana.logo.ast;


import co.edu.javeriana.interpreter.interpreta.Context;
import co.edu.javeriana.logo.Turtle;

public class RotateLeft implements ASTNode {
	private ASTNode operand1;
	private Turtle agente;
	
	
	public RotateLeft(ASTNode operand1, Turtle tr) {
		super();
		this.operand1 = operand1;
		this.agente = tr;
	}



	@Override
	public Object execute(Context symbolTable) {
		agente.left((float)operand1.execute(symbolTable));
		return null;
	}

}
