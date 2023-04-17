package assignment3.problem1;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the file reader object which is used to read any file
 * @author resmohan
 */
public class BaseFileReader implements Reader{

  private FileReader fileReader;

  /**
   * Validation message to notify that file is not open
   */
  public static final String FILE_NOT_OPEN_ERROR = "User is expected to open the file before performing any other operations on it";

  /**
   * This method is used to open a file
   * @param filePath file path
   * @throws FileNotFoundException file not found exception will be thrown if file path is not valid
   */
  @Override
  public void openFile(String filePath) throws FileNotFoundException {
    try {
      this.fileReader = new FileReader(filePath);
    }catch (FileNotFoundException fileNotFoundException){
      logErrors(fileNotFoundException.getMessage());
      throw fileNotFoundException;
    }
  }

  /**
   * This method is used to read the data in csv format
   * @return data as row column format
   * @throws Exception Exceptions may be thrown
   */
  public List<String[]> readDataAsRowColumn() throws Exception{
    try{
      validateFileOperation();

      CSVReader csvReader = new CSVReader(this.fileReader);
      return(csvReader.readAll());

    }catch(CsvValidationException | IOException exception){
      logErrors(exception.getMessage());
      throw exception;
    }
  }

  /**
   * This method is used to read the entire content of the file
   * @return entire content of file as string
   * @throws Exception IOException may be thrown
   */
  public String readDataAsWhole() throws Exception{
    try{
      validateFileOperation();

      StringBuilder templateContentSB = new StringBuilder();

      BufferedReader textReader = new BufferedReader(this.fileReader);
      String line;
      while((line = textReader.readLine()) != null)
        templateContentSB.append(line).append("\n");

      return(templateContentSB.toString());

    }catch(IOException ioException){
      logErrors(ioException.getMessage());
      throw ioException;
    }
  }

  /**
   * This method is used to close the file
   * @throws IOException IOException may be thrown
   */
  @Override
  public void closeFile() throws IOException {
    try {
      validateFileOperation();
      fileReader.close();
    }catch (IOException ioException){
      logErrors(ioException.getMessage());
      throw ioException;
    }
  }

  /**
   * This method is used to log error message
   * @param errorMessage error message
   */
  @Override
  public void logErrors(String errorMessage) {
    Logger logger = Logger.getAnonymousLogger();
    logger.log(Level.SEVERE, errorMessage);
  }

  /**
   * This method is used to check if the given file path exists
   * @param filePath file path
   * @return true or false
   */
  public boolean isFileExists(String filePath) {
    File file = new File(filePath);
    return(file.exists());
  }

  /**
   * This method is used to compare the two instances of base file reader object
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
    BaseFileReader that = (BaseFileReader) o;
    return Objects.equals(fileReader, that.fileReader);
  }

  /**
   * This method is used to generate the hashcode of given file reader instance
   * @return hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(fileReader);
  }

  /**
   * This method is used to generate the string representation of file reader instance
   * @return string representation
   */
  @Override
  public String toString() {
    return "BaseFileReader{" +
        "fileReader=" + fileReader +
        '}';
  }

  /**
   * This method is used to validate if file is open before performing any operations on it
   */
  private void validateFileOperation(){
    if(this.fileReader == null)
      throw new RuntimeException(FILE_NOT_OPEN_ERROR);
  }
}
