package com.cscie97.store.model;
import java.util.*;

/**
 * CSCIE-97 Assignment 2
 * @ author Ellen Siminoff
 * @ date 09/30/2021
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
