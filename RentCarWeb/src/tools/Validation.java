package tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
  /**
   * The method return true in case the fieldName parameter matches the regex
   * provided in the regex parameter
   * 
   * @param regex
   *          non-null valid regex to match upon
   * @param fieldName
   *          non-null field to match
   * @return true in case the fieldName parameter matches the regex provided in
   *         the regex parameter
   */
  public static boolean isValidField(String regex, String fieldName) {
    boolean result;

    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(fieldName);

    result = matcher.matches();

    return result;
  }

  /**
   * @return the regular expression used to match the bulgarian EGN number
   */
  public static String getEGNRegex() {
    StringBuffer result = new StringBuffer();

    String daysForMonthWith31Days = "(0[1-9]|1[0-9]|2[0-9]|3[01])";
    String daysForMonthWith30Days = "(0[1-9]|1[0-9]|2[0-9]|3[0])";
    String daysForMonthWith29Days = "(0[1-9]|1[0-9]|2[0-9])";
    String daysForMonthWith28Days = "(0[1-9]|1[0-9]|2[0-8])";

    String month31days1 = "(0[13578])" + daysForMonthWith31Days;
    String month31days2 = "(4[13578])" + daysForMonthWith31Days;
    String month31days3 = "(1[02])" + daysForMonthWith31Days;
    String month31days4 = "(5[02])" + daysForMonthWith31Days;

    String month30days1 = "(0[469])" + daysForMonthWith30Days;
    String month30days2 = "(11)" + daysForMonthWith30Days;
    String month30days3 = "(4[469])" + daysForMonthWith30Days;
    String month30days4 = "(51)" + daysForMonthWith30Days;

    String month29days = "(02)" + daysForMonthWith29Days;
    String month28days = "(02)" + daysForMonthWith28Days;

    result.append("([0-9]{2})");
    result.append("(");
    result.append(month31days1);
    result.append("|" + month31days2);
    result.append(")");
    result.append("([0-9]{4})");

    return result.toString();
  }
}
