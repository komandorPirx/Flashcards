import java.util.*;

public class Main {
    static final int GAMMA = 3;
    static final int OMEGA = 24;
    static final int ALPHA = 1;
    
    public static void main(String[] args) {
        List<GreekLetter> letterList = new ArrayList<>();

        letterList.add(new GreekLetter("Gamma",  GAMMA));
        letterList.add(new GreekLetter("Omega", OMEGA));
        letterList.add(new GreekLetter("Alpha",  ALPHA));
        for (GreekLetter greek : letterList) {
            System.out.println(greek);
        }
    }

    static class GreekLetter {

        private String letter;
        private Integer position;

        public GreekLetter(String letter, Integer position) {
            this.letter = letter;
            this.position = position;
        }

        @Override
        public String toString() {
            return "{" +
                    "letter='" + letter + '\'' +
                    ", position=" + position +
                    '}';
        }
    }
}
