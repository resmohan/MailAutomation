package assignment3.problem1;

import java.util.Map;
import java.util.Objects;

/**
 * Represents the customer object which contains the properties stored as name value pair
 * @author resmohan
 */
public class Customer {

  private Map<String,String> attrsAndValues;

  /**
   * Creates a new instance of customer object given the properties as name value pair
   * @param attrsAndValues customer attribute values
   */
  public Customer(Map<String, String> attrsAndValues) {
    this.attrsAndValues = attrsAndValues;
  }

  /**
   * Gives the customer attribute values
   * @return map of properties
   */
  public Map<String, String> getAttrsAndValues() {
    return attrsAndValues;
  }

  /**
   * This method is used to compare two instances of customer object
   * @param o instance to be compared
   * @return true or false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Customer customer = (Customer) o;
    return Objects.equals(attrsAndValues, customer.attrsAndValues);
  }

  /**
   * Generate the hashcode of given customer instance
   * @return hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(attrsAndValues);
  }

  /**
   * Gives the string representation of given object
   * @return string representation
   */
  @Override
  public String toString() {
    return "Customer{" +
        "attrsAndValues=" + attrsAndValues +
        '}';
  }
}
