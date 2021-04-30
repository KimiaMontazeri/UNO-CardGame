package com.company;

import java.util.Random;

/**
 * A subclass of Player which represents a bot player.
 * The bot chooses randomly what card to choose and do any other decision making.
 * The bot hand won't be displayed to the console.
 * @author KIMIA
 * @since 4-25-2021
 */
public class Bot extends Player
{
    private Random random;

    /**
     * Calls the super's constructor and creates a new object of Random class
     * @param name name of the bot
     */
    public Bot(String name)
    {
        super(name);
        random = new Random();
    }

    /**
     * If it has any card that can be played, the game will allow the bot to choose a card to draw
     * if not, then the bot has to take a new card
     * If the penalty is non-zero, the bot has to take cards or has to play its seven
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

        if (canPlay(center, gameColor) && penalty == 0) // player can play and no 7 has been played before
        {
            // select a card
            index = random.nextInt(getCards().size());
            card = getCards().get(index);
            // check if the card can be played at the moment
            while (!isValid(card, center, gameColor))
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
