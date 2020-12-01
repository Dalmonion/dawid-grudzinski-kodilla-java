package com.kodilla.rps.old;

import java.util.Scanner;

public class Board {
    private int gamesNumber;
    Computer computer = new Computer();
    User user1 = new User();

    public void introduce() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Podaj swoje imię: ");
        user1.setName(scan.nextLine());
        System.out.print("Witaj " + user1.getName() + "! Podaj liczbę gier decydujących o zwycięstwie: ");
        setGamesNumber(scan.nextInt());
        scan.nextLine();
        System.out.println("Gramy do " + getGamesNumber() + " zwycięstw. \nRozpoczynam grę...");
        play();
        finish();
    }

    private void finish() {
        Scanner scanner = new Scanner(System.in);
        boolean istrue = false;

        while(!istrue) {
            System.out.println("Aby zakończyć grę wcisnij 'x'. Aby rozpocząć nową grę wcisnij 'n'");
            String option = scanner.nextLine();
            switch (option) {
                case "x":
                    System.exit(0);
                case "n":
                    setGamesNumber(0);
                    istrue = true;
                    introduce();
                default:
                    System.out.println("Wciśnięto niewłaściwy klawisz");
            }
        }

    }

    private void abortGame() {
        boolean istrue = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Czy na pewno chcesz zakończyć grę? (y/n)");
        while(!istrue) {
            String option = scanner.nextLine();
            if(option.equals("y")) {
                istrue = true;
                System.exit(0);
            } else if(option.equals("n")) {
                break;
            } else {
                System.out.println("Niedozwolony znak");
            }
        }
    }

    private void startNewGame() {
        boolean istrue = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Czy na pewno chcesz zakończyć aktualną grę? (y/n)");
        while(!istrue) {
            String option = scanner.nextLine();
            if(option.equals("y")) {
                setGamesNumber(0);
                introduce();
                istrue = true;
            } else if(option.equals("n")) {
                break;
            } else {
                System.out.println("Niedozwolony znak");
            }
        }

    }

    private void play() {
        printInformation();
        boolean end = false;
        while (!end) {
            int userChoice = user1.draw();
            if (userChoice == 3 || userChoice == 4) {
                if(userChoice == 3) {
                    abortGame();
                } else {
                    startNewGame();
                }

            } else {
                validator(userChoice, computer.draw());
                System.out.println("Wynik rundy (gracz:komputer) = " + user1.getWinnings() + ":" + computer.getCompWinnings());
                System.out.println();

                if (computer.getCompWinnings() == gamesNumber) {
                    System.out.println("Wygrywa komputer z wynikiem: " + computer.getCompWinnings() + ":" + user1.getWinnings());
                    end = true;
                }
                if (user1.getWinnings() == gamesNumber) {
                    System.out.println("Wygrywa komputer z wynikiem: " + user1.getWinnings() + ":" + computer.getCompWinnings());
                    end = true;
                }
            }
        }
    }

    private void validator(int userChoice, int computerChoice) {

        if (userChoice == 0 && computerChoice == 1) {
            System.out.println("Papier bije kamień. Wygrywa komputer");
            computer.addCompWinnings();
        } else if (userChoice == 0 && computerChoice == 2) {
            System.out.println("Kamień bije nożyce. Wygrywa " + user1.getName());
            user1.addWinnings();
        } else if (userChoice == 1 && computerChoice == 0) {
            System.out.println("Papier bije kamień. Wygrywa " + user1.getName());
            user1.addWinnings();
        } else if (userChoice == 1 && computerChoice == 2) {
            System.out.println("Nożyce biją papier. Wygrywa komputer");
            computer.addCompWinnings();
        } else if (userChoice == 2 && computerChoice == 0) {
            System.out.println("Kamień bije nożyce. Wygrywa komputer");
            computer.addCompWinnings();
        } else if (userChoice == 2 && computerChoice == 1) {
            System.out.println("Nożyce biją kamień. Wygrywa " + user1.getName());
            user1.addWinnings();
        } else System.out.println("Remis");
    }


    private void printInformation() {
        System.out.println("Klawisze jakie obsługuje gra:\nKlawisz 1 - zagranie \"kamień\",\nklawisz 2 - zagranie \"papier\",\n" +
                "klawisz 3 - zagranie \"nożyce\",\nklawisz x - zakończenie gry,\nklawisz n - uruchomienie gry od nowa.\n");
    }


    public void setGamesNumber(int gamesNumber) {
        this.gamesNumber = gamesNumber;
    }

    public int getGamesNumber() {
        return gamesNumber;
    }


}
