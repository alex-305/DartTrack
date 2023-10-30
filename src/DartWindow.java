//Java Swing
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

//Formatting
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;


public class DartWindow extends JFrame {
    //Buttons clicked
    boolean numPicked, multPicked;
    //Colors
    final private Color dartWhite;
    final private Color dartBlack;
    final private Color dartRed;
    //DartTrack
    public static DartTrack dartTrack;
    //Buttons
    private NumberButton [] numberButtons;
    private MultiplierButton [] multButtons;
    //Panels
    private JPanel mainPanel;
    private JPanel numPanel;
    private JPanel scorePanel;
    private JPanel playerScorePanel;
    private JPanel titlePanel;
    //Player Score Labels
    private JLabel [] playerScoreLabels;

    //Singleton
    private static DartWindow dartWindow;

    public static DartWindow getInstance() {
        if(dartWindow == null) {
            dartWindow = new DartWindow();
        }
        return dartWindow;
    }

    //Private constructor
    private DartWindow() {
        numPicked = false;
        multPicked = false;
        dartTrack = DartTrack.getInstance();
        mainPanel = new JPanel(new BorderLayout());
        setSize(1920,1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Colors
        dartWhite = new Color(236, 240, 241);
        dartBlack = new Color(45, 52, 54);
        dartRed = new Color(255, 56, 56);
    }
    
    public void mainMenu() {
        
    }

    public void start() {
        //Resetting the frame
        resetWindow();
        //Score Panels
        scorePanel = new JPanel();
        titlePanel = new JPanel();
        playerScorePanel = new JPanel();
        //Score Panel
        scorePanel.setSize(400,1080);
        scorePanel.setLayout(new GridLayout(dartTrack.getPlayerCount(),1));
        //Score Labels
        JLabel titleLabel = new JLabel("<html><center>Scores</center></html>");
        titleLabel.setForeground(dartWhite);
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 200));
        //Adding labels to panels
        titlePanel.setSize(new Dimension(400,230));
        titlePanel.add(titleLabel);
        titlePanel.setBackground(dartBlack);
        scorePanel.add(titlePanel);
        //Player score panels
        playerScorePanel.setLayout(new GridLayout(dartTrack.getPlayerCount(),1));
        //Player score labels
        playerScoreLabels = new JLabel[dartTrack.getPlayerCount()];
        for (int i = 0; i < dartTrack.getPlayerCount(); i++) {
            playerScoreLabels[i] = new JLabel("Player " + (i+1) + ": 501");
            playerScoreLabels[i].setFont(new Font("Calibri", Font.BOLD, 50));
            playerScoreLabels[i].setForeground(dartWhite);
            playerScorePanel.add(playerScoreLabels[i]);
        }
        playerScorePanel.setBackground(dartBlack);
        //Adding player score panel
        scorePanel.add(playerScorePanel);
        //Number Buttons
        numberButtons = new NumberButton[22];
        //Initialzing and setting layout for numPanel
        numPanel = new JPanel(new GridLayout(5,5));
        numPanel.setSize(1000,1000);
        for (int i = 0; i < 21; i++) {
            //Making all buttons equal a number and adding them to panel
            numberButtons[i] = new NumberButton(i);
            numPanel.add(numberButtons[i]);
        }
        //Special case of 25 being initialized then added
        numberButtons[21] = new NumberButton(25);
        numPanel.add(numberButtons[21]);
        //Multipler Buttons
        multButtons = new MultiplierButton[3];
        for (int i = 0; i < 3; i++) {
            multButtons[i] = new MultiplierButton(i+1);
            numPanel.add(multButtons[i]);
        }
        //Adding panels to main panel
        mainPanel.add(numPanel);
        mainPanel.add(scorePanel, BorderLayout.EAST);
        add(mainPanel);

        setVisible(true);
    }

    //Reset JFrame

    private void resetWindow() {
        getContentPane().removeAll();
        mainPanel.removeAll();
    }

    //Multiplier Button functions
    public void updatePlayScore(String txt) {playerScoreLabels[dartTrack.getTurn()].setText(txt); }

    public void resetNumColors() {
        for (int i = 0; i < 22; i++) {
            numberButtons[i].setBackground(dartWhite);
        }
    }

    public void invalidInput() {
        playerScoreLabels[dartTrack.getTurn()].setForeground(dartRed);
    }

    public void validInput() {
        playerScoreLabels[dartTrack.getTurn()].setForeground(dartWhite);        
    }
}