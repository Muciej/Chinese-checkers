package server.state;

import server.ChineseCheckerServer;
import server.handler.AddPlayerHandler;
import server.handler.IHandler;
import server.handler.MessageHandler;
import server.handler.StartHandler;

public class LobbyState extends State {

    public LobbyState(ChineseCheckerServer con){
        super(con);
    }

    @Override
    public IHandler getHandler() {
        IHandler first = new AddPlayerHandler(context);
        IHandler second = new StartHandler(context);
        IHandler third = new MessageHandler(context);
        first.setNext(second);
        second.setNext(third);

        return first;
    }

    @Override
    public void onStateInit() {

    }
}
