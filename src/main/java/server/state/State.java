package server.state;

import server.ChineseCheckerServer;
import server.Player;
import server.handler.Handler;

public abstract class State {
    State next;
    ChineseCheckerServer context;

    public abstract Handler getHandler();
    public abstract void onStateInit();
}
