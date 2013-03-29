package edu.mit.compilers.IR;

import java.util.List;
import java.util.ArrayList;

public class IrControlPoint extends IrNode {
  private IrNode mCondition;
  private List<IrNode> mChildren;
  
  public IrControlPoint(IrNode condition) {
    mCondition = condition;
    mChildren = new ArrayList<IrNode>();
  }
  
  public void addChild(IrNode child) {
    mChildren.add(child);
  }
  
  public IrNode getCond() {
    return mCondition;
  }
  
  public List<IrNode> getChildren() {
    return mChildren;
  }
  
  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }
}
