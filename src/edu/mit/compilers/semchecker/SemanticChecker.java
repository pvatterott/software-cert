package edu.mit.compilers.semchecker;

import java.util.List;
import java.util.ArrayList;

import edu.mit.compilers.IR.IrProgram;

public class SemanticChecker {
  List<SemanticCheck> mChecks;
  
  public SemanticChecker() {
    mChecks = new ArrayList<SemanticCheck>();
    mChecks.add(new TypeChecker());
  }
  
  public boolean check(IrProgram p) {
    SymbolTableBuilder builder = new SymbolTableBuilder();
    SymbolTable symbolTable;
    
    try {
      symbolTable = builder.getTable(p);
    } catch (CheckException e) {
      System.out.println(e.getMessage());
      return false;
    }
    
    for (SemanticCheck check : mChecks) {
      boolean result;
      try {
        result = check.check(p, symbolTable);
      } catch (CheckException e) {
        System.out.println(e.getMessage());
        return false;
      }
      
      if (result) {
        System.out.println(check.getError());
        return false;
      }
    }
    return true;
  }
}
