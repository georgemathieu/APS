package APS0;


import java.io.*;

import APS0.interpreter.EmptyLexicalEnvironment;
import APS0.interpreter.GlobalVariableEnvironment;
import APS0.interpreter.Interpreter;
import APS0.interpreter.interfaces.EvaluationException;
import APS0.interpreter.interfaces.IGlobalVariableEnvironment;

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
		
		final Interpreter interpreter = new Interpreter((IGlobalVariableEnvironment) new GlobalVariableEnvironment());
		System.out.println("Start-Interpretation");
		try {
			interpreter.visit((AstProgram) prog, new EmptyLexicalEnvironment());
		} catch (EvaluationException ex2) {
			ex2.printStackTrace();
		}
		System.out.println("End-Interpretation\n\n");
	}
}