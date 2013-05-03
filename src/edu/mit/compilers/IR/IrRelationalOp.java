package edu.mit.compilers.IR;

import edu.mit.compilers.semchecker.SymbolTable;

public class IrRelationalOp extends IrExpression implements IrCondExpression {
  private RelOpType mOpType;
  private IrExpression mLeft, mRight;
  private int mAddr;
  
  public IrRelationalOp(IrExpression left, RelOpType op, IrExpression right) {
    mLeft = left;
    mOpType = op;
    mRight = right;
  }
  
  public IrExpression getLeft() {
    return mLeft;
  }
  
  public IrExpression getRight() {
    return mRight;
  }
  
  public RelOpType getOp() {
    return mOpType;
  }
  
  public void setLeft(IrExpression l) {
    mLeft = l;
  }
  
  public void setRight(IrExpression r) {
    mRight = r;
  }
  
  public enum RelOpType {
    EQ,
    NEQ,
    LT,
    GT,
    LEQ,
    GEQ;
  }
  
  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }
  
  @Override
  public String toString() {
    return "(" + mLeft.toString() + " " + mOpType.toString() + " " + mRight.toString() + ")";
  }

  @Override
  public IrNode copy() {
    IrExpression newLeft = (IrExpression)mLeft.copy();
    IrExpression newRight = (IrExpression)mRight.copy();
    IrRelationalOp copy = new IrRelationalOp(newLeft, getOp(), newRight);
    return copy;
  }

  @Override
  public String getDescription() {
    return "(" + mOpType.toString() + " " + 
           ((IrCondExpression)mLeft).getDescription() + " " +
           ((IrCondExpression)mRight).getDescription() + ")";
  }

  @Override
  public void setResultAddress(int addr) {
    mAddr = addr;
  }

  @Override
  public int getResultAddress() {
    return mAddr;
  }

  @Override
  public IrType getType(SymbolTable table, IrIdentifier currentFunction) {
    IrType lType = mLeft.getType(table, currentFunction);
    IrType rType = mRight.getType(table, currentFunction);
    if (lType.equals(rType)) {
      return lType;
    } else {
      return new IrType(IrType.Type.VOID);
    }
  }
}
