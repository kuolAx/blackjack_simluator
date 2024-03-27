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

        Scanner scan = new Scanner(System.in);
        System.out.println("Any input to start the game. To quit, enter [q].");

        Player player = new Player();
        Dealer dealer = new Dealer();

        while( !scan.next().equals("q")) {
            //new Deck every hand to reset odds and make card counting not possible
            Deck deck = new Deck();

            System.out.println();
            System.out.println("You have " + numberFormat.format(player.getCredits()) + "$.");
            System.out.println("How many credits would you like to bet?");

            while( !scan.hasNextInt()) {
                System.out.println("Please enter a number.");
                scan.next();
            }
            player.bet(scan.nextInt());

            System.out.println("###############################################");
            System.out.println();

            dealer.initializeHand(deck);
            player.initializeHand(deck);

            while (player.getScore() < 22) {

                boolean isPlayerHitting = getPlayerAction(scan);

                if (isPlayerHitting) {
                    player.hit(deck);

                    if (!player.isBusted()) {
                        //ask for next hit or stay
                    } else {
                        //dealer wins, lose credits and start next hand
                        break;
                    }

                    if (Dealer.isBusted()) {
                        //player wins, get credits and start next hand
                        break;
                    }


                    //ask for next hit or stay -> loop continues

                } else {
                    System.out.println("Stay!");
                    break;
                }
            }

            while( !Dealer.isFinishedDrawing())
                dealer.hit(deck);
            //compare scores and determine winner of the game. then start next hand
            System.out.println("[q] to quit. Any input to continue with next hand.");
        }
    }

    private static boolean getPlayerAction(Scanner scan) {

        System.out.println();
        System.out.println("Would you like to hit?");
        System.out.println("[y] for yes. [n] for no.");

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
