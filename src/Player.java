public class Player {

    static DartTrack dartTrack;
    private int score;
    
    //Constructor
    Player() {
        dartTrack = DartTrack.getInstance();
        score = 501;
    }

    //Getters
    public int getScore() { return score; }
    private boolean setScore(int score ) {
        boolean win = false;
        if (score == 0) {
            win = true;
            this.score = score;
        } else if(score > 0 && score != 1) {
            this.score = score;
        }
            return win;
    }

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