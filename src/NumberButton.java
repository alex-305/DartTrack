//Java Swing imports
import javax.swing.JButton;

//Formatting imports
import java.awt.Font;

//Action Listener imports
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NumberButton extends JButton implements ActionListener {
    
    //Data
    Boolean pressed;
    int value;

    NumberButton() {
        pressed = false;
        setFont(new Font("Calibri", Font.BOLD, 100));
        setFocusPainted(false);

    }
    NumberButton(int valueOfNumber) {

    }


    public void actionPerformed(ActionEvent e) {
        
        
    }
}