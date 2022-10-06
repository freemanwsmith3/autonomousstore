package com.cscie97.store.model;
import java.util.*;

/**
 * CSCIE-97 Assignment 2
 * @ author Ellen Siminoff
 * @ date 09/30/2021
 *
 * Speaker.java
 * Speaker implements the functions of an appliance. Speaker inherits from Appliance.
 * It holds variables unique to speakers.
 **/
public class Speaker extends Appliance {

	// constructor
  public Speaker(String id, String name, DeviceType type, String storeId, String aisleId) {
    super(id, name, type, storeId, aisleId);
  }

  /** This method is used to print the info in the speaker.
   * @return String simple text of the speaker info
   */
  public String toString() {
    return super.toString();
  }

}
