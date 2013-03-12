// $ANTLR 2.7.7 (2006-11-01): "parser.g" -> "CParser.java"$

package edu.mit.compilers.grammar;

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.collections.AST;
import java.util.Hashtable;
import antlr.ASTFactory;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;

public class CParser extends antlr.LLkParser       implements CParserTokenTypes
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

protected CParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public CParser(TokenBuffer tokenBuf) {
  this(tokenBuf,3);
}

protected CParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public CParser(TokenStream lexer) {
  this(lexer,3);
}

public CParser(ParserSharedInputState state) {
  super(state,3);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

	public final void program() throws RecognitionException, TokenStreamException {
		
		traceIn("program");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST program_AST = null;
			
			try {      // for error handling
				{
				int _cnt3=0;
				_loop3:
				do {
					if ((_tokenSet_0.member(LA(1)))) {
						external_declaration();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						if ( _cnt3>=1 ) { break _loop3; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt3++;
				} while (true);
				}
				program_AST = (AST)currentAST.root;
				program_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(PROGRAM,"program")).add(program_AST));
				currentAST.root = program_AST;
				currentAST.child = program_AST!=null &&program_AST.getFirstChild()!=null ?
					program_AST.getFirstChild() : program_AST;
				currentAST.advanceChildToEnd();
				program_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_1);
			}
			returnAST = program_AST;
		} finally { // debugging
			traceOut("program");
		}
	}
	
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
	public final void external_declaration() throws RecognitionException, TokenStreamException {
		
		traceIn("external_declaration");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST external_declaration_AST = null;
			
			try {      // for error handling
				function_definition();
				astFactory.addASTChild(currentAST, returnAST);
				external_declaration_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_2);
			}
			returnAST = external_declaration_AST;
		} finally { // debugging
			traceOut("external_declaration");
		}
	}
	
	public final void function_definition() throws RecognitionException, TokenStreamException {
		
		traceIn("function_definition");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST function_definition_AST = null;
			
			try {      // for error handling
				{
				switch ( LA(1)) {
				case TK_extern:
				case TK_static:
				case TK_auto:
				case TK_register:
				case TK_const:
				case TK_volatile:
				case TK_void:
				case TK_int:
				{
					declaration_specifiers();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case IDENTIFIER:
				case LPAREN:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				declarator();
				astFactory.addASTChild(currentAST, returnAST);
				compound_statement();
				astFactory.addASTChild(currentAST, returnAST);
				function_definition_AST = (AST)currentAST.root;
				function_definition_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(FN,"fn")).add(function_definition_AST));
				currentAST.root = function_definition_AST;
				currentAST.child = function_definition_AST!=null &&function_definition_AST.getFirstChild()!=null ?
					function_definition_AST.getFirstChild() : function_definition_AST;
				currentAST.advanceChildToEnd();
				function_definition_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_2);
			}
			returnAST = function_definition_AST;
		} finally { // debugging
			traceOut("function_definition");
		}
	}
	
	public final void declaration_specifiers() throws RecognitionException, TokenStreamException {
		
		traceIn("declaration_specifiers");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST declaration_specifiers_AST = null;
			
			try {      // for error handling
				{
				int _cnt11=0;
				_loop11:
				do {
					switch ( LA(1)) {
					case TK_extern:
					case TK_static:
					case TK_auto:
					case TK_register:
					{
						storage_class_specifier();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					case TK_void:
					case TK_int:
					{
						type_specifier();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					case TK_const:
					case TK_volatile:
					{
						type_qualifier();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					default:
					{
						if ( _cnt11>=1 ) { break _loop11; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					}
					_cnt11++;
				} while (true);
				}
				declaration_specifiers_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_3);
			}
			returnAST = declaration_specifiers_AST;
		} finally { // debugging
			traceOut("declaration_specifiers");
		}
	}
	
	public final void declarator() throws RecognitionException, TokenStreamException {
		
		traceIn("declarator");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST declarator_AST = null;
			
			try {      // for error handling
				direct_declarator();
				astFactory.addASTChild(currentAST, returnAST);
				declarator_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			}
			returnAST = declarator_AST;
		} finally { // debugging
			traceOut("declarator");
		}
	}
	
	public final void compound_statement() throws RecognitionException, TokenStreamException {
		
		traceIn("compound_statement");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST compound_statement_AST = null;
			
			try {      // for error handling
				match(LBRACE);
				{
				_loop114:
				do {
					if ((_tokenSet_5.member(LA(1)))) {
						declaration();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop114;
					}
					
				} while (true);
				}
				{
				switch ( LA(1)) {
				case TK_goto:
				case TK_continue:
				case TK_break:
				case TK_return:
				case TK_while:
				case TK_do:
				case TK_for:
				case TK_if:
				case TK_switch:
				case TK_case:
				case TK_default:
				case TK_sizeof:
				case IDENTIFIER:
				case CHARACTER_LITERAL:
				case STRING_LITERAL:
				case HEX_LITERAL:
				case DECIMAL_LITERAL:
				case OCTAL_LITERAL:
				case FLOATING_POINT_LITERAL:
				case LBRACE:
				case LPAREN:
				case SEMI:
				case ASTERISK:
				case PLUS:
				case MINUS:
				case INC:
				case DEC:
				case BIN_AND:
				case TILDE:
				case BANG:
				{
					statement_list();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case RBRACE:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				match(RBRACE);
				compound_statement_AST = (AST)currentAST.root;
				compound_statement_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(BLOCK,"block")).add(compound_statement_AST));
				currentAST.root = compound_statement_AST;
				currentAST.child = compound_statement_AST!=null &&compound_statement_AST.getFirstChild()!=null ?
					compound_statement_AST.getFirstChild() : compound_statement_AST;
				currentAST.advanceChildToEnd();
				compound_statement_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_6);
			}
			returnAST = compound_statement_AST;
		} finally { // debugging
			traceOut("compound_statement");
		}
	}
	
	public final void declaration() throws RecognitionException, TokenStreamException {
		
		traceIn("declaration");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST declaration_AST = null;
			
			try {      // for error handling
				declaration_specifiers();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case IDENTIFIER:
				case LPAREN:
				{
					init_declarator_list();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case SEMI:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				match(SEMI);
				declaration_AST = (AST)currentAST.root;
				declaration_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(DECLARATION,"dec")).add(declaration_AST));
				currentAST.root = declaration_AST;
				currentAST.child = declaration_AST!=null &&declaration_AST.getFirstChild()!=null ?
					declaration_AST.getFirstChild() : declaration_AST;
				currentAST.advanceChildToEnd();
				declaration_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_7);
			}
			returnAST = declaration_AST;
		} finally { // debugging
			traceOut("declaration");
		}
	}
	
	public final void init_declarator_list() throws RecognitionException, TokenStreamException {
		
		traceIn("init_declarator_list");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST init_declarator_list_AST = null;
			
			try {      // for error handling
				init_declarator();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop14:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						init_declarator();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop14;
					}
					
				} while (true);
				}
				init_declarator_list_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_8);
			}
			returnAST = init_declarator_list_AST;
		} finally { // debugging
			traceOut("init_declarator_list");
		}
	}
	
	public final void storage_class_specifier() throws RecognitionException, TokenStreamException {
		
		traceIn("storage_class_specifier");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST storage_class_specifier_AST = null;
			
			try {      // for error handling
				switch ( LA(1)) {
				case TK_extern:
				{
					AST tmp5_AST = null;
					tmp5_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp5_AST);
					match(TK_extern);
					storage_class_specifier_AST = (AST)currentAST.root;
					break;
				}
				case TK_static:
				{
					AST tmp6_AST = null;
					tmp6_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp6_AST);
					match(TK_static);
					storage_class_specifier_AST = (AST)currentAST.root;
					break;
				}
				case TK_auto:
				{
					AST tmp7_AST = null;
					tmp7_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp7_AST);
					match(TK_auto);
					storage_class_specifier_AST = (AST)currentAST.root;
					break;
				}
				case TK_register:
				{
					AST tmp8_AST = null;
					tmp8_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp8_AST);
					match(TK_register);
					storage_class_specifier_AST = (AST)currentAST.root;
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_9);
			}
			returnAST = storage_class_specifier_AST;
		} finally { // debugging
			traceOut("storage_class_specifier");
		}
	}
	
	public final void type_specifier() throws RecognitionException, TokenStreamException {
		
		traceIn("type_specifier");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST type_specifier_AST = null;
			
			try {      // for error handling
				switch ( LA(1)) {
				case TK_void:
				{
					AST tmp9_AST = null;
					tmp9_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp9_AST);
					match(TK_void);
					type_specifier_AST = (AST)currentAST.root;
					type_specifier_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(TYPE,"type")).add(type_specifier_AST));
					currentAST.root = type_specifier_AST;
					currentAST.child = type_specifier_AST!=null &&type_specifier_AST.getFirstChild()!=null ?
						type_specifier_AST.getFirstChild() : type_specifier_AST;
					currentAST.advanceChildToEnd();
					type_specifier_AST = (AST)currentAST.root;
					break;
				}
				case TK_int:
				{
					AST tmp10_AST = null;
					tmp10_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp10_AST);
					match(TK_int);
					type_specifier_AST = (AST)currentAST.root;
					type_specifier_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(TYPE,"type")).add(type_specifier_AST));
					currentAST.root = type_specifier_AST;
					currentAST.child = type_specifier_AST!=null &&type_specifier_AST.getFirstChild()!=null ?
						type_specifier_AST.getFirstChild() : type_specifier_AST;
					currentAST.advanceChildToEnd();
					type_specifier_AST = (AST)currentAST.root;
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_9);
			}
			returnAST = type_specifier_AST;
		} finally { // debugging
			traceOut("type_specifier");
		}
	}
	
	public final void type_qualifier() throws RecognitionException, TokenStreamException {
		
		traceIn("type_qualifier");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST type_qualifier_AST = null;
			
			try {      // for error handling
				switch ( LA(1)) {
				case TK_const:
				{
					AST tmp11_AST = null;
					tmp11_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp11_AST);
					match(TK_const);
					type_qualifier_AST = (AST)currentAST.root;
					break;
				}
				case TK_volatile:
				{
					AST tmp12_AST = null;
					tmp12_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp12_AST);
					match(TK_volatile);
					type_qualifier_AST = (AST)currentAST.root;
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_9);
			}
			returnAST = type_qualifier_AST;
		} finally { // debugging
			traceOut("type_qualifier");
		}
	}
	
	public final void init_declarator() throws RecognitionException, TokenStreamException {
		
		traceIn("init_declarator");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST init_declarator_AST = null;
			
			try {      // for error handling
				declarator();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case ASSIGN:
				{
					AST tmp13_AST = null;
					tmp13_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp13_AST);
					match(ASSIGN);
					initializer();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case SEMI:
				case COMMA:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				init_declarator_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_10);
			}
			returnAST = init_declarator_AST;
		} finally { // debugging
			traceOut("init_declarator");
		}
	}
	
	public final void initializer() throws RecognitionException, TokenStreamException {
		
		traceIn("initializer");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST initializer_AST = null;
			
			try {      // for error handling
				switch ( LA(1)) {
				case TK_sizeof:
				case IDENTIFIER:
				case CHARACTER_LITERAL:
				case STRING_LITERAL:
				case HEX_LITERAL:
				case DECIMAL_LITERAL:
				case OCTAL_LITERAL:
				case FLOATING_POINT_LITERAL:
				case LPAREN:
				case ASTERISK:
				case PLUS:
				case MINUS:
				case INC:
				case DEC:
				case BIN_AND:
				case TILDE:
				case BANG:
				{
					assignment_expression();
					astFactory.addASTChild(currentAST, returnAST);
					initializer_AST = (AST)currentAST.root;
					break;
				}
				case LBRACE:
				{
					match(LBRACE);
					initializer_list();
					astFactory.addASTChild(currentAST, returnAST);
					{
					switch ( LA(1)) {
					case COMMA:
					{
						match(COMMA);
						break;
					}
					case RBRACE:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(RBRACE);
					initializer_AST = (AST)currentAST.root;
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_11);
			}
			returnAST = initializer_AST;
		} finally { // debugging
			traceOut("initializer");
		}
	}
	
	public final void type_id() throws RecognitionException, TokenStreamException {
		
		traceIn("type_id");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST type_id_AST = null;
			
			try {      // for error handling
				AST tmp17_AST = null;
				tmp17_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp17_AST);
				match(IDENTIFIER);
				type_id_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_1);
			}
			returnAST = type_id_AST;
		} finally { // debugging
			traceOut("type_id");
		}
	}
	
	public final void specifier_qualifier_list() throws RecognitionException, TokenStreamException {
		
		traceIn("specifier_qualifier_list");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST specifier_qualifier_list_AST = null;
			
			try {      // for error handling
				{
				int _cnt22=0;
				_loop22:
				do {
					switch ( LA(1)) {
					case TK_const:
					case TK_volatile:
					{
						type_qualifier();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					case TK_void:
					case TK_int:
					{
						type_specifier();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					default:
					{
						if ( _cnt22>=1 ) { break _loop22; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					}
					_cnt22++;
				} while (true);
				}
				specifier_qualifier_list_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_12);
			}
			returnAST = specifier_qualifier_list_AST;
		} finally { // debugging
			traceOut("specifier_qualifier_list");
		}
	}
	
	public final void direct_declarator() throws RecognitionException, TokenStreamException {
		
		traceIn("direct_declarator");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST direct_declarator_AST = null;
			
			try {      // for error handling
				{
				switch ( LA(1)) {
				case IDENTIFIER:
				{
					AST tmp18_AST = null;
					tmp18_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp18_AST);
					match(IDENTIFIER);
					break;
				}
				case LPAREN:
				{
					match(LPAREN);
					declarator();
					astFactory.addASTChild(currentAST, returnAST);
					match(RPAREN);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				_loop28:
				do {
					if ((LA(1)==LPAREN||LA(1)==LBRACKET) && (_tokenSet_13.member(LA(2))) && (_tokenSet_14.member(LA(3)))) {
						declarator_suffix();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop28;
					}
					
				} while (true);
				}
				direct_declarator_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			}
			returnAST = direct_declarator_AST;
		} finally { // debugging
			traceOut("direct_declarator");
		}
	}
	
	public final void declarator_suffix() throws RecognitionException, TokenStreamException {
		
		traceIn("declarator_suffix");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST declarator_suffix_AST = null;
			
			try {      // for error handling
				if ((LA(1)==LBRACKET) && (_tokenSet_15.member(LA(2)))) {
					match(LBRACKET);
					constant_expression();
					astFactory.addASTChild(currentAST, returnAST);
					match(RBRACKET);
					declarator_suffix_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==LBRACKET) && (LA(2)==RBRACKET)) {
					match(LBRACKET);
					match(RBRACKET);
					declarator_suffix_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==LPAREN) && (_tokenSet_5.member(LA(2)))) {
					match(LPAREN);
					parameter_type_list();
					astFactory.addASTChild(currentAST, returnAST);
					match(RPAREN);
					declarator_suffix_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==LPAREN) && (LA(2)==IDENTIFIER)) {
					match(LPAREN);
					identifier_list();
					astFactory.addASTChild(currentAST, returnAST);
					match(RPAREN);
					declarator_suffix_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==LPAREN) && (LA(2)==RPAREN)) {
					match(LPAREN);
					match(RPAREN);
					declarator_suffix_AST = (AST)currentAST.root;
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			}
			returnAST = declarator_suffix_AST;
		} finally { // debugging
			traceOut("declarator_suffix");
		}
	}
	
	public final void constant_expression() throws RecognitionException, TokenStreamException {
		
		traceIn("constant_expression");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST constant_expression_AST = null;
			
			try {      // for error handling
				conditional_expression();
				astFactory.addASTChild(currentAST, returnAST);
				constant_expression_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_16);
			}
			returnAST = constant_expression_AST;
		} finally { // debugging
			traceOut("constant_expression");
		}
	}
	
	public final void parameter_type_list() throws RecognitionException, TokenStreamException {
		
		traceIn("parameter_type_list");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST parameter_type_list_AST = null;
			
			try {      // for error handling
				parameter_list();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case COMMA:
				{
					AST tmp31_AST = null;
					tmp31_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp31_AST);
					match(COMMA);
					AST tmp32_AST = null;
					tmp32_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp32_AST);
					match(105);
					break;
				}
				case RPAREN:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				parameter_type_list_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_17);
			}
			returnAST = parameter_type_list_AST;
		} finally { // debugging
			traceOut("parameter_type_list");
		}
	}
	
	public final void identifier_list() throws RecognitionException, TokenStreamException {
		
		traceIn("identifier_list");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST identifier_list_AST = null;
			
			try {      // for error handling
				AST tmp33_AST = null;
				tmp33_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp33_AST);
				match(IDENTIFIER);
				{
				_loop40:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						AST tmp35_AST = null;
						tmp35_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp35_AST);
						match(IDENTIFIER);
					}
					else {
						break _loop40;
					}
					
				} while (true);
				}
				identifier_list_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_17);
			}
			returnAST = identifier_list_AST;
		} finally { // debugging
			traceOut("identifier_list");
		}
	}
	
	public final void parameter_list() throws RecognitionException, TokenStreamException {
		
		traceIn("parameter_list");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST parameter_list_AST = null;
			
			try {      // for error handling
				parameter_declaration();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop34:
				do {
					if ((LA(1)==COMMA) && (_tokenSet_5.member(LA(2)))) {
						match(COMMA);
						parameter_declaration();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop34;
					}
					
				} while (true);
				}
				parameter_list_AST = (AST)currentAST.root;
				parameter_list_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(PARAM_LIST,"params")).add(parameter_list_AST));
				currentAST.root = parameter_list_AST;
				currentAST.child = parameter_list_AST!=null &&parameter_list_AST.getFirstChild()!=null ?
					parameter_list_AST.getFirstChild() : parameter_list_AST;
				currentAST.advanceChildToEnd();
				parameter_list_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_18);
			}
			returnAST = parameter_list_AST;
		} finally { // debugging
			traceOut("parameter_list");
		}
	}
	
	public final void parameter_declaration() throws RecognitionException, TokenStreamException {
		
		traceIn("parameter_declaration");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST parameter_declaration_AST = null;
			
			try {      // for error handling
				declaration_specifiers();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop37:
				do {
					if ((LA(1)==IDENTIFIER||LA(1)==LPAREN) && (_tokenSet_19.member(LA(2))) && (_tokenSet_20.member(LA(3)))) {
						declarator();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else if ((LA(1)==LPAREN||LA(1)==LBRACKET) && (_tokenSet_21.member(LA(2))) && (_tokenSet_22.member(LA(3)))) {
						abstract_declarator();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop37;
					}
					
				} while (true);
				}
				parameter_declaration_AST = (AST)currentAST.root;
				parameter_declaration_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(PARAM,"param")).add(parameter_declaration_AST));
				currentAST.root = parameter_declaration_AST;
				currentAST.child = parameter_declaration_AST!=null &&parameter_declaration_AST.getFirstChild()!=null ?
					parameter_declaration_AST.getFirstChild() : parameter_declaration_AST;
				currentAST.advanceChildToEnd();
				parameter_declaration_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_18);
			}
			returnAST = parameter_declaration_AST;
		} finally { // debugging
			traceOut("parameter_declaration");
		}
	}
	
	public final void abstract_declarator() throws RecognitionException, TokenStreamException {
		
		traceIn("abstract_declarator");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST abstract_declarator_AST = null;
			
			try {      // for error handling
				direct_abstract_declarator();
				astFactory.addASTChild(currentAST, returnAST);
				abstract_declarator_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_19);
			}
			returnAST = abstract_declarator_AST;
		} finally { // debugging
			traceOut("abstract_declarator");
		}
	}
	
	public final void type_name() throws RecognitionException, TokenStreamException {
		
		traceIn("type_name");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST type_name_AST = null;
			
			try {      // for error handling
				specifier_qualifier_list();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case LPAREN:
				case LBRACKET:
				{
					abstract_declarator();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case RPAREN:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				type_name_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_17);
			}
			returnAST = type_name_AST;
		} finally { // debugging
			traceOut("type_name");
		}
	}
	
	public final void direct_abstract_declarator() throws RecognitionException, TokenStreamException {
		
		traceIn("direct_abstract_declarator");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST direct_abstract_declarator_AST = null;
			
			try {      // for error handling
				{
				if ((LA(1)==LPAREN) && (LA(2)==LPAREN||LA(2)==LBRACKET) && (_tokenSet_21.member(LA(3)))) {
					match(LPAREN);
					abstract_declarator();
					astFactory.addASTChild(currentAST, returnAST);
					match(RPAREN);
				}
				else if ((LA(1)==LPAREN||LA(1)==LBRACKET) && (_tokenSet_13.member(LA(2))) && (_tokenSet_22.member(LA(3)))) {
					abstract_declarator_suffix();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				_loop47:
				do {
					if ((LA(1)==LPAREN||LA(1)==LBRACKET) && (_tokenSet_13.member(LA(2))) && (_tokenSet_22.member(LA(3)))) {
						abstract_declarator_suffix();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop47;
					}
					
				} while (true);
				}
				direct_abstract_declarator_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_19);
			}
			returnAST = direct_abstract_declarator_AST;
		} finally { // debugging
			traceOut("direct_abstract_declarator");
		}
	}
	
	public final void abstract_declarator_suffix() throws RecognitionException, TokenStreamException {
		
		traceIn("abstract_declarator_suffix");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST abstract_declarator_suffix_AST = null;
			
			try {      // for error handling
				if ((LA(1)==LBRACKET) && (LA(2)==RBRACKET)) {
					match(LBRACKET);
					match(RBRACKET);
					abstract_declarator_suffix_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==LBRACKET) && (_tokenSet_15.member(LA(2)))) {
					match(LBRACKET);
					constant_expression();
					astFactory.addASTChild(currentAST, returnAST);
					match(RBRACKET);
					abstract_declarator_suffix_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==LPAREN) && (LA(2)==RPAREN)) {
					match(LPAREN);
					match(RPAREN);
					abstract_declarator_suffix_AST = (AST)currentAST.root;
				}
				else if ((LA(1)==LPAREN) && (_tokenSet_5.member(LA(2)))) {
					match(LPAREN);
					parameter_type_list();
					astFactory.addASTChild(currentAST, returnAST);
					match(RPAREN);
					abstract_declarator_suffix_AST = (AST)currentAST.root;
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_19);
			}
			returnAST = abstract_declarator_suffix_AST;
		} finally { // debugging
			traceOut("abstract_declarator_suffix");
		}
	}
	
	public final void assignment_expression() throws RecognitionException, TokenStreamException {
		
		traceIn("assignment_expression");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST assignment_expression_AST = null;
			
			try {      // for error handling
				if ((_tokenSet_15.member(LA(1))) && (_tokenSet_23.member(LA(2))) && (_tokenSet_24.member(LA(3)))) {
					conditional_expression();
					astFactory.addASTChild(currentAST, returnAST);
					assignment_expression_AST = (AST)currentAST.root;
				}
				else if ((_tokenSet_15.member(LA(1))) && (_tokenSet_25.member(LA(2))) && (_tokenSet_26.member(LA(3)))) {
					{
					lvalue();
					astFactory.addASTChild(currentAST, returnAST);
					assignment_operator();
					astFactory.addASTChild(currentAST, returnAST);
					assignment_expression();
					astFactory.addASTChild(currentAST, returnAST);
					}
					assignment_expression_AST = (AST)currentAST.root;
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_27);
			}
			returnAST = assignment_expression_AST;
		} finally { // debugging
			traceOut("assignment_expression");
		}
	}
	
	public final void initializer_list() throws RecognitionException, TokenStreamException {
		
		traceIn("initializer_list");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST initializer_list_AST = null;
			
			try {      // for error handling
				initializer();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop53:
				do {
					if ((LA(1)==COMMA) && (_tokenSet_28.member(LA(2)))) {
						match(COMMA);
						initializer();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop53;
					}
					
				} while (true);
				}
				initializer_list_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_29);
			}
			returnAST = initializer_list_AST;
		} finally { // debugging
			traceOut("initializer_list");
		}
	}
	
	public final void argument_expression_list() throws RecognitionException, TokenStreamException {
		
		traceIn("argument_expression_list");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST argument_expression_list_AST = null;
			
			try {      // for error handling
				assignment_expression();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop56:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						assignment_expression();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop56;
					}
					
				} while (true);
				}
				argument_expression_list_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_17);
			}
			returnAST = argument_expression_list_AST;
		} finally { // debugging
			traceOut("argument_expression_list");
		}
	}
	
	public final void additive_expression() throws RecognitionException, TokenStreamException {
		
		traceIn("additive_expression");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST additive_expression_AST = null;
			
			try {      // for error handling
				{
				multiplicative_expression();
				astFactory.addASTChild(currentAST, returnAST);
				}
				{
				_loop60:
				do {
					switch ( LA(1)) {
					case PLUS:
					{
						AST tmp49_AST = null;
						tmp49_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp49_AST);
						match(PLUS);
						multiplicative_expression();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					case MINUS:
					{
						AST tmp50_AST = null;
						tmp50_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp50_AST);
						match(MINUS);
						multiplicative_expression();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					default:
					{
						break _loop60;
					}
					}
				} while (true);
				}
				additive_expression_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_30);
			}
			returnAST = additive_expression_AST;
		} finally { // debugging
			traceOut("additive_expression");
		}
	}
	
	public final void multiplicative_expression() throws RecognitionException, TokenStreamException {
		
		traceIn("multiplicative_expression");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST multiplicative_expression_AST = null;
			
			try {      // for error handling
				{
				cast_expression();
				astFactory.addASTChild(currentAST, returnAST);
				}
				{
				_loop64:
				do {
					switch ( LA(1)) {
					case ASTERISK:
					{
						AST tmp51_AST = null;
						tmp51_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp51_AST);
						match(ASTERISK);
						cast_expression();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					case DIV:
					{
						AST tmp52_AST = null;
						tmp52_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp52_AST);
						match(DIV);
						cast_expression();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					case MOD:
					{
						AST tmp53_AST = null;
						tmp53_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp53_AST);
						match(MOD);
						cast_expression();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					default:
					{
						break _loop64;
					}
					}
				} while (true);
				}
				multiplicative_expression_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_31);
			}
			returnAST = multiplicative_expression_AST;
		} finally { // debugging
			traceOut("multiplicative_expression");
		}
	}
	
	public final void cast_expression() throws RecognitionException, TokenStreamException {
		
		traceIn("cast_expression");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST cast_expression_AST = null;
			
			try {      // for error handling
				if ((LA(1)==LPAREN) && (_tokenSet_32.member(LA(2)))) {
					match(LPAREN);
					type_name();
					astFactory.addASTChild(currentAST, returnAST);
					match(RPAREN);
					cast_expression();
					astFactory.addASTChild(currentAST, returnAST);
					cast_expression_AST = (AST)currentAST.root;
				}
				else if ((_tokenSet_15.member(LA(1))) && (_tokenSet_33.member(LA(2)))) {
					unary_expression();
					astFactory.addASTChild(currentAST, returnAST);
					cast_expression_AST = (AST)currentAST.root;
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_34);
			}
			returnAST = cast_expression_AST;
		} finally { // debugging
			traceOut("cast_expression");
		}
	}
	
	public final void unary_expression() throws RecognitionException, TokenStreamException {
		
		traceIn("unary_expression");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST unary_expression_AST = null;
			
			try {      // for error handling
				switch ( LA(1)) {
				case IDENTIFIER:
				case CHARACTER_LITERAL:
				case STRING_LITERAL:
				case HEX_LITERAL:
				case DECIMAL_LITERAL:
				case OCTAL_LITERAL:
				case FLOATING_POINT_LITERAL:
				case LPAREN:
				{
					postfix_expression();
					astFactory.addASTChild(currentAST, returnAST);
					unary_expression_AST = (AST)currentAST.root;
					break;
				}
				case INC:
				{
					AST tmp56_AST = null;
					tmp56_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp56_AST);
					match(INC);
					unary_expression();
					astFactory.addASTChild(currentAST, returnAST);
					unary_expression_AST = (AST)currentAST.root;
					break;
				}
				case DEC:
				{
					AST tmp57_AST = null;
					tmp57_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp57_AST);
					match(DEC);
					unary_expression();
					astFactory.addASTChild(currentAST, returnAST);
					unary_expression_AST = (AST)currentAST.root;
					break;
				}
				case ASTERISK:
				case PLUS:
				case MINUS:
				case BIN_AND:
				case TILDE:
				case BANG:
				{
					unary_operator();
					astFactory.addASTChild(currentAST, returnAST);
					cast_expression();
					astFactory.addASTChild(currentAST, returnAST);
					unary_expression_AST = (AST)currentAST.root;
					break;
				}
				default:
					if ((LA(1)==TK_sizeof) && (_tokenSet_15.member(LA(2))) && (_tokenSet_33.member(LA(3)))) {
						AST tmp58_AST = null;
						tmp58_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp58_AST);
						match(TK_sizeof);
						unary_expression();
						astFactory.addASTChild(currentAST, returnAST);
						unary_expression_AST = (AST)currentAST.root;
					}
					else if ((LA(1)==TK_sizeof) && (LA(2)==LPAREN) && (_tokenSet_32.member(LA(3)))) {
						AST tmp59_AST = null;
						tmp59_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp59_AST);
						match(TK_sizeof);
						match(LPAREN);
						type_name();
						astFactory.addASTChild(currentAST, returnAST);
						match(RPAREN);
						unary_expression_AST = (AST)currentAST.root;
					}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_34);
			}
			returnAST = unary_expression_AST;
		} finally { // debugging
			traceOut("unary_expression");
		}
	}
	
	public final void postfix_expression() throws RecognitionException, TokenStreamException {
		
		traceIn("postfix_expression");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST postfix_expression_AST = null;
			
			try {      // for error handling
				primary_expression();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop69:
				do {
					switch ( LA(1)) {
					case LBRACKET:
					{
						AST tmp62_AST = null;
						tmp62_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp62_AST);
						match(LBRACKET);
						expression();
						astFactory.addASTChild(currentAST, returnAST);
						AST tmp63_AST = null;
						tmp63_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp63_AST);
						match(RBRACKET);
						break;
					}
					case PERIOD:
					{
						AST tmp64_AST = null;
						tmp64_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp64_AST);
						match(PERIOD);
						AST tmp65_AST = null;
						tmp65_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp65_AST);
						match(IDENTIFIER);
						break;
					}
					case ARROW:
					{
						AST tmp66_AST = null;
						tmp66_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp66_AST);
						match(ARROW);
						AST tmp67_AST = null;
						tmp67_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp67_AST);
						match(IDENTIFIER);
						break;
					}
					case INC:
					{
						AST tmp68_AST = null;
						tmp68_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp68_AST);
						match(INC);
						break;
					}
					case DEC:
					{
						AST tmp69_AST = null;
						tmp69_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp69_AST);
						match(DEC);
						break;
					}
					default:
						if ((LA(1)==LPAREN) && (LA(2)==RPAREN)) {
							AST tmp70_AST = null;
							tmp70_AST = astFactory.create(LT(1));
							astFactory.addASTChild(currentAST, tmp70_AST);
							match(LPAREN);
							AST tmp71_AST = null;
							tmp71_AST = astFactory.create(LT(1));
							astFactory.addASTChild(currentAST, tmp71_AST);
							match(RPAREN);
						}
						else if ((LA(1)==LPAREN) && (_tokenSet_15.member(LA(2)))) {
							AST tmp72_AST = null;
							tmp72_AST = astFactory.create(LT(1));
							astFactory.addASTChild(currentAST, tmp72_AST);
							match(LPAREN);
							argument_expression_list();
							astFactory.addASTChild(currentAST, returnAST);
							AST tmp73_AST = null;
							tmp73_AST = astFactory.create(LT(1));
							astFactory.addASTChild(currentAST, tmp73_AST);
							match(RPAREN);
						}
					else {
						break _loop69;
					}
					}
				} while (true);
				}
				postfix_expression_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_34);
			}
			returnAST = postfix_expression_AST;
		} finally { // debugging
			traceOut("postfix_expression");
		}
	}
	
	public final void unary_operator() throws RecognitionException, TokenStreamException {
		
		traceIn("unary_operator");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST unary_operator_AST = null;
			
			try {      // for error handling
				switch ( LA(1)) {
				case BIN_AND:
				{
					AST tmp74_AST = null;
					tmp74_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp74_AST);
					match(BIN_AND);
					unary_operator_AST = (AST)currentAST.root;
					break;
				}
				case ASTERISK:
				{
					AST tmp75_AST = null;
					tmp75_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp75_AST);
					match(ASTERISK);
					unary_operator_AST = (AST)currentAST.root;
					break;
				}
				case PLUS:
				{
					AST tmp76_AST = null;
					tmp76_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp76_AST);
					match(PLUS);
					unary_operator_AST = (AST)currentAST.root;
					break;
				}
				case MINUS:
				{
					AST tmp77_AST = null;
					tmp77_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp77_AST);
					match(MINUS);
					unary_operator_AST = (AST)currentAST.root;
					break;
				}
				case TILDE:
				{
					AST tmp78_AST = null;
					tmp78_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp78_AST);
					match(TILDE);
					unary_operator_AST = (AST)currentAST.root;
					break;
				}
				case BANG:
				{
					AST tmp79_AST = null;
					tmp79_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp79_AST);
					match(BANG);
					unary_operator_AST = (AST)currentAST.root;
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_15);
			}
			returnAST = unary_operator_AST;
		} finally { // debugging
			traceOut("unary_operator");
		}
	}
	
	public final void primary_expression() throws RecognitionException, TokenStreamException {
		
		traceIn("primary_expression");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST primary_expression_AST = null;
			
			try {      // for error handling
				switch ( LA(1)) {
				case IDENTIFIER:
				{
					AST tmp80_AST = null;
					tmp80_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp80_AST);
					match(IDENTIFIER);
					primary_expression_AST = (AST)currentAST.root;
					break;
				}
				case CHARACTER_LITERAL:
				case STRING_LITERAL:
				case HEX_LITERAL:
				case DECIMAL_LITERAL:
				case OCTAL_LITERAL:
				case FLOATING_POINT_LITERAL:
				{
					constant();
					astFactory.addASTChild(currentAST, returnAST);
					primary_expression_AST = (AST)currentAST.root;
					break;
				}
				case LPAREN:
				{
					match(LPAREN);
					expression();
					astFactory.addASTChild(currentAST, returnAST);
					match(RPAREN);
					primary_expression_AST = (AST)currentAST.root;
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_35);
			}
			returnAST = primary_expression_AST;
		} finally { // debugging
			traceOut("primary_expression");
		}
	}
	
	public final void expression() throws RecognitionException, TokenStreamException {
		
		traceIn("expression");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST expression_AST = null;
			
			try {      // for error handling
				assignment_expression();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop75:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						assignment_expression();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop75;
					}
					
				} while (true);
				}
				expression_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_36);
			}
			returnAST = expression_AST;
		} finally { // debugging
			traceOut("expression");
		}
	}
	
	public final void constant() throws RecognitionException, TokenStreamException {
		
		traceIn("constant");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST constant_AST = null;
			
			try {      // for error handling
				switch ( LA(1)) {
				case HEX_LITERAL:
				{
					AST tmp84_AST = null;
					tmp84_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp84_AST);
					match(HEX_LITERAL);
					constant_AST = (AST)currentAST.root;
					break;
				}
				case OCTAL_LITERAL:
				{
					AST tmp85_AST = null;
					tmp85_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp85_AST);
					match(OCTAL_LITERAL);
					constant_AST = (AST)currentAST.root;
					break;
				}
				case DECIMAL_LITERAL:
				{
					AST tmp86_AST = null;
					tmp86_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp86_AST);
					match(DECIMAL_LITERAL);
					constant_AST = (AST)currentAST.root;
					break;
				}
				case CHARACTER_LITERAL:
				{
					AST tmp87_AST = null;
					tmp87_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp87_AST);
					match(CHARACTER_LITERAL);
					constant_AST = (AST)currentAST.root;
					break;
				}
				case STRING_LITERAL:
				{
					AST tmp88_AST = null;
					tmp88_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp88_AST);
					match(STRING_LITERAL);
					constant_AST = (AST)currentAST.root;
					break;
				}
				case FLOATING_POINT_LITERAL:
				{
					AST tmp89_AST = null;
					tmp89_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp89_AST);
					match(FLOATING_POINT_LITERAL);
					constant_AST = (AST)currentAST.root;
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_35);
			}
			returnAST = constant_AST;
		} finally { // debugging
			traceOut("constant");
		}
	}
	
	public final void conditional_expression() throws RecognitionException, TokenStreamException {
		
		traceIn("conditional_expression");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST conditional_expression_AST = null;
			
			try {      // for error handling
				logical_or_expression();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case Q:
				{
					AST tmp90_AST = null;
					tmp90_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp90_AST);
					match(Q);
					expression();
					astFactory.addASTChild(currentAST, returnAST);
					AST tmp91_AST = null;
					tmp91_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp91_AST);
					match(COLON);
					conditional_expression();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case RBRACE:
				case RPAREN:
				case RBRACKET:
				case SEMI:
				case COLON:
				case COMMA:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				conditional_expression_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_27);
			}
			returnAST = conditional_expression_AST;
		} finally { // debugging
			traceOut("conditional_expression");
		}
	}
	
	public final void lvalue() throws RecognitionException, TokenStreamException {
		
		traceIn("lvalue");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST lvalue_AST = null;
			
			try {      // for error handling
				unary_expression();
				astFactory.addASTChild(currentAST, returnAST);
				lvalue_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_37);
			}
			returnAST = lvalue_AST;
		} finally { // debugging
			traceOut("lvalue");
		}
	}
	
	public final void assignment_operator() throws RecognitionException, TokenStreamException {
		
		traceIn("assignment_operator");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST assignment_operator_AST = null;
			
			try {      // for error handling
				switch ( LA(1)) {
				case ASSIGN:
				{
					AST tmp92_AST = null;
					tmp92_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp92_AST);
					match(ASSIGN);
					assignment_operator_AST = (AST)currentAST.root;
					break;
				}
				case MUL_ASSIGN:
				{
					AST tmp93_AST = null;
					tmp93_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp93_AST);
					match(MUL_ASSIGN);
					assignment_operator_AST = (AST)currentAST.root;
					break;
				}
				case DIV_ASSIGN:
				{
					AST tmp94_AST = null;
					tmp94_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp94_AST);
					match(DIV_ASSIGN);
					assignment_operator_AST = (AST)currentAST.root;
					break;
				}
				case MOD_ASSIGN:
				{
					AST tmp95_AST = null;
					tmp95_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp95_AST);
					match(MOD_ASSIGN);
					assignment_operator_AST = (AST)currentAST.root;
					break;
				}
				case ADD_ASSIGN:
				{
					AST tmp96_AST = null;
					tmp96_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp96_AST);
					match(ADD_ASSIGN);
					assignment_operator_AST = (AST)currentAST.root;
					break;
				}
				case SUB_ASSIGN:
				{
					AST tmp97_AST = null;
					tmp97_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp97_AST);
					match(SUB_ASSIGN);
					assignment_operator_AST = (AST)currentAST.root;
					break;
				}
				case SHL_ASSIGN:
				{
					AST tmp98_AST = null;
					tmp98_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp98_AST);
					match(SHL_ASSIGN);
					assignment_operator_AST = (AST)currentAST.root;
					break;
				}
				case SHR_ASSIGN:
				{
					AST tmp99_AST = null;
					tmp99_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp99_AST);
					match(SHR_ASSIGN);
					assignment_operator_AST = (AST)currentAST.root;
					break;
				}
				case AND_ASSIGN:
				{
					AST tmp100_AST = null;
					tmp100_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp100_AST);
					match(AND_ASSIGN);
					assignment_operator_AST = (AST)currentAST.root;
					break;
				}
				case XOR_ASSIGN:
				{
					AST tmp101_AST = null;
					tmp101_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp101_AST);
					match(XOR_ASSIGN);
					assignment_operator_AST = (AST)currentAST.root;
					break;
				}
				case OR_ASSIGN:
				{
					AST tmp102_AST = null;
					tmp102_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp102_AST);
					match(OR_ASSIGN);
					assignment_operator_AST = (AST)currentAST.root;
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_15);
			}
			returnAST = assignment_operator_AST;
		} finally { // debugging
			traceOut("assignment_operator");
		}
	}
	
	public final void logical_or_expression() throws RecognitionException, TokenStreamException {
		
		traceIn("logical_or_expression");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST logical_or_expression_AST = null;
			
			try {      // for error handling
				logical_and_expression();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop85:
				do {
					if ((LA(1)==LOG_OR)) {
						AST tmp103_AST = null;
						tmp103_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp103_AST);
						match(LOG_OR);
						logical_and_expression();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop85;
					}
					
				} while (true);
				}
				logical_or_expression_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_38);
			}
			returnAST = logical_or_expression_AST;
		} finally { // debugging
			traceOut("logical_or_expression");
		}
	}
	
	public final void logical_and_expression() throws RecognitionException, TokenStreamException {
		
		traceIn("logical_and_expression");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST logical_and_expression_AST = null;
			
			try {      // for error handling
				inclusive_or_expression();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop88:
				do {
					if ((LA(1)==LOG_AND)) {
						AST tmp104_AST = null;
						tmp104_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp104_AST);
						match(LOG_AND);
						inclusive_or_expression();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop88;
					}
					
				} while (true);
				}
				logical_and_expression_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_39);
			}
			returnAST = logical_and_expression_AST;
		} finally { // debugging
			traceOut("logical_and_expression");
		}
	}
	
	public final void inclusive_or_expression() throws RecognitionException, TokenStreamException {
		
		traceIn("inclusive_or_expression");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST inclusive_or_expression_AST = null;
			
			try {      // for error handling
				exclusive_or_expression();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop91:
				do {
					if ((LA(1)==BIN_OR)) {
						AST tmp105_AST = null;
						tmp105_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp105_AST);
						match(BIN_OR);
						exclusive_or_expression();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop91;
					}
					
				} while (true);
				}
				inclusive_or_expression_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_40);
			}
			returnAST = inclusive_or_expression_AST;
		} finally { // debugging
			traceOut("inclusive_or_expression");
		}
	}
	
	public final void exclusive_or_expression() throws RecognitionException, TokenStreamException {
		
		traceIn("exclusive_or_expression");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST exclusive_or_expression_AST = null;
			
			try {      // for error handling
				and_expression();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop94:
				do {
					if ((LA(1)==BIN_XOR)) {
						AST tmp106_AST = null;
						tmp106_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp106_AST);
						match(BIN_XOR);
						and_expression();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop94;
					}
					
				} while (true);
				}
				exclusive_or_expression_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_41);
			}
			returnAST = exclusive_or_expression_AST;
		} finally { // debugging
			traceOut("exclusive_or_expression");
		}
	}
	
	public final void and_expression() throws RecognitionException, TokenStreamException {
		
		traceIn("and_expression");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST and_expression_AST = null;
			
			try {      // for error handling
				equality_expression();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop97:
				do {
					if ((LA(1)==BIN_AND)) {
						AST tmp107_AST = null;
						tmp107_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp107_AST);
						match(BIN_AND);
						equality_expression();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop97;
					}
					
				} while (true);
				}
				and_expression_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_42);
			}
			returnAST = and_expression_AST;
		} finally { // debugging
			traceOut("and_expression");
		}
	}
	
	public final void equality_expression() throws RecognitionException, TokenStreamException {
		
		traceIn("equality_expression");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST equality_expression_AST = null;
			
			try {      // for error handling
				relational_expression();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop101:
				do {
					if ((LA(1)==EQ||LA(1)==NEQ)) {
						{
						switch ( LA(1)) {
						case EQ:
						{
							AST tmp108_AST = null;
							tmp108_AST = astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp108_AST);
							match(EQ);
							break;
						}
						case NEQ:
						{
							AST tmp109_AST = null;
							tmp109_AST = astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp109_AST);
							match(NEQ);
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
						relational_expression();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop101;
					}
					
				} while (true);
				}
				equality_expression_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_43);
			}
			returnAST = equality_expression_AST;
		} finally { // debugging
			traceOut("equality_expression");
		}
	}
	
	public final void relational_expression() throws RecognitionException, TokenStreamException {
		
		traceIn("relational_expression");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST relational_expression_AST = null;
			
			try {      // for error handling
				shift_expression();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop105:
				do {
					if (((LA(1) >= GEQ && LA(1) <= LT))) {
						{
						switch ( LA(1)) {
						case LT:
						{
							AST tmp110_AST = null;
							tmp110_AST = astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp110_AST);
							match(LT);
							break;
						}
						case GT:
						{
							AST tmp111_AST = null;
							tmp111_AST = astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp111_AST);
							match(GT);
							break;
						}
						case LEQ:
						{
							AST tmp112_AST = null;
							tmp112_AST = astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp112_AST);
							match(LEQ);
							break;
						}
						case GEQ:
						{
							AST tmp113_AST = null;
							tmp113_AST = astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp113_AST);
							match(GEQ);
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
						shift_expression();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop105;
					}
					
				} while (true);
				}
				relational_expression_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_44);
			}
			returnAST = relational_expression_AST;
		} finally { // debugging
			traceOut("relational_expression");
		}
	}
	
	public final void shift_expression() throws RecognitionException, TokenStreamException {
		
		traceIn("shift_expression");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST shift_expression_AST = null;
			
			try {      // for error handling
				additive_expression();
				astFactory.addASTChild(currentAST, returnAST);
				{
				_loop109:
				do {
					if ((LA(1)==SHL||LA(1)==SHR)) {
						{
						switch ( LA(1)) {
						case SHL:
						{
							AST tmp114_AST = null;
							tmp114_AST = astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp114_AST);
							match(SHL);
							break;
						}
						case SHR:
						{
							AST tmp115_AST = null;
							tmp115_AST = astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp115_AST);
							match(SHR);
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
						additive_expression();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						break _loop109;
					}
					
				} while (true);
				}
				shift_expression_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_45);
			}
			returnAST = shift_expression_AST;
		} finally { // debugging
			traceOut("shift_expression");
		}
	}
	
	public final void statement() throws RecognitionException, TokenStreamException {
		
		traceIn("statement");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST statement_AST = null;
			
			try {      // for error handling
				switch ( LA(1)) {
				case LBRACE:
				{
					compound_statement();
					astFactory.addASTChild(currentAST, returnAST);
					statement_AST = (AST)currentAST.root;
					break;
				}
				case TK_if:
				case TK_switch:
				{
					selection_statement();
					astFactory.addASTChild(currentAST, returnAST);
					statement_AST = (AST)currentAST.root;
					break;
				}
				case TK_while:
				case TK_do:
				case TK_for:
				{
					iteration_statement();
					astFactory.addASTChild(currentAST, returnAST);
					statement_AST = (AST)currentAST.root;
					break;
				}
				case TK_goto:
				case TK_continue:
				case TK_break:
				case TK_return:
				{
					jump_statement();
					astFactory.addASTChild(currentAST, returnAST);
					statement_AST = (AST)currentAST.root;
					break;
				}
				default:
					if ((LA(1)==TK_case||LA(1)==TK_default||LA(1)==IDENTIFIER) && (_tokenSet_46.member(LA(2))) && (_tokenSet_47.member(LA(3)))) {
						labeled_statement();
						astFactory.addASTChild(currentAST, returnAST);
						statement_AST = (AST)currentAST.root;
					}
					else if ((_tokenSet_48.member(LA(1))) && (_tokenSet_49.member(LA(2))) && (_tokenSet_50.member(LA(3)))) {
						expression_statement();
						astFactory.addASTChild(currentAST, returnAST);
						statement_AST = (AST)currentAST.root;
					}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_51);
			}
			returnAST = statement_AST;
		} finally { // debugging
			traceOut("statement");
		}
	}
	
	public final void labeled_statement() throws RecognitionException, TokenStreamException {
		
		traceIn("labeled_statement");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST labeled_statement_AST = null;
			
			try {      // for error handling
				switch ( LA(1)) {
				case IDENTIFIER:
				{
					AST tmp116_AST = null;
					tmp116_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp116_AST);
					match(IDENTIFIER);
					match(COLON);
					statement();
					astFactory.addASTChild(currentAST, returnAST);
					labeled_statement_AST = (AST)currentAST.root;
					break;
				}
				case TK_case:
				{
					AST tmp118_AST = null;
					tmp118_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp118_AST);
					match(TK_case);
					constant_expression();
					astFactory.addASTChild(currentAST, returnAST);
					match(COLON);
					statement();
					astFactory.addASTChild(currentAST, returnAST);
					labeled_statement_AST = (AST)currentAST.root;
					break;
				}
				case TK_default:
				{
					AST tmp120_AST = null;
					tmp120_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp120_AST);
					match(TK_default);
					match(COLON);
					statement();
					astFactory.addASTChild(currentAST, returnAST);
					labeled_statement_AST = (AST)currentAST.root;
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_51);
			}
			returnAST = labeled_statement_AST;
		} finally { // debugging
			traceOut("labeled_statement");
		}
	}
	
	public final void expression_statement() throws RecognitionException, TokenStreamException {
		
		traceIn("expression_statement");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST expression_statement_AST = null;
			
			try {      // for error handling
				switch ( LA(1)) {
				case SEMI:
				{
					match(SEMI);
					expression_statement_AST = (AST)currentAST.root;
					break;
				}
				case TK_sizeof:
				case IDENTIFIER:
				case CHARACTER_LITERAL:
				case STRING_LITERAL:
				case HEX_LITERAL:
				case DECIMAL_LITERAL:
				case OCTAL_LITERAL:
				case FLOATING_POINT_LITERAL:
				case LPAREN:
				case ASTERISK:
				case PLUS:
				case MINUS:
				case INC:
				case DEC:
				case BIN_AND:
				case TILDE:
				case BANG:
				{
					expression();
					astFactory.addASTChild(currentAST, returnAST);
					match(SEMI);
					expression_statement_AST = (AST)currentAST.root;
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_52);
			}
			returnAST = expression_statement_AST;
		} finally { // debugging
			traceOut("expression_statement");
		}
	}
	
	public final void selection_statement() throws RecognitionException, TokenStreamException {
		
		traceIn("selection_statement");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST selection_statement_AST = null;
			
			try {      // for error handling
				switch ( LA(1)) {
				case TK_if:
				{
					AST tmp124_AST = null;
					tmp124_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp124_AST);
					match(TK_if);
					match(LPAREN);
					expression();
					astFactory.addASTChild(currentAST, returnAST);
					match(RPAREN);
					statement();
					astFactory.addASTChild(currentAST, returnAST);
					{
					if ((LA(1)==TK_else) && (_tokenSet_53.member(LA(2))) && (_tokenSet_54.member(LA(3)))) {
						AST tmp127_AST = null;
						tmp127_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp127_AST);
						match(TK_else);
						statement();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else if ((_tokenSet_51.member(LA(1))) && (_tokenSet_55.member(LA(2))) && (_tokenSet_50.member(LA(3)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					selection_statement_AST = (AST)currentAST.root;
					break;
				}
				case TK_switch:
				{
					AST tmp128_AST = null;
					tmp128_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp128_AST);
					match(TK_switch);
					match(LPAREN);
					expression();
					astFactory.addASTChild(currentAST, returnAST);
					match(RPAREN);
					statement();
					astFactory.addASTChild(currentAST, returnAST);
					selection_statement_AST = (AST)currentAST.root;
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_51);
			}
			returnAST = selection_statement_AST;
		} finally { // debugging
			traceOut("selection_statement");
		}
	}
	
	public final void iteration_statement() throws RecognitionException, TokenStreamException {
		
		traceIn("iteration_statement");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST iteration_statement_AST = null;
			
			try {      // for error handling
				switch ( LA(1)) {
				case TK_while:
				{
					AST tmp131_AST = null;
					tmp131_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp131_AST);
					match(TK_while);
					match(LPAREN);
					expression();
					astFactory.addASTChild(currentAST, returnAST);
					match(RPAREN);
					statement();
					astFactory.addASTChild(currentAST, returnAST);
					iteration_statement_AST = (AST)currentAST.root;
					break;
				}
				case TK_do:
				{
					AST tmp134_AST = null;
					tmp134_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp134_AST);
					match(TK_do);
					statement();
					astFactory.addASTChild(currentAST, returnAST);
					AST tmp135_AST = null;
					tmp135_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp135_AST);
					match(TK_while);
					match(LPAREN);
					expression();
					astFactory.addASTChild(currentAST, returnAST);
					match(RPAREN);
					match(SEMI);
					iteration_statement_AST = (AST)currentAST.root;
					break;
				}
				case TK_for:
				{
					AST tmp139_AST = null;
					tmp139_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp139_AST);
					match(TK_for);
					match(LPAREN);
					expression_statement();
					astFactory.addASTChild(currentAST, returnAST);
					expression_statement();
					astFactory.addASTChild(currentAST, returnAST);
					{
					switch ( LA(1)) {
					case TK_sizeof:
					case IDENTIFIER:
					case CHARACTER_LITERAL:
					case STRING_LITERAL:
					case HEX_LITERAL:
					case DECIMAL_LITERAL:
					case OCTAL_LITERAL:
					case FLOATING_POINT_LITERAL:
					case LPAREN:
					case ASTERISK:
					case PLUS:
					case MINUS:
					case INC:
					case DEC:
					case BIN_AND:
					case TILDE:
					case BANG:
					{
						expression();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					case RPAREN:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(RPAREN);
					statement();
					astFactory.addASTChild(currentAST, returnAST);
					iteration_statement_AST = (AST)currentAST.root;
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_51);
			}
			returnAST = iteration_statement_AST;
		} finally { // debugging
			traceOut("iteration_statement");
		}
	}
	
	public final void jump_statement() throws RecognitionException, TokenStreamException {
		
		traceIn("jump_statement");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST jump_statement_AST = null;
			
			try {      // for error handling
				switch ( LA(1)) {
				case TK_goto:
				{
					AST tmp142_AST = null;
					tmp142_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp142_AST);
					match(TK_goto);
					AST tmp143_AST = null;
					tmp143_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp143_AST);
					match(IDENTIFIER);
					match(SEMI);
					jump_statement_AST = (AST)currentAST.root;
					break;
				}
				case TK_continue:
				{
					AST tmp145_AST = null;
					tmp145_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp145_AST);
					match(TK_continue);
					match(SEMI);
					jump_statement_AST = (AST)currentAST.root;
					break;
				}
				case TK_break:
				{
					AST tmp147_AST = null;
					tmp147_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp147_AST);
					match(TK_break);
					match(SEMI);
					jump_statement_AST = (AST)currentAST.root;
					break;
				}
				default:
					if ((LA(1)==TK_return) && (LA(2)==SEMI)) {
						AST tmp149_AST = null;
						tmp149_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp149_AST);
						match(TK_return);
						match(SEMI);
						jump_statement_AST = (AST)currentAST.root;
					}
					else if ((LA(1)==TK_return) && (_tokenSet_15.member(LA(2)))) {
						AST tmp151_AST = null;
						tmp151_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp151_AST);
						match(TK_return);
						assignment_expression();
						astFactory.addASTChild(currentAST, returnAST);
						match(SEMI);
						jump_statement_AST = (AST)currentAST.root;
					}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_51);
			}
			returnAST = jump_statement_AST;
		} finally { // debugging
			traceOut("jump_statement");
		}
	}
	
	public final void statement_list() throws RecognitionException, TokenStreamException {
		
		traceIn("statement_list");
		try { // debugging
			returnAST = null;
			ASTPair currentAST = new ASTPair();
			AST statement_list_AST = null;
			
			try {      // for error handling
				{
				int _cnt118=0;
				_loop118:
				do {
					if ((_tokenSet_53.member(LA(1)))) {
						statement();
						astFactory.addASTChild(currentAST, returnAST);
					}
					else {
						if ( _cnt118>=1 ) { break _loop118; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt118++;
				} while (true);
				}
				statement_list_AST = (AST)currentAST.root;
			}
			catch (RecognitionException ex) {
				reportError(ex);
				recover(ex,_tokenSet_56);
			}
			returnAST = statement_list_AST;
		} finally { // debugging
			traceOut("statement_list");
		}
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"\"extern\"",
		"\"static\"",
		"\"auto\"",
		"\"register\"",
		"\"goto\"",
		"\"continue\"",
		"\"break\"",
		"\"return\"",
		"\"while\"",
		"\"do\"",
		"\"for\"",
		"\"if\"",
		"\"switch\"",
		"\"case\"",
		"\"default\"",
		"\"sizeof\"",
		"\"const\"",
		"\"volatile\"",
		"\"enum\"",
		"\"struct\"",
		"\"union\"",
		"\"void\"",
		"\"char\"",
		"\"short\"",
		"\"int\"",
		"\"long\"",
		"\"float\"",
		"\"double\"",
		"\"signed\"",
		"\"unsigned\"",
		"IDENTIFIER",
		"LETTER",
		"CHARACTER_LITERAL",
		"STRING_LITERAL",
		"HEX_LITERAL",
		"DECIMAL_LITERAL",
		"OCTAL_LITERAL",
		"HexDigit",
		"IntegerTypeSuffix",
		"FLOATING_POINT_LITERAL",
		"Exponent",
		"FloatTypeSuffix",
		"EscapeSequence",
		"OctalEscape",
		"UnicodeEscape",
		"WS_",
		"SL_COMMENT",
		"ML_COMMENT",
		"LINE_COMMAND",
		"LBRACE",
		"RBRACE",
		"LPAREN",
		"RPAREN",
		"LBRACKET",
		"RBRACKET",
		"SEMI",
		"COLON",
		"COMMA",
		"PERIOD",
		"ASTERISK",
		"PLUS",
		"MINUS",
		"DIV",
		"MOD",
		"INC",
		"DEC",
		"LOG_AND",
		"LOG_OR",
		"ARROW",
		"BIN_AND",
		"BIN_OR",
		"BIN_XOR",
		"TILDE",
		"BANG",
		"EQ",
		"NEQ",
		"GEQ",
		"LEQ",
		"GT",
		"LT",
		"SHL",
		"SHR",
		"Q",
		"ASSIGN",
		"MUL_ASSIGN",
		"DIV_ASSIGN",
		"ADD_ASSIGN",
		"SUB_ASSIGN",
		"MOD_ASSIGN",
		"SHR_ASSIGN",
		"SHL_ASSIGN",
		"AND_ASSIGN",
		"OR_ASSIGN",
		"XOR_ASSIGN",
		"BLOCK",
		"PROGRAM",
		"PARAM_LIST",
		"PARAM",
		"TYPE",
		"DECLARATION",
		"FN",
		"\"...\"",
		"TK_else"
	};
	
	protected void buildTokenTypeASTClassMap() {
		tokenTypeToASTClassMap=null;
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 36028814503969008L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 36028814503969010L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 3134505357829734400L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 3143512557084475392L, 8388608L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 305135856L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { -8583849945885310990L, 4398046523955L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { -8583849945885310992L, 12851L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 576460752303423488L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { 3134505358134870256L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = { 2882303761517117440L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	private static final long[] mk_tokenSet_11() {
		long[] data = { 2900318160026599424L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
	private static final long[] mk_tokenSet_12() {
		long[] data = { 252201579132747776L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_12 = new BitSet(mk_tokenSet_12());
	private static final long[] mk_tokenSet_13() {
		long[] data = { -8827044325763841808L, 12851L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_13 = new BitSet(mk_tokenSet_13());
	private static final long[] mk_tokenSet_14() {
		long[] data = { -1179932158488739600L, 16777215L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_14 = new BitSet(mk_tokenSet_14());
	private static final long[] mk_tokenSet_15() {
		long[] data = { -9187332296258617344L, 12851L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_15 = new BitSet(mk_tokenSet_15());
	private static final long[] mk_tokenSet_16() {
		long[] data = { 1441151880758558720L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_16 = new BitSet(mk_tokenSet_16());
	private static final long[] mk_tokenSet_17() {
		long[] data = { 72057594037927936L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_17 = new BitSet(mk_tokenSet_17());
	private static final long[] mk_tokenSet_18() {
		long[] data = { 2377900603251621888L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_18 = new BitSet(mk_tokenSet_18());
	private static final long[] mk_tokenSet_19() {
		long[] data = { 2558044605526310912L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_19 = new BitSet(mk_tokenSet_19());
	private static final long[] mk_tokenSet_20() {
		long[] data = { -5791618176916127504L, 2199031657011L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_20 = new BitSet(mk_tokenSet_20());
	private static final long[] mk_tokenSet_21() {
		long[] data = { -8682929137687985936L, 12851L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_21 = new BitSet(mk_tokenSet_21());
	private static final long[] mk_tokenSet_22() {
		long[] data = { -1765400110046904080L, 8388607L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_22 = new BitSet(mk_tokenSet_22());
	private static final long[] mk_tokenSet_23() {
		long[] data = { -18003454627151872L, 8388607L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_23 = new BitSet(mk_tokenSet_23());
	private static final long[] mk_tokenSet_24() {
		long[] data = { -8996255371886608L, 4415226380287L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_24 = new BitSet(mk_tokenSet_24());
	private static final long[] mk_tokenSet_25() {
		long[] data = { -4431531089755373568L, 17171493683L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_25 = new BitSet(mk_tokenSet_25());
	private static final long[] mk_tokenSet_26() {
		long[] data = { -2053630486198616064L, 17179869183L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_26 = new BitSet(mk_tokenSet_26());
	private static final long[] mk_tokenSet_27() {
		long[] data = { 4413527634823086080L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_27 = new BitSet(mk_tokenSet_27());
	private static final long[] mk_tokenSet_28() {
		long[] data = { -9178325097003876352L, 12851L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_28 = new BitSet(mk_tokenSet_28());
	private static final long[] mk_tokenSet_29() {
		long[] data = { 2323857407723175936L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_29 = new BitSet(mk_tokenSet_29());
	private static final long[] mk_tokenSet_30() {
		long[] data = { 4413527634823086080L, 8376000L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_30 = new BitSet(mk_tokenSet_30());
	private static final long[] mk_tokenSet_31() {
		long[] data = { 4413527634823086080L, 8376003L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_31 = new BitSet(mk_tokenSet_31());
	private static final long[] mk_tokenSet_32() {
		long[] data = { 305135616L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_32 = new BitSet(mk_tokenSet_32());
	private static final long[] mk_tokenSet_33() {
		long[] data = { -18003454932287488L, 17179869183L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_33 = new BitSet(mk_tokenSet_33());
	private static final long[] mk_tokenSet_34() {
		long[] data = { -4809844402031689728L, 17179856591L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_34 = new BitSet(mk_tokenSet_34());
	private static final long[] mk_tokenSet_35() {
		long[] data = { -18014398509481984L, 17179856895L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_35 = new BitSet(mk_tokenSet_35());
	private static final long[] mk_tokenSet_36() {
		long[] data = { 2089670227099910144L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_36 = new BitSet(mk_tokenSet_36());
	private static final long[] mk_tokenSet_37() {
		long[] data = { 0L, 17171480576L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_37 = new BitSet(mk_tokenSet_37());
	private static final long[] mk_tokenSet_38() {
		long[] data = { 4413527634823086080L, 4194304L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_38 = new BitSet(mk_tokenSet_38());
	private static final long[] mk_tokenSet_39() {
		long[] data = { 4413527634823086080L, 4194432L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_39 = new BitSet(mk_tokenSet_39());
	private static final long[] mk_tokenSet_40() {
		long[] data = { 4413527634823086080L, 4194496L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_40 = new BitSet(mk_tokenSet_40());
	private static final long[] mk_tokenSet_41() {
		long[] data = { 4413527634823086080L, 4195520L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_41 = new BitSet(mk_tokenSet_41());
	private static final long[] mk_tokenSet_42() {
		long[] data = { 4413527634823086080L, 4197568L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_42 = new BitSet(mk_tokenSet_42());
	private static final long[] mk_tokenSet_43() {
		long[] data = { 4413527634823086080L, 4198080L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_43 = new BitSet(mk_tokenSet_43());
	private static final long[] mk_tokenSet_44() {
		long[] data = { 4413527634823086080L, 4247232L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_44 = new BitSet(mk_tokenSet_44());
	private static final long[] mk_tokenSet_45() {
		long[] data = { 4413527634823086080L, 5230272L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_45 = new BitSet(mk_tokenSet_45());
	private static final long[] mk_tokenSet_46() {
		long[] data = { -8034410791651770368L, 12851L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_46 = new BitSet(mk_tokenSet_46());
	private static final long[] mk_tokenSet_47() {
		long[] data = { -2693141633284702464L, 8388607L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_47 = new BitSet(mk_tokenSet_47());
	private static final long[] mk_tokenSet_48() {
		long[] data = { -8610871543955193856L, 12851L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_48 = new BitSet(mk_tokenSet_48());
	private static final long[] mk_tokenSet_49() {
		long[] data = { -1522205730168373504L, 4415226380287L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_49 = new BitSet(mk_tokenSet_49());
	private static final long[] mk_tokenSet_50() {
		long[] data = { -297226631523598350L, 4415226380287L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_50 = new BitSet(mk_tokenSet_50());
	private static final long[] mk_tokenSet_51() {
		long[] data = { -8583849946190446848L, 4398046523955L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_51 = new BitSet(mk_tokenSet_51());
	private static final long[] mk_tokenSet_52() {
		long[] data = { -8511792352152518912L, 4398046523955L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_52 = new BitSet(mk_tokenSet_52());
	private static final long[] mk_tokenSet_53() {
		long[] data = { -8601864344699928832L, 12851L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_53 = new BitSet(mk_tokenSet_53());
	private static final long[] mk_tokenSet_54() {
		long[] data = { -369284225561526288L, 4415226380287L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_54 = new BitSet(mk_tokenSet_54());
	private static final long[] mk_tokenSet_55() {
		long[] data = { -369284225561526286L, 4415226380287L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_55 = new BitSet(mk_tokenSet_55());
	private static final long[] mk_tokenSet_56() {
		long[] data = { 18014398509481984L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_56 = new BitSet(mk_tokenSet_56());
	
	}
