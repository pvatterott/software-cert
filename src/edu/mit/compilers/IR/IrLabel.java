package edu.mit.compilers.IR;

public class IrLabel extends IrNode {
  private int mNum;
  private LabelType mType;
  
  public IrLabel(int num, LabelType type) {
    mNum = num;
    mType = type;
  }
  
  public enum LabelType {
    WBEGIN,
    WEND,
    IF_END,
    IF_TRUE;
  }
  
  public int getNum() {
    return mNum;
  }
  
  public LabelType getType() {
    return mType;
  }
  
  public void setNum(int num) {
    mNum = num;
  }

  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }
  
  @Override
  public String toString() {
    return "LABEL: " + mNum;
  }

  @Override
  public IrNode copy() {
    return new IrLabel(mNum, mType);
  }
  
}
