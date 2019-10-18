package co.edu.javeriana.logo.ast;

import co.edu.javeriana.interpreter.interpreta.Context;

public interface ASTNode {
	
	public Object execute(Context symbolTable);
}
