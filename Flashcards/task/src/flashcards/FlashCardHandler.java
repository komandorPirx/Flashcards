package flashcards;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import static java.util.Comparator.reverseOrder;


public class FlashCardHandler {

    private final Scanner inputScanner = new Scanner(System.in);
    private final Map<String, String[]> cards = new LinkedHashMap<>();
    private final LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
    private final LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
    private final Scanner sc = CommonScanner.getInstance();
    final MyLogger logger = MyLogger.getInstance();

    void addCard() {

        //Create cards entered by the user...
        logger.print("The card:");
        String term = sc.nextLine();
        logger.print(term, true);
        term.trim();
        if (cards.containsKey(term)) {
            logger.print(String.format("The card \"%s\" already exists.", term));
            return;
        }

        logger.print("The definition of the card:");
        String definition = sc.nextLine();

        ArrayList<String> answers = new ArrayList<>();
        for (Map.Entry<String, String[]> entry : cards.entrySet()) {
            String[] values = entry.getValue();
            answers.add(values[0]);
        }
        if (answers.contains(definition)) {
            logger.print("The definition \"" + definition + "\" already exists.");
            return;
        }
        String[] defArray = {definition, "0"};
        cards.put(term, defArray);

        logger.print("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
    }

    void removeCard() {

        logger.print("Which card?");
        String term = sc.nextLine();

        if (cards.containsKey(term)) {
            cards.remove(term);
            logger.print("The card has been removed.");
        } else {
            logger.print("Can't remove \"" + term + "\": there is no such card.");
        }
    }

    void ask() {
        logger.print("How many times to ask?");
        int timesToAsk = Integer.parseInt(inputScanner.nextLine());
        Set<Map.Entry<String, String[]>> entrySet = cards.entrySet();
        List<Map.Entry<String, String[]>> listOfEntry = new ArrayList<Map.Entry<String, String[]>>(entrySet);

        boolean inside = false;
        for (int i = 0; i < timesToAsk; i++) {
            String[] str = listOfEntry.get(i).getValue();
            logger.print("Print the definition of \"" + listOfEntry.get(i).getKey() + "\":");
            String definition = sc.nextLine();
            definition = definition.trim();
            if (str[0].equals(definition)) {
                logger.print("Correct!");
            } else {
                for (int j = 0; j < listOfEntry.size(); j++) {
                    String[] strNext = listOfEntry.get(j).getValue();
                    if (strNext[0].equals(definition)) {
                        logger.print("Wrong. The right answer is \"" + str[0] + "\", " +
                                "but your definition is correct for " + "\"" + listOfEntry.get(j).getKey() + "\".");
                        inside = true;
                        str[1] = String.valueOf(Integer.parseInt(str[1]) + 1);//hardestCard
                        break;
                    }
                }
                if (!inside) {
                    logger.print("Wrong. The right answer is \"" + str[0] + "\".");
                    str[1] = String.valueOf(Integer.parseInt(str[1]) + 1);//hardestCard
                    inside = false;
                }
            }
        }
    }

    void hardestCard() {
        int maxValue = 0;
        Map<String, Integer> hashMap = new HashMap<String, Integer>();
        LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
        Map<String, Integer> hardestCard = new LinkedHashMap<String, Integer>();

        if (!cards.isEmpty()) {
            for (Map.Entry<String, String[]> entry : cards.entrySet()) {
                hashMap.put(entry.getKey(),
                        Integer.valueOf(entry.getValue()[1]));
            }
        } else {
            logger.print("There are no cards with errors.");
            return;
        }

        hashMap.entrySet().stream().sorted(Map.Entry.comparingByValue(reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), Integer.valueOf(x.getValue())));


        Iterator<Map.Entry<String, Integer>> it = reverseSortedMap.entrySet().iterator();
        Map.Entry<String, Integer> pair = it.next();
        int maxHardest = pair.getValue();

        if (maxHardest == 0) {
            logger.print("There are no cards with errors.");
            return;
        }

        for (Map.Entry<String, Integer> entryLoop : reverseSortedMap.entrySet()) {
            if (entryLoop.getValue() >= maxHardest) {
                hardestCard.put(entryLoop.getKey(), entryLoop.getValue());
            }
        }
        StringBuilder theHardest = new StringBuilder("The hardest cards are ");
        for (Map.Entry<String, Integer> entry : hardestCard.entrySet()) {

            if (hardestCard.size() == 1) {
                logger.print("The hardest card is \"" + entry.getKey() + "\"" + "." +
                        " You have " + entry.getValue() + " errors answering it.");
            } else {
                theHardest.append("\"" + entry.getKey() + "\", ");
                maxValue = entry.getValue();
            }
        }
        if (hardestCard.size() > 1) {
            logger.println(theHardest.deleteCharAt(theHardest.lastIndexOf(",")).toString() + ".");
            logger.print(" You have " + maxValue + " errors answering them.");
        }
    }

    void exportCards(String fileName) {
        logger.print("File name:");
        logger.print(fileName, true);
        File file = new File(fileName);
        try (PrintWriter writer = new PrintWriter(file)) {
            for (Map.Entry<String, String[]> entry : cards.entrySet()) {
                String[] values = entry.getValue();
                writer.println(entry.getKey() + ":" + values[0] + ":" + values[1]);
            }
        } catch (IOException e) {
            logger.print("File not found");
            return;
        }
        if (cards.size() == 1) {
            logger.print(cards.size() + " card have been saved.");
        } else {
            logger.print(cards.size() + " cards have been saved.");
        }
    }

    void exportCards() {
        logger.print("File name:");
        String fileName = sc.nextLine();
        logger.print(fileName, true);
        File file = new File(fileName);

        try (PrintWriter writer = new PrintWriter(file)) {
            for (Map.Entry<String, String[]> entry : cards.entrySet()) {
                String[] values = entry.getValue();
                writer.println(entry.getKey() + ":" + values[0] + ":" + values[1]);
            }
        } catch (IOException e) {
            logger.print("File not found");
            return;
        }
        if (cards.size() == 1) {
            logger.print(cards.size() + " card have been saved.");
        } else {
            logger.print(cards.size() + " cards have been saved.");
        }
    }

    void importCards(String fileName) {
        logger.print("File name:");
        File file = new File(fileName);
        int entryCount = 0;
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNext()) {
                String[] entry = fileScanner.nextLine().split(":");
                String[] values = {entry[1], entry[2]};
                cards.put(entry[0], values);

                entryCount++;
            }
        } catch (IOException e) {
            logger.print("File not found.");
            return;
        }
        if (entryCount == 1) {
            logger.print(entryCount + " card have been loaded.");
        } else {
            logger.print(entryCount + " cards have been loaded.");
        }
    }

    void importCards() {
        logger.print("File name:");
        File file = new File(sc.nextLine());
        int entryCount = 0;

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNext()) {
                String[] entry = fileScanner.nextLine().split(":");
                String[] values = {entry[1], entry[2]};
                cards.put(entry[0], values);

                entryCount++;
            }

        } catch (IOException e) {
            logger.print("File not found.");
            return;
        }
        if (entryCount == 1) {
            logger.print(entryCount + " card have been loaded.");
        } else {
            logger.print(entryCount + " cards have been loaded.");
        }
    }

    void resetStats() {
        logger.print("reset stats");
        //hardestCard.replaceAll((key, value) -> value * 0);
        for (var str : cards.entrySet()) {
            String[] values = str.getValue();
            values[1] = String.valueOf(0);
        }
        logger.print("Card statistics have been reset.");
    }
}
