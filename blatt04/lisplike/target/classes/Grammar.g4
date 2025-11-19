grammar Grammar;

program : expr+ EOF;

expr    : literal
        | symbol
        | operation
        | def
        | defn
        | let
        | ifExpr
        | doBlock
        | list
        | head
        | tail
        | nth
        | str
        | print
        | funccall
        ;

literal : NUMBER    #number
        | STRING    #string
        | TRUE      #true
        | FALSE     #false
        ;

symbol  : ID;

operator
        : '*'   #mult
        | '/'   #div
        | '+'   #add
        | '-'   #sub
        | '<'   #lt
        | '>'   #gt
        | '='   #eq
        ;

def     : '(' 'def' binding ')' ;

defn    : '(' 'defn' symbol '(' symbol* ')' expr ')' ;

let     : '(' 'let' '(' binding* ')' expr ')' ;

ifExpr  : '(' 'if' expr expr (expr)? ')' ;

doBlock : '(' 'do' expr* ')' ;

list    : '(' 'list' expr* ')' ;

head    : '(' 'head' list ')' ;

tail    : '(' 'tail' list ')' ;

nth     : '(' 'nth' list expr ')' ;

str     : '(' 'str' expr* ')' ;

print   : '(' 'print' expr ')' ;

operation
        : '(' operator expr* ')' ;

funccall: '(' symbol expr* ')';

binding : symbol expr ;


TRUE    : 'true';
FALSE   : 'false';
ID      : [a-zA-Z_][a-zA-Z0-9_]*;
NUMBER  : [0-9]+;
STRING  :  '"' (~[\n\r"])* '"' ;
COMMENT :  ';;' ~[\n\r]* -> skip ;
WS      :  [ ,\t\n\r]+ -> skip ;