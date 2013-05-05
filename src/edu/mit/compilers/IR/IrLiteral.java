package edu.mit.compilers.IR;

import edu.mit.compilers.IR.IrType.Type;
import edu.mit.compilers.semchecker.SymbolTable;

public class IrLiteral extends IrExpression {
  private int mIntVal;
  private double mDoubleVal;
  private boolean mIsDouble;

  public IrLiteral(boolean isDouble, String text) {
    mIsDouble = isDouble;
    if (isDouble) {
      mDoubleVal = Double.parseDouble(text);
    } else {
      mIntVal = Integer.parseInt(text);
    }
  }
  
  public IrLiteral(int num) {
    mIntVal = num;
  }
  
  public IrLiteral(double num) {
    mDoubleVal = num;
  }
  
  public boolean isDouble() {
    return mIsDouble;
  }
  
  public int getIntVal() {
    return mIntVal;
  }
  
  public double getDoubleVal() {
    return mDoubleVal;
  }

  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }

  @Override
  public void setResultAddress(int addr) {
    throw new IllegalArgumentException("Cannot assign address to literal");
  }

  @Override
  public int getResultAddress() {
    throw new IllegalArgumentException();
  }
  
  @Override
  public String toString() {
    if (mIsDouble) {
      return Double.toString(mDoubleVal);
    } else {
      return Integer.toString(mIntVal);
    }
  }

  @Override
  public IrNode copy() {
    if (mIsDouble) {
      return new IrLiteral(mDoubleVal);
    } else {
      return new IrLiteral(mIntVal);
    }
  }

  @Override
  public IrType getType(SymbolTable table, IrIdentifier currentFunction) {
    if (mIsDouble) {
      mType = new IrType(Type.DOUBLE);
    } else {
      mType = new IrType(Type.INT);
    }
    return mType;
  }
}
