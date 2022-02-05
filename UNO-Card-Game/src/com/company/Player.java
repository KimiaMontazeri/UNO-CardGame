package com.company;

import java.util.ArrayList;

/**
 * An abstract representation of a player. A player has a name and a set of cards.
 * @author KIMIA
 * @since 4-25-2021
 */
public abstract class Player
    implements Comparable<Player>
{
    private ArrayList<Card> cards;
    private final String name;

    /**
     * Sets the player's name to the given string, and creates a new ArrayList to hold the player's cards
     * @param name the player's name
     */
    public Player(String name)
    {
        cards = new ArrayList<>();
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() { return name; }

    /**
     * @return all the player's cards
     */
    public ArrayList<Card> getCards() { return cards; }

    /**
     * Calculates the current score that the player has
     * @return the total score
     */
    public Integer score()
    {
        int score = 0;

        for (Card card : cards)
            score += card.getScore();

        return score;
    }

    /**
     * Adds a new card to the player's cards
     * @param card a new card
     */
    public void add(Card card) { cards.add(card); }

    /**
     * Removes a card from the player's hand
     * @param card a card to remove
     */
    public void remove(Card card) { cards.remove(card); }

    /**
     * @return the player's name and score in a string format
     */
    @Override
    public String toString() { return name + ": " + score(); }

    /**
     * Compares the player's score to the given object
     * @param o an object to compare its score to the player's score
     * @return if the score is equal to the argument's score then 0 is returned
     * if it's less than the argument's score then -1 is returned
     * if it's greater than the argument's score then 1 is returned
     */
    @Override
    public int compareTo(Player o) { return this.score().compareTo(o.score()); }

    /**
     * This method will only print an empty line
     * It's overridden in the subclasses
     */
    public void displayHand() { System.out.println(); }

    /**
     * Allows the player to draw a card if they can
     * @param center the center's card in the game
     * @param penalty the current penalty of the game
     * @param gameColor the current color of the game
     * @return the drawn card (null if no card is drawn)
     */
    public abstract Card draw(Card center, int penalty, Color gameColor);

    /**
     * Checks if the given integer is inside the boundary of the ArrayList of cards
     * @param index index of a card
     * @return true if the index is valid
     */
    public boolean isValid(int index) { return index >= 0 && index < cards.size(); }

    /**
     * Checks if the given card can be played with the center card and the game color
     * It will compare the card's color with the game's color and the card's type with the center card's type
     * @param card the card to check if it's valid
     * @param center the center card in the game
     * @param gameColor the current color of the game
     * @return true if one (or more) of the above conditions is true
     */
    public boolean isValid(Card card, Card center, Color gameColor)
    {
        return card.getType().equals(center.getType())        // same types
                || card.getColor().equals(gameColor)          // same colors
                || card.getType().equals("B");                // B can be played anytime
    }

    /**
     * Iterates the player's hand and checks if the player has any card
     * that is valid, according to the center card and the game's color
     * @param center the card in the center
     * @param gameColor the game's color
     * @return true if the player can play
     */
    public boolean canPlay(Card center, Color gameColor)
    {
        for (Card card : getCards())
        {
            if (isValid(card, center, gameColor))
                return true;
            if (card.getColor() == gameColor)
                return true;
        }
        return false;
    }

    /**
     * Iterates the player's hand and finds a card with type "7"
     * @return the first "7" card that is found in the player's hand
     */
    public Card getSeven()
    {
        for (Card card : getCards())
        {
            if (card.getType().equals("7"))
                return card;
        }
        return null;
    }
}
