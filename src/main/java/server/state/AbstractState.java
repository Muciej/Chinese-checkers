package server.state;

import server.Player;

public abstract class AbstractState {
    AbstractState next;
    Player moving;

    public abstract void do_comm(String command);
}
