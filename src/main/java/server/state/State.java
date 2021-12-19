package server.state;

import server.ChineseCheckerServer;
import server.handler.Handler;

public abstract class State {
    ChineseCheckerServer context;

    public State(ChineseCheckerServer con){
        context = con;
        onStateInit();
    }

    public abstract Handler getHandler();
    public abstract void onStateInit();
}
