package edu.mit.compilers.semchecker;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;

import org.junit.Test;

import antlr.RecognitionException;
import antlr.TokenStreamException;

import edu.mit.compilers.IR.IrGenerator;
import edu.mit.compilers.IR.IrProgram;
import edu.mit.compilers.grammar.CParser;
import edu.mit.compilers.grammar.CScanner;
import edu.mit.compilers.tools.CLI;


public class SemanticCheckTests {
  public IrProgram getProg(String prog) throws RecognitionException, TokenStreamException {
    InputStream inputStream = new ByteArrayInputStream(prog.getBytes());
    CScanner scanner = new CScanner(new DataInputStream(inputStream));
    CParser parser = new CParser(scanner);
    parser.setTrace(CLI.debug);
    parser.program();
    IrProgram cfg = (IrProgram)IrGenerator.getIr(parser.getAST());
    return cfg;
  }

  @Test
  public void test0() {
    try {
    SemanticChecker c = new SemanticChecker();
    IrProgram p = getProg("int main() {" +
    		                  "int a, b;" +
    		                  "int b;" +
    		                  "return b; }");
    
    assertFalse(c.check(p));
    
    } catch (Exception e) {
      fail("Exception thrown: " + e.getMessage());
    }
  }

  @Test
  public void test1() {
    try {
    SemanticChecker c = new SemanticChecker();
    IrProgram p = getProg("int main() {" +
                          "int a, b;" +
                          "double c;" +
                          "return a + c; }");
    
    assertFalse(c.check(p));
    
    } catch (Exception e) {
      fail("Exception thrown: " + e.getMessage());
    }
  }
  
  @Test
  public void test2() {
    try {
    SemanticChecker c = new SemanticChecker();
    IrProgram p = getProg("int main() {" +
                          "int a, b;" +
                          "double c;" +
                          "return a + 2.0; }");
    
    assertFalse(c.check(p));
    
    } catch (Exception e) {
      fail("Exception thrown: " + e.getMessage());
    }
  }

  @Test
  public void test3() {
    try {
      SemanticChecker c = new SemanticChecker();
      IrProgram p = getProg("int main() {" +
                            "int a, b;" +
                            "double c;" +
                            "a = (int)c;" +
                            "b = a + b * (double)a;" +
                            "return a + b; }");

      assertFalse(c.check(p));

    } catch (Exception e) {
      fail("Exception thrown: " + e.getMessage());
    }
  }
}
