package com.cscie97.store.controller;
import com.cscie97.store.model.*;
import java.util.*;

/**
 * CSCIE-97 Assignment 3
 * @ author Freeman Smith
 * 
 *
 * FetchProduct.java
 * Inherits from Command
 * It holds variables unique to FetchProduct.
 **/
public class FetchProduct extends Command {

  public int cap;
  public int amountTaken;
  private Inventory newInventory;
  private Inventory inventory;
  private Customer customer;
	// constructor
  public FetchProduct(String deviceId, String storeId, EventType type, String[] eventText) {
    super(deviceId, storeId, type, eventText);
    fetchProduct(eventText);
    
  }


  public void fetchProduct(String[] eventText){
    try{
      customer = Store24X7Service.showCustomer(eventText[1]);
    }catch (Store24X7ServiceException e) {
    }
        
    amountTaken = Integer.parseInt(eventText[7].substring(0, eventText[7].length() - 1));
    //adding these dashes to help the grader find my output in the terminal quickly    
    StdOut.println("----------------");
    StdOut.println("Robot command: Fetch " + amountTaken + " of " +  eventText[2] + " from " + eventText[6] + " and " + eventText[5] + " and bring it to " + customer.firstName + " in aisle " + customer.aisleId);
    StdOut.println("----------------");
    try{
      inventory = Store24X7Service.showInventory(eventText[3]);
      cap = inventory.capacity;
    }catch (Store24X7ServiceException e) {

    }

    if (inventory.count > amountTaken){
      try{
        newInventory = Store24X7Service.updateInventoryCount(eventText[3], (0-amountTaken));
        
      }catch (Store24X7ServiceException e) {}
    }
    else{
      try{
      StdOut.println("Robot command: Restock");
      newInventory = Store24X7Service.updateInventoryCount(eventText[3], cap);
      }catch (Store24X7ServiceException e) {}
    }
  }
  /** This method is used to print the info in the sensor.
   * @return String simple text of the sensor info
   */
  public String toString() {
    return super.toString();
  }

}
