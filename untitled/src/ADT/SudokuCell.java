package ADT;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class SudokuCell extends JTextField {
    Font sudokuCell = new Font("Arial", Font.BOLD, 20);
    private int cellInt;
    private boolean isLocked;
    private int[] position;

    public SudokuCell(){
        setValue(0);
        setLock(false);
        init();
    }

    public SudokuCell(int[] position){
        this.position = position;
        setValue(0);
        setLock(false);
        init();
    }

    public SudokuCell(int valInt, boolean lock){
        setValue(valInt);
        setLock(lock);
        init();
    }

    private void init(){
        setHorizontalAlignment(JTextField.CENTER);
        setFont(sudokuCell);
        drawBorder();
        setEditable(false); //Alle Felder sind nicht Editierbar, so dass der Benutzer keine Eingaben außer über den KeyListener machen kann

        //KeyListener für Eingaben der Felder die nicht gesperrt (=!isLocked) sind
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //irrelevant
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(!isLocked){
                    switch(e.getKeyCode()){
                        case KeyEvent.VK_1: setText("1"); break;
                        case KeyEvent.VK_2: setText("2"); break;
                        case KeyEvent.VK_3: setText("3"); break;
                        case KeyEvent.VK_4: setText("4"); break;
                        case KeyEvent.VK_5: setText("5"); break;
                        case KeyEvent.VK_6: setText("6"); break;
                        case KeyEvent.VK_7: setText("7"); break;
                        case KeyEvent.VK_8: setText("8"); break;
                        case KeyEvent.VK_9: setText("9"); break;
                        case KeyEvent.VK_BACK_SPACE:
                        case KeyEvent.VK_DELETE:
                            setText("");
                            e.consume(); //verhindert dass Windows einen Fehlersound bei Eingaben wie BackSpace abspieltbreak;
                            break;
                    }
                }
                else{
                    try{
                        String soundName = "Sudoku/untitled/sounds/wrong_input.wav";
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                        clip.start();
                    } catch(Exception ex){
                        System.out.println(ex.getMessage());
                    }

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //irrelevant
            }
        });
    }

    public void setValueandDraw(String value){
        cellInt = Integer.parseInt(value);
        drawValueOnGUI();
    }
    public void setValueandDraw(int value){
        cellInt = value;
        drawValueOnGUI();
    }

    public void setValue(int value){
        cellInt = value;
    }

    private void drawBorder(){

        //Um alle Sudokukästchen eine dünne graue Linie ziehen
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        if(position != null){
            //Für die dicken Linen in den Eckpunkten (3, 3); (6, 3); (3, 6); (6, 6);
            if((position[0] == 3 && position[1] == 3) || (position[0] == 6 && position[1] == 3) || (position[0] == 3 && position[1] == 6) || (position[0] == 6 && position[1] == 6)){
                setBorder(new CompoundBorder(BorderFactory.createMatteBorder(3, 3, 0, 0, Color.black),
                        BorderFactory.createMatteBorder(0, 0, 1, 1, Color.GRAY)));
            }
            //Für die dicken Linen der horizontalen Achse;
            else if(position[0] == 3 || position[0] == 6){
                setBorder(new CompoundBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, Color.black),
                        BorderFactory.createMatteBorder(0, 1, 1, 1, Color.GRAY)));
            }
            //Für die dicken Linen der vertikalen Achse;
            else if(position[1] == 3 || position[1] == 6){
                setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, Color.black),
                        BorderFactory.createMatteBorder(1, 0, 1, 1, Color.GRAY)));
            }
        }
    }
    public String getStringValue(){
        return String.valueOf(cellInt);
    }
    public int getIntValue(){
        return cellInt;
    }
    public void setLock(boolean L){
        isLocked = L;
        if(isLocked){
            setForeground(new Color(100, 100, 100));
        }
        else{
            setForeground(Color.BLACK);
        }

    }
    public boolean isLocked(){
        return isLocked;
    }
    public void drawValueOnGUI(){
        if(cellInt == 0){
            setText("");
        }
        else{
            setText(String.valueOf(cellInt));
        }

    }
    public void markwithColor(Color color){
        setBackground(color);
    }

    public void markDefault(){
        setBackground(Color.white);
    }

    public void updateValue(){
        if (getText().equals("")){
            cellInt = 0;
        } else {
            cellInt = Integer.parseInt(getText());
        }
    }

    public int[] getPosition(){
        return this.position;
    }
}
