import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.Font;

public class DartWindow extends JFrame {
    private JButton [] buttons;
    private JPanel numOptions;
    public DartWindow(Player p1, Player p2) {
        setSize(1000,1000);
    }
    
    public void start() {
        //Buttons
        buttons = new JButton[25];
        numOptions.setLayout(new GridBagLayout());
        //Number picked
        boolean numPicked = false;

        for (int i = 0, multi = 0; i < 25; i++) {
                buttons[i].setFont(new Font("Calibri", Font.BOLD, 100));
                buttons[i].setFocusPainted(false);
            if (i < 20) { //Numbers
                buttons[i] = new JButton(String.valueOf(i));
                buttons[i].addActionListener((e) -> {
                    JButton numButton = (JButton) e.getSource();
                    for (int j = 20; j < 23; j++) {
                        buttons[i];
                    }
                });
            } else if (i < 23) {
                multi++;
                buttons[i] = new JButton(String.valueOf(i)+'x');
            } else if (i == 23) {
                buttons[i] = new JButton(String.valueOf(i));
            } else {
                buttons[i] = new JButton("Enter");
            }

            numOptions.add(buttons[i]);
        }

    }

}