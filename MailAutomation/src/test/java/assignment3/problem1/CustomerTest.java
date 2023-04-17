package assignment3.problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {

  private Customer customer;
  @BeforeEach
  void setUp() {
    Map<String,String> attrsAndValues = new HashMap<>();
    attrsAndValues.put("firstName","John");
    customer = new Customer(attrsAndValues);
  }

  @Test
  void getAttrsAndValues() {
    Assertions.assertEquals(customer.getAttrsAndValues(), customer.getAttrsAndValues());
  }

  @Test
  void testEquals() {
    Assertions.assertEquals(true, customer.equals(customer));
  }

  @Test
  void testEqualsNull() {
    Assertions.assertEquals(false, customer.equals(null));
  }

  @Test
  void testEqualsDiff() {
    Assertions.assertEquals(false, customer.equals("null"));
  }

  @Test
  void testEqualsDiffCust() {
    Assertions.assertEquals(false, customer.equals(new Customer(new HashMap<>())));
  }
  @Test
  void testHashCode() {
    Assertions.assertNotEquals(1,customer.hashCode());
  }

  @Test
  void testToString() {
    Assertions.assertNotEquals("test",customer.toString());
  }
}