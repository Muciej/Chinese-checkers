package server.handler;

import server.ChineseCheckerServer;

/**
 * Abstrakcyjna klasa podstawowego handlera, po której dziedziczą inne handlery
 */
public abstract class BaseHandler implements IHandler {

    IHandler next = null;
    ChineseCheckerServer manager;

    public BaseHandler(ChineseCheckerServer man){
        manager = man;
    }
}
