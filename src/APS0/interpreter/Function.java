/* *****************************************************************
 * ILP9 - Implantation d'un langage de programmation.
 * by Christian.Queinnec@paracamplus.com
 * See http://mooc.paracamplus.com/ilp9
 * GPL version 3
 ***************************************************************** */
package APS0.interpreter;

import APS0.interpreter.interfaces.EvaluationException;
import APS0.interpreter.interfaces.IFunction;
import APS0.interpreter.interfaces.ILexicalEnvironment;
import java.util.ArrayList;
import APS0.Ast;
import APS0.AstArg;

public class Function implements IFunction {
    
    public Function (ArrayList<AstArg> variables, 
                     Ast body,
                     ILexicalEnvironment lexenv) {
        this.variables = variables;
        this.body = body;
        this.lexenv = lexenv;
    }
    
    private final ArrayList<AstArg>  variables;
    private final Ast body;
    private  ILexicalEnvironment lexenv;

    @Override
	public int getArity() {
        return variables.size();
    }
    public ArrayList<AstArg> getVariables() {
        return variables;
    }
    public Ast getBody() {
        return body;
    }
    
    protected ILexicalEnvironment getClosedEnvironment() {
        return lexenv;
    }
    
    public void setClosedEnvironment(ILexicalEnvironment lexenv) {
        this.lexenv = lexenv;
    }

    @Override
	public Object apply(Interpreter interpreter, Object[] argument)
            throws EvaluationException {
        if ( argument.length != getArity() ) {
            String msg = "Wrong arity";
            throw new EvaluationException(msg);
        }
        ILexicalEnvironment lexenv2 = getClosedEnvironment();
        ArrayList<AstArg>  variables = getVariables();
        for ( int i=0 ; i<argument.length ; i++ ) {
            lexenv2 = lexenv2.extend(variables.get(i).getNom(), argument[i]);
        }
        return getBody().accept(interpreter, lexenv2);
    }
}
