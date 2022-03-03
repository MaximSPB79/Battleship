package battleship;

import java.util.Scanner;

import static battleship.Ships.*;

public class Main {

    public static void main(String[] args) {

        playGame();
    }

    public static void playGame() {
        Shot shotPlayer1 = new Shot();
        Shot shotPlayer2 = new Shot();

        PlayingField player1 = new PlayingField("Player 1");
        PlayingField player2 = new PlayingField("Player 2");

        PlayingField player_1_FogOfWar = new PlayingField();
        PlayingField player_2_FogOfWar = new PlayingField();

        streamShips(player1, player_1_FogOfWar);
        streamShips(player2, player_2_FogOfWar);

        do {
            shotPlayer1.shot(player1, player_1_FogOfWar, player2);
            if (!shotPlayer1.checkForVictory(player2)) {
                break;
            } else {
                player1.passTheMove();
            }
            shotPlayer2.shot(player2, player_2_FogOfWar, player1);
            if (!shotPlayer2.checkForVictory(player1)) {
                break;
            } else {
                player2.passTheMove();
            }
        } while (true);
        shotPlayer1.printVictory();
    }


    private static void streamShips(PlayingField player, PlayingField playerFogOfWar) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(player.getName() + " , place your ships on the game field \n");
        playerFogOfWar.initMap();
        player.initMap();
        player.printMap();

        AIRCRAFT_CARRIER.creationShip(player.map);
        player.printMap();

        BATTLESHIP.creationShip(player.map);
        player.printMap();

        SUBMARINE.creationShip(player.map);
        player.printMap();

        CRUISER.creationShip(player.map);
        player.printMap();

        DESTROYER.creationShip(player.map);
        player.printMap();
        System.out.println();

        player.passTheMove();

    }


}



