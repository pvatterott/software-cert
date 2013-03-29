package edu.mit.compilers.graphmodel;

import java.util.List;

import edu.mit.compilers.IR.*;

public class GraphConverter {
  int mCurrentNum;
  
  public GraphNode convert(IrProgram program) {
    mCurrentNum = 0;
    GraphNode x_0 = newNode();
    GraphNode x_inf = new GraphNode(-1);
    
    x_0.mPositiveEdge = convert(program.getMain(), x_inf);
    
    x_inf.mNodeNumber = mCurrentNum++;
    
    simplify(x_0);
    return x_0;
  }
  
  public static void simplify(GraphNode current) {
    GraphNode next = current.mPositiveEdge;
    
    if (next.mCondition == null &&
        next.mExpression.isEmpty()) {
      current.mPositiveEdge = next.mPositiveEdge;
    }
    
    if (current.mPositiveEdge != null) {
      simplify(current.mPositiveEdge);
    }
    
    if (current.mNegativeEdge != null) {
      simplify(current.mNegativeEdge);
    }
  }
  
  public int numCreated() {
    return mCurrentNum;
  }
  
  private GraphNode newNode() {
    return new GraphNode(mCurrentNum++);
  }
  
  private GraphNode convertSwitch(IrNode n, GraphNode x_inf) {
 /*   if (n instanceof IrReturn) {
      return convert((IrReturn)n, x_inf);
    } else if (n instanceof IrRedacted) {
      return newNode();
    } else if (n instanceof IrAssignment) {
      return convert((IrAssignment)n, x_inf);
    } else if (n instanceof IrExpression) {
      return convert((IrExpression)n, x_inf);
    } else if (n instanceof IrWhile) {
      return convert((IrWhile)n, x_inf);
    } else if (n instanceof IrIf) {
      return convert((IrIf)n, x_inf);
    } else {*/
      throw new ClassCastException();
    //}
  }
  
  private GraphNode convert(IrIf i, GraphNode x_inf) {
    
    return null;
  }
  
  /*private GraphNode convert(IrAssignment a, GraphNode x_inf) {
    GraphNode out = newNode();
    StringBuilder s = new StringBuilder();
    s.append(a.getTarget().getText());
    s.append('=');
    s.append(a.getValue().getText());
    out.mExpression = s.toString();
    return out;
  }
  
  private GraphNode convert(IrExpression e, GraphNode x_inf) {
    /*GraphNode out = newNode();
    out.mExpression = e.getText();
    return out;
  }*/
  
  private GraphNode convert(IrFunctionDef f, GraphNode x_inf) {
    List<IrNode> nodes = f.getChildren();
    
    if (nodes.isEmpty()) {
      return x_inf;
    }
    
    GraphNode out = convertSwitch(nodes.get(0), x_inf);
    GraphNode prev = out;
    GraphNode next;
    
    for (int i = 1; i < nodes.size(); i++) {
      next = convertSwitch(nodes.get(i), x_inf);
      
      if (prev.useNeg()) {
        prev.mNegativeEdge = next;
      } else {
        prev.mPositiveEdge = next;
      }
      
      prev = next;
    }
    
    return out;
  }
  
  /*private GraphNode convert(IrWhile w, GraphNode x_inf) {
    GraphNode out = newNode();
    out.mCondition = w.getCond().getText();
    
    GraphNode prev = out;
    GraphNode next;
    
    List<IrNode> nodes = w.getChildren();
    if (!nodes.isEmpty()) {
    
      for (int i = 0; i < nodes.size(); i++) {
        next = convertSwitch(nodes.get(i), x_inf);
        if (prev.useNeg()) {
          prev.mNegativeEdge = next;
        } else {
          prev.mPositiveEdge = next;
        }

        prev = next;
      }
    }
    
    // Create loop
    if (prev.useNeg()) {
      prev.mNegativeEdge = out;
    } else {
      prev.mPositiveEdge = out;
    }
    
    out.setUseNeg();
    return out;
  }
  
  private GraphNode convert(IrReturn r, GraphNode x_inf) {
    if (r.hasReturn()) {
      GraphNode out = newNode();
      out.mPositiveEdge = x_inf;
      out.mExpression = r.getExpr().getText();
      return out;
    } else {
      return x_inf;
    }
  }*/
  
}
