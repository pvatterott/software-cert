package edu.mit.compilers.semchecker;

import java.util.List;

import edu.mit.compilers.IR.*;
import edu.mit.compilers.IR.IrType.Type;

public class TypeChecker extends SemanticCheck {
  private SymbolTable mTable;
  private IrIdentifier mCurrentFunction;
  
  @Override
  public boolean check(IrProgram p, SymbolTable table) {
    mTable = table;
    return super.check(p, table);
  }
  
  public void typeCheck(IrType expected, IrType actual, String m) {
    mFailed = !expected.equals(actual);
    if (mFailed) {
      mErrorMessage = m;
      throw new CheckException(mErrorMessage);
    }
    
  }
  
  @Override
  public void visit(IrFunctionDef n) {
    mCurrentFunction = n.getName();
    
    for (IrParam p : n.getParams()) {
      p.accept(this);
    }
    
    for (IrNode c : n.getChildren()) {
      c.accept(this);
    }
  }
  
  @Override
  public void visit(IrExtFunctionCall n) {
    IrIdentifier fnName = n.getName();
    IrType expected, actual;
    List<IrExpression> params = n.getParams();
    IrExpression param;
    int numParams = params.size();
    
    String err = "Illegal parameter type in call to " + fnName;
    
    for (int i = 0; i < numParams; i++) {
      param = params.get(i);
      param.accept(this);
      expected = mTable.getFunctionParamType(fnName, i);
      actual = param.getType(mTable, mCurrentFunction);
      typeCheck(expected, actual, err);
    }
  }
  
  @Override
  public void visit(IrAssignment n) {
    IrIdentifier lhs = n.getTarget();
    IrExpression rhs = n.getValue();
    lhs.accept(this);
    rhs.accept(this);
    
    IrType expected = mTable.getLiteralType(mCurrentFunction, lhs);
    IrType actual = rhs.getType(mTable, mCurrentFunction);
    
    String err = "Type error in assignment: " + n.toString();
    typeCheck(expected, actual, err);
  }
  
  @Override
  public void visit(IrReturn n) {
    IrExpression exp = n.getExpr();
    exp.accept(this);
    IrType expected = mTable.getFunctionReturnType(mCurrentFunction);
    
    if (expected.equals(new IrType(Type.VOID))) {
      if (n.hasReturn()) {
        mErrorMessage = "Cannot return value in void function";
        mFailed = true;
      }
      return;
    }
    
    if (!n.hasReturn()) {
      mErrorMessage = "Return statement must have value";
      mFailed = true;
    }
    
    IrType actual = exp.getType(mTable, mCurrentFunction);
    
    String err = "Cannot return type " + actual + " in " + mCurrentFunction;
    typeCheck(expected, actual, err);
  }
}
