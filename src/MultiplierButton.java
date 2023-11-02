//JButton stuff
import javax.swing.JButton;
//Action Listener
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//Formatting
import java.awt.Font;
import java.awt.Dimension;
//Randomizing messages
import java.util.Random;


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
            //Used to randomize messages
            Random messageRandomizer = new Random();
            int randomInt = (messageRandomizer.nextInt(5) + 1 );//Random int from 1-5
            if (dartTrack.getValueToMult() < 25  || (dartTrack.getValueToMult() == 25 && multValue != 3)) { //Check to make sure they cannot use 3x when 25 is selected
                //Adding points to player score
                dartTrack.addPoints(dartTrack.getValueToMult(), multValue);
                //Updating the Label with player score
                //Next turn
                
                if (dartTrack.getDartCount() == -1) {//if bust then next turn
                    dartWindow.updatePlayScore("Player " + (dartTrack.getTurn()+1) + " bust"); 
                    dartWindow.addLogText("\nIt's a bust\n" + randomNextTurn(randomInt) + '\n');

                    dartTrack.nextTurn();
                } else if (dartTrack.getDartCount() == 3 && dartTrack.getWinner() == -1) { //if dartcount =3 then next turn
                    dartWindow.addLogText(randomMessage(randomInt));
                    dartWindow.addLogText('\n' + randomNextTurn(randomInt) + '\n');
                    dartTrack.nextTurn();
                } else { //else valid non end of turn actions
                    dartWindow.updatePlayScore("Player " + (dartTrack.getTurn()+1) + ": " + dartTrack.getPlayerScore(dartTrack.getTurn()));
                    dartWindow.addLogText(randomMessage(randomInt));
                }
                //Changing colors in dart window
                dartWindow.updateActivePlayer();
                dartTrack.setValueToMult(-1);
                dartWindow.resetNumColors();
            } 
            if(dartTrack.getValueToMult() != 25) {
                dartWindow.enable3x();
            }
        }
        if(dartTrack.getWinner() != -1 && !win) {
            dartWindow.showPlayerScores();
            dartWindow.whiteOutMult();
            win = true;
            dartWindow.updatePlayScore("Player " + (dartTrack.getWinner()+1) + " wins!");
            dartWindow.addLogText("\nPlayer " + (dartTrack.getWinner()+1) + " wins!");
            dartWindow.changeNumsGameOver(12);
            dartWindow.changeNumsGameOver(13);        
        }
    }


    private String randomMessage(int randomInt) {
        String msg = "\0";
        switch(multValue) {
            case 1:
                msg = random1x(randomInt);
                break;
            case 2:
                msg = random2x(randomInt);
                break;
            case 3:
                msg = random3x(randomInt);
                break;
        }
        return msg;
    }
    private String random3x(int randomInt) {
        String msg = "\0";
        switch(randomInt) {
            case 1:
                msg = ("Player " + (dartTrack.getTurn()+1) + " hit a whopping 3x " + dartTrack.getValueToMult());
                break;
            case 2:
                msg = ("Player " + (dartTrack.getTurn()+1) + " hit the 3x " + dartTrack.getValueToMult() + '!');
                break;
            case 3:
                msg = "Woah! Player " + (dartTrack.getTurn()+1) + " got a " + dartTrack.getValueToMult() + "x of " + dartTrack.getValueToMult();
                break;
            case 4:
                msg = "Player " + (dartTrack.getTurn()+1) + " shot a 3x " + dartTrack.getValueToMult() + '!';
                break;
            case 5:
                msg = "Player " + (dartTrack.getTurn()+1) + " hit a 3x " + dartTrack.getValueToMult() + '!';
                break;
        }
        return msg;
    }
    
    private String random2x(int randomInt) {
        String msg = "\0";
        switch(randomInt) {
            case 1:
                msg = ("Player " + (dartTrack.getTurn()+1) + " got " + dartTrack.getValueToMult() + " with 2x multiplier");
                break;
            case 2:
                msg = "Player " + (dartTrack.getTurn()+1) + " got a 2x " + dartTrack.getValueToMult();
                break;
            case 3:
                msg = "Player " + (dartTrack.getTurn()+1) + " hit the 2x " + dartTrack.getValueToMult();
                break;
            case 4:
                msg = "Player " + (dartTrack.getTurn()+1) + " hit " + dartTrack.getValueToMult() + " with 2x multiplier";
                break;
            case 5:
                msg = ("Player " + (dartTrack.getTurn()+1) + " shot the 2x " + dartTrack.getValueToMult());
                break;
        }
        return msg;

    }

    private String random1x(int randomInt) {
        String msg = "\0";
        switch(randomInt) {
            case 1:
                msg = ("Player " + (dartTrack.getTurn()+1) + " hit the " + dartTrack.getValueToMult());
                break;
            case 2:
                msg = ("Player " + (dartTrack.getTurn()+1) + " hit " + dartTrack.getValueToMult());
                break;
            case 3:
                msg = ("Player " + (dartTrack.getTurn()+1) + " shot the " + dartTrack.getValueToMult());
                break;
            case 4:
                msg = ("Player " + (dartTrack.getTurn()+1) + " got a " + dartTrack.getValueToMult());
                break;
            case 5:
                msg = ("Player " + (dartTrack.getTurn()+1) + " scored " + dartTrack.getValueToMult() + " points");
                break;
        }
        return msg;
    }

    private String randomNextTurn(int randomInt) {
        String msg = "\0";
        switch(randomInt) {
            case 1:
                msg = "Player " + (dartTrack.getTurn()+1) + "'s turn!";
                break;
            case 2:
                msg = "Now it's Player " + (dartTrack.getTurn()+1) + "'s turn to shine!";
                break;
            case 3:
                msg = "Player " + (dartTrack.getTurn()+1) + ", you're up!";
                break;
            case 4:
                msg = "It's your move Player " + (dartTrack.getTurn()+1) + '!';
                break;
            case 5:
                msg = "It's Player " + (dartTrack.getTurn()+1) + "'s turn now!";
                break;
        }
        return msg;
    }
}