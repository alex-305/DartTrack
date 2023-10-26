public class Driver {

    static Player playerOne, playerTwo;
    public static void main(String[] args) {
        DartWindow window = new DartWindow(playerOne, playerTwo);
        window.start();
    }

}