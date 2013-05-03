package edu.mit.compilers.IR;

import edu.mit.compilers.semchecker.SymbolTable;

public class IrIdentifier extends IrExpression implements IrCondExpression {
  private String mName;
  private int mAddrOfResult;
  
  public IrIdentifier() {
    mAddrOfResult = -1;
  }

  public IrIdentifier(String name) {
    mName = name;
    mAddrOfResult = -1;
  }
  
  public String getName() {
    return mName;
  }

  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }

  @Override
  public void setResultAddress(int addr) {
    mAddrOfResult = addr;
  }

  @Override
  public int getResultAddress() {
    return mAddrOfResult;
  }
  
  @Override
  public String toString() {
    return mName + " (" + mAddrOfResult + ")";
  }

  @Override
  public IrNode copy() {
    IrIdentifier copy = new IrIdentifier(mName);
    copy.setResultAddress(mAddrOfResult);
    copy.setNextInstr(getNextInstr());
    return copy;
  }

  @Override
  public String getDescription() {
    return Integer.toString(mAddrOfResult);
  }

  @Override
  public IrType getType(SymbolTable table, IrIdentifier currentFunction) {
    return table.getLiteralType(currentFunction, this);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj instanceof IrIdentifier) {
      IrIdentifier other = (IrIdentifier)obj;
      return this.mName.equals(other.mName);
    } else {
      return false;
    }
  }
  
  @Override
  public int hashCode() {
    return mName.hashCode();
  }
}
