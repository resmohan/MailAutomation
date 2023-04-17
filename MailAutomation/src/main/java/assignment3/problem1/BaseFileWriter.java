package assignment3.problem1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the file writer object which is used to write content into files
 * @author resmohan
 */
public class BaseFileWriter implements Writer{

  private FileWriter fileWriter;
  /**
   * Validation message to notify that file should be created
   */
  public static final String FILE_NOT_CREATED_ERROR = "User is expected to create the file before performing any other operations on it";

  /**
   * Create a new file in the given file path
   * @param filePath file path
   * @throws IOException expected to throw IOException
   */
  @Override
  public void createFile(String filePath) throws IOException {
    try{
      fileWriter = new FileWriter(filePath);
    }catch (IOException ioException){
      logErrors(ioException.getMessage());
      throw ioException;
    }
  }

  /**
   * This method is used to write the given content into file
   * @param content content
   * @throws IOException expected to throw IOException
   */
  @Override
  public void writeFile(String content) throws IOException {
    try{
      validateFileOperation();
      fileWriter.write(content);
    }catch (IOException ioException){
      logErrors(ioException.getMessage());
      throw ioException;
    }
  }

  /**
   * This method is used to close the file
   * @throws IOException expected to throw IOException
   */
  @Override
  public void closeFile() throws IOException {
    try{
      validateFileOperation();
      fileWriter.close();
    }catch (IOException ioException){
      logErrors(ioException.getMessage());
      throw ioException;
    }
  }

  /**
   * This method is used to log the given error message
   * @param errorMessage error message
   */
  @Override
  public void logErrors(String errorMessage) {
    Logger logger = Logger.getAnonymousLogger();
    logger.log(Level.SEVERE, errorMessage);
  }

  /**
   * This method is used to compare the two instances of file writer objects
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
    BaseFileWriter that = (BaseFileWriter) o;
    return Objects.equals(fileWriter, that.fileWriter);
  }

  /**
   * This method is used to generate the hash code of given object
   * @return hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(fileWriter);
  }

  /**
   * This method is used to generate the string representation of given object
   * @return string representation
   */
  @Override
  public String toString() {
    return "BaseFileWriter{" +
        "fileWriter=" + fileWriter +
        '}';
  }

  /**
   * This method is used to confirm if file is open
   */
  private void validateFileOperation(){
    if(this.fileWriter == null)
      throw new RuntimeException(FILE_NOT_CREATED_ERROR);
  }
}
