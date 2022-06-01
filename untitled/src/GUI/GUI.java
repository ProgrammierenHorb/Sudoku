package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import ADT.SudokuCell;

public class GUI {
    DrawSudokuField draw;
    public GUI(){
        Var.frame = new JFrame("Sudoku");
        Var.frame.setResizable(false);
        Var.frame.setSize(900, 600);
        Var.frame.setLocationRelativeTo(null);
        Var.frame.setLayout(null);
        Var.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Var.frame.setBackground(Color.white);

        Var.gridPanel = new JPanel();
        Var.gridPanel.setBounds(25, 25, 500, 500);
        Var.gridPanel.setBackground(Color.GRAY);
        Var.gridPanel.setLayout(new GridLayout(9, 9));

        Var.buttonPanel = new JPanel();
        Var.buttonPanel.setBounds(575, 25, 300, 500);
        Var.buttonPanel.setBackground(Color.GRAY);
        Var.buttonPanel.setLayout(null);

        Var.drawPanel = new JPanel();
        Var.drawPanel.setBounds(25, 25, 500, 500);
        Var.drawPanel.setBackground(Color.GRAY);
        Var.drawPanel.setLayout(null);

        Var.buttonExit = new JButton("Exit");
        CreateButton(Var.buttonExit, 10, 400);

        Var.buttonResume = new JButton("Resume");
        CreateButton(Var.buttonResume, 10, 275);

        Var.buttonNewGame = new JButton("New Game");
        CreateButton(Var.buttonNewGame, 10, 150);

        Var.buttonStr8ts = new JButton("Stra8ts");
        CreateButton(Var.buttonStr8ts, 10, 25);


        int i = 0;
        int j = 0;
        do{
            if(j == 8 && i == 8){
                Var.grid[i][j] = new JTextField(i + "/" + j);
                CreateTextBox(Var.grid[i][j]);
                break;
            }
            else if(i == 8){
                Var.grid[i][j] = new JTextField(i + "/" + j);
                CreateTextBox(Var.grid[i][j]);
                j++;
                i = 0;
            }
            else if(i == 0 && j != 0){
                Var.grid[i][j] = new JTextField(i + "/" + j);
                CreateTextBox(Var.grid[i][j]);
                i++;
            }else{
                Var.grid[i][j] = new JTextField(i + "/" + j);
                CreateTextBox(Var.grid[i][j]);
                i++;
            }
        }
        while(true);

        draw = new DrawSudokuField();
        draw.setBounds(0, 0, 500, 500);
        draw.setVisible(true);
        //GUI.Var.drawPanel.add(draw);

        //GUI.Var.frame.add(GUI.Var.gridPanel);
        Var.frame.add(Var.buttonPanel);
        //GUI.Var.frame.add(GUI.Var.drawPanel);
        Var.frame.add(draw);
        Var.frame.setVisible(true);
    }
    public void CreateTextBox(JTextField textField){
        textField.setBorder(null);
        Var.gridPanel.add(textField);
    }

    public void CreateButton(JButton button, int x, int y){
        button.setBounds(x, y, 275, 75);
        button.setBackground(Color.GRAY);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 40));
        button.setBorder(null);
        button.setFocusPainted(false);
        button.addActionListener(new ActionHandler());
        JButton finalButton = button;
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                finalButton.setBackground(Color.CYAN);
                finalButton.setForeground(Color.BLACK);
            }
            public void mouseExited(MouseEvent evt) {
                finalButton.setBackground(Color.GRAY);
                finalButton.setForeground(Color.BLACK);
            }
        });
        Var.buttonPanel.add(button);
    }
}
