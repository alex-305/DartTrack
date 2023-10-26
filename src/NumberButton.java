//Java Swing imports
import javax.swing.JButton;
import javax.swing.JFrame;
//Formatting imports
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

//Action Listener imports
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NumberButton extends JButton implements ActionListener {
    
    //Data
    int value;
    private DartWindow dartWindow;
    static boolean pressed;
    static boolean pOneTurn;
    static int dartCount;

    //Setter
    void resetPressed() { pressed = false; }

    NumberButton(int valueOfNumber, DartWindow dartWindow) {
        dartCount = 0;
        pressed = false;
        pOneTurn = true;
        this.dartWindow = dartWindow;
        setFont(new Font("Calibri", Font.BOLD, 100));
        setFocusPainted(false);
        setPreferredSize(new Dimension(100, 100));
        setText(String.valueOf(valueOfNumber));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!pressed) {
            setBackground(new Color(246, 185, 59));
            pressed = true;
            dartCount++;
            if (pOneTurn) {
                dartWindow.PlayerOne.addPoints(Integer.parseInt(this.getText()));
            } else {
                dartWindow.PlayerTwo.addPoints(Integer.parseInt(this.getText()));
            }
            pOneTurn = !pOneTurn;
        }        
        
    }

    public void passPlayerScore(Player player, int value) {
        player.addPoints(value);        
    }

}