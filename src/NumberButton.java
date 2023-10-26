//Java Swing imports
import javax.swing.JButton;

import java.awt.Dimension;
//Formatting imports
import java.awt.Font;

//Action Listener imports
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NumberButton extends JButton implements ActionListener {
    
    //Data
    int value;
    static boolean pressed;

    NumberButton(int valueOfNumber) {
        pressed = false;
        setFont(new Font("Calibri", Font.BOLD, 100));
        setFocusPainted(false);
        setPreferredSize(new Dimension(100, 100));
        setText(String.valueOf(valueOfNumber));
    }


    public void actionPerformed(ActionEvent e) {
        if(!pressed) {

        }        
        
    }
}