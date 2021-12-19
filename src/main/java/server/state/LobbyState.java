package server.state;

import server.ChineseCheckerServer;
import server.handler.AddPlayerHandler;
import server.handler.Handler;
import server.handler.StartHandler;

public class LobbyState extends State {

    public LobbyState(ChineseCheckerServer con){
        super(con);
    }

    @Override
    public Handler getHandler() {
        Handler first = new AddPlayerHandler(context);
        first.setNext(new StartHandler(context));

        return first;
    }

    @Override
    public void onStateInit() {

    }
}
