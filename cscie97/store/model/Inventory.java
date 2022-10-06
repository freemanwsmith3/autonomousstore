package com.cscie97.store.model;
import java.util.*;

/**
 * CSCIE-97 Assignment 2
 * @ author Ellen Siminoff
 * @ date 09/30/2021
 *
 * Inventory.java
 * Inventory implements the attributes of managing inventory. Inventory  carries its own information, including
 * location and productId.
 **/
public class Inventory {

  // instance variables
  private String id;
  public int capacity;
  public int count;
  public String[] location;
  public String storeId;
  public String aisleId;
  public String shelfId;
  public String productId;

  // constructor
  public Inventory(String id, int capacity, int count, String storeId, String aisleId, String shelfId, String productId) {
    this.id = id;
    this.capacity = capacity;
    this.productId = productId;
    this.count = count;

    location = new String[4];
    location[0] = id;
    location[1] = storeId;
    location[2] = aisleId;
    location[3] = shelfId;

    // sets as individual parameters if don't want to use array
    this.storeId = storeId;
    this.shelfId = shelfId;
    this.aisleId = aisleId;
  }

  /** This method gets product Id
   *@return String productId of product held within specific inventory
   **/
  public String getProductId() {
    return this.productId;
  }

  /** This method gets inventory Id
   *@return String id of inventory
   **/
  public String getInventoryId() {
    return this.id;
  }

  /** This method gets count of inventory
   *@return int count of inventory
   **/
  public int getCount() {
    return this.count;
  }

  /** This method gets capacity of inventory
   *@return int capacity of inventory
   **/
  public int getCapacity() {
    return this.capacity;
  }

  /** This method gets array of inventoryid, storeid, aisleId and shelfId of inventory
   *@return String[] location of inventory
   **/
  public String[] getLocation() {
    return this.location;
  }

  /** This method gets id of store
   *@return String storeId of store
   **/
  public String getStoreId() {
    return this.storeId;
  }

  /** This method gets id of aisle
   *@return String aisleId of aisle
   **/
  public String getAisleId() {
    return this.aisleId;
  }

  /** This method gets id of shelf
   *@return String shelfId of shelf
   **/
  public String getShelfId() {
    return this.shelfId;
  }

  /** This method sets/changes inventory count
   *@param int increment change of inventory
   *@return String id of inventory
   **/
  public String changeInventoryCount(int increment) {

    this.count = this.count + increment;
    return this.id;
  }

  /** This method sets product Id
   *@param String productId
   *@return String productId of product held within specific inventory
   **/
  protected String setProductId(String productId) {
    this.productId = productId;
    return this.productId;
  }

  /** This method sets inventory Id
   *@param String id of inventory
   *@return String id of inventory
   **/
  protected String setInventoryId(String id) {
    this.id = id;
    return this.id;
  }

  /** This method sets capacity of inventory
   *@param int capacity
   *@return int capacity of inventory
   **/
  protected int setCapacity(int capacity) {
    this.capacity = capacity;
    return this.capacity;
  }

  /** This method sets id of store
   *@param String storeId
   *@return String storeId of store
   **/
  protected String getStoreId(String storeId) {
    this.storeId = storeId;
    return this.storeId;
  }

  /** This method sets id of aisle
   @param String aisleId
   *@return String aisleId of aisle
   **/
  protected String setAisleId(String aisleId) {
    this.aisleId = aisleId;
    return this.aisleId;
  }

  /** This method sets id of shelf
   *@param String shelfId
   *@return String shelfId of shelf
   **/
  protected String setShelfId(String shelfId) {
    this.shelfId = shelfId;
    return this.shelfId;
  }

  /** This method is used to print the info in the inventory.
   * @return String simple text of the inventory info
   */
  public String toString() {
    return "Inventory id: " + this.id + " Capacity:  " + this.capacity + " Count: " + this.count +
      " Product id: " + this.productId + " Location, store: " + this.storeId + " aisle: " + this.aisleId + " shelf: " +
      this.shelfId;
  }

}
