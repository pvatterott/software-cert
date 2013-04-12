package edu.mit.compilers.IR;

import java.util.List;
import java.util.ArrayList;

public abstract class IrNode {
  private List<IrNode> mChildren;
  private int mNextInstr;
  
  public IrNode() {
    mChildren = new ArrayList<IrNode>();
    mNextInstr = -1;
  }
  
  public void addChild(IrNode child) {
    mChildren.add(child);
  }
  
  public List<IrNode> getChildren() {
    return mChildren;
  }
  
  public void setNextInstr(int instr) {
    mNextInstr = instr;
  }
  
  public int getNextInstr() {
    return mNextInstr;
  }
  
  abstract public void accept(IrNodeVisitor v);
  
  abstract public IrNode copy();
}
