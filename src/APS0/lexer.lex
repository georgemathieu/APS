%%

%byaccj

%{
	private Parser yyparser;

	public Yylex(java.io.Reader r, Parser yyparser) {
	 this(r);
	 this.yyparser = yyparser;
	}
%}

nums = -?[0-9]+
ident = [a-z][a-zA-Z0-9]*
nls  = \n | \r | \r\n
%%

/* operators */
"add"  { return Parser.PLUS; }
"sub"  { return Parser.MINUS; }
"mul"  { return Parser.TIMES; }
"div"  { return Parser.DIV; }

/* parenthesis */
"("  { return Parser.LPAR; }
")"  { return Parser.RPAR; }

/* crochets */
"["  { return Parser.LCRO; }
"]"  { return Parser.RCRO; }

/* fleche */
"->"  { return Parser.FLECHE; }

/* etoile */
"*"  { return Parser.ETOILE; }

/* virgule */
","  { return Parser.VIRGULE; }

/* deuxpoints */
":"  { return Parser.DEUXPOINTS; }

/* pointvirgule */
";"  { return Parser.POINTVIRGULE; }

/* newline */
{nls}   { return 0; } //{ return Parser.NL; }

/* echo */
"ECHO" {return Parser.ECHO; }

/* fun */
"FUN" {return Parser.FUN; }

/* true */
"TRUE" {return Parser.TRUE; }

/* false */
"FALSE" {return Parser.FALSE; }

/* bool */
"bool" {return Parser.BOOL; }

/* int */
"int" {return Parser.INT; }

/* const */
"CONST" {return Parser.CONST; }

/* rec */
"REC" {return Parser.REC; }

/* not */
"not" {return Parser.NOT; }

/* and */
"and" {return Parser.AND; }

/* or */
"or" {return Parser.OR; }

/* eq */
"eq" {return Parser.EQ; }

/* lt */
"lt" {return Parser.LT; }

/* if */
"if" {return Parser.IF; }


{nums}  { yyparser.yylval = new ParserVal(Integer.parseInt(yytext()));return Parser.NUM; }
{ident} { yyparser.yylval = new ParserVal(yytext());return Parser.IDENT;}


/* whitespace */
[ \t]+ { }
\b     { System.err.println("Sorry, backspace doesn’t work"); }

/* error fallback */
[^]    { System.err.println("Error: unexpected character ’"+yytext()+"’"); return -1; }
