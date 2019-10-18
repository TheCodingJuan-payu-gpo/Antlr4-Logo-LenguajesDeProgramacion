package co.edu.javeriana.logo.ast;

import co.edu.javeriana.interpreter.interpreta.Context;
import java.util.List;

public class Function_Call implements ASTNode {
	
	private String name;
	private List<ASTNode> parameters; 
	
	public Function_Call(String name, List<ASTNode> parameters) 
	{
		super();
		this.name = name;
		this.parameters = parameters;
	}

	@Override
	public Object execute(Context symbolTable) 
	{
		
		Function func = (Function)symbolTable.get(name);
		
		Context local_context = new Context(func.getMy_context());
		
		List<String> arguments_list = func.getArguments();
			
		for(int i=0;i<arguments_list.size();++i) 
		{
			local_context.put(arguments_list.get(i) ,parameters.get(i).execute(symbolTable));
		}
		
		for(ASTNode n:func.getBody()) 
		{
			Object task = n.execute(local_context);
			
			if(task!=null) {
				return task;
			};
		}
		return null;
	}


}