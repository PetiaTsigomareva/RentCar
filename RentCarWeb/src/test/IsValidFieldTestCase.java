package test;

import junit.framework.TestCase;

import org.junit.BeforeClass;
import org.junit.Test;

import tools.Validation;

public class IsValidFieldTestCase extends TestCase {

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Test
  public void test() {
    String text = "Sofia kv.Bucston bl.21";
    String regex = "([A-ZÀ-ß][a-zà-ÿ]+ \\t{1})([a-zà-ÿ]+\\.{1})([A-ZÀ-ß][a-zà-ÿ]+\\t{1})([a-zà-ÿ]+ \\.{1}\\d+)";
    if (Validation.isValidField(regex, text)) {
      System.out.println("The enter text is:" + text + "\n The formmat must be:" + regex + "\n The result is valid, formmat is Sofia kv.Bucston bl.21");
    } else {
      System.out.println("The enter text is: " + text
          + "\n The formmat must be  \"See kv.Frr bl.21 \" \n The result is incorrect, the formmat is diferent of searching Sofia kv.Bucston bl.21");
    }

    fail("Not yet implemented");
  }

  public void testEGNRegex() {

  }
}
