//Java Swing
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

//Formatting
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;


public class DartWindow extends JFrame {
    //Colors
    final private Color dartWhite;
    final private Color dartBlack;
    final private Color dartGreen;
    public DartTrack dartTrack;
    //Buttons
    private NumberButton [] numberButtons;
    private MultiplierButton [] multButtons;
    //Panels
    private JPanel mainPanel;
    private JPanel numPanel;
    private JPanel scorePanel;
    private JPanel p1ScorePanel;
    private JPanel p2ScorePanel;
    private JPanel titlePanel;


    public DartWindow() {
        dartTrack = new DartTrack();
        mainPanel = new JPanel(new BorderLayout());
        setSize(1920,1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        dartWhite = new Color(236, 240, 241);
        dartBlack = new Color(45, 52, 54);
        dartGreen = new Color(68, 189, 50);
    }
    
    public void start() {
        //Score Panels
        scorePanel = new JPanel();
        titlePanel = new JPanel();
        p1ScorePanel = new JPanel();
        p2ScorePanel = new JPanel();
        //Sizing
        scorePanel.setSize(400,1080);
        //Score Labels
        JLabel titleLabel = new JLabel("<html><center>Scores</center></html>");
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 200));
        //Adding labels to panels
        titlePanel.add(titleLabel);
        scorePanel.add(titlePanel);
        //Number Buttons
        numberButtons = new NumberButton[22];
        //Initialzing and setting layout for numPanel
        numPanel = new JPanel(new GridLayout(5,5));
        numPanel.setSize(1000,1000);
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
            numPanel.add(numberButtons[i]);
        }
        //Special case of 25 being initialized then added
        numberButtons[21] = new NumberButton(25, this);
        numberButtons[21].setForeground(dartBlack);
        numberButtons[21].setBackground(dartGreen);        
        numPanel.add(numberButtons[21]);
        //Multipler Buttons
        multButtons = new MultiplierButton[3];
        for (int i = 0; i < 3; i++) {
            multButtons[i] = new MultiplierButton(i+1, this);
            numPanel.add(multButtons[i]);
        }
        //Adding panels to main panel
        mainPanel.add(numPanel);
        mainPanel.add(scorePanel, BorderLayout.EAST);
        add(mainPanel);

        setVisible(true);
    }

    public void play() {

    }

}