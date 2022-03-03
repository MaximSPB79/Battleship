package battleship;

import java.util.Scanner;

public class Shot {

    public final char defeat = 'X';
    private final char miss = 'M';
    String strShot;
    private int shotRow;
    private int shotColumn;
    Scanner sc = new Scanner(System.in);
    private int countHit = 0;

    public void shot(PlayingField player, PlayingField playerFogOfWar, PlayingField enemy) {

        playerFogOfWar.printMap();
        System.out.println("---------------------");
        player.printMap();
        printShot(player);
        while (true) {
            inputShot();
            if (exceptionHandling()) { // проверяем кооректность входных координат
                break;
            }
        }
        convertingCharactersToNumbers(); // конвертируе входные данные в числа

        actionChoice(playerFogOfWar, enemy);



    }

    private void printShot(PlayingField player) {
        System.out.println(player.getName() + " , it's your turn: \n");
    }

    private void inputShot() {
        strShot = sc.next();
        System.out.println();
    }

    private void convertingCharactersToNumbers() {
        shotRow = strShot.charAt(0) - 65;
        if (strShot.length() != 3) {
            shotColumn = strShot.charAt(1) - 49;
        } else if (strShot.charAt(1) == 49 && strShot.charAt(2) == 48) {
            shotColumn = 9;
        }
    }

    private boolean exceptionHandling() {
        if (strShot.length() == 3 &&
                (strShot.charAt(0) > 75 ||
                        (strShot.charAt(1) != 49 && strShot.charAt(2) != 48))) {
            System.out.println("Error! You entered the wrong coordinates! Try again: \n");
            return false;
        } else if (strShot.length() == 2 && strShot.charAt(0) >= 75) {
            System.out.println("Error! You entered the wrong coordinates! Try again: \n");
            return false;
        }
        return true;
    }

    private void actionChoice(PlayingField playerFogOfWar, PlayingField enemy) {
        if (enemy.map[shotRow][shotColumn] == Ships.getPartShip() ||
                enemy.map[shotRow][shotColumn] == 'X') {
            methodHit(playerFogOfWar, enemy);
            countHit++;
        } else {
            methodMiss(playerFogOfWar, enemy);
        }
    }

    private void methodHit(PlayingField playerFogOfWar, PlayingField enemy) {
        playerFogOfWar.map[shotRow][shotColumn] = defeat;
        enemy.map[shotRow][shotColumn] = defeat;
        if (shipKill(enemy)) {
            printKillShip();
        } else {
            printHit();
        }
    }

    public boolean checkForVictory(PlayingField enemy) {
        int countDefeat = 0;

        for (int i = 0; i < enemy.map.length; i++) {
            for (int j = 0; j < enemy.map.length; j++) {
                if (enemy.map[i][j] == defeat) {
                    countDefeat++;
                }
            }
        }
        if (countDefeat == 17) {
            return false;
        }
        return true;
    }

    private void printKillShip() {

        System.out.println("You sank a ship! Specify a new target: \n");
    }

    private void printHit() {

        System.out.println("You hit a ship! Try again: \n");
    }

    private void methodMiss(PlayingField playerFogOfWar, PlayingField enemy) {
        playerFogOfWar.map[shotRow][shotColumn] = miss;
        enemy.map[shotRow][shotColumn] = miss;
        System.out.println("You missed. Try again: \n");
    }

    public void printVictory() {

        System.out.println("You sank the last ship. You won. Congratulations! \n");
    }

    private boolean shipKill(PlayingField player) {

        try {
            if (player.map[shotRow][shotColumn + 1] == 'O' ||
                    player.map[shotRow][shotColumn - 1] == 'O' ||
                    player.map[shotRow - 1][shotColumn] == 'O' ||
                    player.map[shotRow + 1][shotColumn] == 'O') {
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.getMessage();
        }
        return true;
    }
}

