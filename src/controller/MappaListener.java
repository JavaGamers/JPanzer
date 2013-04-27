package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Esagono;

import view2.MappaGrafica;

public class MappaListener extends MouseAdapter implements ActionListener {
	
	public static final String UPOPT = "up";
	public static final String DOWNOPT = "down";
	public static final String LEFTOPT = "left";
	public static final String RIGHTOPT = "right";

	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();
		
		if(com.equals(UPOPT)){
			upOpt();
		}
		else if(com.equals(DOWNOPT)){
			downOpt();
		}
		else if(com.equals(LEFTOPT)){
			leftOpt();
		}
		else if(com.equals(RIGHTOPT)){
			leftOpt();
		}
	}
	
	public void mouseClicked(MouseEvent mE){
		double x = mE.getX();
		double y = mE.getY();
		// potenzialmente dannoso!!!
		MappaGrafica mG =(MappaGrafica) mE.getSource();
		Esagono e = mG.contains(x, y);
		// to be continued...
	}

	private void leftOpt() {
		// TODO Auto-generated method stub
		
	}

	private void downOpt() {
		// TODO Auto-generated method stub
		
	}

	private void upOpt() {
		// TODO Auto-generated method stub
		
	}

}
