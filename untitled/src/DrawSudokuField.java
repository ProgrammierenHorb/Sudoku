import javax.swing.*;
import java.awt.*;

public class DrawSudokuField extends JPanel {
    private static long serialVersionUID = 1L;
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.BLACK);
        g.drawLine(10, 0, 500, 0);
    }

}
