package com.company;

import java.util.Scanner;

public class Card2 extends Card implements Operable
{
    public Card2(String type, String color, int score, Game game)
    {
        super(type, color, score, game);
    }

    // gives card to a chosen component
    public void operate()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Which player do you want to give a card to? Enter their name:");
        String name = scanner.nextLine();
        Player opponent = game.findPlayer(name);

        while (opponent == null)
        {
            System.out.println("Invalid name! Try again:");
            name = scanner.nextLine();
            opponent = game.findPlayer(name);
        }

        System.out.println("Which one of your cards to you want to give to " + name + "? (Enter the card's number):");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        Player current = game.getPlayers().get(game.getTurn());

        while (!isValid(index, current))
        {
            System.out.println("Invalid number! Try again:");
            index = Integer.parseInt(scanner.nextLine()) - 1;
        }

        Card cardToGive = current.getCards().get(index);
        opponent.addCard(cardToGive);
        current.removeCard(cardToGive);
    }

    private boolean isValid(int index, Player current)
    {
        return index >= 0 && index < current.getCards().size();
    }
}
