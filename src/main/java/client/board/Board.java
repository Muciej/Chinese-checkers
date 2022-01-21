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
    boolean moving = false;
    int from_x, from_y;

    public Board(ClientFacade facade){
        this.facade = facade;
    }

    public void initBoard(Scanner scanner, int width, int height, int occupiedStartFields){

        this.height = height;
        this.width = width;
        fields = new Field[width][height];

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
                    fields[j][i] = new NoField(this, bcgrndCol);
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
                        fields[j][i] = new OccupiedField(this, j,i,idInt, bcgrndCol, tempCol);
                    } else{
                        fields[j][i] = new OccupiedField(this,j,i,0, bcgrndCol, Color.black);
                        fields[j][i].setField(Color.black, 0.7);
                    }
                    fields[j][i].addMouseListener(fields[j][i]);
                }
            }
        }
        for(int i = 0; i < height; i++){
            for(int j=0; j< width; j++){
                this.add(fields[j][i]);
            }
        }
        repaint();
        System.out.println("Board init completed");
    }

    public void doMove(int fromX, int fromY, int toX, int toY){
        /*
        Field oldField = fields[fromX][fromY];
        Field newField = fields[toX][toY];
        Field tempField = new OccupiedField(this, toX, toY,newField.getStartNo(), bcgrndCol, oldField.getFigCol());
        fields[toX][toY] = tempField;
        tempField = new OccupiedField(this, fromX, fromY, oldField.getStartNo(), bcgrndCol);
        fields[fromX][fromY] = tempField;
        unhighlight();
        for(int i = 0; i < height; i++){
            for(int j = 0; j<width; j++){
                fields[j][i].repaint();
            }
        }

         */
        unhighlight();
        fields[toX][toY].setField(fields[fromX][fromY].getFigCol(), 1.0);
        fields[fromX][fromY].setField(Color.BLACK, 0.7);
        repaint();
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

    public void unhighlight(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j<width; j++){
                fields[j][i].setStroke(false);
            }
        }
    }

    public void initiateMove(int x, int y){
        if( !moving ){
            from_x = x;
            from_y = y;
            moving = true;
        } else {
            facade.sendCommand("MOVE "+facade.getName() + " "+from_x + " " + from_y + " " +x + " "+ y);
            moving = false;
            unhighlight();
        }
    }
}
