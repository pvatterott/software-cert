package edu.mit.compilers.IR;

public class IrBinOp extends IrExpression {
  private IrExpression mLeft, mRight;
  private BinOpType mOpType;
  private int mAddrOfResult;
  
  public IrBinOp(IrExpression left, BinOpType op, IrExpression right) {
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
  
  public BinOpType getOp() {
    return mOpType;
  }
  
	public enum BinOpType {
		ADD,
		SUB,
		MUL,
		DIV,
		MOD,
		RIGHT_SHIFT,
		LEFT_SHIFT,
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
    return "(" + mLeft.toString() + " " + mOpType.toString() + " " + mRight.toString() + ")";
  }

  @Override
  public IrNode copy() {
    IrExpression newLeft = (IrExpression)mLeft.copy();
    IrExpression newRight = (IrExpression)mRight.copy();
    IrBinOp copy = new IrBinOp(newLeft, getOp(), newRight);
    copy.setResultAddress(mAddrOfResult);
    copy.setNextInstr(getNextInstr());
    return copy;
  }
  
  
}
