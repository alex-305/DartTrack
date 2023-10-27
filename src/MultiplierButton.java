//JButton stuff
import javax.swing.JButton;
//Action Listener
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//Formatting
import java.awt.Font;
import java.awt.Dimension;
public class MultiplierButton extends JButton implements ActionListener {
    //Data
    private static DartWindow dartWindow;
    static boolean numPicked;
    static boolean multPicked;
    static int valueToMult;
    int multValue;

    //Setter
    void setNumPicked(boolean NumberHasBeenPicked) { numPicked = NumberHasBeenPicked; }
    void setNum(int value) { valueToMult = value; }
    public static void initializeWindow(DartWindow dw) { dartWindow = dw; }
    //Getter

    //Constructor
    MultiplierButton(int valueOfNumber, DartWindow dartWindow) {
        addActionListener(this);
        multPicked = false;
        setFont(new Font("Calibri", Font.BOLD, 100));
        setFocusPainted(false);
        setPreferredSize(new Dimension(100, 100));
        setText(String.valueOf(valueOfNumber) + 'x');
    }

    //Action Listener
    @Override
    public void actionPerformed(ActionEvent e) {
        if(numPicked && !multPicked) {
            if (dartWindow.dartTrack.isP1Turn()) {
                dartWindow.dartTrack.PlayerOne.addPoints(valueToMult * multValue);
                dartWindow.dartTrack.flipNumPicked();
            } else {
                dartWindow.dartTrack.PlayerTwo.addPoints(valueToMult * multValue);
            }
            if (dartWindow.dartTrack.returnDartCount() == 3) {
                dartWindow.dartTrack.resetDarts();
                dartWindow.dartTrack.nextTurn();
            } else {
                dartWindow.dartTrack.incrementDart();
            }
        }
    }

}