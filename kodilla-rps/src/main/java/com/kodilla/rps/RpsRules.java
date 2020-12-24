package com.kodilla.rps;

import java.util.Scanner;

public class RpsRules implements Rules {
    public int selectionResult(int player1Choice, int player2Choice) {
        switch(player1Choice) {
            case 1:
                switch (player2Choice) {
                    case 2:
                        IOService.verbalValidation(3);
                        return 2;
                    case 3:
                        IOService.verbalValidation(1);
                        return 1;
                    case 4:
                        IOService.verbalValidation(4);
                        return 1;
                    case 5:
                        IOService.verbalValidation(10);
                        return 2;
                    default:
                        return 0;
                }
            case 2:
                switch (player2Choice) {
                    case 1:
                        IOService.verbalValidation(3);
                        return 1;
                    case 3:
                        IOService.verbalValidation(2);
                        return 2;
                    case 4:
                        IOService.verbalValidation(8);
                        return 2;
                    case 5:
                        IOService.verbalValidation(9);
                        return 1;
                    default:
                        return 0;
                }
            case 3:
                switch (player2Choice) {
                    case 1:
                        IOService.verbalValidation(1);
                        return 2;
                    case 2:
                        IOService.verbalValidation(2);
                        return 1;
                    case 4:
                        IOService.verbalValidation(7);
                        return 1;
                    case 5:
                        IOService.verbalValidation(6);
                        return 2;
                    default:
                        return 0;
                }
            case 4:
                switch (player2Choice) {
                    case 1:
                        IOService.verbalValidation(4);
                        return 2;
                    case 2:
                        IOService.verbalValidation(8);
                        return 1;
                    case 3:
                        IOService.verbalValidation(7);
                        return 2;
                    case 5:
                        IOService.verbalValidation(5);
                        return 1;
                    default:
                        return 0;
                }
            case 5:
                switch (player2Choice) {
                    case 1:
                        IOService.verbalValidation(10);
                        return 1;
                    case 2:
                        IOService.verbalValidation(9);
                        return 2;
                    case 3:
                        IOService.verbalValidation(6);
                        return 1;
                    case 4:
                        IOService.verbalValidation(5);
                        return 2;
                    default:
                        return 0;
                }
        }
        return -1;
    }
}
