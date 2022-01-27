package server;

import server.connection.IServer;
import server.connection.Server;
import server.handler.BaseHandler;
import server.board.Board;
import server.handler.IHandler;
import server.state.IllegalCommandException;
import server.state.LobbyState;
import server.state.State;

import java.util.ArrayList;

/**
 * Główna klasa serwera
 * Pełni rolę podobną do fasady
 * Zarządza pozostałymi klasami i udostępnia interfejs do komunikacji z serwerem
 */
public class ChineseCheckerServer {

    IServer server;
    Board board;
    ArrayList<Player> players;
    ArrayList<Player> playOrder;
    ArrayList<String> commands;
    Player currentPlayer;
    int intCurrentPlayer;
    State currentState;
    IHandler commandHandler;

    ChineseCheckerServer(){
        commands = new ArrayList<>();
        players = new ArrayList<>();
        playOrder = new ArrayList<>();
        currentPlayer = null;
        board = new Board(this, "Chinese-checkers.bd");
        currentState = new LobbyState(this);
        commandHandler =  currentState.getHandler();
        server = new Server(this, 60000);
    }

    /**
     * Funkcja inicjująca przetwarzanie odebranej komendy
     * Wysyła ją do łańcucha handlerów
     */
    private synchronized void executeCommands() {
        try {
            while(commands.size() > 0){
                for(String c: commands){
                    commandHandler.handle(c);
                }
                commands.clear();
            }
        } catch (IllegalCommandException e) {
            sendCommand("ERROR " + e.getMessage());
        }
    }

    /**
     * Funkcja pozwalająca dodać komendę do wykonania
     * @param command - komenda do obsłużenia przez serwer
     */
    public synchronized void addCommandToExecute(String command){
        commands.add(command);
        executeCommands();
    }

    /**
     * Funkcja służąca do wysyłania komend do serwera
     * @param command - komenda do wysłania
     */
    public void sendCommand(String command){
        server.sendCommand(command);
    }

    /**
     * Funkcja pozwalająca ustawić obecny stan gry
     * @param newState - nowy stan do ustawienia
     */
    public void setCurrentState(State newState){
        currentState = newState;
        commandHandler = currentState.getHandler();
    }

    public int getPlayerCount(){
        return players.size();
    }

    public Player getPlayer(String name){
        Player temp = null;
        for(Player p: players){
            if(p.getName().equals(name)){
                temp = p;
            }
        }
        return temp;
    }

    public Board getBoard() {
        return board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void clearPlayOrder(){
        playOrder = new ArrayList<>();
    }

    public void addToPlayorder(Player p){
        playOrder.add(p);
    }

    /**
     * Funkcja pozwalająca zmienić obecnie ruszającego się gracza
     * @param currentPlayer
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public ArrayList<Player> getPlayOrder() {
        return playOrder;
    }

    /**
     * Funkcja odpowiedzialna za zmianę aktywnego gracza
     * na kolejnego gracza z listy
     */
    public void nextPlayer(){
        if(intCurrentPlayer == playOrder.size()-1){
            intCurrentPlayer = 0;
            currentPlayer = playOrder.get(0);
        } else {
            intCurrentPlayer++;
            currentPlayer = playOrder.get(intCurrentPlayer);
        }
    }
}
