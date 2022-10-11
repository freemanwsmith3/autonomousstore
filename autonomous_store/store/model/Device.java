package com.cscie97.store.model;
import java.util.*;

/**
 * Device.java
 * Device implements the functions of a device.
 **/
public class Device {

  // instance variables
  protected String id;
  protected String name;
  protected DeviceType type;
  protected String storeId;
  protected String aisleId;

  //constructor
  public Device(String id, String name, DeviceType type, String storeId, String aisleId) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.storeId = storeId;
    this.aisleId = aisleId;
  }

  /** This method is used to get device's id
   *@return String id
   */
  public String getDeviceId() {
    return this.id;
  }

  /** This method is used to get device's name
   *@return String name
   */
  public String getDeviceName() {
    return this.name;
  }

  /** This method is used to get device's type
   *@return DeviceType type
   */
  public DeviceType getDeviceType() {
    return this.type;
  }

  /** This method is used to get device's storeId
   *@return String storeId
   */
  public String getStoreId() {
    return this.storeId;
  }

  /** This method is used to get device's aisleId
   *@return String aisleId
   */
  public String getAisleId() {
    return this.aisleId;
  }

  /** This method is used to set device's id
   *@param String id
   *@return String id
   */
  protected String setDeviceId(String id) {
    this.id = id;
    return this.id;
  }

  /** This method is used to set device's name
   *@param String name
   *@return String name
   */
  protected String setDeviceName(String name) {
    this.name = name;
    return this.name;
  }

  /** This method is used to set device's type
   *@param DeviceType type
   *@return DeviceType type
   */
  protected DeviceType setDeviceType(DeviceType type) {
    this.type = type;
    return this.type;
  }

  /** This method is used to set device's storeId
   *@param String storeId
   *@return String storeId
   */
  protected String setStoreId(String storeId) {
    this.storeId = storeId;
    return this.storeId;
  }

  /** This method is used to set device's aisleId
   *@param String aisleId
   *@return String aisleId
   */
  public String setAisleId(String aisleId) {
    this.aisleId = aisleId;
    return this.aisleId;
  }

  /** This method is used to print the info in the device.
   * @return String simple text of the device info
   */
  public String toString() {
    return "Id: " + this.id + " name:  " + this.name + " deviceType: " + this.type +
      " location: " + this.aisleId + " , " + this.storeId;
  }

}
