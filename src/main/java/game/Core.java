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
        System.out.println("Any input to start the game. To quit, enter [q].");

        Player player = new Player();

        //new hand starts every iteration
        while (!input.next().equals("q")) {

            System.out.println("You have " + numberFormat.format(player.getCredits()) + "$.");
            System.out.println("How many $$$ would you like to bet?");

            player.bet(input);

            System.out.println(numberFormat.format(player.getCredits()) + "$ remaining.");
            System.out.println("###############################################");
            System.out.println();

            //new Deck every hand to reset odds and make card counting not possible
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

                //ask for next hit or stay -> loop continues
            }

            if(player.isBusted()) {
                System.out.println("[q] to quit. Any input to continue with next hand.");
                continue;
            }

            while (!Dealer.isFinishedDrawing())
                Dealer.hit(deck);

            //compare scores and determine winner of the game
            player.evaluateHand(Dealer.getScore());

            //start next hand
            System.out.println("[q] to quit. Any input to continue with next hand.");
        }
    }

    private static boolean getPlayerAction(Scanner scan) {

        System.out.println();
        System.out.println("Would you like to hit? [y] for yes. [n] for no.");

        while (scan.hasNext()) {

            String next = scan.next();
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
