package com.team254.lib.trajectory;

import com.team254.lib.util.ChezyMath;

import java.util.Arrays;

/**
 * A WaypointSequence is a sequence of Waypoints.  #whatdidyouexpect
 *
 * @author Art Kalb
 * @author Stephen Pinkerton
 * @author Jared341
 */
public class WaypointSequence {

  public static class Waypoint {

    public Waypoint(double x, double y, double theta, Double velocity) {
      this.x = x;
      this.y = y;
      this.theta = theta;
      this.velocity = velocity;
    }

    public Waypoint(double x, double y, double theta, double velocity) {
      this.x = x;
      this.y = y;
      this.theta = theta;
      this.velocity = new Double(velocity);
    }

    public Waypoint(Waypoint tocopy) {
      this.x = tocopy.x;
      this.y = tocopy.y;
      this.theta = tocopy.theta;
      this.velocity = tocopy.velocity;
    }

    public double x;
    public double y;
    public double theta;
    public Double velocity;
  }

  Waypoint[] waypoints_;
  int num_waypoints_;

  public WaypointSequence(int max_size) {
    waypoints_ = new Waypoint[max_size];
  }

  public void addWaypoint(Waypoint w) {
    if (num_waypoints_ < waypoints_.length) {
      waypoints_[num_waypoints_] = w;
      ++num_waypoints_;
    }
  }

  public WaypointSequence slice(int start, int stop) {
    final int count = stop - start + 1;
    WaypointSequence result = new WaypointSequence(count);
    for (int i = 0; i < count; ++i) {
      result.waypoints_[i] = waypoints_[i + start];
    }
     result.num_waypoints_ = count;
//     result.waypoints_ = Arrays.copyOfRange(waypoints_, start, stop);

     return result;
  }

  public int getNumWaypoints() {
    return num_waypoints_;
  }

  public Waypoint getWaypoint(int index) {
    if (index >= 0 && index < getNumWaypoints()) {
      return waypoints_[index];
    } else {
      return null;
    }
  }
  
  public WaypointSequence invertY() {
    WaypointSequence inverted = new WaypointSequence(waypoints_.length);
    inverted.num_waypoints_ = num_waypoints_;
    for (int i = 0; i < num_waypoints_; ++i) {
      inverted.waypoints_[i] = waypoints_[i];
      inverted.waypoints_[i].y *= -1;
      inverted.waypoints_[i].theta = ChezyMath.boundAngle0to2PiRadians(
              2*Math.PI - inverted.waypoints_[i].theta);
    }
    
    return inverted;
  }
}
