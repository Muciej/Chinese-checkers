package server.state;

public class IllegalCommandException extends Exception{
    public IllegalCommandException(String errorMessage){
        super(errorMessage);
    }
}
