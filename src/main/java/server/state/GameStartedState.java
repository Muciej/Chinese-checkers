package server.state;

import server.ChineseCheckerServer;
import server.handler.Handler;

public class GameStartedState extends State{

    public GameStartedState(ChineseCheckerServer con){
        super(con);
    }

    @Override
    public Handler getHandler() {
        return null;
    }

    @Override
    public void onStateInit() {

    }
}
