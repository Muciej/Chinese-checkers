package server.handler;

import server.ChineseCheckerServer;
import server.state.IllegalCommandException;

public class MessageHandler extends BaseHandler{
    public MessageHandler(ChineseCheckerServer man) {
        super(man);
    }

    @Override
    public void setNext(IHandler h) {
        this.next = h;
    }

    @Override
    public void handle(String command) throws IllegalCommandException {
        if(command.startsWith("MESSAGE")){

            //wzór komendy: MESSAGE NazwaGracza wiadomość
            manager.sendCommand("ALL "+command);

        } else{
            if(next != null) next.handle(command);
        }
    }
}