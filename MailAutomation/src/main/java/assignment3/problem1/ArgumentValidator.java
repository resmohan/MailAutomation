package assignment3.problem1;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the argument validator class which is used to validate and parse the input arguments
 * @author resmohan
 */
public class ArgumentValidator {
  private String[] arguments;
  private Map<String,String> argumentMap;

  /**
   * Creates a new instance of argument validator class
   * @param arguments input arguments
   */
  public ArgumentValidator(String[] arguments) {
    this.arguments = arguments;
  }


  private static final String EMAIL = "email";
  private static final String LETTER = "letter";
  private static final String EMAIL_TEMPLATE = "email-template";
  private static final String LETTER_TEMPLATE = "letter-template";
  private static final String CSV_FILE = "csv-file";
  private static final String OUTPUT_DIR = "output-dir";
  private static final String ARGUMENT_PREFIX = "--";
  private static final int ARGUMENT_PREFIX_INDEX = 2;
  private static final int ZERO = 0;
  private static final String TRUE = "True";
  /**
   * Validation message to notify the invalid argument
   */
  public static final String INVALID_ARGUMENT_ERROR = "\nError: Given argument %s is not valid\n";
  /**
   * Validation message to notify that file path is not given
   */
  public static final String MISSING_PATH_ERROR = "\nError: File path is not given for argument %s\n";
  /**
   * Validation message to notify that file path is not correct
   */
  public static final String INVALID_PATH_ERROR = "\nError: File path is not correct for argument %s\n";
  /**
   * Validation message to notify that user is expected to pass either email or letter template option
   */
  public static final String EITHER_EXPECTED_ERROR = "\nError: User is expected to pass either email or letter template option\n";
  /**
   * Validation message to notify that user is expected to pass either email or letter template path
   */
  public static final String EITHER_PATH_ERROR = "\nError: User is expected to pass either email or letter template path\n";
  /**
   * Validation message to notify that email template path is not given
   */
  public static final String MISSING_EMAIL_ERROR = "\nError: --email provided but no --email-template was given.\n";
  /**
   * Validation message to notify that letter template path is not given
   */
  public static final String MISSING_LETTER_ERROR = "\nError: --letter provided but no --letter-template was given.\n";
  /**
   * Validation message to notify the sample usage content
   */
  public static final String SAMPLE_USAGE_MESSAGE = """

      Usage:
      --email  only generate email messages
      --email-template <file> accept a filename that holds the email template.
      Required if --email is used

      --letter only generate letters
      --letter-template <file> accept a filename that holds the letter template.
      Required if --letter is used

      --output-dir <path> accept the name of a folder, all output is placed in this folder
      --csv-file <path> accept the name of the csv file to process

      Examples:

      --email --email-template email-template.txt --output-dir emails --csv-file customer.csv
      --letter --letter-template letter-template.txt --output-dir letters --csv-file customer.csv
      """;

  /**
   * This method is used to validate the input arguments
   * @return returns the argument value pair
   */
  public Map<String,String> validateArguments(){
    initializeMap();

    for(int i=ZERO;i< arguments.length;i++){
      validateArgument(arguments[i]);
      i = populateArgument(i);
    }

    validateArgumentValues();
    validateFilePaths();

    return argumentMap;
  }

  /**
   * initialize the argument map
   */
  private void initializeMap(){
    argumentMap = new HashMap<>();
    argumentMap.put(EMAIL, null);
    argumentMap.put(LETTER, null);
    argumentMap.put(EMAIL_TEMPLATE, null);
    argumentMap.put(LETTER_TEMPLATE, null);
    argumentMap.put(CSV_FILE, null);
    argumentMap.put(OUTPUT_DIR, null);
  }

  /**
   * This method is used to confirm that the input argument is valid
   * @param argument input argument
   */
  private void validateArgument(String argument){
    if(argument.startsWith(ARGUMENT_PREFIX) && argumentMap.containsKey(argument.substring(ARGUMENT_PREFIX_INDEX))){
      return;
    }

    throw new RuntimeException(String.format(INVALID_ARGUMENT_ERROR,argument) + SAMPLE_USAGE_MESSAGE);
  }

  /**
   * This method is used to populate the argument value
   * @param index current index of argument string array
   * @return new index of argument string array
   */
  private int populateArgument(int index){
    String argument = arguments[index].substring(ARGUMENT_PREFIX_INDEX);

    switch (argument) {
      case EMAIL -> argumentMap.put(EMAIL, TRUE);
      case LETTER -> argumentMap.put(LETTER, TRUE);
      case CSV_FILE,OUTPUT_DIR,EMAIL_TEMPLATE,LETTER_TEMPLATE -> {
        populatePath(argument, index);
        index++;
      }
    }

    return index;
  }

  /**
   * This method is used to populate the path of an input argument
   * Currently we are expecting input arguments among csv file path, output directory, email template path or letter template path
   * @param argument argument value
   * @param index current index in string argument array
   */
  private void populatePath(String argument, int index){
    if(index+1 == arguments.length || arguments[index+1].startsWith(ARGUMENT_PREFIX)){
      throw new RuntimeException(String.format(MISSING_PATH_ERROR, ARGUMENT_PREFIX+argument) + SAMPLE_USAGE_MESSAGE);
    }

    argumentMap.put(argument,arguments[index+1]);
  }

  /**
   * This method is used to validate and throw all wrong argument value combinations
   */
  private void validateArgumentValues(){
    if(argumentMap.get(CSV_FILE) == null)
      throw new RuntimeException(String.format(MISSING_PATH_ERROR, ARGUMENT_PREFIX+CSV_FILE) + SAMPLE_USAGE_MESSAGE);

    if(argumentMap.get(OUTPUT_DIR) == null)
      throw new RuntimeException(String.format(MISSING_PATH_ERROR, ARGUMENT_PREFIX+OUTPUT_DIR) + SAMPLE_USAGE_MESSAGE);

    if((argumentMap.get(EMAIL) == null && argumentMap.get(LETTER) == null)
        || (argumentMap.get(EMAIL) != null && argumentMap.get(LETTER) != null))
      throw new RuntimeException(EITHER_EXPECTED_ERROR + SAMPLE_USAGE_MESSAGE);

    if((argumentMap.get(EMAIL_TEMPLATE) == null && argumentMap.get(LETTER_TEMPLATE) == null)
              || (argumentMap.get(EMAIL_TEMPLATE) != null && argumentMap.get(LETTER_TEMPLATE) != null))
      throw new RuntimeException(EITHER_PATH_ERROR + SAMPLE_USAGE_MESSAGE);

    if(argumentMap.get(EMAIL) != null && argumentMap.get(EMAIL_TEMPLATE) == null)
      throw new RuntimeException(MISSING_EMAIL_ERROR + SAMPLE_USAGE_MESSAGE);

    if(argumentMap.get(LETTER) != null && argumentMap.get(LETTER_TEMPLATE) == null)
      throw new RuntimeException(MISSING_LETTER_ERROR + SAMPLE_USAGE_MESSAGE);
  }

  /**
   * This method is used to validate the file paths given for csv file, output directory and template path
   * It supports both absolute path and relative path
   * For every path value, this method checks if it is a valid path
   * otherwise throw runtime exception stating that file path is invalid
   */
  private void validateFilePaths(){
    BaseFileReader fileReader = new BaseFileReader();

    argumentMap.entrySet().stream()
        .filter(keyVal -> keyVal.getValue() != null && (keyVal.getKey().equals(CSV_FILE) || keyVal.getKey().equals(OUTPUT_DIR)
                            || keyVal.getKey().equals(EMAIL_TEMPLATE) || keyVal.getKey().equals(LETTER_TEMPLATE)))
        .forEach(keyVal -> {
          if (!fileReader.isFileExists(keyVal.getValue()))
              throw new RuntimeException(String.format(INVALID_PATH_ERROR, (ARGUMENT_PREFIX + keyVal.getKey())));
        });
  }
}
