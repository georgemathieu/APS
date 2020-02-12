LEX_J  = jflex-1.7.0/bin/jflex
YACC_J = byaccj1.15_linux/yacc.linux -J
JAVAC = javac

parser: parser.y lexer.lex
	$(LEX_J) lexer.lex
	$(YACC_J) parser.y

prologTerm: parser Op.java PrologTerm.java
	$(JAVAC) PrologTerm.java
		
clean:
	rm Parser*.java
	rm Yylex.java
	rm *.class
