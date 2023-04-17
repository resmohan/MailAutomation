package assignment3.problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BaseFileWriterTest {

  private BaseFileWriter baseFileWriter;
  @BeforeEach
  void setUp() {
    baseFileWriter = new BaseFileWriter();
  }

  @Test
  void createFile() {
    Assertions.assertDoesNotThrow(() -> baseFileWriter.createFile("Output"+ File.separator+"Test1.txt"));
  }

  @Test
  void writeFile() throws IOException {
    baseFileWriter.createFile("Output"+File.separator+"Test1.txt");
    Assertions.assertDoesNotThrow(() -> baseFileWriter.writeFile("test"));
  }

  @Test
  void writeFileError() {
    RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> baseFileWriter.writeFile(""));
    Assertions.assertEquals(exception.getMessage(), BaseFileWriter.FILE_NOT_CREATED_ERROR);
  }

  @Test
  void closeFile() throws IOException {
    baseFileWriter.createFile("Output"+File.separator+"Test1.txt");
    Assertions.assertDoesNotThrow(() -> baseFileWriter.closeFile());
  }

  @Test
  void closeFileError() {
    RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> baseFileWriter.closeFile());
    Assertions.assertEquals(exception.getMessage(), BaseFileWriter.FILE_NOT_CREATED_ERROR);
  }

  @Test
  void logErrors() {
    Assertions.assertDoesNotThrow(() -> baseFileWriter.logErrors("test11"));
  }

  @Test
  void testEquals() {
    Assertions.assertEquals(true, baseFileWriter.equals(baseFileWriter));
  }

  @Test
  void testEqualsNull() {
    Assertions.assertEquals(false, baseFileWriter.equals(null));
  }

  @Test
  void testEqualsDiff() {
    Assertions.assertEquals(false, baseFileWriter.equals("null"));
  }

  @Test
  void testEqualsSame() {
    Assertions.assertEquals(true, baseFileWriter.equals(new BaseFileWriter()));
  }

  @Test
  void testHashCode() {
    Assertions.assertNotEquals(1,baseFileWriter.hashCode());
  }

  @Test
  void testToString() {
    Assertions.assertNotEquals("test",baseFileWriter.toString());
  }
}