package edu.mit.compilers.IR;

import java.util.List;
import java.util.ArrayList;

public class IrExtFunctionCall extends IrExpression {
  IrIdentifier mFnName;
  List<IrExpression> mParams;
  int mAddr;
  
  public IrExtFunctionCall(IrIdentifier name) {
    mFnName = name;
    mParams = new ArrayList<IrExpression>();
  }
  
  public void addParam(IrExpression param) {
    mParams.add(param);
  }
  
  public IrIdentifier getName() {
    return mFnName;
  }
  
  public List<IrExpression> getParams() {
    return mParams;
  }
  
  @Override
  public void accept(IrNodeVisitor v) {
    v.visit(this);
  }

  @Override
  public void setResultAddress(int addr) {
    mAddr = addr;
  }

  @Override
  public int getResultAddress() {
    return mAddr;
  }
  
  @Override
  public String toString() {
    String out = mFnName.getName() + "(";
    for (IrExpression param : mParams) {
      out = out + param.toString() + ", ";
    }
    
    out += ")";
    
    return out;
  }

  @Override
  public IrNode copy() {
    IrIdentifier newName = (IrIdentifier)mFnName.copy();
    IrExtFunctionCall newCall = new IrExtFunctionCall(newName);
    for (IrExpression param : mParams) {
      newCall.addParam((IrExpression)param.copy());
    }
    newCall.setResultAddress(mAddr);
    return newCall;
  }
}
