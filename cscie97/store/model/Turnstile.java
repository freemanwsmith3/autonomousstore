package com.cscie97.store.model;
import java.util.*;

/**
 * CSCIE-97 Assignment 2
 * @ author Ellen Siminoff
 * @ date 09/30/2021
 *
 * Turnstile.java
 * Turnstile implements the functions of an appliance. Turnstile inherits from Appliance.
 * It holds variables unique to turnstiles.
 **/
public class Turnstile extends Appliance {

	// constructor
  public Turnstile(String id, String name, DeviceType type, String storeId, String aisleId) {
    super(id, name, type, storeId, aisleId);
  }

  /** This method is used to print the info in the turnstile.
   * @return String simple text of the turnstile info
   */
  public String toString() {
    return super.toString();
  }

}
