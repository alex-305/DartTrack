import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.Font;

public class DartWindow extends JFrame {
    private NumberButton [] numberButtons;
    private JPanel numOptions;

    public DartWindow(Player p1, Player p2) {
        setSize(1000,1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void start() {
        //Buttons
        numberButtons = new NumberButton[25];
        //Initialzing and setting layout for numPanel
        numOptions = new JPanel(new GridLayout(5,5));
        numOptions.setSize(500,500);
        for (int i = 0; i < 20; i++) {
            //Making all buttons equal a number and adding them to panel
            numberButtons[i] = new NumberButton(i);
            numOptions.add(numberButtons[i]);
        }
        //Special case of 25 being initialized then added
        numberButtons[20] = new NumberButton(25);
        numOptions.add(numberButtons[20]);
        
        //Adding panel to frame
        add(numOptions);

        setVisible(true);
    }

}