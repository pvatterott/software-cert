package edu.mit.compilers.IR;

import java.util.List;
import java.util.ArrayList;

public class IrNode {
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
}
