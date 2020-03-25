package APS0;

import java.util.ArrayList;

public class AstApp implements Ast{
	
	Ast nom;
	ArrayList<Ast> param;
	
	
	public AstApp(Ast nom, ArrayList<Ast> param) {
		this.nom = nom ;
		this.param = param;
	}


	@Override
	public String toPrologString() {
		StringBuilder sb = new StringBuilder("app(" + nom.toPrologString() + ",[");
		for (int i = param.size() - 1; i > 0; i--) {
			sb.append(param.get(i).toPrologString()+",");
		}
		sb.append(param.get(0).toPrologString());
		sb.append("])");
		return sb.toString();
	}
}
