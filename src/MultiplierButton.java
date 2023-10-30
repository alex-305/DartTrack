//JButton stuff
import javax.swing.JButton;
//Action Listener
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//Formatting
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;

public class MultiplierButton extends JButton implements ActionListener {
    //Data
    private static DartTrack dartTrack = DartTrack.getInstance();
    private static DartWindow dartWindow = DartWindow.getInstance();
    static int valueToMult;
    int multValue;

    //Setter
    void setNum(int value) { valueToMult = value; }
    //Getter

    //Constructor
    MultiplierButton(int valueOfNumber) {
        multValue = valueOfNumber;
        addActionListener(this);
        setFont(new Font("Calibri", Font.BOLD, 100));
        setFocusPainted(false);
        setBackground(new Color(246, 185, 59));
        setPreferredSize(new Dimension(100, 100));
        setText(String.valueOf(valueOfNumber) + 'x');
    }

    //Action Listener
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean win = false;
        if(dartTrack.getNumPicked()) {
            valueToMult = dartTrack.getValueToMult();
            win = dartTrack.addPoints(valueToMult * multValue);
            dartTrack.flipNumPicked();
            dartWindow.updatePlayScore("Player " + (dartTrack.getTurn()+1) + ": " + dartTrack.getPlayerScore(dartTrack.getTurn()));
            dartWindow.resetNumColors();
        }
        if(win) {
            dartWindow.updatePlayScore("Player " + (dartTrack.getWinner()+1) + " wins!");
        }
    }

}