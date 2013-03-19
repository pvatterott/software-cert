package edu.mit.compilers.IR;

import java.util.List;
import java.util.ArrayList;

public class IrWhile extends IrNode{
  private IrExpression mExpr;
 // private List<IrNode> mSatisfied;
 // private List<IrNode> mUnsatisfied;
  
  public IrWhile(IrExpression expr) {
    mExpr = expr;
    //mSatisfied = new ArrayList<IrNode>();
    //mUnsatisfied = new ArrayList<IrNode>();
  }
  
  public IrExpression getCond() {
    return mExpr;
  }
}
