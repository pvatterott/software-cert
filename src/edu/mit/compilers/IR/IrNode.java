package edu.mit.compilers.IR;

import java.util.List;
import java.util.ArrayList;

public abstract class IrNode {
  private List<IrNode> mChildren;
  
  public IrNode() {
    mChildren = new ArrayList<IrNode>();
  }
  
  public void addChild(IrNode child) {
    mChildren.add(child);
  }
  
  public List<IrNode> getChildren() {
    return mChildren;
  }
  
  abstract public void accept(IrNodeVisitor v);
  
  abstract public IrNode copy();
}
