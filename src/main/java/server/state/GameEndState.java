package server.state;

import server.ChineseCheckerServer;
import server.handler.IHandler;
import server.handler.QuitHandler;

public class GameEndState extends State {

    public GameEndState(ChineseCheckerServer con){
        super(con);
    }

    @Override
    public IHandler getHandler() {
        IHandler first = new QuitHandler(context);
        return first;
    }

    @Override
    public void onStateInit() {

    }
}
