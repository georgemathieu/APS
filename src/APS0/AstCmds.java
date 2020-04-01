package APS0;

import APS0.interfaces.IASTvisitor;

public class AstCmds implements Ast {

	private AstEcho statement;
	private AstDec declaration;
	private AstCmds commands;

	public AstCmds(AstEcho obj) {
		this.statement = obj;
	}

	public AstCmds(AstEcho obj, AstCmds obj2) {
		this.statement = obj;
		this.commands = obj2;
	}

	public AstCmds(AstDec obj, AstCmds obj2) {
		this.declaration = obj;
		this.commands = obj2;
	}

	@Override
	public String toPrologString() {
		if (declaration != null) {
			return "commands(declaration(" + declaration.toPrologString() + ")," + commands.toPrologString() + ")";
		} else if (commands != null) {
			return "commands(" + statement.toPrologString() + "," + commands.toPrologString() + ")";
		}
		return "commands(" + statement.toPrologString() + ")";
	}
	
	public <Result, Data, Anomaly extends Throwable> Result accept(
			IASTvisitor<Result, Data, Anomaly> visitor, Data data) throws Anomaly {
		 return visitor.visit(this, data);
	}

	public AstEcho getStatement() {
		return statement;
	}

	public void setStatement(AstEcho statement) {
		this.statement = statement;
	}

	public AstDec getDeclaration() {
		return declaration;
	}

	public void setDeclaration(AstDec declaration) {
		this.declaration = declaration;
	}

	public AstCmds getCommands() {
		return commands;
	}

	public void setCommands(AstCmds commands) {
		this.commands = commands;
	}
	
	

}
