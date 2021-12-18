package server.state;

import server.ChineseCheckerServer;

public class PlayerMove extends AbstractState{
    PlayerMove(ChineseCheckerServer man, AbstractState next){
        super(man, next);
    }

    public void do_comm(String command) throws IllegalCommandException {

    }
}
