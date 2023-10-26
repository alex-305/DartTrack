public class Driver {

    static Player playerOne, playerTwo;
    public static void main() {
        DartWindow window = new DartWindow(playerOne, playerTwo);
        window.start();
    }

}