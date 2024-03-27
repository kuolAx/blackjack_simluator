package player;

import cards.Deck;
import dealer.Dealer;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private static final NumberFormat numberFormat = new DecimalFormat("###,###,###");
    private int credits = 10_000;
    private int currentBetSize;
    private int score;
    private List<Integer> currentHand = new ArrayList<>();
    private boolean busted = false;

    public int getCredits() {
        return credits;
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
        int value = deck.getCard();

        this.score += value;
        this.currentHand.add(value);

        System.out.print("Hitting! You drew card: " + value + " Score: " + this.score + " New hand: " + printHand());
        System.out.println();

        if (this.score > 21) {
            this.busted = true;
        }
    }

    public void bet(Scanner input) {

        //betSize Validation
        while (true) {

            if (input.hasNextInt()) {
                int betSize = input.nextInt();
                if (betSize > 0 && betSize <= this.credits) {
                    this.credits -= betSize;
                    currentBetSize = betSize;
                    break;
                }
                else {
                    System.out.println("Illegal amount. Please enter a valid amount.");
                }
            } else {
                if (input.next().equals("q"))
                    throw new RuntimeException("Quitting");
                System.out.println("Please enter a number.");
            }

        }
    }

    private String printHand() {
        StringBuilder ret = new StringBuilder();

        for (Integer i : this.currentHand) {
            ret.append(i).append(" ");
        }

        return ret.toString();
    }

    public void bust() {
        System.out.println("You lost! Your score is: " + this.score + " with hand " + printHand());
    }

    private void win() {
        System.out.println();
        System.out.println("You won! Your score is: " + this.score + " with hand " + printHand());
        System.out.println("Dealer scored:          " + Dealer.getScore() + " with hand " + Dealer.getHand());

        this.credits += this.currentBetSize*2;
        System.out.println("You won " + this.currentBetSize + " credits. Now at " + numberFormat.format(this.credits) + "$");
    }

    public void evaluateHand(int dealerScore) {
        if (this.isBusted())
            this.bust();
        else if (Dealer.isBusted())
            this.win();
        else if (dealerScore > this.score)
            this.bust();
        else if (dealerScore == this.score)
            System.out.println("Draw! Dealer score: " + Dealer.getScore() + ". Your score: " + this.score + ".");
        else
            this.win();
    }


}
