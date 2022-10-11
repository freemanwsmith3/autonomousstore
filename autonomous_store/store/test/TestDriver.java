//package cscie97.store.model.test;
package com.cscie97.store.test;
import com.cscie97.store.model.*;

/**

 * TestDriver.java
 * TestDriver Class exists to feed CommandProcessor a file.
 **/

public class TestDriver {

  /** This method is a main method to get the program going.
   * @param command a String argument giving the file name
   * @exception CommandProcessorException for errors
   */
  public static void main(String[] args) throws CommandProcessorException {

    CommandProcessor cP = new CommandProcessor();
    try {
      cP.processCommandFile(args[0]);
    } catch (CommandProcessorException e) {
      StdOut.println(e);
    }
  }
}
