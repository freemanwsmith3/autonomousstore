package com.cscie97.store.controller;
import com.cscie97.store.model.*;
import java.util.*;

/**
 * CSCIE-97 Assignment 3
 * @ author Freeman Smith
 * 
 *
 * Emergency.java
 * Inherits from Command
 * It holds variables unique to emergency.
 **/
public class Emergency extends Command {

	// constructor
  public Emergency(String deviceId, String storeId, EventType type, String[] eventText) {
    super(deviceId, storeId, type, eventText);
    String emergencyType = eventText[1];
    String aisle = eventText[3].substring(0, eventText[3].length() - 1);
    addressEmergency(emergencyType, storeId, aisle);
  }

  public void addressEmergency(String emergencyType, String storeId, String aisle){
    StdOut.println("----------------");
    StdOut.println("EMERGENCY!");
    StdOut.println("Open all turnstiles");
    StdOut.println("Speakers: There is a " + emergencyType + " in " + aisle + " please leave " + storeId + " immediately!");
    StdOut.println("Robot 1: Address " + emergencyType + " in " + aisle);
    StdOut.println("Remaining Robots: Assist customers leaving " + storeId);
    StdOut.println("----------------");
  }

  /** This method is used to print the info in the sensor.
   * @return String simple text of the sensor info
   */
  public String toString() {
    return super.toString();
  }

}
