package cards;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Deck {

    private static List<Integer> decks = new ArrayList<>();

    public Deck() {
        initializeSixDecks();
    }

    /**
 * initializes the deck with 6 decks of 52 cards from a standard deck
 */
private void initializeSixDecks() {
    IntStream.rangeClosed(1, 13).forEach(i -> {
        for (int j = 0; j < 24; j++) {
            decks.add(i);
        }
    });
}

}
