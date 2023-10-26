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
    static boolean numPicked;
    static boolean multPicked;
    static int valueToMult;
    static int result;
    int multValue;
    //Constructor
    MultiplierButton(int valueOfNumber) {
        numPicked = false;
        multPicked = false;
        setFont(new Font("Calibri", Font.BOLD, 100));
        setFocusPainted(false);
        setPreferredSize(new Dimension(100, 100));
        setText(String.valueOf(valueOfNumber) + 'x');
    }
    //Getter
    int getResult() { return result; }
    //Setter
    void setNumPicked(boolean NumberHasBeenPicked) { numPicked = NumberHasBeenPicked; }
    void setNum(int value) { valueToMult = value; }
    //Action Listener
    public void actionPerformed(ActionEvent e) {
        if(!numPicked && !multPicked) {
            result = valueToMult * multValue;
        }
    }

}