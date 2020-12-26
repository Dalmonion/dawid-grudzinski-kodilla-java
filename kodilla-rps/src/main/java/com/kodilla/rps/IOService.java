package com.kodilla.rps;

import java.util.InputMismatchException;
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
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice > 0) {
                    return choice;
                } else {
                    System.out.println("Podaj liczbę większą od 0");
                    choice = 0;
                }
            } catch(InputMismatchException a) {
                System.out.println("Wpisz liczbę");
                choice = 0;
            }
            scanner.nextLine();
        } while (choice <= 0);
        return -1;
    }

    private static void printChoice() {
        System.out.println("Podaj swój wybór:\n1. Kamień,\n2. Papier,\n3. Nożyce," +
                "\n4. Jaszczurka,\n5. Spock," +
                "\nKlawisz 'x' aby zakończyć grę,\nKlawisz 'n' aby rozpocząć gre od nowa");
    }

    public static int humanChoice(Player player) {
        String choice;
        printChoice();
        do {
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println(player.getName() + " wybrał kamień");
                    return (Integer.parseInt(choice));
                case "2":
                    System.out.println(player.getName() + " wybrał papier");
                    return (Integer.parseInt(choice));
                case "3":
                    System.out.println(player.getName() + " wybrał nożyce");
                    return (Integer.parseInt(choice));
                case "4":
                    System.out.println(player.getName() + " wybrał jaszczurkę");
                    return (Integer.parseInt(choice));
                case "5":
                    System.out.println(player.getName() + " wybrał Spocka");
                    return (Integer.parseInt(choice));
                case "x":
                    choice = abortConfirmation();
                    printChoice();
                    break;
                case "n":
                    choice = startNewGame();
                    printChoice();
                    break;
                default:
                    System.out.println("Podany wybór jest nieprawidłowy. Spróbuj jeszcze raz");
                    choice = null;
            }
        } while (choice == null);
        return -1;
    }

    public static int computerChoice(int compChoice, Player player) {
        switch (compChoice) {
            case 0:
                System.out.println(player.getName() + " wylosował kamień");
                return 1;
            case 1:
                System.out.println(player.getName() + " wylosował papier");
                return 2;
            case 2:
                System.out.println(player.getName() + " wylosował nożyce");
                return 3;
            case 3:
                System.out.println(player.getName() + " wylosował jaszczurkę");
                return 4;
            case 4:
                System.out.println(player.getName() + " wylosował Spocka");
                return 5;
            default:
                return -1;
        }
    }

    public static void presentScore(Player player1, Player player2) {
        System.out.println("Aktualny wynik: (" + player1.getName() + ":" + player2.getName() + ") " + player1.getScore() + ":" + player2.getScore());
    }

    public static void verbalValidation(int option) {
//
        switch(option) {
            case 1:
                System.out.println("Kamień bije nożyce");
                break;
            case 2:
                System.out.println("Nożyce tną papier");
                break;
            case 3:
                System.out.println("Papier przykrywa kamień");
                break;
            case 4:
                System.out.println("Kamień zgniata jaszczurkę");
                break;
            case 5:
                System.out.println("Jaszczurka zatruwa Spocka");
                break;
            case 6:
                System.out.println("Spock łamie nożyce");
                break;
            case 7:
                System.out.println("Nożyce ranią jaszczurkę");
                break;
            case 8:
                System.out.println("Jaszczurka zjada papier");
                break;
            case 9:
                System.out.println("Papier udowadnia błąd Spocka");
                break;
            case 10:
                System.out.println("Spock kruszy kamień");
                break;
            default:
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

    private static String abortConfirmation() {
        boolean istrue = false;

        System.out.println("Czy na pewno chcesz zakończyć grę? (y/n)");
        while(!istrue) {
            String option = scanner.nextLine();
            if(option.equals("y")) {
                istrue = true;
                System.exit(0);
            } else if(option.equals("n")) {
                return null;
            } else {
                System.out.println("Niedozwolony znak");
            }
        }
        return null;
    }

    private static String startNewGame() {
        boolean istrue = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Czy na pewno chcesz zakończyć aktualną grę? (y/n)");
        while(!istrue) {
            String option = scanner.nextLine();
            if(option.equals("y")) {
                GameProcessor.startGame();
                break;
            } else if(option.equals("n")) {
                return null;
            } else {
                System.out.println("Niedozwolony znak");
            }
        }
        return null;
    }

    public static void ending() {
        String choice;
        do {
            System.out.println("Wciśnij 'x' aby zakończyć grę lub 'n' aby rozpocząc nową grę.");
            choice = scanner.nextLine();
            if ("x".equals(choice) || "X".equals(choice)) {
                choice = abortConfirmation();
            } else if ("n".equals(choice) || "N".equals(choice)) {
                choice = startNewGame();
            } else {
                System.out.println("Podany wybór jest nieprawidłowy. Spróbuj jeszcze raz.\n");
                choice = null;
            }
        } while (choice == null);
    }


}
