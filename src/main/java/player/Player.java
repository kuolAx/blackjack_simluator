package player;

import cards.Deck;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int credits = 10_000;
    private int currentBetSize = 0;
    private int score = 0;
    private List<Integer> currentHand = new ArrayList<>();

    public int getCredits() {
        return credits;
    }
    public int getCurrentBetSize() {
        return currentBetSize;
    }
    public int getScore() {
        return this.score;
    }

    public void initializeHand(Deck deck) {
        this.score = 0;
        this.currentHand = new ArrayList<>();

        int firstCard = deck.getCard();
        int secondCard = deck.getCard();
        this.score += firstCard + secondCard;

        this.currentHand.add(firstCard);
        this.currentHand.add(secondCard);

        System.out.println("Your starting hand:   " + printHand());
    }

    public void hit(Deck deck) {
        System.out.println("Hit!");
        int value = deck.getCard();
        this.score += value;
        this.currentHand.add(value);

        if (this.score > 21) {
            busted();
        }
    }

    public int stand(Deck deck) {
        //handle standing and evaluation of next steps
        return 0;
    }

    public void bet(int credits) {
        this.credits -= credits;
        currentBetSize = credits;
        System.out.println(this.credits + " credits remaining.");
    }

    private void busted() {
        System.out.println("Busted! Your score is: " + this.score);
        System.out.println("Your credit is: " + this.credits + " credits.");
    }

    private void win() {
        System.out.println("You won! Your score is: " + this.score);
        System.out.println("You won " + this.getCurrentBetSize() + " credits.");
        this.credits += this.getCurrentBetSize()*2;
        System.out.println("Your credit is: " + this.credits + " credits.");
    }

    private String printHand() {
        StringBuilder ret = new StringBuilder();

        for (Integer i : this.currentHand) {
            ret.append(i).append(" ");
        }

        return ret.toString();
    }

}
