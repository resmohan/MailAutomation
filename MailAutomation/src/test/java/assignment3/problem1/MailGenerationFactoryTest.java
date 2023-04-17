package assignment3.problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MailGenerationFactoryTest {

  private MailGenerationFactory mailGenerationFactory;
  @BeforeEach
  void setUp() {
    mailGenerationFactory = new MailGenerationFactory("CustomerData"+ File.separator+"insurance-company-members.csv",
        "Emails"+File.separator+"EmailTemplate1.txt",
        "Output");
  }

  @Test
  void generateMail() {
    Assertions.assertDoesNotThrow(() -> mailGenerationFactory.generateMail());
  }

  @Test
  void testEquals() {
    Assertions.assertEquals(true, mailGenerationFactory.equals(mailGenerationFactory));
  }
  @Test
  void testEqualsNull() {
    Assertions.assertEquals(false, mailGenerationFactory.equals(null));
  }

  @Test
  void testEqualsDiffObj() {
    Assertions.assertEquals(false, mailGenerationFactory.equals("null"));
  }

  @Test
  void testEqualsDiff() {
    Assertions.assertEquals(false, mailGenerationFactory.equals(new MailGenerationFactory(null,null,null)));
  }
  @Test
  void testEqualsDiff1() {
    Assertions.assertEquals(false, mailGenerationFactory.equals(new MailGenerationFactory("CustomerData"+File.separator+"insurance-company-members.csv",null,null)));
  }

  @Test
  void testEqualsDiff2() {
    Assertions.assertEquals(false, mailGenerationFactory.equals(
        new MailGenerationFactory("CustomerData"+File.separator+"insurance-company-members.csv",
            "Emails"+File.separator+"EmailTemplate1.txt",null)));
  }
  @Test
  void testHashCode() {
    Assertions.assertNotEquals(1,mailGenerationFactory.hashCode());
  }

  @Test
  void testToString() {
    Assertions.assertNotEquals("test",mailGenerationFactory.toString());
  }
}