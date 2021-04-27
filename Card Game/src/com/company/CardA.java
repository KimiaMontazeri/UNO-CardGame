package com.company;

public class CardA extends Card implements Operable
{
    public CardA(String type, String color, int score, Game game)
    {
        super(type, color, score, game);
    }

    // skip
    public void operate()
    {
        int currentTurn = game.getTurn();
        game.setTurn(currentTurn + 1);
    }
}
