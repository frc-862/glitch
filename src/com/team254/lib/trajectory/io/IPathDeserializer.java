package com.team254.lib.trajectory.io;

import com.team254.lib.trajectory.Path;

/**
 * Interface for methods that deserializes a Path or Trajectory.
 * 
 * @author Jared341
 */
interface IPathDeserializer {
  
  Path deserialize(String serialized);
}
