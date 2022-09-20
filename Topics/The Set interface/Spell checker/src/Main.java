import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<String> dict = new LinkedHashSet<>();
        Set<String> sentence = new LinkedHashSet<>();
        int wordsNb = scanner.nextInt();
        String strIn;
        for (int i = 0; i < wordsNb; i++) {
            strIn = scanner.next().toLowerCase(Locale.ROOT);
            dict.add(strIn);
        }
        int sentenceNb = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        for (int i = 0; i < sentenceNb; i++) {
            String[] strInput = scanner.nextLine().toLowerCase(Locale.ROOT).split(" ");
            for (int j = 0; j < strInput.length; j++) {
                sentence.add(strInput[j]);
            }
        }


        for (String d : dict) {
            sentence.remove(d);
        }
        sentence.forEach(e -> System.out.println(e));
    }

}
