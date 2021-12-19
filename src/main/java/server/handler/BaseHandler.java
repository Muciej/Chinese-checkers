package server.handler;

import server.ChineseCheckerServer;

public abstract class BaseHandler implements Handler{

    Handler next = null;
    ChineseCheckerServer manager;

    public BaseHandler(ChineseCheckerServer man){
        manager = man;
    }
}
