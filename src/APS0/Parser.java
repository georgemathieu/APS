package APS0;

//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "parser.y"
	import java.io.*;
	import java.util.ArrayList;
//#line 20 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short NL=257;
public final static short NUM=258;
public final static short IDENT=259;
public final static short PLUS=260;
public final static short MINUS=261;
public final static short TIMES=262;
public final static short DIV=263;
public final static short LPAR=264;
public final static short RPAR=265;
public final static short LCRO=266;
public final static short RCRO=267;
public final static short ECHO=268;
public final static short BOOL=269;
public final static short INT=270;
public final static short CONST=271;
public final static short FUN=272;
public final static short VIRGULE=273;
public final static short DEUXPOINTS=274;
public final static short REC=275;
public final static short AND=276;
public final static short OR=277;
public final static short IF=278;
public final static short NOT=279;
public final static short FLECHE=280;
public final static short ETOILE=281;
public final static short EQ=282;
public final static short LT=283;
public final static short FALSE=284;
public final static short TRUE=285;
public final static short POINTVIRGULE=286;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    2,    6,    6,    6,    6,    6,
    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,
    6,    6,    7,    7,    3,    3,    3,    4,    4,    4,
    5,    5,    8,    9,    9,
};
final static short yylen[] = {                            2,
    3,    1,    3,    3,    2,    1,    1,    1,    1,    6,
    4,    4,    4,    4,    4,    4,    4,    4,    4,    3,
    4,    4,    1,    2,    4,    7,    8,    1,    1,    5,
    1,    3,    3,    1,    3,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    6,    9,
    0,    0,    8,    7,    5,    0,    0,    0,    1,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   28,   29,    0,    0,
    0,    3,    4,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   20,    0,    0,    0,    0,    0,
    0,   25,    0,    0,   24,   11,   12,   13,   14,   17,
   16,    0,   15,   19,   18,   22,   33,   35,   21,    0,
    0,    0,    0,    0,   32,    0,    0,    0,   10,   30,
   26,    0,   27,
};
final static short yydgoto[] = {                          2,
    6,    7,    8,   60,   61,   44,   45,   34,   35,
};
final static short yysindex[] = {                      -261,
 -211,    0, -214, -253, -252, -247, -274, -262,    0,    0,
 -245, -231,    0,    0,    0, -215, -215, -224,    0, -211,
 -211, -214, -214, -214, -214, -214, -214, -214, -214, -214,
 -214, -255, -233, -230, -221, -215,    0,    0, -214, -213,
 -215,    0,    0, -214, -203, -202, -201, -200, -198, -197,
 -214, -188, -183, -182,    0, -180, -215, -231, -214, -195,
 -229,    0, -231, -179,    0,    0,    0,    0,    0,    0,
    0, -214,    0,    0,    0,    0,    0,    0,    0, -215,
 -215, -177, -231, -174,    0, -173, -214, -172,    0,    0,
    0, -214,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0, -171,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -170,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -167,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -192,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
   38,    0,    0,  -15,   14,   -3,   49,    0,  -36,
};
final static int YYTABLESIZE=98;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         15,
   39,   40,    9,   10,    1,   16,   17,   32,   11,   55,
   12,   20,    9,   10,   22,   23,   24,   25,   11,   19,
   12,   78,   18,   21,   51,   64,   82,   33,   13,   14,
   26,   27,   28,   29,   41,   62,   30,   31,   13,   14,
   57,   77,   58,    9,   10,   59,   88,   72,   36,   11,
   81,   12,   63,   37,   38,   79,    3,   42,   43,    4,
    5,   66,   67,   68,   69,   86,   70,   71,   84,   13,
   14,   46,   47,   48,   49,   50,   73,   52,   53,   54,
   56,   74,   75,   91,   76,   80,   83,   31,   93,   87,
   89,   90,   65,   85,   92,    2,   34,   23,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          3,
   16,   17,  258,  259,  266,  259,  259,   11,  264,  265,
  266,  286,  258,  259,  260,  261,  262,  263,  264,  267,
  266,   58,  275,  286,   28,   41,   63,  259,  284,  285,
  276,  277,  278,  279,  259,   39,  282,  283,  284,  285,
  274,   57,  273,  258,  259,  267,   83,   51,  264,  264,
  280,  266,  266,  269,  270,   59,  268,   20,   21,  271,
  272,  265,  265,  265,  265,   81,  265,  265,   72,  284,
  285,   23,   24,   25,   26,   27,  265,   29,   30,   31,
   32,  265,  265,   87,  265,  281,  266,  280,   92,  267,
  265,  265,   44,   80,  267,  267,  267,  265,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=286;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"NL","NUM","IDENT","PLUS","MINUS","TIMES","DIV","LPAR","RPAR",
"LCRO","RCRO","ECHO","BOOL","INT","CONST","FUN","VIRGULE","DEUXPOINTS","REC",
"AND","OR","IF","NOT","FLECHE","ETOILE","EQ","LT","FALSE","TRUE","POINTVIRGULE",
};
final static String yyrule[] = {
"$accept : progs",
"progs : LCRO cmds RCRO",
"cmds : stat",
"cmds : stat POINTVIRGULE cmds",
"cmds : dec POINTVIRGULE cmds",
"stat : ECHO expr",
"expr : NUM",
"expr : TRUE",
"expr : FALSE",
"expr : IDENT",
"expr : LPAR IF expr expr expr RPAR",
"expr : LPAR PLUS exprs RPAR",
"expr : LPAR MINUS exprs RPAR",
"expr : LPAR TIMES exprs RPAR",
"expr : LPAR DIV exprs RPAR",
"expr : LPAR NOT exprs RPAR",
"expr : LPAR OR exprs RPAR",
"expr : LPAR AND exprs RPAR",
"expr : LPAR LT exprs RPAR",
"expr : LPAR EQ exprs RPAR",
"expr : LPAR expr RPAR",
"expr : LCRO args RCRO expr",
"expr : LPAR expr exprs RPAR",
"exprs : expr",
"exprs : expr exprs",
"dec : CONST IDENT type expr",
"dec : FUN IDENT type LCRO args RCRO expr",
"dec : FUN REC IDENT type LCRO args RCRO expr",
"type : BOOL",
"type : INT",
"type : LPAR types FLECHE type RPAR",
"types : type",
"types : type ETOILE types",
"arg : IDENT DEUXPOINTS type",
"args : arg",
"args : arg VIRGULE args",
};

//#line 121 "parser.y"

	public Ast prog;
	
	private Yylex lexer;
	
	private int yylex () {
		int yyl_return = -1;
		try {
			yylval = new ParserVal(0);
			yyl_return = lexer.yylex();
		} catch (IOException e) {
			System.err.println("IO error :"+e);
		}
		return yyl_return;
	}
	
	public void yyerror (String error) {
		System.err.println ("Error: " + error);
	}
	
	public Parser(Reader r) {
		lexer = new Yylex(r, this);
	}
		
		
		
		
		
//#line 298 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 48 "parser.y"
{ yyval.obj= new AstProgram((AstCmds)val_peek(1).obj); }
break;
case 2:
//#line 51 "parser.y"
{  yyval.obj = new AstCmds((AstEcho)val_peek(0).obj); }
break;
case 3:
//#line 52 "parser.y"
{  yyval.obj = new AstCmds((AstEcho)val_peek(2).obj, (AstCmds)val_peek(0).obj); }
break;
case 4:
//#line 53 "parser.y"
{  yyval.obj = new AstCmds((AstDec)val_peek(2).obj, (AstCmds)val_peek(0).obj); }
break;
case 5:
//#line 57 "parser.y"
{ yyval.obj = new AstEcho((Ast)val_peek(0).obj); }
break;
case 6:
//#line 60 "parser.y"
{ yyval.obj = new AstNum(val_peek(0).ival); }
break;
case 7:
//#line 61 "parser.y"
{ yyval.obj = new AstBool("true"); }
break;
case 8:
//#line 62 "parser.y"
{ yyval.obj = new AstBool("false"); }
break;
case 9:
//#line 63 "parser.y"
{ yyval.obj = new AstId(val_peek(0).sval); }
break;
case 10:
//#line 64 "parser.y"
{ yyval.obj = new AstIF ((Ast)val_peek(3).obj,(Ast)val_peek(2).obj,(Ast)val_peek(1).obj); }
break;
case 11:
//#line 65 "parser.y"
{ yyval.obj = new AstPrim(Op.ADD,(ArrayList<Ast>)val_peek(1).obj); }
break;
case 12:
//#line 66 "parser.y"
{ yyval.obj = new AstPrim(Op.SUB,(ArrayList<Ast>)val_peek(1).obj); }
break;
case 13:
//#line 67 "parser.y"
{ yyval.obj = new AstPrim(Op.MUL,(ArrayList<Ast>)val_peek(1).obj); }
break;
case 14:
//#line 68 "parser.y"
{ yyval.obj = new AstPrim(Op.DIV,(ArrayList<Ast>)val_peek(1).obj); }
break;
case 15:
//#line 69 "parser.y"
{ yyval.obj = new AstPrim(Op.NOT,(ArrayList<Ast>)val_peek(1).obj); }
break;
case 16:
//#line 70 "parser.y"
{ yyval.obj = new AstPrim(Op.OR,(ArrayList<Ast>)val_peek(1).obj); }
break;
case 17:
//#line 71 "parser.y"
{ yyval.obj = new AstPrim(Op.AND,(ArrayList<Ast>)val_peek(1).obj); }
break;
case 18:
//#line 72 "parser.y"
{ yyval.obj = new AstPrim(Op.LT,(ArrayList<Ast>)val_peek(1).obj); }
break;
case 19:
//#line 73 "parser.y"
{ yyval.obj = new AstPrim(Op.EQ,(ArrayList<Ast>)val_peek(1).obj); }
break;
case 20:
//#line 74 "parser.y"
{ yyval.obj = (Ast)val_peek(1).obj ;}
break;
case 21:
//#line 75 "parser.y"
{ yyval.obj = new AstAbtract((ArrayList<AstArg>)val_peek(2).obj,(Ast)val_peek(0).obj);}
break;
case 22:
//#line 76 "parser.y"
{ yyval.obj = new AstApp((Ast)val_peek(2).obj,(ArrayList<Ast>)val_peek(1).obj); }
break;
case 23:
//#line 80 "parser.y"
{ ArrayList<Ast> r = new ArrayList<Ast>();
						r.add((Ast)val_peek(0).obj); yyval.obj = r; }
break;
case 24:
//#line 82 "parser.y"
{ ((ArrayList<Ast>)val_peek(0).obj).add((Ast)val_peek(1).obj); yyval.obj = val_peek(0).obj; }
break;
case 25:
//#line 86 "parser.y"
{ yyval.obj = new AstConst(new AstId(val_peek(2).sval),(AstType)val_peek(1).obj,(Ast)val_peek(0).obj);}
break;
case 26:
//#line 88 "parser.y"
{yyval.obj = new AstFun(new AstId(val_peek(5).sval),(AstType)val_peek(4).obj,(ArrayList<AstArg>)val_peek(2).obj,(Ast)val_peek(0).obj);}
break;
case 27:
//#line 89 "parser.y"
{yyval.obj = new AstFunRec(new AstId(val_peek(5).sval),(AstType)val_peek(4).obj,(ArrayList<AstArg>)val_peek(2).obj,(Ast)val_peek(0).obj);}
break;
case 28:
//#line 94 "parser.y"
{ ArrayList<String> ar = new ArrayList<String>(); ar.add("bool");
		yyval.obj = new AstType(ar);}
break;
case 29:
//#line 96 "parser.y"
{ ArrayList<String> ar = new ArrayList<String>(); ar.add("int");
		yyval.obj = new AstType(ar);}
break;
case 30:
//#line 98 "parser.y"
{ ((ArrayList<AstType>)val_peek(3).obj).add((AstType)val_peek(1).obj);
								ArrayList<String> ar = new ArrayList<String>();
								for(AstType at : (ArrayList<AstType>)val_peek(3).obj){ar.addAll(at.getNom());}
								AstType s = new AstType(ar); yyval.obj = s;}
break;
case 31:
//#line 105 "parser.y"
{ ArrayList<AstType> r = new ArrayList<AstType>();
						r.add((AstType)val_peek(0).obj); yyval.obj = r; }
break;
case 32:
//#line 107 "parser.y"
{((ArrayList<AstType>)val_peek(0).obj).add((AstType)val_peek(2).obj); yyval.obj = val_peek(0).obj;}
break;
case 33:
//#line 111 "parser.y"
{ yyval.obj = new AstArg(new AstId(val_peek(2).sval),(AstType)val_peek(0).obj);}
break;
case 34:
//#line 114 "parser.y"
{ ArrayList<AstArg> r = new ArrayList<AstArg>();
		r.add((AstArg)val_peek(0).obj); yyval.obj = r; }
break;
case 35:
//#line 116 "parser.y"
{ ((ArrayList<AstArg>)val_peek(0).obj).add((AstArg)val_peek(2).obj); yyval.obj = val_peek(0).obj; }
break;
//#line 595 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
