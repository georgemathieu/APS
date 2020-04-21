/* *****************************************************************
 * ILP9 - Implantation d'un langage de programmation.
 * by Christian.Queinnec@paracamplus.com
 * See http://mooc.paracamplus.com/ilp9
 * GPL version 3
 ***************************************************************** */
package APS0.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import APS0.interpreter.EmptyLexicalEnvironment;
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
import APS0.Op;
import APS0.interfaces.IASTvisitor;
import APS0.interpreter.interfaces.EvaluationException;
import APS0.interpreter.interfaces.IFunction;
import APS0.interpreter.interfaces.IGlobalVariableEnvironment;
import APS0.interpreter.interfaces.ILexicalEnvironment;
//import com.paracamplus.ilp1.interpreter.interfaces.IOperator;
//import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
//import com.paracamplus.ilp1.interpreter.interfaces.Invocable;
import APS0.interpreter.interfaces.Invocable;

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

	@Override
	public Object visit(AstAbtract ast, ILexicalEnvironment data) throws EvaluationException {
		ast.getArgs();
		ast.getExpression();
        IFunction fun = new Function(ast.getArgs(),
                ast.getExpression(),
                data);
        return fun;
	}

	@Override
	public Object visit(AstApp ast, ILexicalEnvironment data) throws EvaluationException {
		Object function = ast.getNom().accept(this, data);
	        if ( function instanceof Invocable ) {
	            Invocable f = (Invocable)function;
	            List<Object> args = new Vector<Object>();
	            for ( Ast arg : ast.getParam() ) {
	                Object value = arg.accept(this, data);
	                args.add(value);
	            }
	            return f.apply(this, args.toArray());
	        } else {
	            String msg = "Cannot apply " + function;
	            throw new EvaluationException(msg);
	        }
	}

	@Override
	public Object visit(AstArg ast, ILexicalEnvironment data) throws EvaluationException {
		// TODO Auto-generated method stub
		//not used
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
		//jamais utiliser car ya AstConst, AstFun et AstFunRec (AstDec = interface)
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
		ArrayList<AstArg> args = ast.getArgs();
		Ast body = ast.getBody();
		//EmptyLexicalEnv or data dunoo for now
		IFunction f = new Function(args, body,  new EmptyLexicalEnvironment()); 
		return data.extend(nom,f);
	}

	@Override
	public Object visit(AstFunRec ast, ILexicalEnvironment data) throws EvaluationException {
		AstId nom = ast.getNom();
		ArrayList<AstArg> args = ast.getArgs();
		Ast body = ast.getBody();
		ILexicalEnvironment env = new EmptyLexicalEnvironment();
		IFunction f = new Function(args, body,  env);
		f.setClosedEnvironment(env.extend(nom, f));
		return data.extend(nom,f);
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
		Op operator = ast.getOp();
        
        if(operator == Op.NOT){
    		Object operand = ast.getOpands().get(0).accept(this, data);
    		if(operand instanceof Integer){
    			return ((Integer)operand == 1) ? 0:1;
    		}else {
    			throw new EvaluationException("operand not integer");
    		}
        }
        else {
    		Object rightOperand =  ast.getOpands().get(0).accept(this, data);
            Object leftOperand =  ast.getOpands().get(1).accept(this, data);
            if(leftOperand instanceof Integer && rightOperand instanceof Integer) {
		        switch(operator) {
	        	case ADD : 
	        		return (Integer)leftOperand + (Integer)rightOperand;
	        	case SUB : 
	        		return (Integer)leftOperand - (Integer)rightOperand;
	        	case MUL : 
	        		return (Integer)leftOperand * (Integer)rightOperand;
	        	case DIV :
	        		return (Integer)leftOperand / (Integer)rightOperand;
	        	case AND : 
	        		return ((Integer)leftOperand==1 && (Integer)rightOperand == 1) ? 1:0;
	        	case OR : 
	        		return ((Integer)leftOperand==1 || (Integer)rightOperand == 1) ? 1:0;
	        	case LT :
	        		return ((Integer)leftOperand < (Integer) rightOperand) ? 1:0;
	        	case EQ:
	        		return ((Integer)leftOperand == (Integer)rightOperand) ? 1:0;
	        	default:
	        		throw new EvaluationException("operator doesn't exist");
		        }
            } else {
            	throw new EvaluationException("operands are not integers");
            }            	
        }
	}

	@Override
	public Object visit(AstType ast, ILexicalEnvironment data) throws EvaluationException {
		// a priori on visite jamais les types.
		return null;
	}
    
  
}
