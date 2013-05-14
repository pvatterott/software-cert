package edu.mit.compilers.graphmodel;

public class Bound {
  private int mIntLower, mIntUpper;
  private double mDoubleLower, mDoubleUpper;
  private boolean mIsDouble;
  
  public Bound(int lower, int upper) {
    setIntBounds(lower, upper);
  }
  
  public Bound(double lower, double upper) {
    setDoubleBounds(lower, upper);
  }
  
  public int getIntLower() {
    return mIntLower;
  }
  
  public void setIntBounds(int lower, int upper) {
    mIntLower = lower;
    mIntUpper = upper;
    mIsDouble = false;
  }
  
  public int getIntUpper() {
    return mIntUpper;
  }
  
  public void setDoubleBounds(double lower, double upper) {
    mDoubleLower = lower;
    mDoubleUpper = upper;
    mIsDouble = true;
  }
  
  public double getDoubleLower() {
    return mDoubleLower;
  }
  
  public double getDoubleUpper() {
    return mDoubleUpper;
  }
  
  public boolean holdsDoubleBounds() {
    return mIsDouble;
  }
  
  public Bound add(Bound other) {
    if (mIsDouble) {
      double upper, lower;
      lower = mDoubleLower + other.mDoubleLower;
      upper = mDoubleUpper + other.mDoubleUpper;
      return new Bound(lower, upper);
    } else {
      int upper, lower;
      lower = mIntLower + other.mIntLower;
      upper = mIntUpper + other.mIntUpper;
      return new Bound(lower, upper);
    }
  }
  
  public Bound sub(Bound other) {
    Bound newOther;
    if (mIsDouble) {
      newOther = new Bound(-other.mDoubleUpper, -other.mDoubleLower);
    } else {
      newOther = new Bound(-other.mIntUpper, -other.mIntLower);
    }
    return this.add(newOther);
  }
  
  public Bound mul(Bound other) {
    if (mIsDouble) {
      double[] v = new double[4];
      v[0] = mDoubleLower * other.mDoubleLower;
      v[1] = mDoubleLower * other.mDoubleUpper;
      v[2] = mDoubleUpper * other.mDoubleLower;
      v[3] = mDoubleUpper * other.mDoubleUpper;
      double min = Double.MAX_VALUE;
      double max = Double.MIN_VALUE;
      for (int i = 0; i < 4; i++) {
        if (v[i] > max)
          max = v[i];
        if (v[i] < min)
          min = v[i];
      }
      return new Bound(min, max);
    } else {
      int[] v = new int[4];
      v[0] = mIntLower * other.mIntLower;
      v[1] = mIntLower * other.mIntUpper;
      v[2] = mIntUpper * other.mIntLower;
      v[3] = mIntUpper * other.mIntUpper;
      int min = Integer.MAX_VALUE;
      int max = Integer.MIN_VALUE;
      for (int i = 0; i < 4; i++) {
        if (v[i] > max)
          max = v[i];
        if (v[i] < min)
          min = v[i];
      }
      return new Bound(min, max);
    }
  }
  
  public Bound div(Bound other) {
    // TODO : find value in ranges closest to 0, farthest from 0
    if (mIsDouble) {
      return new Bound(Double.MIN_VALUE, Double.MAX_VALUE);
    } else {
      return new Bound(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
  }
  
  @Override
  public String toString() {
    StringBuilder out = new StringBuilder();
    out.append("[");
    if (mIsDouble) {
      out.append(Double.toString(mDoubleLower));
      out.append(":");
      out.append(Double.toString(mDoubleUpper));
    } else {
      out.append(Integer.toString(mIntLower));
      out.append(":");
      out.append(Integer.toString(mIntUpper));
    }
    out.append("]");
    return out.toString();
  }
}
