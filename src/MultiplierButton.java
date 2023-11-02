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
    private static DartTrack dartTrack = DartTrack.getInstance();
    private static DartWindow dartWindow = DartWindow.getInstance();
    private static boolean win;
    int multValue;

    //Constructor
    MultiplierButton(int valueOfNumber) {
        win = false;
        multValue = valueOfNumber;
        addActionListener(this);
        setFont(new Font("Calibri", Font.BOLD, 100));
        setFocusPainted(false);
        setBackground(dartWindow.getGold());
        setPreferredSize(new Dimension(100, 100));
        setText(String.valueOf(valueOfNumber) + 'x');
    }

    //Action Listener
    @Override
    public void actionPerformed(ActionEvent e) {
        if(dartTrack.getValueToMult() != -1) {
            if (dartTrack.getValueToMult() < 25  || (dartTrack.getValueToMult() == 25 && multValue != 3)) { //Check to make sure they cannot use 3x when 25 is selected
                //Adding the points
                dartTrack.addPoints(dartTrack.getValueToMult(), multValue);
                //Updating the log
                dartWindow.updateLog(multValue);
                dartTrack.setValueToMult(-1);
            }

            if(dartTrack.getValueToMult() != 25 && dartTrack.getWinner() != 1) {
                dartWindow.enable3x();
            }
        }
        if(dartTrack.getWinner() != -1 && !win) {
            dartWindow.gameOverState();
            win = true;
        }
    }
}