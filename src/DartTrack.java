public class DartTrack {
    //Singleton
    private static DartTrack dartTrack;

    static DartTrack getInstance() {
        if (dartTrack == null) {
            dartTrack = new DartTrack(2);
        }
        return dartTrack;
    }

    //Data
    private int playerScores[];
    private int winner;
    private int playerCount;
    private int dartCount;
    private boolean numPicked;
    private int turnCount;  
    private int valueToMult;  

    //Getters
    public int getValueToMult() { return valueToMult; }
    public int getTurn() { return turnCount; }
    public int getDartCount() { return dartCount; }
    public int getPlayerScore(int playerNumber) { return playerScores[playerNumber]; }
    public boolean getNumPicked() { return numPicked; }
    public int getPlayerCount() { return playerCount; }
    public int getWinner() { return winner; }
    
    //next turn
    private void nextTurn() { turnCount = turnCount == playerCount - 1 ? 0 : turnCount+1; dartCount = 0; }

    //Setters
    public void setValueToMult(int valueToMult) { this.valueToMult = valueToMult; }
    public void flipNumPicked() { numPicked = !numPicked; }
    //Set score and error check
    private boolean setScore(int score) {
        boolean win = false;
        if (playerScores[turnCount] - score == 0) {
            win = true;
            winner = turnCount;
            playerScores[turnCount] -= score;
        } else if(playerScores[turnCount] - score > 0 && playerScores[turnCount] - score != 1) {
            playerScores[turnCount] -= score;
        }
        dartCount++;
        return win;
    }

    public boolean addPoints(int points) {
        boolean valid = false;
        if(dartCount == 3) {
            nextTurn();
        }
        try {
            if (points > 60 || points < 0) {
                throw new ImpossibleScoreException(points);
            }
            valid = setScore(points);
        } catch (ImpossibleScoreException ise) {
            System.out.printf(ise.getMessage());
        }
        return valid;
    }

    
    //Private constructor
    private DartTrack(int playerCount) {
        //Setting up player scores
        this.playerCount = playerCount;
        playerScores = new int [playerCount];
        for (int i = 0; i < playerCount; i++) {
            playerScores[i] = 501;
        }
        numPicked = false;
        turnCount = 0;
        dartCount = 0;        
    }
}