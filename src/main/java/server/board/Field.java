package server.board;

public class Field {
    private String occupant;
    private int startFieldNo;
    Field(int startFieldNo){
        this.startFieldNo = startFieldNo;
        occupant = null;
    }

    public void setOccupant(String occupant) {
        this.occupant = occupant;
    }

    public String getOccupant() {
        return occupant;
    }

    public int getStartFieldNo() {
        return startFieldNo;
    }
}
