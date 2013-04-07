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
          int externalOffset = internalOffset + instructionsToInsert.size();
          recalibrateJumps(internalOffset, externalOffset, internalOffset);
          recalibrateJumps(externalOffset, mProgram.size(), instructionsToInsert.size());
          break;
        }
      }
      internalOffset++;
    }
    
    
    return wasInlined;
  }
  
  private List<IrNode> getCodeToInsert(List<IrNode> calledFunction, IrIdentifier target, List<IrExpression> inParams, List<IrDeclaration> declaredParams) {
    List<IrNode> outInstructions = new ArrayList<IrNode>();
    List<IrJmp> insertedJumps = new ArrayList<IrJmp>();
    
    int numDecs = inParams.size();
    IrIdentifier passedIn, assigned;
    IrAssignment assignment;
    for (int i = 0; i < numDecs; i++) {
      passedIn = (IrIdentifier)inParams.get(i);
      assigned = declaredParams.get(i).getName();
      assignment = new IrAssignment(assigned, passedIn);
      outInstructions.add(assignment);
    }
    
    int oldI, newI;
    IrJmp jmp;
    IrReturn ret;
    for (IrNode n : calledFunction) {
      if (n instanceof IrReturn) {
        ret = (IrReturn)n;
        assignment = new IrAssignment(target, ret.getExpr());
        outInstructions.add(assignment);
        jmp = new IrJmp(-1);
        outInstructions.add(jmp);
        insertedJumps.add(jmp);
      } else if  (n instanceof IrJmp) {
        jmp = (IrJmp)n;
        oldI = jmp.getTarget();
        newI = oldI + numDecs;
        jmp.setTarget(newI);
      } else {
        outInstructions.add(n);
      }
    }
    
    int jmpTarget = outInstructions.size();
    for (IrJmp j : insertedJumps) {
      j.setTarget(jmpTarget);
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
  
  private void recalibrateJumps(int start, int end, int offset) {
    IrNode next;
    IrJmp jmp;
    int oldTarget, newTarget;
    for (int i = start; i < end; i++) {
      next = mProgram.get(i);
      if (next instanceof IrJmp) {
        jmp = (IrJmp)next;
        oldTarget = jmp.getTarget();
        newTarget = oldTarget + offset;
        jmp.setTarget(newTarget);
      }
    }
  }
}
