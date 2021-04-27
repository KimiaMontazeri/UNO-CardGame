package com.company;

public class Card7 extends Card implements Operable
{


    public Card7(String type, String color, int score, Game game)
    {
        super(type, color, score, game);
    }

    // draw two
    public void operate()
    {
        int num;
        if (getColor().equals("black"))
        {
            num = 4;
            Player nextPlayer = game.getPlayers().get(game.getTurn() + 1);
            if ()
        }
        else
        {
            num = 2;
        }
    }
}
