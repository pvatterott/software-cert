header {
package edu.mit.compilers.grammar;
}

options
{
  mangleLiteralPrefix = "TK_";
  language = "Java";
} 

{@SuppressWarnings("unchecked")}
class CScanner extends Lexer;
options
{
  k = 3;
}

tokens
{
  "extern";
  "static";
  "auto";
  "register";
  "goto";
  "continue";
  "break";
  "return";
  "while";
  "do";
  "for";
  "if";
  "else";
  "switch";
  "case";
  "default";
  "sizeof";
  "const";
  "volatile";
  "enum";
  "struct";
  "union";
  "void";
  "char";
  "short";
  "int";
  "long";
  "float";
  "double";
  "signed";
  "unsigned";
}

// Selectively turns on debug tracing mode.
// You can insert arbitrary Java code into your parser/lexer this way.
{
  /** Whether to display debug information. */
  private boolean trace = false;

  public void setTrace(boolean shouldTrace) {
    trace = shouldTrace;
  }
  @Override
  public void traceIn(String rname) throws CharStreamException {
    if (trace) {
      super.traceIn(rname);
    }
  }
  @Override
  public void traceOut(String rname) throws CharStreamException {
    if (trace) {
      super.traceOut(rname);
    }
  }
}

IDENTIFIER : LETTER (LETTER | '0'..'9')*;
  
//fragment
protected
LETTER : ('$' | 'A'..'Z' | 'a'..'z' | '_');

CHARACTER_LITERAL
    :   '\'' ( EscapeSequence | ~('\''|'\\') ) '\''
    ;

STRING_LITERAL
    :  '"' ( EscapeSequence | ~('\\'|'"') )* '"'
    ;

HEX_LITERAL : '0' ('x'|'X') (HexDigit)+ (IntegerTypeSuffix)? ;

DECIMAL_LITERAL : ('0' | '1'..'9' ('0'..'9')*) (IntegerTypeSuffix)? ;

OCTAL_LITERAL : '0' ('0'..'7')+ (IntegerTypeSuffix)? ;

//fragment
protected
HexDigit 
  : '0'..'9'
  | 'a'..'f'
  | 'A'..'F'
  ;

//fragment
protected
IntegerTypeSuffix
  : ('u'|'U')? ('l'|'L')
  | ('u'|'U')  ('l'|'L')?
  ;

FLOATING_POINT_LITERAL
    :   ('0'..'9')+ '.' ('0'..'9')* (Exponent)? (FloatTypeSuffix)?
    //|   '.' ('0'..'9')+ (Exponent)? (FloatTypeSuffix)?
    //|   ('0'..'9')+ (Exponent) (FloatTypeSuffix)?
    //|   ('0'..'9')+ (Exponent)? FloatTypeSuffix
  ;

//fragment
protected
Exponent : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

//fragment
protected
FloatTypeSuffix : ('f'|'F'|'d'|'D') ;

//fragment
protected
EscapeSequence
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   OctalEscape
    ;

//fragment
protected
OctalEscape
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

//fragment
protected
UnicodeEscape
    :   '\\' 'u' HexDigit HexDigit HexDigit HexDigit
    ;

WS_ : (' ' | '\n' {newline();} | '\t' | '\r') {_ttype = Token.SKIP; };

RANGE_SPECIFIER : "//#" (~'\n')* '\n' {newline();};

SL_COMMENT : "//" (~('\n'|'#')) (~'\n')* '\n' {_ttype = Token.SKIP; newline (); };

BLANK_COMMENT : "//\n" {_ttype = Token.SKIP; newline (); };

ML_COMMENT : "/*"
     (
       options {
         generateAmbigWarnings=false;
       }
       :  { LA(2)!='/' }? '*'
       | '\r' '\n' {newline();}
       | '\r' {newline();}
       | '\n' {newline();}
       | ~('*'|'\n'|'\r')
     )*
     "*/"
     {$setType(Token.SKIP);};

LINE_COMMAND 
    : '#' (~('\n'|'\r'))* ('\r')? '\n'
    ;
    
LBRACE : '{';
RBRACE : '}';
LPAREN : '(';
RPAREN : ')';
LBRACKET : '[';
RBRACKET : ']';
SEMI : ';';
COLON : ':';
COMMA : ',';
PERIOD : '.';

ASTERISK : '*';
PLUS : '+';
MINUS : '-';
DIV : '/';
MOD : '%';

INC : "++";
DEC : "--";

LOG_AND : "&&";
LOG_OR : "||";

//ARROW : "->";

BIN_AND : '&';
BIN_OR : '|';
BIN_XOR : '^';
TILDE : '~';
BANG : '!';

EQ : "==";
NEQ : "!=";
GEQ : ">=";
LEQ : "<=";
GT : ">";
LT : "<";

SHL : "<<";
SHR : ">>";

Q : '?';

ASSIGN : '=';
MUL_ASSIGN : "*=";
DIV_ASSIGN : "/=";
ADD_ASSIGN : "+=";
SUB_ASSIGN : "-=";
MOD_ASSIGN : "%=";
SHR_ASSIGN : ">>=";
SHL_ASSIGN : "<<=";
AND_ASSIGN : "&=";
OR_ASSIGN  : "|=";
XOR_ASSIGN : "^=";
