package server.handler;

import server.state.IllegalCommandException;

/**
 * Interfejs handlera komend
 */
public interface IHandler {
    /**
     * Ustawia następny handler
     * @param h - następny handler do ustawienia
     */
    void setNext(IHandler h);

    /**
     * Funkcja obsługująca komendę, jeśli jest odpowiednia dla tego handlera. W przeciwnym
     * razie komenda jest przekazywana dalej.
     * @param command
     * @throws IllegalCommandException
     */
    void handle(String command) throws IllegalCommandException;
}
