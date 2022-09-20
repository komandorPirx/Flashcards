import java.util.*;
import java.util.stream.Collectors;

class Age implements Comparable<Age> {
    private final int value;



    public Age(int value) {
        this.value = value;
    }
    
    public int compareTo(Age otherAge) {
        if (this.value < otherAge.value) {
            return -1;
        } else if (this.value == otherAge.value) {
            return 0;
        } else {
            return +1;
        }
    }

    public int getValue() {
        return value;
    }
}

// do not change the code below
class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Age> list = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .mapToObj(Age::new)
                .sorted()
                .collect(Collectors.toList());

        Checker.check(list);
    }
}
