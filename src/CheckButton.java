//Formatting imports
import java.awt.Dimension;
import java.awt.Font;
//Action listener imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//JButton import
import javax.swing.JButton;

class CheckButton extends JButton implements ActionListener{

    private static DartWindow dartWindow = DartWindow.getInstance();
    private boolean isIn;
    private static int inValue = -1; //check-in Value
    private static int outValue = -1; //check-out Value
    private int value;
    //Get Selection value
    public static int getCheckin() { return inValue; }
    public static int getCheckout() { return outValue; }
    //Static setters
    private static void setCheckIn(int value) { inValue = value; }
    private static void setCheckOut(int value) { outValue = value; }

    CheckButton(int value, boolean isCheckIn) {
        addActionListener(this);
        isIn = isCheckIn;
        this.value = value;
        setFocusPainted(false);
        setPreferredSize(new Dimension(100, 100));
        setFont(new Font("Calibri", Font.BOLD, 65));
        setBackground(dartWindow.getWhite());
        setForeground(dartWindow.getBlack());
        switch(value) {
            case 1:
                setText("Open");
                break;
            case 2:
                setText("Double");
                break;
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(isIn) {
            dartWindow.resetcheckinColors();
            setCheckIn(value);
            dartWindow.setCheckInTrue();
        } else {
            dartWindow.resetcheckoutColors();
            setCheckOut(value);
            dartWindow.setCheckOutTrue();
        }
        setBackground(dartWindow.getLightGreen());
    }
}