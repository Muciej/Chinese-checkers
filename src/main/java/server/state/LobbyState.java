package server.state;

import server.ChineseCheckerServer;
import server.handler.AddPlayerHandler;
import server.handler.IHandler;
import server.handler.StartHandler;

public class LobbyState extends State {

    public LobbyState(ChineseCheckerServer con){
        super(con);
    }

    @Override
    public IHandler getHandler() {
        IHandler first = new AddPlayerHandler(context);
        first.setNext(new StartHandler(context));

        return first;
    }

    @Override
    public void onStateInit() {

    }
}
