package Controll;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class Cell extends JPanel {

    protected int cellValue;
    protected JLabel textField;
    protected int[] position;
    protected boolean locked;
    protected Font cellFont;

    public Cell() {
        cellValue = 0;
        init();
    }

    public Cell(int[] position) {
        this.position = position;
        cellValue = 0;
        init();
    }

    public void init() {
        setFocusable(true);
        textField = new JLabel();
        setLayout(new FlowLayout());
        add(textField);
        setLock(false);
        cellFont = new Font("Arial", Font.BOLD, 20);
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setVerticalAlignment(JLabel.CENTER);
        textField.setFont(cellFont);
        drawBorder();
        //textField.setEditable(false); //Alle Felder sind nicht Editierbar, so dass der Benutzer keine Eingaben außer über den KeyListener machen kann

        //KeyListener für Eingaben der Felder die nicht gesperrt (=!isLocked) sind
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                //irrelevant
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (true) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_1:
                            textField.setText("1");
                            break;
                        case KeyEvent.VK_2:
                            textField.setText("2");
                            break;
                        case KeyEvent.VK_3:
                            textField.setText("3");
                            break;
                        case KeyEvent.VK_4:
                            textField.setText("4");
                            break;
                        case KeyEvent.VK_5:
                            textField.setText("5");
                            break;
                        case KeyEvent.VK_6:
                            textField.setText("6");
                            break;
                        case KeyEvent.VK_7:
                            textField.setText("7");
                            break;
                        case KeyEvent.VK_8:
                            textField.setText("8");
                            break;
                        case KeyEvent.VK_9:
                            textField.setText("9");
                            break;
                        case KeyEvent.VK_BACK_SPACE:
                        case KeyEvent.VK_DELETE:
                            textField.setText("");
                            e.consume(); //verhindert dass Windows einen Fehlersound bei Eingaben wie BackSpace abspieltbreak;
                            break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //irrelevant
            }
        });
    }

    private void drawBorder() {

        //Um alle Sudokukästchen eine dünne graue Linie ziehen
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        if (position != null) {
            //Für die dicken Linen in den Eckpunkten (3, 3); (6, 3); (3, 6); (6, 6);
            if ((position[0] == 3 && position[1] == 3) || (position[0] == 6 && position[1] == 3) || (position[0] == 3 && position[1] == 6) || (position[0] == 6 && position[1] == 6)) {
                setBorder(new CompoundBorder(BorderFactory.createMatteBorder(3, 3, 0, 0, Color.black),
                        BorderFactory.createMatteBorder(0, 0, 1, 1, Color.GRAY)));
            }
            //Für die dicken Linen der horizontalen Achse;
            else if (position[0] == 3 || position[0] == 6) {
                setBorder(new CompoundBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, Color.black),
                        BorderFactory.createMatteBorder(0, 1, 1, 1, Color.GRAY)));
            }
            //Für die dicken Linen der vertikalen Achse;
            else if (position[1] == 3 || position[1] == 6) {
                setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, Color.black),
                        BorderFactory.createMatteBorder(1, 0, 1, 1, Color.GRAY)));
            }
        }
    }

    public void setValueandDraw(int value) {
        cellValue = value;
        drawValueOnGUI();
    }

    public void setCellValue(int value) {
        cellValue = value;
    }

    public int getCellValue() {
        return cellValue;
    }

    public void setLock(boolean L) {
        locked = L;
        if (locked) setForeground(new Color(100, 100, 100));
        else setForeground(Color.BLACK);
    }

    public boolean isLocked() {
        return locked;
    }

    public void drawValueOnGUI() {
        if (cellValue == 0) {
            textField.setText("");
        } else {
            textField.setText(String.valueOf(cellValue));
        }
    }

    public void markWithColor(Color color){
        setBackground(color);
    }

    public void markDefault(){
        setBackground(Color.white);
    }

    public void updateValue(){
        if (textField.getText().equals("")){
            cellValue = 0;
        } else {
            cellValue = Integer.parseInt(textField.getText());
        }
    }

    public int[] getPosition(){
        return new int[] {position[0], position[1]};
    }

    public JLabel getTextField(){
        return textField;
    }
}
