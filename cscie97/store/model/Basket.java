package com.cscie97.store.model;
import java.util.*;

/**
 * CSCIE-97 Assignment 2
 * @ author Ellen Siminoff
 * @ date 09/30/2021
 *
 * Basket.java
 * Basket implements the functions of a basket. The Aisle holds a Map of its products and their count.
 * It holds variables unique to its basket as many baskets can be created within different stores with different customers.
 **/
public class Basket {

  // instance variables
  public String id;

  // product map of productId and count
  private Map < String, Integer > mind;

	//
	public int valueOfBasket;

  // two constructors - one with id provided/one without
  public Basket() {

    // if no basket need to generate unique number - use date createad
    this.id = "basket" + new Date();
    this.mind = new HashMap < > ();
		this.valueOfBasket = 0;

  }

  // constructor with id provided
  public Basket(String id) {
    this.id = id;
    this.mind = new HashMap < > ();
		this.valueOfBasket = 0;

  }

  /** This method is used to get basket's id
   *@return String id
   */
  public String getBasketId() {
    return this.id;
  }

  /** This method is used to set basket's id
   *@param String id of basket
   *@return String id
   */
  public String setBasketId(String id) {
    this.id = id;
    return this.id;
  }

  /** This method is used to add a product to a basket object
   *@param String productId
   *@param int count of product in basket
   */
  public void addProduct(String prodId, int count) {

    int oldCount = 0;

    // check to see if product already in basket, then increment
    // if not, just place it in
    if (this.mind.containsKey(prodId)) {
      oldCount = this.mind.get(prodId);
      int total = count + oldCount;
      this.mind.replace(prodId, total);
    } else {
      this.mind.put(prodId, count);
    }
  }

  /** This method is used to remove a product from a basket object
   *@param String productId
   *@param int count of product in basket
   @Exception Store24X7ServiceException when removing more items than there are or product isn't in basket
   */
  public void removeProduct(String prodId, int count) throws Store24X7ServiceException {

    int oldCount = 0;

    if (!(this.mind.containsKey(prodId))) {
      throw new Store24X7ServiceException("id", "That product isn't available in this basket");
    } else {
      oldCount = this.mind.get(prodId);
      int total = oldCount - count;
      if (oldCount < count) {
        throw new Store24X7ServiceException("id", "That product doesn't have that quantity in  basket");
      } else {
        this.mind.replace(prodId, total);
      }
    }
  }

  /** This method is used to empty products from a basket.
   */
  public void clearBasket() {

    this.mind.clear();
    this.valueOfBasket = 0;

  }

  /** This method is used to get the id's and counts of the products in a basket.
   *@return Map String, Integer
   */
  public Map < String, Integer > getBasketProductMap() {
    return this.mind;
  }

	/** This method is used to get the value of the products in a basket.
	 *@return int
	 */
	public int getBasketValue() {
		return this.valueOfBasket;
	}

	/** This method is used to 2et the value of the products in a basket.
	 *@param int value of basket
	 *@return int
		*/
	public int setBasketValue(int value) {
		this.valueOfBasket = value;
		return this.valueOfBasket;
	}

  /** This method is used to print the info in the basket.
   * @return String simple text of the basket info
   */
  public String toString() {
    return "Basket id: " + this.id + " Products:  " + this.mind + " Value: " + this.valueOfBasket;
  }
}
