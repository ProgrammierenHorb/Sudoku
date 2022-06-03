package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener {
    JButton buttonExit;
    JButton buttonNewGame;
    JButton buttonStr8ts;
    JButton buttonResume;

    public ActionHandler(JButton buttonExit,JButton buttonNewGame, JButton buttonStr8ts,JButton buttonResume) {
        this.buttonExit = buttonExit;
        this.buttonNewGame = buttonNewGame;
        this.buttonResume = buttonResume;
        this.buttonStr8ts = buttonStr8ts;
    }
        @Override
        public void actionPerformed (ActionEvent e){
            if (e.getSource() == buttonExit) {
                System.exit(0);
            } else if (e.getSource() == buttonNewGame) {
                //Code hier für ein neues Game --> Machen wir verschiedene Schwierigkeiten?
            } else if (e.getSource() == buttonResume) {
                //Code hier
            } else if (e.getSource() == buttonStr8ts) {
                //Code hier
            }
        }
}
