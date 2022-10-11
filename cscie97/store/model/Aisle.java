package com.cscie97.store.model;
import java.util.*;

/**
 *
 * Aisle.java
 * Aisle implements the functions of an aisle. The Aisle holds a list of its shelves.
 * It holds variables unique to its aisle as many aisles can be created within different stores.
 **/
public class Aisle {

  // instance variables
  private String number;
  private String name;
  private String description;
  private Location location;
  private String storeId;

  // list of shelves
  private ArrayList < String > shelfIdList = new ArrayList < String > ();

  // constructor
  public Aisle(String number, String name, String description, Location location, String storeId) {
    this.number = number;
    this.name = name;
    this.description = description;
    this.location = location;
    this.storeId = storeId;
  }

  /** This method is used to get an aisle number (id)
   *@return String number
   */
  public String getAisleId() {
    return this.number;
  }

  /** This method is used to get an aisle name
   *@return String name
   */
  public String getAisleName() {
    return this.name;
  }

  /** This method is used to get an aisle description
   *@return String description
   */
  public String getAisleDescription() {
    return this.description;
  }

  /** This method is used to get an aisle location
   *@return Location location
   */
  public Location getAisleLocation() {
    return this.location;
  }

  /** This method is used to get an aisle's store Id
   *@return String storeId
   */
  public String getAisleStoreId() {
    return this.storeId;
  }

  /** This method is used to set an aisle id. Limited to package.
   *@param id String aisle Id
   *@return String number
   */
  protected String setAisleId(String id) {
    this.number = id;
    return this.number;
  }

  /** This method is used to set an aisle name. Limited to package.
   *@param name String name
   *@return String name
   */
  protected String setAisleName(String name) {
    this.name = name;
    return this.name;
  }

  /** This method is used to set an aisle description. Limited to package.
   *@param description String description
   *@return String description
   */
  protected String setAisleDescription(String description) {
    this.description = description;
    return this.description;
  }

  /** This method is used to set an aisle location. Limited to package.
   *@param Location location
   *@return Location
   */
  protected Location setAisleLocation(Location location) {
    this.location = location;
    return this.location;
  }

  /** This method is used to set a storeId. Limited to package.
   *@param storeId String storeId
   *@return String storeId
   */
  protected String setAisleStoreId(String storeId) {
    this.storeId = storeId;
    return this.storeId;
  }

  /** This method is used to get a List of shelves in the aisle.
   *@return List list of shelves
   */
  public ArrayList < String > getShelfIdList() {
    return this.shelfIdList;
  }

  /** This method is used to get a add to List of shelves in the aisle.
   *@param shelfId String shelfId
   *@return List list of shelf ids
   */
  public ArrayList < String > addShelfIdList(String shelfId) {
    this.shelfIdList.add(shelfId);
    return this.shelfIdList;
  }

  /** This method is used to print the info in the aisle.
   * @return String simple text of the aisle info
   */
  public String toString() {
    return "Aisle Number: " + this.number + " Name:  " + this.name + " Description: " + this.description +
      " Location: " + this.location + " Store: " + this.storeId + " Shelf/shelves: " + this.shelfIdList;
  }

}
