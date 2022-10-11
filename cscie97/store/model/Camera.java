package com.cscie97.store.model;
import java.util.*;

/**
 *
 * Camera.java
 * Sensor implements the functions of a sensor. Camera inherits from Sensor.
 * It holds variables unique to cameras.
 **/
public class Camera extends Sensor {

	// constructor
  public Camera(String id, String name, DeviceType type, String storeId, String aisleId) {
    super(id, name, type, storeId, aisleId);
  }

  /** This method is used to print the info in the camera.
   * @return String simple text of the camera info
   */
  public String toString() {
    return super.toString();
  }

}
