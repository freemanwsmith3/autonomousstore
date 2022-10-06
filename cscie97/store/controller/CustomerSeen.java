package com.cscie97.store.controller;
import com.cscie97.store.model.*;
import java.util.*;

/**
 * CSCIE-97 Assignment 3
 * @ author Freeman Smith
 * 
 *
 * CustomerSeen.java
 * Inherits from Command
 * It holds variables unique to CustomerSeen.
 **/
public class CustomerSeen extends Command {
  private Customer customer;
	// constructor
  public CustomerSeen(String deviceId, String storeId, EventType type, String[] eventText) {
    super(deviceId, storeId, type, eventText);
    String customerId = eventText[1];
    String aisle =  eventText[3].substring(0, eventText[3].length() - 1);
    updateCustomerLocation(customerId, storeId, aisle);
  }

  public void updateCustomerLocation(String customerId, String storeId, String aisle){
    //updates customer location 
    //no need for output
    try{
      customer = Store24X7Service.updateCustomerLocation(customerId, storeId, aisle);
    }catch (Store24X7ServiceException e) {
    }
  }

  /** This method is used to print the info in the sensor.
   * @return String simple text of the sensor info
   */
  public String toString() {
    return super.toString();
  }

}
