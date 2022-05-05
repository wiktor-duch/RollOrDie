package inputHandling;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputHandler {
    /**
     * Converts String input to 
     * @param input
     * @return
     */
    public boolean coverToBoolean(String input) {
        if (input.equals("y")
            || input.equals("Y")
            || input.equals("yes")
            || input.equals("Yes")) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isInteger(int startInt, int endInt, String input) {
        if (startInt>endInt) {
            System.out.println("Incorrect parameters passed.");
            return false;
        }
        for (int i=startInt; i<=endInt; i++) {
            if (input.equals(String.valueOf(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean checkStringsOnly(String input) {
        Pattern pattern = Pattern.compile("^[a-z]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input.toLowerCase());
        return matcher.find();
    }
    
    public boolean checkStringsOrIntegers(String input) {
        Pattern pattern = Pattern.compile("^[a-z0-9]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input.toLowerCase());
        return matcher.find();
    }
}
