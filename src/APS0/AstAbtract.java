package APS0;

import java.util.ArrayList;

import APS0.interfaces.IASTvisitor;

public class AstAbtract implements Ast{
	
	private ArrayList<AstArg> args;
	private Ast expression;

	public AstAbtract(ArrayList<AstArg> args, Ast exp) {
		this.args = args;
		this.expression = exp;
		
	}

	@Override
	public String toPrologString() {
		StringBuilder sb = new StringBuilder("abstract(" + "args([");
		for (int i = args.size() - 1; i > 0; i--) {
			sb.append(args.get(i).toPrologString()+",");
		}
		sb.append(args.get(0).toPrologString());
		sb.append("])");
		sb.append("," + expression.toPrologString() + ")");
		return sb.toString();
	}
	
	public <Result, Data, Anomaly extends Throwable> Result accept(
			IASTvisitor<Result, Data, Anomaly> visitor, Data data) throws Anomaly {
		 return visitor.visit(this, data);
	}

	public ArrayList<AstArg> getArgs() {
		return args;
	}

	public void setArgs(ArrayList<AstArg> args) {
		this.args = args;
	}

	public Ast getExpression() {
		return expression;
	}

	public void setExpression(Ast expression) {
		this.expression = expression;
	}
	
	
}
