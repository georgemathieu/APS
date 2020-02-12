package APS0;

import java.util.ArrayList;

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
		StringBuilder sb = new StringBuilder("Fun(" + nom.toPrologString() + "," + type.toPrologStringF() + ",Args(");
		for (int i = args.size() - 1; i > 0; i--) {
			sb.append(args.get(i).toPrologString()+",");
		}
		sb.append(args.get(0).toPrologString());
		sb.append(")," + body.toPrologString() + ")");
		return sb.toString();
	}
}
