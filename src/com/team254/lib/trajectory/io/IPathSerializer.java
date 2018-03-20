package com.team254.lib.trajectory.io;

import com.team254.lib.trajectory.Path;

/**
 * Interface for methods that serialize a Path or Trajectory.
 *
 * @author Jared341
 */
interface IPathSerializer {

  String serialize(Path path);
}
