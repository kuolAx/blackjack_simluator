package cards;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Deck {

    private static final List<Integer> cacheDecks = new ArrayList<>();

    // 6 decks of 52 cards. cards above 10 are still only worth 10 points in blackjack, so they get replaced with value 10
    static { IntStream.rangeClosed(1, 13).map(i -> Math.min(i, 10)).forEach(i -> {
        for (int j = 0; j < 24; j++) {
            cacheDecks.add(i);
        }});
    }
    private final List<Integer> decks = cacheDecks;

    public int getCard() {
        int index = (int) (Math.random() * decks.size());
        int value = decks.get(index);
        decks.remove(index);
        return value;
    }

}
