//Java Swing
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;

//Formatting
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

//Writing to file
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;


public class DartWindow extends JFrame {
    //Buttons clicked
    boolean numPicked, multPicked;
    //Main Menu has occurred once
    boolean mainOnce;
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
    //Numbers Panel
    private JPanel numPanel;    
    private JPanel scorePanel;
    private JPanel playerScorePanel;
    private JPanel titlePanel;
    //Player Score Panels
    private JPanel [] playerPanels;
    //Player Score Labels
    private DartLabel [] playerScoreLabels;
    //Scrollable Log
    private JPanel logTitlePanel;
    private JPanel logScrollPanel;
    private DartLabel modeLabel;
    private JScrollPane logScroll;
    private JTextArea logText;

    //Main Menu////////////////////
    //Panels
    private JPanel checkInPanel;
    private JPanel middlePanel;
    private JPanel checkOutPanel;
    private JPanel playPanel;
    private JPanel titleBarPanel;

    //Play Button
    private JButton playButton;
    private Boolean checkInClicked;
    private Boolean checkOutClicked;
    //Check-in Check-out Buttons
    CheckButton [] checkinButtons;
    CheckButton [] checkoutButtons;
    DartLabel tLabel;
    DartLabel scoreLabel;
    DartLabel playerCountLabel;
    JPanel startScorePanel;
    JPanel playCountPanel;
    DartLabel spacingLabel[];
    JSpinner scoreSpinner;
    JSpinner playCountSpinner;
    DartLabel checkInLabel;
    DartLabel checkOutLabel;
    int checkinCount;
    int checkoutCount;


    ///////////////////////////////

    //Singleton////////////////////////
    private static DartWindow dartWindow;

    public static DartWindow getInstance() {
        if(dartWindow == null) {
            dartWindow = new DartWindow();
        }
        return dartWindow;
    }
    ///////////////////////////////////


    //Private constructor
    private DartWindow() {
        checkInClicked = false;
        checkOutClicked = false;
        numPicked = false;
        multPicked = false;
        dartTrack = DartTrack.getInstance();
        mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);
        setSize(1920,1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Colors/////////////////////////////////////
        dartWhite = new Color(200, 214, 229);
        dartBlack = new Color(34, 47, 62);
        dartRed = new Color(255, 56, 56);
        dartGreen = new Color(68, 189, 50);
        dartGold = new Color(254, 202, 87);
        darkDartWhite = new Color(136, 166, 199);
        lightDartGreen = new Color(177, 232, 169);
        lightDartRed = new Color(255, 175, 175);
        //////////////////////////////////////////////

        //Initialization for main menu
        checkInPanel = new JPanel(new GridLayout(3,1));
        checkOutPanel = new JPanel(new GridLayout(3,1));
        middlePanel = new JPanel();
        playPanel = new JPanel();
        titleBarPanel = new JPanel();
        playButton = new JButton("Play");
        mainOnce = false;
        tLabel = new DartLabel("DartTrack", 80, dartBlack);
        scoreLabel = new DartLabel("<html><center>Starting<br>Score</center></html>", 50, dartBlack);
        playerCountLabel = new DartLabel("<html><center>Player<br>Count</center></html>", 50, dartBlack);
        startScorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 5));
        playCountPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 5));
        spacingLabel = new DartLabel[4];
        scoreSpinner = new JSpinner(new SpinnerNumberModel(501,101,901,100));
        playCountSpinner = new JSpinner(new SpinnerNumberModel(2,2,10,1));
        checkInLabel = new DartLabel("<html><center>Check<br>In<center></html>", 50, dartBlack);
        checkOutLabel = new DartLabel("<html><center>Check<br>Out<center></html>", 50, dartBlack);
        checkinCount = 2;
        checkoutCount = 2;
        checkinButtons = new CheckButton[checkinCount];
        checkoutButtons = new CheckButton[checkoutCount];

    }
    
    public void mainMenu() {
        //Setting background colors
        playPanel.setBackground(dartWhite);
        checkOutPanel.setBackground(dartWhite);
        checkInPanel.setBackground(dartWhite);
        //Setting up title bar
        titleBarPanel.add(tLabel);
        titleBarPanel.setBackground(dartWhite);
        //Setting Score and Player Count Panel
        startScorePanel.setBackground(dartWhite);
        playCountPanel.setBackground(dartWhite);
        //Adding panels
        for (int i = 0; i < 4; i++) {
            spacingLabel[i] = new DartLabel();
            spacingLabel[i].setPreferredSize(new Dimension(100,60));
        }
        startScorePanel.add(spacingLabel[0]);
        startScorePanel.add(new JPanel().add(scoreLabel));
        startScorePanel.add(spacingLabel[1]);
        playCountPanel.add(spacingLabel[2]);
        playCountPanel.add(new JPanel().add(playerCountLabel));
        playCountPanel.add(spacingLabel[3]);
        //Setting up middle Panel
        middlePanel.setPreferredSize(new Dimension(1720,980));
        middlePanel.setLayout(new GridLayout(1,2));
        //Resizing and reformatting
        scoreSpinner.setPreferredSize(new Dimension(220,220));
        playCountSpinner.setPreferredSize(new Dimension(220,220));
        //Score Spinner Formatting
        (((JSpinner.DefaultEditor) scoreSpinner.getEditor()).getTextField()).setFont(new Font("Calibri", Font.BOLD, 100));
        scoreSpinner.setBackground(darkDartWhite);
        //Player Count Spinner Formatting
        (((JSpinner.DefaultEditor) playCountSpinner.getEditor()).getTextField()).setFont(new Font("Calibri", Font.BOLD, 100));
        playCountSpinner.setBackground(darkDartWhite);
        //Adding to panels
        startScorePanel.add(scoreSpinner);
        playCountPanel.add(playCountSpinner);
        //Adding to middlePanel
        middlePanel.add(startScorePanel);
        middlePanel.add(playCountPanel);
        checkInLabel.setPreferredSize(new Dimension(300,60));
        checkOutLabel.setPreferredSize(new Dimension(300,60));
        checkInPanel.add(checkInLabel);
        checkInPanel.setPreferredSize(new Dimension(300,260));
        checkOutPanel.add(checkOutLabel);
        checkOutPanel.setPreferredSize(new Dimension(300,260));
        //Adding all the button groups and panels
        for (int i = 0; i < checkinCount; i++) {
            checkinButtons[i] = new CheckButton(i+1, true);
            checkinButtons[i].setPreferredSize(new Dimension(100,100));
            checkinButtons[i].setBackground(darkDartWhite);
            checkInPanel.add(checkinButtons[i]);
        }
        for (int i = 0; i < checkoutCount; i++) {
            checkoutButtons[i] = new CheckButton(i+1, false);
            checkoutButtons[i].setPreferredSize(new Dimension(100,100));
            checkoutButtons[i].setBackground(darkDartWhite);
            checkOutPanel.add(checkoutButtons[i]);
        }
        //Setting up play button
        playButton.setFont(new Font("Calibri", Font.BOLD, 50));
        playButton.setBackground(lightDartRed);
        playButton.addActionListener(e -> { //Action listener for play button
            if (CheckButton.getCheckin() != 0 && CheckButton.getCheckout() != 0) {
                dartTrack.setMode(CheckButton.getCheckin(), CheckButton.getCheckout());
                dartTrack.setStartScore((Integer)scoreSpinner.getValue());
                dartTrack.setPlayerCount((Integer)playCountSpinner.getValue());
                //Removing all
                playPanel.removeAll();
                checkOutPanel.removeAll();
                checkInPanel.removeAll();
                middlePanel.removeAll();
                titleBarPanel.removeAll();
                getContentPane().removeAll();
                start();
            }
        });
        //Adding to play Panel
        playPanel.add(playButton);
        playPanel.setPreferredSize(new Dimension(1920,100));
        //Adding all panels to main panel
        mainPanel.add(titleBarPanel, BorderLayout.NORTH);
        mainPanel.add(checkInPanel, BorderLayout.WEST);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(checkOutPanel, BorderLayout.EAST);
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
        scorePanel.setBackground(dartBlack);
        scorePanel.setLayout(new FlowLayout());
        //Score Labels
        DartLabel titleLabel = new DartLabel("<html><center>Scores</center></html>",100,dartBlack);
        //Adding labels to panels
        titlePanel.setPreferredSize(new Dimension(500,100));
        titlePanel.add(titleLabel);
        titlePanel.setBackground(dartWhite);
        scorePanel.add(titlePanel);
        //Player score panels
        playerScorePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100,5));
        //Player Panels
        playerPanels = new JPanel[dartTrack.getPlayerCount()];
        //Player score setup
        playerScoreLabels = new DartLabel[dartTrack.getPlayerCount()];
        int playerPanelSize = 5;//Used to make playerScorePanel resizable according to # of players
        for (int i = 0; i < dartTrack.getPlayerCount(); i++) {
            playerPanelSize += 75;
            //Labels
            playerScoreLabels[i] = new DartLabel("Player " + (i+1) + ": " + dartTrack.getPlayerScore(i),50, dartBlack);
            //Panels
            playerPanels[i] = new JPanel();
            playerPanels[i].add(playerScoreLabels[i]);
            playerPanels[i].setPreferredSize(new Dimension(500,70));
            playerPanels[i].setBackground(dartWhite);
            playerScorePanel.add(playerPanels[i]);
        }
        playerScorePanel.setPreferredSize(new Dimension(500,playerPanelSize));
        playerScorePanel.setBackground(dartBlack);

        //Adding player score panel
        scorePanel.add(playerScorePanel);
        //Creating scroll panels
        logScrollPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100,0));
        logTitlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100,0));
        logTitlePanel.setPreferredSize(new Dimension(500,40));
        logTitlePanel.setBackground(dartWhite);
        //Gamemode Title Bar
        modeLabel = new DartLabel("Game Log",30,dartBlack);
        if(dartTrack.getCheckin()==1 && dartTrack.getCheckout()==1) {
            modeLabel.setText("Open-in | Open-out");
        } else if(dartTrack.getCheckin()==1 && dartTrack.getCheckout()==2) {
            modeLabel.setText("Open-in | Double-out");
        } else if(dartTrack.getCheckin()==2 && dartTrack.getCheckout()==1) {
            modeLabel.setText("Double-in | Open-out");
        } else {
            modeLabel.setText("Double-in | Double-out");
        }
        //Log Text Area
        logText = new JTextArea(modeLabel.getText() + "\nGame has commenced.\n\n");
        logText.setEditable(false);
        logText.setForeground(dartWhite);
        logText.setBackground(dartBlack);
        logText.setLineWrap(true);
        logText.setWrapStyleWord(true);
        logText.setFont(logText.getFont().deriveFont(25f));
        //Log scrollpane
        logScroll = new JScrollPane(logText);
        logScroll.setPreferredSize(new Dimension(450,1080-playerPanelSize-235));
        logTitlePanel.add(modeLabel);
        logScrollPanel.add(logScroll);
        logScrollPanel.setPreferredSize(new Dimension(500,1080-playerPanelSize));
        //Adding to scorePanel
        scorePanel.add(logTitlePanel);
        scorePanel.add(logScrollPanel);
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
        mainPanel.setBackground(dartBlack);

        setVisible(true);
    }

    //Update score
    public void updatePlayScore(String txt) {
        playerScoreLabels[dartTrack.getTurn()].setText(txt);
    }

    //Show all scores
    public void showPlayerScores() {
        for (int i = 0; i < dartTrack.getPlayerCount(); i++) {
            playerScoreLabels[i].setText("Player " + (i+1) + ": " + dartTrack.getPlayerScore(i));
        }
    }


    //Active player indicator
    public void updateActivePlayer() {
        playerPanels[dartTrack.getTurn()].setBackground(lightDartGreen);
        for (int i = 0; i < dartTrack.getPlayerCount(); i++) {
            if (i != dartTrack.getTurn()) {
                playerPanels[i].setBackground(darkDartWhite);
            }
        }
        if(dartTrack.getWinner() == -1) {
            updatePlayScore("Player " + (dartTrack.getTurn()+1) + ": " + dartTrack.getPlayerScore(dartTrack.getTurn()));
        }
    }

    //Change color of 3x
    public void disable3x() { multButtons[2].setBackground(lightDartRed);}
    public void enable3x() {multButtons[2].setBackground(dartGold);}

    public void resetNumColors() {
        for (int i = 0; i < 22; i++) {
            numberButtons[i].setBackground(dartWhite);
        }
    }

    public void resetcheckinColors() {
        for (int i = 0; i < 2; i++) {
            checkinButtons[i].setBackground(darkDartWhite);
        }
    }

    public void resetcheckoutColors() {
        for (int i = 0; i < 2; i++) {
            checkoutButtons[i].setBackground(darkDartWhite);
        }
    }
    //Check in Check-out change color of play button
    public void playButtonGreen() {playButton.setBackground(lightDartGreen);}
    public void setCheckInTrue() { checkInClicked = true; 
        if (checkOutClicked) { playButton.setBackground(lightDartGreen);}
    }
    public void setCheckOutTrue() { checkOutClicked = true;
        if(checkInClicked) { playButton.setBackground(lightDartGreen); }
    }

    public void addLogText(String txt) {
        logText.append(txt + '\n');
        logScroll.getVerticalScrollBar().setValue(logScroll.getVerticalScrollBar().getMaximum());
    }

    public void updateLog(int multValue) {
        Random messageRandomizer = new Random();
        int randomInt = (messageRandomizer.nextInt(5) + 1 );//Random int from 1-5
        if (dartTrack.getBust() == dartTrack.getPreviousTurn()) {//if bust then next turn
            dartWindow.addLogText("\nPlayer " + (dartTrack.getPreviousTurn()+1) + " a bust\n" + randomNextTurn(randomInt) + '\n');
        } else if (dartTrack.getDartCount() == 0 && dartTrack.getWinner() == -1) { //if not bust and dartcount = 0 then next turn
            dartWindow.addLogText(randomMessage(randomInt, multValue));
            dartWindow.addLogText('\n' + randomNextTurn(randomInt) + '\n');
        } else { //normal dart
            dartWindow.addLogText(randomMessage(randomInt, multValue));
        }
        dartWindow.showPlayerScores();
        dartWindow.updateActivePlayer();
        dartWindow.resetNumColors();
    }

    public void gameOverState() {
            dartWindow.showPlayerScores();
            dartWindow.grayOutNums();
            dartWindow.updatePlayScore("Player " + (dartTrack.getWinner()+1) + " wins!");
            dartWindow.addLogText("\nPlayer " + (dartTrack.getWinner()+1) + " wins!");
            dartWindow.changeNumsGameOver(12);
            dartWindow.changeNumsGameOver(13);
            dartWindow.fileWriteAddButton();
    }

    private void changeNumsGameOver(int number) {
        numberButtons[number].setOpaque(true);
        numberButtons[number].setFont(new Font("Calibri",Font.BOLD,70));
        numberButtons[number].setBackground(dartGold);
        if(number == 12) {
            numberButtons[number].setText("<html><center>Play<br>Again</center><html>");
        } else {
            numberButtons[number].setText("<html><center>Main<br>Menu</center><html>");
        }
        numberButtons[number].addActionListener(e -> {
            if(dartTrack.getWinner() != -1) {
                dartTrack.softResetGame();
                logText.setText("");
                //Reset number 12
                numberButtons[12].setBackground(dartWhite);
                numberButtons[12].setText("12");
                numberButtons[12].setFont(new Font("Calibri", Font.BOLD, 100));
                //Reset number 13
                numberButtons[13].setText("13");
                numberButtons[13].setBackground(dartWhite);
                numberButtons[13].setFont(new Font("Calibri", Font.BOLD, 100));
                revertLogTitlePanel();
                if(number == 13) {
                    getContentPane().removeAll();
                    mainMenu();
                }
                revalidate();
                repaint();
            }
        });
    }

    private void grayOutNums() {
        for (int i = 0; i < 3; i++) {
            multButtons[i].setBackground(darkDartWhite);
        }
        for(int i = 0; i < 22; i++) {
            numberButtons[i].setBackground(darkDartWhite);
        }
    }
    //Writing to file
    private boolean writeLogToFile() {
        String fileName="dart_log_";
        boolean writeSuccessful = false;
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH_mm_ss");
        fileName += time.format(formatter);
        fileName += ".txt";

        try(FileWriter fileWriter = new FileWriter(fileName);) {
            fileWriter.write(logText.getText());
            writeSuccessful = true;
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return writeSuccessful;
    }

    private void fileWriteAddButton() {
        logTitlePanel.removeAll();
        JButton fileWriteButton = new JButton("Save game logs");
        fileWriteButton.setPreferredSize(new Dimension(380,35));
        fileWriteButton.setFont(new Font("Calibri", Font.BOLD, 30));
        fileWriteButton.setForeground(dartBlack);
        fileWriteButton.setBackground(dartGold);
        fileWriteButton.addActionListener(e->{
            if(writeLogToFile()) {
                addLogText("\nWrite to file was successful.");
            }
            
        });
        logTitlePanel.setBackground(dartBlack);
        logTitlePanel.add(fileWriteButton);
    }

    private void revertLogTitlePanel() {
        logTitlePanel.removeAll();
        logTitlePanel.add(modeLabel);
    }

    private String randomMessage(int randomInt, int multValue) {
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
                msg = ("Player " + (dartTrack.getPreviousTurn()+1) + " hit a whopping 3x " + dartTrack.getValueToMult());
                break;
            case 2:
                msg = ("Player " + (dartTrack.getPreviousTurn()+1) + " hit the 3x " + dartTrack.getValueToMult() + '!');
                break;
            case 3:
                msg = "Woah! Player " + (dartTrack.getPreviousTurn()+1) + " got a " + dartTrack.getValueToMult() + "x of " + dartTrack.getValueToMult();
                break;
            case 4:
                msg = "Player " + (dartTrack.getPreviousTurn()+1) + " shot a 3x " + dartTrack.getValueToMult() + '!';
                break;
            case 5:
                msg = "Player " + (dartTrack.getPreviousTurn()+1) + " hit a 3x " + dartTrack.getValueToMult() + '!';
                break;
        }
        return msg;
    }
    
    private String random2x(int randomInt) {
        String msg = "\0";
        switch(randomInt) {
            case 1:
                msg = ("Player " + (dartTrack.getPreviousTurn()+1) + " got " + dartTrack.getValueToMult() + " with 2x multiplier");
                break;
            case 2:
                msg = "Player " + (dartTrack.getPreviousTurn()+1) + " got a 2x " + dartTrack.getValueToMult();
                break;
            case 3:
                msg = "Player " + (dartTrack.getPreviousTurn()+1) + " hit the 2x " + dartTrack.getValueToMult();
                break;
            case 4:
                msg = "Player " + (dartTrack.getPreviousTurn()+1) + " hit " + dartTrack.getValueToMult() + " with 2x multiplier";
                break;
            case 5:
                msg = ("Player " + (dartTrack.getPreviousTurn()+1) + " shot the 2x " + dartTrack.getValueToMult());
                break;
        }
        return msg;

    }

    private String random1x(int randomInt) {
        String msg = "\0";
        switch(randomInt) {
            case 1:
                msg = ("Player " + (dartTrack.getPreviousTurn()+1) + " hit the " + dartTrack.getValueToMult());
                break;
            case 2:
                msg = ("Player " + (dartTrack.getPreviousTurn()+1) + " hit " + dartTrack.getValueToMult());
                break;
            case 3:
                msg = ("Player " + (dartTrack.getPreviousTurn()+1) + " shot the " + dartTrack.getValueToMult());
                break;
            case 4:
                msg = ("Player " + (dartTrack.getPreviousTurn()+1) + " got a " + dartTrack.getValueToMult());
                break;
            case 5:
                msg = ("Player " + (dartTrack.getPreviousTurn()+1) + " scored " + dartTrack.getValueToMult() + " points");
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