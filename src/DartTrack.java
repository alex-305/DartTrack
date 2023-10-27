public class DartTrack {
    //Data
    public Player PlayerOne, PlayerTwo;
    int dartCount;
    private boolean oneTurn;
    boolean numPicked;
    

    //Getter
    public boolean isP1Turn() { return oneTurn; }
    public int returnDartCount() { return dartCount; }
    public boolean isNumPicked() { return numPicked; }
    //Setter
    void flipNumPicked() { numPicked = !numPicked; }
    public void nextTurn() { oneTurn = !oneTurn; }
    public void incrementDart() { dartCount++; }
    public void resetDarts() { dartCount = 0; }
    
    DartTrack() {
        oneTurn = true;
        numPicked = false;
        dartCount = 0;        
    }
}