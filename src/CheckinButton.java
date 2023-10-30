//Formatting imports
import java.awt.Dimension;
import java.awt.Font;
//Action listener imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//JButton import
import javax.swing.JButton;

class CheckinButton extends JButton implements ActionListener{

    private static DartWindow dartWindow = DartWindow.getInstance();

    private static int selectionValue = 0; //value that user has selected
    private int value;

    //Static setters
    void setSelectionValue(int value) { selectionValue = value; }

    CheckinButton(int value) {
        addActionListener(this);
        this.value = value;
        setPreferredSize(new Dimension(100, 100));
        setFont(new Font("Calibri", Font.BOLD, 35));
        setBackground(dartWindow.getWhite());
        setForeground(dartWindow.getBlack());
        switch(value) {
            case 1:
                setText("Single-in");
                break;
            case 2:
                setText("Double-in");
                break;
            case 3:
                setText("Triple-in");
                break;
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        dartWindow.resetcheckinColors();
        setBackground(dartWindow.getGreen());
    }
}