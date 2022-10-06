package com.cscie97.store.model;
import com.cscie97.store.controller.ControllerService;
import java.util.*;

/**
 * 
 * Freeman Smith utilizing Ellen's work
 * 
 * CSCIE-97 Assignment 2
 * @ author Ellen Siminoff
 * @ date 09/30/2021
 *
 * Store24X7Service.java
 * Store24X7Service implements the Service. The Service manages the overall service.
 * It is primarily responsible for managing the state of the stores.
 * It instantiates and manages stores, inventory, products, devices, customers and baskets.
 * The Store Model Service is responsible for maintaining the state of the sensors
 * and appliances within the Store. Sensors are able to collect and share data.
 * Sensors include cameras and microphones. Like Sensors, Appliances can collect and
 * share data, however, appliances can also be controlled.
 *
 * The Store Model Service provides a service interface for managing the state of the stores.
 * The API supports commands for
 * 1. Defining the store configuration
 * 2. Showing the store configuration
 * 3. Creating/Simulating sensor events
 * 4. Sending command messages to appliances
 * 5. Accessing sensor and appliance state
 * 6. Monitoring and supporting customers
 **/
public class Store24X7Service {

  // objects for classes that will be instantiated
  private Store store;
  private Customer customer;
  private Product product;
  private Inventory inventory;
  private Basket basket;
  private Device device;
  private Sensor sensor;
  private Appliance appliance;
  private Event event;

  // maps to hold objects for easy lookup
  private static Map < String, Store > storeMap = new HashMap < > ();
  private static Map < String, Customer > customerMap = new HashMap < > ();
  private static Map < String, Product > prodMap = new HashMap < > ();
  private static Map < String, Inventory > invMap = new HashMap < > ();
  private static Map < String, Basket > baskMap = new HashMap < > ();
  private static Map < String, Device > deviceMap = new HashMap < > ();

  /** Constructor
   *  Exists to instantiate the Service
   */
  public Store24X7Service() {

  }

  /** This method is used to create a store. StoreIds must be unique.
   * @param id a String giving the id
   * @param name a String giving the name
   * @param address a String giving the address
   * @return Store upon successful creation
   * @exception Store24X7ServiceException for non unique ids
   */
  public Store defineStore(String id, String name, String address) throws Store24X7ServiceException {
    if (storeMap.containsKey(id)) {
      throw new Store24X7ServiceException("id", "Not a unique store");
    }
    store = new Store(id, name, address);

    storeMap.put(id, store);
    return store;
  }

  /** This method is used to get a store with latest customer, inventory and device lists.
   * @param id a String giving the id
   * @return Store
   * @exception Store24X7ServiceException for storeIds that do not exist
   */
  public Store showStore(String id) throws Store24X7ServiceException, StoreException {

    //Show the details of a store, Print out the details of the store including the id, name, address, active customers, aisles, inventory, sensors, and devices.
    if (!(storeMap.containsKey(id))) {
      throw new Store24X7ServiceException("id", "No store exists for that Id");
    }

    // latest list of active customers, inventory and devices
    storeMap.get(id).showActiveCustomers();
    storeMap.get(id).showStoreInventory();
    storeMap.get(id).showDevices();

    return storeMap.get(id);
  }

  /** This method is used to create a product. ProductIds must be unique.
   * @param id a String giving the id
   * @param name a String giving the name
   * @param description a String giving the description
   * @param size a String giving the size
   * @param category a String giving the category
   * @param price int giving the price
   * {@link #defineProduct(String id, String name, String description, String size, String category, int price, Temperature temperature) defineProduct}
   * method for method with Temperature provided
   * @return Product upon successful creation
   * @exception Store24X7ServiceException for non unique ids
   */
  public Product defineProduct(String id, String name, String description, String size, String category, int price) throws Store24X7ServiceException {
    if (prodMap.containsKey(id)) {
      throw new Store24X7ServiceException("id", "Not a unique product");
    }
    product = new Product(id, name, description, size, category, price);
    prodMap.put(id, product);
    return product;
  }

  /** This method is used to create a product. ProductIds must be unique.
   * @param id a String giving the id
   * @param name a String giving the name
   * @param description a String giving the description
   * @param size a String giving the size
   * @param category a String giving the category
   * @param price int giving the price
   * @param temperature Temperature enum with specific values
   * {@link #defineProduct(String id, String name, String description, String size, String category, int price) defineProduct}
   * method for method with Temperature not provided
   * @return Product upon successful creation
   * @exception Store24X7ServiceException for non unique ids
   */
  public Product defineProduct(String id, String name, String description, String size, String category, int price, Temperature temperature) throws Store24X7ServiceException {
    if (prodMap.containsKey(id)) {
      throw new Store24X7ServiceException("id", "Not a unique product");
    }
    product = new Product(id, name, description, size, category, price, temperature);
    prodMap.put(id, product);
    return product;
  }

  /** This method is used to get a product.
   * @param id a String giving the id
   * @return Product
   * @exception Store24X7ServiceException for productIds that don't exist
   */
  public static Product showProduct(String id) throws Store24X7ServiceException {
    if (!(prodMap.containsKey(id))) {
      throw new Store24X7ServiceException("id", "No product exists for that Id");
    }
    return prodMap.get(id);
  }

  /** This method is used to create and define inventory. InventoryIds must be unique.
   * @param id a String giving the id
   * @param location a String array holding ids for inventory, store, aisle, and shelf
   * @param capacity int giving the capacity
   * @param count int giving the product count, which must be >+0 and <= capacity
   * @param productId a String giving the id of the product
   * @return Inventory upon successful creation
   * @exception Store24X7ServiceException for non unique ids and counts < 0 or > capacity
   */
  public Inventory defineNewInventory(String id, String[] location, int capacity, int count, String productId) throws Store24X7ServiceException, StoreException {
    if (count < 0) {
      throw new Store24X7ServiceException("count", "Count cannot be below zero");
    }
    if (count > capacity) {
      throw new Store24X7ServiceException("count", "Count cannot be greater than capacity");
    }
    if (invMap.containsKey(id)) {
      throw new Store24X7ServiceException("id", "Not a unique inventory");
    }

    Temperature tempShelf = showStore(location[1]).showShelf(location[1], location[2], location[3]).getTemperature();
    Temperature tempProduct = showProduct(productId).getTemp();

    if (!(tempProduct.equals(tempShelf))) {
      StdOut.println("Just a warning: temperature of shelf: " + tempShelf + " not same as temp of product: " + tempProduct);
    }

    inventory = new Inventory(id, capacity, count, location[1], location[2], location[3], productId);
    invMap.put(id, inventory);
    return inventory;
  }

  /** This method is used to get inventory.
   * @param id a String giving the id
   * @return Inventory
   * @exception Store24X7ServiceException for ids that don't exist
   */
  public static Inventory showInventory(String id) throws Store24X7ServiceException {
    if (!(invMap.containsKey(id))) {
      throw new Store24X7ServiceException("id", "No inventory exists for that Id");
    }
    return invMap.get(id);
  }

  /** This method is used to update inventory counts.
   * @param id a String giving the id
   * @param increment int giving the change (can be negative or positive)
   * @return Inventory
   * @exception Store24X7ServiceException for inventory that misses constraints
   */
  public static Inventory updateInventoryCount(String id, int increment) throws Store24X7ServiceException {
    if (invMap.get(id).getCount() + increment < 0) {
      throw new Store24X7ServiceException("increment", "Inventory change cannot cause inventory below zero");
    }
    if (invMap.get(id).getCount() + increment > invMap.get(id).getCapacity()) {
      throw new Store24X7ServiceException("increment", "Inventory change cannot be greater than capacity");
    }

    invMap.get(id).changeInventoryCount(increment);
    return invMap.get(id);
  }

  /** This method is used to create and define customers. CustomerIds must be unique.
   * @param id a String giving the id
   * @param firstName a String giving the first name
   * @param lastName a String giving the last name
   * @param type a CustomerType designating registered or guest
   * @param billing a String giving the billing address
   * @param email a String giving the email
   * @return Customer upon successful creation
   * @exception Store24X7ServiceException for non unique ids
   */
  public Customer defineCustomer(String id, String firstName, String lastName, CustomerType type, String billing, String email) throws Store24X7ServiceException {
    if (customerMap.containsKey(id)) {
      throw new Store24X7ServiceException("id", "Customer already exists");
    }
    customer = new Customer(id, firstName, lastName, type, billing, email);
    customerMap.put(id, customer);
    return customer;
  }

  /** This method is used to update customer location.
   * @param id a String giving the customerId
   * @param storeLocation a String giving the storeId
   * @param aisleLocation a String giving the aisleId
   * @return Customer
   * @exception Store24X7ServiceException for customers or stores that do not exist
   */
  public static Customer updateCustomerLocation(String customerId, String storeLocation, String aisleLocation) throws Store24X7ServiceException {
    if (!(customerMap.containsKey(customerId))) {
      throw new Store24X7ServiceException("id", "No customer exists for that Id");
    }
    if (!(storeMap.containsKey(storeLocation))) {
      throw new Store24X7ServiceException("id", "No store exists for that Id");
    }
    customerMap.get(customerId).setCustomerLocation(aisleLocation, storeLocation);
    return customerMap.get(customerId);
  }

  /** This method is used to get customer.
   * @param id a String giving the id
   * @return Customer
   * @exception Store24X7ServiceException for ids that don't exist
   */
  public static Customer showCustomer(String id) throws Store24X7ServiceException {
    if (!(customerMap.containsKey(id))) {
      throw new Store24X7ServiceException("id", "No customer exists for that Id");
    }
    Customer result = customerMap.get(id);
    return result;
  }

  /** This method is used to create a basket. BasketIds must be unique.
   * @param id a String giving the id
   * @return Basket upon successful creation
   * @exception Store24X7ServiceException for non unique ids
   */
  public Basket defineBasket(String id) throws Store24X7ServiceException {
    if (baskMap.containsKey(id)) {
      throw new Store24X7ServiceException("id", "Id for basket already in use");
    }
    basket = new Basket(id);
    baskMap.put(id, basket);
    return basket;
  }

  /** This method is used to assign a basket to a customer where basket already exists.
   * @param basketId a String giving the basketId
   * @param customerId a String giving the customerId
   * @return String basketId of assigned basket upon successful assignment
   * @exception Store24X7ServiceException for non unique baskets and customers that don't exist
   * @exception Store24X7ServiceException for unregistered customers
   */
  public String assignCustomerBasket(String basketId, String customerId) throws Store24X7ServiceException {
    if (!(baskMap.containsKey(basketId))) {
      throw new Store24X7ServiceException("id", "Basket doesn't exist");
    }
    if (!(customerMap.containsKey(customerId))) {
      throw new Store24X7ServiceException("id", "Customer doesn't exist");
    }
    if (!(customerMap.get(customerId).isRegistered())) {
      throw new Store24X7ServiceException("id", "Customer is not registered/doesn't get basket");
    }

    // call Customer class from customer and set basketId
    String basketAssigned = showCustomer(customerId).setBasket(basketId);

    return basketAssigned;
  }

  /** This method is used to get the basketId associated with a customer. If the
   * customer is registered and a basket exists, it retrieves basketId. If the
   * customer is registered and does not have a basket, it is created.
   * @param id a String giving the id
   * @return String the basketId
   * @exception Store24X7ServiceException for customers that are not registered
   */
  public String getCustomerBasket(String customerId) throws Store24X7ServiceException {

    customer = showCustomer(customerId);

    if (customer.isRegistered()) {
      if (customer.existsBasket()) {
        return customer.getBasket();
      } else {
        return customer.setBasket();
      }
    } else {
      throw new Store24X7ServiceException("id", "Customer is not registered/doesn't get basket");
    }
  }

  /** This method is used to add a product to basket. It also decreases
   * inventory based on the first available inventory.
   * @param basketId a String giving the basketId
   * @param productId a String giving the productId
   * @param count int giving the count
   * @return Basket upon success
   * @exception Store24X7ServiceException for products and baskets not available for id
   */
  public static Basket addProductToBasket(String basketId, String productId, int count) throws Store24X7ServiceException {

    if (!(baskMap.containsKey(basketId))) {
      throw new Store24X7ServiceException("id", "No basket available by that Id");
    }
    if (!(prodMap.containsKey(productId))) {
      throw new Store24X7ServiceException("id", "No product available by that Id");
    }

    // identify the basket
    Basket b = getBasket(basketId);

    // add the product to the basket
    b.addProduct(productId, count);

    // get customer information and store information
    // identify inventory with product and decrease
    String storeLocation = "";
    String aisleLocation = "";
    Set < Map.Entry < String, Customer >> entrySet = getCustomerMap().entrySet();
    for (Map.Entry < String, Customer > entry: entrySet) {
      Customer cust = entry.getValue();
      if (cust.getBasket().equals(basketId)) {
        storeLocation = cust.getCustomerStore();
        aisleLocation = cust.getCustomerAisle();
      }
    }

    // identify inventory with product and decrease
    Set < Map.Entry < String, Inventory >> entry2Set = getInventoryMap().entrySet();
    for (Map.Entry < String, Inventory > entry: entry2Set) {
      Inventory invent = entry.getValue();

      // if customer in aisle, make assumption took that inventory else just same store
      if (invent.getProductId().equals(productId) && invent.getStoreId().equals(storeLocation) &&
        invent.getAisleId().equals(aisleLocation)) {
          updateInventoryCount(entry.getKey(), -count);
          StdOut.println("Removed from inventory  " + invent.getInventoryId());
          break;
      } else if (invent.getProductId().equals(productId) && invent.getStoreId().equals(storeLocation)) {
          updateInventoryCount(entry.getKey(), -count);
          StdOut.println("Removed from inventory  " + invent.getInventoryId());
          break;
      }
    }

    // resets value
    getValueForBasketItems(basketId);

    return b;
  }

  /** This method is used to remove a product from basket. It also increases
   * inventory based on the first available inventory.
   * @param basketId a String giving the basketId
   * @param productId a String giving the productId
   * @param count int giving the count
   * @return Basket upon success
   * @exception Store24X7ServiceException for products and baskets not available for id
   */
  public static Basket removeProductFromBasket(String basketId, String productId, int count) throws Store24X7ServiceException {

    // check basket exists
    if (!(baskMap.containsKey(basketId))) {
      throw new Store24X7ServiceException("id", "No basket available by that Id");
    }
    if (!(prodMap.containsKey(productId))) {
      throw new Store24X7ServiceException("id", "No product available by that Id");
    }

    // identify basket
    Basket b = getBasket(basketId);

    // remove product
    b.removeProduct(productId, count);

    // get customer information and store information
    // identify inventory with product and decrease
    String storeLocation = "";
    String aisleLocation = "";
    Set < Map.Entry < String, Customer >> entrySet = getCustomerMap().entrySet();
    for (Map.Entry < String, Customer > entry: entrySet) {
      Customer cust = entry.getValue();
      if (cust.getBasket().equals(basketId)) {
        storeLocation = cust.getCustomerStore();
        aisleLocation = cust.getCustomerAisle();
      }
    }

    // find correct inventory and increase
    // if customer in aisle, make assumption returned that inventory else just same store
    Set < Map.Entry < String, Inventory >> entry2Set = getInventoryMap().entrySet();
    for (Map.Entry < String, Inventory > entry: entry2Set) {
      Inventory invent = entry.getValue();
      if (invent.getProductId().equals(productId) && invent.getStoreId().equals(storeLocation) &&
        invent.getAisleId().equals(aisleLocation)) {
        updateInventoryCount(entry.getKey(), count);
        StdOut.println("Added back to inventory  " + invent.getInventoryId());
        break;
      } else if (invent.getProductId().equals(productId) && invent.getStoreId().equals(storeLocation)) {
          updateInventoryCount(entry.getKey(), count);
          StdOut.println("Added back to inventory  " + invent.getInventoryId());
          break;
      }
    }

    // resets value
    getValueForBasketItems(basketId);

    return b;
  }

  /** This method is used to clear contents from basket. It also disaggregates
   * customer.
   * @param basketId a String giving the basketId
   * @return Basket upon success
   * @exception Store24X7ServiceException for baskets not available for id
   */
  public Basket clearContents(String basketId) throws Store24X7ServiceException {

    if (!(baskMap.containsKey(basketId))) {
      throw new Store24X7ServiceException("id", "No basket available by that Id");
    }

    // identify basket
    Basket b = getBasket(basketId);
    b.clearBasket();

    // find correct customer and disaggregate
    Set < Map.Entry < String, Customer >> entrySet = getCustomerMap().entrySet();
    for (Map.Entry < String, Customer > entry: entrySet) {
      Customer cust = entry.getValue();
      if (cust.getBasket().equals(basketId)) {
        cust.removeBasket();
      }
    }
    return b;
  }

  /** This method is used to get latest value of the basket.
   * @param id a String giving the basketId
   * @return int value of basket
   * @exception Store24X7ServiceException for baskets not available for id
   */
  public static int getValueForBasketItems(String id) throws Store24X7ServiceException {

    if (!(baskMap.containsKey(id))) {
      throw new Store24X7ServiceException("id", "No basket available by that Id");
    }

    getItems(id);
    Basket result = getBasket(id);
    int value = result.getBasketValue();

    return value;
  }

  /** This method is used to get items from basket along with product count and set value of basket.
   * @param id a String giving the basketId
   * @return Map products and quantity
   * @exception Store24X7ServiceException for baskets not available for id
   */
  public static Map < Product, String > getItems(String id) throws Store24X7ServiceException {

    if (!(baskMap.containsKey(id))) {
      throw new Store24X7ServiceException("id", "No basket available by that Id");
    }

		// used to calculate value of Basket
		int valueOfBasket = 0;

    Basket result = getBasketMap().get(id);
    Map < String, Integer > items = result.getBasketProductMap();
    Map < Product, String > prodResult = new HashMap < > ();
    for (Map.Entry < String, Integer > entry: items.entrySet()) {
      prodResult.put(showProduct(entry.getKey()), " Quantity: " + entry.getValue());
			valueOfBasket += showProduct(entry.getKey()).getPrice() * entry.getValue();
    }
		// set value of Basket
		result.setBasketValue(valueOfBasket);

    return prodResult;
  }

  /** This method is used to create a device. DeviceIds must be unique.
   * @param id a String giving the id
   * @param name a String giving the name
   * @param type a DeviceType giving the type of device
   * @param storeId a String giving the id of the store
   * @param aisleId a String giving the id of the aisle
   * @return Device upon successful creation
   * @exception Store24X7ServiceException for non unique ids
   */
  public Device defineDevice(String id, String name, DeviceType type, String storeId, String aisleId) throws Store24X7ServiceException {
    if (deviceMap.containsKey(id)) {
      throw new Store24X7ServiceException("id", "Not a unique id");
    }

    if (type.equals(DeviceType.MICROPHONE)) {
      device = new Microphone(id, name, type, storeId, aisleId);
    } else if (type.equals(DeviceType.CAMERA)) {
      device = new Camera(id, name, type, storeId, aisleId);
    } else if (type.equals(DeviceType.SPEAKER)) {
      device = new Speaker(id, name, type, storeId, aisleId);
    } else if (type.equals(DeviceType.TURNSTILE)) {
      device = new Turnstile(id, name, type, storeId, aisleId);
    } else if (type.equals(DeviceType.ROBOT)) {
      device = new Robot(id, name, type, storeId, aisleId);
    } else {
      device = new Device(id, name, type, storeId, aisleId);
    }

    deviceMap.put(id, device);
    return device;
  }

  /** This method is used to get device.
   * @param id a String giving the id
   * @return Device
   * @exception Store24X7ServiceException for ids that don't exist
   */
  public Device showDevice(String id) throws Store24X7ServiceException {

    if (!(deviceMap.containsKey(id))) {
      throw new Store24X7ServiceException("id", "No device available by that Id");
    }
    return deviceMap.get(id);
  }

  //This is freeman's work for assignment 3
  /** This method is used to create an event/just a placeholder for now.

   */

  public String createEvent(String deviceId, String storeId, EventType type, String[] eventText) {
    
    event = new Event(deviceId, storeId, type, eventText);
    ControllerService.invokeCommand(event);


    
    return type.toString();
  }

  /** This method is used to create a command/just a placeholder for now.
   * @param id a String giving the id
   * @param event a String giving the message (placeholder)
   * @return String id
   */
  public String createCommand(String id, String message) {
    StdOut.println("id" + id + " " + "message" + message);
    return id;
  }

  /** This method is used to retrieve the StoreMap.
   * @return Map id and Store Object
   */
  public static Map < String, Store > getStoreMap() {
    return storeMap;
  }

  /** This method is used to retrieve the CustomerMap.
   * @return Map id and Customer Object
   */
  public static Map < String, Customer > getCustomerMap() {
    return customerMap;
  }

  /** This method is used to retrieve the ProductMap.
   * @return Map id and Product Object
   */
  public static Map < String, Product > getProductMap() {
    return prodMap;
  }

  /** This method is used to retrieve the InventoryMap.
   * @return Map id and Inventory Object
   */
  public static Map < String, Inventory > getInventoryMap() {
    return invMap;
  }

  /** This method is used to retrieve the BasketMap.
   * @return Map id and Basket Object
   */
  public static Map < String, Basket > getBasketMap() {
    return baskMap;
  }

  /** This method is used to retrieve the DeviceMap.
   * @return Map id and Device Object
   */
  public static Map < String, Device > getDeviceMap() {
    return deviceMap;
  }
  /** This method is used to retrieve the Basket from basketMap.
   * @return Basket
   */
  public static Basket getBasket(String basketId) {
    return baskMap.get(basketId);
  }

}
