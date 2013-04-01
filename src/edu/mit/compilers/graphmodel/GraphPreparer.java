package edu.mit.compilers.graphmodel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import edu.mit.compilers.IR.*;
import edu.mit.compilers.IR.IrLabel.LabelType;

public class GraphPreparer implements IrNodeVisitor {
  private List<IrNode> mInstructions;
  private Map<String, Integer> mNameAssignments;
  private int mNextID;
  private int mNextLabel;
  
  public List<IrNode> prepare(IrProgram p) {
    mInstructions = new ArrayList<IrNode>();
    mNameAssignments = new HashMap<String, Integer>();
    mNextID = 0;
    mNextLabel = 0;
    
    visit(p);
    return mInstructions;
  }
  
  private int getNextID() {
    return mNextID++;
  }
  
  private int getNextLabel() {
    return mNextLabel++;
  }

  @Override
  public void visit(IrAssignment n) {
    IrExpression rhs = n.getValue();
    IrIdentifier lhs = n.getTarget();
    
    lhs.accept(this);
    rhs.accept(this);
    
    if (rhs instanceof IrBinOp) {
      IrIdentifier newValue = new IrIdentifier();
      newValue.setResultAddress(rhs.getResultAddress());
      IrAssignment newAssign = new IrAssignment(n.getTarget(), newValue);
      mInstructions.add(newAssign);
    } else {
      mInstructions.add(n);
    }
  }

  @Override
  public void visit(IrBinOp n) {
    IrExpression oldLeft = n.getLeft();
    IrExpression oldRight = n.getRight();
    IrExpression newLeft, newRight;
    
    int newAddr = getNextID();
    IrIdentifier newTarget = new IrIdentifier();
    newTarget.setResultAddress(newAddr);
    n.setResultAddress(newAddr);
    IrBinOp newOp;
    IrAssignment tempAssign;
    
    oldLeft.accept(this);
    oldRight.accept(this);
    
    if (oldLeft instanceof IrBinOp) {
      newLeft = new IrIdentifier();
      newLeft.setResultAddress(oldLeft.getResultAddress());
    } else {
      newLeft = oldLeft;
    }
    
    if (oldRight instanceof IrBinOp) {
      newRight = new IrIdentifier();
      newRight.setResultAddress(oldRight.getResultAddress());
    } else {
      newRight = oldRight;
    }
    
    newOp = new IrBinOp(newLeft, n.getOp(), newRight);
    tempAssign = new IrAssignment(newTarget, newOp);
    mInstructions.add(tempAssign);
  }

  @Override
  public void visit(IrDeclaration n) {
    // Not necessary to handle
  }

  @Override
  public void visit(IrExtFunctionCall n) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visit(IrFunctionDef n) {
    for (IrNode node : n.getChildren()) {
      node.accept(this);
    }
  }

  @Override
  public void visit(IrIdentifier n) {
    String name = n.getName();
    if (!mNameAssignments.containsKey(name)) {
      int id = getNextID();
      String newName = n.getName();
      mNameAssignments.put(newName, id);
      n.setResultAddress(id);
    } else {
      int id = mNameAssignments.get(name);
      n.setResultAddress(id);
    }
  }

  @Override
  public void visit(IrLiteral n) {
    // Address already assigned
  }

  @Override
  public void visit(IrProgram n) {
    IrFunctionDef main = n.getMain();
    main.accept(this);
  }

  @Override
  public void visit(IrRedacted n) {
    // Ignore
  }

  @Override
  public void visit(IrReturn n) {
    if (n.hasReturn()) {
      IrExpression value = n.getExpr();
      value.accept(this);
      if (value instanceof IrBinOp) {
        IrIdentifier newValue = new IrIdentifier();
        newValue.setResultAddress(value.getResultAddress());
        IrReturn newReturn = new IrReturn(newValue);
        mInstructions.add(newReturn);
      } else {
        mInstructions.add(n);
      }
    } else {
      mInstructions.add(n);
    }
  }

  @Override
  public void visit(IrStringLiteral n) {
    // Should not be visited
  }

  @Override
  public void visit(IrType n) {
    // Should not be visited
  }

  @Override
  public void visit(IrWhile n) {
    IrExpression cond = n.getCond();
    cond.accept(this);
    
    int beginNum = getNextLabel();
    int endNum = getNextLabel();
    IrLabel wBegin = new IrLabel(beginNum, LabelType.WBEGIN);
    IrJmp wCheck = new IrJmp(cond, endNum);
    mInstructions.add(wBegin);
    mInstructions.add(wCheck);
    
    for (IrNode subNode : n.getChildren()) {
      subNode.accept(this);
    }
    
    IrJmp wLoop = new IrJmp(beginNum);
    IrLabel wEnd = new IrLabel(endNum, LabelType.WEND);
    mInstructions.add(wLoop);
    mInstructions.add(wEnd);
  }

  @Override
  public void visit(IrIf n) {
    IrExpression cond = n.getCond();
    cond.accept(this);

    int trueNum = getNextLabel();
    int endNum = getNextLabel();
    IrLabel trueLabel = new IrLabel(trueNum, LabelType.IF_TRUE);
    IrLabel endLabel = new IrLabel(endNum, LabelType.IF_END);
    IrJmp jmpTrue = new IrJmp(cond, trueNum);
    IrJmp jmpEnd = new IrJmp(endNum);

    mInstructions.add(jmpTrue);
    
    for (IrNode elseNode : n.getUnsatisfied()) {
      elseNode.accept(this);
    }
    
    mInstructions.add(jmpEnd);
    mInstructions.add(trueLabel);
    
    for (IrNode trueNode : n.getSatisfied()) {
      trueNode.accept(this);
    }
    
    mInstructions.add(endLabel);
  }

  @Override
  public void visit(IrLabel n) {
    // Eventually deal with inline labels (goes with goto statements)
  }

  @Override
  public void visit(IrJmp n) {
    // Eventually deal with goto statements
  }

}
