package player;

import cards.Deck;

public class Player {
    private int credits = 10_000;
    private int score = 0;
    private int currentBetSize = 0;

    public int getCredits() {
        return credits;
    }
    public int getCurrentBetSize() {
        return currentBetSize;
    }
    public int getScore() {
        return this.score;
    }

    public int hit(Deck deck) {
        int value = deck.getCard();
        this.score += value;
        if (this.score > 21) {
            busted();
        }
        return value;
    }

    public int stand(Deck deck) {
        //handle standing and evaluation of next steps
        return 0;
    }

    public void bet(int credits) {
        this.credits -= credits;
        currentBetSize = credits;
        System.out.println("You bet: " + credits + " credits and now have: " + this.credits + " credits.");
    }

    private void busted() {
        System.out.println("Busted! Your score is: " + this.score);
        System.out.println("Your credit is: " + this.credits + " credits.");
        this.score = 0;
    }

    private void win() {
        System.out.println("You won! Your score is: " + this.score);
        System.out.println("You won " + this.getCurrentBetSize() + " credits.");
        this.credits += this.getCurrentBetSize();
        System.out.println("Your credit is: " + this.credits + " credits.");
        this.score = 0;
    }

}
