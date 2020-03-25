package APS0;


import java.util.ArrayList;

import APS0.interfaces.IASTvisitor;

public class AstPrim implements Ast {

	Op op;
	ArrayList<Ast> opands;

	AstPrim(Op op, ArrayList<Ast> es) {
		this.op = op;
		this.opands = es;
	}

	public String toPrologString() {
		String r = "";
		r = op.toString() + "(";
		for (int i = opands.size() - 1; i > 0 ; i--)
			r += opands.get(i).toPrologString() + ",";
		r += opands.get(0).toPrologString();
		r += ")";
		return r;
	}
	
	public <Result, Data, Anomaly extends Throwable> Result accept(
			IASTvisitor<Result, Data, Anomaly> visitor, Data data) throws Anomaly {
		 return visitor.visit(this, data);
	}
}
