/* *****************************************************************
 * ILP9 - Implantation d'un langage de programmation.
 * by Christian.Queinnec@paracamplus.com
 * See http://mooc.paracamplus.com/ilp9
 * GPL version 3
 ***************************************************************** */
package APS0.interpreter;

import java.util.ArrayList;

import APS0.Ast;
import APS0.AstAbtract;
import APS0.AstApp;
import APS0.AstArg;
import APS0.AstBool;
import APS0.AstCmds;
import APS0.AstConst;
import APS0.AstDec;
import APS0.AstEcho;
import APS0.AstFun;
import APS0.AstFunRec;
import APS0.AstIF;
import APS0.AstId;
import APS0.AstNum;
import APS0.AstPrim;
import APS0.AstProgram;
import APS0.AstType;
import APS0.interfaces.IASTvisitor;
import APS0.interpreter.interfaces.EvaluationException;
import APS0.interpreter.interfaces.IGlobalVariableEnvironment;
import APS0.interpreter.interfaces.ILexicalEnvironment;
//import com.paracamplus.ilp1.interpreter.interfaces.IOperator;
//import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
//import com.paracamplus.ilp1.interpreter.interfaces.Invocable;

public class Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {
    
    public Interpreter (IGlobalVariableEnvironment globalVariableEnvironment
                        /*, IOperatorEnvironment operatorEnvironment */ ) {
        this.globalVariableEnvironment = globalVariableEnvironment;
        //this.operatorEnvironment = operatorEnvironment;
    }
    protected IGlobalVariableEnvironment globalVariableEnvironment;
    //protected IOperatorEnvironment operatorEnvironment;

    /*public IOperatorEnvironment getOperatorEnvironment() {
        return operatorEnvironment;
    }*/
    
    public IGlobalVariableEnvironment getGlobalVariableEnvironment() {
        return globalVariableEnvironment;
    }
    
    // 
    
    @Override
	public Object visit(AstProgram iast, ILexicalEnvironment lexenv) throws EvaluationException {
    	 try {
             return iast.getBody().accept(this, lexenv);
         } catch (Exception exc) {
             return exc;
         }
	}
    // 
    
    //private static Object whatever = "whatever";
            
    @Override
	public Object visit(AstIF ast, ILexicalEnvironment lexenv) throws EvaluationException {
    	Object c = ast.getCondition().accept(this, lexenv);
        if ( c != null && c instanceof Integer ) {
            int x = (Integer) c;
            if (x == 1) {
                return ast.getConsequence().accept(this, lexenv);
            } else if (x == 0){
                return ast.getAlternant().accept(this, lexenv);          
            }
            else {
            	String msg = "condition not a boolean value";
    			throw new EvaluationException(msg);
            }
        }
        else
        {
        	String msg = "condition not a boolean value";
			throw new EvaluationException(msg);
        }
	}

    /*
    @Override
	public Object visit(IASTunaryOperation iast, ILexicalEnvironment lexenv) 
            throws EvaluationException {
        Object operand = iast.getOperand().accept(this, lexenv);
        IASToperator operator = iast.getOperator();
        IOperator op = getOperatorEnvironment().getUnaryOperator(operator);
        return op.apply(operand);
    }
    
    @Override
	public Object visit(IASTbinaryOperation iast, ILexicalEnvironment lexenv) 
            throws EvaluationException {
        Object leftOperand = iast.getLeftOperand().accept(this, lexenv);
        Object rightOperand = iast.getRightOperand().accept(this, lexenv);
        IASToperator operator = iast.getOperator();
        IOperator op = getOperatorEnvironment().getBinaryOperator(operator);
        return op.apply(leftOperand, rightOperand);
    }

    @Override
	public Object visit(IASTsequence iast, ILexicalEnvironment lexenv) 
            throws EvaluationException {
        IASTexpression[] expressions = iast.getExpressions();
        Object lastValue = null;
        for ( IASTexpression e : expressions ) {
            lastValue = e.accept(this, lexenv);
        }
        return lastValue;
    }
    
    @Override
	public Object visit(IASTblock iast, ILexicalEnvironment lexenv) 
            throws EvaluationException {
        ILexicalEnvironment lexenv2 = lexenv;
        for ( IASTbinding binding : iast.getBindings() ) {
            Object initialisation = 
                    binding.getInitialisation().accept(this, lexenv);
            lexenv2 = lexenv2.extend(binding.getVariable(), initialisation);
        }
        return iast.getBody().accept(this, lexenv2);
    }

    @Override
	public Object visit(IASTboolean iast, ILexicalEnvironment lexenv) 
            throws EvaluationException {
        return iast.getValue();
    }
    
    @Override
	public Object visit(IASTinteger iast, ILexicalEnvironment lexenv) 
            throws EvaluationException {
        return iast.getValue();
    }
    
    @Override
	public Object visit(IASTfloat iast, ILexicalEnvironment lexenv) 
            throws EvaluationException {
        return iast.getValue();
    }
    
    @Override
	public Object visit(IASTstring iast, ILexicalEnvironment lexenv) 
            throws EvaluationException {
        return iast.getValue();
    }

    @Override
	public Object visit(IASTvariable iast, ILexicalEnvironment lexenv) 
            throws EvaluationException {
        try {
            return lexenv.getValue(iast);
        } catch (EvaluationException exc) {
            return getGlobalVariableEnvironment()
                    .getGlobalVariableValue(iast.getName());
        }
    }
    
    @Override
	public Object visit(IASTinvocation iast, ILexicalEnvironment lexenv) 
            throws EvaluationException {
        Object function = iast.getFunction().accept(this, lexenv);
        if ( function instanceof Invocable ) {
            Invocable f = (Invocable)function;
            List<Object> args = new Vector<Object>();
            for ( IASTexpression arg : iast.getArguments() ) {
                Object value = arg.accept(this, lexenv);
                args.add(value);
            }
            return f.apply(this, args.toArray());
        } else {
            String msg = "Cannot apply " + function;
            throw new EvaluationException(msg);
        }
    }
	*/
    
	@Override
	public Object visit(AstAbtract ast, ILexicalEnvironment data) throws EvaluationException {
		ast.getArgs();
		return null;
	}

	@Override
	public Object visit(AstApp ast, ILexicalEnvironment data) throws EvaluationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(AstArg ast, ILexicalEnvironment data) throws EvaluationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(AstBool ast, ILexicalEnvironment data) throws EvaluationException {
		String value = ast.getValue();
		if (value == "true") {
			return 1;
		} else if (value == "false") {
			return 0;
		} else {
			String msg = "not a boolean value";
			throw new EvaluationException(msg);
		}
	}

	@Override
	public Object visit(AstCmds ast, ILexicalEnvironment data) throws EvaluationException {
		
		 AstEcho statement = ast.getStatement();
		 AstDec declaration = ast.getDeclaration();
		 AstCmds commands = ast.getCommands();
		 
		if (statement != null ) {
			statement.accept(this, data);
			commands.accept(this, data);
		}
		else if (declaration != null){
			ILexicalEnvironment newData =  (ILexicalEnvironment) declaration.accept(this, data);
			commands.accept(this, newData);
		} else if(commands != null){
			commands.accept(this, data);
		}
		//(END)
		return true;
	}

	@Override
	public Object visit(AstConst ast, ILexicalEnvironment data) throws EvaluationException {
		return data.extend(ast.getNom(),ast.getValeur().accept(this, data));
	}

	@Override
	public Object visit(AstDec iast, ILexicalEnvironment data) throws EvaluationException {
		//jamais utiliser car ya AstConst, AstFun et AstFunRec
		return null;
	}

	@Override
	public Object visit(AstEcho ast, ILexicalEnvironment data) throws EvaluationException {
		Object n = ast.getAst().accept(this, data);
		if(n != null && n instanceof Integer)
		{
			System.out.println(n);
		}
		else
		{
			String msg = "not an Integer value";
			throw new EvaluationException(msg);
		}
		return n;
	}

	@Override
	public Object visit(AstFun ast, ILexicalEnvironment data) throws EvaluationException {
		AstId nom = ast.getNom();
		//AstType type = ast.getType();
		ArrayList<AstArg> args = ast.getArgs();
		Ast body = ast.getBody();
		
		//creer une Fermeture avec args et body et remplacer le 0 par la Fermeture
		return data.extend(nom,0);
	}

	@Override
	public Object visit(AstFunRec iast, ILexicalEnvironment data) throws EvaluationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(AstId ast, ILexicalEnvironment data) throws EvaluationException {
		return data.getValue(ast);
	}

	@Override
	public Object visit(AstNum ast, ILexicalEnvironment data) throws EvaluationException {
		return ast.getValue();
	}

	@Override
	public Object visit(AstPrim ast, ILexicalEnvironment data) throws EvaluationException {
		return null;
	}

	@Override
	public Object visit(AstType ast, ILexicalEnvironment data) throws EvaluationException {
		// a priori on visite jamais les types.
		return null;
	}
    
  
}
