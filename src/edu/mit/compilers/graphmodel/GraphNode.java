package edu.mit.compilers.graphmodel;

public class GraphNode {
  public int mNodeNumber;
  public String mCondition;
  public String mExpression;
  public GraphNode mPositiveEdge;
  public GraphNode mNegativeEdge;
  
  private boolean mUseNegEdge;
  
  public GraphNode(int num) {
    mNodeNumber = num;
    mUseNegEdge = false;
  }
  
  public void setUseNeg() {
    mUseNegEdge = true;
  }
  
  public boolean useNeg() {
    return mUseNegEdge;
  }
}
