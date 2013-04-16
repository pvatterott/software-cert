package edu.mit.compilers.IR;

import java.util.ArrayList;
import java.util.List;

public class IrIf extends IrNode {
  private IrCondExpression mExpr;
  private List<IrNode> mSatisfied;
  private List<IrNode> mUnsatisfied;
  private boolean mHasElse;
  
  public IrIf(IrCondExpression expr) {
    mExpr = expr;
    mSatisfied = new ArrayList<IrNode>();
    mUnsatisfied = new ArrayList<IrNode>();
    mHasElse = false;
  }
  
  public void addSatisfied(IrNode child) { 
    mSatisfied.add(child);
  }
  
  public void addUnsatisfied(IrNode child) {
    mUnsatisfied.add(child);
  }
  
  public IrCondExpression getCond() {
    return mExpr;
  }
  
  public List<IrNode> getSatisfied() {
    return mSatisfied;
  }
  
  public List<IrNode> getUnsatisfied() {
    return mUnsatisfied;
  }
  
  public void setHasElse() {
    mHasElse = true;
  }
  
  public boolean hasElse() {
    return mHasElse;
  }
  
  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }

  @Override
  public IrNode copy() {
    // TODO Auto-generated method stub
    return null;
  }
  
  
}
