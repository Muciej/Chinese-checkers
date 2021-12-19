package server.handler;

import server.ChineseCheckerServer;

public abstract class BaseHandler implements Handler{
    Handler next;
    ChineseCheckerServer manager;
}
