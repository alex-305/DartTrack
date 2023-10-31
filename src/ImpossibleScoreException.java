public class ImpossibleScoreException extends Throwable {

    public ImpossibleScoreException(int num, int mult) {
        super(num*mult + " is an invalid darts score.\n");
    }


}