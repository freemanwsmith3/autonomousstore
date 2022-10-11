package com.cscie97.store.model;
import java.util.*;

/**
 * Customer.java
 * Customer implements the functions of a customer. The customer carries its own information, including
 * latest location. It holds variables unique to the customer as a customer can visit different
 * stores in the Service.
 **/
public class Customer {

  // instance variables
  public String id;
  public String firstName;
  public String lastName;
  private CustomerType type; //Registered or Guest
  private String email;
  public String billingAddress;
  private Date time;
  public String basketId;
  private String storeId;
  public String aisleId;

  // constructor
  public Customer(String id, String firstName, String lastName, CustomerType type, String billingAddress, String email) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.type = type;
    this.email = email;
    this.billingAddress = billingAddress;
    this.time = null;
    this.aisleId = "";
    this.storeId = "";
    this.basketId = "";
  }

  /** This method gets customer Id
   *@return String id of customer
   **/
  public String getCustomerId() {
    return this.id;
  }

  /** This method gets customer first name
   *@return String firstName of customer
   **/
  public String getCustomerFirst() {
    return this.firstName;
  }

  /** This method gets customer last name
   *@return String lastName of customer
   **/
  public String getCustomerLast() {
    return this.lastName;
  }

  /** This method gets customer type
   *@return CustomerType type of customer
   **/
  public CustomerType getCustomerType() {
    return this.type;
  }

  /** This method gets customer email
   *@return String email of customer
   **/
  public String getCustomerEmail() {
    return this.email;
  }

  /** This method gets customer address
   *@return String billingAddress of customer
   **/
  public String getCustomerBilling() {
    return this.billingAddress;
  }

  /** This method gets customer time of last check in
   *@return Date time
   **/
  public Date getCustomerTime() {
    return this.time;
  }

  /** This method gets customer store location
   *@return String store of customer
   **/
  public String getCustomerStore() {
    return this.storeId;
  }

  /** This method gets customer aisle location
   *@return String aisle of customer
   **/
  public String getCustomerAisle() {
    return this.aisleId;
  }

  /** This method is used to get a basketId for a customer.
   *@return String basketId
   */
  public String getBasket() {
    if (this.basketId == null) {
      return null;
    }
    return this.basketId;
  }

  /** This method is used to set a customer location with latest time.
   *@param String newStore id for new store location
   *@param STring newAisle id for new aisle location
   *@return String id of Customer
   */
  public String setCustomerLocation(String newAisle, String newStore) {
    this.storeId = newStore;
    this.aisleId = newAisle;
    time = new Date();
    return this.id;
  }

  /** This method is used to set a basketId for a customer.
   * {@link #setBasket(String basketId) setBasket}
   * method for method with basketId}
   *@return String id of basket
   */
  public String setBasket() {
    Basket additional = new Basket();
    this.basketId = additional.getBasketId();
    Store24X7Service.getBasketMap().put(this.basketId, additional);
    return this.basketId;
  }

  /** This method is used to set a basketId for a customer where basket already created.
   *@param String basketId id of basket
   * {@link #setBasket() setBasket}
   * method for method without basketId}
   *@return String id of basket
   */
  public String setBasket(String basketId) {
    this.basketId = basketId;
    return this.basketId;
  }

  /** This method is used to determine if a basket exists for a customer.
   *@return boolean
   */
  public boolean existsBasket() {
    if (this.basketId == "") {
      return false;
    }
    return true;
  }

  /** This method is used to set a basketId for a customer to null.
   */
  public void removeBasket() {
    this.basketId = null;
  }

  /** This method is used to determine if a customer is registered.
   *@return boolean
   */
  public boolean isRegistered() {
    if (this.type.equals(CustomerType.REGISTERED)) {
      return true;
    }
    return false;
  }

  /** This method sets customer Id. Limited to package
   *@param String customerId
   *@return String id of customer
   **/
  protected String setCustomerId(String customerId) {
    this.id = customerId;
    return this.id;
  }

  /** This method sets customer firstName. Limited ot package
   *@param String firstName
   *@return String firstName
   **/
  protected String setCustomerFirst(String firstName) {
    this.firstName = firstName;
    return this.firstName;
  }

  /** This method sets customer last name. Limited to package
   *@param String lastName
   *@ Return String lastName of customer
   **/
  protected String setCustomerLast(String lastName) {
    this.lastName = lastName;
    return this.lastName;
  }

  /** This method sets customer type. Limited to package
   *@param CustomerType type of customer
   *@ Return CustomerType type of customer
   **/
  protected CustomerType setCustomerType(CustomerType type) {
    this.type = type;
    return this.type;
  }

  /** This method sets customer email. Limited to package
   *@param String email of customer
   *@ Return String email of customer
   **/
  protected String setCustomerEmail(String email) {
    this.email = email;
    return this.email;
  }

  /** This method sets customer address. Limited to package
   *@param String billingAddress of customer
   *@ Return String billingAddress of customer
   **/
  protected String setCustomerBilling(String billingAddress) {
    this.billingAddress = billingAddress;
    return this.billingAddress;
  }

  /** This method sets customer time of check in . Limited to package
   *@param Date time of customer
   *@ Return Date time of customer
   **/
  protected Date setCustomerTime(Date time) {
    this.time = time;
    return this.time;
  }

  /** This method is used to print the info in the customer.
   * @return String simple text of the customer info
   */
  public String toString() {
    return "Id: " + this.id + " firstName:  " + this.firstName + " lastName: " + this.lastName +
      " type: " + this.type + " email: " + this.email + " Address: " + this.billingAddress + " time: " +
      this.time + " location: " + this.aisleId + " , " + this.storeId + " assignedBasket: " + this.basketId;
  }

}
