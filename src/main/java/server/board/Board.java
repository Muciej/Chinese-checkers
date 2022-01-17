package server.board;

import server.ChineseCheckerServer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Board {
    ChineseCheckerServer manager;
    ArrayList<Integer> validPlayersNumber;
    ArrayList<String> initPositions;
    ArrayList<String> destPositions;
    Field[][] fields;
    int width;
    int height;
    public Board(ChineseCheckerServer manager, String boardFileName) {
        this.manager = manager;
        validPlayersNumber = new ArrayList<>();
        initPositions = new ArrayList<>();
        destPositions = new ArrayList<>();
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
            for (String s : temp) {
                //System.out.println(temp[i]);
                validPlayersNumber.add(Integer.parseInt(s));
            }
            for(int i=0; i<validPlayersNumber.size(); i++){
                line = scanner.nextLine();
                initPositions.add(line);
                line = scanner.nextLine();
                destPositions.add(line);
            }
            int j = 0;
            while(scanner.hasNextLine()){
                line = scanner.nextLine();
                for(int i = 0; i< line.length(); i++){
                    Field tempField = switch (line.charAt(i)) {
                        case '-', ' ' -> null;
                        case '*' -> new Field(0);
                        default -> new Field(Character.getNumericValue(line.charAt(i)));
                    };
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

    public int[] getPositions(int playerCount, String goal){
        ArrayList<String> posArray;
        if(goal.equals("START")){
            posArray = initPositions;
        } else {
            posArray = destPositions;
        }
        int[] pos = new int[playerCount];
        String line = null;
        for(String s: posArray){
            if(s.startsWith(Integer.toString(playerCount))){
                line = s;
            }
        }
        String[] splitLine = null;
        if(line != null){
            splitLine = line.substring(2).split(" ");
            for(int i=0; i<playerCount; i++){
                pos[i] = Integer.parseInt(splitLine[i]);
            }
        } else{
            System.out.println("Nieprawidłowa liczba graczy");
        }

        return pos;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Field getField(int x, int y){
        return fields[x][y];
    }

    public void showBoard(){
        if(fields == null) return;
        System.out.print("   ");
        for(int i=0; i<width; i++){
            System.out.print(i);
            if(i < 10) {
                System.out.print(' ');
            }
        }
        System.out.print('\n');
        for(int i=0; i<height; i++){
            System.out.print(i);
            if(i < 10){
                System.out.print("  ");
            } else {
                System.out.print(" ");
            }
            for(int j=0; j<width; j++){
                Field tempField = fields[j][i];
                if(tempField != null){
                    if(tempField.getOccupant()!= null){
                        System.out.print(tempField.getOccupant().charAt(0));
                        System.out.print(tempField.getOccupant().charAt(1));
                    } else{
                        System.out.print("[]");
                    }
                } else{
                    System.out.print("  ");
                }
            }
            System.out.print('\n');
        }
    }
}
