package co.edu.javeriana.interpreter.interpreta;

import java.util.HashMap;

public class Context {

	private HashMap<String, Object> tabla;
	protected Context anterior = null;
	
	public Context(Context ant)
	{
		tabla = new HashMap<String, Object>();
		anterior = ant;
	}
	
	public Context()
	{
		tabla = new HashMap<String,Object>();
	}
	
	public void putDecl(String simbolo,Object statement)
	{
		if(this.get(simbolo)!=null)
		{
			this.tabla.put(simbolo, statement);
		}
		else
		{
			this.anterior.putDecl(simbolo, statement);
		}
	}
	
	public void put(String simbolo, Object statement)
	{
		tabla.put(simbolo, statement);
	}
	
	public Object  get(String palabra)
	{
		for(Context e = this; e!=null;e=e.anterior)
		{
			Object encontrado = (Object) (e.tabla.get(palabra));
			if(encontrado != null)
			{
				return encontrado;
			}
			
		}
		return null;
	}
}
