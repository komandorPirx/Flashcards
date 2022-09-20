import java.util.*;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        ArrayList<String> lst = new ArrayList<>();
        lst.addAll(Arrays.asList(str.split(" ")));
        ArrayList<String> out = new ArrayList<>();

        //System.out.println(lst);

       /* Collection<Integer> numbers = Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).collect(Collectors.toList());
*/
        for (int i = 0; i < lst.size(); i++){
            if (i % 2 != 0) {
                out.add(lst.get(i));
            }
        }

        Collections.reverse(out);
        out.forEach(e -> System.out.print(e + " "));

    }
}