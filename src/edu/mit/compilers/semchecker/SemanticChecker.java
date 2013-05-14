package edu.mit.compilers.semchecker;

import java.util.List;
import java.util.ArrayList;

import edu.mit.compilers.IR.IrProgram;

public class SemanticChecker {
  List<SemanticCheck> mChecks;
  SymbolTable mSymbolTable;
  
  public SemanticChecker() {
    mChecks = new ArrayList<SemanticCheck>();
    mChecks.add(new TypeChecker());
  }
  
  public SymbolTable getSymbolTable() {
    return mSymbolTable;
  }
  
  public boolean check(IrProgram p) {
    SymbolTableBuilder builder = new SymbolTableBuilder();
    
    try {
      mSymbolTable = builder.getTable(p);
    } catch (CheckException e) {
      System.out.println(e.getMessage());
      return false;
    }
    
    for (SemanticCheck check : mChecks) {
      boolean result;
      try {
        result = check.check(p, mSymbolTable);
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
