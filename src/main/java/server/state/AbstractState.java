package server.state;

import server.ChineseCheckerServer;
import server.Player;

public abstract class AbstractState {
    AbstractState next;
    Player moving;
    ChineseCheckerServer manager;

    public AbstractState(ChineseCheckerServer man, AbstractState next){
        this.next = next;
        this.manager = man;
    }

    public abstract void do_comm(String command) throws IllegalCommandException;
}
