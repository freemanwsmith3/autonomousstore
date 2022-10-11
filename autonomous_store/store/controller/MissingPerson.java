package com.cscie97.store.controller;
import com.cscie97.store.model.*;

import java.util.*;

/**
 * CSCIE-97 Assignment 3
 * @ author Freeman Smith
 * 
 *
 * Missing Person.java
 * Inherits from Command
 * It holds variables unique to its missing persons.
 **/
public class MissingPerson extends Command {
  public Customer customer;
	// constructor
  public MissingPerson(String deviceId, String storeId, EventType type, String[] eventText) {
    super(deviceId, storeId, type, eventText);
    String customerId = eventText[2].substring(0, eventText[2].length() - 1);

    locatePerson(customerId);
  }
  public void locatePerson(String customerId){
    try{
      customer = Store24X7Service.showCustomer(customerId);

      StdOut.println("----------------");
      StdOut.println("Locate Customer " + customer.firstName + " " + customer.lastName);
      StdOut.println("Speaker: " + customer.firstName + " " + customer.lastName + " is in " + customer.aisleId);
      StdOut.println("----------------");
    }catch (Store24X7ServiceException e) {
      System.out.println(e.a);      
      System.out.println(e.r);      

    }
  }

  /** This method is used to print the info in the sensor.
   * @return String simple text of the sensor info
   */
  public String toString() {
    return super.toString();
  }

}
