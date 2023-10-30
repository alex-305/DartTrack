public class DartTrack {
    //Singleton
    private static DartTrack dartTrack;

    public void setMode(int checkinMode, int checkoutMode) {
        this.checkinMode = checkinMode;
        this.checkoutMode = checkoutMode;
    }

    public void setStartScore(int score) { startScore = score; }

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
    private int turn;  
    private int valueToMult;
    private boolean badScore;
    private int startScore;

    //GameMode
    private int checkinMode;
    private int checkoutMode;

    //Getters
    public int getValueToMult() { return valueToMult; }
    public int getTurn() { return turn; }
    public int getDartCount() { return dartCount; }
    public int getPlayerScore(int playerNumber) { return playerScores[playerNumber]; }
    public boolean getNumPicked() { return numPicked; }
    public int getPlayerCount() { return playerCount; }
    public int getWinner() { return winner; }
    public boolean getBadScore() { return badScore; }
    
    //next turn
    public void nextTurn() { turn = turn == playerCount - 1 ? 0 : turn+1; dartCount = 0; }

    //Setters
    public void setValueToMult(int valueToMult) { this.valueToMult = valueToMult; }
    public void flipNumPicked() { numPicked = !numPicked; }

    //Single in Single out
    private void setScoreOpenInOpenOut(int score, int mult) {
        badScore = false;
        if (playerScores[turn] - (score*mult) == 0) {//win
            winner = turn;
            playerScores[turn] = 0;
        } else if(playerScores[turn] - (score*mult) > 0) {
            playerScores[turn] -= (score*mult);
            dartCount++;
        } else {
            dartCount = -1; //dartcount = -1 is a bust
        }
    }
    //Single in Double Out
    private void setScoreOpenIn2Out(int score, int mult) {
        if (mult == 2 && score != 25 && playerScores[turn] - (score * mult) == 0) {//win
            winner = turn;
            playerScores[turn] = 0;
        } else if (playerScores[turn] - (score*mult) > 1) {
            playerScores[turn] -= (score*mult);
            dartCount++;
        } else {
            dartCount = -1;//bust
        }
    }
    //Double in Double Out
    private void setScore2In2Out(int score, int mult) {
        if(playerScores[turn] == startScore) {
            if (mult == 2 && score != 25) {
                playerScores[turn] -= (score*mult);
                dartCount++;
            } else {
                dartCount = -1;//bust from failed check in
            }
        } else if (mult == 2 && score != 25 && playerScores[turn] - (score * mult) == 0) {//win
            winner = turn;
            playerScores[turn] = 0;
        } else if(playerScores[turn] - (score*mult) > 1) {
            playerScores[turn] -= (score*mult);
            dartCount++;
        } else {
            dartCount = -1; //bust
        }
    }

    public void addPoints(int points, int mult) {
        try {
            if (points > 60 || points < 0) {
                badScore = true;
                throw new ImpossibleScoreException(points);
            }
            switch(checkinMode) {
                case 1:
                    switch(checkoutMode) {
                        case 1:
                            setScoreOpenInOpenOut(points, mult);
                            break;
                        case 2:
                            setScoreOpenIn2Out(points, mult);
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
                            setScore2In2Out(points, mult);
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
        this.startScore = startScore;
        playerScores = new int [playerCount];
        for (int i = 0; i < playerCount; i++) {
            playerScores[i] = startScore;
        }
        numPicked = false;
        badScore = false;
        turn = 0;
        dartCount = 0;  
        winner = -1;
    }
}