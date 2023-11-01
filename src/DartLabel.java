import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;


public class DartLabel extends JLabel {
    DartLabel(String text, int sizeOfText, Color colorOfText) {
        super(text);
        setForeground(colorOfText);
        setFont(new Font("Calibri", Font.BOLD, sizeOfText));
        setHorizontalAlignment(JLabel.CENTER);
    }
    DartLabel() {
        
    }
}