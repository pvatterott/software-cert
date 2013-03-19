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

/** Either a function definition or any other kind of C decl/def.
 *  The LL(*) analysis algorithm fails to deal with this due to
 *  recursion in the declarator rules.  I'm putting in a
 *  manual predicate here so that we don't backtrack over
 *  the entire function.  Further, you get a better error
 *  as errors within the function itself don't make it fail
 *  to predict that it's a function.  Weird errors previously.
 *  Remember: the goal is to avoid backtrack like the plague
 *  because it makes debugging, actions, and errors harder.
 *
 *  Note that k=1 results in a much smaller predictor for the 
 *  fixed lookahead; k=2 made a few extra thousand lines. ;)
 *  I'll have to optimize that in the future.
 */
external_declaration
  //: ( (declaration_specifiers)? declarator (declaration)* LBRACE )=> function_definition
  : function_definition
  //| declaration
  ;

function_definition
  : (declaration_specifiers)? declarator compound_statement
      //( (declaration)+ compound_statement // K&R style
       //| compound_statement        // ANSI style
      //)
      {#function_definition = #([FN, "fn"], #function_definition); }
  ;

declaration
  //: "typedef" (declaration_specifiers)?
  //  init_declarator_list SEMI // special case, looking for typedef 
  : declaration_specifiers (init_declarator_list)? SEMI!
  { #declaration = #([DECLARATION, "dec"], #declaration); }
  ;

declaration_specifiers
  : (   storage_class_specifier
    |   type_specifier
    |   type_qualifier
    )+
  ;

init_declarator_list
  : init_declarator (COMMA! init_declarator)*
  ;

init_declarator
  : declarator (ASSIGN^ initializer)?
  ;

storage_class_specifier
  : TK_extern
  | TK_static
  | TK_auto
  | TK_register
  ;

type_specifier
  : TK_void   { #type_specifier = #([TYPE, "type"], #type_specifier); }
  //| "char"
  //| "short"
  | TK_int    { #type_specifier = #([TYPE, "type"], #type_specifier); }
  //| "long"
  //| "float"
  //| "double"
  //| "signed"
  //| "unsigned"
  //| struct_or_union_specifier
  //| enum_specifier
  //| type_id
  //{ #type_specifier = #([TYPE, "type"], #type_specifier); }
  ;

/*type_id
    : IDENTIFIER
    ;*/

// STRUCTS
/*struct_or_union_specifier
  : struct_or_union (IDENTIFIER)? LBRACE struct_declaration_list RBRACE
  | struct_or_union IDENTIFIER
  ;

struct_or_union
  : "struct"
  | "union"
  ;

struct_declaration_list
  : (struct_declaration)+
  ;

struct_declaration
  : specifier_qualifier_list struct_declarator_list SEMI
  ;
*/
specifier_qualifier_list
  : ( type_qualifier | type_specifier )+
  ;
/*
struct_declarator_list
  : struct_declarator (COMMA struct_declarator)*
  ;

struct_declarator
  : declarator (COLON constant_expression)?
  | COLON constant_expression
  ;
*/
// ENUMS
/*enum_specifier
  : "enum" LBRACE enumerator_list RBRACE
  | "enum" IDENTIFIER LBRACE enumerator_list RBRACE
  | "enum" IDENTIFIER
  ;

enumerator_list
  : enumerator (COMMA enumerator)*
  ;

enumerator
  : IDENTIFIER (ASSIGN constant_expression)?
  ;*/

type_qualifier
  : TK_const
  | TK_volatile
  ;

declarator
  //: (pointer)? direct_declarator
  //| pointer
  : direct_declarator
  ;

direct_declarator
  //:   ( IDENTIFIER | LPAREN! declarator RPAREN! ) (declarator_suffix)*
  :   IDENTIFIER (declarator_suffix)*
  ;

declarator_suffix
  :   LBRACKET!constant_expression RBRACKET!
    |   LBRACKET! RBRACKET!
    |   LPAREN! parameter_type_list RPAREN!
    |   LPAREN! identifier_list RPAREN!
    |   LPAREN! RPAREN!
  ;

/*pointer
  : ASTERISK (type_qualifier)+ (pointer)?
  | ASTERISK pointer
  | ASTERISK
  ;*/

parameter_type_list
  : parameter_list (COMMA "...")?
  ;

parameter_list
  : parameter_declaration (COMMA! parameter_declaration)*
  { #parameter_list = #([PARAM_LIST, "params"], #parameter_list); }
  ;

parameter_declaration
  : declaration_specifiers (declarator|abstract_declarator)*
  { #parameter_declaration = #([PARAM, "param"], #parameter_declaration); }
  ;

identifier_list
  : IDENTIFIER (COMMA! IDENTIFIER)*
  ;

type_name
  : specifier_qualifier_list (abstract_declarator)?
  ;

abstract_declarator
  //: pointer (direct_abstract_declarator)?
  //| direct_abstract_declarator
  : direct_abstract_declarator
  ;

direct_abstract_declarator
  : ( LPAREN! abstract_declarator RPAREN! | abstract_declarator_suffix ) (abstract_declarator_suffix)*
  ;

abstract_declarator_suffix
  : LBRACKET! RBRACKET!
  | LBRACKET! constant_expression RBRACKET!
  | LPAREN! RPAREN!
  | LPAREN! parameter_type_list RPAREN!
  ;
  
initializer
  : assignment_expression
  | LBRACE! initializer_list (COMMA!)? RBRACE!
  ;

initializer_list
  : initializer (COMMA! initializer)*
  ;

// E x p r e s s i o n s

argument_expression_list
  :   assignment_expression (COMMA! assignment_expression)*
  ;

additive_expression
  : (multiplicative_expression) (PLUS^ multiplicative_expression | MINUS^ multiplicative_expression)*
  ;

multiplicative_expression
  : (cast_expression) (ASTERISK^ cast_expression | DIV^ cast_expression | MOD^ cast_expression)*
  ;

cast_expression
  : LPAREN! type_name RPAREN! cast_expression
  | unary_expression
  ;

unary_expression
  : postfix_expression
  | INC^ unary_expression
  | DEC^ unary_expression
  //| unary_operator cast_expression
  //| TK_sizeof unary_expression
  //| TK_sizeof LPAREN! type_name RPAREN!
  ;

postfix_expression
  :   primary_expression
        (   LBRACKET! expression RBRACKET!
        //|   LPAREN! RPAREN!
        |   LPAREN! (argument_expression_list)? RPAREN!
        //|   PERIOD IDENTIFIER
        //|   ARROW IDENTIFIER
        |   INC
        |   DEC
        )*
  ;

unary_operator
  : BIN_AND
  | ASTERISK
  | PLUS
  | MINUS
  | TILDE
  | BANG
  ;

primary_expression
  : IDENTIFIER
  | constant
  | LPAREN! expression RPAREN!
  ;

constant
    :   HEX_LITERAL
    |   OCTAL_LITERAL
    |   DECIMAL_LITERAL
    |   CHARACTER_LITERAL
    |   STRING_LITERAL
    |   FLOATING_POINT_LITERAL
    ;


/////

expression
  : assignment_expression (COMMA! assignment_expression)*
  ;

constant_expression
  : conditional_expression
  ;

assignment_expression!
  : (assignee:lvalue op:assignment_operator assignment:assignment_expression)
    { #assignment_expression = #(op, assignee, assignment); }
  | conditional_expression
  ;
  
lvalue
  : unary_expression
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

conditional_expression
  : logical_or_expression (Q expression COLON conditional_expression)?
  ;

logical_or_expression
  : logical_and_expression (LOG_OR^ logical_and_expression)*
  ;

logical_and_expression
  : inclusive_or_expression (LOG_AND^ inclusive_or_expression)*
  ;

inclusive_or_expression
  : exclusive_or_expression (BIN_OR^ exclusive_or_expression)*
  ;

exclusive_or_expression
  : and_expression (BIN_XOR^ and_expression)*
  ;

and_expression
  : equality_expression (BIN_AND^ equality_expression)*
  ;
equality_expression
  : relational_expression ((EQ^ | NEQ^) relational_expression)*
  ;

relational_expression
  : shift_expression ((LT^|GT^|LEQ^|GEQ^) shift_expression)*
  ;

shift_expression
  : additive_expression ((SHL^|SHR^) additive_expression)*
  ;

// S t a t e m e n t s

statement
  : expression_statement
  | compound_statement
  | labeled_statement
  | selection_statement
  | iteration_statement
  | jump_statement
  ;

labeled_statement
  : IDENTIFIER COLON! //statement
  | TK_case^ constant_expression COLON! //statement
  | TK_default^ COLON! //statement
  ;

compound_statement
  : LBRACE! (declaration)* (statement)* RBRACE!
    { #compound_statement = #([BLOCK, "block"], #compound_statement); }
  ;

statement_list
  : (statement)+
  ;

expression_statement
  : (expression)? SEMI!
  ;
  
sub_block
  : LBRACKET! (statement)* RBRACKET!
  | statement 
  ;

selection_statement
  : TK_if^ LPAREN! expression RPAREN! sub_block (TK_else sub_block)?
  | TK_switch^ LPAREN! expression RPAREN! statement_list
  ;

iteration_statement
  : TK_while^ LPAREN! expression RPAREN! sub_block
  | TK_do^ sub_block TK_while LPAREN! expression RPAREN! SEMI!
  | TK_for^ LPAREN! expression_statement expression_statement (expression)? RPAREN! sub_block
  ;

jump_statement
  : TK_goto^ IDENTIFIER SEMI!
  | TK_continue^ SEMI!
  | TK_break^ SEMI!
  | TK_return^ (expression)? SEMI!
  ;
