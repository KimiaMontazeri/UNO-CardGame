package com.company;

import java.util.Random;

public class Bot extends Player
{
    private Random random;

    public Bot(String name)
    {
        super(name);
        random = new Random();
    }

    @Override
    public Card draw(Card center, int penalty)
    {
        int index;
        Card card, temp;

        if (canPlay(center) && penalty == 0) // no 7 has been played before
        {
            // select a card
            index = random.nextInt(getCards().size());
            card = getCards().get(index);
            // check if the card can be played at the moment
            while (!isValid(card, center))
            {
                index = random.nextInt(getCards().size());
                card = getCards().get(index);
            }

            // remove the card from the player's hand
            temp = card;
            remove(card);
            return temp;
        }

        card = getSeven();
        if (card != null && penalty != 0) // 7 has been played and the player has 7 so they have to play 7
        {
            temp = card;
            remove(card);
            return temp;
        }
        // player can't play any of their cards
        return null;
    }
}
