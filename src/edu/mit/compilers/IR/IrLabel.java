package edu.mit.compilers.IR;

public class IrLabel extends IrNode {
  int mNum;
  
  public IrLabel(int num) {
    mNum = num;
  }
  
  public int getNum() {
    return mNum;
  }
  
  public void setNum(int num) {
    mNum = num;
  }

  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }
  
}
