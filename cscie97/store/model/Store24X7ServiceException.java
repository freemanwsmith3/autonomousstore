package com.cscie97.store.model;
import java.util.*;
import java.io.*;
import java.util.*;

/**
 * CSCIE-97 Assignment 2
 * @ author Ellen Siminoff
 * @ date 09/30/2021
 *
 * Store24X7ServiceException.java
 * Store24X7ServiceException Class exists to return from Store24X7Service
 * methods in response to error. Exception captures the action and the reason
 * for failure.
 **/
public class Store24X7ServiceException extends Exception {

  public String a;
  public String r;

  public Store24X7ServiceException(String action, String reason) {
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
