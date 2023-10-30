public class ImpossibleScoreException extends Throwable {

    public ImpossibleScoreException(int num) {
        super(num + " is an invalid darts score.\n");
    }


}