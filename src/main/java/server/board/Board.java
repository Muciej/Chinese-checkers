package server.board;

import server.ChineseCheckerServer;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Klasa reprezentująca planszę gry
 * Poza funkcją przechowywania informacji implementuje także
 * częśc logiki gry
 */
public class Board {
    ChineseCheckerServer manager;
    ArrayList<Integer> validPlayersNumber;
    ArrayList<String> initPositions;
    ArrayList<String> destPositions;
    Field[][] fields;
    int width;
    int height;

    /**
     * Konstruktor
     * @param manager - klasa managera gry
     * @param boardFileName - nazwa pliku, z którego ma być wczytana plansza
     */
    public Board(ChineseCheckerServer manager, String boardFileName) {
        this.manager = manager;
        validPlayersNumber = new ArrayList<>();
        initPositions = new ArrayList<>();
        destPositions = new ArrayList<>();
        readFromFile(boardFileName);
    }

    /**
     * Funkcja wczytująa planszę z pliku .txt
     * UWAGA: PLIK MUSI BYĆ POPRAWNIE SFORMATOWANY
     * @param filename - nazwa pliku
     */
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

    /**
     * Funkcja realizująca ruch
     * @param fromX - wsp. X pola pocz.
     * @param fromY - wsp. Y pola pocz.
     * @param toX - wsp. X pola docelowego
     * @param toY - wsp. Y pola docelowego
     */
    public void doMove(int fromX, int fromY, int toX, int toY){
        fields[toX][toY].setOccupant( fields[fromX][fromY].getOccupant());
        fields[fromX][fromY].setOccupant(null);
    }

    /**
     * Funkcja wypełniająca odpowiednim kolorem pola o zadanej liczbie startowej
     * @param occupant - nazwa gracza, który ma objąć pola
     * @param posNo - nr startowy pól, które ma objąć gracz
     * @param color - kolor pionków gracza
     */
    public void fillPos(String occupant, int posNo, Color color){
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                Field tempField = fields[j][i];
                if(tempField != null && tempField.getStartFieldNo() == posNo){
                    tempField.setOccupant(occupant);
                    tempField.setColor(color);
                }
            }
        }
    }

    /**
     * Funckja sprawdzająca, czy dana plansza pozwala na grę dla danej liczby graczy
     * @param count - liczba graczy do sprawdzenia
     * @return - true, jeśli gra jest możliwa i false w przeciwnym wypadku
     */
    public boolean isValidPlayerCount(int count){
        return validPlayersNumber.contains(count);
    }

    /**
     * Funkcja zwracająca tablicę zawierającą nr pól startowych, które powinny zostać
     * zajęte na starcie oraz na końcu gry
     * @param playerCount - liczba graczy
     * @param goal - określamy, czy chcemy pola początkowe ( "START" ), czy końcowe ( cokolwiek innego)
     * @return zwraca tablicę intów ozn. nr pól, które poiwnny zostać zajęte
     */
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

    /**
     * Funkcja pomocnicza służąca do wypisania planszy na konsoli
     */
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

    /**
     * Funkcja sprawdzająca, czy dany gracz przypadkiem nie wygrywa
     * @param playerName - nazwa gracza
     * @param posNo - nr pól startowych do sprawdzenia
     * @return - true, jeśli dany gracz wygrywa i false w przeciwnym wypadku
     */
    public boolean checkPos(String playerName, int posNo){
        System.out.println("Checking win for "+ playerName + " on "+posNo);
        boolean occupies = true;
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                Field tempField = fields[j][i];
                if (tempField != null && tempField.getStartFieldNo() == posNo && (tempField.getOccupant() == null || !tempField.getOccupant().equals(playerName) ) ) {
                    occupies = false;
                    break;
                }
            }
        }
        return occupies;
    }
}
