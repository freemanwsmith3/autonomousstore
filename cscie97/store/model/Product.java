package com.cscie97.store.model;
import java.util.*;

/**
 * CSCIE-97 Assignment 2
 * @ author Ellen Siminoff
 * @ date 09/30/2021
 *
 * Product.java
 * Product implements the attributes of managing products.
 **/
public class Product {

  // instance variables
  private String productid;
  private String name;
  private String description;
  private Temperature temperature;
  private String size;
  private String category;
  private int price;
  static final Temperature DEFAULT_TEMPERATURE = Temperature.AMBIENT;

  // two constructors - one with Temperature provided/one without
  public Product(String id, String name, String description, String size, String category, int price, Temperature temperature) {
    this.productid = id;
    this.name = name;
    this.description = description;
    this.size = size;
    this.category = category;
    this.price = price;
    this.temperature = temperature;
  }

  public Product(String id, String name, String description, String size, String category, int price) {
    this.productid = id;
    this.name = name;
    this.description = description;
    this.size = size;
    this.category = category;
    this.price = price;
    this.temperature = DEFAULT_TEMPERATURE;
  }

  /** This method gets product Id
   *@return String productId of product.
   **/
  public String getProductId() {
    return this.productid;
  }

  /** This method gets product name
   *@return String name of product
   **/
  public String getName() {
    return this.name;
  }

  /** This method gets product description
   *@return String description of product
   **/
  public String getDescription() {
    return this.description;
  }

  /** This method gets product size
   *@return String size of product
   **/
  public String getSize() {
    return this.size;

  }

  /** This method gets product category
   *@return String category of product
   **/
  public String getCategory() {
    return this.category;
  }

  /** This method gets product price
   *@return int price of product
   **/
  public int getPrice() {
    return this.price;

  }

  /** This method gets product temperature
   *@return Temperature temperature of product
   **/
  public Temperature getTemp() {
    return this.temperature;
  }

  /** This method sets product Id
   *@param String productId
   *@return String productId of product.
   **/
  protected String getProductId(String productId) {
    this.productid = productId;
    return this.productid;
  }

  /** This method sets product name
   *@param String name of product
   *@return String name of product
   **/
  protected String setName(String name) {
    this.name = name;
    return this.name;
  }

  /** This method sets product description
   @param String description
   *@return String description of product
   **/
  protected String setDescription(String description) {
    this.description = description;
    return this.description;
  }

  /** This method sets product size
   *@param String size
   *@return String size of product
   **/
  protected String setSize(String size) {
    this.size = size;
    return this.size;

  }

  /** This method sets product category
   *@param String category
   *@return String category of product
   **/
  public String setCategory(String category) {
    this.category = category;
    return this.category;
  }

  /** This method sets product price
   *@param int price
   *@return int price of product
   **/
  public int getPrice(int price) {
    this.price = price;
    return this.price;

  }

  /** This method sets product temperature
   *@param Temperature temperature
   *@return Temperature temperature of product
   **/
  public Temperature setTemp(Temperature temperature) {
    this.temperature = temperature;
    return this.temperature;
  }

  /** This method is used to print the info in the product.
   * @return String simple text of the product info
   */
  public String toString() {
    return "Product id: " + this.productid + " Name:  " + this.name + " Description: " + this.description +
      " Size: " + this.size + " Category: " + this.category + " Price: " + this.price + " Temperature: " + this.temperature;
  }

}
