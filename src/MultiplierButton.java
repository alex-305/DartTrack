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
    static int valueToMult;
    int multValue;

    //Setter
    void setNum(int value) { valueToMult = value; }
    //Constructor
    MultiplierButton(int valueOfNumber) {
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
        if(dartTrack.getNumPicked()) {
            valueToMult = dartTrack.getValueToMult();
            dartTrack.addPoints(valueToMult * multValue);
            dartWindow.updatePlayScore("Player " + (dartTrack.getTurn()+1) + ": " + dartTrack.getPlayerScore(dartTrack.getTurn()));
            if(dartTrack.getDartCount() == 3) { dartTrack.nextTurn(); }
            dartWindow.updateActivePlayer();
            dartTrack.flipNumPicked();
            dartWindow.resetNumColors();
        }
        if(dartTrack.getWinner() != 0) {
            dartWindow.updatePlayScore("Player " + (dartTrack.getWinner()+1) + " wins!");
        }
    }

}