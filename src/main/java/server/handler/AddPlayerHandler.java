package server.handler;

import server.ChineseCheckerServer;

public class AddPlayerHandler extends BaseHandler{
    public AddPlayerHandler(ChineseCheckerServer man) {
        super(man);
    }

    @Override
    public void setNext(Handler h) {
        this.next = h;
    }

    @Override
    public void handle(String command) {
        if(command.startsWith("ADD")){

            //adding player

        }
        else {
            if( next != null) {
                //next.handle(command);
            }
        }
    }
}
