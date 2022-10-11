package com.cscie97.store.controller;
import com.cscie97.store.model.*;
import java.util.*;

/**
 * CSCIE-97 Assignment 3
 * @ author Freeman Smith
 * 
 *
 * BasketEvent.java
 * Inherits from Command
 * It holds variables unique to BasketEvent.
 **/
public class BasketEvent extends Command {
  public Customer customer;
  public Basket basket;
  private Inventory inventory;
  private Inventory newInventory;
	// constructor
  public BasketEvent(String deviceId, String storeId, EventType type, String[] eventText) {
    super(deviceId, storeId, type, eventText);

    int count = Integer.parseInt(eventText[7].substring(0, eventText[7].length() - 1)); 
    if (count > 0){
      addBasketItem(eventText, count);
    }else {    
      removeBasketItem(eventText, count);
    }
  }
     
  public void addBasketItem(String[] eventText, int count){
    try{
      customer = Store24X7Service.showCustomer(eventText[1]);

      //this method adds to the customers basket and removes it from the inventory 
      basket = Store24X7Service.addProductToBasket(customer.basketId, eventText[2], count);
      StdOut.println("----------------");
      StdOut.println("Robot command: Perform restock in aisle: " + eventText[5] + " on shelf: " + eventText[4]);
      StdOut.println("----------------");
    }catch (Store24X7ServiceException e) {
      System.out.println(e.a);      
      System.out.println(e.r);      
    }
    }


    public void removeBasketItem(String[] eventText, int count){
      try{
        customer = Store24X7Service.showCustomer(eventText[1]);
  
        //this method adds to the customers basket and removes it from the inventory 
        basket = Store24X7Service.removeProductFromBasket(customer.basketId, eventText[2], count);

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
