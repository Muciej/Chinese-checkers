package server.state;

import server.ChineseCheckerServer;
import server.handler.IHandler;

public class GameStartedState extends State {

    public GameStartedState(ChineseCheckerServer con){
        super(con);
    }

    @Override
    public IHandler getHandler() {
        return null;
    }

    @Override
    public void onStateInit() {

    }
}
