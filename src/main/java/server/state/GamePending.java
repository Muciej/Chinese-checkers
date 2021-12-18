package server.state;

import server.ChineseCheckerServer;

public class GamePending extends AbstractState{
    GamePending(ChineseCheckerServer man, AbstractState next){
        super(man, next);
    }

    public void do_comm(String command) throws IllegalCommandException {

    }
}
