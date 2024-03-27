package dealer;

import cards.Deck;

public class Dealer {

    private int score = 0;

    public int hit(Deck deck) {

        if (this.score < 17) {
            int value = deck.getCard();
            this.score += value;
            return value;
        }
        else {
            System.out.println("Dealer stays at 17 and above");
            return 0;
        }
    }

    public int getScore() {
        return this.score;
    }
}
