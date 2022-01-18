package server.state;

import server.ChineseCheckerServer;
import server.handler.CheckHandler;
import server.handler.IHandler;
import server.handler.MoveHandler;
import server.handler.QuitHandler;

public class GameStartedState extends State {

    public GameStartedState(ChineseCheckerServer con){
        super(con);
    }

    @Override
    public IHandler getHandler() {
        IHandler first = new MoveHandler(context);
        IHandler second = new CheckHandler(context);
        IHandler third = new QuitHandler(context);
        first.setNext(second);
        second.setNext(third);
        return first;
    }

    @Override
    public void onStateInit() {

    }
}
