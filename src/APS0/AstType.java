package APS0;

import java.util.ArrayList;

import APS0.interfaces.IASTvisitor;

public class AstType implements Ast{
	
	ArrayList<String> nom;
	
	public AstType(ArrayList<String> nom) {
		this.nom = nom;
	}

	@Override
	public String toPrologString() {
		return nom.get(0);
	}
	
	public String toPrologStringF() {
		StringBuilder sb = new StringBuilder("fleche(");
		if(nom.size() > 2) {
			sb.append("[");
			for (int i = nom.size() - 2; i > 0; i--) 
			{
				sb.append("" + nom.get(i)+",");
			}
			sb.append(nom.get(1) + "]," + nom.get(0));
		} else {
			sb.append("[" + nom.get(1) + "]," + nom.get(0));
		}
		
		sb.append(")");
		return sb.toString();
	}

	public ArrayList<String> getNom() {
		return nom;
	}
	
	public <Result, Data, Anomaly extends Throwable> Result accept(
			IASTvisitor<Result, Data, Anomaly> visitor, Data data) throws Anomaly {
		 return visitor.visit(this, data);
	}
}
