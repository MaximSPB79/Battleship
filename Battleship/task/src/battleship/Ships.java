package battleship;

import java.util.Scanner;

public enum Ships {
    AIRCRAFT_CARRIER(5, "Aircraft Carrier"),
    BATTLESHIP(4, "Battleship"),
    SUBMARINE(3, "Submarine"),
    CRUISER(3, "Cruiser"),
    DESTROYER(2, "Destroyer");

    private int size = 0;
    private final String name;
    private int fromRow;
    private int buferRow;
    private int fromColumn;
    private int buferColumn;
    private int beforeRow;
    private int beforeColumn;
    private String strFrom;
    private String strBefore;
    private static final char partShip = 'O';
    Scanner scanner = new Scanner(System.in);

    Ships(int size, String name) {

        this.size = size;
        this.name = name;
    }


    public static char getPartShip() {

        return partShip;
    }

    public void creationShip(char[][] map) {

        inputPrintInfo();
        while (true) {
            input();
            conversionOfCoordinatesToNumericalFormat();
            if (creatingShip(map)) {
                break;
            }
        }
    }

    private void inputPrintInfo() {
        System.out.println();
        System.out.println("Enter the coordinates of the " + this.name + "(" + this.size + " cells):");
        System.out.println();
    }

    private void input() {
        strFrom = scanner.next();
        strBefore = scanner.next();
        System.out.println();
    }

    private void conversionOfCoordinatesToNumericalFormat() {

        fromRow = strFrom.charAt(0) - 65;
        fromColumn = getY(strFrom);
        beforeRow = strBefore.charAt(0) - 65;
        beforeColumn = getY(strBefore);

        if (fromRow > beforeRow) {
            buferRow = fromRow;
            fromRow = beforeRow;
            beforeRow = buferRow;
        } else if (fromColumn > beforeColumn) {
            buferColumn = fromColumn;
            fromColumn = beforeColumn;
            beforeColumn = buferColumn;
        }
    }

    static int getY(String strLenght) {
        if (strLenght.length() == 3) {
            return 9;
        } else {
            return strLenght.charAt(1) - 49;
        }
    }

    private boolean creatingShip(char[][] map) {

        if (fromRow == beforeRow &&
                beforeColumn - fromColumn == this.size - 1 &&
                freeSpaceCheckHorizontal(map)) {
            initializeTheShipHorizontally(map);
            return true;
        } else if (fromColumn == beforeColumn &&
                beforeRow - fromRow == this.size - 1 &&
                freeSpaceCheckVertical(map)) {
            initializeTheShipVertically(map);
            return true;
        } else if ((fromRow == beforeRow ||
                fromColumn == beforeColumn) &&
                (beforeColumn - fromColumn != this.size - 1 ||
                        beforeRow - fromRow != this.size - 1)) {
            printShipLengthError();
        } else if (fromRow != beforeRow && fromColumn != beforeColumn) {
            printShipRowError();
        } else {
            printErrorTheShipIsTooClose();
        }
        return false;
    }


    private void initializeTheShipVertically(char[][] map) {
        for (int i = 0; i < this.size; i++) {
            map[fromRow++][fromColumn] = partShip;
        }
    }

    private void initializeTheShipHorizontally(char[][] map) {
        for (int i = 0; i < this.size; i++) {
            map[fromRow][fromColumn++] = partShip;
        }
    }


    private void printShipLengthError() {
        System.out.println("Error! Wrong length of the " + this.name + " ! Try again:");
        System.out.println();
    }

    private void printShipRowError() {
        System.out.println("Error! Wrong ship location! Try again:");
        System.out.println();
    }

    private void printErrorTheShipIsTooClose() {
        System.out.println("Error! You placed it too close to another one. Try again:");
        System.out.println();
    }

    private boolean freeSpaceCheckHorizontal(char[][] map) {
        if (fromRow == 0 && fromColumn == 0) {
            for (int i = fromRow; i <= beforeRow + 1; i++) {
                for (int j = fromColumn; j <= beforeColumn + 1; j++) {
                    if (map[i][j] == partShip) {
                        return false;
                    }
                }
            }
        } else if (fromRow == 0 && beforeColumn == 9) {
            for (int i = fromRow; i <= beforeRow + 1; i++) {
                for (int j = fromColumn - 1; j <= beforeColumn; j++) {
                    if (map[i][j] == partShip) {
                        return false;
                    }
                }
            }
        } else if (fromRow == 9 && fromColumn == 0) {
            for (int i = fromRow - 1; i <= beforeRow; i++) {
                for (int j = fromColumn; j <= beforeColumn + 1; j++) {
                    if (map[i][j] == partShip) {
                        return false;
                    }
                }
            }
        } else if (fromRow == 9 && beforeColumn == 9) {
            for (int i = fromRow - 1; i <= beforeRow; i++) {
                for (int j = fromColumn - 1; j <= beforeColumn; j++) {
                    if (map[i][j] == partShip) {
                        return false;
                    }
                }
            }
        } else if (beforeColumn == 9) {
            for (int i = fromRow - 1; i <= beforeRow + 1; i++) {
                for (int j = fromColumn - 1; j <= beforeColumn; j++) {
                    if (map[i][j] == partShip) {
                        return false;
                    }
                }
            }
        } else if (fromRow == 0) {
            for (int i = fromRow; i <= beforeRow + 1; i++) {
                for (int j = fromColumn - 1; j <= beforeColumn + 1; j++) {
                    if (map[i][j] == partShip) {
                        return false;
                    }
                }
            }
        } else if (fromRow == 9) {
            for (int i = fromRow - 1; i <= beforeRow; i++) {
                for (int j = fromColumn - 1; j <= beforeColumn + 1; j++) {
                    if (map[i][j] == partShip) {
                        return false;
                    }
                }
            }
        } else if (fromColumn == 0) {
            for (int i = fromRow - 1; i <= beforeRow + 1; i++) {
                for (int j = fromColumn; j <= beforeColumn + 1; j++) {
                    if (map[i][j] == partShip) {
                        return false;
                    }
                }
            }
        } else {
            for (int i = fromRow - 1; i <= beforeRow + 1; i++) {
                for (int j = fromColumn - 1; j <= beforeColumn + 1; j++) {
                    if (map[i][j] == partShip) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean freeSpaceCheckVertical(char[][] map) {
        if (fromRow == 0 && fromColumn == 0) {
            for (int i = fromRow; i <= beforeRow + 1; i++) {
                for (int j = fromColumn; j <= beforeColumn + 1; j++) {
                    if (map[i][j] == partShip) {
                        return false;
                    }
                }
            }
        } else if (fromRow == 0 && fromColumn == 9) {
            for (int i = fromRow; i <= beforeRow + 1; i++) {
                for (int j = fromColumn; j <= beforeColumn; j++) {
                    if (map[i][j] == partShip) {
                        return false;
                    }
                }
            }
        } else if (beforeRow == 9 && fromColumn == 0) {
            for (int i = fromRow - 1; i <= beforeRow; i++) {
                for (int j = fromColumn; j <= beforeColumn + 1; j++) {
                    if (map[i][j] == partShip) {
                        return false;
                    }
                }
            }
        } else if (beforeRow == 9 && fromColumn == 9) {
            for (int i = fromRow - 1; i <= beforeRow; i++) {
                for (int j = fromColumn - 1; j <= beforeColumn; j++) {
                    if (map[i][j] == partShip) {
                        return false;
                    }
                }
            }
        } else if (fromRow == 0) {
            for (int i = fromRow; i <= beforeRow + 1; i++) {
                for (int j = fromColumn - 1; j <= beforeColumn + 1; j++) {
                    if (map[i][j] == partShip) {
                        return false;
                    }
                }
            }
        } else if (beforeRow == 9) {
            for (int i = fromRow - 1; i <= beforeRow; i++) {
                for (int j = fromColumn - 1; j <= beforeColumn + 1; j++) {
                    if (map[i][j] == partShip) {
                        return false;
                    }
                }
            }
        } else if (fromColumn == 0) {
            for (int i = fromRow - 1; i <= beforeRow + 1; i++) {
                for (int j = fromColumn; j <= beforeColumn + 1; j++) {
                    if (map[i][j] == partShip) {
                        return false;
                    }
                }
            }
        } else if (fromColumn == 9) {
            for (int i = fromRow - 1; i <= beforeRow + 1; i++) {
                for (int j = fromColumn - 1; j <= beforeColumn; j++) {
                    if (map[i][j] == partShip) {
                        return false;
                    }
                }
            }
        } else {
            for (int i = fromRow - 1; i <= beforeRow + 1; i++) {
                for (int j = fromColumn - 1; j <= beforeColumn + 1; j++) {
                    if (map[i][j] == partShip) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}






