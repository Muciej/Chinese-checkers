package server.state;

import server.ChineseCheckerServer;
import server.handler.Handler;

public class LobbyState extends State {

    public LobbyState(ChineseCheckerServer con){
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
