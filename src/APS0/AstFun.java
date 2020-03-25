package APS0;

import java.util.ArrayList;

import APS0.interfaces.IASTvisitor;

public class AstFun implements AstDec{
	AstId nom;
	AstType type;
	ArrayList<AstArg> args;
	Ast body;
	
	public AstFun(AstId nom, AstType type, ArrayList<AstArg> args, Ast body) {
		this.nom = nom;
		this.type = type;
		this.args = args;
		this.body = body;
	}

	@Override
	public String toPrologString() {
		String t;
 		if(type.getNom().size() == 1) {
			t = type.toPrologString();
		} else
		{
			t = type.toPrologStringF();
		}
		StringBuilder sb = new StringBuilder("fun(" + nom.toPrologString() + "," + t + ",args([");
		for (int i = args.size() - 1; i > 0; i--) {
			sb.append(args.get(i).toPrologString()+",");
		}
		sb.append(args.get(0).toPrologString());
		sb.append("])," + body.toPrologString() + ")");
		return sb.toString();
	}
	
	public <Result, Data, Anomaly extends Throwable> Result accept(
			IASTvisitor<Result, Data, Anomaly> visitor, Data data) throws Anomaly {
		 return visitor.visit(this, data);
	}
}
