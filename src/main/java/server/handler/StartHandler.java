package server.handler;

import server.ChineseCheckerServer;

public class StartHandler extends BaseHandler{
    public StartHandler(ChineseCheckerServer man) {
        super(man);
    }

    @Override
    public void setNext(Handler h) {

    }

    @Override
    public void handle(String command) {
        if (command.startsWith("START")){

            //when start is requested
            //if( manager.getBoard(). manager.getPlayerCount())

        } else if (next != null) {
            next.handle(command);
        }
    }
}
