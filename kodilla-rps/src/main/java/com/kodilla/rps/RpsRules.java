package com.kodilla.rps;

import java.util.Scanner;

public class RpsRules implements Rules {

//    public int selectionResult(int player1Choice, int player2Choice) {
//        if (player1Choice == 0 && player2Choice == 1) {
//            IOService.verbalValidation(3);
//            return 2;
//        } else if (player1Choice == 0 && player2Choice == 2) {
//            IOService.verbalValidation(1);
//            return 1;
//        } else if (player1Choice == 1 && player2Choice == 0) {
//            IOService.verbalValidation(3);
//            return 1;
//        } else if (player1Choice == 1 && player2Choice == 2) {
//            IOService.verbalValidation(2);
//            return 2;
//        } else if (player1Choice == 2 && player2Choice == 0) {
//            IOService.verbalValidation(1);
//            return 2;
//        } else if (player1Choice == 2 && player2Choice == 1) {
//            IOService.verbalValidation(2);
//            return 1;
//        } else {
//            return 0;
//        }
//    }

    public int selectionResult(int player1Choice, int player2Choice) {
        switch(player1Choice) {
            case 0:
                if (player2Choice == 1) {
                    IOService.verbalValidation(3);
                    return 2;
                } else if (player2Choice == 2){
                    IOService.verbalValidation(1);
                    return 1;
                } else {
                    return 0;
                }
            case 1:
                if (player2Choice == 0) {
                    IOService.verbalValidation(3);
                    return 1;
                } else if (player2Choice == 2) {
                    IOService.verbalValidation(2);
                    return 2;
                } else {
                    return 0;
                }
            case 2:
                if (player2Choice == 0) {
                    IOService.verbalValidation(1);
                    return 2;
                } else if (player2Choice == 1) {
                    IOService.verbalValidation(2);
                    return 1;
                } else {
                    return 0;
                }
        }
        return -1;
    }
}
