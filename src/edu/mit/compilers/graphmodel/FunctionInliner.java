package edu.mit.compilers.graphmodel;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import edu.mit.compilers.IR.*;

public class FunctionInliner {
  private final String MAIN = "main";
  
  private List<IrNode> mProgram;
  private Map<String, IrFunctionDef> mDefs;
  private Map<String, List<IrNode>> mCode;
  private int mCurrentNumIdentifiers;
  
  public FunctionInliner() {
  }
  
  public List<IrNode> inline(Map<String, IrFunctionDef> defs, Map<String, List<IrNode>> code) {
    mDefs = defs;
    mCode = code;
    mProgram = code.get(MAIN);
    mCurrentNumIdentifiers = defs.get(MAIN).getNumVars();
    
    while (inlineSingleFunctionCall());
    
    return mProgram;
  }
  
  public boolean inlineSingleFunctionCall() {
    boolean wasInlined = false;
    List<IrNode> instructionsToInsert = new ArrayList<IrNode>();
    
    List<IrNode> calledFunction, calledCopy;
    IrExtFunctionCall call;
    IrFunctionDef def;
    String calledFunctionName;
    IrIdentifier target;
    
    int internalOffset = 0;
    for (IrNode n : mProgram) {
      if (n instanceof IrAssignment) {
        IrAssignment assign = (IrAssignment)n;
        IrExpression value = assign.getValue();
        if (value instanceof IrExtFunctionCall) {
          wasInlined = true;
          call = (IrExtFunctionCall)value;
          target = assign.getTarget();
          calledFunctionName = call.getName().getName();
          calledFunction = mCode.get(calledFunctionName);
          calledCopy = new ArrayList<IrNode>();
          for (IrNode instr : calledFunction) {
            calledCopy.add(instr.copy());
          }
          def = (IrFunctionDef)mDefs.get(calledFunctionName).copy();

          recalibrateIDs(calledCopy, def);
          mCurrentNumIdentifiers += def.getNumVars();
          List<IrExpression> inParams = call.getParams();
          List<IrDeclaration> declaredParams = def.getParams();
          instructionsToInsert = getCodeToInsert(calledCopy, target, inParams, declaredParams);

          mProgram.remove(internalOffset); // Remove old assign (function call) instruction
          mProgram.addAll(internalOffset, instructionsToInsert);
          // Recalibrate jumps
          int inserted = instructionsToInsert.size() - 1;
          int externalOffset = internalOffset + inserted + 1;
          recalibratePre(internalOffset, inserted);
          recalibrateInserted(internalOffset, externalOffset, internalOffset); // Inserted code
          recalibratePost(externalOffset, internalOffset, inserted); // Post code
          break;
        }
      }
      internalOffset++;
    }
    
    
    return wasInlined;
  }
  
  private List<IrNode> getCodeToInsert(List<IrNode> calledFunction, IrIdentifier target, List<IrExpression> inParams, List<IrDeclaration> declaredParams) {
    List<IrNode> outInstructions = new ArrayList<IrNode>();
    List<IrNode> insertedJumps = new ArrayList<IrNode>();
    
    int numDecs = inParams.size();
    IrIdentifier passedIn, assigned;
    IrAssignment assignment;
    for (int i = 0; i < numDecs; i++) {
      passedIn = (IrIdentifier)inParams.get(i);
      assigned = declaredParams.get(i).getName();
      assignment = new IrAssignment(assigned, passedIn);
      assignment.setNextInstr(i+1);
      outInstructions.add(assignment);
    }
    
    int oldI, newI;
    IrBranch b;
    IrReturn ret;
    for (IrNode n : calledFunction) {
      if (n instanceof IrReturn) {
        ret = (IrReturn)n;
        assignment = new IrAssignment(target, ret.getExpr());
        outInstructions.add(assignment);
        insertedJumps.add(assignment);
      } else if  (n instanceof IrBranch) {
        b = (IrBranch)n;
        oldI = b.getFalseBranch();
        newI = oldI + numDecs;
        b.setFalseBranch(newI);
        oldI = b.getTrueBranch();
        newI = oldI + numDecs;
        b.setTrueBranch(newI);
        outInstructions.add(b);
      } else {
        oldI = n.getNextInstr();
        newI = oldI + numDecs;
        n.setNextInstr(newI);
        outInstructions.add(n);
      }
    }
    
    int jmpTarget = outInstructions.size();
    for (IrNode j : insertedJumps) {
      j.setNextInstr(jmpTarget);
    }

    return outInstructions;
  }
  
  private void recalibrateIDs(List<IrNode> nodes, IrFunctionDef def) {
    IDRecalibrator recalibrator = new IDRecalibrator();
    recalibrator.recalibrate(def, mCurrentNumIdentifiers);
    for (IrNode n : nodes) {
      recalibrator.recalibrate(n, mCurrentNumIdentifiers);
    }
  }
  
  private void recalibrateInserted(int start, int end, int offset) {
    IrNode next;
    IrBranch branch;
    int oldT, newT;
    for (int i = start; i < end; i++) {
      next = mProgram.get(i);
      if (next instanceof IrBranch) {
        branch = (IrBranch)next;
        oldT = branch.getFalseBranch();
        newT = oldT + offset;
        branch.setFalseBranch(newT);
        oldT = branch.getTrueBranch();
        newT = oldT + offset;
        branch.setTrueBranch(newT);
      } else {
        oldT = next.getNextInstr();
        newT = oldT + offset;
        next.setNextInstr(newT);
      }
    }
  }
  
  private void recalibratePre(int end, int offset) {
    IrNode next;
    IrBranch branch;
    int oldT, newT;
    for (int i = 0; i < end; i++) {
      next = mProgram.get(i);
      if (next instanceof IrBranch) {
        branch = (IrBranch)next;
        oldT = branch.getFalseBranch();
        if (oldT > end) {
          newT = oldT + offset;
          branch.setFalseBranch(newT);
        }
        oldT = branch.getTrueBranch();
        if (oldT > end) {
          newT = oldT + offset;
          branch.setTrueBranch(newT);
        }
      }
    }
  }
  
  private void recalibratePost(int postStart, int insertedStart, int offset) {
    IrNode next;
    IrBranch branch;
    int oldT, newT;
    for (int i = postStart; i < mProgram.size(); i++) {
      next = mProgram.get(i);
      if (next instanceof IrBranch) {
        branch = (IrBranch)next;
        oldT = branch.getFalseBranch();
        if (oldT > insertedStart) {
          newT = oldT + offset;
          branch.setFalseBranch(newT);
        }
        oldT = branch.getTrueBranch();
        if (oldT > insertedStart) {
          newT = oldT + offset;
          branch.setTrueBranch(newT);
        }
      } else {
        oldT = next.getNextInstr();
        if (oldT > insertedStart) {
          newT = oldT + offset;
          next.setNextInstr(newT);
        }
      }
    }
  }
}
