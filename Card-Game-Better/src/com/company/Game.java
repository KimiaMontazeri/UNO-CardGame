package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

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
    // used for printing the scoreboard
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String RESET = "\033[0m";      // Text Reset

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
    }

    public Table getTable() { return table; }

    public ArrayList<Player> getPlayers() { return players; }

    public Player getCurrentPlayer() { return currentPlayer; }

    public Card getCenterCard() { return center; }

    public int getNumOfPlayers() { return numOfPlayers; }

    public void setCurrentColor(Color color) { currentColor = color; }

    public void setPlayers()
    {
        Player player; // holds the new players
        boolean isBot; // determines if the player is a bot or human
        String input;  // stores the user's input

        for (int i = 0; i < numOfPlayers; i++)
        {
            System.out.println("Type 'bot' or 'human' ");
            input = scanner.nextLine();

            if (input.equals("bot") || input.equals("Bot") || input.equals("BOT"))
                isBot = true;

            else if (input.equals("human") || input.equals("Human") || input.equals("HUMAN"))
                isBot = false;

            else // create a bot if the user typed invalid input
                isBot = true;

            System.out.println("Name?");
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

    public void play()
    {
        // do the basic operations before the game is started
        setPlayers();
        dealCards();

        Card card;       // holds the card that has been just played
        int penalty = 0; // increments by 2 or 4 if "seven" is played

        // the games continues until one of the players lose all of their cards
        while (!currentPlayer.getCards().isEmpty())
        {
            // update the center card and the game's color
            center = table.getTopCard();
            if (!center.getType().equals("B"))
                currentColor = center.getColor();

            next();        // move to the next player
            displayGame(); // display the game for the current player

            // player selects a card
            card = currentPlayer.draw(center, penalty);

            if (card != null)  // player has played a card
            {
                table.addTop(card); // add the card to the table
                // card.act returns true if the card's number is seven
                if (card.act())
                {
                    // update the penalty according to the card's color
                    if (card.getColor() == Color.BLACK)
                        penalty += 4;
                    else
                        penalty += 2;
                }
                else penalty = 0;
            }

            else // player hasn't played any card in the following conditions
            {
                if (penalty != 0) // 7 has been dealt before
                {
                    // player should take 2 or 4 or... cards
                    // notify the player about the number of cards they have to get
                    System.out.println(currentPlayer.getName() + " gets " + penalty + " cards!");
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
                    System.out.println(currentPlayer.getName() + "gets another chance.");
                    prev();
                }
                else
                {
                    // penalty is definitely zero and player should get 1 card
                    currentPlayer.add(table.takeBottomCard());
                    System.out.println(currentPlayer.getName() + " gets a new card!");
                }
            }
            wait(3000);
            displayGame();
        }

        System.out.println("\tGAME OVER\t");
        System.out.println("\tscoreboard\t");
        scoreboard();
    }

    public void reverseTheGame()
    {
        isClockwise = !isClockwise;
        prev(); // idk if it's necessary
    }

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

    public Player findPlayer(String name)
    {
        for (Player player : players)
        {
            if (player.getName().equals(name))
                return player;
        }
        return null;
    }

    public void wait(int ms)
    {
        System.out.println("\tPLEASE WAIT...");
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public void scoreboard()
    {
        // sort the players based on their scores
        Collections.sort(players);
        // print the winner in green color
        System.out.println("\t" + GREEN + players.get(0) + RESET);
        // print the others in default color
        for (int i = 1; i < players.size(); i++)
            System.out.println("\t" + players.get(i));
    }

    public void displayGame()
    {
        // print the number of cards of each player
        for (Player player : players)
            System.out.println("\t" + player.getName() + ": " + player.getCards().size() + " cards\n");

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
}
