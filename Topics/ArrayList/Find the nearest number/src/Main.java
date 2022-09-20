import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //System.out.println(scanner.nextLine().split(" ").length);
        String[] str = scanner.nextLine().split(" ");
        int nb = scanner.nextInt();
        int minDist = Integer.MAX_VALUE;
        int dist = 0;

        List<Integer> listI = new ArrayList<>();
        List<Integer> listOut = new ArrayList<>();
        for (int i = 0; i < str.length; i++) {
            listI.add(Integer.parseInt(str[i]));
            dist = Math.abs(listI.get(i) - nb);
            if (minDist > dist) {
                minDist = dist;
            }
        }
        for (int i = 0; i < str.length; i++) {
            dist = Math.abs(listI.get(i) - nb);
            if (dist == minDist) {
                listOut.add(listI.get(i));
            }
        }
        listOut.sort(Comparator.naturalOrder());

        listOut.forEach(datint -> System.out.print(" " + datint));
    }
}
