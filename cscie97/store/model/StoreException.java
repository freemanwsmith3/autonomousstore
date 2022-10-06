package com.cscie97.store.model;
import java.util.*;
import java.io.*;

/**
 * CSCIE-97 Assignment 2
 * @ author Ellen Siminoff
 * @ date 09/30/2021
 *
 * StoreException.java
 * StoreException Class exists to return from Store
 * methods in response to error. Exception captures the action and the reason
 * for failure.
 **/
public class StoreException extends Exception {

  private String a;
  private String r;

  public StoreException(String action, String reason) {
    this.a = action;
    this.r = reason;
  }

  public String getAction() {
    return this.a;
  }

  public String getReason() {
    return this.r;
  }

  public String toString() {
    return "action: " + a + " reason: " + r;
  }

}
