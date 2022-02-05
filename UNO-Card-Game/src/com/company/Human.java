package com.company;

import java.util.Scanner;

/**
 * This class is a human type of a player. All the human's decisions are based on
 * what the user wants. The human's hand is displayed on the screen.
 * @author KIMIA
 * @since 4-25-2021
 */
public class Human extends Player
{
    private Scanner scanner;

    /**
     * Calls the super's constructor and creates a new object of Scanner class for scanning the human's commands
     * @param name name of the bot
     */
    public Human(String name)
    {
        super(name);
        scanner = new Scanner(System.in);
    }

    /**
     * Displays all the human's cards
     */
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

    /**
     * If they have any card that can be played, the game will allow the human to choose a card to draw
     * if not, then the player has to take a new card
     * If the penalty is non-zero, the player has to take cards or has to play their seven
     * @param center the center's card in the game
     * @param penalty the current penalty of the game
     * @param gameColor the current color of the game
     * @return the drawn card (null if no card is drawn)
     */
    @Override
    public Card draw(Card center, int penalty, Color gameColor)
    {
        int index;
        Card card, temp;

        if (canPlay(center, gameColor) && penalty == 0) // no 7 has been played before
        {
            index = getIndex();
            card = getCards().get(index);
            while (!isValid(card, center, gameColor))
            {
                System.out.println("\tYou can't play this card! Try again:");
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
            System.out.println("\tYou should play your seven!");
            temp = card;
            remove(card);
            return temp;
        }

        // player can't play any of their cards
        return null;
    }

    /**
     * Asks the human player, which card they want to draw, by typing the index of the card
     * @return the chosen card's index
     */
    public int getIndex()
    {
        System.out.println("\tChoose a card by typing the index of it");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        while (!isValid(index))
        {
            System.out.println("\tInvalid index! Try again:");
            index = Integer.parseInt(scanner.nextLine()) - 1;
        }

        return index;
    }
}
