import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.Font;

public class DartWindow extends JFrame {
    private NumberButton [] numberButtons;
    private JPanel numOptions;
    
    public DartWindow(Player p1, Player p2) {
        setSize(1000,1000);
    }
    
    public void start() {
        //Buttons
        numberButtons = new NumberButton[25];
        numOptions.setLayout(new GridBagLayout());
        for (int i = 0, multi = 0; i < 21; i++) {
            if (i < 20) { //Numbers
                numberButtons[i] = new NumberButton(i);
                numberButtons[i].addActionListener((e) -> {

                });
            } else {
                numberButtons[20] = new NumberButton(25);
            }

            numOptions.add(numberButtons[i]);
        }

    }

}