package com.company;

public class NormalCard extends Card
{
    public NormalCard(String type, Color color, int score, Game game)
    {
        super(type, color, score, game);
    }

    @Override
    public void act()
    {
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
