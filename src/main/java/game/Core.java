package game;

import cards.Deck;
import dealer.Dealer;
import player.Player;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Core {
    private static final NumberFormat numberFormat = new DecimalFormat("###,###,###");

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("Welcome to blackjack!");
        System.out.println("To quit, enter [q] anytime.");
        System.out.println();

        Player player = new Player();

        try {
            //new hand starts every iteration
            while (true) {

                System.out.println("######################-new hand-######################");
                System.out.println();

                System.out.println("You have " + numberFormat.format(player.getCredits()) + "$. How many $$$ would you like to bet?");

                player.bet(input);

                //new Deck on every hand dealt to reset odds and make card counting not possible
                Deck deck = new Deck();

                Dealer.initializeHand(deck);
                player.initializeHand(deck);

                while (player.getScore() < 22) {

                    boolean isPlayerHitting = getPlayerAction(input);

                    if (isPlayerHitting) {
                        player.hit(deck);
                    } else {
                        System.out.println("Stay!");
                        break;
                    }
                }

                if (player.isBusted()) {
                    player.bust();
                    continue;
                }

                while (!Dealer.isFinishedDrawing())
                    Dealer.hit(deck);

                //compare scores and determine winner of the game
                player.evaluateHand(Dealer.getScore());

                //start next hand
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Thank you for playing. You ended on " + numberFormat.format(player.getCredits()) + "$.");
            System.exit(0);
        }
    }

    private static boolean getPlayerAction(Scanner scan) {

        System.out.println();
        System.out.println("Would you like to hit? [y] yes [n] no");

        while (scan.hasNext()) {

            String next = scan.next();
            if (next.equals("q"))
                throw new RuntimeException("Quitting");
            if (next.equals("y")) {
                return true;
            } else if (next.equals("n")) {
                return false;
            }

            System.out.println("Please enter either [y] or [n]");
        }

        return false;
    }

}
