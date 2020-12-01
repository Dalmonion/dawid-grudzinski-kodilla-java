package com.kodilla.rps;

public class GameProcessor {
    private Player player1;
    private Player player2;
    private Rules rules;
    private int winningRounds;


    public GameProcessor(Player player1, Player player2, Rules rules, int winningRounds) {
        this.player1 = player1;
        this.player2 = player2;
        this.rules = rules;
        this.winningRounds = winningRounds;
    }

    public void startGame() {

        while ((player1.getScore() != winningRounds) && (player2.getScore() != winningRounds)) {
            IOService.presentScore(player1, player2);
            int winning = rules.selectionResult(player1.getMove(), player2.getMove());

            if (winning == 1) {
                player1.addScore();
                IOService.roundWinner(player1);
            } else if (winning == 2) {
                player2.addScore();
                IOService.roundWinner(player2);
            } else {
                IOService.verbalValidation(4);
            }
        }

        IOService.gameWinner(player1, player2);



    }
}
