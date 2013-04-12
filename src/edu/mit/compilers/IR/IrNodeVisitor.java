package edu.mit.compilers.IR;

public interface IrNodeVisitor {
  public void visit(IrAssignment n);
  public void visit(IrBinOp n);
  public void visit(IrBranch n);
  public void visit(IrDeclaration n);
  public void visit(IrExtFunctionCall n);
  public void visit(IrFunctionDef n);
  public void visit(IrIdentifier n);
  public void visit(IrLiteral n);
  public void visit(IrProgram n);
  public void visit(IrRedacted n);
  public void visit(IrReturn n);
  public void visit(IrType n);
  public void visit(IrWhile n);
  public void visit(IrIf n);
  public void visit(IrLabel n);
}
