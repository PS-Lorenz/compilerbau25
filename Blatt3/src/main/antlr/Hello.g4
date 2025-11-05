grammar Hello;


// Parser
start : stmt+ EOF;


stmt    : ID ':=' expr NL   #ASSIGN
        | expr NL           #EX
        | while             #LOOP
        | if                #CHECK
        ;

expr    :  expr '/' expr    #DIV
        |  expr '*' expr    #MULT
        |  expr '-' expr    #SUB
        |  expr '+' expr    #ADD
        |  expr '<' expr    #LT
        |  expr '>' expr    #GT
        |  expr '!=' expr   #NEQ
        |  expr '==' expr   #EQ
        |  ID               #VAR
        |  INT              #NUM
        |  STRING           #TXT
        ;

while   : 'while' expr 'do' NL stmt* 'end' NL
        ;

if      : 'if' expr 'do' NL stmt* 'end' NL                      #SIF
        | 'if' expr 'do' NL stmt* 'else' 'do' NL stmt* 'end' NL #LIF
        ;



// Lexer
ID      : [a-zA-Z_][a-zA-Z0-9_]* ;
INT     : [0-9]+ ;
STRING  :  '"' (~[\n\r"])* '"' ;
NL      : '\n' ;
COMMENT :  '#' ~[\r\n]* -> skip ;
WS      : [ \t]+ -> skip ;
