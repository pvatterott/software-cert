package edu.mit.compilers.graphmodel;

public class AdjacenyMatrix {
  private int[][] mLinks;
  private int mNumNodes;
  
  private final String NAN = "NaN";
  private final int NUM_COLS = 2;
  
  public AdjacenyMatrix(int numNodes) {
    mNumNodes = numNodes;
    mLinks = new int[mNumNodes][NUM_COLS];
    for (int i = 0; i < mNumNodes; i++) {
      for (int j = 0; j < NUM_COLS; j++) {
        mLinks[i][j] = -1;
      }
    }
  }
  
  public void addNonJumpLink(int nodeNum, int target) {
    mLinks[nodeNum][0] = target;
  }
  
  public void addJumpLink(int nodeNum, int trueTarget, int falseTarget) {
    mLinks[nodeNum][0] = trueTarget;
    mLinks[nodeNum][1] = falseTarget;
  }
  
  public String[][] getCsvFormat() {
    String[][] out = new String[mNumNodes][2];
    for (int i = 0; i < mNumNodes; i++) {
      for (int j = 0; j < NUM_COLS; j++) {
        if (mLinks[i][j] == -1) {
          out[i][j] = NAN;
        } else {
          out[i][j] = Integer.toString(mLinks[i][j]);
        }
      }
    }
    
    return out;
  }
}
