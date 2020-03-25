main_stdin :-
read(user_input,T), 
typeCheck(T,R), 
print(R), 
nl, 
exitCode(R).

typeCheck(P,ok) :- typeProg(P).
typeCheck(_,ko).

exitCode(ok) :- halt(0).
exitCode(_) :- halt(1).

%%assoc
assoc(X,[(X,V)|_],V).
assoc(X,[_|XVs],V) :- assoc(X,XVs,V).

typeExpr(_, true, bool). 
typeExpr(_, false, bool). 
typeExpr(_, num(_), int). 

typeExpr(G, add(X,Y), int):- typeExpr(G,X,int),typeExpr(G,Y,int).
typeExpr(G, sub(X,Y), int):- typeExpr(G,X,int),typeExpr(G,Y,int).
typeExpr(G, mul(X,Y), int):- typeExpr(G,X,int),typeExpr(G,Y,int).
typeExpr(G, div(X,Y), int):- typeExpr(G,X,int),typeExpr(G,Y,int).

typeExpr(G, lt(X,Y), bool):- typeExpr(G,X,int),typeExpr(G,Y,int).
typeExpr(G, eq(X,Y), bool):- typeExpr(G,X,T),typeExpr(G,Y,T).
typeExpr(G, not(X), bool):- typeExpr(G,X,bool).
typeExpr(G, and(X,Y), bool):- typeExpr(G,X,bool),typeExpr(G,Y,bool).
typeExpr(G, or(X,Y), bool):- typeExpr(G,X,bool),typeExpr(G,Y,bool).


typeExpr(G,var(X),T) :- assoc(X,G,T). 
typeExpr(G,if(E,E1,E2),T) :- typeExpr(G, E, bool), typeExpr(G, E1, T), typeExpr(G, E2 ,T). 
typeExpr(G,abstract(args(A),E), fleche(TT,T)) :- typeArgs(A,TT),removeVar(A,AA),append(AA,G,Result),typeExpr(Result,E,T).
typeExpr(G,app(E,P), T) :- typeExpr(G,E,fleche(TT,T)), typeParam(G,P,TT).

typeParam(G,[X|[]],[T]) :- typeExpr(G,X,T).
typeParam(G,[X|XS],[T|TT]) :- typeExpr(G,X,T) , typeParam(G,XS,TT).

typeStat(G,E,void) :- typeExpr(G,E,int).

typeArgs([(_,T)],[T]).
typeArgs([(_,T)|AA], [T|TT]) :- typeArgs(AA,TT). 

removeVar([(var(X),T)],[(X,T)]).
removeVar([(var(X),T)|[AA]],[(X,T)|TT]) :- removeVar(AA,TT).

typeDec(G,const(var(X),T,E),(G|(X,T))) :- typeExpr(G,E,T).
typeDec(G,fun(var(X),T,args(A),E), [(X,fleche(TT,T))|G]) :- typeArgs(A,TT),removeVar(A,AA),append(AA,G,Result),typeExpr(Result,E,T).
typeDec(G,funRec(var(X),T,args(A),E), [(X,fleche(TT,T))|G]) :- typeArgs(A,TT),removeVar(A,AA),append(AA,[(X,fleche(TT,T))|G],Result),typeExpr(Result,E,T).

typeCmds(G,commands(declaration(D),commands(CS)),void):- typeDec(G,D,E),typeCmds(E,commands(CS),void).
typeCmds(G,commands(echo(S),commands(CS)),void):- typeStat(G,S,void), typeCmds(G,commands(CS),void).
typeCmds(_,commands(_),void).

typeProg(program(CS)):- typeCmds([],CS,void). 

%typeExpr([],abstract(args([(var(x),int)]),add(var(x),num(5))),T).
%typeDec([],funRec(var(f),int,args([(var(x),int)]),add((x,x),T).


%%test typeExpr([(x,int),(y,int)],[(var(x),int),(var(y),int)],T).
%%test typeDec([(x,int)],fun(double,int,args([(var(x),int)]),_), T).
%%test typeExpr([(x,int),(y,bool)],abstract(args([(var(x),int),(var(y),bool)]),num(4)),T).
%%test typeExpr([(x,int),(y,bool)],abstract(args([(var(x),int),(var(y),bool)]),true),T).	
%[ECHO([x : int, y : int] (x))]

%[const a int 5;FUN f int [x:int] (add x a);ECHO (f 37)]
%typeProg(program(commands(declaration(const(var(a),int,num(5))),commands(echo(num(a)))))).
%typeProg(program(commands(declaration(const(var(a),int,num(5))),commands(declaration(fun(var(f),int,args([(var(x),int)]),add(var(x),var(a)))),commands(echo(app(var(f),[num(37)]))))))).


