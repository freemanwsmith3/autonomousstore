package com.cscie97.store.controller;
import com.cscie97.store.model.*;
import java.util.*;

/**
 * CSCIE-97 Assignment 3
 * @ author Freeman Smith
 * 
 *
 * BrokenGlass.java
 * Inherits from Command
 * It holds variables unique to BrokenGlass.
 **/
public class BrokenGlass extends Command {

	// constructor
  public BrokenGlass(String deviceId, String storeId, EventType type, String[] eventText) {
    super(deviceId, storeId, type, eventText);

    String aisle = eventText[2].substring(0, eventText[2].length() - 1);
    cleanGlass(aisle);
  }
  public void cleanGlass(String aisle){
    StdOut.println("----------------");
    StdOut.println("Robot command: Clean up broken glass in: " + aisle);
    StdOut.println("----------------");
  }

  

  /** This method is used to print the info in the sensor.
   * @return String simple text of the sensor info
   */
  public String toString() {
    return super.toString();
  }

}
