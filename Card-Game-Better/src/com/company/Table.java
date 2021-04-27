package com.company;

import java.util.Stack;

public class Table
{
    private Stack<Card> centerCards;

    public Table(Game game)
    {
        centerCards = new Stack<>();
        createDeckOfCards(game);
    }

    private void createDeckOfCards(Game game)
    {
        Card RED;
        Card BLUE;
        Card GREEN;
        Card BLACK;

        // 2
        RED = new ActiveCard("2", Color.RED, 2, game);
        BLUE = new ActiveCard("2", Color.BLUE, 2, game);
        GREEN = new ActiveCard("2", Color.GREEN, 2, game);
        BLACK = new ActiveCard("2", Color.BLACK, 2, game);
        centerCards.add(RED);
        centerCards.add(BLUE);
        centerCards.add(GREEN);
        centerCards.add(BLACK);

        // 3
        RED = new NormalCard("3", Color.RED, 3, game);
        BLUE = new NormalCard("3", Color.BLUE, 3, game);
        GREEN = new NormalCard("3", Color.GREEN, 3, game);
        BLACK = new NormalCard("3", Color.BLACK, 3, game);
        centerCards.add(RED);
        centerCards.add(BLUE);
        centerCards.add(GREEN);
        centerCards.add(BLACK);

        // 4
        RED = new NormalCard("4", Color.RED, 4, game);
        BLUE = new NormalCard("4", Color.BLUE, 4, game);
        GREEN = new NormalCard("4", Color.GREEN, 4, game);
        BLACK = new NormalCard("4", Color.BLACK, 4, game);
        centerCards.add(RED);
        centerCards.add(BLUE);
        centerCards.add(GREEN);
        centerCards.add(BLACK);

        // 5
        RED = new NormalCard("5", Color.RED, 5, game);
        BLUE = new NormalCard("5", Color.BLUE, 5, game);
        GREEN = new NormalCard("5", Color.GREEN, 5, game);
        BLACK = new NormalCard("5", Color.BLACK, 5, game);
        centerCards.add(RED);
        centerCards.add(BLUE);
        centerCards.add(GREEN);
        centerCards.add(BLACK);


        // 6
        RED = new NormalCard("6", Color.RED, 6, game);
        BLUE = new NormalCard("6", Color.BLUE, 6, game);
        GREEN = new NormalCard("6", Color.GREEN, 6, game);
        BLACK = new NormalCard("6", Color.BLACK, 6, game);
        centerCards.add(RED);
        centerCards.add(BLUE);
        centerCards.add(GREEN);
        centerCards.add(BLACK);

        // 7
        RED = new ActiveCard("7", Color.RED, 10, game);
        BLUE = new ActiveCard("7", Color.BLUE, 10, game);
        GREEN = new ActiveCard("7", Color.GREEN, 10, game);
        BLACK = new ActiveCard("7", Color.BLACK, 15, game);
        centerCards.add(RED);
        centerCards.add(BLUE);
        centerCards.add(GREEN);
        centerCards.add(BLACK);

        // 8
        RED = new ActiveCard("8", Color.RED, 8, game);
        BLUE = new ActiveCard("8", Color.BLUE, 8, game);
        GREEN = new ActiveCard("8", Color.GREEN, 8, game);
        BLACK = new ActiveCard("8", Color.BLACK, 8, game);
        centerCards.add(RED);
        centerCards.add(BLUE);
        centerCards.add(GREEN);
        centerCards.add(BLACK);

        // 9
        RED = new NormalCard("9", Color.RED, 9, game);
        BLUE = new NormalCard("9", Color.BLUE, 9, game);
        GREEN = new NormalCard("9", Color.GREEN, 9, game);
        BLACK = new NormalCard("9", Color.BLACK, 9, game);
        centerCards.add(RED);
        centerCards.add(BLUE);
        centerCards.add(GREEN);
        centerCards.add(BLACK);

        // 10
        RED = new ActiveCard("10", Color.RED, 10, game);
        BLUE = new ActiveCard("10", Color.BLUE, 10, game);
        GREEN = new ActiveCard("10", Color.GREEN, 10, game);
        BLACK = new ActiveCard("10", Color.BLACK, 10, game);
        centerCards.add(RED);
        centerCards.add(BLUE);
        centerCards.add(GREEN);
        centerCards.add(BLACK);

        // A
        RED = new ActiveCard("A", Color.RED, 11, game);
        BLUE = new ActiveCard("A", Color.BLUE, 11, game);
        GREEN = new ActiveCard("A", Color.GREEN, 11, game);
        BLACK = new ActiveCard("A", Color.BLACK, 11, game);
        centerCards.add(RED);
        centerCards.add(BLUE);
        centerCards.add(GREEN);
        centerCards.add(BLACK);

        // B
        RED = new ActiveCard("B", Color.RED, 12, game);
        BLUE = new ActiveCard("B", Color.BLUE, 12, game);
        GREEN = new ActiveCard("B", Color.GREEN, 12, game);
        BLACK = new ActiveCard("B", Color.BLACK, 12, game);
        centerCards.add(RED);
        centerCards.add(BLUE);
        centerCards.add(GREEN);
        centerCards.add(BLACK);

        // C
        RED = new NormalCard("C", Color.RED, 12, game);
        BLUE = new NormalCard("C", Color.BLUE, 12, game);
        GREEN = new NormalCard("C", Color.GREEN, 12, game);
        BLACK = new NormalCard("C", Color.BLACK, 12, game);
        centerCards.add(RED);
        centerCards.add(BLUE);
        centerCards.add(GREEN);
        centerCards.add(BLACK);

        // D
        RED = new NormalCard("D", Color.RED, 13, game);
        BLUE = new NormalCard("D", Color.BLUE, 13, game);
        GREEN = new NormalCard("D", Color.GREEN, 13, game);
        BLACK = new NormalCard("D", Color.BLACK, 13, game);
        centerCards.add(RED);
        centerCards.add(BLUE);
        centerCards.add(GREEN);
        centerCards.add(BLACK);
    }

    public Stack<Card> getTotalCards() { return centerCards; }

    public Card getCardByIndex(int index)
    {
        if (index >= 0 && index < centerCards.size())
        {
            Card card = centerCards.get(index);
            centerCards.remove(index);
            return card;
        }
        return null;
    }

    public void addTop(Card card)
    {
        centerCards.add(card);
    }

    public Card getTopCard()
    {
        return centerCards.peek();
    }

    public Card takeBottomCard()
    {
        Card bottom = centerCards.firstElement();
        centerCards.removeElementAt(0);
        return bottom;
    }

    public void display()
    {
        centerCards.peek().display();
    }
}
