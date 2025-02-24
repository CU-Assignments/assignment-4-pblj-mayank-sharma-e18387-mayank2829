import java.util.*;

class CardCollection {
    private HashMap<String, List<String>> cards;

    public CardCollection() {
        cards = new HashMap<>();
    }

    public void addCard(String symbol, String cardName) {
        cards.putIfAbsent(symbol, new ArrayList<>());
        cards.get(symbol).add(cardName);
    }

    public List<String> getCardsBySymbol(String symbol) {
        return cards.getOrDefault(symbol, new ArrayList<>());
    }

    public void displayAllCards() {
        if (cards.isEmpty()) {
            System.out.println("No cards in the collection.");
        } else {
            for (Map.Entry<String, List<String>> entry : cards.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}

public class CardCollectionSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static CardCollection collection = new CardCollection();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nCard Collection System");
            System.out.println("1. Add Card");
            System.out.println("2. Find Cards by Symbol");
            System.out.println("3. Display All Cards");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCard();
                    break;
                case 2:
                    findCards();
                    break;
                case 3:
                    collection.displayAllCards();
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addCard() {
        System.out.print("Enter Card Symbol (e.g., Hearts, Spades): ");
        String symbol = scanner.nextLine();
        System.out.print("Enter Card Name (e.g., Ace, King, Queen): ");
        String cardName = scanner.nextLine();
        
        collection.addCard(symbol, cardName);
        System.out.println("Card added successfully!");
    }

    private static void findCards() {
        System.out.print("Enter Symbol to search for: ");
        String symbol = scanner.nextLine();
        
        List<String> cards = collection.getCardsBySymbol(symbol);
        if (cards.isEmpty()) {
            System.out.println("No cards found for symbol: " + symbol);
        } else {
            System.out.println("Cards of " + symbol + ": " + cards);
        }
    }
}
