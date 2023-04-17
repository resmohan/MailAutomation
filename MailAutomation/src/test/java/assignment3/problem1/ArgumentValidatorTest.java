package assignment3.problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArgumentValidatorTest {

  private ArgumentValidator argumentValidator;
  String[] args = null;
  @BeforeEach
  void setUp() {
    args = new String[]{"--csv-file","CustomerData"+ File.separator+"insurance-company-members.csv","--email-template",
                    "Emails"+File.separator+"EmailTemplate1.txt","--email","--output-dir",
                    "Output"};
  }

  @Test
  void validateEmailArguments() {
    argumentValidator = new ArgumentValidator(args);
    Map<String,String> argumentMap = argumentValidator.validateArguments();
    Assertions.assertNotNull(argumentMap.get("email-template"));
  }

  @Test
  void validateLetterArguments() {
    args[2] = "--letter-template";
    args[3] = "Letters"+File.separator+"LetterTemplate1.txt";
    args[4] = "--letter";
    argumentValidator = new ArgumentValidator(args);
    Map<String,String> argumentMap = argumentValidator.validateArguments();
    Assertions.assertNotNull(argumentMap.get("letter-template"));
  }
  @Test
  void validateInvalidArguments() {
    args[2] = "--letter-template1";
    argumentValidator = new ArgumentValidator(args);
    Exception exception = Assertions.assertThrows(RuntimeException.class, () -> argumentValidator.validateArguments());
    Assertions.assertEquals(String.format(ArgumentValidator.INVALID_ARGUMENT_ERROR,args[2]) + ArgumentValidator.SAMPLE_USAGE_MESSAGE, exception.getMessage());
  }

  @Test
  void validateInvalidPath() {
    args[6] = "--letter";
    argumentValidator = new ArgumentValidator(args);
    Exception exception = Assertions.assertThrows(RuntimeException.class, () -> argumentValidator.validateArguments());
    Assertions.assertEquals(String.format(ArgumentValidator.MISSING_PATH_ERROR,args[5]) + ArgumentValidator.SAMPLE_USAGE_MESSAGE, exception.getMessage());
  }

  @Test
  void validateCsvPath() {
    String[] args1 = new String[]{"--email"};
    argumentValidator = new ArgumentValidator(args1);
    Exception exception = Assertions.assertThrows(RuntimeException.class, () -> argumentValidator.validateArguments());
    Assertions.assertEquals(String.format(ArgumentValidator.MISSING_PATH_ERROR, "--csv-file") + ArgumentValidator.SAMPLE_USAGE_MESSAGE, exception.getMessage());
  }

  @Test
  void validateOutputPath() {
    String[] args1 = new String[]{"--csv-file","CustomerData"+File.separator+"insurance-company-members.csv"};
    argumentValidator = new ArgumentValidator(args1);
    Exception exception = Assertions.assertThrows(RuntimeException.class, () -> argumentValidator.validateArguments());
    Assertions.assertEquals(String.format(ArgumentValidator.MISSING_PATH_ERROR, "--output-dir") + ArgumentValidator.SAMPLE_USAGE_MESSAGE, exception.getMessage());
  }

  @Test
  void validateBothTemplate() {
    String[] args1 = new String[]{"--csv-file","CustomerData"+File.separator+"insurance-company-members.csv",
        "--output-dir", "Output"};
    argumentValidator = new ArgumentValidator(args1);
    Exception exception = Assertions.assertThrows(RuntimeException.class, () -> argumentValidator.validateArguments());
    Assertions.assertEquals(ArgumentValidator.EITHER_EXPECTED_ERROR + ArgumentValidator.SAMPLE_USAGE_MESSAGE, exception.getMessage());
  }

  @Test
  void validateBothTemplatePath() {
    String[] args1 = new String[]{"--csv-file","CustomerData"+File.separator+"insurance-company-members.csv",
        "--output-dir", "Output","--email"};
    argumentValidator = new ArgumentValidator(args1);
    Exception exception = Assertions.assertThrows(RuntimeException.class, () -> argumentValidator.validateArguments());
    Assertions.assertEquals(ArgumentValidator.EITHER_PATH_ERROR + ArgumentValidator.SAMPLE_USAGE_MESSAGE, exception.getMessage());
  }

  @Test
  void validateEmailTemplatePath() {
    String[] args1 = new String[]{"--csv-file","CustomerData"+File.separator+"insurance-company-members.csv",
        "--output-dir", "Output","--email",
        "--letter-template","Letters"+File.separator+"LetterTemplate1.txt"};
    argumentValidator = new ArgumentValidator(args1);
    Exception exception = Assertions.assertThrows(RuntimeException.class, () -> argumentValidator.validateArguments());
    Assertions.assertEquals(ArgumentValidator.MISSING_EMAIL_ERROR + ArgumentValidator.SAMPLE_USAGE_MESSAGE, exception.getMessage());
  }

  @Test
  void validateLetterTemplatePath() {
    String[] args1 = new String[]{"--csv-file","CustomerData"+File.separator+"insurance-company-members.csv",
        "--output-dir", "Output","--letter",
        "--email-template","Emails"+File.separator+"EmailTemplate1.txt"};
    argumentValidator = new ArgumentValidator(args1);
    Exception exception = Assertions.assertThrows(RuntimeException.class, () -> argumentValidator.validateArguments());
    Assertions.assertEquals(ArgumentValidator.MISSING_LETTER_ERROR + ArgumentValidator.SAMPLE_USAGE_MESSAGE, exception.getMessage());
  }

  @Test
  void validatePath() {
    String[] args1 = new String[]{"--csv-file","CustomerData1"+File.separator+"insurance-company-members.csv",
        "--output-dir", "Output","--email",
        "--email-template","Emails"+File.separator+"EmailTemplate1.txt"};
    argumentValidator = new ArgumentValidator(args1);
    Exception exception = Assertions.assertThrows(RuntimeException.class, () -> argumentValidator.validateArguments());
    Assertions.assertEquals(String.format(ArgumentValidator.INVALID_PATH_ERROR, "--csv-file"), exception.getMessage());
  }
}