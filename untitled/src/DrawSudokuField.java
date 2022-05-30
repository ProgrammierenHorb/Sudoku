import javax.swing.*;
import java.awt.*;

public class DrawSudokuField extends JPanel {
    private static long serialVersionUID = 1L;
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.BLACK);

        int x = 50;
        int y = 50;
        for(int i = 0; i < 9; i++ ){
            DrawLine(g, x, y);
            x += 50;
            y += 50;
            System.out.println(x);
        }
    }
    public void DrawLine(Graphics g, int x, int y){
        g.drawLine(x, 0, x, 500);
        g.drawLine(0, y, 500, y);
    }

}
