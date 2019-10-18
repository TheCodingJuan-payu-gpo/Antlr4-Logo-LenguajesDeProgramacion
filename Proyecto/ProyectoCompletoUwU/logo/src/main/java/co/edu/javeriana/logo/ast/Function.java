package co.edu.javeriana.logo.ast;

import java.util.List;

import co.edu.javeriana.interpreter.interpreta.Context;

public class Function implements ASTNode {
	
	private String name;
	private List<String> arguments;
	private Context symbolTable;
	private List<ASTNode> body;

	
	
	public Function(String name, List<String> arguments, List<ASTNode> body) {
		super();
		this.name = name;
		this.arguments = arguments;
		this.body = body;
	}
	
	public String getName() {
		return name;
	}
	
	public List<String> getArguments() {
		return arguments;
	}

	public Context getMy_context() {
		return symbolTable;
	}
	
	public List<ASTNode> getBody() {
		return body;
	}

	@Override
	public Object execute(Context symbolTable) 
	{
		this.symbolTable = symbolTable;
		this.symbolTable.put(name, this);
		
		return null;
	}

}