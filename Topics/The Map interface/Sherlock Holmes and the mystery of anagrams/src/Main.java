import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine().toLowerCase(Locale.ROOT);
        String str2 = scanner.nextLine().toLowerCase(Locale.ROOT);

        if (str1.length() == str2.length()) {
            if (checkAnagram(str1, str2) == true) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        } else {
            System.out.println("no");
        }
    }
    static boolean checkAnagram(String str1, String str2) {
        char[] c1 = str1.toCharArray();
        char[] c2 = str2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        if (Arrays.equals(c1, c2)) {
            return true;
        } else {
            return false;
        }
    }
}