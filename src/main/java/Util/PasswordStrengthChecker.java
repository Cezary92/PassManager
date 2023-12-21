package Util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordStrengthChecker {

    public enum PasswordStrength {
        WEAK, MEDIUM, STRONG
    }


     //Checks the strength of a given password.

    public static PasswordStrength checkPasswordStrength(String password) {
        if (isWeak(password)) {
            return PasswordStrength.WEAK;
        } else if (isMedium(password)) {
            return PasswordStrength.MEDIUM;
        } else {
            return PasswordStrength.STRONG;
        }
    }

     // Checks if the password is weak.
    private static boolean isWeak(String password) {
        // Check if the password is sufficiently long
        return password.length() < 8;
    }

     //Checks if the password is of medium strength.

    private static boolean isMedium(String password) {
        // Check if the password contains both lowercase and uppercase letters
        return containsLowerCase(password) && containsUpperCase(password);
    }

     //Checks if the password contains lowercase letters.

    private static boolean containsLowerCase(String password) {
        // Check if the password contains lowercase letters
        return !password.equals(password.toUpperCase());
    }


     //Checks if the password contains uppercase letters.

    private static boolean containsUpperCase(String password) {
        // Check if the password contains uppercase letters
        return !password.equals(password.toLowerCase());
    }
}
