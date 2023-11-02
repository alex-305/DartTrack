import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;
public class DartPanel extends JPanel {

    DartPanel(LayoutManager layout, int width, int height, Color background) {
        if(layout != null) {
            setLayout(layout);
        }
        setPreferredSize(new Dimension(width,height));
        setBackground(background);
    }
    DartPanel(LayoutManager layout, int width, int height) {
        if(layout != null) {
            setLayout(layout);
        }
        setPreferredSize(new Dimension(width,height));
    }

    DartPanel(LayoutManager layout, Color background) {
        if(layout != null) {
            setLayout(layout);
        }
        setBackground(background);
    }

    DartPanel() {
        
    }

}