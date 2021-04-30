package com.company;

/**
 * An abstracts representation of a card in UNO card game.
 * A card, has a name, a color, and a score.
 * @author KIMIA
 * @since 4-25-2021
 */
public abstract class Card
{
    private final String type;
    private final Color color;
    private final int score;
    protected Game game;

    // console colors
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RESET = "\033[0m";      // Text Reset

    /**
     * Sets the given parameters to their corresponding field.
     * @param type type of the card (like "4", "A", ...)
     * @param color can be red, blue, green or black
     * @param score score of the card
     * @param game the game in which the card is being played in
     */
    public Card(String type, Color color, int score, Game game)
    {
        this.type = type;
        this.color = color;
        this.score = score;
        this.game = game;
    }

    /**
     * @return the card's type
     */
    public String getType() { return type; }

    /**
     * @return the card's color
     */
    public Color getColor() { return color; }

    /**
     * @return the card's score
     */
    public int getScore() { return score; }

    /**
     * The action that a card can have.
     */
    public abstract void act();

    /**
     * @return String format of the top of the card
     */
    public String top()
    {
        if (color == Color.RED)
            return RED + " ___ " + RESET;

        else if (color == Color.BLUE)
            return BLUE + " ___ " + RESET;

        else if (color == Color.GREEN)
            return GREEN + " ___ " + RESET;

        else if (color == Color.BLACK)
            return BLACK + " ___ " + RESET;

        return "";
    }

    /**
     * @return String format of the middle of the card
     */
    public String middle()
    {
        if (color == Color.RED)
        {
            if (type.equals("10"))
                return RED + "|" + type + " " + "|" + RESET;
            else
                return RED + "|" + type + "  " + "|" + RESET;
        }

        else if (color == Color.BLUE)
        {
            if (type.equals("10"))
                return BLUE + "|" + type + " " + "|" + RESET;
            else
                return BLUE + "|" + type + "  " + "|" + RESET;
        }

        else if (color == Color.GREEN)
        {
            if (type.equals("10"))
                return GREEN + "|" + type + " " + "|" + RESET;
            else
                return GREEN + "|" + type + "  " + "|" + RESET;
        }

        else if (color == Color.BLACK)
        {
            if (type.equals("10"))
                return BLACK + "|" + type + " " + "|" + RESET;
            else
                return BLACK + "|" + type + "  " + "|" + RESET;
        }

        return "";
    }

    /**
     * @return String format of the bottom of the card
     */
    public String bottom()
    {
        if (color == Color.RED)
            return RED + "|___|" + RESET;

        else if (color == Color.BLUE)
            return BLUE + "|___|" + RESET;

        else if (color == Color.GREEN)
            return GREEN + "|___|" + RESET;

        else if (color == Color.BLACK)
            return BLACK + "|___|" + RESET;

        return "";
    }

    /**
     * Displays the whole card by printing the top, middle and then the bottom of the card on the console.
     */
    public void display()
    {
        System.out.println("\t\t" + top());
        System.out.println("\t\t" + middle());
        System.out.println("\t\t" + bottom());
    }
}
