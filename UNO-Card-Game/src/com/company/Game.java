package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents the dirty seven or UNO game. It contains an array of players, a table
 * in which the cards are on. The first player to lose all their cards wins the game.
 * @author KIMIA
 * @since 4-25-2021
 */
public class Game
{
    private Table table;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Color currentColor;
    private Card center;
    private Random random;
    private Scanner scanner;
    private boolean isClockwise;
    private final int numOfPlayers;
    private int turn;
    private int penalty;

    /**
     * The constructor creates a table, an arraylist for players, a scanner to scan the user's commands,
     * and a random generator for getting random numbers.
     * It randomly chooses if the game is clockwise or not.
     * Lastly, the constructor creates some players and deals cards to them.
     * @param numOfPlayers the number of the players
     */
    public Game(int numOfPlayers)
    {
        table = new Table(this);
        players = new ArrayList<>();
        scanner = new Scanner(System.in);
        random = new Random();
        this.numOfPlayers = numOfPlayers;

        // randomly choose if the game is clockwise or not
        int r = random.nextInt(2);
        isClockwise = r == 0;
        // prepare the game
        setPlayers();
        dealCards();
    }

    /**
     * @return the ArrayList of the players
     */
    public ArrayList<Player> getPlayers() { return players; }

    /**
     * @return the current player
     */
    public Player getCurrentPlayer() { return currentPlayer; }

    /**
     * @return the current color of the game
     */
    public Color getCurrentColor() { return currentColor; }

    /**
     * @return the card at the center of the game (the last card that has been played)
     */
    public Card getCenterCard() { return center; }

    /**
     * @return the number of players
     */
    public int getNumOfPlayers() { return numOfPlayers; }

    /**
     * @return the penalty (the number of cards that the next player has to take as a penalty)
     */
    public int getPenalty() { return penalty; }

    /**
     * Sets penalty to the given parameter only if the integer is positive and the multiple of 2.
     * @param penalty the penalty of the game
     */
    public void setPenalty(int penalty)
    {
        if (penalty >= 0 && penalty % 2 == 0)
            this.penalty = penalty;
    }

    /**
     * Changes the color of the game to the given parameter
     * and notifies the players about the game's new color.
     * @param color a new color for the game
     */
    public void setCurrentColor(Color color)
    {
        currentColor = color;
        System.out.println("\tGame's color changed to " + color);
    }

    /**
     * Asks the user about the players' names and whether each player is human or bot.
     * The it will randomly choose one of the players to be the current player
     * and the game will be started with that player.
     */
    public void setPlayers()
    {
        Player player; // holds the new players
        boolean isBot; // determines if the player is a bot or human
        String input;  // stores the user's input

        for (int i = 0; i < numOfPlayers; i++)
        {
            System.out.println("\tType 'bot' or 'human' ");
            input = scanner.nextLine();

            if (input.equals("bot") || input.equals("Bot") || input.equals("BOT"))
                isBot = true;

            else if (input.equals("human") || input.equals("Human") || input.equals("HUMAN"))
                isBot = false;

            else // create a bot if the user typed invalid input
                isBot = true;

            System.out.println("\tName?");
            input = scanner.nextLine();

            // create the player object
            if (isBot) player = new Bot(input);
            else       player = new Human(input);

            players.add(player);
        }
        // choose the current player randomly
        turn = random.nextInt(numOfPlayers);
        currentPlayer = players.get(turn);
    }

    /**
     * Deals 7 cards to each player by choosing each card randomly from the table.
     */
    public void dealCards()
    {
        int index, bound = 52; // the game has 52 cards

        for (int i = 0; i < numOfPlayers; i++)
        {
            // give 7 random cards to each player
            for (int j = 0; j < 7; j++)
            {
                index = random.nextInt(bound);
                players.get(i).add(table.getCardByIndex(index));
                bound--;
            }
        }
    }

    /**
     * The actual game is started from this method.
     * It'll be executed until one of the player loses all their cards
     * Everytime that it's next player's turn, it will display the game for the users.
     */
    public void play()
    {
        if (!welcome())
            return;      // the user wants to exit

        Card card;       // holds the card that has been just played
        penalty = 0;     // increments this variable by 2 or 4 if "seven" is played

        // the games continues until one of the players lose all of their cards
        while (!finished())
        {
            // update the center card and the game's color
            center = table.getTopCard();
            if (!center.getType().equals("B"))
                currentColor = center.getColor();

            next();        // move to the next player
            displayGame(); // display the game for the current player

            // player selects a card
            card = currentPlayer.draw(center, penalty, currentColor);

            if (card != null)  // player has played a card
            {
                table.addTop(card); // put the card on the table
                card.act();
            }

            else // player hasn't played any card in the following conditions
            {
                if (penalty != 0) // 7 has been dealt before
                {
                    // player should take 2 or 4 or... cards
                    // notify the player about the number of cards they have to get
                    System.out.println("\t" + currentPlayer.getName() + " gets " + penalty + " cards!");
                    for (int i = 0; i < penalty; i++)
                    {
                        currentPlayer.add(table.takeBottomCard());
                    }
                    penalty = 0;
                }
                else if (center.getType().equals("8"))
                {
                    // the player has played 8, bot didn't play any card for a bonus
                    // they get another chance after getting a new card
                    currentPlayer.add(table.takeBottomCard());
                    center.act(); // "8" card (which is the center card) acts again as a bonus
                }
                else
                {
                    // penalty is definitely zero and player should get 1 card
                    currentPlayer.add(table.takeBottomCard());
                    System.out.println("\t" + currentPlayer.getName() + " gets a new card!");
                }
            }
        }
        displayGame();
        System.out.println("\tGAME OVER");
        System.out.println("\tscoreboard");
        scoreboard();
    }

    /**
     * Checks whether the game is finished or not, by checking if a player has no cards
     * @return true if the game is finished
     */
    public boolean finished()
    {
        for (Player player : players)
        {
            if (player.getCards().isEmpty())
                return true;
        }
        return false;
    }

    /**
     * Changes the direction (orientation) of the game.
     */
    public void reverseTheGame() { isClockwise = !isClockwise; }

    /**
     * Moves to the next player.
     */
    public void next()
    {
        if (isClockwise) {
            turn++;
            if (turn == numOfPlayers)
                turn = 0;
        }
        else {
            turn--;
            if (turn == -1)
                turn = numOfPlayers - 1;
        }

        currentPlayer = players.get(turn);
    }

    /**
     * Moves to the previous player.
     */
    public void prev()
    {
        if (isClockwise) {
            turn--;
            if (turn == -1)
                turn = numOfPlayers - 1;
        }
        else {
            turn++;
            if (turn == numOfPlayers)
                turn = 0;
        }
    }

    /**
     * Finds a player from the game's list of players using the given name.
     * @param name the name of the player to find
     * @return the player that has been found (or null if no player's name has matched the given name)
     */
    public Player findPlayer(String name)
    {
        for (Player player : players)
        {
            if (player.getName().equals(name))
                return player;
        }
        return null;
    }

    /**
     * Pauses the game for the given milliseconds.
     * @param ms the amount of time to pause the game
     */
    public void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Lists the players based on their scores from the lowest to the highest.
     */
    public void scoreboard()
    {
        // sort the players based on their scores
        Collections.sort(players);
        // print the winner in green color
        System.out.println("\t" + "\033[0;32m" + players.get(0) + " WON!" + "\033[0m");
        // print the others in default color
        for (int i = 1; i < players.size(); i++)
            System.out.println("\t" + players.get(i));
    }

    /**
     * Displays the game by printing the orientation, color, and the current player's name.
     * It also displays the number of cards each player currently has.
     * It then displays the center card and the current player's hand.
     */
    public void displayGame()
    {
        // print the number of cards of each player
        System.out.println();
        for (Player player : players)
            System.out.println("\t" + player.getName() + ": " + player.getCards().size() + " cards");
        System.out.println();

        if (isClockwise)
            System.out.println("\tCLOCKWISE");
        else
            System.out.println("\tCOUNTERCLOCKWISE");

        System.out.println("\tCurrent color : " + currentColor);
        System.out.println("\tTurn : " + currentPlayer.getName() + "\n");

        // display the center card
        table.display();
        System.out.println();
        // display the current player's hand
        currentPlayer.displayHand();
        wait(3000);
    }

    /**
     * Welcomes a user to the game by explaining what the game is about
     * and asks if the player wants to continue or exit.
     * @return true if the player wants to continue and play the game, false if they want to quit.
     */
    public boolean welcome()
    {
        System.out.println("\tWELCOME TO UNO!");
        System.out.println("\tEvery player gets 7 cards");
        System.out.println("\tWinner is the first person who loses all their cards");

        System.out.println("\t1.Play\t2.Exit");
        String input = scanner.nextLine();
        return input.equals("1");
    }
}
