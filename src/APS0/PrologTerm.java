package APS0;


import java.io.*;

class PrologTerm {
	public static void main(String args[]) throws IOException {
		Parser yyparser;
		Ast prog;
		yyparser = new Parser(new InputStreamReader(new FileInputStream(args[0])));
		yyparser.yyparse();
		prog = (Ast) yyparser.yyval.obj;
		if (prog != null)
			System.out.println(prog.toPrologString());
		else
			System.out.println("Null");
	}
}