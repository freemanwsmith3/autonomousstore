package com.cscie97.store.controller;
import com.cscie97.ledger.*;
import com.cscie97.store.model.*;
import java.util.*;

/**
 * CSCIE-97 Assignment 3
 * @ author Freeman Smith
 * 
 *
 * CheckAccountBalance.java
 * Inherits from Command
 * It holds variables unique to CheckAccountBalance.
 **/
public class CheckAccountBalance extends Command {
  private Customer customer;
  public Basket basket;
	// constructor
  public CheckAccountBalance(String deviceId, String storeId, EventType type, String[] eventText) {
    super(deviceId, storeId, type, eventText);

  }
  
  public void checkAccountBalance(String[] eventText){
    try{
      customer = Store24X7Service.showCustomer(eventText[1]);
      int accountBalance =  LedgerImpl.commandAccountBalance(customer.billingAddress);
      basket = Store24X7Service.getBasket(customer.basketId);

      //adding dashes to make it easier on the grader
      StdOut.println("----------------");
      if (basket.valueOfBasket < accountBalance){
      StdOut.println("Speaker: your account balance is  " + accountBalance + " which is more than " + basket.valueOfBasket);
      StdOut.println("----------------");
      }else{
        StdOut.println("Speaker: your account balance is  " + accountBalance + " which is less than " + basket.valueOfBasket);
        StdOut.println("----------------");
      }
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
