import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.plaf.basic.BasicSpinnerUI;

public class DartSpinnerButtonUI extends BasicSpinnerUI {

    private DartWindow dartWindow;
    private int width;
    
    DartSpinnerButtonUI() {
        dartWindow = DartWindow.getInstance();
        width = 80;
    }
    @Override
    protected Component createNextButton() {
        JButton upButton = (JButton) super.createNextButton();
        upButton.setForeground(dartWindow.getBlack());
        upButton.setBackground(dartWindow.getDarkWhite());
        upButton.setSize(new Dimension(width, upButton.getPreferredSize().height));
        upButton.doLayout();
        return upButton;
    }

    @Override
    protected Component createPreviousButton() {
        JButton downButton = (JButton) super.createPreviousButton();
        downButton.setForeground(dartWindow.getBlack());
        downButton.setBackground(dartWindow.getDarkWhite());
        downButton.setSize(new Dimension(width, downButton.getPreferredSize().height));
        downButton.doLayout();
        return downButton; 
    }
}