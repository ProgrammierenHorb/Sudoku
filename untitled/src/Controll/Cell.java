package Controll;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class Cell extends JPanel {

    protected boolean[] notesActive;
    public JLabel[] notes;
    protected int cellValue;
    protected JLabel textField;
    protected int[] position;
    protected boolean locked;
    protected Font cellFont;

    GridLayout notesLayout = new GridLayout(3, 3);
    GridBagLayout valueLayout = new GridBagLayout();

    public Cell() {
        cellValue = 0;
        init();
    }

    public Cell(int[] position) {
        this.position = position;
        cellValue = 0;
        init();
        initNotes();
    }

    public void init() {
        textField = new JLabel();
        setLayout(valueLayout);
        markDefault();
        //add(textField, Grid.CENTER);
        add(textField);
        cellFont = new Font("Arial", Font.BOLD, 20);
        //textField.setHorizontalAlignment(JLabel.CENTER);
        //textField.setVerticalAlignment(JLabel.TOP);
        //textField.setBackground(Color.green);
        textField.setFont(cellFont);
        drawBorder();
        //textField.setEditable(false); //Alle Felder sind nicht Editierbar, so dass der Benutzer keine Eingaben außer über den KeyListener machen kann

    }

    public void initNotes(){
            notes = new JLabel[9];
            for (int i = 0; i < 9; i++) {
                notes[i] = new JLabel();
                notes[i].setText("");
            }
    }

    public void setNotesLayout(){
        if(getLayout() != notesLayout) {
            setLayout(notesLayout);
            remove(textField);
            revalidate();
            repaint();
            for (int i = 0; i < 9; i++) {
                notes[i].setText("");
                add(notes[i]);
            }
        }
    }

    public void setValueLayout(){
        if(getLayout() != valueLayout){
            setValueandDraw(0);
            for(int i = 0; i < 9; i++) {
                remove(notes[i]);
            }
            revalidate();
            repaint();
            setLayout(valueLayout);
            add(textField);
        }
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
        if (locked) {textField.setForeground(new Color(100, 100, 100));}
        else {textField.setForeground(Color.BLACK);}
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
