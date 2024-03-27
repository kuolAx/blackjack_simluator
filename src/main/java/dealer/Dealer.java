package dealer;

import cards.Deck;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private static int score = 0;
    private static List<Integer> currentHand = new ArrayList<>();

    public void hit(Deck deck) {

        if (score < 17) {
            int value = deck.getCard();
            score += value;
            currentHand.add(value);
        }
        else if (score < 22) {
            System.out.println("Dealer stays at 17 and above");
        }

        if (score > 21) {
            System.out.println("Dealer busted");
            //handle bust event
            return;
        }

        System.out.println("Dealer hand: " + printHand());
    }

    public void initializeHand(Deck deck) {
        score = 0;
        currentHand = new ArrayList<>();

        score += deck.getCard();
        currentHand.add(score);

        System.out.println("Dealer starting hand: " + score);
    }

    private String printHand() {
        StringBuilder ret = new StringBuilder();

        for (Integer i : currentHand) {
            ret.append(i).append(" ");
        }

        return ret.toString();
    }

}
