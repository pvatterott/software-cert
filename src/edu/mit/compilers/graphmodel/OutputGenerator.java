package edu.mit.compilers.graphmodel;

import java.util.*;

import edu.mit.compilers.IR.*;
import edu.mit.compilers.semchecker.SymbolTable;

public class OutputGenerator {
  
  public void generate(IrProgram p, SymbolTable symbolTable) {
    GraphPreparer preparer = new GraphPreparer();
    NodeDescriber nDescriber = new NodeDescriber();
    FunctionInliner inliner = new FunctionInliner();
    RangeAssigner assigner = new RangeAssigner();
    RangePropagator propagator = new RangePropagator();
    
    List<IrNode> program;
    Map<String, IrFunctionDef> defs = new HashMap<String, IrFunctionDef>();
    Map<String, List<IrNode>> code = new HashMap<String, List<IrNode>>();
    
    for (IrFunctionDef fn : p.getFunctions()) {
      String fnName = fn.getStrName();
      IrIdentifier name = fn.getName();
      List<IrNode> unsimplifiedLinearRep = preparer.prepare(fn);
      List<IrNode> simplifiedLinearRep = simplifyLinearRep(unsimplifiedLinearRep);
      assigner.propagate(simplifiedLinearRep, name, symbolTable);
      
      defs.put(fnName, fn);
      code.put(fnName, simplifiedLinearRep);
    }
    
    program = inliner.inline(defs, code);
    
    propagator.propagate(program, symbolTable);
    
    AdjacenyMatrix adj = getAdjacencyMatrix(program);
    NodeDescriptionTable nodeDescriptions = new NodeDescriptionTable();
    
    for (IrNode n : program) {
      String[] desc = nDescriber.getNodeDescription(n);
      nodeDescriptions.addRow(desc);
    }
    
    GraphPrinter.printRanges(propagator.getBounds());
    GraphPrinter.print(adj, nodeDescriptions);
  }
  
  private AdjacenyMatrix getAdjacencyMatrix(List<IrNode> linearRep) {
    int numNodes = linearRep.size();
    AdjacenyMatrix out = new AdjacenyMatrix(numNodes);
    
    IrNode next;
    IrBranch nextBranch;
    int jumpTarget, trueTarget, falseTarget;
    for (int i = 0; i < numNodes; i++) {
      next = linearRep.get(i);
      if (next instanceof IrBranch) {
        nextBranch = (IrBranch)next;
        trueTarget = nextBranch.getTrueBranch();
        falseTarget = nextBranch.getFalseBranch();
        out.addJumpLink(i, trueTarget, falseTarget);
      } else {
        jumpTarget = next.getNextInstr();
        out.addNonJumpLink(i, jumpTarget);
      }
    }
    
    return out;
  }
  
  private List<IrNode> simplifyLinearRep(List<IrNode> unsimplified) {
    List<IrNode> simplified = new ArrayList<IrNode>();
    Map<Integer, Integer> labelsToIndexes = new HashMap<Integer, Integer>();
    
    IrNode next;
    
    int end = unsimplified.size() - 1;
    int nonLabelIndex = 0;
    int labelNum;
    
    // Start by gathering instructions and mapping labels to the index in a reversed list
    for (int i = end; i >= 0; i--) {
      next = unsimplified.get(i);
      if (next instanceof IrLabel) {
        labelNum = ((IrLabel) next).getNum();
        if (simplified.size() == 0) {
          labelsToIndexes.put(labelNum, -1);
        } else {
          labelsToIndexes.put(labelNum, nonLabelIndex);
        }
      } else {
        nonLabelIndex = simplified.size();
        simplified.add(next);
      }
    }   

    // Put instructions in correct order and invert the indexes
    int back = simplified.size() - 1;
    int oldIndex, newIndex;
    Collections.reverse(simplified);
    for (int label : labelsToIndexes.keySet()) {
      oldIndex = labelsToIndexes.get(label);
      newIndex = back - oldIndex;
      labelsToIndexes.put(label, newIndex);
    }
    
    // Alter the jumps to now use the labeled node locations
    IrBranch b;
    IrNode n;
    int oldT, newT;
    for (int i = 0; i < simplified.size(); i++) {
      n = simplified.get(i);
      if (n instanceof IrBranch) {
        b = (IrBranch)n;
        oldT = b.getFalseBranch();
        newT = labelsToIndexes.get(oldT);
        b.setFalseBranch(newT);
        oldT = b.getTrueBranch();
        newT = labelsToIndexes.get(oldT);
        b.setTrueBranch(newT);
      } else {
        oldT = n.getNextInstr();
        if (oldT == -1) {
          newT = i + 1;
        } else {
          newT = labelsToIndexes.get(oldT);
        }
        n.setNextInstr(newT);
      }
    }
    
    return simplified;
  }
}
