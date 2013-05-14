package edu.mit.compilers.semchecker;

import edu.mit.compilers.IR.*;
import edu.mit.compilers.graphmodel.Bound;

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
    IrIdentifier id;
    int lower = n.getLowerBound();
    int upper = n.getUpperBound();
    Bound b = new Bound(lower, upper);
    for (IrNode c : n.getChildren()) {
      if (c instanceof IrIdentifier) {
        id = (IrIdentifier)c;
        mTable.addLiteralType(mCurrentFunction, id, t);
        mTable.addBounds(mCurrentFunction, id, b);
      } else if (c instanceof IrAssignment) {
        IrAssignment a = (IrAssignment)c;
        id = a.getTarget();
        mTable.addLiteralType(mCurrentFunction, id, t);
        mTable.addBounds(mCurrentFunction, id, b);
      } else {
        throw new RuntimeException("Illegal declaration format");
      }
    }
  }
  
  @Override
  public void visit(IrParam n) {
    IrType t = n.getType();
    IrIdentifier i = n.getName();
    mTable.addLiteralType(mCurrentFunction, i, t);
    mTable.addFunctionParamType(mCurrentFunction, t);
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
