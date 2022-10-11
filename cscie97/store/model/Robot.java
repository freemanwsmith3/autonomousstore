package com.cscie97.store.model;
import java.util.*;

/**
 *
 * Robot.java
 * Robot implements the functions of an appliance. Robot inherits from Appliance.
 * It holds variables unique to robots.
 **/
public class Robot extends Appliance {

	// constructor
  public Robot(String id, String name, DeviceType type, String storeId, String aisleId) {
    super(id, name, type, storeId, aisleId);
  }

  /** This method is used to print the info in the robot.
   * @return String simple text of the robot info
   */
  public String toString() {
    return super.toString();
  }

}
