package com.company;

public abstract class Card
{
    private final String type;
    private final String color;
    private final int score;
    protected Game game;

    public Card(String type, String color, int score, Game game)
    {
        this.type = type;
        this.color = color;
        this.score = score;
        this.game = game;
    }

    public String getType() { return type; }

    public String getColor() { return color; }

    public int getScore() { return score; }

    public boolean isOperable()
    {
        return type.equals("2") || type.equals("7") || type.equals("8")
                || type.equals("10") || type.equals("A") || type.equals("B");
    }
}
