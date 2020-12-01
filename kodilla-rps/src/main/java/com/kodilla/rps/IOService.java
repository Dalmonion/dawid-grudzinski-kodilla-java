package com.kodilla.rps;

import java.util.Scanner;

public class IOService {
    private static final Scanner scanner = new Scanner(System.in);

    public static void showWelcomeMessage() {
        System.out.println("Witam w naszej grze.\nWybierz rodzaj gry: \n1: Komputer vs Człowiek,\n2: Komputer vs Komputer,\n3: Człowiek vs Człowiek");
    }

    public static int getGamePlayerOption() {
        String value;
        do {
            value = scanner.nextLine();
            if ("1".equals(value) || "2".equals(value) || "3".equals(value)) {
                return Integer.parseInt(value);
            } else {
                System.out.println("Podana wartość jest nieprawidłowa. Wybierz rodzaj gry jeszcze raz.");
                value = null;
            }
        } while (value == null);
        return -1;
    }

    public static String getPlayerName() {
        System.out.println("Podaj nazwę gracza:");
        return scanner.nextLine();
    }

    public static String getPlayersName(String number) {
        System.out.println("Podaj nazwę " + number + " gracza:");
        return scanner.nextLine();
    }

    public static int setWinningRounds() {
        System.out.println("Podaj liczbę gier, stanowiącą o wygranej:");
        int choice;
        do {
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice > 0) {
                return choice;
            } else {
                System.out.println("Podaj liczbę większą od 0");
                choice = 0;
            }

        } while (choice <= 0);
        return -1;
    }

    public static int humanChoice(Player player) {
        String choice;
        System.out.println("Podaj swój wybór:\n1. Kamień,\n2. Papier,\n3. Nożyce");
        do {
            choice = scanner.nextLine();
            if ("1".equals(choice)) {
                System.out.println(player.getName() + " wybrał kamień");
                return (Integer.parseInt(choice) - 1);
            } else if ("2".equals(choice)) {
                System.out.println(player.getName() + " wybrał papier");
                return (Integer.parseInt(choice) - 1);
            } else if ("3".equals(choice)) {
                System.out.println(player.getName() + " wybrał nożyce");
                return (Integer.parseInt(choice) - 1);
            } else {
                System.out.println("Podany wybór jest nieprawidłowy. Spróbuj jeszcze raz");
                choice = null;
            }
        } while (choice == null);
        return -1;
    }

    public static int computerChoice(int compChoice, Player player) {
        if (compChoice == 0) {
            System.out.println(player.getName() + " wylosował kamień");
            return 0;
        } else if (compChoice == 1) {
            System.out.println(player.getName() + " wylosował papier");
            return 1;
        } else if (compChoice == 2) {
            System.out.println(player.getName() + " wylosował nożyce");
            return 2;
        } else {
            return -1;
        }
    }

    public static void presentScore(Player player1, Player player2) {
        System.out.println("Aktualny wynik: (" + player1.getName() + ":" + player2.getName() + ") " + player1.getScore() + ":" + player2.getScore());
    }

    public static void verbalValidation(int option) {
        if(option == 1) {
            System.out.println("Kamień bije nożyce");
        } else if (option == 2) {
            System.out.println("Nożyce tną papier");
        } else if (option == 3) {
            System.out.println("Papier przykrywa kamień");
        } else {
            System.out.println("Remis");
            System.out.println();
        }
    }

    public static void roundWinner(Player player) {
        System.out.println("Runde wygrywa " + player.getName());
        System.out.println();
    }

    public static void gameWinner(Player player1, Player player2) {
        if (player1.getScore() > player2.getScore()) {
            System.out.println("Grę wygrywa " + player1.getName() + ", z wynikiem " + player1.getScore() + ":" + player2.getScore());
        } else {
            System.out.println("Grę wygrywa " + player2.getName() + ", z wynikiem " + player2.getScore() + ":" + player1.getScore());
        }
    }


}
