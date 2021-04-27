package com.company;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many players?");
        int num = Integer.parseInt(scanner.nextLine());

        while (num < 2 || num > 5)
        {
            System.out.println("Invalid input! Try again");
            num = Integer.parseInt(scanner.nextLine());
        }

        Game UNO = new Game(num);
        UNO.play();
    }
}
