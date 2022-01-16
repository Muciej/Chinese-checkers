package server.state;

import server.ChineseCheckerServer;
import server.handler.IHandler;

public class GameEndState extends State {

    public GameEndState(ChineseCheckerServer con){
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
