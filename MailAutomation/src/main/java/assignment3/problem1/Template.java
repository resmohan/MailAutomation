package assignment3.problem1;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents the template object which is used to represent an email or letter template
 * @author resmohan
 */
public class Template {

  private String content;
  private Customer customer;
  private String name;

  private static final String SQUARE_BRACE_PATTERN = "\\[\\[(.*?)\\]\\]";
  private static final int ZERO = 0;
  private static final int TWO = 2;

  /**
   * Creates a new instance of template object given the content and customer
   * @param content template content
   * @param customer customer
   */
  public Template(String content, Customer customer) {
    this.content = content;
    this.customer = customer;
    this.name = String.valueOf(UUID.randomUUID());
  }

  /**
   * This method is used to replace the customer attributes in the template with corresponding values
   */
  public void processTemplate(){
    Map<String,String> attrsAndValues = this.customer.getAttrsAndValues();

    Pattern pattern = Pattern.compile(SQUARE_BRACE_PATTERN);
    Matcher matcher = pattern.matcher(this.content);

    while(matcher.find()){
      String attributeName = matcher.group(ZERO);
      attributeName = attributeName.substring(TWO,attributeName.length() - TWO);

      this.content = this.content.replace(matcher.group(ZERO), attrsAndValues.get(attributeName));
    }
  }

  /**
   * Gives the content of template
   * @return template content
   */
  public String getContent() {
    return content;
  }

  /**
   * Gives the name of template
   * @return template name
   */
  public String getName() {
    return name;
  }

  /**
   * This method is used to compare two instances of template objects
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
    Template template = (Template) o;
    return Objects.equals(content, template.content) && Objects.equals(customer,
        template.customer) && Objects.equals(name, template.name);
  }

  /**
   * Gives the hashcode of given template instance
   * @return hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(content, customer, name);
  }

  /**
   * Gives the string representation of given template instance
   * @return string representation
   */
  @Override
  public String toString() {
    return "Template{" +
        "content='" + content + '\'' +
        ", customer=" + customer +
        ", name='" + name + '\'' +
        '}';
  }
}
