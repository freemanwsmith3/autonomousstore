package com.cscie97.store.controller;
import com.cscie97.store.model.*;
import java.util.*;

/**
 * CSCIE-97 Assignment 3
 * @ author Freeman Smith
 * 
 *
 * CleaningEvent.java
 * Inherits from Command
 * It holds variables unique to CleaningEventen.
 **/
public class CleaningEvent extends Command {

	// constructor
  public CleaningEvent(String deviceId, String storeId, EventType type, String[] eventText) {
    super(deviceId, storeId, type, eventText);
    String product = eventText[3].substring(0, eventText[3].length() - 1);
    String aisle = eventText[2];

    cleanSpill(product, aisle);
  }
  public void cleanSpill(String product, String aisle){
    StdOut.println("----------------");
    StdOut.println("Robot command: Clean up " + product + " in: " + aisle);
    StdOut.println("----------------");
  }


  /** This method is used to print the info in the sensor.
   * @return String simple text of the sensor info
   */
  public String toString() {
    return super.toString();
  }

}
