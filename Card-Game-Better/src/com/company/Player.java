package com.company;

import java.util.ArrayList;

public abstract class Player
    implements Comparable<Player>
{
    private ArrayList<Card> cards;
    private final String name;

    public Player(String name)
    {
        cards = new ArrayList<>();
        this.name = name;
    }

    public String getName() { return name; }

    public ArrayList<Card> getCards() { return cards; }

    public Integer score()
    {
        int score = 0;

        for (Card card : cards)
            score += card.getScore();

        return score;
    }

    public void add(Card card) { cards.add(card); }

    public void remove(Card card) { cards.remove(card); }

    @Override
    public String toString() { return name + ": " + score(); }

    @Override
    public int compareTo(Player o)
    {
        return this.score().compareTo(o.score());
    }

    public void displayHand() { System.out.println(); }

    public abstract Card draw(Card center, int penalty);

    public boolean isValid(int index)
    {
        return index >= 0 && index < cards.size();
    }

    public boolean isValid(Card card, Card center)
    {
        return card.getType().equals(center.getType())        // same types
                || card.getColor().equals(center.getColor())  // same colors
                || card.getType().equals("B");                // B can be played anytime
    }

    public boolean canPlay(Card center)
    {
        for (Card card : getCards())
        {
            if (isValid(card, center))
                return true;
        }
        return false;
    }

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
