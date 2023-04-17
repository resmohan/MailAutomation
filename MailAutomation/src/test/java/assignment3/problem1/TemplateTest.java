package assignment3.problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TemplateTest {

  private Template template;
  @BeforeEach
  void setUp() {
    Map<String,String> custValueMap = new HashMap<>();
    custValueMap.put("firstName","John");
    template = new Template("Hi [[firstName]]",new Customer(custValueMap));
  }

  @Test
  void processTemplate() {
    template.processTemplate();
    Assertions.assertEquals("Hi John",template.getContent());
  }

  @Test
  void getName() {
    Assertions.assertNotNull(template.getName());
  }

  @Test
  void testEquals() {
    Assertions.assertEquals(true, template.equals(template));
  }
  @Test
  void testEqualsNull() {
    Assertions.assertEquals(false, template.equals(null));
  }

  @Test
  void testEqualsDiffObj() {
    Assertions.assertEquals(false, template.equals("null"));
  }

  @Test
  void testEqualsDiff() {
    Assertions.assertEquals(false, template.equals(new Template(null,null)));
  }
  @Test
  void testEqualsDiff1() {
    Assertions.assertEquals(false, template.equals(new Template("Hi [[firstName]]",null)));
  }
  @Test
  void testEqualsDiff2() {
    Map<String,String> custValueMap = new HashMap<>();
    custValueMap.put("firstName","John");
    Assertions.assertEquals(false, template.equals(new Template("Hi [[firstName]]",new Customer(custValueMap))));
  }

  @Test
  void testHashCode() {
    Assertions.assertNotEquals(1,template.hashCode());
  }

  @Test
  void testToString() {
    Assertions.assertNotEquals("test",template.toString());
  }
}