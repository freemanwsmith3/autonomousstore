package com.cscie97.store.model;
import java.util.*;

/**
 *
 * Sensor.java
 * Sensor implements the functions of a sensor. Sensor inherits from Device.
 * It holds variables unique to its sensors.
 **/
public class Sensor extends Device {

	// constructor
  public Sensor(String id, String name, DeviceType type, String storeId, String aisleId) {
    super(id, name, type, storeId, aisleId);
  }

  /** This method is used to print the info in the sensor.
   * @return String simple text of the sensor info
   */
  public String toString() {
    return super.toString();
  }

}
