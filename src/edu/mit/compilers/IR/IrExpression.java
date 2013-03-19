package edu.mit.compilers.IR;

public class IrExpression extends IrNode {
  private String mText;
  
  public IrExpression(String text) {
    mText = text;
  }
  
  public String getText() {
    return mText;
  }
}
