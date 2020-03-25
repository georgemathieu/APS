package APS0;

import java.util.ArrayList;

public class AstFunRec implements AstDec{

	AstId nom;
	AstType type;
	ArrayList<AstArg> args;
	Ast body;
	
	public AstFunRec(AstId nom, AstType type, ArrayList<AstArg> args, Ast body) {
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
		StringBuilder sb = new StringBuilder("funRec(" + nom.toPrologString() + "," + t + ",args([");
		for (int i = args.size() - 1; i > 0; i--) {
			sb.append(args.get(i).toPrologString()+",");
		}
		sb.append(args.get(0).toPrologString());
		sb.append("])," + body.toPrologString() + ")");
		return sb.toString();
	}
}
