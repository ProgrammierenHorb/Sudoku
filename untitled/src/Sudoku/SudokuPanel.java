package Sudoku;

import Actions.*;
import Control.Control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuPanel extends JPanel {
    private final JPanel gridPanel;
    private final JPanel buttonPanel;
    private SudokuCell[][] grid;
    private SudokuCell currentSelectedCell;
    private String difficulty;
    private boolean helpActivated = true;
    private final JLabel timerLabel;
    private final JLabel clueCounterLabel;
    private final JLabel difficultyLabel;
    public int[] time = {0,0,0};


    public SudokuPanel(Control theControl) {
        //Referenz auf Controll-Instanz in Attributen speichern

        //Initialisierung von der Sudoku Oberfläche
        setFocusable(true);
        setBounds(0, 75, 900, 600);
        setLayout(null);
        setBackground(Color.white);

        //Erstellen des Sudoku Spielfelds
        gridPanel = new JPanel();
        gridPanel.setBounds(25, 50, 500, 500);
        gridPanel.setBackground(Color.GRAY);
        gridPanel.setLayout(new GridLayout(9, 9));
        gridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        //Erstellen des JPanels für die Knöpfe
        buttonPanel = new JPanel();
        buttonPanel.setBounds(575, 50, 300, 500);
        buttonPanel.setLayout(null);

        //Standardwert Schwierigkeit
        difficulty = "default";
        difficultyLabel = new JLabel("Difficulty: " + difficulty);
        difficultyLabel.setHorizontalAlignment(JLabel.RIGHT);
        difficultyLabel.setBounds(600, 555, 275, 25);
        difficultyLabel.setFont(new Font("Arial", Font.BOLD, 15) );
        add(difficultyLabel);

        initSudokuField();
        changeCurrentSelectedCellsWithMouse(grid);

        //Timer für Spielzeit erstellen
        timerLabel = new JLabel("Game time: 0h 0m 0s");
        timerLabel.setBounds(25, 555, 275, 25);
        timerLabel.setBackground(Color.WHITE);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 15) );
        ActionListener timerAction = e -> {
            if(!theControl.gewonnen) {
                time[0]++;
                if (time[0] % 60 == 0) {
                    time[0] = 0;
                    time[1]++;
                    if (time[1] % 60 == 0) {
                        time[1] = 0;
                        time[2]++;
                    }
                }
                timerLabel.setText("Game time: " + time[2] + "h " + time[1] + "m " + time[0] + "s");
            }else{
                timerLabel.setText("Game time: " + time[2] + "h " + time[1] + "m " + time[0] + "s");
            }
        };

        //Timer Intervall setzen, starten und auf Sudoku Oberfläche hinzufügen
        Timer timer = new Timer(1000, timerAction);
        timer.start();
        add(timerLabel);

        //Exit Button initialisieren inkl. Action Listener
        JButton buttonExit = new JButton("Exit");
        buttonExit.addActionListener(e -> {
            int reply = JOptionPane.showConfirmDialog(null, "Do you really want to leave the game?", "Exit Sudoku?", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        createButton(buttonExit, 10, 400);

        //Zählt, wie viele Hilfestellungen man verwendet hat
        clueCounterLabel = new JLabel("Used clues: 0");
        clueCounterLabel.setBounds(375, 555, 275, 25);
        clueCounterLabel.setBackground(Color.WHITE);
        clueCounterLabel.setFont(new Font("Arial", Font.BOLD, 15) );
        add(clueCounterLabel);

        //Clue Button initialisieren inkl. Action Listener
        JButton buttonClue = new JButton("Get Clue");
        buttonClue.addActionListener(e -> theControl.callgetClue(grid));
        createButton(buttonClue, 10, 275
        );

        //New Game Button initialisieren inkl. Action Listener
        JButton buttonNewGame = new JButton("New Game");
        buttonNewGame.addActionListener(e -> {

            theControl.callSudokuGenerator(grid, difficulty);
            theControl.gewonnen = false;
            clueCounterLabel.setText("Used clues: 0");

            time[0] = 0;
            time[1] = 0;
            time[2] = 0;

            setDifficultyLabel(difficulty);
        });
        createButton(buttonNewGame, 10, 25);

        //Check Button initialisieren inkl. Action Listener
        JButton buttonCheck = new JButton("Check");
        buttonCheck.addActionListener(e -> theControl.callSudokuInputCheck(grid));
        createButton(buttonCheck, 10, 150);


        theControl.callSudokuGenerator(grid, difficulty);

        //Panels auf Sudoku Oberfläche hinzufügen
        add(gridPanel);
        add(buttonPanel);

        //Key Bindings für Eingabe der Zahlen + Bewegung mit den Pfeiltasten
        initKeyBindings();

    }
    public void sethelpActivated(boolean bool){
        helpActivated = bool;
    }

    //Methode schreibt festen Wert (SHIFT+zahl) in das jeweilige Feld
    public void triggerWriteValue(int value) {
        if (currentSelectedCell != null && !currentSelectedCell.isLocked()) {
            currentSelectedCell.setValueLayout();
            currentSelectedCell.setValueandDraw(value);
            markCellKollision();
        }
    }

    //Methode hebt Kollisionen hervor
    public void markCellKollision(){
        int value = currentSelectedCell.getCellValue();
        boolean minonemarked = false;
        if(helpActivated && !currentSelectedCell.isLocked() && currentSelectedCell.getCellValue() != 0){ //nur markieren wenn Einstellung getätigt ist und die Zelle nicht gesperrt ist / bereits vom SudokuGenerator ausgefüllt wurde
            int[] currentPos = currentSelectedCell.getPosition();
            Color colormarkselected = new Color(255, 128, 128);
            for(int i=0; i<9;i++) {
                if(value == grid[currentPos[0]][i].getCellValue() && i != currentPos[1])
                {
                    grid[currentPos[0]][i].setTextColor(colormarkselected);
                    minonemarked = true;
                }
                if(value == grid[i][currentPos[1]].getCellValue() && i != currentPos[0])
                {
                    grid[i][currentPos[1]].setTextColor(colormarkselected);
                    minonemarked = true;
                }
                for (int j = 0; j < 9; j++) {
                    if ((i / 3) == (currentPos[0] / 3) && (j / 3) == (currentPos[1] / 3)) {
                        if (value == grid[i][j].getCellValue() && i != currentPos[0] && j != currentPos[1]){
                            grid[i][j].setTextColor(colormarkselected);
                            minonemarked = true;
                        }
                    }
                }
            }
            for(int i=0; i<9;i++) {
                for (int j = 0; j < 9; j++) {
                    if(value != grid[i][j].getCellValue())
                    {
                        grid[i][j].setTextColorDefault();
                    }
                }
            }
            if(minonemarked){
                currentSelectedCell.setTextColor(colormarkselected);
            }
            else{
                currentSelectedCell.setTextColorDefault();
            }
            revalidate();
            repaint();
        }
    }

    //Methode schreibt eingegebenen Wert als Notizu in das jeweilige Feld bzw. entfernt die Notizt bei wiederholtem drücken der Taste
    public void triggerWriteNote(int value) {
        if (currentSelectedCell != null && !currentSelectedCell.isLocked()) {
            currentSelectedCell.setNotesLayout();
            if (currentSelectedCell.getNotes()[value - 1].getText().equals("")) {
                currentSelectedCell.getNotes()[value - 1].setText(String.valueOf(value));
            } else {
                currentSelectedCell.getNotes()[value - 1].setText("");
            }
        }
    }

    //Setzt alle KeyBindings für Eingabe der Zahlen + Steuerung mit Pfeiltasten
    private void initKeyBindings(){
        InputMap im = getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();

        im.put(KeyStroke.getKeyStroke('1'), "write1");
        am.put("write1", new Type1Action(this, false));

        im.put(KeyStroke.getKeyStroke('1', InputEvent.SHIFT_DOWN_MASK), "write1shift");
        am.put("write1shift", new Type1Action(this, true));

        im.put(KeyStroke.getKeyStroke('2'), "write2");
        am.put("write2", new Type2Action(this, false));

        im.put(KeyStroke.getKeyStroke('2', InputEvent.SHIFT_DOWN_MASK), "write2shift");
        am.put("write2shift", new Type2Action(this, true));

        im.put(KeyStroke.getKeyStroke('3'), "write3");
        am.put("write3", new Type3Action(this, false));

        im.put(KeyStroke.getKeyStroke('3', InputEvent.SHIFT_DOWN_MASK), "write3shift");
        am.put("write3shift", new Type3Action(this, true));

        im.put(KeyStroke.getKeyStroke('4'), "write4");
        am.put("write4", new Type4Action(this, false));

        im.put(KeyStroke.getKeyStroke('4', InputEvent.SHIFT_DOWN_MASK), "write4shift");
        am.put("write4shift", new Type4Action(this, true));

        im.put(KeyStroke.getKeyStroke('5'), "write5");
        am.put("write5", new Type5Action(this, false));

        im.put(KeyStroke.getKeyStroke('5', InputEvent.SHIFT_DOWN_MASK), "write5shift");
        am.put("write5shift", new Type5Action(this, true));

        im.put(KeyStroke.getKeyStroke('6'), "write6");
        am.put("write6", new Type6Action(this, false));

        im.put(KeyStroke.getKeyStroke('6', InputEvent.SHIFT_DOWN_MASK), "write6shift");
        am.put("write6shift", new Type6Action(this, true));

        im.put(KeyStroke.getKeyStroke('7'), "write7");
        am.put("write7", new Type7Action(this, false));

        im.put(KeyStroke.getKeyStroke('7', InputEvent.SHIFT_DOWN_MASK), "write7shift");
        am.put("write7shift", new Type7Action(this, true));

        im.put(KeyStroke.getKeyStroke('8'), "write8");
        am.put("write8", new Type8Action(this, false));

        im.put(KeyStroke.getKeyStroke('8', InputEvent.SHIFT_DOWN_MASK), "write8shift");
        am.put("write8shift", new Type8Action(this, true));

        im.put(KeyStroke.getKeyStroke('9'), "write9");
        am.put("write9", new Type9Action(this, false));

        im.put(KeyStroke.getKeyStroke('9', InputEvent.SHIFT_DOWN_MASK), "write9shift");
        am.put("write9shift", new Type9Action(this, true));

        im.put(KeyStroke.getKeyStroke("BACK_SPACE"), "delete");
        am.put("delete", new DeleteValueAction(this));

        im.put(KeyStroke.getKeyStroke("UP"), "moveUP");
        am.put("moveUP", new ArrowUPAction(this));

        im.put(KeyStroke.getKeyStroke("DOWN"), "moveDOWN");
        am.put("moveDOWN", new ArrowDOWNAction(this));

        im.put(KeyStroke.getKeyStroke("LEFT"), "moveLEFT");
        am.put("moveLEFT", new ArrowLEFTAction(this));

        im.put(KeyStroke.getKeyStroke("RIGHT"), "moveRIGHT");
        am.put("moveRIGHT", new ArrowRIGHTAction(this));

    }

    //Ändert das ausgewählte Sudoku Feld mit Pfeiltasten
    public void changeCurrentSelectedCellWithArrowKeys(String direction) {
        if (currentSelectedCell != null) {
            switch (direction) {
                case "UP":
                    if (currentSelectedCell.getPosition()[0] > 0) {
                        currentSelectedCell = grid[currentSelectedCell.getPosition()[0] - 1][currentSelectedCell.getPosition()[1]];
                        markCellsSelected(new int[]{currentSelectedCell.getPosition()[0], currentSelectedCell.getPosition()[1]});
                    }
                    break;
                case "DOWN":
                    if (currentSelectedCell.getPosition()[0] < 8) {
                        currentSelectedCell = grid[currentSelectedCell.getPosition()[0] + 1][currentSelectedCell.getPosition()[1]];
                        markCellsSelected(new int[]{currentSelectedCell.getPosition()[0], currentSelectedCell.getPosition()[1]});
                    }
                    break;
                case "RIGHT":
                    if (currentSelectedCell.getPosition()[1] < 8) {
                        currentSelectedCell = grid[currentSelectedCell.getPosition()[0]][currentSelectedCell.getPosition()[1] + 1];
                        markCellsSelected(new int[]{currentSelectedCell.getPosition()[0], currentSelectedCell.getPosition()[1]});
                    }
                    break;
                case "LEFT":
                    if (currentSelectedCell.getPosition()[1] > 0) {
                        currentSelectedCell = grid[currentSelectedCell.getPosition()[0]][currentSelectedCell.getPosition()[1] - 1];
                        markCellsSelected(new int[]{currentSelectedCell.getPosition()[0], currentSelectedCell.getPosition()[1]});
                    }
                    break;
            }
            markCellKollision();
        }
    }

    //Entwernt alle Notizen aus einem Sudokufeld
    public void removeNotes() {
        for (int i = 0; i < 9; i++) {
            currentSelectedCell.getNotes()[i].setText("");
        }
    }

    private void initSudokuField() {

        grid = new SudokuCell[9][9];

        //Füllt das Sudokufeld Array mit SudokuCell Objekten
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = new SudokuCell(new int[]{i, j});
                gridPanel.add(grid[i][j]);
            }
        }
    }

    public void setClueCount(int clues){
        clueCounterLabel.setText("Used clues: " + clues);
    }

    //Markiert alle Zellen, die in der gleichen Zeile, Spalte oder 3x3 Quadrat wie die gegebene Position sind
    private void markCellsSelected(int[] position) {
        Color colormarkselected = new Color(215, 255, 255);

        int posY = position[0];
        int posX = position[1];

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if(helpActivated) {
                        if (i == posY) { //Markiert Reihe
                            grid[i][j].markWithColor(colormarkselected);
                            grid[i][j].setTextColorDefault();
                        } else if (j == posX) { //Markiert Spalte
                            grid[i][j].markWithColor(colormarkselected);
                            grid[i][j].setTextColorDefault();
                        } else if ((i / 3) == (posY / 3) && (j / 3) == (posX / 3)) { //Markiert Box
                            grid[i][j].markWithColor(colormarkselected);
                            grid[i][j].setTextColorDefault();
                        } else {
                            grid[i][j].markDefault();
                            grid[i][j].setTextColorDefault();
                        }
                    }
                    else{
                        grid[i][j].markDefault();
                        grid[i][j].setTextColorDefault();
                    }
                }
            }
        grid[posY][posX].markWithColor(new Color(140, 236, 239)); //Markiert angeklicktes Feld in einer etwas anderen Farbe
    }


    private void changeCurrentSelectedCellsWithMouse(SudokuCell[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int finalI = i;
                int finalJ = j;
                grid[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        currentSelectedCell = grid[finalI][finalJ];
                        markCellsSelected(new int[]{finalI, finalJ});
                        markCellKollision();
                    }
                });
            }
        }
    }

    //Kosmetische Änderung der Buttons + Zuweisung der Listeners
    public void createButton(JButton button, int x, int y) {
        setFocusable(false);
        button.setBounds(x, y, 275, 75);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 40));
        button.setBorder(null);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                button.setBorder(BorderFactory.createLineBorder(Color.orange, 3));
            }

            public void mouseExited(MouseEvent evt) {
                button.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            }
        });

        button.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                button.setBorder(BorderFactory.createLineBorder(Color.orange, 3));
            }

            @Override
            public void focusLost(FocusEvent e) {
                button.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            }
        });
        buttonPanel.add(button);
    }

    public void setDifficulty(String difficulty){
        this.difficulty = difficulty;
    }

    public void setDifficultyLabel(String difficulty){
        difficultyLabel.setText("Difficulty: " + difficulty);
    }


}

