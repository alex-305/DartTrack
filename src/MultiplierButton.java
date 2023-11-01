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
    int multValue;

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
        if(dartTrack.getValueToMult() != -1) {
            if (dartTrack.getValueToMult() > -1  || (dartTrack.getValueToMult() == 25 && multValue != 3)) { //Check to make sure they cannot use 3x when 25 is selected
                //Adding points to player score
                dartTrack.addPoints(dartTrack.getValueToMult(), multValue);
                //dartWindow.
                //Updating the Label with player score
                dartWindow.updatePlayScore("Player " + (dartTrack.getTurn()+1) + ": " + dartTrack.getPlayerScore(dartTrack.getTurn()));
                //Next turn
                if(dartTrack.getDartCount() == 3 && dartTrack.getWinner() == -1) { dartTrack.nextTurn(); }
                else if (dartTrack.getDartCount() == -1) { dartWindow.updatePlayScore("Player " + (dartTrack.getTurn()+1) + " bust"); dartTrack.nextTurn();}
                //Changing colors in dart window
                dartWindow.updateActivePlayer();
                dartTrack.setValueToMult(-1);
                dartWindow.resetNumColors();
            } 
            if(dartTrack.getValueToMult() != 25) {
                dartWindow.enable3x();
            }
        }
        if(dartTrack.getWinner() != -1) {
            dartWindow.showPlayerScores();
            dartWindow.updatePlayScore("Player " + (dartTrack.getWinner()+1) + " wins!");
            
        }
    }

}