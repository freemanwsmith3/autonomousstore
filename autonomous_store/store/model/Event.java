package com.cscie97.store.model;
import com.cscie97.store.model.*;
import java.util.*;

/**
 *
 * Device.java
 * Device implements the functions of a device.
 **/
public class Event {

  // instance variables
  public String deviceId;
  public String storeId;
  public EventType type;
  public String[] eventText;

  //constructor
  public Event(String deviceId, String storeId, EventType type, String[] eventText) {
    this.deviceId = deviceId;
    this.storeId = storeId;
    this.type = type;
    this.eventText = eventText;
  }

  /** This method is used to get device's id
   *@return String deviceId
  */
  public String getDeviceId() {
  return this.deviceId;
  }

  /** This method is used to get device's storeId
   *@return String storeId
   */
  public String getStoreId() {
    return this.storeId;
  }


  /** This method is used to get device's type
   *@return DeviceType type
   */
  public EventType getEventType() {
    return this.type;
  }

  /** This method is used to get device's eventText
   *@return String[] eventText
   */
  public String[] getEventText() {
    return this.eventText;
  }


  /** This method is used to set device's id
   *@param String id
   *@return String id
   */
  protected String setDeviceId(String deviceId) {
    this.deviceId = deviceId;
    return this.deviceId;
  }

  /** This method is used to set device's storeId
   *@param String storeId
   *@return String storeId
   */
  protected String setStoreId(String storeId) {
    this.storeId = storeId;
    return this.storeId;
  }


  /** This method is used to set device's type
   *@param EventType type
   *@return DeviceType type
   */
  protected EventType setEventType(EventType type) {
    this.type = type;
    return this.type;
  }

  /** This method is used to set device's eventText
   *@param String[] eventText
   *@return String[] eventText
   */
  protected String[] setEventText(String[] eventText) {
    this.eventText = eventText;
    return this.eventText;
  }


  /** This method is used to print the info in the device.
   * @return String simple text of the device info
   */
  public String toString() {
    return "Device Id: " + this.deviceId + "store:"  + this.storeId + " eventType: " + this.type + " eventText: " + this.eventText;
  }

}
