package server.handler;

import server.ChineseCheckerServer;

public abstract class BaseHandler implements IHandler {

    IHandler next = null;
    ChineseCheckerServer manager;

    public BaseHandler(ChineseCheckerServer man){
        manager = man;
    }
}
