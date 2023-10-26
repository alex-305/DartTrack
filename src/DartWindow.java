//Java Swing
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

//Formatting
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;


public class DartWindow extends JFrame {
    final private Color dartWhite;
    final private Color dartBlack;
    final private Color dartGreen;
    public Player PlayerOne, PlayerTwo;
    private NumberButton [] numberButtons;
    private MultiplierButton [] multButtons;
    private JPanel numOptions;

    public DartWindow() {
        setSize(1000,1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        dartWhite = new Color(236, 240, 241);
        dartBlack = new Color(45, 52, 54);
        dartGreen = new Color(68, 189, 50);
    }
    
    public void start() {
        //Buttons
        numberButtons = new NumberButton[22];
        //Initialzing and setting layout for numPanel
        numOptions = new JPanel(new GridLayout(5,5));
        numOptions.setSize(500,500);
        for (int i = 0; i < 21; i++) {
            //Making all buttons equal a number and adding them to panel
            numberButtons[i] = new NumberButton(i, this);
            switch(i) {
                case 1: case 4: case 5:
                case 6: case 9: case 11:
                case 15: case 17:
                case 16: case 19:
                    numberButtons[i].setForeground(dartBlack);
                    numberButtons[i].setBackground(dartWhite);
                    break;
                default:
                    numberButtons[i].setForeground(dartWhite);
                    numberButtons[i].setBackground(dartBlack);
            }
            numOptions.add(numberButtons[i]);
        }
        //Special case of 25 being initialized then added
        numberButtons[21] = new NumberButton(25, this);
        numberButtons[21].setForeground(dartBlack);
        numberButtons[21].setBackground(dartGreen);        
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

    public void play() {

    }

}