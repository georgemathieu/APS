package APS0;

public class AstProgram implements Ast{
	
	private AstCmds cmds;
	
	public AstProgram(AstCmds cmds)
	{
		this.cmds = cmds;
	}
	
	@Override
	public String toPrologString() {
		return "typeProg(program("+cmds.toPrologString()+")).";
	}

}
