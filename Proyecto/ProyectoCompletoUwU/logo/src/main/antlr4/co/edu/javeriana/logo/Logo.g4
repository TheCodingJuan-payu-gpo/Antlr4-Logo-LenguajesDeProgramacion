grammar Logo;

@parser::header{
	import java.util.Map;
	import java.util.HashMap;
	import co.edu.javeriana.logo.ast.*;
	import co.edu.javeriana.interpreter.interpreta.*;	
}

@parser::members {
	Map<String, Object> symbolTable = new HashMap<String, Object>();


private Turtle turtle;

public LogoParser(TokenStream input, Turtle turtle) 
{
    this(input);
    this.turtle = turtle;
}

}

program: 
{
	List<ASTNode> body = new ArrayList<ASTNode>();
	Context symbolTable = new Context();
	}
	(sentence{body.add($sentence.node);})* 
	{
		for(ASTNode n: body)
		{
			n.execute(symbolTable);
		}
	};

		
sentence returns[ASTNode node]:
		 var_decl{$node = $var_decl.node;} 
		 |var_assign{$node = $var_assign.node;}
		 |var_decl_assign{$node = $var_decl_assign.node;}
		 |println{$node = $println.node;}
		 |read{$node = $read.node;}
		 |set_color{$node = $set_color.node;}
		 |move_forw{$node = $move_forw.node;}
		 |move_back{$node = $move_back.node;}
		 |rot_l{$node = $rot_l.node;}
		 |rot_r{$node = $rot_r.node;}
		 |conditional{$node = $conditional.node;}
		 |while_loop{$node = $while_loop.node;}
		 |function {$node = $function.node;}
    	 |function_call {$node = $function_call.node;};
		 
		
function returns [ASTNode node]
:

		{
			List<ASTNode> body = new ArrayList<ASTNode>();
			List<String> parameters = new ArrayList<String>();
		}
		
		DEF id=ID PAR_OPEN (par1=ID{parameters.add($par1.text);} (COMMA)*(par2=ID{parameters.add($par2.text);}))? 
		
		PAR_CLOSE DOSP
		
		(s1=sentence{body.add($s1.node);})* 
		
		END_DEF
		{ $node = new Function($id.text,parameters,body); }
;

function_call returns [ASTNode node]
:
	{
		List<ASTNode> parameters = new ArrayList<ASTNode>();
	}
	ID PAR_OPEN (expression{parameters.add($expression.node);})? PAR_CLOSE
	{$node = new Function_Call($ID.text,parameters); } 
	|
	{
		List<ASTNode> parameters = new ArrayList<ASTNode>();
	}
	 ID PAR_OPEN (t1=expression{parameters.add($t1.node);} COMMA)*(t2=expression{parameters.add($t2.node);}) PAR_CLOSE 
	{$node = new Function_Call($ID.text,parameters); }
;
		
println returns[ASTNode node]: PRINTLN expression
	{$node = new Println($expression.node);};
	
move_forw returns[ASTNode node]: MOVE_FORW expression
	{$node = new MoveForw($expression.node, turtle);};
	
move_back returns[ASTNode node]: MOVE_BACK expression
	{$node = new MoveBack($expression.node, turtle);};
	
rot_l returns[ASTNode node]: ROT_L expression
	{$node = new RotateLeft($expression.node, turtle);};
	
rot_r returns[ASTNode node]: ROT_R expression
	{$node = new RotateRight($expression.node, turtle);};
	
set_color returns[ASTNode node]: SET_COLOR e1=expression COMMA e2=expression COMMA e3=expression COMMA  e4=expression
	{$node = new SetColor($e1.node,$e2.node,$e3.node,$e4.node, turtle);};

var_decl returns [ASTNode node]: LET ID
	{$node = new VariableDeclare($ID.text);};

var_decl_assign returns [ASTNode node]: LET ID ASSIGN expression
	{$node = new VariableDeclareAssign($ID.text, $expression.node);};
	
var_assign returns [ASTNode node]: ID ASSIGN expression
	{$node = new VariableAssign ($ID.text, $expression.node);};
		
while_loop returns [ASTNode node]:
	WHILE logic 
	{
		List<ASTNode> whileBody = new ArrayList<ASTNode>();
	}
	DO (s1=sentence {whileBody.add($s1.node);})* END_WHILE
	{
		$node = new While_Loop($logic.node, whileBody);
	};
	
conditional returns[ASTNode node]: 
	IF logic
	{
		List<ASTNode> ifBody = new ArrayList<ASTNode>();
	}
	THEN (s1=sentence {ifBody.add($s1.node);})* 
	{
			List<ASTNode> elseBody = new ArrayList<ASTNode>();
	}
	(ELSE
	(s2=sentence {elseBody.add($s2.node);})*)* END_IF
	{
		$node = new If($logic.node, ifBody, elseBody);
	};	


logic returns [ASTNode node]:
		 f1=comparison {$node = $f1.node;}
		 (AND f2=comparison {$node = new And($f1.node,$f2.node);}
		 |
		 OR f2=comparison {$node = new Or($f1.node,$f2.node);})*;
	  		 	
comparison returns [ASTNode node]:
		 C1=expression {$node = $C1.node;}
		 (GT C2=expression {$node = new GreaterThan($C1.node,$C2.node);}
		 |LT C2=expression {$node = new LowerThan($C1.node,$C2.node);}
		 |GEQ C2=expression {$node = new GreaterEqualThan($C1.node,$C2.node);}
		 |LEQ C2=expression {$node = new LowerEqualThan($C1.node,$C2.node);}
		 |EQ C2=expression {$node = new EqualTo($C1.node,$C2.node);}
		 |DIF C2=expression {$node = new Dif($C1.node,$C2.node);})
		 |NOT C2=expression {$node = new Not($C2.node);};

		 	
expression returns [ASTNode node]:
		 f1=factor {$node = $f1.node;}
		 	(PLUS f2=factor {$node = new Addition($f1.node,$f2.node);}
		 	|
		 	MINUS f2=factor {$node = new Substraction($f1.node,$f2.node);})*;

factor returns [ASTNode node]: 
		ai1=term {$node = $ai1.node;}
			(MULT ai2=term {$node = new Multiplication($ai1.node,$ai2.node);}
			|
			DIV ai2=term {$node = new Division($ai1.node,$ai2.node);}	
			)*;

term returns[ASTNode node]:
		 NUM {$node = new Constant (Float.parseFloat($NUM.text));}
		 |MINUS term {$node = new AddInv($term.node);}
		 |STRING {$node = new Constant(String.valueOf($STRING.text).replace("\"",""));}
		 |BOOL {$node = new Constant(Boolean.parseBoolean($BOOL.text));} 
		 |ID {$node = new VarRef($ID.text); }
		 |PAR_OPEN expression {$node = $expression.node;} PAR_CLOSE;
		 		
			
read returns [ASTNode node]: 
		READ ID {$node = new Read($ID.text);};

MOVE_FORW:'move_forw';
MOVE_BACK:'move_back';
ROT_L:'rot_l';
ROT_R:'rot_r';
SET_COLOR:'set_color';
LET:'let';
PRINTLN: 'println';
READ: 'read';

PLUS:'+';
MINUS:'-';
MULT:'*';
DIV:'/';

AND:'&&';
OR:'||';
NOT:'!';

GT: '>';
LT: '<';
GEQ: '>=';
LEQ: '<=';
EQ: '==';
DIF: '<>';

ASSIGN:'=';

PAR_OPEN:'(';
PAR_CLOSE:')';
COMMA:',';
DOSP:':';

IF:'if';
THEN:'then';
END_IF:'end_if';
ELSE:'else';
DO:'do';
WHILE:'while';
END_WHILE:'end_while';
DEF:'def';
END_DEF:'end_def';
BOOL: 'true' | 'false';
ID:[a-zA-Z_][a-zA-Z0-9_]*;
NUM: [0-9]+('.'[0-9]+)?;

STRING: '"' ~('"')* '"';

WS
:
	[ \t\r\n]+ -> skip
;