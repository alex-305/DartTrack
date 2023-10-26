import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class MultiplierButton extends JButton implements ActionListener {
    //Data
    static boolean numPicked;
    static boolean multPicked;
    //Constructor
    MultiplierButton() {
        numPicked = false;
        multPicked = false;
    }

    //Setter
    void setNum(boolean NumberHasBeenPicked) { numPicked = NumberHasBeenPicked; }

    //Action Listener
    public void actionPerformed(ActionEvent e) {

    }

}