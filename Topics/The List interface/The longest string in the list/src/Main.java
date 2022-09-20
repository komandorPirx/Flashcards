import java.util.*;

public class Main {

    static void changeList(List<String> list) {
        int length = 0;
       
        String longestStr = "0";
        for (String one : list) {
            if (one.length() > length) {
                length = one.length();
                longestStr = one;
            }
        }
       
        for (int i = 0; i < list.size(); i++) {
            list.set(i, longestStr);
        }
      
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        List<String> lst = Arrays.asList(s.split(" "));
        changeList(lst);
        lst.forEach(e -> System.out.print(e + " "));
    }
}
