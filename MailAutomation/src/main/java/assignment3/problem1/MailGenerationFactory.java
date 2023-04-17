package assignment3.problem1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents the mail generation factory class which is used to generate mails
 * @author resmohan
 */
public class MailGenerationFactory {

  private String customerFilePath;
  private String templatePath;
  private String outputDirPath;

  /**
   * Creates a new instance of mail generation factory class
   * @param customerFilePath customer file path
   * @param templatePath template path
   * @param outputDirPath output directory path
   */
  public MailGenerationFactory(String customerFilePath, String templatePath, String outputDirPath) {
    this.customerFilePath = customerFilePath;
    this.templatePath = templatePath;
    this.outputDirPath = outputDirPath;
  }

  /**
   * Generates the mail
   * @throws Exception expected to throw exception
   */
  public void generateMail() throws Exception{
    CustomerSet customerSet = readAndPopulateCustomerData();
    List<Template> templates = readAndProcessTemplates(customerSet);
    writeToFiles(templates);
  }

  /**
   * This method is used to read the customer data and generate customers
   * @return set of customers
   * @throws Exception expected to throw exception
   */
  private CustomerSet readAndPopulateCustomerData() throws Exception{
    Reader reader = new BaseFileReader();
    reader.openFile(this.customerFilePath);
    List<String[]> customerData = reader.readDataAsRowColumn();
    reader.closeFile();

    CustomerSet customerSet = new CustomerSet();
    customerSet.populateCustomerData(customerData);

    return customerSet;
  }

  /**
   * This method is used to read and process the template
   * @param customerSet set of customers
   * @return list of templates
   * @throws Exception expected to throw exception
   */
  private List<Template> readAndProcessTemplates(CustomerSet customerSet) throws Exception{
    List<Template> templates = new ArrayList<>();

    String templateContent = readTemplate();//read the template content

    List<Customer> customerList = customerSet.getCustomerList();
    for (Customer customer : customerList) {//for each customer, read and process the template
      Template template = new Template(templateContent, customer);
      template.processTemplate();
      templates.add(template);
    }

    return templates;
  }

  /**
   * This method is used to write the templates
   * @param templates set of templates
   * @throws Exception expected to throw exception
   */
  private void writeToFiles(List<Template> templates) throws Exception{
    for(Template template : templates){
      BaseFileWriter baseFileWriter = new BaseFileWriter();
      baseFileWriter.createFile(this.outputDirPath + (outputDirPath.endsWith(File.separator) ? "" : File.separator) + template.getName());
      baseFileWriter.writeFile(template.getContent());
      baseFileWriter.closeFile();
    }
  }

  /**
   * This method is used to read the template
   * @return string representation of template
   * @throws Exception expected to throw exception
   */
  private String readTemplate() throws Exception{
    Reader reader = new BaseFileReader();
    reader.openFile(templatePath);
    String templateContent = reader.readDataAsWhole();
    reader.closeFile();

    return templateContent;
  }

  /**
   * This method is used to compare two instances of mail generation factory class
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
    MailGenerationFactory that = (MailGenerationFactory) o;
    return Objects.equals(customerFilePath, that.customerFilePath)
        && Objects.equals(templatePath, that.templatePath)
        && Objects.equals(outputDirPath, that.outputDirPath);
  }

  /**
   * This method is used ot generate the hashcode of given instance
   * @return hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(customerFilePath, templatePath, outputDirPath);
  }

  /**
   * This method is used to generate the string representation of given instance
   * @return string representation
   */
  @Override
  public String toString() {
    return "MailGenerationFactory{" +
        "customerFilePath='" + customerFilePath + '\'' +
        ", emailTemplatePath='" + templatePath + '\'' +
        ", outputDirPath='" + outputDirPath + '\'' +
        '}';
  }
}
