package assignment3.problem1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Interface to read file content
 * @author resmohan
 */
public interface Reader {

  /**
   * Method to open a file given the file path
   * @param filePath file path
   * @throws FileNotFoundException expected to throw file not found exception
   */
  void openFile(String filePath) throws FileNotFoundException;

  /**
   * Method to read the data in row column format
   * @return data
   * @throws Exception expected to throw exception
   */
  List<String[]> readDataAsRowColumn() throws Exception;

  /**
   * Method to read the data as a string
   * @return data
   * @throws Exception expected to throw exception
   */
  String readDataAsWhole() throws Exception;

  /**
   * Method to close the file
   * @throws IOException expected to throw IOException
   */
  void closeFile() throws IOException;

  /**
   * Method to log error message
   * @param errorMessage error message
   */
  void logErrors(String errorMessage);
}
