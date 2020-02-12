package APS0;

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
			return "Commands(" + declaration.toPrologString() + "," + commands.toPrologString() + ")";
		} else if (commands != null) {
			return "Commands(" + statement.toPrologString() + "," + commands.toPrologString() + ")";
		}
		return "Commands(" + statement.toPrologString() + ")";
	}

}
