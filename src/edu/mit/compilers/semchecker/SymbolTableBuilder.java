package edu.mit.compilers.semchecker;

import edu.mit.compilers.IR.*;

public class SymbolTableBuilder extends SemanticCheck {
  private SymbolTable mTable;
  private IrIdentifier mCurrentFunction;
  
  public SymbolTable getTable(IrProgram p) {
    mTable = new SymbolTable();
    p.accept(this);
    return mTable;
  }
  
  @Override
  public void visit(IrDeclaration n) {
    IrType t = n.getType();
    for (IrNode c : n.getChildren()) {
      if (c instanceof IrIdentifier) {
        mTable.addLiteralType(mCurrentFunction, (IrIdentifier)c, t);
      } else if (c instanceof IrAssignment) {
        IrAssignment a = (IrAssignment)c;
        IrIdentifier target = a.getTarget();
        mTable.addLiteralType(mCurrentFunction, target, t);
      } else {
        throw new IllegalArgumentException();
      }
    }
  }
  
  @Override
  public void visit(IrParam n) {
    IrType t = n.getType();
    IrIdentifier i = n.getName();
    mTable.addLiteralType(mCurrentFunction, i, t);
  }
  
  @Override
  public void visit(IrFunctionDef n) {
    
    IrType retType = n.getType();
    IrIdentifier fName = n.getName();
    mCurrentFunction = fName;
    mTable.addFunctionReturnType(fName, retType);
    
    for (IrParam p : n.getParams()) {
      p.accept(this);
    }
    
    for (IrNode c : n.getChildren()) {
      c.accept(this);
    }
  }
}
