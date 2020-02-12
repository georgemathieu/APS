package APS0;

import java.util.ArrayList;

public class AstType implements Ast{
	
	ArrayList<AstId> nom;
	
	public AstType(ArrayList<AstId> nom) {
		this.nom = nom;
	}

	@Override
	public String toPrologString() {
		return "Type("+nom.get(0)+")";
	}
	
	public String toPrologStringF() {
		StringBuilder sb = new StringBuilder("Signature(");
		for (int i = nom.size() - 1; i > 0; i--) {
			sb.append("Type(" + nom.get(i).toPrologString()+"),");
		}
		sb.append("Retour(" + nom.get(0).toPrologString());
		sb.append("))");
		return sb.toString();
	}
	
}
