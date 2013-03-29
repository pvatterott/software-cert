package edu.mit.compilers.IR;

import java.util.ArrayList;
import java.util.List;

public class IrIf extends IrNode {
  private IrExpression mExpr;
  private List<IrNode> mSatisfied;
  private List<IrNode> mUnsatisfied;
  
  public IrIf(IrExpression expr) {
    mExpr = expr;
    mSatisfied = new ArrayList<IrNode>();
    mUnsatisfied = new ArrayList<IrNode>();
  }
  
  public void addSatisfied(IrNode child) { 
    mSatisfied.add(child);
  }
  
  public void addUnsatisfied(IrNode child) {
    mUnsatisfied.add(child);
  }
  
  public IrExpression getCond() {
    return mExpr;
  }
  
  public List<IrNode> getSatisfied() {
    return mSatisfied;
  }
  
  public List<IrNode> getUnsatisfied() {
    return mUnsatisfied;
  }
  
  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }
}
