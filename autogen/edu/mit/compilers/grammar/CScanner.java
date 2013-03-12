// $ANTLR 2.7.7 (2006-11-01): "scanner.g" -> "CScanner.java"$

package edu.mit.compilers.grammar;

import java.io.InputStream;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.TokenStreamRecognitionException;
import antlr.CharStreamException;
import antlr.CharStreamIOException;
import antlr.ANTLRException;
import java.io.Reader;
import java.util.Hashtable;
import antlr.CharScanner;
import antlr.InputBuffer;
import antlr.ByteBuffer;
import antlr.CharBuffer;
import antlr.Token;
import antlr.CommonToken;
import antlr.RecognitionException;
import antlr.NoViableAltForCharException;
import antlr.MismatchedCharException;
import antlr.TokenStream;
import antlr.ANTLRHashString;
import antlr.LexerSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.SemanticException;
@SuppressWarnings("unchecked")
public class CScanner extends antlr.CharScanner implements CScannerTokenTypes, TokenStream
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
public CScanner(InputStream in) {
	this(new ByteBuffer(in));
}
public CScanner(Reader in) {
	this(new CharBuffer(in));
}
public CScanner(InputBuffer ib) {
	this(new LexerSharedInputState(ib));
}
public CScanner(LexerSharedInputState state) {
	super(state);
	caseSensitiveLiterals = true;
	setCaseSensitive(true);
	literals = new Hashtable();
	literals.put(new ANTLRHashString("switch", this), new Integer(16));
	literals.put(new ANTLRHashString("case", this), new Integer(17));
	literals.put(new ANTLRHashString("for", this), new Integer(14));
	literals.put(new ANTLRHashString("register", this), new Integer(7));
	literals.put(new ANTLRHashString("sizeof", this), new Integer(19));
	literals.put(new ANTLRHashString("auto", this), new Integer(6));
	literals.put(new ANTLRHashString("void", this), new Integer(25));
	literals.put(new ANTLRHashString("float", this), new Integer(30));
	literals.put(new ANTLRHashString("continue", this), new Integer(9));
	literals.put(new ANTLRHashString("long", this), new Integer(29));
	literals.put(new ANTLRHashString("do", this), new Integer(13));
	literals.put(new ANTLRHashString("short", this), new Integer(27));
	literals.put(new ANTLRHashString("signed", this), new Integer(32));
	literals.put(new ANTLRHashString("enum", this), new Integer(22));
	literals.put(new ANTLRHashString("static", this), new Integer(5));
	literals.put(new ANTLRHashString("char", this), new Integer(26));
	literals.put(new ANTLRHashString("union", this), new Integer(24));
	literals.put(new ANTLRHashString("while", this), new Integer(12));
	literals.put(new ANTLRHashString("const", this), new Integer(20));
	literals.put(new ANTLRHashString("break", this), new Integer(10));
	literals.put(new ANTLRHashString("extern", this), new Integer(4));
	literals.put(new ANTLRHashString("return", this), new Integer(11));
	literals.put(new ANTLRHashString("if", this), new Integer(15));
	literals.put(new ANTLRHashString("int", this), new Integer(28));
	literals.put(new ANTLRHashString("double", this), new Integer(31));
	literals.put(new ANTLRHashString("volatile", this), new Integer(21));
	literals.put(new ANTLRHashString("default", this), new Integer(18));
	literals.put(new ANTLRHashString("unsigned", this), new Integer(33));
	literals.put(new ANTLRHashString("struct", this), new Integer(23));
	literals.put(new ANTLRHashString("goto", this), new Integer(8));
}

public Token nextToken() throws TokenStreamException {
	Token theRetToken=null;
tryAgain:
	for (;;) {
		Token _token = null;
		int _ttype = Token.INVALID_TYPE;
		resetText();
		try {   // for char stream error handling
			try {   // for lexical error handling
				switch ( LA(1)) {
				case '$':  case 'A':  case 'B':  case 'C':
				case 'D':  case 'E':  case 'F':  case 'G':
				case 'H':  case 'I':  case 'J':  case 'K':
				case 'L':  case 'M':  case 'N':  case 'O':
				case 'P':  case 'Q':  case 'R':  case 'S':
				case 'T':  case 'U':  case 'V':  case 'W':
				case 'X':  case 'Y':  case 'Z':  case '_':
				case 'a':  case 'b':  case 'c':  case 'd':
				case 'e':  case 'f':  case 'g':  case 'h':
				case 'i':  case 'j':  case 'k':  case 'l':
				case 'm':  case 'n':  case 'o':  case 'p':
				case 'q':  case 'r':  case 's':  case 't':
				case 'u':  case 'v':  case 'w':  case 'x':
				case 'y':  case 'z':
				{
					mIDENTIFIER(true);
					theRetToken=_returnToken;
					break;
				}
				case '\'':
				{
					mCHARACTER_LITERAL(true);
					theRetToken=_returnToken;
					break;
				}
				case '"':
				{
					mSTRING_LITERAL(true);
					theRetToken=_returnToken;
					break;
				}
				case '\t':  case '\n':  case '\r':  case ' ':
				{
					mWS_(true);
					theRetToken=_returnToken;
					break;
				}
				case '#':
				{
					mLINE_COMMAND(true);
					theRetToken=_returnToken;
					break;
				}
				case '{':
				{
					mLBRACE(true);
					theRetToken=_returnToken;
					break;
				}
				case '}':
				{
					mRBRACE(true);
					theRetToken=_returnToken;
					break;
				}
				case '(':
				{
					mLPAREN(true);
					theRetToken=_returnToken;
					break;
				}
				case ')':
				{
					mRPAREN(true);
					theRetToken=_returnToken;
					break;
				}
				case '[':
				{
					mLBRACKET(true);
					theRetToken=_returnToken;
					break;
				}
				case ']':
				{
					mRBRACKET(true);
					theRetToken=_returnToken;
					break;
				}
				case ';':
				{
					mSEMI(true);
					theRetToken=_returnToken;
					break;
				}
				case ':':
				{
					mCOLON(true);
					theRetToken=_returnToken;
					break;
				}
				case ',':
				{
					mCOMMA(true);
					theRetToken=_returnToken;
					break;
				}
				case '~':
				{
					mTILDE(true);
					theRetToken=_returnToken;
					break;
				}
				case '?':
				{
					mQ(true);
					theRetToken=_returnToken;
					break;
				}
				default:
					if ((LA(1)=='0') && (LA(2)=='X'||LA(2)=='x')) {
						mHEX_LITERAL(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='0') && ((LA(2) >= '0' && LA(2) <= '7'))) {
						mOCTAL_LITERAL(true);
						theRetToken=_returnToken;
					}
					else if ((_tokenSet_0.member(LA(1))) && (_tokenSet_1.member(LA(2)))) {
						mFLOATING_POINT_LITERAL(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='/') && (LA(2)=='/')) {
						mSL_COMMENT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='/') && (LA(2)=='*')) {
						mML_COMMENT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='+') && (LA(2)=='+')) {
						mINC(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='-') && (LA(2)=='-')) {
						mDEC(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='&') && (LA(2)=='&')) {
						mLOG_AND(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='|') && (LA(2)=='|')) {
						mLOG_OR(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='-') && (LA(2)=='>')) {
						mARROW(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='=') && (LA(2)=='=')) {
						mEQ(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='!') && (LA(2)=='=')) {
						mNEQ(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='>') && (LA(2)=='=')) {
						mGEQ(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='<') && (LA(2)=='=')) {
						mLEQ(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='<') && (LA(2)=='<')) {
						mSHL(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='>') && (LA(2)=='>')) {
						mSHR(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='*') && (LA(2)=='=')) {
						mMUL_ASSIGN(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='/') && (LA(2)=='=')) {
						mDIV_ASSIGN(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='+') && (LA(2)=='=')) {
						mADD_ASSIGN(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='-') && (LA(2)=='=')) {
						mSUB_ASSIGN(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='%') && (LA(2)=='=')) {
						mMOD_ASSIGN(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='>') && (LA(2)=='>')) {
						mSHR_ASSIGN(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='<') && (LA(2)=='<')) {
						mSHL_ASSIGN(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='&') && (LA(2)=='=')) {
						mAND_ASSIGN(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='|') && (LA(2)=='=')) {
						mOR_ASSIGN(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='^') && (LA(2)=='=')) {
						mXOR_ASSIGN(true);
						theRetToken=_returnToken;
					}
					else if (((LA(1) >= '0' && LA(1) <= '9')) && (true)) {
						mDECIMAL_LITERAL(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='.') && (true)) {
						mPERIOD(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='*') && (true)) {
						mASTERISK(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='+') && (true)) {
						mPLUS(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='-') && (true)) {
						mMINUS(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='/') && (true)) {
						mDIV(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='%') && (true)) {
						mMOD(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='&') && (true)) {
						mBIN_AND(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='|') && (true)) {
						mBIN_OR(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='^') && (true)) {
						mBIN_XOR(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='!') && (true)) {
						mBANG(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='>') && (true)) {
						mGT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='<') && (true)) {
						mLT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='=') && (true)) {
						mASSIGN(true);
						theRetToken=_returnToken;
					}
				else {
					if (LA(1)==EOF_CHAR) {uponEOF(); _returnToken = makeToken(Token.EOF_TYPE);}
				else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
				}
				}
				if ( _returnToken==null ) continue tryAgain; // found SKIP token
				_ttype = _returnToken.getType();
				_ttype = testLiteralsTable(_ttype);
				_returnToken.setType(_ttype);
				return _returnToken;
			}
			catch (RecognitionException e) {
				throw new TokenStreamRecognitionException(e);
			}
		}
		catch (CharStreamException cse) {
			if ( cse instanceof CharStreamIOException ) {
				throw new TokenStreamIOException(((CharStreamIOException)cse).io);
			}
			else {
				throw new TokenStreamException(cse.getMessage());
			}
		}
	}
}

	public final void mIDENTIFIER(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mIDENTIFIER");
		_ttype = IDENTIFIER;
		int _saveIndex;
		try { // debugging
			
			mLETTER(false);
			{
			_loop3:
			do {
				switch ( LA(1)) {
				case '$':  case 'A':  case 'B':  case 'C':
				case 'D':  case 'E':  case 'F':  case 'G':
				case 'H':  case 'I':  case 'J':  case 'K':
				case 'L':  case 'M':  case 'N':  case 'O':
				case 'P':  case 'Q':  case 'R':  case 'S':
				case 'T':  case 'U':  case 'V':  case 'W':
				case 'X':  case 'Y':  case 'Z':  case '_':
				case 'a':  case 'b':  case 'c':  case 'd':
				case 'e':  case 'f':  case 'g':  case 'h':
				case 'i':  case 'j':  case 'k':  case 'l':
				case 'm':  case 'n':  case 'o':  case 'p':
				case 'q':  case 'r':  case 's':  case 't':
				case 'u':  case 'v':  case 'w':  case 'x':
				case 'y':  case 'z':
				{
					mLETTER(false);
					break;
				}
				case '0':  case '1':  case '2':  case '3':
				case '4':  case '5':  case '6':  case '7':
				case '8':  case '9':
				{
					matchRange('0','9');
					break;
				}
				default:
				{
					break _loop3;
				}
				}
			} while (true);
			}
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mIDENTIFIER");
		}
	}
	
	protected final void mLETTER(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mLETTER");
		_ttype = LETTER;
		int _saveIndex;
		try { // debugging
			
			{
			switch ( LA(1)) {
			case '$':
			{
				match('$');
				break;
			}
			case 'A':  case 'B':  case 'C':  case 'D':
			case 'E':  case 'F':  case 'G':  case 'H':
			case 'I':  case 'J':  case 'K':  case 'L':
			case 'M':  case 'N':  case 'O':  case 'P':
			case 'Q':  case 'R':  case 'S':  case 'T':
			case 'U':  case 'V':  case 'W':  case 'X':
			case 'Y':  case 'Z':
			{
				matchRange('A','Z');
				break;
			}
			case 'a':  case 'b':  case 'c':  case 'd':
			case 'e':  case 'f':  case 'g':  case 'h':
			case 'i':  case 'j':  case 'k':  case 'l':
			case 'm':  case 'n':  case 'o':  case 'p':
			case 'q':  case 'r':  case 's':  case 't':
			case 'u':  case 'v':  case 'w':  case 'x':
			case 'y':  case 'z':
			{
				matchRange('a','z');
				break;
			}
			case '_':
			{
				match('_');
				break;
			}
			default:
			{
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			}
			}
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mLETTER");
		}
	}
	
	public final void mCHARACTER_LITERAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mCHARACTER_LITERAL");
		_ttype = CHARACTER_LITERAL;
		int _saveIndex;
		try { // debugging
			
			match('\'');
			{
			switch ( LA(1)) {
			case '\\':
			{
				mEscapeSequence(false);
				break;
			}
			case '\u0000':  case '\u0001':  case '\u0002':  case '\u0003':
			case '\u0004':  case '\u0005':  case '\u0006':  case '\u0007':
			case '\u0008':  case '\t':  case '\n':  case '\u000b':
			case '\u000c':  case '\r':  case '\u000e':  case '\u000f':
			case '\u0010':  case '\u0011':  case '\u0012':  case '\u0013':
			case '\u0014':  case '\u0015':  case '\u0016':  case '\u0017':
			case '\u0018':  case '\u0019':  case '\u001a':  case '\u001b':
			case '\u001c':  case '\u001d':  case '\u001e':  case '\u001f':
			case ' ':  case '!':  case '"':  case '#':
			case '$':  case '%':  case '&':  case '(':
			case ')':  case '*':  case '+':  case ',':
			case '-':  case '.':  case '/':  case '0':
			case '1':  case '2':  case '3':  case '4':
			case '5':  case '6':  case '7':  case '8':
			case '9':  case ':':  case ';':  case '<':
			case '=':  case '>':  case '?':  case '@':
			case 'A':  case 'B':  case 'C':  case 'D':
			case 'E':  case 'F':  case 'G':  case 'H':
			case 'I':  case 'J':  case 'K':  case 'L':
			case 'M':  case 'N':  case 'O':  case 'P':
			case 'Q':  case 'R':  case 'S':  case 'T':
			case 'U':  case 'V':  case 'W':  case 'X':
			case 'Y':  case 'Z':  case '[':  case ']':
			case '^':  case '_':  case '`':  case 'a':
			case 'b':  case 'c':  case 'd':  case 'e':
			case 'f':  case 'g':  case 'h':  case 'i':
			case 'j':  case 'k':  case 'l':  case 'm':
			case 'n':  case 'o':  case 'p':  case 'q':
			case 'r':  case 's':  case 't':  case 'u':
			case 'v':  case 'w':  case 'x':  case 'y':
			case 'z':  case '{':  case '|':  case '}':
			case '~':  case '\u007f':
			{
				{
				match(_tokenSet_2);
				}
				break;
			}
			default:
			{
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			}
			}
			match('\'');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mCHARACTER_LITERAL");
		}
	}
	
	protected final void mEscapeSequence(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mEscapeSequence");
		_ttype = EscapeSequence;
		int _saveIndex;
		try { // debugging
			
			if ((LA(1)=='\\') && (_tokenSet_3.member(LA(2)))) {
				match('\\');
				{
				switch ( LA(1)) {
				case 'b':
				{
					match('b');
					break;
				}
				case 't':
				{
					match('t');
					break;
				}
				case 'n':
				{
					match('n');
					break;
				}
				case 'f':
				{
					match('f');
					break;
				}
				case 'r':
				{
					match('r');
					break;
				}
				case '"':
				{
					match('\"');
					break;
				}
				case '\'':
				{
					match('\'');
					break;
				}
				case '\\':
				{
					match('\\');
					break;
				}
				default:
				{
					throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
				}
				}
				}
			}
			else if ((LA(1)=='\\') && ((LA(2) >= '0' && LA(2) <= '7'))) {
				mOctalEscape(false);
			}
			else {
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mEscapeSequence");
		}
	}
	
	public final void mSTRING_LITERAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mSTRING_LITERAL");
		_ttype = STRING_LITERAL;
		int _saveIndex;
		try { // debugging
			
			match('"');
			{
			_loop12:
			do {
				switch ( LA(1)) {
				case '\\':
				{
					mEscapeSequence(false);
					break;
				}
				case '\u0000':  case '\u0001':  case '\u0002':  case '\u0003':
				case '\u0004':  case '\u0005':  case '\u0006':  case '\u0007':
				case '\u0008':  case '\t':  case '\n':  case '\u000b':
				case '\u000c':  case '\r':  case '\u000e':  case '\u000f':
				case '\u0010':  case '\u0011':  case '\u0012':  case '\u0013':
				case '\u0014':  case '\u0015':  case '\u0016':  case '\u0017':
				case '\u0018':  case '\u0019':  case '\u001a':  case '\u001b':
				case '\u001c':  case '\u001d':  case '\u001e':  case '\u001f':
				case ' ':  case '!':  case '#':  case '$':
				case '%':  case '&':  case '\'':  case '(':
				case ')':  case '*':  case '+':  case ',':
				case '-':  case '.':  case '/':  case '0':
				case '1':  case '2':  case '3':  case '4':
				case '5':  case '6':  case '7':  case '8':
				case '9':  case ':':  case ';':  case '<':
				case '=':  case '>':  case '?':  case '@':
				case 'A':  case 'B':  case 'C':  case 'D':
				case 'E':  case 'F':  case 'G':  case 'H':
				case 'I':  case 'J':  case 'K':  case 'L':
				case 'M':  case 'N':  case 'O':  case 'P':
				case 'Q':  case 'R':  case 'S':  case 'T':
				case 'U':  case 'V':  case 'W':  case 'X':
				case 'Y':  case 'Z':  case '[':  case ']':
				case '^':  case '_':  case '`':  case 'a':
				case 'b':  case 'c':  case 'd':  case 'e':
				case 'f':  case 'g':  case 'h':  case 'i':
				case 'j':  case 'k':  case 'l':  case 'm':
				case 'n':  case 'o':  case 'p':  case 'q':
				case 'r':  case 's':  case 't':  case 'u':
				case 'v':  case 'w':  case 'x':  case 'y':
				case 'z':  case '{':  case '|':  case '}':
				case '~':  case '\u007f':
				{
					{
					match(_tokenSet_4);
					}
					break;
				}
				default:
				{
					break _loop12;
				}
				}
			} while (true);
			}
			match('"');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mSTRING_LITERAL");
		}
	}
	
	public final void mHEX_LITERAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mHEX_LITERAL");
		_ttype = HEX_LITERAL;
		int _saveIndex;
		try { // debugging
			
			match('0');
			{
			switch ( LA(1)) {
			case 'x':
			{
				match('x');
				break;
			}
			case 'X':
			{
				match('X');
				break;
			}
			default:
			{
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			}
			}
			{
			int _cnt16=0;
			_loop16:
			do {
				if ((_tokenSet_5.member(LA(1)))) {
					mHexDigit(false);
				}
				else {
					if ( _cnt16>=1 ) { break _loop16; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
				}
				
				_cnt16++;
			} while (true);
			}
			{
			if ((_tokenSet_6.member(LA(1)))) {
				mIntegerTypeSuffix(false);
			}
			else {
			}
			
			}
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mHEX_LITERAL");
		}
	}
	
	protected final void mHexDigit(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mHexDigit");
		_ttype = HexDigit;
		int _saveIndex;
		try { // debugging
			
			switch ( LA(1)) {
			case '0':  case '1':  case '2':  case '3':
			case '4':  case '5':  case '6':  case '7':
			case '8':  case '9':
			{
				matchRange('0','9');
				break;
			}
			case 'a':  case 'b':  case 'c':  case 'd':
			case 'e':  case 'f':
			{
				matchRange('a','f');
				break;
			}
			case 'A':  case 'B':  case 'C':  case 'D':
			case 'E':  case 'F':
			{
				matchRange('A','F');
				break;
			}
			default:
			{
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			}
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mHexDigit");
		}
	}
	
	protected final void mIntegerTypeSuffix(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mIntegerTypeSuffix");
		_ttype = IntegerTypeSuffix;
		int _saveIndex;
		try { // debugging
			
			if ((_tokenSet_6.member(LA(1))) && (true)) {
				{
				switch ( LA(1)) {
				case 'u':
				{
					match('u');
					break;
				}
				case 'U':
				{
					match('U');
					break;
				}
				case 'L':  case 'l':
				{
					break;
				}
				default:
				{
					throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
				}
				}
				}
				{
				switch ( LA(1)) {
				case 'l':
				{
					match('l');
					break;
				}
				case 'L':
				{
					match('L');
					break;
				}
				default:
				{
					throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
				}
				}
				}
			}
			else if ((LA(1)=='U'||LA(1)=='u') && (true)) {
				{
				switch ( LA(1)) {
				case 'u':
				{
					match('u');
					break;
				}
				case 'U':
				{
					match('U');
					break;
				}
				default:
				{
					throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
				}
				}
				}
				{
				switch ( LA(1)) {
				case 'l':
				{
					match('l');
					break;
				}
				case 'L':
				{
					match('L');
					break;
				}
				default:
					{
					}
				}
				}
			}
			else {
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mIntegerTypeSuffix");
		}
	}
	
	public final void mDECIMAL_LITERAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mDECIMAL_LITERAL");
		_ttype = DECIMAL_LITERAL;
		int _saveIndex;
		try { // debugging
			
			{
			switch ( LA(1)) {
			case '0':
			{
				match('0');
				break;
			}
			case '1':  case '2':  case '3':  case '4':
			case '5':  case '6':  case '7':  case '8':
			case '9':
			{
				matchRange('1','9');
				{
				_loop21:
				do {
					if (((LA(1) >= '0' && LA(1) <= '9'))) {
						matchRange('0','9');
					}
					else {
						break _loop21;
					}
					
				} while (true);
				}
				break;
			}
			default:
			{
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			}
			}
			{
			if ((_tokenSet_6.member(LA(1)))) {
				mIntegerTypeSuffix(false);
			}
			else {
			}
			
			}
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mDECIMAL_LITERAL");
		}
	}
	
	public final void mOCTAL_LITERAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mOCTAL_LITERAL");
		_ttype = OCTAL_LITERAL;
		int _saveIndex;
		try { // debugging
			
			match('0');
			{
			int _cnt25=0;
			_loop25:
			do {
				if (((LA(1) >= '0' && LA(1) <= '7'))) {
					matchRange('0','7');
				}
				else {
					if ( _cnt25>=1 ) { break _loop25; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
				}
				
				_cnt25++;
			} while (true);
			}
			{
			if ((_tokenSet_6.member(LA(1)))) {
				mIntegerTypeSuffix(false);
			}
			else {
			}
			
			}
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mOCTAL_LITERAL");
		}
	}
	
	public final void mFLOATING_POINT_LITERAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mFLOATING_POINT_LITERAL");
		_ttype = FLOATING_POINT_LITERAL;
		int _saveIndex;
		try { // debugging
			
			if (((LA(1) >= '0' && LA(1) <= '9')) && (_tokenSet_0.member(LA(2)))) {
				{
				int _cnt35=0;
				_loop35:
				do {
					if (((LA(1) >= '0' && LA(1) <= '9'))) {
						matchRange('0','9');
					}
					else {
						if ( _cnt35>=1 ) { break _loop35; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
					}
					
					_cnt35++;
				} while (true);
				}
				match('.');
				{
				_loop37:
				do {
					if (((LA(1) >= '0' && LA(1) <= '9'))) {
						matchRange('0','9');
					}
					else {
						break _loop37;
					}
					
				} while (true);
				}
				{
				if ((LA(1)=='E'||LA(1)=='e')) {
					mExponent(false);
				}
				else {
				}
				
				}
				{
				if ((_tokenSet_7.member(LA(1)))) {
					mFloatTypeSuffix(false);
				}
				else {
				}
				
				}
			}
			else if (((LA(1) >= '0' && LA(1) <= '9')) && (_tokenSet_8.member(LA(2)))) {
				{
				int _cnt45=0;
				_loop45:
				do {
					if (((LA(1) >= '0' && LA(1) <= '9'))) {
						matchRange('0','9');
					}
					else {
						if ( _cnt45>=1 ) { break _loop45; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
					}
					
					_cnt45++;
				} while (true);
				}
				{
				mExponent(false);
				}
				{
				if ((_tokenSet_7.member(LA(1)))) {
					mFloatTypeSuffix(false);
				}
				else {
				}
				
				}
			}
			else if (((LA(1) >= '0' && LA(1) <= '9')) && (_tokenSet_9.member(LA(2)))) {
				{
				int _cnt49=0;
				_loop49:
				do {
					if (((LA(1) >= '0' && LA(1) <= '9'))) {
						matchRange('0','9');
					}
					else {
						if ( _cnt49>=1 ) { break _loop49; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
					}
					
					_cnt49++;
				} while (true);
				}
				{
				switch ( LA(1)) {
				case 'E':  case 'e':
				{
					mExponent(false);
					break;
				}
				case 'D':  case 'F':  case 'd':  case 'f':
				{
					break;
				}
				default:
				{
					throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
				}
				}
				}
				mFloatTypeSuffix(false);
			}
			else if ((LA(1)=='.')) {
				match('.');
				{
				int _cnt41=0;
				_loop41:
				do {
					if (((LA(1) >= '0' && LA(1) <= '9'))) {
						matchRange('0','9');
					}
					else {
						if ( _cnt41>=1 ) { break _loop41; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
					}
					
					_cnt41++;
				} while (true);
				}
				{
				if ((LA(1)=='E'||LA(1)=='e')) {
					mExponent(false);
				}
				else {
				}
				
				}
				{
				if ((_tokenSet_7.member(LA(1)))) {
					mFloatTypeSuffix(false);
				}
				else {
				}
				
				}
			}
			else {
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mFLOATING_POINT_LITERAL");
		}
	}
	
	protected final void mExponent(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mExponent");
		_ttype = Exponent;
		int _saveIndex;
		try { // debugging
			
			{
			switch ( LA(1)) {
			case 'e':
			{
				match('e');
				break;
			}
			case 'E':
			{
				match('E');
				break;
			}
			default:
			{
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			}
			}
			{
			switch ( LA(1)) {
			case '+':
			{
				match('+');
				break;
			}
			case '-':
			{
				match('-');
				break;
			}
			case '0':  case '1':  case '2':  case '3':
			case '4':  case '5':  case '6':  case '7':
			case '8':  case '9':
			{
				break;
			}
			default:
			{
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			}
			}
			{
			int _cnt55=0;
			_loop55:
			do {
				if (((LA(1) >= '0' && LA(1) <= '9'))) {
					matchRange('0','9');
				}
				else {
					if ( _cnt55>=1 ) { break _loop55; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
				}
				
				_cnt55++;
			} while (true);
			}
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mExponent");
		}
	}
	
	protected final void mFloatTypeSuffix(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mFloatTypeSuffix");
		_ttype = FloatTypeSuffix;
		int _saveIndex;
		try { // debugging
			
			{
			switch ( LA(1)) {
			case 'f':
			{
				match('f');
				break;
			}
			case 'F':
			{
				match('F');
				break;
			}
			case 'd':
			{
				match('d');
				break;
			}
			case 'D':
			{
				match('D');
				break;
			}
			default:
			{
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			}
			}
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mFloatTypeSuffix");
		}
	}
	
	protected final void mOctalEscape(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mOctalEscape");
		_ttype = OctalEscape;
		int _saveIndex;
		try { // debugging
			
			if ((LA(1)=='\\') && ((LA(2) >= '0' && LA(2) <= '3'))) {
				match('\\');
				{
				matchRange('0','3');
				}
				{
				matchRange('0','7');
				}
				{
				matchRange('0','7');
				}
			}
			else if ((LA(1)=='\\') && ((LA(2) >= '0' && LA(2) <= '7'))) {
				match('\\');
				{
				matchRange('0','7');
				}
				{
				matchRange('0','7');
				}
			}
			else if ((LA(1)=='\\') && ((LA(2) >= '0' && LA(2) <= '7'))) {
				match('\\');
				{
				matchRange('0','7');
				}
			}
			else {
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mOctalEscape");
		}
	}
	
	protected final void mUnicodeEscape(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mUnicodeEscape");
		_ttype = UnicodeEscape;
		int _saveIndex;
		try { // debugging
			
			match('\\');
			match('u');
			mHexDigit(false);
			mHexDigit(false);
			mHexDigit(false);
			mHexDigit(false);
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mUnicodeEscape");
		}
	}
	
	public final void mWS_(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mWS_");
		_ttype = WS_;
		int _saveIndex;
		try { // debugging
			
			{
			switch ( LA(1)) {
			case ' ':
			{
				match(' ');
				break;
			}
			case '\n':
			{
				match('\n');
				newline();
				break;
			}
			case '\t':
			{
				match('\t');
				break;
			}
			case '\r':
			{
				match('\r');
				break;
			}
			default:
			{
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			}
			}
			_ttype = Token.SKIP;
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mWS_");
		}
	}
	
	public final void mSL_COMMENT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mSL_COMMENT");
		_ttype = SL_COMMENT;
		int _saveIndex;
		try { // debugging
			
			match("//");
			{
			_loop72:
			do {
				if ((_tokenSet_10.member(LA(1)))) {
					matchNot('\n');
				}
				else {
					break _loop72;
				}
				
			} while (true);
			}
			match('\n');
			_ttype = Token.SKIP; newline ();
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mSL_COMMENT");
		}
	}
	
	public final void mML_COMMENT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mML_COMMENT");
		_ttype = ML_COMMENT;
		int _saveIndex;
		try { // debugging
			
			match("/*");
			{
			_loop76:
			do {
				switch ( LA(1)) {
				case '\n':
				{
					match('\n');
					newline();
					break;
				}
				case '\u0000':  case '\u0001':  case '\u0002':  case '\u0003':
				case '\u0004':  case '\u0005':  case '\u0006':  case '\u0007':
				case '\u0008':  case '\t':  case '\u000b':  case '\u000c':
				case '\u000e':  case '\u000f':  case '\u0010':  case '\u0011':
				case '\u0012':  case '\u0013':  case '\u0014':  case '\u0015':
				case '\u0016':  case '\u0017':  case '\u0018':  case '\u0019':
				case '\u001a':  case '\u001b':  case '\u001c':  case '\u001d':
				case '\u001e':  case '\u001f':  case ' ':  case '!':
				case '"':  case '#':  case '$':  case '%':
				case '&':  case '\'':  case '(':  case ')':
				case '+':  case ',':  case '-':  case '.':
				case '/':  case '0':  case '1':  case '2':
				case '3':  case '4':  case '5':  case '6':
				case '7':  case '8':  case '9':  case ':':
				case ';':  case '<':  case '=':  case '>':
				case '?':  case '@':  case 'A':  case 'B':
				case 'C':  case 'D':  case 'E':  case 'F':
				case 'G':  case 'H':  case 'I':  case 'J':
				case 'K':  case 'L':  case 'M':  case 'N':
				case 'O':  case 'P':  case 'Q':  case 'R':
				case 'S':  case 'T':  case 'U':  case 'V':
				case 'W':  case 'X':  case 'Y':  case 'Z':
				case '[':  case '\\':  case ']':  case '^':
				case '_':  case '`':  case 'a':  case 'b':
				case 'c':  case 'd':  case 'e':  case 'f':
				case 'g':  case 'h':  case 'i':  case 'j':
				case 'k':  case 'l':  case 'm':  case 'n':
				case 'o':  case 'p':  case 'q':  case 'r':
				case 's':  case 't':  case 'u':  case 'v':
				case 'w':  case 'x':  case 'y':  case 'z':
				case '{':  case '|':  case '}':  case '~':
				case '\u007f':
				{
					{
					match(_tokenSet_11);
					}
					break;
				}
				default:
					if (((LA(1)=='*') && ((LA(2) >= '\u0000' && LA(2) <= '\u007f')))&&( LA(2)!='/' )) {
						match('*');
					}
					else if ((LA(1)=='\r') && (LA(2)=='\n')) {
						match('\r');
						match('\n');
						newline();
					}
					else if ((LA(1)=='\r') && ((LA(2) >= '\u0000' && LA(2) <= '\u007f'))) {
						match('\r');
						newline();
					}
				else {
					break _loop76;
				}
				}
			} while (true);
			}
			match("*/");
			_ttype = Token.SKIP;
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mML_COMMENT");
		}
	}
	
	public final void mLINE_COMMAND(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mLINE_COMMAND");
		_ttype = LINE_COMMAND;
		int _saveIndex;
		try { // debugging
			
			match('#');
			{
			_loop80:
			do {
				if ((_tokenSet_12.member(LA(1)))) {
					{
					match(_tokenSet_12);
					}
				}
				else {
					break _loop80;
				}
				
			} while (true);
			}
			{
			switch ( LA(1)) {
			case '\r':
			{
				match('\r');
				break;
			}
			case '\n':
			{
				break;
			}
			default:
			{
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			}
			}
			match('\n');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mLINE_COMMAND");
		}
	}
	
	public final void mLBRACE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mLBRACE");
		_ttype = LBRACE;
		int _saveIndex;
		try { // debugging
			
			match('{');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mLBRACE");
		}
	}
	
	public final void mRBRACE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mRBRACE");
		_ttype = RBRACE;
		int _saveIndex;
		try { // debugging
			
			match('}');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mRBRACE");
		}
	}
	
	public final void mLPAREN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mLPAREN");
		_ttype = LPAREN;
		int _saveIndex;
		try { // debugging
			
			match('(');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mLPAREN");
		}
	}
	
	public final void mRPAREN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mRPAREN");
		_ttype = RPAREN;
		int _saveIndex;
		try { // debugging
			
			match(')');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mRPAREN");
		}
	}
	
	public final void mLBRACKET(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mLBRACKET");
		_ttype = LBRACKET;
		int _saveIndex;
		try { // debugging
			
			match('[');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mLBRACKET");
		}
	}
	
	public final void mRBRACKET(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mRBRACKET");
		_ttype = RBRACKET;
		int _saveIndex;
		try { // debugging
			
			match(']');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mRBRACKET");
		}
	}
	
	public final void mSEMI(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mSEMI");
		_ttype = SEMI;
		int _saveIndex;
		try { // debugging
			
			match(';');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mSEMI");
		}
	}
	
	public final void mCOLON(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mCOLON");
		_ttype = COLON;
		int _saveIndex;
		try { // debugging
			
			match(':');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mCOLON");
		}
	}
	
	public final void mCOMMA(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mCOMMA");
		_ttype = COMMA;
		int _saveIndex;
		try { // debugging
			
			match(',');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mCOMMA");
		}
	}
	
	public final void mPERIOD(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mPERIOD");
		_ttype = PERIOD;
		int _saveIndex;
		try { // debugging
			
			match('.');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mPERIOD");
		}
	}
	
	public final void mASTERISK(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mASTERISK");
		_ttype = ASTERISK;
		int _saveIndex;
		try { // debugging
			
			match('*');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mASTERISK");
		}
	}
	
	public final void mPLUS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mPLUS");
		_ttype = PLUS;
		int _saveIndex;
		try { // debugging
			
			match('+');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mPLUS");
		}
	}
	
	public final void mMINUS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mMINUS");
		_ttype = MINUS;
		int _saveIndex;
		try { // debugging
			
			match('-');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mMINUS");
		}
	}
	
	public final void mDIV(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mDIV");
		_ttype = DIV;
		int _saveIndex;
		try { // debugging
			
			match('/');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mDIV");
		}
	}
	
	public final void mMOD(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mMOD");
		_ttype = MOD;
		int _saveIndex;
		try { // debugging
			
			match('%');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mMOD");
		}
	}
	
	public final void mINC(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mINC");
		_ttype = INC;
		int _saveIndex;
		try { // debugging
			
			match("++");
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mINC");
		}
	}
	
	public final void mDEC(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mDEC");
		_ttype = DEC;
		int _saveIndex;
		try { // debugging
			
			match("--");
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mDEC");
		}
	}
	
	public final void mLOG_AND(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mLOG_AND");
		_ttype = LOG_AND;
		int _saveIndex;
		try { // debugging
			
			match("&&");
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mLOG_AND");
		}
	}
	
	public final void mLOG_OR(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mLOG_OR");
		_ttype = LOG_OR;
		int _saveIndex;
		try { // debugging
			
			match("||");
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mLOG_OR");
		}
	}
	
	public final void mARROW(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mARROW");
		_ttype = ARROW;
		int _saveIndex;
		try { // debugging
			
			match("->");
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mARROW");
		}
	}
	
	public final void mBIN_AND(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mBIN_AND");
		_ttype = BIN_AND;
		int _saveIndex;
		try { // debugging
			
			match('&');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mBIN_AND");
		}
	}
	
	public final void mBIN_OR(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mBIN_OR");
		_ttype = BIN_OR;
		int _saveIndex;
		try { // debugging
			
			match('|');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mBIN_OR");
		}
	}
	
	public final void mBIN_XOR(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mBIN_XOR");
		_ttype = BIN_XOR;
		int _saveIndex;
		try { // debugging
			
			match('^');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mBIN_XOR");
		}
	}
	
	public final void mTILDE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mTILDE");
		_ttype = TILDE;
		int _saveIndex;
		try { // debugging
			
			match('~');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mTILDE");
		}
	}
	
	public final void mBANG(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mBANG");
		_ttype = BANG;
		int _saveIndex;
		try { // debugging
			
			match('!');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mBANG");
		}
	}
	
	public final void mEQ(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mEQ");
		_ttype = EQ;
		int _saveIndex;
		try { // debugging
			
			match("==");
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mEQ");
		}
	}
	
	public final void mNEQ(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mNEQ");
		_ttype = NEQ;
		int _saveIndex;
		try { // debugging
			
			match("!=");
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mNEQ");
		}
	}
	
	public final void mGEQ(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mGEQ");
		_ttype = GEQ;
		int _saveIndex;
		try { // debugging
			
			match(">=");
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mGEQ");
		}
	}
	
	public final void mLEQ(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mLEQ");
		_ttype = LEQ;
		int _saveIndex;
		try { // debugging
			
			match("<=");
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mLEQ");
		}
	}
	
	public final void mGT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mGT");
		_ttype = GT;
		int _saveIndex;
		try { // debugging
			
			match(">");
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mGT");
		}
	}
	
	public final void mLT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mLT");
		_ttype = LT;
		int _saveIndex;
		try { // debugging
			
			match("<");
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mLT");
		}
	}
	
	public final void mSHL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mSHL");
		_ttype = SHL;
		int _saveIndex;
		try { // debugging
			
			match("<<");
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mSHL");
		}
	}
	
	public final void mSHR(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mSHR");
		_ttype = SHR;
		int _saveIndex;
		try { // debugging
			
			match(">>");
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mSHR");
		}
	}
	
	public final void mQ(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mQ");
		_ttype = Q;
		int _saveIndex;
		try { // debugging
			
			match('?');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mQ");
		}
	}
	
	public final void mASSIGN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mASSIGN");
		_ttype = ASSIGN;
		int _saveIndex;
		try { // debugging
			
			match('=');
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mASSIGN");
		}
	}
	
	public final void mMUL_ASSIGN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mMUL_ASSIGN");
		_ttype = MUL_ASSIGN;
		int _saveIndex;
		try { // debugging
			
			match("*=");
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mMUL_ASSIGN");
		}
	}
	
	public final void mDIV_ASSIGN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mDIV_ASSIGN");
		_ttype = DIV_ASSIGN;
		int _saveIndex;
		try { // debugging
			
			match("/=");
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mDIV_ASSIGN");
		}
	}
	
	public final void mADD_ASSIGN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mADD_ASSIGN");
		_ttype = ADD_ASSIGN;
		int _saveIndex;
		try { // debugging
			
			match("+=");
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mADD_ASSIGN");
		}
	}
	
	public final void mSUB_ASSIGN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mSUB_ASSIGN");
		_ttype = SUB_ASSIGN;
		int _saveIndex;
		try { // debugging
			
			match("-=");
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mSUB_ASSIGN");
		}
	}
	
	public final void mMOD_ASSIGN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mMOD_ASSIGN");
		_ttype = MOD_ASSIGN;
		int _saveIndex;
		try { // debugging
			
			match("%=");
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mMOD_ASSIGN");
		}
	}
	
	public final void mSHR_ASSIGN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mSHR_ASSIGN");
		_ttype = SHR_ASSIGN;
		int _saveIndex;
		try { // debugging
			
			match(">>=");
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mSHR_ASSIGN");
		}
	}
	
	public final void mSHL_ASSIGN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mSHL_ASSIGN");
		_ttype = SHL_ASSIGN;
		int _saveIndex;
		try { // debugging
			
			match("<<=");
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mSHL_ASSIGN");
		}
	}
	
	public final void mAND_ASSIGN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mAND_ASSIGN");
		_ttype = AND_ASSIGN;
		int _saveIndex;
		try { // debugging
			
			match("&=");
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mAND_ASSIGN");
		}
	}
	
	public final void mOR_ASSIGN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mOR_ASSIGN");
		_ttype = OR_ASSIGN;
		int _saveIndex;
		try { // debugging
			
			match("|=");
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mOR_ASSIGN");
		}
	}
	
	public final void mXOR_ASSIGN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		traceIn("mXOR_ASSIGN");
		_ttype = XOR_ASSIGN;
		int _saveIndex;
		try { // debugging
			
			match("^=");
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		} finally { // debugging
			traceOut("mXOR_ASSIGN");
		}
	}
	
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 288019269919178752L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 288019269919178752L, 481036337264L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { -549755813889L, -268435457L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 566935683072L, 5700160604602368L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { -17179869185L, -268435457L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 287948901175001088L, 541165879422L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 0L, 9024791442886656L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 0L, 343597383760L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 287948901175001088L, 137438953504L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { 287948901175001088L, 481036337264L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = { -1025L, -1L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	private static final long[] mk_tokenSet_11() {
		long[] data = { -4398046520321L, -1L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
	private static final long[] mk_tokenSet_12() {
		long[] data = { -9217L, -1L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_12 = new BitSet(mk_tokenSet_12());
	
	}
