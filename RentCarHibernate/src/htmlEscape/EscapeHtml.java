package htmlEscape;

public class EscapeHtml {
  public static String htmlEscape(String text) {
    if (text == null) {
      return "";
    }
    StringBuilder escapedText = new StringBuilder();
    for (int i = 0; i < text.length(); i++) {
      char ch = text.charAt(i);
      if (ch == '<')
        escapedText.append("&lt;");
      else if (ch == '>')
        escapedText.append("&gt;");
      else if (ch == '&')
        escapedText.append("&amp;");
      else if (ch == '\"')
        escapedText.append("&quot;");
      else
        escapedText.append(ch);
    }
    String result = escapedText.toString();
    return result;
  }

}
