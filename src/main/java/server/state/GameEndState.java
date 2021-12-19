package server.state;

import server.ChineseCheckerServer;
import server.handler.Handler;

public class GameEndState extends State{

    public GameEndState(ChineseCheckerServer con){
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
