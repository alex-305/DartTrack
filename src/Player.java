public class Player {

    private int score;
    private boolean win;
    private boolean numPicked;
    
    //Constructor
    Player() {
        score = 501;
        win = false;
    }

    //Getters
    public int getScore() { return score; }
    public boolean hasWon() { return win; }
    //Setters
    public void setPicked(boolean hasPicked) { numPicked = hasPicked; }
    public boolean setScore(int score ) {
        boolean goodScore = true;
        if (score == 0) {
            win = true;
            this.score = score;
        } else if(score > 0 && score != 1) {
            this.score = score;
        } else {
            goodScore = false;
        }
        return goodScore;
    }
    public void win(boolean winStatus) { win = winStatus; }

    public boolean addPoints(int points) {
        boolean valid = false;
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


}