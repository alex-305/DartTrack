import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

class CheckoutButton extends JButton implements ActionListener {
    private static DartWindow dartWindow = DartWindow.getInstance();

    private static int selectionValue = 0; //value that user has selected
    private int value;

    //Get Selection value
    public static int getSelectionValue() {return selectionValue; }
    //Static setters
    private static void setSelectionValue(int value) { selectionValue = value; }

    CheckoutButton(int value) {
        addActionListener(this);
        this.value = value;
        setFocusPainted(false);
        setPreferredSize(new Dimension(100, 100));
        setFont(new Font("Calibri", Font.BOLD, 35));
        setBackground(dartWindow.getWhite());
        setForeground(dartWindow.getBlack());
        switch(value) {
            case 1:
                setText("Single-out");
                break;
            case 2:
                setText("Double-out");
                break;
            case 3:
                setText("Triple-out");
                break;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        dartWindow.resetcheckoutColors();
        setBackground(dartWindow.getGreen());
        setSelectionValue(value);
    }
}