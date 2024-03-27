package player;

import cards.Deck;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int credits = 10_000;
    private int currentBetSize;
    private int score;
    private List<Integer> currentHand = new ArrayList<>();
    private boolean busted = false;

    public int getCredits() {
        return credits;
    }
    public int getCurrentBetSize() {
        return currentBetSize;
    }
    public int getScore() {
        return this.score;
    }
    public boolean isBusted() {
        return busted;
    }

    public void initializeHand(Deck deck) {
        this.score = 0;
        this.currentHand = new ArrayList<>();
        this.busted = false;

        int firstCard = deck.getCard();
        int secondCard = deck.getCard();
        this.score += firstCard + secondCard;

        this.currentHand.add(firstCard);
        this.currentHand.add(secondCard);

        System.out.println("Your starting hand:   " + printHand());
    }

    public void hit(Deck deck) {
        System.out.println("Hitting!");
        int value = deck.getCard();

        this.score += value;
        this.currentHand.add(value);

        System.out.println("You drew card: " + value);
        System.out.println("Your new hand: " + printHand());

        if (this.score > 21) {
            this.busted = true;
        }
    }

    public void bet(int credits) {
        this.credits -= credits;
        currentBetSize = credits;
        System.out.println(this.credits + " credits remaining.");
    }

    private String printHand() {
        StringBuilder ret = new StringBuilder();

        for (Integer i : this.currentHand) {
            ret.append(i).append(" ");
        }

        return ret.toString();
    }

}
