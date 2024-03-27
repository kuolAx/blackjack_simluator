package game;

import cards.Deck;
import dealer.Dealer;
import player.Player;

import java.util.Scanner;

public class Core {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Any input to start the game. To quit, enter \"q\" at any time.");

        Player player = new Player();
        Dealer dealer = new Dealer();

        while( !scan.next().equals("q")) {
            //new Deck every hand to reset odds and make card counting not possible
            Deck deck = new Deck();

            System.out.println("You have " + player.getCredits() + " credits.");
            System.out.println("How many credits would you like to bet?");

            while( !scan.hasNextInt()) {
                System.out.println("Please enter a number");
                scan.next();
            }
            player.bet(scan.nextInt());

            System.out.println("##################################################################################");
            System.out.println("New hand starting:");

            System.out.println("Your first card:    " + player.hit(deck));
            System.out.println("Dealers first card: " + dealer.hit(deck));

            System.out.println("Your second card:    " + player.hit(deck));
            System.out.println("Dealers second card: " + dealer.hit(deck));

            System.out.println("Your score:          " + player.getScore());
            System.out.println("Dealers score:          " + dealer.getScore());

            System.out.println("would you like to hit or stand?");
            System.out.println("[y] for yes. [n] for no.");

            boolean isPlayerHitting = getPlayerAction(scan);

            if (isPlayerHitting) {
                System.out.println("Hit!");
                player.hit(deck);
                dealer.hit(deck);
            } else {
                System.out.println("Stay!");
                //handle dealers turn and evaluation
            }
        }

    }

    private static boolean getPlayerAction(Scanner scan) {

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
