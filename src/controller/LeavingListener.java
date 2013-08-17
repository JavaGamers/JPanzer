package controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GameWin;
import view.InitGame;

public class LeavingListener implements ActionListener {
	
	private GameMode gameMode = GameMode.getGameMode();
	
	public final static String SIOPT = "si";
	public final static String NOOPT = "no";
	public final static String ANNULLAOPT = "annulla";
	
	
	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();
		
		if(com.equals(SIOPT)){
			siOpt();
		}
		else if(com.equals(NOOPT)){
			noOpt();
		}
		else if(com.equals(ANNULLAOPT)){
			annullaOpt();
		}
	}


	private void annullaOpt() {
		gameMode.getLeavingWin().setVisible(false);
		gameMode.getCommandPanel().enableAll();
		
	}


	private void noOpt() {
		
		GameWin gameWin = gameMode.getGameWin();
		Container c = gameWin.getContentPane();
		
		gameMode.getLeavingWin().setVisible(false);
		
		// rimuovo gli eventuali altri pannelli presenti sulla finestra e aggiungo quelli nuovi
		c.removeAll();
		c.add(gameMode.getInitGame(),BorderLayout.CENTER);
		gameWin.repaint();
		gameWin.validate();

	}


	private void siOpt() {
		gameMode.salvaPartita();
		
		GameWin gameWin = gameMode.getGameWin();
		Container c = gameWin.getContentPane();
		
		gameMode.getLeavingWin().setVisible(false);
		
		// rimuovo gli eventuali altri pannelli presenti sulla finestra e aggiungo quelli nuovi
		c.removeAll();
		c.add(gameMode.getInitGame(),BorderLayout.CENTER);
		gameWin.repaint();
		gameWin.validate();
	}
}
