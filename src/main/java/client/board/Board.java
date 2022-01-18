package client.board;

import client.facade.ClientFacade;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Board extends JPanel {

    final Color bcgrndCol = new Color(97, 148, 229);
    ClientFacade facade;
    int width, height;
    Field[][] fields;
    startFieldCol[] startFieldTab;

    public Board(ClientFacade facade){
        this.facade = facade;
    }

    public void initBoard(Scanner scanner, int height, int width, int occupiedStartFields){
        this.height = height;
        this.width = width;
        fields = new Field[height][width];

        setLayout(new GridLayout(height, width, 0, 0));

        startFieldTab = new startFieldCol[occupiedStartFields];
        //zbieranie informacji o kolorach dla pól startowych
        for(int i=0; i<occupiedStartFields; i++){
            String[] tab = scanner.nextLine().split(" ");
            Color tempCol = new Color( Integer.parseInt(tab[1]), Integer.parseInt(tab[2]), Integer.parseInt(tab[3]));
            startFieldTab[i] = new startFieldCol(Integer.parseInt(tab[0]), tempCol);
        }
        String[] tempTab;
        for(int i=0; i<height; i++){
            tempTab = scanner.nextLine().split(" ");

            for(int j=0; j<width; j++){
                char id = tempTab[j].charAt(0);
                if(id == '*'){
                    fields[j][i] = new NoField(bcgrndCol);
                } else{
                    boolean occupied = false;
                    Color tempCol = null;
                    int idInt = Character.getNumericValue(id);

                    for(startFieldCol sfc: startFieldTab){
                        if(sfc.getStartFieldNo() == idInt){
                            occupied = true;
                            tempCol = sfc.getColor();
                            break;
                        }
                    }
                    if(occupied){
                        fields[j][i] = new OccupiedField(idInt, bcgrndCol, tempCol);
                    } else{
                        fields[j][i] = new FreeField(0, bcgrndCol);
                    }
                }
            }
        }
        for(int i = 0; i < height; i++){
            for(int j=0; j< width; j++){
                this.add(fields[j][i]);
            }
        }
        repaint();
    }

    public void doMove(int fromX, int fromY, int toX, int toY){
        Field oldField = fields[fromX][fromY];
        Field newField = fields[toX][toY];
        Field tempField = new OccupiedField(newField.getStartNo(), bcgrndCol, oldField.getFigCol());
        fields[toX][toY] = tempField;
        tempField = new FreeField(oldField.getStartNo(), bcgrndCol);
        fields[fromX][fromY] = tempField;
    }

    private class startFieldCol{
        int startFieldNo;
        Color color;
        startFieldCol(int startFieldNo, Color color){
            this.startFieldNo = startFieldNo;
            this.color = color;
        }

        public int getStartFieldNo() {
            return startFieldNo;
        }

        public Color getColor() {
            return color;
        }
    }
}
