import javax.swing.SwingUtilities;

public class Driver {
    static DartWindow dartWindow = DartWindow.getInstance();
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            dartWindow.start();
        });
    }
}