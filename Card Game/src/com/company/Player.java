package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Player
{
    private String name;
    private ArrayList<Card> cards;
    private int score;
    private boolean isBot;

    public Player(String name, boolean isBot)
    {
        cards = new ArrayList<>();
        this.name = name;
        this.isBot = isBot;
    }

    public ArrayList<Card> getCards() { return cards; }

    public String getName() { return name; }

    public int getScore() { return score; }

    public boolean isBot() { return isBot; }

    public void addCard(Card card) { cards.add(card); }

    public void removeCard(Card card) { cards.remove(card); }

    public Card placeCard(Card middleCard)
    {
        int index;
        if (isBot)
        {
            // generate a random valid card
            // while (not valid) { generate again }
        }
        else
        {

        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the card's number: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        while (index < 0 || index >= cards.size())
        {
            System.out.println("Invalid number! Try again:");
            index = Integer.parseInt(scanner.nextLine());
        }

        Card card = cards.get(index);
        cards.remove(index);
        return card;
    }
}
