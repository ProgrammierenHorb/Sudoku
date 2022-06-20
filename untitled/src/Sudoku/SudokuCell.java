package Sudoku;

import Controll.Cell;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class SudokuCell extends Cell {

    public SudokuCell(int[] position){
        super(position);
    }

    public SudokuCell(){
        super();
    }
}
