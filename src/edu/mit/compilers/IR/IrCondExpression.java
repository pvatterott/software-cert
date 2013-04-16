package edu.mit.compilers.IR;

abstract public interface IrCondExpression {
  IrNode copy();
  String getDescription();
  void accept(IrNodeVisitor idRecalibrator);
}
