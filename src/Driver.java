import javax.swing.SwingUtilities;

public class Driver {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DartWindow dartWindow = new DartWindow();
            dartWindow.start();
            dartWindow.play();

        });
    }
}