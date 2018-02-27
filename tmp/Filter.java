
class Filter {
  public static double boundTheta0to360(double theta) {
    return theta - Math.floor(theta/360)*360;  // [0;360)
  }

  public static double boundThetaNeg360to360(double theta) {
    return theta - (Math.ceil((theta + 360)/720)-1)*720; // (-360;360]
  }

  static public void main(String[] args) {
    ValueFilter filter = new ExponentialSmoothingFilter(0.015);

    // low for a long time
    for (int i = 0; i < 10000; ++i) {
      filter.filter(0);
    }

    System.out.println("Heating up");
    // rise up for 5 seconds
    for (int i = 0; i < 5; ++i) {
      System.out.println("At " + i + " seconds: " + filter.get());
      for (int j = 0; j < 50; ++j) {
        if (filter.get() > 6) {
          System.out.println("  reduced power");
          filter.filter(6);
        } else {
          System.out.println("  full power");
          filter.filter(12 * 0.862);
        }
      }
    } 

    System.out.println("Cooling down");
    // monitor cool down
    for (int i = 0; i < 10; ++i) {
      System.out.println("At " + i + " seconds: " + filter.get());
      for (int j = 0; j < 50; ++j) {
        filter.filter(0);
      }
    }


    System.out.println("-280:" + boundThetaNeg360to360(-280));
    System.out.println("280:" + boundThetaNeg360to360(280));
    System.out.println("400:" + boundThetaNeg360to360(400));
    System.out.println("370:" + boundThetaNeg360to360(350));
  }
}

