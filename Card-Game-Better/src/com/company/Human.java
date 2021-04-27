package com.company;

import java.util.Scanner;

public class Human extends Player
{
    private Scanner scanner;

    public Human(String name)
    {
        super(name);
        scanner = new Scanner(System.in);
    }

    @Override
    public void displayHand()
    {
        for (Card card : getCards())
        {
            System.out.print(card.top());
        }
        System.out.println();
        for (Card card : getCards())
        {
            System.out.print(card.middle());
        }
        System.out.println();
        for (Card card : getCards())
        {
            System.out.print(card.bottom());
        }
        System.out.println();
        for (int i = 1; i <= getCards().size(); i++)
        {
            System.out.print("  " + i + "  ");
        }
        System.out.println();
    }

    @Override
    public Card draw(Card center, int penalty)
    {
        int index;
        Card card, temp;

        if (canPlay(center) && penalty == 0) // no 7 has been played before
        {
            index = getIndex();
            card = getCards().get(index);
            while (!isValid(card, center))
            {
                System.out.println("You can't play this card! Try again:");
                index = getIndex();
                card = getCards().get(index);
            }

            temp = card;
            remove(card);
            return temp;
        }

        card = getSeven();
        if (card != null && penalty != 0) // 7 has been played and the player has 7 so they have to play 7
        {
            System.out.println("You should play your seven!");
            temp = card;
            remove(card);
            return temp;
        }

        // player can't play any of their cards
        return null;
    }

    public int getIndex()
    {
        System.out.println("Choose a card by typing the index of it");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        while (!isValid(index))
        {
            System.out.println("Invalid index! Try again:");
            index = Integer.parseInt(scanner.nextLine()) - 1;
        }

        return index;
    }
}
