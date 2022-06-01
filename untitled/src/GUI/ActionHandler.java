package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Var.buttonExit){
            System.exit(0);
        }
        else if(e.getSource() == Var.buttonNewGame){
            //Code hier für ein neues Game --> Machen wir verschiedene Schwierigkeiten?
        }
        else if(e.getSource() == Var.buttonResume){
            //Code hier
        }
        else if(e.getSource() == Var.buttonStr8ts){
            //Code hier
        }
    }
}
