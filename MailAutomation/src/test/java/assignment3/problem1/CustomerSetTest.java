package assignment3.problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerSetTest {

  private CustomerSet customerSet;
  @BeforeEach
  void setUp() {
    customerSet = new CustomerSet();
  }

  @Test
  void addCustomer() {
    customerSet.addCustomer(new Customer(new HashMap<>()));
    Assertions.assertEquals(1, customerSet.getCustomerList().size());
  }

  @Test
  void populateCustomerData() {
    String[] header = new String[1];
    String[] data = new String[1];
    String[] data1 = new String[1];
    header[0] = "firstName";
    data[0] = "John";
    data1[0] = "";
    List<String[]> customerData = new ArrayList<>();
    customerData.add(header);
    customerData.add(data);
    customerData.add(data1);

    customerSet.populateCustomerData(customerData);
    Assertions.assertEquals(1, customerSet.getCustomerList().size());
  }
  @Test
  void testEquals() {
    Assertions.assertEquals(true, customerSet.equals(customerSet));
  }
  @Test
  void testEqualsNull() {
    Assertions.assertEquals(false, customerSet.equals(null));
  }

  @Test
  void testEqualsDiff() {
    Assertions.assertEquals(false, customerSet.equals("null"));
  }

  @Test
  void testEqualsSameCustomer() {
    Assertions.assertEquals(true, customerSet.equals(new CustomerSet()));
  }
  @Test
  void testHashCode() {
    Assertions.assertNotEquals(1,customerSet.hashCode());
  }

  @Test
  void testToString() {
    Assertions.assertNotEquals("test",customerSet.toString());
  }
}