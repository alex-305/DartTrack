import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.Font;

public class DartWindow extends JFrame {
    private NumberButton [] numberButtons;
    private MultiplierButton [] multButtons;
    private JPanel numOptions;

    public DartWindow(Player p1, Player p2) {
        setSize(1000,1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void start() {
        //Buttons
        numberButtons = new NumberButton[22];
        //Initialzing and setting layout for numPanel
        numOptions = new JPanel(new GridLayout(5,5));
        numOptions.setSize(500,500);
        for (int i = 0; i < 21; i++) {
            //Making all buttons equal a number and adding them to panel
            numberButtons[i] = new NumberButton(i);
            numOptions.add(numberButtons[i]);
        }
        //Special case of 25 being initialized then added
        numberButtons[21] = new NumberButton(25);
        numOptions.add(numberButtons[21]);
        //Multipler Buttons
        multButtons = new MultiplierButton[3];
        for (int i = 0; i < 3; i++) {
            multButtons[i] = new MultiplierButton(i+1);
            numOptions.add(multButtons[i]);
        }
        //Adding panel to frame
        add(numOptions);

        setVisible(true);
    }

}