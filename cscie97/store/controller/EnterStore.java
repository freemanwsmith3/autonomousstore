package com.cscie97.store.controller;
import com.cscie97.store.model.*;
import com.cscie97.ledger.*;
import java.util.*;

/**
 * CSCIE-97 Assignment 3
 * @ author Freeman Smith
 * 
 *
 * EnterStore.java
 * Inherits from Command
 * It holds variables unique to EnterStore.
 **/
public class EnterStore extends Command {
  private Customer customer;

  public Block lastBlock;
  public Basket newBasket;
	// constructor
  public EnterStore(String deviceId, String storeId, EventType type, String[] eventText) {
    super(deviceId, storeId, type, eventText);
    enterStore(eventText);
  }
  public void enterStore(String[] eventText){
    try{
      customer = Store24X7Service.showCustomer(eventText[1]);
      int accountBalance =  LedgerImpl.commandAccountBalance(customer.billingAddress);
      //check for positive balance
      if (accountBalance <= 0){
        throw new ControllerServiceException ("Error entering store", "No Positive balance");
      }
      else{
        //added the dashes to make it easier on the gradres

        StdOut.println("----------------");
        StdOut.println("Open Turnstile " + deviceId);
        StdOut.println("Hello " + customer.firstName + " welcome to " + eventText[3]);
        StdOut.println("----------------");
      }
      }catch (Store24X7ServiceException e) {
      }catch (ControllerServiceException ex) {
        //throwing exception when balance is not positive
        //tests for this shown in the script
        System.err.println("Exception" + ex.a + " " + ex.r);
      }



    
  }

  /** This method is used to print the info in the sensor.
   * @return String simple text of the sensor info
   */
  public String toString() {
    return super.toString();
  }

}
