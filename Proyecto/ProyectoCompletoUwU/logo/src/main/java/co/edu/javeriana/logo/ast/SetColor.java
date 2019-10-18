package co.edu.javeriana.logo.ast;



import co.edu.javeriana.interpreter.interpreta.Context;
import co.edu.javeriana.logo.Turtle;

public class SetColor implements ASTNode {
	private ASTNode operand1;
	private ASTNode operand2;
	private ASTNode operand3;
	private ASTNode operand4;
	private Turtle agente;
	
	
	public SetColor(ASTNode operand1,ASTNode operand2,ASTNode operand3,ASTNode operand4, Turtle tr) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.operand3 = operand3;
		this.operand4 = operand4;
		this.agente = tr;
	}



	@Override
	public Object execute(Context symbolTable) {
		agente.color((float)operand1.execute(symbolTable),(float)operand2.execute(symbolTable),(float)operand3.execute(symbolTable),(float)operand4.execute(symbolTable));
		return null;
	}

}
