package com.cscie97.store.model;
import java.util.*;

/**
 * CSCIE-97 Assignment 2
 * @ author Ellen Siminoff
 * @ date 09/30/2021
 *
 * Appliance.java
 * Appliance implements the functions of an appliance. Appliance inherits from Device.
 * It holds variables unique to its appliances.
 **/
public class Appliance extends Device {

  // constructor
  public Appliance(String id, String name, DeviceType type, String storeId, String aisleId) {
    super(id, name, type, storeId, aisleId);
  }

  /** This method is used to print the info in the appliance.
   * @return String simple text of the appliance info
   */
  public String toString() {
    return super.toString();
  }

}
