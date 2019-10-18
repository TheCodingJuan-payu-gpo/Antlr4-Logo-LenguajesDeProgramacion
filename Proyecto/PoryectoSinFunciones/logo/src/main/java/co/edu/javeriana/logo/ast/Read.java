package co.edu.javeriana.logo.ast;

import java.util.Scanner;

import co.edu.javeriana.interpreter.interpreta.Context;

public class Read implements ASTNode 
{
	private String ID;

	public Read(String ID1) 
	{
		super();
		this.ID = ID1;
	}
	
	@Override
	public Object execute(Context symbolTable) 
	{
		Scanner scanner = new Scanner(System.in);
		
		String lectura = scanner.next();
		
		symbolTable.put(ID,lectura);
		
		scanner.close();
		
		return null;
	}
	
}
