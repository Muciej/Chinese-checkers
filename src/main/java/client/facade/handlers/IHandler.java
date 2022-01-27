package client.facade.handlers;

import server.state.IllegalCommandException;

/**
 * Interfejs handlera komend
 */
public interface IHandler {
    /**
     * Funkcja ustawiająca następny handler w łańcuch
     * @param h - handler, który ma być wywołany jako osatatni
     */
    void setNext(IHandler h);

    /**
     * Funckja odpowiadająca za sprawdzenie, czy dana
     * komenda powinna zostać obsłużona i obslużenie jej, bądź przekazanie jej dalej
     * @param command - komenda do obsłużenia
     * @throws IllegalCommandException - wyrzucany, jeśli komenda jest nieprawidłowo sformatowana
     */
    void handle(String command) throws IllegalCommandException;
}
