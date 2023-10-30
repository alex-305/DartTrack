//Java Swing
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

//Formatting
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;


public class DartWindow extends JFrame {
    //Buttons clicked
    boolean numPicked, multPicked;
    //Colors
    final private Color dartWhite;
    final private Color dartBlack;
    final private Color dartRed;
    final private Color dartGreen;
    final private Color dartGold;
    final private Color darkDartWhite;
    final private Color lightDartRed;
    final private Color lightDartGreen;
    //Color Getters
    public Color getWhite() { return dartWhite; }
    public Color getBlack() { return dartBlack; }
    public Color getRed() { return dartRed; }
    public Color getGreen() { return dartGreen; }
    public Color getGold() { return dartGold; }
    public Color getDarkWhite() { return darkDartWhite; }
    public Color getLightRed() { return lightDartRed; }
    public Color getLightGreen() { return lightDartGreen; }
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
    //Player Score Panels
    private JPanel [] playerPanels;
    //Player Score Labels
    private JLabel [] playerScoreLabels;

    //Singleton
    private static DartWindow dartWindow;

    //Main Menu
    //Panels
    private JPanel checkinPanel;
    private JPanel startScorePanel;
    private JPanel checkoutPanel;
    private JPanel playPanel;

    //Play Button
    private JButton playButton;
    //Check-in Check-out Buttons
    CheckinButton [] checkinButtons;
    CheckoutButton [] checkoutButtons;

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
        mainPanel = new JPanel(new FlowLayout());
        setContentPane(mainPanel);
        setSize(1920,1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Colors
        dartWhite = new Color(200, 214, 229);
        dartBlack = new Color(34, 47, 62);
        dartRed = new Color(255, 56, 56);
        dartGreen = new Color(68, 189, 50);
        dartGold = new Color(254, 202, 87);
        darkDartWhite = new Color(136, 166, 199);
        lightDartGreen = new Color(177, 232, 169);
        lightDartRed = new Color(255, 175, 175);
    }
    
    public void mainMenu() {
        mainPanel.setLayout(new BorderLayout());
        int checkinCount = 3;
        int checkoutCount = 3;
        //Setting up panels
        checkinPanel = new JPanel(new GridLayout(4,1));
        checkoutPanel = new JPanel(new GridLayout(4,1));
        startScorePanel = new JPanel();
        playPanel = new JPanel();
        //Spacing Panel
        startScorePanel.setPreferredSize(new Dimension(1720,980));
        //Check-in Check-out Labels
        JLabel checkInLabel = new JLabel("Check in");
        JLabel checkOutLabel = new JLabel("Check out");
        checkInLabel.setFont(new Font("Calibri", Font.BOLD, 50));
        checkOutLabel.setFont(new Font("Calibri", Font.BOLD, 50));
        checkinPanel.add(checkInLabel);
        checkoutPanel.add(checkOutLabel);
        //Setting up check-in check-out radio buttons
        checkinButtons = new CheckinButton[checkinCount];
        checkoutButtons = new CheckoutButton[checkoutCount];
        //Adding all the button groups and panels
        for (int i = 0; i < checkinCount; i++) {
            checkinButtons[i] = new CheckinButton(i+1);
            checkinPanel.add(checkinButtons[i]);
        }
        for (int i = 0; i < checkoutCount; i++) {
            checkoutButtons[i] = new CheckoutButton(i+1);
            checkoutPanel.add(checkoutButtons[i]);
        }
        //Setting up play button
        playButton = new JButton("Play");
        playButton.setFont(new Font("Calibri", Font.BOLD, 50));
        playButton.addActionListener(e -> {
            if (CheckinButton.getSelectionValue() != 0 && CheckoutButton.getSelectionValue() != 0) {
                dartTrack.setMode(CheckinButton.getSelectionValue(), CheckoutButton.getSelectionValue());
                getContentPane().removeAll();
                start();
            }
        });
        //Adding to play Panel
        playPanel.add(playButton);
        playPanel.setPreferredSize(new Dimension(1920,100));
        //Adding all panels to main panel
        mainPanel.add(checkinPanel, BorderLayout.WEST);
        mainPanel.add(startScorePanel, BorderLayout.CENTER);
        mainPanel.add(checkoutPanel, BorderLayout.EAST);
        mainPanel.add(playPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void start() {
        //Resetting the frame
        revalidate();
        repaint();
        //Score Panels
        scorePanel = new JPanel();
        titlePanel = new JPanel();
        playerScorePanel = new JPanel();
        //Score Panel
        scorePanel.setPreferredSize(new Dimension(450,1080));
        scorePanel.setBackground(dartWhite);
        scorePanel.setLayout(new FlowLayout());
        //Score Labels
        JLabel titleLabel = new JLabel("<html><center>Scores</center></html>");
        titleLabel.setForeground(dartBlack);
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 100));
        //Adding labels to panels
        titlePanel.setPreferredSize(new Dimension(500,100));
        titlePanel.add(titleLabel);
        titlePanel.setBackground(dartWhite);
        scorePanel.add(titlePanel);
        //Player score panels
        playerScorePanel.setLayout(new FlowLayout());
        playerScorePanel.setPreferredSize(new Dimension(500,850));
        //Player Panels
        playerPanels = new JPanel[dartTrack.getPlayerCount()];
        //Player score setup
        playerScoreLabels = new JLabel[dartTrack.getPlayerCount()];
        for (int i = 0; i < dartTrack.getPlayerCount(); i++) {
            playerScoreLabels[i] = new JLabel("Player " + (i+1) + ": " + dartTrack.getPlayerScore(i));
            playerScoreLabels[i].setFont(new Font("Calibri", Font.BOLD, 50));
            playerScoreLabels[i].setForeground(dartBlack);
            //Panels
            playerPanels[i] = new JPanel();
            playerPanels[i].add(playerScoreLabels[i]);
            playerPanels[i].setPreferredSize(new Dimension(500,70));
            playerPanels[i].setBackground(dartWhite);
            playerScorePanel.add(playerPanels[i]);
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
        //Updating active player
        updateActivePlayer();
        //Adding panels to main panel
        mainPanel.add(numPanel);
        mainPanel.add(scorePanel, BorderLayout.EAST);

        setVisible(true);
    }

    //Update score
    public void updatePlayScore(String txt) {playerScoreLabels[dartTrack.getTurn()].setText(txt); }

    //Show all scores
    public void showPlayerScores() {
        for (int i = 0; i < dartTrack.getPlayerCount(); i++) {
            playerScoreLabels[i].setText("Player " + (i+1) + ": " + dartTrack.getPlayerScore(i));
        }
    }

    //Active player indicator
    public void updateActivePlayer() {
        if (dartTrack.getBadScore()) {
            playerPanels[dartTrack.getTurn()].setBackground(lightDartRed);
        } else {
            playerPanels[dartTrack.getTurn()].setBackground(lightDartGreen);
        }
        for (int i = 0; i < dartTrack.getPlayerCount(); i++) {
            if (i != dartTrack.getTurn()) {
                playerPanels[i].setBackground(darkDartWhite);
            }
        }
        if(dartTrack.getWinner() == -1) {
            updatePlayScore("Player " + (dartTrack.getTurn()+1) + ": " + dartTrack.getPlayerScore(dartTrack.getTurn()));
        }
    }

    public void resetNumColors() {
        for (int i = 0; i < 22; i++) {
            numberButtons[i].setBackground(dartWhite);
        }
    }

    public void resetcheckinColors() {
        for (int i = 0; i < 3; i++) {
            checkinButtons[i].setBackground(dartWhite);
        }
    }

    public void resetcheckoutColors() {
        for (int i = 0; i < 3; i++) {
            checkoutButtons[i].setBackground(dartWhite);
        }
    }
}