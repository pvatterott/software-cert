package edu.mit.compilers.graphmodel;

import java.util.List;

import edu.mit.compilers.IR.*;
import edu.mit.compilers.semchecker.SymbolTable;

// Propagates information about the potential range of variables through a function.
// All instances of an identifier will be populated with the correct range information
public class RangeAssigner implements IrNodeVisitor{
  private SymbolTable mTable;
  private IrIdentifier mFunctionName;
  
  public void propagate(List<IrNode> function, IrIdentifier name, SymbolTable table) {
    mTable = table;
    mFunctionName = name;
    
    for (IrNode n : function) {
      n.accept(this);
    }
  }
  
  
  public void visit(IrAssignment n) {
    n.getTarget().accept(this);
    n.getValue().accept(this);
  }
  
  public void visit(IrBinOp n) {
    n.getLeft().accept(this);
    n.getRight().accept(this);
  }
  
  public void visit(IrBranch n) {
    n.getCond().accept(this);
  }
  
  public void visit(IrDeclaration n) {
    for (IrNode c : n.getChildren()) {
      c.accept(this);
    }
  }
  
  public void visit(IrExtFunctionCall n) {
    n.getName().accept(this);
    for (IrExpression p : n.getParams()) {
      p.accept(this);
    }
  }
  
  public void visit(IrFunctionDef n) {
    for (IrParam p : n.getParams()) {
      p.accept(this);
    }
    
    for (IrNode c : n.getChildren()) {
      c.accept(this);
    }
  }
  
  public void visit(IrIdentifier n) {
    Bound b;
    if (mTable.containsBoundsFor(mFunctionName, n)) {
      b = mTable.getBounds(mFunctionName, n);
      n.setBounds(b);
    } else if (n.getName() == null) {
      b = mTable.getDefaultBound();
      n.setBounds(b);
    }
  }
  
  public void visit(IrLiteral n) {}
  
  public void visit(IrProgram n) {
    for (IrFunctionDef f : n.getFunctions()) {
      f.accept(this);
    }
  }
  
  public void visit(IrRedacted n) {}
  
  public void visit(IrReturn n) {
    n.getExpr().accept(this);
  }
  
  public void visit(IrType n) {}
  
  public void visit(IrWhile n) {
    n.getCond().accept(this);
    
    for (IrNode c : n.getChildren()) {
      c.accept(this);
    }
  }
  
  public void visit(IrFor n) {
    n.getCondition().accept(this);
    n.getInitializer().accept(this);
    n.getUpdate().accept(this);
    
    for (IrNode c : n.getChildren()) {
      c.accept(this);
    }
  }
  
  public void visit(IrIf n) {
    
  }
  
  public void visit(IrParam n) {
    n.getName().accept(this);
  }
  
  public void visit(IrLabel n) {}
  
  public void visit(IrCast n) {
    n.getExpression().accept(this);
  }
}
