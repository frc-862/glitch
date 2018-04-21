package com.team254.lib.trajectory;

/**
 * Base class for an autonomous path.
 * 
 * @author Jared341
 */
public class Path {
  protected Trajectory.Pair go_left_pair_;
  protected Trajectory original;
  protected String name_;
  protected boolean go_left_;

  public Path(String name, Trajectory.Pair go_left_pair) {
    name_ = name;
    go_left_pair_ = go_left_pair;
    go_left_ = true;
  }

  public Path(String name, Trajectory.Pair go_left_pair, Trajectory orig) {
    name_ = name;
    go_left_pair_ = go_left_pair;
    go_left_ = true;
    original = orig;
  }

  public Trajectory getOriginal() { return original; }

  public Path() {
    
  }
  
  public String getName() { return name_; }
  
  public void goLeft() { 
    go_left_ = true; 
    go_left_pair_.left.setInvertedY(false);
    go_left_pair_.right.setInvertedY(false);
  }
  
  public void goRight() {
    go_left_ = false; 
    go_left_pair_.left.setInvertedY(true);
    go_left_pair_.right.setInvertedY(true);
  }
  
  public Trajectory getLeftWheelTrajectory() {
    return (go_left_ ? go_left_pair_.left : go_left_pair_.right);
  }
  
  public Trajectory getRightWheelTrajectory() {
    return (go_left_ ? go_left_pair_.right : go_left_pair_.left);
  }
  
  public Trajectory.Pair getPair() {
    return go_left_pair_;
  }

  public double getEndHeading() {
    int numSegments = getLeftWheelTrajectory().getNumSegments();
    Trajectory.Segment lastSegment = getLeftWheelTrajectory().getSegment(numSegments - 1);
    return lastSegment.heading;
  }

    public void reverse() {
        go_left_pair_ = go_left_pair_.reverse();
    }
}
