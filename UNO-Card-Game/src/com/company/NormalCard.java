package com.company;

/**
 * A subclass of Card that represents a non-active card.
 * These cards don't have any affect on the process and the flow of the game.
 * @author KIMIA
 * @since 4-25-2021
 */
public class NormalCard extends Card
{
    /**
     * Calls the super's constructor.
     * @param type type of the card
     * @param color color of the card
     * @param score score of the card
     * @param game the game in which the card is being played in
     */
    public NormalCard(String type, Color color, int score, Game game)
    {
        super(type, color, score, game);
    }

    /**
     * The action of a NormalCard is nothing special.
     * The card's color and type will be printed out on the console for the players to be notified
     * about the card that's been just played.
     */
    @Override
    public void act()
    {
        System.out.print("\t");

        if (getColor() == Color.RED)
            System.out.print(RED + getColor() + " ");

        else if (getColor() == Color.BLUE)
            System.out.print(BLUE + getColor() + " ");

        else if (getColor() == Color.GREEN)
            System.out.print(GREEN + getColor() + " ");

        else if (getColor() == Color.BLACK)
            System.out.print(BLACK + getColor() + " ");

        System.out.println(getType() + " is played!" + RESET);
    }
}
