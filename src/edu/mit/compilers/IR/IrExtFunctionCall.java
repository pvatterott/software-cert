package edu.mit.compilers.IR;

import java.util.List;
import java.util.ArrayList;

import edu.mit.compilers.semchecker.SymbolTable;

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
    newCall.setNextInstr(getNextInstr());
    return newCall;
  }

  @Override
  public IrType getType(SymbolTable table, IrIdentifier currentFunction) {
    mType = table.getFunctionReturnType(mFnName);
    return mType;
  }
}
