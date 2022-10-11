package com.cscie97.store.model;
import java.util.*;

/**
 * Store.java
 * Store implements the functions of a store. The Store instantiates its aisles and shelves.
 * It holds variables unique to its store as many stores can be created within the overall Service.
 **/
public class Store {

  // attributes particular to store
  private String id;
  private String name;
  private String address;

  // shelf and aisle are instantiated
  private Shelf shelf;
  private Aisle aisle;

  // maps to hold Aisle and Shelf objects unique to store
  private Map < String, Aisle > ais;
  private Map < String, Shelf > shel;

  // lists holding updated customers, inventory and devices unique to store
  private ArrayList < Customer > customerStoreList;
  private ArrayList < Inventory > inventoryStoreList;
  private ArrayList < Device > deviceStoreList;

  // constructor
  public Store(String id, String name, String address) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.ais = new HashMap < > ();
    this.shel = new HashMap < > ();
  }

  /** This method is used to create an aisle within a store. AisleIds within stores and location must be unique.
   * @param aisleId a String giving the id
   * @param name a String giving the name
   * @param description a String giving the description
   * @param location a Location giving the location of floor or storeroom
   * @return Aisle upon successful creation
   * @exception StoreException for non unique ids
   */
  public Aisle defineAisle(String aisleId, String name, String description, Location location) throws StoreException {

    //guarantees unique id
    String mapId = aisleId + this.id;
    if (this.ais.containsKey(mapId) && this.ais.get(mapId).getAisleStoreId().equals(this.id) &&
      this.ais.get(mapId).getAisleLocation().equals(location)) {
      throw new StoreException("id", "Not a unique aisle for this store and location");
    }

    this.aisle = new Aisle(aisleId, name, description, location, this.id);

    // add to map for tracking
    this.ais.put(mapId, aisle);
    return aisle;
  }

  /** This method is used to get an aisle with its shelf list.
   * @param storeId a String giving the id of the store
   * @param aisleId a String giving the id of the aisle
   * @return Aisle
   * @exception StoreException for aisleIds that do not exist
   */
  public Aisle showAisle(String storeId, String aisleId) throws StoreException {

    String mapId = aisleId + storeId;
    if (!(this.ais.containsKey(mapId))) {
      throw new StoreException("id", "No aisle for this store and location");
    }

    return this.ais.get(mapId);
  }

  /** This method is used to create a shelf within a store and aisle. ShelfIds within stores and aisle must be unique.
   * @param shelfId a String giving the id
   * @param name a String giving the name
   * @param level a Level giving low, medium or high
   * @param description a String giving the description
   * @param temperature a Temperature giving FROZEN, REFRIGERATED, AMBIENT, WARM, HOT
   * {@link #defineShelf(String shelfId, String name, Level level, String description, String aisleId) defineShelf}
   * method for method with Temperature not provided
   * @param aisleId a String giving the id of the aisle
   * @return Shelf upon successful creation
   * @exception StoreException for non unique ids
   */
  public Shelf defineShelf(String shelfId, String name, Level level, String description, Temperature temperature, String aisleId) throws StoreException {
    String mapId = shelfId + aisleId + this.id;
    String mapAisleId = aisleId + this.id;
    if (this.shel.containsKey(mapId) && this.shel.get(mapId).getStoreId().equals(this.id) &&
      this.shel.get(mapId).getAisleId().equals(aisleId)) {
      throw new StoreException("id", "Not a unique shelf for this store and aisle");
    }

    this.shelf = new Shelf(shelfId, name, level, description, temperature, this.id, aisleId);
    this.shel.put(mapId, shelf);

    // provide a list of shelves for each aisle
    this.ais.get(aisleId + this.id).addShelfIdList(shelfId);
    return shelf;
  }

  /** This method is used to create a shelf within a store and aisle. ShelfIds within stores and aisle must be unique.
   * @param shelfId a String giving the id
   * @param name a String giving the name
   * @param level a Level giving low, medium or high
   * @param description a String giving the description
   * @param aisleId a String giving the id of the aisle
   * {@link #defineShelf(String shelfId, String name, Level level, String description, Temperature temperature, String aisleId) defineShelf}
   * method for method with Temperature provided
   * @return Shelf upon successful creation
   * @exception StoreException for non unique ids
   */
  public Shelf defineShelf(String shelfId, String name, Level level, String description, String aisleId) throws StoreException {

    String mapId = shelfId + aisleId + this.id;
    String mapAisleId = aisleId + this.id;

    if (this.shel.containsKey(mapId) && this.shel.get(mapId).getStoreId().equals(this.id) &&
      this.shel.get(mapId).getAisleId().equals(aisleId)) {
      throw new StoreException("id", "Not a unique shelf for this store and aisle");
    }

    this.shelf = new Shelf(shelfId, name, level, description, this.id, aisleId);
    // ensures uniqueness
    this.shel.put(mapId, shelf);

    //p provide to aisle's shelfList
    this.ais.get(aisleId + this.id).addShelfIdList(shelfId);

    return shelf;
  }

  /** This method is used to get a shelf.
   * @param storeId a String giving the id of the store
   * @param aisleId a String giving the id of the aisle
   * @param shelfId a String giving the id of the shelf
   * @return Shelf
   * @exception StoreException for shelfIds that do not exist
   */
  public Shelf showShelf(String storeId, String aisleId, String shelfId) throws StoreException {
    String mapId = shelfId + aisleId + storeId;
    if (!(this.shel.containsKey(mapId))) {
      throw new StoreException("id", "No shelf for this store and location");
    }

    return this.shel.get(mapId);
  }

  /** This method is used to get a storeId
   *@return String id
   */
  public String getStoreId() {
    return this.id;
  }

  /** This method is used to get a store name
   *@return name
   */
  public String getStoreName() {
    return this.name;
  }

  /** This method is used to get a store address
   *@return address
   */
  public String getStoreAddress() {
    return this.address;
  }

  /** This method is used to set a storeId. Limited to package.
   *@param id String store Id
   *@return String store Id
   */
  protected String setStoreId(String id) {
    this.id = id;
    return this.id;
  }

  /** This method is used to set a store name. Limited to package.
   *@param name String store name
   *@return String store name
   */
  protected String setStoreName(String name) {
    this.name = name;
    return this.name;
  }

  /** This method is used to set a store address. Limited to package.
   *@param address String store address
   *@return String store address
   */
  protected String setStoreAddress(String address) {
    this.address = address;
    return this.address;
  }

  /** This method is used to get a store active customer list
   *@return List of Customer objects
   */
  public List < Customer > showActiveCustomers() throws StoreException {

    this.customerStoreList = new ArrayList < Customer > ();

    Set < Map.Entry < String, Customer >> entrySet = Store24X7Service.getCustomerMap().entrySet();
    for (Map.Entry < String, Customer > entry: entrySet) {
      Customer cust = entry.getValue();
      if (cust.getCustomerStore().equals(this.id)) {
        this.customerStoreList.add(cust);
      }
    }
    return this.customerStoreList;
  }

  /** This method is used to get a store active inventory list
   *@return List of Inventory objects
   */
  public List < Inventory > showStoreInventory() {
    this.inventoryStoreList = new ArrayList < Inventory > ();

    Set < Map.Entry < String, Inventory >> entrySet = Store24X7Service.getInventoryMap().entrySet();
    for (Map.Entry < String, Inventory > entry: entrySet) {
      Inventory inv = entry.getValue();
      if (inv.getStoreId().equals(this.id)) {
        this.inventoryStoreList.add(inv);
      }
    }
    return this.inventoryStoreList;

  }

  /** This method is used to get a store active device list
   *@return List of Device objects
   */
  public List < Device > showDevices() {
    this.deviceStoreList = new ArrayList < Device > ();

    Set < Map.Entry < String, Device >> entrySet = Store24X7Service.getDeviceMap().entrySet();
    for (Map.Entry < String, Device > entry: entrySet) {
      Device dev = entry.getValue();
      if (dev.getStoreId().equals(this.id)) {
        this.deviceStoreList.add(dev);
      }
    }
    return this.deviceStoreList;
  }

  /** This method is used to print the info in the store.
   * @return String simple text of the store info
   */
  public String toString() {

    String result = "StoreId: " + this.id + " Name:  " + this.name + " Address: " + this.address;
    result += " Aisle Map: " + this.ais;
    result += " Active Customers " + this.customerStoreList;
    result += " Store Inventory " + this.inventoryStoreList;
    result += " Device Inventory " + this.deviceStoreList;

    return result;

  }

}
