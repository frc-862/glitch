package com.team254.lib.trajectory;

/**
 * Generate a smooth Trajectory from a Path.
 *
 * @author Art Kalb
 * @author Stephen Pinkerton
 * @author Jared341
 */
public class PathGenerator {
  /**
   * Generate a path for autonomous driving. 
   * 
   * @param waypoints The waypoints to drive to (FOR THE "GO LEFT" CASE!!!!)
   * @param config Trajectory config.
   * @param wheelbase_width Wheelbase separation; units must be consistent with
   * config and waypoints.
   * @param name The name of the new path.  THIS MUST BE A VALID JAVA CLASS NAME
   * @return The path.
   */
  public static Path makePath(WaypointSequence waypoints, 
          TrajectoryGenerator.Config config, double wheelbase_width, 
          String name) {
    Trajectory base = generateFromPath(waypoints, config);
    return new Path(name,
            generateLeftAndRightFromSeq(waypoints, config, wheelbase_width), base);
  }

  static Trajectory.Pair generateLeftAndRightFromSeq(WaypointSequence path,
          TrajectoryGenerator.Config config, double wheelbase_width) {
      Trajectory base = generateFromPath(path, config);
    return makeLeftAndRightTrajectories(base, wheelbase_width);
  }

  public static Trajectory generateFromPath(WaypointSequence path,
                                            TrajectoryGenerator.Config config) {
    final int size = path.getNumWaypoints();
    if (size < 2) {
      return null;
    }

    final int last = size - 1;
    double start_vel = 0;

    if (path.getWaypoint(0).velocity != null) {
      start_vel = path.getWaypoint(0).velocity.doubleValue();
    }

    if (path.getWaypoint(last).velocity == null) {
      path.getWaypoint(last).velocity = new Double(0);
    }


    Trajectory result = null;
    int start = 0;
    double start_pos = 0;
    int stop = 1;

    while (start < last) {
      // find next waypoint with a non-null end velocity
      while (path.getWaypoint(stop).velocity == null && stop < size) {
        ++stop;
      }

      double end_vel = path.getWaypoint(stop).velocity.doubleValue();
      WaypointSequence seq = path.slice(start, stop);
      if (result != null) {
        seq.getWaypoint(0).theta = result.getSegment(result.getNumSegments() - 1).heading;
        seq.getWaypoint(0).x = result.getSegment(result.getNumSegments() - 1).x;
        seq.getWaypoint(0).y = result.getSegment(result.getNumSegments() - 1).y;
      }
      Trajectory traj = generateFromPath(seq, config, start_vel, end_vel, start_pos);

      if (result == null) {
        result = traj;
      } else {
        // update the position
        double offset = result.getSegment(result.getNumSegments() - 2).pos;
        for (int i = 0; i < traj.getNumSegments(); ++i) {
          traj.getSegment(i).pos += offset;
        }

        result.append(traj);
      }

      start = stop;
      Trajectory.Segment seg = traj.getSegment(traj.getNumSegments() - 1);
      start_pos = 0; // seg.pos; // + (0.02 * seg.vel);
      stop = stop + 1;
      start_vel = seg.vel;
    }

    return result;
  }

   public static Trajectory generateFromPath(WaypointSequence path,
          TrajectoryGenerator.Config config, double start_vel, double end_vel, double start_pos) {
    if (path.getNumWaypoints() < 2) {
      return null;
    }

    // Compute the total length of the path by creating splines for each pair
    // of waypoints.
    Spline[] splines = new Spline[path.getNumWaypoints() - 1];
    double[] spline_lengths = new double[splines.length];
    double total_distance = 0;
    for (int i = 0; i < splines.length; ++i) {
      splines[i] = new Spline();
      if (!Spline.reticulateSplines(path.getWaypoint(i),
              path.getWaypoint(i + 1), splines[i], Spline.QuinticHermite)) {
        return null;
      }
      spline_lengths[i] = splines[i].calculateLength();
      total_distance += spline_lengths[i];
    }

    // Generate a smooth trajectory over the total distance.
    Trajectory traj = TrajectoryGenerator.generate(config,
            TrajectoryGenerator.AutomaticStrategy, start_vel, path.getWaypoint(0).theta,
            total_distance, end_vel, path.getWaypoint(path.getNumWaypoints() - 1).theta, start_pos);

    // Assign headings based on the splines.
    int cur_spline = 0;
    double cur_spline_start_pos = 0;
    double length_of_splines_finished = 0;
    for (int i = 0; i < traj.getNumSegments(); ++i) {
      double cur_pos = traj.getSegment(i).pos;

      boolean found_spline = false;
      while (!found_spline) {
        double cur_pos_relative = cur_pos - cur_spline_start_pos;
        if (cur_pos_relative <= spline_lengths[cur_spline]) {
          double percentage = splines[cur_spline].getPercentageForDistance(
                  cur_pos_relative);
          traj.getSegment(i).heading = splines[cur_spline].angleAt(percentage);
          double[] coords = splines[cur_spline].getXandY(percentage);
          traj.getSegment(i).x = coords[0];
          traj.getSegment(i).y = coords[1];
          found_spline = true;
        } else if (cur_spline < splines.length - 1) {
          length_of_splines_finished += spline_lengths[cur_spline];
          cur_spline_start_pos = length_of_splines_finished;
          ++cur_spline;
        } else {
          // new option, trim traj and return from here

          traj.getSegment(i).heading = splines[splines.length - 1].angleAt(1.0);
          double[] coords = splines[splines.length - 1].getXandY(1.0);
          traj.getSegment(i).x = coords[0];
          traj.getSegment(i).y = coords[1];
          found_spline = true;

          traj.truncateTo(i+1);
          return traj;
        }
      }
    }

    return traj;
  }

  /**
   * Generate left and right wheel trajectories from a reference.
   *
   * @param input The reference trajectory.
   * @param wheelbase_width The center-to-center distance between the left and
   * right sides.
   * @return [0] is left, [1] is right
   */
  public static Trajectory.Pair makeLeftAndRightTrajectories(Trajectory input,
          double wheelbase_width) {
    Trajectory[] output = new Trajectory[2];
    output[0] = input.copy();
    output[1] = input.copy();
    Trajectory left = output[0];
    Trajectory right = output[1];

    for (int i = 0; i < input.getNumSegments(); ++i) {
      Trajectory.Segment current = input.getSegment(i);
      double cos_angle = Math.cos(current.heading);
      double sin_angle = Math.sin(current.heading);

      Trajectory.Segment s_left = left.getSegment(i);
      s_left.x = current.x - wheelbase_width / 2 * sin_angle;
      s_left.y = current.y + wheelbase_width / 2 * cos_angle;
      adjustSide(left, i, s_left);

      Trajectory.Segment s_right = right.getSegment(i);
      s_right.x = current.x + wheelbase_width / 2 * sin_angle;
      s_right.y = current.y - wheelbase_width / 2 * cos_angle;
      adjustSide(right, i, s_right);
    }

    return new Trajectory.Pair(output[0], output[1]);
  }

  private static void adjustSide(Trajectory trajectory, int index, Trajectory.Segment segment) {
    if (index > 0) {
      // Get distance between current and last segment
      double dist = Math.sqrt((segment.x - trajectory.getSegment(index - 1).x)
              * (segment.x - trajectory.getSegment(index - 1).x)
              + (segment.y - trajectory.getSegment(index - 1).y)
              * (segment.y - trajectory.getSegment(index - 1).y));
      segment.pos = trajectory.getSegment(index - 1).pos + dist;
      segment.vel = dist / segment.dt;
      segment.acc = (segment.vel - trajectory.getSegment(index - 1).vel) / segment.dt;
      segment.jerk = (segment.acc - trajectory.getSegment(index - 1).acc) / segment.dt;
    }
  }
}
