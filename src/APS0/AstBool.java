package APS0;

public class AstBool implements Ast {
	private String b;
	
	public AstBool(String sval)
	{
		this.b = sval;
	}

	@Override
	public String toPrologString() {
		if (b == "true")
		return "true";
		else
		return "false";
	}
}
