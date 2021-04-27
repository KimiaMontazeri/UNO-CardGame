package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Game
{
    private ArrayList<Player> players;
    private ArrayList<Card> deck;
    private int turn; // an int from 0 to players.size()-1
    private Scanner input;
    private boolean isClockWise;
    private boolean isSeven;
    private String gameColor;

    // console colors
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String BLACK = "\033[0;30m";   // BLACK

    public Game(int numOfPlayers)
    {
        // check for validity of the parameter in main
        players = new ArrayList<>(numOfPlayers);
        // turn  = random [ 0,players.size() )
        input = new Scanner(System.in);
        isClockWise = true;
        isSeven = false;

    }

    public ArrayList<Player> getPlayers() { return players; }
    public String getGameColor() { return gameColor; }
    public int getTurn() { return turn; }
    public boolean isSeven() { return isSeven; }
    public void setGameColor(String gameColor) { this.gameColor = gameColor; }
    public void setTurn(int turn) { this.turn = turn; }


    public void initTable()
    {
        for (int i = 0; i < players.size(); i++)
        {
            System.out.println("Player " + i + ""); // ask if they are bot or human
        }
    }

    public void initCards()
    {

    }

    public void play()
    {
        initCards();
        initTable();
        while (!gameOver())
        {
            displayTable();
            // ... some code
            // ... some code
            displayTable();
            nextPlayer();
        }
    }

    public void drawTwoOrFour()
    {
        int numOfCards = 2; // set it four if the cart is black
        while (isSeven)
        {
            System.out.println(players.get(turn).getName() + ", draw " + numOfCards + " cards!");
        }
    }

    private boolean gameOver()
    {
        for (Player player : players)
        {
            if (player.getCards().size() == 0)
                return false;
        }
        return true;
    }

    public void displayTable()
    {

    }

    public void reverseTheGame()
    {
        isClockWise = !isClockWise;
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

    public void nextPlayer()
    {
        turn++;
        if (turn == players.size())
            turn = 0;
    }

    public void scoreboard()
    {

    }
}
