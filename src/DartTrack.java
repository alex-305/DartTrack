public class DartTrack {
    //Singleton
    private static DartTrack dartTrack;
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
    private int multValue;
    private int startScore;
    private boolean playerBust[];

    //GameMode
    private int checkinMode;
    private int checkoutMode;

    //Getters
    public int getMultValue() { return multValue; }
    public int getValueToMult() { return valueToMult; }
    public int getStartScore() { return startScore; }
    public int getTurn() { return turn; }
    public int getDartCount() { return dartCount; }
    public int getPlayerScore(int playerNumber) { return playerScores[playerNumber]; }
    public boolean getNumPicked() { return numPicked; }
    public int getPlayerCount() { return playerCount; }
    public int getWinner() { return winner; }
    public int getCheckin() { return checkinMode; }
    public int getCheckout() { return checkoutMode; }
    public int getBust() { //return which player has a bust
        int b = -1;
        for(int i = 0; i < playerCount; i++) {
            if(playerBust[i]==true) {
                b=i;
            }
        }
        if(b > -1) {
            playerBust[b] = false;
        }
        return b;
    }
    public int getPreviousTurn() {
        int prevTurn = turn;
        if(dartCount==0) {
            prevTurn = turn == 0 ? playerCount-1 : turn-1;
        }
        return prevTurn;
    }
    
    //next turn
    public void nextTurn() { turn = turn == playerCount - 1 ? 0 : turn+1; dartCount = 0; }

    //Setters
    public void setValueToMult(int valueToMult) { this.valueToMult = valueToMult; }
    public void setNumPicked(boolean numPicked) { this.numPicked = numPicked; }
    public void setMode(int checkinMode, int checkoutMode) {
        this.checkinMode = checkinMode;
        this.checkoutMode = checkoutMode;
    }
    public void setStartScore(int score) { startScore = score; }
    public void setPlayerCount(int playerCount) { 
        this.playerCount = playerCount; 
        playerScores = new int [playerCount];
        for (int i = 0; i < playerCount; i++) {
            playerScores[i] = startScore;
            playerBust[i] = false;
        }
    }

    //Single in Single out
    private void setScoreOpenInOpenOut(int score, int mult) {
        if (playerScores[turn] - (score*mult) == 0) {//win
            winner = turn;
            playerScores[turn] = 0;
        } else if(playerScores[turn] - (score*mult) > 0) {
            playerScores[turn] -= (score*mult);
            dartCount++;
        } else { //bust
            playerBust[turn] = true;
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
        } else {//bust
            playerBust[turn] = true;
        }
    }
    //Double in Double Out
    private void setScore2In2Out(int score, int mult) {
        if(playerScores[turn] == startScore) {
            if (mult == 2 && score != 25) {
                playerScores[turn] -= (score*mult);
                dartCount++;
            } else {
                dartCount++;//bust from failed check in
            }
        } else if (mult == 2 && score != 25 && playerScores[turn] - (score * mult) == 0) {//win
            winner = turn;
            playerScores[turn] = 0;
        } else if(playerScores[turn] - (score*mult) > 1) {//score greater than 1
            playerScores[turn] -= (score*mult);
            dartCount++;
        } else { //bust
            playerBust[turn] = true;
        }
    }
    
    private void setScore2InOpenOut(int score, int mult) {
        if (playerScores[turn] == startScore) {
            if (mult == 2 && score != 25) {
                playerScores[turn] -= (score*mult);
                dartCount++;
            } else {
                dartCount++;
            } 
        } else if(playerScores[turn] - (score*mult) > 0) {
            playerScores[turn] -= (score*mult);
            dartCount++;
        } else {//bust
            playerBust[turn] = true;
        }
    }

    public void addPoints(int points, int mult) {
        multValue = mult;
            switch(checkinMode) {
                case 1:
                    switch(checkoutMode) {
                        case 1: setScoreOpenInOpenOut(points, mult);
                            break;
                        case 2: setScoreOpenIn2Out(points, mult);
                            break;
                    }
                    break;
                case 2:
                    switch(checkoutMode) {
                        case 1: setScore2InOpenOut(points, mult);
                            break;
                        case 2: setScore2In2Out(points, mult);
                            break;
                    }
                    break;
        }
        if (dartCount == 3 || playerBust[turn]) { nextTurn(); }
    }
    
    //Private constructor
    private DartTrack(int playerCount, int startScore) {
        //Setting up player scores
        this.playerCount = playerCount;
        this.startScore = startScore;
        playerBust = new boolean [playerCount];
        valueToMult = -1;
        turn = 0;
        dartCount = 0;  
        winner = -1;
    }
    //Reset game with same player count and start score
    //ie: Play again
    public void softResetGame() {
        for (int i = 0; i < playerCount; i++) {
            playerScores[i] = startScore;
            playerBust[i] = false;
        }
        winner = -1;
        dartCount = 0;
        turn = 0;
        valueToMult = -1;
        multValue = -1;
    }
}