grammar minic;

// Parser
program : stmt+ EOF ;

stmt
  : vardecl #VARDECLS
  | assign  #ASSIGNS
  | fndecl  #FNDECLS
  | expr ';'#EXPRS
  | block   #BLOCKS
  | while   #WHILES
  | cond    #CONDS
  | return  #RETURNS
  ;

vardecl : type ID ('=' expr)? ';' ;
assign  : ID '=' expr ';' ;

fndecl  : type ID '(' params? ')' block ;
params  : type ID (',' type ID)* ;
return  : 'return' expr ';' ;

fncall  : ID '(' args? ')' ;
args    : expr (',' expr)* ;

block   : '{' stmt* '}' ;
while   : 'while' '(' expr ')' block ;
cond    : 'if' '(' expr ')' block ('else' block)? ;

expr
  : fncall                  #CALL
  | expr ('*' | '/') expr   #MD
  | expr ('+' | '-') expr   #PM
  | expr ('>' | '<') expr   #GL
  | expr ('==' | '!=') expr #EQNQ
  | ID                      #IDENT
  | NUMBER                  #NUM
  | STRING                  #STR
  | 'T'                     #BT
  | 'F'                     #BF
  | '(' expr ')'            #BRAK
  ;

type : 'int' | 'string' | 'bool' ;


// Lexer
ID      : [a-zA-Z] [a-zA-Z0-9]* ;
NUMBER  : [0-9]+ ;
STRING  : '"' (~[\n\r"])* '"' ;

COMMENT : '#' ~[\n\r]* -> skip ;
WS      : [ \t\n\r]+   -> skip ;
