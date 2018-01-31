## Points of Interest

### Framework
Glitch is a [command based](https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599732-what-is-command-based-programming) robot. Well truthfully Glitch is a gleam in designs CAD files, and this is just source code for a robot we might see some day, that happens to be tested on the same practice bot that spawned the World Champion Valkyrie.

### Tools
Much like Valkyrie we are using [RobotBuilder](https://wpilib.screenstepslive.com/s/currentCS/m/robotbuilder), which is a great way to organize our code. It is not perfect, it would be nice if RobotMap only had our subsystems and hardware was initalized and owned directly by the subsystems. We could do that, but instead we pretend that is the case and only access hardware via subsystems, and use RobotBuilder because it is more consistant than 15 freshmen and their laptops.

We are also using [GradleRIO](https://github.com/Open-RIO/GradleRIO) this year, which has been wonderful.

### Credit
Also much like Valkyrie we continue to learn from [great](https://github.com/strykeforce) [teams](https://github.com/Team254). In particular we use a lot of code from the Cheesy Poofs, and it was great getting to talk with some of them last year, bummer they won't be in Detroit this year. 

### Cheesy Path -> Lightning Path
Okay the heart and soul of Lightning Path belongs to 254, we just hit it with some [Lightning](http://lightningrobotics.com/). 254's code last year included a cool web based GUI for tracking waypoints that they used for their adaptive pursuit auton code. We started investigating adaptive pursuit too late to feel safe utilizing it this year (at least for now), so we fell back to using 254's [trajectory code from 2014](https://github.com/frc-862/glitch/tree/master/src/com/team254/lib/trajectory) which we have used successfully in past. But we missed their cool GUI tool, so a little hacking later, [Lightning Path](https://frc-862.github.io/glitch/light_path/) was born.

