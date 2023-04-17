package assignment3.problem1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the CustomerSet object which is used to track the set of customers in the system
 * @author resmohan
 */
public class CustomerSet {

  private List<Customer> customerList;

  private static final int ZERO = 0;
  private static final int ONE = 1;

  /**
   * Creates a new instance of customer set object
   */
  public CustomerSet() {
    this.customerList = new ArrayList<>();
  }

  /**
   * Adds a new customer to the list
   * @param customer customer instance
   */
  public void addCustomer(Customer customer){
    this.customerList.add(customer);
  }

  /**
   * This method is used to generate the customer and store them as a list
   * @param customerData attribute values of each customer
   */
  public void populateCustomerData(List<String[]> customerData) {
    String[] header = customerData.get(ZERO);//customer attribute headers

    for (int i = ONE; i < customerData.size(); i++) {
      String[] data = customerData.get(i);//customer attribute values

      if(data.length == ONE && data[ZERO].isEmpty())//skip the blank line
        continue;

      Map<String, String> customerAttrsAndValues = new HashMap<>();
      for (int j = ZERO; j < data.length && j < header.length; j++) {
        customerAttrsAndValues.put(header[j], data[j]);
      }

      //System.out.println("customerAttrsAndValues : " + customerAttrsAndValues);
      Customer customer = new Customer(customerAttrsAndValues);
      this.addCustomer(customer);
    }
  }

  /**
   * Provides the list of customers in the system
   * @return list of customers
   */
  public List<Customer> getCustomerList() {
    return customerList;
  }

  /**
   * This method is used to compare two instances of customer set objects
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
    CustomerSet that = (CustomerSet) o;
    return Objects.equals(customerList, that.customerList);
  }

  /**
   * This method is used to generate the hashcode of given instance
   * @return hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(customerList);
  }

  /**
   * This method is used to generate the string representation of given instance
   * @return string representation
   */
  @Override
  public String toString() {
    return "CustomerSet{" +
        "customerList=" + customerList +
        '}';
  }
}
