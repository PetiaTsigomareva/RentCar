package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.TestCase;

import org.junit.BeforeClass;
import org.junit.Test;

public class IsValidFieldTestCase extends TestCase {

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Test
  public void test() {
    String text = "Sofia kv.Bucston bl.21";
    String regex = "([A-Z�-�][a-z�-�]+ \\t{1})([a-z�-�]+\\.{1})([A-Z�-�][a-z�-�]+\\t{1})([a-z�-�]+ \\.{1}\\d+)";
    if (isValidField(regex, text)) {
      System.out.println("The enter text is:" + text + "\n The formmat must be:" + regex + "\n The result is valid, formmat is Sofia kv.Bucston bl.21");
    } else {
      System.out.println("The enter text is: " + text
          + "\n The formmat must be  \"See kv.Frr bl.21 \" \n The result is incorrect, the formmat is diferent of searching Sofia kv.Bucston bl.21");
    }

    fail("Not yet implemented");
  }

  private boolean isValidField(String regex, String text) {
    boolean result;

    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(text);

    result = matcher.matches();

    return result;
  }
}
