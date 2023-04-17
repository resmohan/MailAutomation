package assignment3.problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BaseFileReaderTest {

  private BaseFileReader baseFileReader;
  @BeforeEach
  void setUp() {
    baseFileReader = new BaseFileReader();
  }

  @Test
  void openFile() {
    Assertions.assertThrows(FileNotFoundException.class, () -> baseFileReader.openFile("test"));
  }

  @Test
  void readDataAsRowColumn() throws Exception {
    baseFileReader.openFile("CustomerData"+ File.separator+"insurance-company-members.csv");
    Assertions.assertNotNull(baseFileReader.readDataAsRowColumn());
  }

  @Test
  void readDataAsRowColumnError() throws Exception {
    RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> baseFileReader.readDataAsRowColumn());
    Assertions.assertEquals(exception.getMessage(), BaseFileReader.FILE_NOT_OPEN_ERROR);
  }

  @Test
  void readDataAsWhole() throws Exception {
    baseFileReader.openFile("Letters"+File.separator+"LetterTemplate1.txt");
    Assertions.assertNotNull(baseFileReader.readDataAsWhole());
  }

  @Test
  void readDataAsWholeError() throws Exception {
    RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> baseFileReader.readDataAsWhole());
    Assertions.assertEquals(exception.getMessage(), BaseFileReader.FILE_NOT_OPEN_ERROR);
  }

  @Test
  void closeFile() throws FileNotFoundException {
    baseFileReader.openFile("CustomerData"+File.separator+"insurance-company-members.csv");
    Assertions.assertDoesNotThrow(() -> baseFileReader.closeFile());
  }

  @Test
  void closeFileError() {
    RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> baseFileReader.closeFile());
    Assertions.assertEquals(exception.getMessage(), BaseFileReader.FILE_NOT_OPEN_ERROR);
  }

  @Test
  void isFileExists() {
    Assertions.assertEquals(true, baseFileReader.isFileExists("CustomerData"+File.separator+"insurance-company-members.csv"));
  }

  @Test
  void testEquals() {
    Assertions.assertEquals(true, baseFileReader.equals(baseFileReader));
  }

  @Test
  void testEqualsNull() {
    Assertions.assertEquals(false,baseFileReader.equals(null));
  }

  @Test
  void testEqualsDiffType() {
    Assertions.assertEquals(false,baseFileReader.equals("null"));
  }

  @Test
  void testEqualsSame() {
    Assertions.assertEquals(true,baseFileReader.equals(new BaseFileReader()));
  }

  @Test
  void testHashCode() {
    Assertions.assertNotEquals(100,baseFileReader.hashCode());
  }

  @Test
  void testToString() {
    Assertions.assertNotEquals("test",baseFileReader.toString());
  }
}