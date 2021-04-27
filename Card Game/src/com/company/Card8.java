package com.company;

public class Card8 extends Card implements Operable
{

    public Card8(String type, String color, int score, Game game)
    {
        super(type, color, score, game);
    }

    // bonus
    public void operate()
    {
        System.out.println("YOU GET A BONUS");
        int currentTurn = game.getTurn();
        game.setTurn(currentTurn - 1);
    }
}
