package controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GameWin;

public class StartPanelListener implements ActionListener {
	public static GameMode gameMode = GameMode.getGameMode();
	public final static String NEWOPT = "new";
	public final static String LOADOPT = "load";
	public final static String EDITOPT = "edit";

	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();

		if (com.equals(NEWOPT)) {
			newOpt();
		} else if (com.equals(LOADOPT)) {
			loadOpt();
		} else if (com.equals(EDITOPT)) {
			editOpt();
		}

	}

	private void editOpt() {
		GameWin gameWin = gameMode.getGameWin();
		Container c = gameWin.getContentPane();

		// rimuovo gli eventuali altri pannelli presenti sulla finestra e
		// aggiungo quelli nuovi
		c.removeAll();
		c.add(gameMode.getInitMapPanel(), BorderLayout.CENTER);

		// ridisegno della finestra
		gameWin.repaint();
		gameWin.validate();

	}

	private void loadOpt() {
		gameMode.caricaPartita();
		
		//visualizzo la partita
		GameWin gameWin = gameMode.getGameWin();
		Container c = gameWin.getContentPane();

		// rimuovo gli eventuali altri pannelli presenti sulla finestra e
		// aggiungo quelli nuovi
		c.removeAll();
		c.add(gameMode.getMappaGrafica(), BorderLayout.CENTER);
		c.add(gameMode.getCommandPanel(), BorderLayout.EAST);

		// ridisegno della finestra
		gameWin.repaint();
		gameWin.validate();
	}

	private void newOpt() {
		GameWin gameWin = gameMode.getGameWin();
		Container c = gameWin.getContentPane();

		// rimuovo gli eventuali altri pannelli presenti sulla finestra e
		// aggiungo quelli nuovi
		c.removeAll();
		c.add(gameMode.getInitGame(), BorderLayout.CENTER);

		// ridisegno della finestra
		gameWin.repaint();
		gameWin.validate();

	}
}
