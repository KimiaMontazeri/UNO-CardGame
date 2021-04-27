package com.company;

public class Card10 extends Card implements Operable
{

    public Card10(String type, String color, int score, Game game)
    {
        super(type, color, score, game);
    }

    // reverse the game
    public void operate()
    {
        game.reverseTheGame();
    }
}
