header {
package edu.mit.compilers.grammar;
}

options
{
  mangleLiteralPrefix = "TK_";
  language = "Java";
}

class CParser extends Parser;
options
{
  importVocab = CScanner;
  k = 3;
  buildAST = true;
}

tokens
{
  BLOCK;
  PROGRAM;
  PARAM_LIST;
  PARAM;
  TYPE;
  DECLARATION;
  FN;
  FN_CALL;
}


// Java glue code that makes error reporting easier.
// You can insert arbitrary Java code into your parser/lexer this way.
{
  // Do our own reporting of errors so the parser can return a non-zero status
  // if any errors are detected.
  /** Reports if any errors were reported during parse. */
  private boolean error;

  @Override
  public void reportError (RecognitionException ex) {
    // Print the error via some kind of error reporting mechanism.
    System.err.println(ex.toString());
    error = true;
  }
  @Override
  public void reportError (String s) {
    // Print the error via some kind of error reporting mechanism.
    System.err.println(s);
    error = true;
  }
  public boolean getError () {
    return error;
  }

  public void outputError () {
    System.err.println("Error while parsing!");
  }

  // Selectively turns on debug mode.

  /** Whether to display debug information. */
  private boolean trace = false;

  public void setTrace(boolean shouldTrace) {
    trace = shouldTrace;
  }
  @Override
  public void traceIn(String rname) throws TokenStreamException {
    if (trace) {
      super.traceIn(rname);
    }
  }
  @Override
  public void traceOut(String rname) throws TokenStreamException {
    if (trace) {
      super.traceOut(rname);
    }
  }
}

program
  : (external_declaration)+
  { #program = #([PROGRAM, "program"], #program); }
  ;
  
external_declaration
  : function_definition
  ;
  
function_definition
  : type_specifier IDENTIFIER LPAREN! parameter_type_list RPAREN! compound_statement
      {#function_definition = #([FN, "fn"], #function_definition); }
  ;
  
type_specifier
  : TK_void   { #type_specifier = #([TYPE, "type"], #type_specifier); }
  | TK_int    { #type_specifier = #([TYPE, "type"], #type_specifier); }
  ;

parameter_type_list
  : parameter_list //(COMMA "...")?
  ;

parameter_list
  : parameter_declaration (COMMA! parameter_declaration)*
  { #parameter_list = #([PARAM_LIST, "params"], #parameter_list); }
  ;

parameter_declaration
  : (type_specifier IDENTIFIER)*
  { #parameter_declaration = #([PARAM, "param"], #parameter_declaration); }
  ;
  
compound_statement
  : LBRACE! (declaration)* (statement)* RBRACE!
    { #compound_statement = #([BLOCK, "block"], #compound_statement); }
  ;
  
declaration
  : TK_int (init_declarator_list)? SEMI!
  { #declaration = #([DECLARATION, "dec"], #declaration); }
  ;
  
init_declarator_list
  : init_declarator (COMMA! init_declarator)*
  ;

init_declarator
  : declarator (ASSIGN^ initializer)?
  ;
  
declarator
  : direct_declarator
  ;

direct_declarator
  :   IDENTIFIER (declarator_suffix)*
  ;

declarator_suffix
  :  LPAREN! parameter_type_list RPAREN!
  ;
  
initializer
  : additive_expression
  //| LBRACE! initializer_list (COMMA!)? RBRACE!
  ;

/*initializer_list
  : initializer (COMMA! initializer)*
  ;*/
  
statement
  : jump_statement
  | expression_statement
  | (TK_while)=>iteration_statement
  | (TK_if)=>selection_statement
  ;
  
expression_statement
  : (expression)? SEMI!
  ;
  
jump_statement
  : TK_goto^ IDENTIFIER SEMI!
  | TK_continue^ SEMI!
  | TK_break^ SEMI!
  | TK_return^ (expression)? SEMI! 
  ;
  
iteration_statement
  : TK_while^ LPAREN! conditional_expression RPAREN! body 
  //| TK_do^ sub_block TK_while LPAREN! expression RPAREN! SEMI!
  //| TK_for^ LPAREN! expression_statement expression_statement (expression)? RPAREN! sub_block
  ;
  
selection_statement
  : TK_if^ LPAREN! conditional_expression RPAREN! body (TK_else! body)?
  //| TK_switch^ LPAREN! expression RPAREN! statement_list
  ;

body
  : compound_statement 
  //| statement
  ;
  
expression
  : (IDENTIFIER assignment_operator)=>assignment_expression
  | shift_expression
  ;
  
assignment_expression!
  : assignee:IDENTIFIER op:assignment_operator assignment:conditional_expression
    { #assignment_expression = #(op, assignee, assignment); }
  ;

shift_expression
  : additive_expression ((SHL^|SHR^) additive_expression)*
  ;
  
additive_expression
  : (multiplicative_expression) (PLUS^ multiplicative_expression | MINUS^ multiplicative_expression)*
  ;

multiplicative_expression
  : (primary_expression) (ASTERISK^ primary_expression | DIV^ primary_expression | MOD^ primary_expression)*
  ;
  
// ----
conditional_expression
  : logical_or_expression_2
  ;

logical_or_expression_2
  : logical_and_expression_2 (LOG_OR^ logical_and_expression_2)*
  ;

logical_and_expression_2
  : equality_expression_2 (LOG_AND^ equality_expression_2)*
  ;
  
equality_expression_2
  : relational_expression_2 ((EQ^ | NEQ^) relational_expression_2)*
  ;

relational_expression_2
  : shift_expression_2 ((LT^|GT^|LEQ^|GEQ^) shift_expression_2)*
  ;

shift_expression_2
  : additive_expression_2 ((SHL^|SHR^) additive_expression_2)*
  ;
  
additive_expression_2
  : (multiplicative_expression_2) (PLUS^ multiplicative_expression_2 | MINUS^ multiplicative_expression_2)*
  ;

multiplicative_expression_2
  : (primary_expression) (ASTERISK^ primary_expression | DIV^ primary_expression | MOD^ primary_expression)*
  ;
  
primary_expression
  : IDENTIFIER
  | constant
  | LPAREN! conditional_expression RPAREN!
  | fn_call
  ;
  
fn_call
  : IDENTIFIER LPAREN! (arg_list)? RPAREN!
    { #fn_call = #([FN_CALL, "fn_call"], #fn_call); }
  ;
  
arg_list
  : expression (COMMA! expression)*
  ;
  
constant
  :   HEX_LITERAL
  |   OCTAL_LITERAL
  |   DECIMAL_LITERAL
  ;
  
assignment_operator
  : ASSIGN
  | MUL_ASSIGN
  | DIV_ASSIGN
  | MOD_ASSIGN
  | ADD_ASSIGN
  | SUB_ASSIGN
  | SHL_ASSIGN
  | SHR_ASSIGN
  | AND_ASSIGN
  | XOR_ASSIGN
  | OR_ASSIGN
  ;
  
  