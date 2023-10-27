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
    private static DartWindow dartWindow;
    
    //Setter
    void setValue(int valueOfButton) { value = valueOfButton; }
    public static void initializeWindow(DartWindow dw) { dartWindow = dw; }


    NumberButton(int valueOfNumber, DartWindow dartWindow) {
        addActionListener(this);
        setFont(new Font("Calibri", Font.BOLD, 100));
        setFocusPainted(false);
        setPreferredSize(new Dimension(100, 100));
        value = valueOfNumber;
        setText(String.valueOf(valueOfNumber));
    }

    //Action Listener
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.printf("Listened\n");
        if(!(dartWindow.dartTrack.isNumPicked())) {
            System.out.printf("True\n");
            setBackground(new Color(246, 185, 59));
            dartWindow.dartTrack.flipNumPicked();
            if (dartWindow.dartTrack.isP1Turn()) {
                dartWindow.dartTrack.PlayerOne.addPoints(value);
            } else {
                dartWindow.dartTrack.PlayerTwo.addPoints(value);
            }
            dartWindow.dartTrack.nextTurn();
        }        
        
    }

    public void passPlayerScore(Player player, int value) {
        player.addPoints(value);        
    }

}