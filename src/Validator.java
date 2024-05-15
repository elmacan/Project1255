import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static ArrayList<String> usedIDs = new ArrayList<>();


    public boolean isValidID(String id){
        // Check if the ID is already used
        if (usedIDs.contains(id)) {
            return false; // ID is not unique
        }

        String regex = "^[a-zA-Z][a-zA-Z0-9_]*$"; //doğru regex

        // Compile the regular expression
        Pattern pattern = Pattern.compile(regex);

        // Create matcher object
        Matcher matcher = pattern.matcher(id);

        // Check if the string matches the condition
        if (matcher.matches()) {
            usedIDs.add(id); // Add the ID to the list of used IDs
            return true;
        } else {
            return false;
        }
    }

    public boolean isNumber(String str) {
        // Regular expression to match numbers (integer or decimal)
        String regex = "[0-9]*[.]?[0-9]*";   //doğru regex

        // Compile the regular expression
        Pattern pattern = Pattern.compile(regex);

        // Create matcher object
        Matcher matcher = pattern.matcher(str);

        // Check if the string matches the condition
        return matcher.matches();
    }

    public boolean iscontainsClosingParenthesis(String str) {

        return str.indexOf(')') != -1;
    }
    public boolean hasClosingParenthesisAtLast(String str) {
        char lastChar = str.charAt(str.length() - 1);

        if(lastChar == ')') return true;

        return false;
    }



}
