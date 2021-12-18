package server.state;

import server.ChineseCheckerServer;

public class GameEnd extends AbstractState{
    GameEnd(ChineseCheckerServer man, AbstractState next){
        super(man, next);
    }
    public void do_comm(String command) throws IllegalCommandException {

    }
}
