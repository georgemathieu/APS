%{
	import java.io.*;
	import java.util.ArrayList;
%}

%token NL                    /* newline  */
%token <ival> NUM 	         /* a number */
%token <sval> IDENT          /* an identifier */
%token PLUS MINUS TIMES DIV  /* operators */
%token LPAR RPAR             /* parenthesis */
%token LCRO RCRO             /* crochets */
%token ECHO 				 /* echo */
%token BOOL 				 /* bool */
%token INT					 /* int  */
%token CONST			 	 /* const  */
%token FUN			 	     /* fun  */
%token VIRGULE			 	 /* virgule  */
%token DEUXPOINTS			 /* deuxpoints */
%token REC			 	 	 /* rec  */
%token AND			 		 /* and  */
%token OR					 /* or */
%token IF					 /* if */
%token NOT			 		 /* not  */
%token FLECHE			 	 /* fleche  */
%token ETOILE			 	 /* etoile  */
%token EQ				 	 /* eq  */
%token LT					 /* lt  */
%token FALSE				 /* true  */
%token TRUE					 /* false  */
%token POINTVIRGULE			 /* point virgule */

%type <obj> progs
%type <obj> cmds
%type <obj> stat
%type <obj> dec
%type <obj> type
%type <obj> types
%type <obj> expr
%type <obj> exprs
%type <obj> arg
%type <obj> args


%start progs

%%

progs : LCRO cmds RCRO { $$= new AstProgram((AstCmds)$2); }
;

cmds:  stat   			 {  $$ = new AstCmds((AstEcho)$1); }
| stat POINTVIRGULE cmds {  $$ = new AstCmds((AstEcho)$1, (AstCmds)$3); }
| dec  POINTVIRGULE cmds {  $$ = new AstCmds((AstDec)$1, (AstCmds)$3); }
;

stat : 
ECHO  expr 	  { $$ = new AstEcho((Ast)$2); }
;

expr:NUM                 	{ $$ = new AstNum($1); }
| TRUE						{ $$ = new AstBool("true"); }
| FALSE						{ $$ = new AstBool("false"); }
| IDENT                  	{ $$ = new AstId($1); }
| LPAR IF  expr expr expr RPAR { $$ = new AstIF ((Ast)$3,(Ast)$4,(Ast)$5); }
| LPAR PLUS exprs RPAR   	{ $$ = new AstPrim(Op.ADD,(ArrayList<Ast>)$3); }
| LPAR MINUS exprs RPAR  	{ $$ = new AstPrim(Op.SUB,(ArrayList<Ast>)$3); }
| LPAR TIMES exprs RPAR  	{ $$ = new AstPrim(Op.MUL,(ArrayList<Ast>)$3); }
| LPAR DIV exprs RPAR    	{ $$ = new AstPrim(Op.DIV,(ArrayList<Ast>)$3); }
| LPAR NOT exprs RPAR 		{ $$ = new AstPrim(Op.NOT,(ArrayList<Ast>)$3); }
| LPAR OR exprs RPAR 		{ $$ = new AstPrim(Op.OR,(ArrayList<Ast>)$3); }
| LPAR AND exprs RPAR 		{ $$ = new AstPrim(Op.AND,(ArrayList<Ast>)$3); }
| LPAR LT exprs RPAR 		{ $$ = new AstPrim(Op.LT,(ArrayList<Ast>)$3); }
| LPAR EQ exprs RPAR 		{ $$ = new AstPrim(Op.EQ,(ArrayList<Ast>)$3); }
| LPAR expr RPAR 		 	{ $$ = (Ast)$2 ;}
| LCRO args RCRO expr  	 	{ $$ = new AstAbtract((ArrayList<AstArg>)$2,(Ast)$4);}
| LPAR expr exprs RPAR      { $$ = new AstApp((Ast)$2,(ArrayList<Ast>)$3); }
;

exprs:
expr                   { ArrayList<Ast> r = new ArrayList<Ast>();
						r.add((Ast)$1); $$ = r; }
| expr exprs           { ((ArrayList<Ast>)$2).add((Ast)$1); $$ = $2; }
;

dec : 
CONST IDENT type expr { $$ = new AstConst(new AstId($2),(AstType)$3,(Ast)$4);}
|FUN IDENT type LCRO args RCRO expr 
	{$$ = new AstFun(new AstId($2),(AstType)$3,(ArrayList<AstArg>)$5,(Ast)$7);}
|FUN REC IDENT type LCRO args RCRO expr {$$ = new AstFunRec(new AstId($3),(AstType)$4,(ArrayList<AstArg>)$6,(Ast)$8);}
;


type : 
BOOL   { ArrayList<String> ar = new ArrayList<String>(); ar.add("bool");
		$$ = new AstType(ar);}
|INT   { ArrayList<String> ar = new ArrayList<String>(); ar.add("int");
		$$ = new AstType(ar);}
|LPAR types FLECHE type RPAR { ((ArrayList<AstType>)$2).add((AstType)$4);
								ArrayList<String> ar = new ArrayList<String>();
								for(AstType at : (ArrayList<AstType>)$2){ar.addAll(at.getNom());}
								AstType s = new AstType(ar); $$ = s;}
;

types : 
type 	{ ArrayList<AstType> r = new ArrayList<AstType>();
						r.add((AstType)$1); $$ = r; }
|type ETOILE types {((ArrayList<AstType>)$3).add((AstType)$1); $$ = $3;}
;

arg :
IDENT DEUXPOINTS type { $$ = new AstArg(new AstId($1),(AstType)$3);}

args :
 arg { ArrayList<AstArg> r = new ArrayList<AstArg>();
		r.add((AstArg)$1); $$ = r; }
| arg VIRGULE args	{ ((ArrayList<AstArg>)$3).add((AstArg)$1); $$ = $3; }
;


%%

	public Ast prog;
	
	private Yylex lexer;
	
	private int yylex () {
		int yyl_return = -1;
		try {
			yylval = new ParserVal(0);
			yyl_return = lexer.yylex();
		} catch (IOException e) {
			System.err.println("IO error :"+e);
		}
		return yyl_return;
	}
	
	public void yyerror (String error) {
		System.err.println ("Error: " + error);
	}
	
	public Parser(Reader r) {
		lexer = new Yylex(r, this);
	}
		
		
		
		
		