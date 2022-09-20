import java.util.Scanner;

enum DaysOfTheWeek { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DaysOfTheWeek day = DaysOfTheWeek.valueOf(scanner.next());
        int numLetters;
        
        final int six = 6;
        final int seven = 7;
        final int eight = 8;
        final int night = 9;
        
        // Put switch expression here
        numLetters = switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> six;
            case TUESDAY -> seven;
            case WEDNESDAY -> night;
            case THURSDAY, SATURDAY -> eight;
            default -> throw new IllegalStateException("Invalid day: " + day);
        };
        System.out.println(numLetters);
    }
}
