package com.company;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\tType in the number of players");
        int num = Integer.parseInt(scanner.nextLine());

        while (num < 3 || num > 5)
        {
            System.out.println("\tInvalid input! Try again");
            num = Integer.parseInt(scanner.nextLine());
        }

        Game UNO = new Game(num);
        UNO.play();
    }
}
