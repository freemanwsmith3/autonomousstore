package com.cscie97.store.controller;
import com.cscie97.store.model.*;
import java.util.*;

/**
 * CSCIE-97 Assignment 3
 * @ author Freeman Smith
 * 
 *
 * AssistCustomer.java
 * Inherits from Command
 * It holds variables unique to AssistCustomer.
 **/
public class AssistCustomer extends Command {

	// constructor
  public AssistCustomer(String deviceId, String storeId, EventType type, String[] eventText) {
    super(deviceId, storeId, type, eventText);

    String customerId = eventText[1].substring(0, eventText[1].length() - 1);
    assistCustomer(customerId);
  }
  public void assistCustomer(String customerId){
    StdOut.println("----------------");
    StdOut.println("Robot command: Assist customer " + customerId + " to car");
    StdOut.println("----------------");
  }
  /** This method is used to print the info in the sensor.
   * @return String simple text of the sensor info
   */
  public String toString() {
    return super.toString();
  }

}
