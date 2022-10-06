package com.cscie97.store.controller;
import com.cscie97.store.model.*;
import com.cscie97.ledger.*;
import java.util.*;

/**
 * CSCIE-97 Assignment 3
 * @ author Freeman Smith
 * 
 *
 * Checkout.java
 * Inherits from Command
 * It holds variables unique to Checkout.
 **/
public class Checkout extends Command {
  private Customer customer;
  public Basket basket;
	// constructor
  public Checkout(String deviceId, String storeId, EventType type, String[] eventText) {

    super(deviceId, storeId, type, eventText);
    checkout(eventText);
  }
  public void checkout(String[] eventText){
    try{
      customer = Store24X7Service.showCustomer(eventText[1]);
      basket = Store24X7Service.getBasket(customer.basketId);
      int accountBalance =  LedgerImpl.commandAccountBalance(customer.billingAddress);
      StdOut.println("----------------");
      if (basket.valueOfBasket < accountBalance){
        //double value =  basket.valueOfBasket;
        //int fee = value/10;
        basket = Store24X7Service.getBasket(customer.basketId);
  
        //processing a transaction. Going to go back and figure out how to get the latest transaciton number, 100 is a placeholder
        //the amount is the value of the basket
        //the ten percent fee is the amount times .1
        //automatic note generated
        //the customer is the payer
        //the master is the receiver
        StdOut.println("----------------");
        StdOut.println("Goodbye: " + customer.firstName + ", thanks for shopping at " + eventText[2] + "!");
        //please see my attempt to process the transaction below. I kept getting issues with variables not beinging able to be referenced from a static context
        // this is something i need to ask in office hours
        //I got everything else to work besides this though

        //String transactionId = LedgerImpl.processTransaction("100", basket.valueOfBasket, fee, "Automatic Transaction from turnstile", customer.id, "master");
        
        //Demonstrating exception handling
      }else{
        throw new ControllerServiceException ("Error checking out", "No Positive balance");
      }
      StdOut.println("----------------");
      }catch (Store24X7ServiceException e) {
      }
      catch (ControllerServiceException ex) {
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
