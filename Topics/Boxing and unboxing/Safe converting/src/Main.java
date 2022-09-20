import java.util.Scanner;

public class Main {

    public static int convert(Long val) {
        if (val == null) {
            return 0;
        }
        return (int) Math.max(Integer.MIN_VALUE, Math.min(val, Integer.MAX_VALUE));
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String val = scanner.nextLine();
        Long longVal = "null".equals(val) ? null : Long.parseLong(val);
        System.out.println(convert(longVal));
    }
}