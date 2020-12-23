package com.kodilla.rps;

public class RpsRunner {
    public static void main(String[] args) {
//        Player player1;
//        Player player2;
//        IOService.showWelcomeMessage();
//
//        int playerOption = IOService.getGamePlayerOption();
//        if(playerOption == 1) {
//            String playerName = IOService.getPlayerName();
//            player1 = new HumanPlayer(playerName);
//            player2 = new ComputerPlayer();
//        } else if (playerOption == 2) {
//            player1 = new ComputerPlayer("Komputer1");
//            player2 = new ComputerPlayer("Komputer2");
//        } else {
//            String playerName1 = IOService.getPlayersName("pierwszego");
//            player1 = new HumanPlayer(playerName1);
//            String playerName2 = IOService.getPlayersName("drugiego");
//            player2 = new HumanPlayer(playerName2);
//        }
//        int winningRounds = IOService.setWinningRounds();
//        Rules rules = new RpsRules();
//        GameProcessor gameProcessor = new GameProcessor(player1, player2, rules, winningRounds);
//        gameProcessor.startGame();

        GameProcessor.start();
    }
}
