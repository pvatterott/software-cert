package edu.mit.compilers.IR;

import edu.mit.compilers.graphmodel.Bound;
import edu.mit.compilers.semchecker.SymbolTable;

public class IrIdentifier extends IrExpression {
  private String mName;
  private int mAddrOfResult;
  private Bound mBounds;
  
  public IrIdentifier() {
    mAddrOfResult = -1;
    mBounds = null;
  }

  public IrIdentifier(String name) {
    mName = name;
    mAddrOfResult = -1;
  }
  
  public Bound getBounds() {
    return mBounds;
  }
  
  public void setBounds(Bound bounds) {
    mBounds = bounds;
  }
  
  public boolean hasBounds() {
    return (mBounds != null);
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
  public IrType getType(SymbolTable table, IrIdentifier currentFunction) {
    mType = table.getLiteralType(currentFunction, this);
    return mType;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    
    if (obj == null) {
      return false;
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
    if (mName == null) {
      return "".hashCode();
    }
    return mName.hashCode();
  }
}
