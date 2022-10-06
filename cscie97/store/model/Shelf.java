package com.cscie97.store.model;
import java.util.*;

/**
 * CSCIE-97 Assignment 2
 * @ author Ellen Siminoff
 * @ date 09/30/2021
 *
 * Shelf.java
 * Shelf implements the functions of a shelf. T
 * It holds variables unique to its shelf as shelves can be in many aisles in many stores.
 **/
public class Shelf {

  private String id;
  private String name;
  private Level level;
  private String description;
  private Temperature temperature;
  private String storeId;
  private String aisleId;

  // default if not porvided
  static final Temperature DEFAULT_TEMPERATURE = Temperature.AMBIENT;

  // two constructors - one with temperature and one using default
  public Shelf(String id, String name, Level level, String description, Temperature temperature, String storeId, String aisleId) {
    this.id = id;
    this.name = name;
    this.level = level;
    this.description = description;
    this.temperature = temperature;
    this.storeId = storeId;
    this.aisleId = aisleId;
  }

  public Shelf(String id, String name, Level level, String description, String storeId, String aisleId) {
    this.id = id;
    this.name = name;
    this.level = level;
    this.description = description;
    this.temperature = DEFAULT_TEMPERATURE;
    this.storeId = storeId;
    this.aisleId = aisleId;
  }

  /** This method is used to get shelfId
   *@return String id
   */
  public String getShelfId() {
    return this.id;
  }

  /** This method is used to get an aisle id
   *@return String aisleId
   */
  public String getAisleId() {
    return this.aisleId;
  }

  /** This method is used to get a storeId
   *@return String storeId
   */
  public String getStoreId() {
    return this.storeId;
  }

  /** This method is used to get a shelf name
   *@return String name
   */
  public String getName() {
    return this.name;
  }

  /** This method is used to get a shelf level
   *@return Level level
   */
  public Level getLevel() {
    return this.level;
  }

  /** This method is used to get a shelf description
   *@return String description
   */
  public String getDescription() {
    return this.description;
  }

  /** This method is used to get a shelf temperature
   *@return Temperature temperature
   */
  public Temperature getTemperature() {
    return this.temperature;
  }

  /** This method is used to set a shelfId. Limited to package.
   *@param id String shelfId
   *@return String shelfId
   */
  public String setShelfId(String id) {
    this.id = id;
    return this.id;
  }

  /** This method is used to set an aisleId. Limited to package.
   *@param id String aisleId
   *@return String aisleId
   */
  protected String setAisleId(String id) {
    this.aisleId = id;
    return this.aisleId;
  }

  /** This method is used to set a storeId. Limited to package.
   *@param id String storeId
   *@return String storeId
   */
  protected String setStoreId(String id) {
    this.storeId = id;
    return this.storeId;
  }

  /** This method is used to set a shelf name. Limited to package.
   *@param name String name
   *@return String name
   */
  protected String setName(String name) {
    this.name = name;
    return this.name;
  }

  /** This method is used to set an aisle level. Limited to package.
   *@param level Level
   *@return Level level set
   */
  protected Level setLevel(Level level) {
    this.level = level;
    return this.level;
  }

  /** This method is used to set a shelf description. Limited to package.
   *@param description String description
   *@return String description
   */
  protected String setDescription(String description) {
    this.description = description;
    return this.description;
  }

  /** This method is used to set a Temperature. Limited to package.
   *@param temperature Temperature
   *@return Temperature temperature set
   */
  protected Temperature setTemperature(Temperature temperature) {
    this.temperature = temperature;
    return this.temperature;
  }

  /** This method is used to print the info in the shelf.
   * @return String simple text of the shelf info
   */
  public String toString() {
    return "Shelf id: " + this.id + " Name:  " + this.name + " Description: " + this.description +
      " Level: " + this.level + " Store: " + this.storeId + " Temperature: " + this.temperature + " Aisle: " + this.aisleId;
  }

}
