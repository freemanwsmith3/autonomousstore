package com.cscie97.store.model;
import com.cscie97.ledger.*;
import java.io.*;
import java.util.*;

/**
 * CSCIE-97 Assignment 2
 * @ author Ellen Siminoff
 * @ date 09/30/2021
 *
 * CommandProcessor.java
 * CommandProcessor Class exists to feed the Store24X7Service a set of operations.
 **/
public class CommandProcessor {

  private Store24X7Service service;
  private Store store1;

  //using variables in both methods
  private String line = "";
  private int number = 0;

  //simply a placeholder until we do authentication
  private boolean authenticatedUser = true;

  /** This method is used to create process commands.
   * @param command a String giving the command
   * @exception CommandProcessorException for errors
   */
  public void processCommand(String command) throws CommandProcessorException {

    if (!(authenticatedUser)) {
      throw new CommandProcessorException("user", "Not an authenticated user", 0);
    }

    String[] words = command.split("[\\s:]+");

    service = new Store24X7Service();

    if (words[0].equalsIgnoreCase("define-store")) {
      int wordLength = words.length;
      String identifier = words[1];
      String name = words[3];
      String address = "";
      for (int i = 5; i < words.length; i++) {
        address += words[i] + " ";
      }

      try {

        store1 = service.defineStore(identifier, name, address);

        com.cscie97.ledger.test.TestDriver.main(new String[]{"ledger.script"});
        StdOut.println("Store: " + store1);
      } catch (Store24X7ServiceException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      }catch (Exception e){ 
      }

    }

    if (words[0].equalsIgnoreCase("show-store")) {

      String identifier = words[1];
      try {
        StdOut.println("Store: " + service.showStore(identifier));
      } catch (Store24X7ServiceException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      } catch (StoreException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      }
    }

    if (words[0].equalsIgnoreCase("define-aisle")) {
      String storeIdentifier = words[1];
      String aisleIdentifier = words[2]; // colon issue
      String name = words[4];
      String description = words[6];
      String location = words[8].toUpperCase();

      try {
        Aisle aisle1 = service.showStore(storeIdentifier).defineAisle(aisleIdentifier, name, description, Location.valueOf(location));
        StdOut.println("Aisle : " + aisle1);
      } catch (Store24X7ServiceException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      } catch (StoreException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      } catch(IllegalArgumentException e){
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getMessage(), number);
      }
    }

    if (words[0].equalsIgnoreCase("show-aisle")) {
      String storeIdentifier = words[1];
      String aisleIdentifier = words[2];
      try {
        StdOut.println("Aisle: " + service.showStore(storeIdentifier).showAisle(storeIdentifier, aisleIdentifier));
      } catch (Store24X7ServiceException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      } catch (StoreException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      }
    }

    if (words[0].equalsIgnoreCase("define-shelf")) {
      Shelf shelf1;
      String storeIdentifier = words[1];
      String aisleIdentifier = words[2]; // colon issue
      String shelfIdentifier = words[3];
      String name = words[5];
      Level lev;
      //error handling if incorrect - should do illegal argument exception
      try {
        lev = Level.valueOf(words[7].toUpperCase());
      } catch (IllegalArgumentException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(),"Not a valid level", number);
      }

      String description = words[9];
      try {
        if (words.length == 12) {
          Temperature temp = Temperature.valueOf(words[11].toUpperCase());
          shelf1 = service.showStore(storeIdentifier).defineShelf(shelfIdentifier, name, lev, description, temp, aisleIdentifier);
        } else {
          shelf1 = service.showStore(storeIdentifier).defineShelf(shelfIdentifier, name, lev, description, aisleIdentifier);
        }
      } catch (Store24X7ServiceException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      } catch (StoreException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      } catch (IllegalArgumentException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getMessage(), number);
      }

      StdOut.println("Shelf: " + shelf1);
    }

    if (words[0].equalsIgnoreCase("show-shelf")) {
      String storeIdentifier = words[1];
      String aisleIdentifier = words[2];
      String shelfIdentifier = words[3];
      try {
        Shelf shelf1 = service.showStore(storeIdentifier).showShelf(storeIdentifier, aisleIdentifier, shelfIdentifier);
        StdOut.println("Shelf: " + shelf1);
      } catch (Store24X7ServiceException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      } catch (StoreException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      }

    }

    if (words[0].equalsIgnoreCase("define-product")) {
      int fieldDescriptionStart = 0;
      int fieldSizeStart = 0;
      int fieldCategoryStart = 0;
      int fieldPriceStart = 0;
      int fieldTemperatureStart = 0;

      for (int i = 0; i < words.length; i++) {
        if (words[i].equalsIgnoreCase("description")) {
          fieldDescriptionStart = i;
        }
        if (words[i].equalsIgnoreCase("size")) {
          fieldSizeStart = i;
        }
        if (words[i].equalsIgnoreCase("category")) {
          fieldCategoryStart = i;
        }
        if (words[i].equalsIgnoreCase("unit_price")) {
          fieldPriceStart = i;
        }
        if (words[i].equalsIgnoreCase("temperature")) {
          fieldTemperatureStart = i;
        }
      }
      Product product1;
      String productIdentifier = words[1];
      String name = words[3];
      String description = "";
      for (int i = fieldDescriptionStart + 1; i < fieldSizeStart; i++) {
        description += words[i] + " ";
      }

      String size = words[fieldSizeStart + 1];
      String cat = words[fieldCategoryStart + 1];
      int price = Integer.parseInt(words[fieldPriceStart + 1]);

      try {
        if (fieldTemperatureStart != 0) {
          Temperature temp = Temperature.valueOf(words[fieldTemperatureStart + 1].toUpperCase());
          product1 = service.defineProduct(productIdentifier, name, description, size, cat, price, temp);
        } else {
          product1 = service.defineProduct(productIdentifier, name, description, size, cat, price);
        }
      } catch (Store24X7ServiceException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      } catch (IllegalArgumentException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getMessage(), number);
      }

      StdOut.println("Product: " + product1);

    }

    if (words[0].equalsIgnoreCase("show-product")) {
      String identifer = words[1];
      try {
        StdOut.println("Product: " + service.showProduct(words[1]));
      } catch (Store24X7ServiceException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      }

    }

    if (words[0].equalsIgnoreCase("define-inventory")) {
      String inventoryIdentifier = words[1];
      String storeIdentifier = words[3];
      String aisleIdentifier = words[4]; // colon issue
      String shelfIdentifier = words[5]; // colon issue
      int capacity = Integer.parseInt(words[7]);
      int count = Integer.parseInt(words[9]);
      String product = words[11];
      try {
        String[] input = {
          inventoryIdentifier,
          storeIdentifier,
          aisleIdentifier,
          shelfIdentifier
        };
        StdOut.println("Inventory: " + service.defineNewInventory(inventoryIdentifier, input, capacity, count, product));
      } catch (Store24X7ServiceException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      } catch (StoreException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      }  
    }

    if (words[0].equalsIgnoreCase("show-inventory")) {
      try {
        StdOut.println("Inventory: " + service.showInventory(words[1]));
      } catch (Store24X7ServiceException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      }

    }

    if (words[0].equalsIgnoreCase("update-inventory")) {
      String identifier = words[1];
      int increment = Integer.parseInt(words[3]);
      try {
        StdOut.println("Inventory: " + service.updateInventoryCount(identifier, increment));
      } catch (Store24X7ServiceException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      }

    }

    if (words[0].equalsIgnoreCase("define-customer")) {
      String customerIdentifier = words[1];
      String firstName = words[3];
      String lastName = words[5];
      CustomerType type = CustomerType.valueOf(words[7].toUpperCase());
      String email = words[9];
      String billing = words[11];
      try {
        StdOut.println("Customer: " + service.defineCustomer(customerIdentifier, firstName, lastName, type, billing, email));
      } catch (Store24X7ServiceException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      }
    }

    if (words[0].equalsIgnoreCase("update-customer")) {
      String customerIdentifier = words[1];
      String storeIdentifier = words[3];
      String aisleIdentifier = words[4];
      try {
        StdOut.println("Customer: " + service.updateCustomerLocation(customerIdentifier, storeIdentifier, aisleIdentifier));
      } catch (Store24X7ServiceException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      }

    }

    if (words[0].equalsIgnoreCase("show-customer")) {
      String customerIdentifier = words[1];
      try {
        StdOut.println("Customer: " + service.showCustomer(customerIdentifier));
      } catch (Store24X7ServiceException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      }
    }

    if (words[0].equalsIgnoreCase("define-basket")) {
      String basketIdentifier = words[1];
      try {
        StdOut.println("Basket: " + service.defineBasket(basketIdentifier));
      } catch (Store24X7ServiceException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      }

    }
    if (words[0].equalsIgnoreCase("assign-basket")) {
      String basketIdentifier = words[1];
      String customerIdentifier = words[3];
      try {
        StdOut.println("Basket Assigned: " + service.assignCustomerBasket(basketIdentifier, customerIdentifier));
        StdOut.println("Customer Assigned Basket: " + service.showCustomer(customerIdentifier));
      } catch (Store24X7ServiceException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      }

    }

    if (words[0].equalsIgnoreCase("get-customer-basket")) {
      String customerIdentifier = words[1];
      try {
        StdOut.println("Customer Basket :" + service.getCustomerBasket(customerIdentifier));
        StdOut.println("Customer With Basket :" + service.showCustomer(customerIdentifier));
      } catch (Store24X7ServiceException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      }

    }
    if (words[0].equalsIgnoreCase("add-basket-item")) {
      String basketIdentifier = words[1];
      String productIdentifier = words[3];
      int count = Integer.parseInt(words[5]);
      try {
        StdOut.println("Add Product to Basket " + basketIdentifier + ": " + service.addProductToBasket(basketIdentifier, productIdentifier, count));
      } catch (Store24X7ServiceException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      }

    }

    if (words[0].equalsIgnoreCase("remove-basket-item")) {
      String basketIdentifier = words[1];
      String productIdentifier = words[3];
      int count = Integer.parseInt(words[5]);
      try {
        StdOut.println("Remove Product From Basket " + basketIdentifier + ": " + service.removeProductFromBasket(basketIdentifier, productIdentifier, count));
      } catch (Store24X7ServiceException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      }

    }

    if (words[0].equalsIgnoreCase("clear-basket")) {
      String basketIdentifier = words[1];
      try {
        StdOut.println("Basket cleared " + basketIdentifier + ": " + service.clearContents(basketIdentifier));
      } catch (Store24X7ServiceException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      }
    }

    if (words[0].equalsIgnoreCase("show-basket-items")) {
      String basketIdentifier = words[1];
      try {
        StdOut.println("Basket Items from basket" + basketIdentifier + ": " + service.getItems(basketIdentifier));
      } catch (Store24X7ServiceException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      }
    }

    if (words[0].equalsIgnoreCase("define-device")) {
      String deviceIdentifier = words[1];
      String name = words[3];
      DeviceType type;
      String storeIdentifier = words[7];
      String aisleIdentifier = words[8];

      try {
        type = DeviceType.valueOf(words[5].toUpperCase());
        StdOut.println("Create Device: " + deviceIdentifier + ": " + service.defineDevice(deviceIdentifier, name, type, storeIdentifier, aisleIdentifier));
      } catch (Store24X7ServiceException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      } catch (IllegalArgumentException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getMessage(), number);
      }

    }

    if (words[0].equalsIgnoreCase("show-device")) {
      String deviceIdentifier = words[1];
      try {
        StdOut.println("Show Device " + deviceIdentifier + ": " + service.showDevice(deviceIdentifier));
      } catch (Store24X7ServiceException e) {
        throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getReason(), number);
      }
    }


    // This is Freeman's assignment three work 
    if (words[0].equalsIgnoreCase("create-event")) {

      int wordsSize = words.length;
      String deviceId = words[1];
      String storeId = words[3];
      EventType type;
      String[] eventText = new String[wordsSize-5];

      if (words[5].substring(1).equals("emergency")){
        type = EventType.EMERGENCY;
      }
      else if (words[5].substring(1).equals("basket-event")){
        type = EventType.BASKET_EVENT;
      }
      else if (words[5].substring(1).equals("product-spill")){
        type = EventType.CLEANING_EVENT;
      }
      else if (words[5].substring(1).equals("broken-glass")){
        type = EventType.BROKEN_GLASS;
      }
      else if (words[5].substring(1).equals("missing-person")){
        type = EventType.MISSING_PERSON;
      }
      else if (words[5].substring(1).equals("customer-seen")){
        type = EventType.CUSTOMER_SEEN;
      }
      else if (words[5].substring(1).equals("fetch-product")){
        type = EventType.FETCH_PRODUCT;
      }
      else if (words[5].substring(1).equals("check-acc-bal")){
        type = EventType.CHECK_ACCOUNT_BALANCE;
      }
      else if (words[5].substring(1).equals("assist-customer")){
        type = EventType.ASSIST_CUSTOMER_TO_CAR;
      }
      else if (words[5].substring(1).equals("checkout")){
        type = EventType.CHECKOUT;
      }
      else if (words[5].substring(1).equals("enter-store")){
        type = EventType.ENTER_STORE;
      }
      else{
        System.out.println(words[5].substring(1));
        type = null;
      }
      
      for (int i = 6; i < wordsSize; i++) {
        eventText[i-5] = words[i];
      }

      StdOut.println("Device " + deviceId + " in store " + storeId + " event type: "+  type + " event description: " + service.createEvent(deviceId, storeId, type, eventText));
    }

    // end freeman's portion

    if (words[0].equalsIgnoreCase("create-command")) {
      int wordsSize = words.length;
      String deviceIdentifier = words[1];
      String message = "";
      for (int i = 3; i < wordsSize; i++) {
        message += words[i];
      }

      StdOut.println("Create Command Placeholder " + deviceIdentifier + ": " + service.createCommand(deviceIdentifier, message));
    }

  }

  /** This method is used to create process commands within a given command file.
   * @param command a String giving the file
   * @exception CommandProcessorException for errors
   */

  public void processCommandFile(String file) throws CommandProcessorException {

    try {
      File f = new File(file);
      Scanner in = null;
      line = "";
      number = 0;

      in = new Scanner(f);
      number = 0;
      while ( in .hasNextLine()) {
        line = in .nextLine().trim();
        number++;
        if (line.length() == 0 || line.startsWith("#")) {
          continue;
        }
        try {
          processCommand(line);
        } catch (CommandProcessorException e) {
          StdOut.println(e);
        }
      }

      in .close();
    } catch (IOException e) {
      throw new CommandProcessorException(e.getStackTrace()[0].getMethodName(), e.getMessage(), 0);
    }
  }

}
