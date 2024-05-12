import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static ArrayList<String> usedIDs = new ArrayList<>();


    public static boolean isValidID(String id){
        // Check if the ID is already used
        if (usedIDs.contains(id)) {
            return false; // ID is not unique
        }

        String regex = "^[a-zA-Z][a-zA-Z0-9_]*$";

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





}
