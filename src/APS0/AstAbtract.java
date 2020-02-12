package APS0;

import java.util.ArrayList;

public class AstAbtract implements Ast{
	
	private ArrayList<AstArg> args;
	private Ast expression;

	public AstAbtract(ArrayList<AstArg> args, Ast exp) {
		this.args = args;
		this.expression = exp;
		
	}

	@Override
	public String toPrologString() {
		StringBuilder sb = new StringBuilder("Abstract(");
		for (int i = args.size() - 1; i > 0; i--) {
			sb.append(args.get(i).toPrologString()+",");
		}
		sb.append(args.get(0).toPrologString());
		sb.append("," + expression.toPrologString() + ")");
		return sb.toString();
	}
	

}
