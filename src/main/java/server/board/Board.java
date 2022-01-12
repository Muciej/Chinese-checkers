package server.board;

import server.ChineseCheckerServer;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Board {
    ChineseCheckerServer manager;
    ArrayList<Integer> validPlayersNumber;
    Field[][] fields;
    int width;
    int height;
    public Board(ChineseCheckerServer manager, String boardFileName) {
        this.manager = manager;
        validPlayersNumber = new ArrayList<>();
        readFromFile(boardFileName);
    }

    void readFromFile(String filename){
        try {
            Scanner scanner = new Scanner(new File(filename));
            String line = scanner.nextLine();
            width = Integer.parseInt( line.substring(7) );
            line = scanner.nextLine();
            height = Integer.parseInt( line.substring(8) );
            fields = new Field[width][height];
            line = scanner.nextLine().substring(18);
            String[] temp = line.split(",");
            for(int i=0; i<temp.length; i++){
                //System.out.println(temp[i]);
                validPlayersNumber.add(Integer.parseInt(temp[i]));
            }
            int j = 0;
            while(scanner.hasNextLine()){
                line = scanner.nextLine();
                for(int i = 0; i< line.length(); i++){
                    Field tempField;
                    switch (line.charAt(i)){
                        case '-':
                        case ' ':
                            tempField = null;
                            break;
                        case '*':
                            tempField = new Field(0);
                            break;
                        default:
                            tempField = new Field( Character.getNumericValue(line.charAt(i)));
                    }
                    fields[i][j] = tempField;
                }
                j++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void doMove(int fromX, int fromY, int toX, int toY){
        fields[toX][toY].setOccupant( fields[fromX][fromY].getOccupant());
        fields[fromX][fromY].setOccupant(null);
    }

    public boolean isValidPlayerCount(int count){
        return validPlayersNumber.contains(count);
    }

    public ArrayList<String> getPositions(int playerCount){
        return null;
    }
}
