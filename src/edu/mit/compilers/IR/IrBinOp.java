package edu.mit.compilers.IR;

import edu.mit.compilers.semchecker.SymbolTable;

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
		AND,
    OR,
    EQ,
    NEQ,
    LT,
    GT,
    LEQ,
    GEQ,
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
  
  @Override
  public IrType getType(SymbolTable table, IrIdentifier currentFunction) {
    IrType lType = mLeft.getType(table, currentFunction);
    IrType rType = mRight.getType(table, currentFunction);
    if (lType.equals(rType)) {
      mType = lType;
    } else {
      mType = new IrType(IrType.Type.VOID);
    }
    return mType;
  }
}
