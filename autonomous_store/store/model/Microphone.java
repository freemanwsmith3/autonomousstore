package com.cscie97.store.model;
import java.util.*;

/**
 * Microphone.java
 * Sensor implements the functions of a sensor. Microphone inherits from Sensor.
 * It holds variables unique to microwaves.
 **/
public class Microphone extends Sensor {

	// constructor
  public Microphone(String id, String name, DeviceType type, String storeId, String aisleId) {
    super(id, name, type, storeId, aisleId);
  }

  /** This method is used to print the info in the Microphone.
   * @return String simple text of the microphone info
   */
  public String toString() {
    return super.toString();
  }

}
