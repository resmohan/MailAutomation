package assignment3.problem1;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the mail generation application which is used to generate mails
 * @author resmohan
 */
public class MailGenerationApp {

  private static final String NO_ARGUMENT_ERROR = "\nError: This application is expecting certain parameters to be passed. Please check the readme.md file";
  private static final String EMAIL_TEMPLATE = "email-template";
  private static final String LETTER_TEMPLATE = "letter-template";
  private static final String CSV_FILE = "csv-file";
  private static final String OUTPUT_DIR = "output-dir";

  /**
   * Main method used to generate mails
   * @param args input arguments
   * @throws Exception throws exception
   */
  public static void main(String[] args) throws Exception {
    try {
      if(args == null)
        throw new RuntimeException(NO_ARGUMENT_ERROR);

      ArgumentValidator argumentValidator = new ArgumentValidator(args);//validate the arguments
      Map<String, String> argumentMap = argumentValidator.validateArguments();

      String customerFilePath = argumentMap.get(CSV_FILE);
      String templatePath =
          argumentMap.get(EMAIL_TEMPLATE) != null ? argumentMap.get(EMAIL_TEMPLATE)
              : argumentMap.get(LETTER_TEMPLATE);
      String outputPath = argumentMap.get(OUTPUT_DIR);

      MailGenerationFactory mailGenerationFactory = new MailGenerationFactory(customerFilePath,
          templatePath, outputPath);//generate the mail
      mailGenerationFactory.generateMail();

    }catch (Exception exception){
      Logger logger = Logger.getAnonymousLogger();
      logger.log(Level.SEVERE, exception.getMessage());
      throw exception;
    }
  }
}
