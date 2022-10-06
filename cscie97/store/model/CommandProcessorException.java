package com.cscie97.store.model;
import java.io.*;
import java.util.*;

/**
 * CSCIE-97 Assignment 2
 * @ author Ellen Siminoff
 * @ date 09/30/2021
 *
 * CommandProcessorException.java
 * CommandProcessorException Class exists to return from Command Processor
 * methods in response to error. Exception captures the command that was attempted
 * and the reason for failure. In the case where commabds are read from a file the
 * line number is included.
 **/
public class CommandProcessorException extends Exception {

  private String command;
  private String reason;
  private int lineNumber;

  CommandProcessorException(String command, String reason, int lineNumber) {
    this.command = command;
    this.reason = reason;
    this.lineNumber = lineNumber;
  }

  public String toString() {
    return "command: " + this.command + " reason: " + this.reason + " lineNumber: " + this.lineNumber;

  }
}
