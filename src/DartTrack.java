public class DartTrack {
    //Singleton
    private static DartTrack dartTrack;

    public void setMode(int checkinMode, int checkoutMode) {
        this.checkinMode = checkinMode;
        this.checkoutMode = checkoutMode;
    }

    public static DartTrack getInstance() {
        if (dartTrack == null) {
            dartTrack = new DartTrack(2, 501);
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
    private boolean badScore;

    //GameMode
    private int checkinMode;
    private int checkoutMode;

    //Getters
    public int getValueToMult() { return valueToMult; }
    public int getTurn() { return turnCount; }
    public int getDartCount() { return dartCount; }
    public int getPlayerScore(int playerNumber) { return playerScores[playerNumber]; }
    public boolean getNumPicked() { return numPicked; }
    public int getPlayerCount() { return playerCount; }
    public int getWinner() { return winner; }
    public boolean getBadScore() { return badScore; }
    
    //next turn
    public void nextTurn() { turnCount = turnCount == playerCount - 1 ? 0 : turnCount+1; dartCount = 0; }

    //Setters
    public void setValueToMult(int valueToMult) { this.valueToMult = valueToMult; }
    public void flipNumPicked() { numPicked = !numPicked; }
    //Set score and error check
    private void setScore1In1Out(int score) {
        if (playerScores[turnCount] - score == 0) {
            winner = turnCount;
            badScore = false;
            playerScores[turnCount] = 0;
        } else if(playerScores[turnCount] - score > 0) {
            playerScores[turnCount] -= score;
            badScore = false;
            dartCount++;
        } else {
            badScore = true;
            dartCount++;
        }
    }

    public void addPoints(int points) {
        try {
            if (points > 60 || points < 0) {
                badScore = true;
                throw new ImpossibleScoreException(points);
            }
            switch(checkinMode) {
                case 1:
                    switch(checkoutMode) {
                        case 1:
                            setScore1In1Out(points);
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 2:
                    switch(checkoutMode) {
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 3:
                    switch(checkoutMode) {
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                    }
                    break;
            }
        } catch (ImpossibleScoreException ise) {
            System.out.printf(ise.getMessage());
        }
    }
    
    //Private constructor
    private DartTrack(int playerCount, int startScore) {
        //Setting up player scores
        this.playerCount = playerCount;
        playerScores = new int [playerCount];
        for (int i = 0; i < playerCount; i++) {
            playerScores[i] = startScore;
        }
        numPicked = false;
        badScore = false;
        turnCount = 0;
        dartCount = 0;  
        winner = 0;   
    }
}