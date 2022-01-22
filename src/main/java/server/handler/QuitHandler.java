package server.handler;

import server.ChineseCheckerServer;
import server.state.IllegalCommandException;

public class QuitHandler extends BaseHandler{
    public QuitHandler(ChineseCheckerServer man) {
        super(man);
    }

    @Override
    public void setNext(IHandler h) {
        this.next = h;
    }

    @Override
    public void handle(String command) throws IllegalCommandException {
        if(command.startsWith("QUIT")){
            //wzór komendy: MESSAGE NazwaGracza wiadomość
            manager.sendCommand("ALL QUIT");
        }
        if(next != null) next.handle(command);
    }
}
