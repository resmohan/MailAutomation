package assignment3.problem1;

import java.io.IOException;

/**
 * Represents the writer interface which is used to write files
 * @author resmohan
 */
public interface Writer {

  /**
   * Create a new file given the file path
   * @param filePath file path
   * @throws IOException expected to throw IOException
   */
  void createFile(String filePath) throws IOException;

  /**
   * Write the content to file
   * @param content content
   * @throws IOException expected to throw IOException
   */
  void writeFile(String content) throws IOException;

  /**
   * Close the file
   * @throws IOException expected to throw IOException
   */
  void closeFile() throws IOException;

  /**
   * Log the error message
   * @param errorMessage error message
   */
  void logErrors(String errorMessage);
}
