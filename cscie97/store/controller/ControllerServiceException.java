package com.cscie97.store.controller;
import java.util.*;
import java.io.*;
import java.util.*;

/**
 * CSCIE-97 Assignment 3
 * Freeman Smith
 *
 * Store24X7ServiceException.java
 * Store24X7ServiceException Class exists to return from Store24X7Service
 * methods in response to error. Exception captures the action and the reason
 * for failure.
 **/
public class ControllerServiceException extends Exception {

  public String a;
  public String r;

  public ControllerServiceException(String action, String reason) {
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
