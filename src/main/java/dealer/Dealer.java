package dealer;

import cards.Deck;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private static int score;
    private static List<Integer> currentHand = new ArrayList<>();
    private static boolean busted;
    private static boolean finishedDrawing;

    public static int getScore() {return score;}
    public static boolean isBusted() {
        return busted;
    }

    public static boolean isFinishedDrawing() {
        return finishedDrawing;
    }

    public static void hit(Deck deck) {

        if (score < 17) {
            int value = deck.getCard();
            score += value;
            currentHand.add(value);
            System.out.print("Dealer hits. " + value + " ");
        }
        else if (score < 22) {
            System.out.println("Dealer stays at 17 and above");
            finishedDrawing = true;
        }

        if (score > 21) {
            System.out.println("Dealer busted");
            busted = true;
            finishedDrawing = true;
            return;
        }

        System.out.println("Dealer hand: " + printHand());
    }

    public static void initializeHand(Deck deck) {
        score = 0;
        currentHand = new ArrayList<>();
        Dealer.busted = false;

        score += deck.getCard();
        currentHand.add(score);

        System.out.println("Dealer starting hand: " + score);
    }

    private static String printHand() {
        StringBuilder ret = new StringBuilder();

        for (Integer i : currentHand) {
            ret.append(i).append(" ");
        }

        return ret.toString();
    }

    public static String getHand() {
        return Dealer.printHand();
    }
}
