package com.company;

import java.util.Random;
import java.util.Scanner;

/**
 * This class is a subclass of Card. It represents an active card in UNO card game.
 * These cards may change the flow of the game, by either changing the game's color,
 * or skip the next player, and so on.
 * @author KIMIA
 * @since 4-25-2021
 */
public class ActiveCard extends Card
{
    /**
     * Calls the super's constructor
     * @param type type of the card
     * @param color color of the card
     * @param score score of the card
     * @param game the game in which the card is being played in
     */
    public ActiveCard(String type, Color color, int score, Game game)
    {
        super(type, color, score, game);
    }

    /**
     * Depending on the card's type, the card will act and affects the game
     */
    @Override
    public void act()
    {
        switch (getType())
        {
            case "2":
                giveCard();
                return;
            case "7":
                dirtySeven();
                return;
            case "8":
                bonus();
                return;
            case "10":
                reverse();
                return;
            case "A":
                skip();
                return;
            case "B":
                if (game.getCurrentPlayer() instanceof Human)
                    wild();

                else randomWild();
                return;
            default:
        }
    }

    /**
     * Increments the game's penalty by 2 (if it's not black) or 4 (if it's black)
     */
    private void dirtySeven() // 7
    {
        System.out.println(RED + "\tDIRTY SEVEN!!!" + RESET);
        System.out.println("\tNext player must take new cards or play seven if they can");

        int penalty = game.getPenalty();

        if (getColor() == Color.BLACK)
            game.setPenalty(penalty + 4);
        else
            game.setPenalty(penalty + 2);
    }

    /**
     * Gives the current player another chance to play a card
     */
    private void bonus() // 8
    {
        System.out.println(GREEN + "\tYOU GET A BONUS" + RESET);
        game.prev();
    }

    /**
     * Reverses the game
     */
    private void reverse() // 10
    {
        System.out.println("\tTHE GAME IS REVERSED");
        game.reverseTheGame();
    }

    /**
     * Skips the next player
     */
    private void skip() // A
    {
        System.out.println("\tNEXT PLAYER IS SKIPPED");
        game.next();
    }

    /**
     * Manually chooses a new color for the game by asking what the player wants
     * (Used for when the current player is human)
     * The player has to type in a number between 1 to 4
     */
    private void wild() // B
    {
        System.out.println("\tYOU CAN CHANGE THE COLOR OF THE GAME");
        game.wait(1000);

        Scanner scanner = new Scanner(System.in);
        System.out.println("\tChoose between these colors:");
        System.out.println("\t1.Red 2.Blue 3.Green 4.Black");
        System.out.println("\t(Enter a number from 1 to 4)");

        int input = Integer.parseInt(scanner.nextLine());
        while (input < 1 || input > 4)
        {
            System.out.println("\tInvalid input! Try again");
            input = Integer.parseInt(scanner.nextLine());
        }

        changeColor(input);
    }

    /**
     * Randomly chooses a color (used for when the player who has played B is a bot)
     * by generating a random number between 1 to 4
     */
    private void randomWild() // B
    {
        Random random = new Random();
        int r = random.nextInt(4) + 1;
        changeColor(r);
    }

    /**
     * Changes the color of the game.
     * @param num a number between 1 to 4 that represents a color
     */
    private void changeColor(int num)
    {
        switch (num)
        {
            case 1 -> game.setCurrentColor(Color.RED);
            case 2 -> game.setCurrentColor(Color.BLUE);
            case 3 -> game.setCurrentColor(Color.GREEN);
            case 4 -> game.setCurrentColor(Color.BLACK);
        }
    }

    /**
     * The player gives one of their cards to an opponent
     * The card is chosen randomly (both for human players and bots)
     */
    private void giveCard() // 2
    {
        // if the player played 2 but now they don't have any card
        if (game.getCurrentPlayer().getCards().isEmpty())
            return;

        Random random = new Random();
        Player opponent;
        Card card;
        int i;

        if (game.getCurrentPlayer() instanceof Bot) // choose an opponent randomly
            opponent = chooseOpponentRandomly();
        else                                        // choose an opponent manually
            opponent = chooseOpponentManually();

        // choose a card randomly
        i = random.nextInt(game.getCurrentPlayer().getCards().size());
        card = game.getCurrentPlayer().getCards().get(i);

        game.getCurrentPlayer().remove(card);
        opponent.add(card);
        // print a message to notify the player
        System.out.println("\t" + card.getColor() + " " + card.getType() + " is " + opponent.getName() + "'s now!");
    }

    /**
     * Chooses an opponent randomly (used for a bot player)
     * @return the chosen opponent
     */
    private Player chooseOpponentRandomly()
    {
        Random random = new Random();
        int i = random.nextInt(game.getNumOfPlayers());
        Player opponent = game.getPlayers().get(i);

        while (opponent.getName().equals(game.getCurrentPlayer().getName()))
        {
            // A player can't give themselves a card !
            i = random.nextInt(game.getNumOfPlayers());
            opponent = game.getPlayers().get(i);
        }
        return game.getPlayers().get(i);
    }

    /**
     * The user (human player) will choose an opponent by typing in their name
     * The name will be searched in the list of players and will be found
     * The player can try again if the typed in an invalid name
     * @return the chosen opponent
     */
    public Player chooseOpponentManually() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\tWhich player do you want to give a card to? Enter their name:");
        String name = scanner.nextLine();
        Player opponent = game.findPlayer(name);

        // the chosen opponent doesn't exist, or the player has typed in their own name :|
        while (opponent == null || opponent.getName().equals(game.getCurrentPlayer().getName()))
        {
            System.out.println("\tNot a valid name! " +
                    "Either " + name + " does not exist, or is you! Try again:");
            name = scanner.nextLine();
            opponent = game.findPlayer(name);
        }

        return opponent;
    }
}
