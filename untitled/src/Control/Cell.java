package Control;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public abstract class Cell extends JPanel {
    protected JLabel[] notes;
    protected int cellValue;
    protected JLabel textField;
    protected int[] position;
    protected boolean locked;
    protected Font cellFont;
    protected Font notesFont;

    private GridLayout notesLayout = new GridLayout(3, 3);
    private GridBagLayout valueLayout = new GridBagLayout();

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
        add(textField);
        cellFont = new Font("Arial", Font.BOLD, 20);
        notesFont = new Font("Arial", Font.BOLD, 12);
        textField.setFont(cellFont);
        drawBorder();
    }

    public void initNotes(){
            notes = new JLabel[9];
            for (int i = 0; i < 9; i++) {
                notes[i] = new JLabel();
                notes[i].setText("");
                notes[i].setHorizontalAlignment(JLabel.CENTER);
                notes[i].setVerticalAlignment(JLabel.CENTER);
                notes[i].setFont(notesFont);
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
                setBorder(new CompoundBorder(BorderFactory.createMatteBorder(2, 2, 0, 0, Color.black),
                        BorderFactory.createMatteBorder(0, 0, 1, 1, Color.GRAY)));
            }
            else if((position[0] == 2 && position[1] == 2) || (position[0] == 5 && position[1] == 2) || (position[0] == 2 && position[1] == 5) || (position[0] == 5 && position[1] == 5)) {
                setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 2, 2, Color.black),
                        BorderFactory.createMatteBorder(1, 1, 0, 0, Color.GRAY)));
            }
            else if((position[0] == 3 && position[1] == 2) || (position[0] == 6 && position[1] == 2) || (position[0] == 3 && position[1] == 5) || (position[0] == 6 && position[1] == 5)) {
                setBorder(new CompoundBorder(BorderFactory.createMatteBorder(2, 0, 0, 2, Color.black),
                        BorderFactory.createMatteBorder(0, 1, 1, 0, Color.GRAY)));
            }
            else if((position[0] == 2 && position[1] == 3) || (position[0] == 5 && position[1] == 3) || (position[0] == 2 && position[1] == 6) || (position[0] == 5 && position[1] == 6)) {
                setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0, 2, 2, 0, Color.black),
                        BorderFactory.createMatteBorder(1, 0, 0, 1, Color.GRAY)));
            }


            //Für die dicken Linen der horizontalen Achse;
            else if(position[0] == 2 || position[0] == 5){
                setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black),
                        BorderFactory.createMatteBorder(1, 1, 0, 1, Color.GRAY)));
            }
            else if (position[0] == 3 || position[0] == 6) {
                setBorder(new CompoundBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.black),
                        BorderFactory.createMatteBorder(0, 1, 1, 1, Color.GRAY)));
            }

            //Für die dicken Linen der vertikalen Achse;
            else if (position[1] == 3 || position[1] == 6) {
                setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Color.black),
                        BorderFactory.createMatteBorder(1, 0, 1, 1, Color.GRAY)));
            }
            else if(position[1] == 2 || position[1] == 5){
                setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.black),
                        BorderFactory.createMatteBorder(1, 1, 1, 0, Color.GRAY)));
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
    public void setTextColor(Color color){
        textField.setForeground(color);
    }
    public void setTextColorDefault(){
        if(isLocked()){
            textField.setForeground(new Color(100, 100, 100));
        }
        else{
            textField.setForeground(Color.black);
        }
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

    public JLabel[] getNotes(){
        return notes;
    }

}
