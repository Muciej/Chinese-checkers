package server.handler;

import server.ChineseCheckerServer;
import server.Player;
import server.state.IllegalCommandException;

import java.awt.*;

public class AddPlayerHandler extends BaseHandler{
    public AddPlayerHandler(ChineseCheckerServer man) {
        super(man);
    }

    @Override
    public void setNext(IHandler h) {
        this.next = h;
    }

    @Override
    public void handle(String command) throws IllegalCommandException {
        if(command.startsWith("ADD")){

            //wzór komendy ADD NazwaGracza R G B

            String[] tab = command.split(" ");
            Color c = new Color(Integer.parseInt(tab[2]), Integer.parseInt(tab[3]), Integer.parseInt(tab[4]));
            Player player = new Player(tab[1], c);
            boolean validName = true;
            for(Player p: manager.getPlayers()){
                if (p.getName().equals(player.getName())) {
                    validName = false;
                    break;
                }
            }
            if(validName) {
                manager.getPlayers().add(player);
                System.out.println("Added new player: "+player.getName() + " " + player.getColor().toString());
            } else {
                System.out.println("Name already occupied");
                manager.sendCommand("ALL POPUP Name occupied");
            }
        }
        else {
            if( next != null) {
                next.handle(command);
            } else{
                System.out.println("Unrecognized command");
            }
        }
    }
}
