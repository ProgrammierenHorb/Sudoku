import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GUI {
    public GUI(){
        Var.frame = new JFrame("Sudoku");
        Var.frame.setResizable(false);
        Var.frame.setSize(600, 700);
        Var.frame.setLocationRelativeTo(null);
        Var.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Var.frame.setBackground(Color.white);

        Var.gridPanel = new JPanel();
        Var.gridPanel.setBounds(20, 20, 100, 100);
        Var.gridPanel.setBackground(Color.GRAY);
        Var.gridPanel.setLayout(new GridLayout(9, 9));

        int i = 0;
        int j = 0;
        int testCounter = 0;
        do{
            System.out.println(i + "/" + j);
            if(j == 8 && i == 8){
                break;
            }
            else if(i == 8){
                Var.grid[i][j] = new JTextField("Test");
                CreateTextBox(Var.grid[i][j]);
                j++;
                i = 0;
            }
            else if(i == 0 && j != 0){
                Var.grid[i][j] = new JTextField("Test");
                CreateTextBox(Var.grid[i][j]);
                i++;
            }else{
                Var.grid[i][j] = new JTextField("Test");
                CreateTextBox(Var.grid[i][j]);
                i++;
            }
            testCounter++;
        }
        while(true);
        System.out.println(testCounter);
        Var.frame.add(Var.gridPanel);
        Var.frame.setVisible(true);
    }
    public void CreateTextBox(JTextField textField){
        Var.gridPanel.add(textField);
    }
}
