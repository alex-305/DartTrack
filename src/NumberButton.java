//Java Swing imports
import javax.swing.JButton;
//Formatting imports
import java.awt.Dimension;
import java.awt.Font;

//Action Listener imports
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NumberButton extends JButton implements ActionListener {
    
    //Data
    int value;
    private static DartTrack dartTrack = DartTrack.getInstance();
    private static DartWindow dartWindow = DartWindow.getInstance();
    //Setter
    void setValue(int valueOfButton) { value = valueOfButton; }
    
    NumberButton(int valueOfNumber) {
        addActionListener(this);
        setForeground(dartWindow.getBlack());
        setBackground(dartWindow.getWhite());
        setFont(new Font("Calibri", Font.BOLD, 100));
        setFocusPainted(false);
        setPreferredSize(new Dimension(100, 100));
        value = valueOfNumber;
        setText(String.valueOf(valueOfNumber));
    }

    //Action Listener
    @Override
    public void actionPerformed(ActionEvent e) {
        if (dartTrack.getWinner() == 0) {
            dartWindow.resetNumColors();
            setBackground(dartWindow.getGreen());
            dartTrack.flipNumPicked();
            dartTrack.setValueToMult(value);
        }
    }
}