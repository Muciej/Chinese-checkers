package server.state;

import server.ChineseCheckerServer;
import server.handler.IHandler;

public abstract class State {
    ChineseCheckerServer context;

    public State(ChineseCheckerServer con){
        context = con;
        onStateInit();
    }

    public abstract IHandler getHandler();
    public abstract void onStateInit();
}
