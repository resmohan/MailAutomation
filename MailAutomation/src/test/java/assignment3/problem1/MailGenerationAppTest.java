package assignment3.problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MailGenerationAppTest {

  @Test
  void mainEmail() {
    MailGenerationApp mailGenerationApp = new MailGenerationApp();
    String[] args = new String[]{"--csv-file","CustomerData"+ File.separator+"insurance-company-members.csv","--email-template",
        "Emails"+File.separator+"EmailTemplate1.txt","--email","--output-dir",
        "Output"};
    Assertions.assertDoesNotThrow(() -> MailGenerationApp.main(args));
  }

  @Test
  void mainLetter() {
    MailGenerationApp mailGenerationApp = new MailGenerationApp();
    String[] args = new String[]{"--csv-file","CustomerData"+File.separator+"insurance-company-members.csv","--letter-template",
        "Letters"+File.separator+"LetterTemplate1.txt","--letter","--output-dir",
        "Output"};
    Assertions.assertDoesNotThrow(() -> MailGenerationApp.main(args));
  }

  @Test
  void mainException() {
    String[] args = new String[]{"test"};
    Assertions.assertThrows(RuntimeException.class,() -> MailGenerationApp.main(args));
  }
  @Test
  void mainNoArgException() {
    Assertions.assertThrows(RuntimeException.class,() -> MailGenerationApp.main(null));
  }
}