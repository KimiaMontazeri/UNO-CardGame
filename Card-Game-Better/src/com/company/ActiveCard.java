package com.company;

import java.util.Random;
import java.util.Scanner;

public class ActiveCard extends Card
{
    public ActiveCard(String type, Color color, int score, Game game)
    {
        super(type, color, score, game);
    }

    @Override
    public void act()
    {
        switch (getType())
        {
            case "2":
                giveCard();
                return;
            case "7":
                dirtySeven();
                return;
            case "8":
                bonus();
                return;
            case "10":
                reverse();
                return;
            case "A":
                skip();
                return;
            case "B":
                if (game.getCurrentPlayer() instanceof Human)
                    wild();

                else randomWild();
                return;
            default:
        }
    }

    private void dirtySeven() // 7
    {
        System.out.println(RED + "\tDIRTY SEVEN!!!" + RESET);
        System.out.println("\tNext player must take new cards or play seven if they can");

        int penalty = game.getPenalty();

        if (getColor() == Color.BLACK)
            game.setPenalty(penalty + 4);
        else
            game.setPenalty(penalty + 2);
    }

    private void bonus() // 8
    {
        System.out.println(GREEN + "\tYOU GET A BONUS" + RESET);
        game.prev();
    }

    private void reverse() // 10
    {
        System.out.println("\tTHE GAME IS REVERSED");
        game.reverseTheGame();
    }

    private void skip() // A
    {
        System.out.println("\tNEXT PLAYER IS SKIPPED");
        game.next();
    }

    private void wild() // B
    {
        System.out.println("\tYOU CAN CHANGE THE COLOR OF THE GAME");
        game.wait(5000);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose between these colors:");
        System.out.println("1.Red 2.Blue 3.Green 4.Black");
        System.out.println("(Enter a number from 1 to 4)");

        int input = Integer.parseInt(scanner.nextLine());
        while (input < 1 || input > 4)
        {
            System.out.println("Invalid input! Try again");
            input = Integer.parseInt(scanner.nextLine());
        }

        changeColor(input);
    }

    private void randomWild() // B
    {
        Random random = new Random();
        int r = random.nextInt(4) + 1;
        changeColor(r);
    }

    private void changeColor(int num)
    {
        switch (num)
        {
            case 1 -> game.setCurrentColor(Color.RED);
            case 2 -> game.setCurrentColor(Color.BLUE);
            case 3 -> game.setCurrentColor(Color.GREEN);
            case 4 -> game.setCurrentColor(Color.BLACK);
        }
    }

    private void giveCard() // 2
    {
        Random random = new Random();
        Player opponent;
        Card card;
        int i;

        if (game.getCurrentPlayer() instanceof Bot) // choose an opponent randomly
            opponent = chooseOpponentRandomly();
        else                                        // choose an opponent manually
            opponent = chooseOpponentManually();

        // choose a card randomly
        i = random.nextInt(game.getCurrentPlayer().getCards().size());
        card = game.getCurrentPlayer().getCards().get(i);

        game.getCurrentPlayer().remove(card);
        opponent.add(card);
        // print a message to notify the player
        System.out.println(card.getColor() + " " + card.getType() + " is " + opponent.getName() + "'s now!");
    }

    private Player chooseOpponentRandomly()
    {
        Random random = new Random();
        int i = random.nextInt(game.getNumOfPlayers());
        Player opponent = game.getPlayers().get(i);

        while (opponent.getName().equals(game.getCurrentPlayer().getName()))
        {
            // A player can't give themselves a card !
            i = random.nextInt(game.getNumOfPlayers());
            opponent = game.getPlayers().get(i);
        }
        return game.getPlayers().get(i);
    }

    public Player chooseOpponentManually() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Which player do you want to give a card to? Enter their name:");
        String name = scanner.nextLine();
        Player opponent = game.findPlayer(name);

        while (opponent == null || opponent.getName().equals(game.getCurrentPlayer().getName()))
        {
            System.out.println("Not a valid name! " +
                    "Either" + name + " does not exist, or is you! Try again:");
            name = scanner.nextLine();
            opponent = game.findPlayer(name);
        }

        return opponent;
    }
}
