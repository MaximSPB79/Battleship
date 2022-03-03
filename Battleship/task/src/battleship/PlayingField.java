package battleship;


import java.util.Scanner;

public class PlayingField {

    private final int row = 10;
    private int column = 10;
    private final char fogOfWar = '~';
    protected char[][] map = new char[row][column];
    private final char[] rowName = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    private String name;
    Scanner scanner = new Scanner(System.in);

    public PlayingField(String name) {
        this.name = name;
    }

    public PlayingField() {

    }

    void initMap() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                map[i][j] = fogOfWar;
            }
        }
    }

    public String getName() {
        return name;
    }

    void printMap() {

        printHeaderMap();
        printBodyMap();
    }

    private void printHeaderMap() {
        System.out.print(" " + " ");
        for (int i = 0; i < column; i++) {
            printNumberMap(i);
        }
        System.out.println();
    }

    private void printNumberMap(int i) {

        System.out.print(i + 1 + " ");
    }

    private void printBodyMap() {
        for (int i = 0; i < row; i++) {
            char n = rowName[i];
            System.out.print(n + " ");
            for (int j = 0; j < column; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    public void passTheMove() {
        System.out.println("Press Enter and pass the move to another player");
        String blanc = scanner.nextLine();
    }
}

