package com.company;

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

    public Card(String type, Color color, int score, Game game)
    {
        this.type = type;
        this.color = color;
        this.score = score;
        this.game = game;
    }

    public String getType() { return type; }

    public Color getColor() { return color; }

    public int getScore() { return score; }

    public boolean isRelative(Card center)
    {
        if (color == game.getCurrentColor())
            return true;

        else return color == center.getColor() || type.equals(center.getType());
    }

    public abstract void act();

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

    public void display()
    {
        System.out.println("\t\t" + top());
        System.out.println("\t\t" + middle());
        System.out.println("\t\t" + bottom());
    }
}
