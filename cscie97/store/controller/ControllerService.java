package com.cscie97.store.controller;
import com.cscie97.store.model.*;
import java.util.*;


public class ControllerService {
  public static Command command;
  //private Command command;
  // objects for classes that will be instantiated


  // maps to hold objects for easy lookup

  /** Constructor
   *  Exists to instantiate the Service
   */
  public ControllerService() {

  }
    /** This method is used to Invoke commands received from the observer pattern. StoreIds must be unique.

   */

   //change string and return to command
  public static void invokeCommand(Event event) {

    if (event.type.equals(EventType.EMERGENCY)) {
      command = new Emergency(event.deviceId, event.storeId, event.type, event.eventText);
    } else if (event.type.equals(EventType.BASKET_EVENT)) {
      command = new BasketEvent(event.deviceId, event.storeId, event.type, event.eventText);
    }else if (event.type.equals(EventType.CLEANING_EVENT)) {
      command = new CleaningEvent(event.deviceId, event.storeId, event.type, event.eventText);
    }else if (event.type.equals(EventType.BROKEN_GLASS)) {
      command = new BrokenGlass(event.deviceId, event.storeId, event.type, event.eventText);
    }else if (event.type.equals(EventType.MISSING_PERSON)) {
      command = new MissingPerson(event.deviceId, event.storeId, event.type, event.eventText);
    }else if (event.type.equals(EventType.CUSTOMER_SEEN)) {
      command = new CustomerSeen(event.deviceId, event.storeId, event.type, event.eventText);
    }else if (event.type.equals(EventType.FETCH_PRODUCT)) {
      command = new FetchProduct(event.deviceId, event.storeId, event.type, event.eventText);
    }else if (event.type.equals(EventType.CHECK_ACCOUNT_BALANCE)) {
      command = new CheckAccountBalance(event.deviceId, event.storeId, event.type, event.eventText);
    }else if (event.type.equals(EventType.ASSIST_CUSTOMER_TO_CAR)) {
      command = new AssistCustomer(event.deviceId, event.storeId, event.type, event.eventText);
    }else if (event.type.equals(EventType.CHECKOUT)) {
      command = new Checkout(event.deviceId, event.storeId, event.type, event.eventText);
    }else if (event.type.equals(EventType.ENTER_STORE)) {
      command = new EnterStore(event.deviceId, event.storeId, event.type, event.eventText);
      } 
    }
}