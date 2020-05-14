import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Monopoly{

    private Square[] square = new Square[40];
    private Player[] player = new Player[4];

    public Monopoly(){

        //squares.txt file to string array
        try {
            Path file = Paths.get("squares.txt");
            Scanner scanner = new Scanner(file);

            int type;
            int j = 0;

            //string array to squares
            for (int k = 0; k < 40; k++) {
                String[] s = scanner.nextLine().split(",");

                type = Integer.parseInt(s[0]);
                switch (type) {
                    case 1:
                        square[j] = new Square(s[1]);
                        j++;
                        break;
                    case 2:
                        square[j] = new PropertySquare(s[1], s[2], Double.parseDouble(s[3]), Double.parseDouble(s[4]),
                                Double.parseDouble(s[5]), Double.parseDouble(s[6]), Double.parseDouble(s[7]), Double.parseDouble(s[8]));
                        j++;
                        break;
                    case 3:
                        square[j] = new TaxSquare(s[1], Double.parseDouble(s[2]));
                        j++;
                        break;
                    case 4:
                        square[j] = new RailroadSquare(s[1], Double.parseDouble(s[2]), Double.parseDouble(s[3]));
                        j++;
                        break;
                    case 5:
                        square[j] = new UtilitySquare(s[1], Double.parseDouble(s[2]), Double.parseDouble(s[3]));
                        j++;
                        break;
                }
            }
            scanner.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }



        //Players
        player[0] = new Player("Player 1", 1500.0, 0);
        player[1] = new Player("Player 2", 1500.0, 0);
        player[2] = new Player("Player 3", 1500.0, 0);
        player[3] = new Player("Player 4", 1500.0, 0);
    }

    public Square[] getSquare() {
        return square;
    }

    public Player[] getPlayer(){
        return player;
    }
}