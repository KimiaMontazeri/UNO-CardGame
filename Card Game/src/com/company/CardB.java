package com.company;

import java.util.Scanner;

public class CardB extends Card implements Operable
{
    public CardB(String type, String color, int score, Game game)
    {
        super(type, color, score, game);
    }

    // change color (WILD)
    public void operate()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a new color: (Type blue, green, black or red)");
        String color = scanner.nextLine();

        while (!isValid(color))
        {
            System.out.println("Invalid color! Try again:");
            color = scanner.nextLine();
        }

        game.setGameColor(color);
    }

    private boolean isValid(String color)
    {
        return switch (color) {
            case "Blue", "blue", "Green", "green", "Black", "black", "Red", "red" -> true;
            default -> false;
        } || color.equals(game.getGameColor());
    }
}
