package flashcards;
import java.util.Locale;
import java.util.Scanner;

public class FlashCardAction {

    private final FlashCardHandler handler = new FlashCardHandler();
    //private final Scanner inputScanner = new Scanner(System.in);
    final Scanner sc = CommonScanner.getInstance();
    final MyLogger logger = MyLogger.getInstance();

    public void runApp(String[] args) {
        boolean export = false;
        String exportFileName = new String();
        for (int i = 0; i < args.length - 1; i +=2) {
            if ("-import".equals(args[i])) {
                //logger.print("Import cards!");
                //logger.print("nazwa pliku " + args[i+1]);
                handler.importCards(args[i + 1]);
            }
            if ("-export".equals(args[i])) {
                //logger.print("Export cards");
                //logger.print("nazwa pliku " + args[i+1]);
                //handler.exportCards(args[i + 1]);
                exportFileName = args[i + 1];
                export = true;
            }
        }

        //Ask user to select option from menu until the user select exit option...
        String action;

        do {
            printMenu();
            action = sc.nextLine();
            logger.print(action, true);
            switch (action.trim().toLowerCase()) {
                case "exit":
                    if (export) {
                        handler.exportCards(exportFileName);
                    }
                    logger.print("Bye bye!");
                    continue;
                case "add":
                    handler.addCard();
                    break;
                case "remove":
                    handler.removeCard();
                    break;
                case "ask":
                    handler.ask();
                    break;
                case "export":
                    handler.exportCards();
                    break;
                case "import":
                    handler.importCards();
                    break;
                case "log":
                    logger.saveToFile();
                    break;
                case "hardest card":
                    handler.hardestCard();
                    break;
                case "reset stats":
                    handler.resetStats();
                    break;
                default:
            }
            logger.print("");
        }while (!action.equals("exit"));
    }

    private void printMenu() {
        logger.print("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
    }
}
