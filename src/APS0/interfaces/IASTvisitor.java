/* *****************************************************************
 * ILP9 - Implantation d'un langage de programmation.
 * by Christian.Queinnec@paracamplus.com
 * See http://mooc.paracamplus.com/ilp9
 * GPL version 3
 ***************************************************************** */
package APS0.interfaces;

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

public interface IASTvisitor<Result, Data, Anomaly extends Throwable> {
    Result visit(AstAbtract iast, Data data) throws Anomaly;
    Result visit(AstApp iast, Data data) throws Anomaly;
    Result visit(AstArg iast, Data data) throws Anomaly;
    Result visit(AstBool iast, Data data) throws Anomaly;
    Result visit(AstCmds iast, Data data) throws Anomaly;
    Result visit(AstConst iast, Data data) throws Anomaly;
    Result visit(AstDec iast, Data data) throws Anomaly;
    Result visit(AstEcho iast, Data data) throws Anomaly;
    Result visit(AstFun iast, Data data) throws Anomaly;
    Result visit(AstFunRec iast, Data data) throws Anomaly;
    Result visit(AstId iast, Data data) throws Anomaly;
    Result visit(AstIF iast, Data data) throws Anomaly;
    Result visit(AstNum iast, Data data) throws Anomaly;
    Result visit(AstPrim iast, Data data) throws Anomaly;
    Result visit(AstProgram iast, Data data) throws Anomaly;
    Result visit(AstType iast, Data data) throws Anomaly;
}
