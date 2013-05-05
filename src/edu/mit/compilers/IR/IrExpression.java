package edu.mit.compilers.IR;

import edu.mit.compilers.semchecker.SymbolTable;

abstract public class IrExpression extends IrNode {
  protected IrType mType;
  abstract public void setResultAddress(int addr);
  abstract public int getResultAddress();
  abstract public IrType getType(SymbolTable table, IrIdentifier currentFunction);
  public IrType getCachedType() {
    return mType;
  }
}
