package co.edu.javeriana.logo.ast;

import co.edu.javeriana.interpreter.interpreta.Context;
import co.edu.javeriana.logo.Turtle;

public class MoveBack implements ASTNode {
	private ASTNode operand1;
	private Turtle agente;
	
	
	public MoveBack(ASTNode operand1, Turtle tr) {
		super();
		this.operand1 = operand1;
		this.agente = tr;
	}



	@Override
	public Object execute(Context symbolTable) {
		agente.backwards((float)operand1.execute(symbolTable));
		return null;
	}

}
