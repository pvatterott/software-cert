package edu.mit.compilers;

import java.io.*;

import antlr.Token;
import edu.mit.compilers.IR.*;
import edu.mit.compilers.grammar.*;
import edu.mit.compilers.graphmodel.*;
import edu.mit.compilers.semchecker.SemanticChecker;
import edu.mit.compilers.semchecker.SymbolTable;
import edu.mit.compilers.tools.CLI;
import edu.mit.compilers.tools.CLI.Action;
import edu.mit.compilers.tools.TreeVisualizer;

class Main {
  public static void main(String[] args) {
    try {
      CLI.parse(args, new String[0]);
      InputStream inputStream = args.length == 0 ? System.in
          : new java.io.FileInputStream(CLI.infile);
      if (CLI.target == Action.SCAN) {
        CScanner scanner = new CScanner(new DataInputStream(inputStream));
        scanner.setTrace(CLI.debug);
        Token token;
        boolean done = false;
        while (!done) {
          try {
            for (token = scanner.nextToken(); token.getType() != CParserTokenTypes.EOF; token = scanner
                .nextToken()) {
              System.out.println(token.getType());
            }
            done = true;
          } catch (Exception e) {
            // print the error:
            System.out.println("ERROR: " + CLI.infile + " " + e);
            scanner.consume();
          }
        }
      } else if (CLI.target == Action.PARSE || CLI.target == Action.DOT
          || CLI.target == Action.DEFAULT) {
        CScanner scanner = new CScanner(new DataInputStream(inputStream));
        CParser parser = new CParser(scanner);
        parser.setTrace(CLI.debug);
        parser.program();
        if (parser.getError()) {
          System.out.println("ERROR");
          System.exit(-1);
        }
        if (CLI.target == Action.DOT) {
          System.out.println(TreeVisualizer.generateDOT(parser.getAST()));
        }
      } else if (CLI.target == Action.LOWIR) {
        CScanner scanner = new CScanner(new DataInputStream(inputStream));
        CParser parser = new CParser(scanner);
        parser.setTrace(CLI.debug);
        parser.program();
        if (parser.getError()) {
          System.out.println("ERROR");
          System.exit(-1);
        }
        IrProgram cfg = (IrProgram)IrGenerator.getIr(parser.getAST());
        
        SemanticChecker checker = new SemanticChecker();
        checker.check(cfg);
        SymbolTable table = checker.getSymbolTable();
        
        OutputGenerator gen = new OutputGenerator();
        gen.generate(cfg, table);
      } else {
        System.out.println("Unsupported target");
      }
    } catch (Exception e) {
      // print the error:
      System.out.println(CLI.infile + " " + e);
    }
  }
}
