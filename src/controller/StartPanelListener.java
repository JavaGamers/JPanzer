package controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import view.CommandPanel;
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

	//Metodo per accedere all'editor di mappa
	private void editOpt() {
		GameWin gameWin = gameMode.getGameWin();
		Container c = gameWin.getContentPane();

		/*
		 *  rimuovo gli eventuali altri pannelli presenti sulla finestra
		 * e aggiungo quelli nuovi
		 */
		c.removeAll();
		c.add(gameMode.getInitMapPanel(), BorderLayout.CENTER);

		gameWin.repaint();
		gameWin.validate();

	}

	//Metodo per caricare la partita
	private void loadOpt() {
		if (gameMode.caricaPartita()) {
			
			gameMode.setPlayingMode(true);

			// visualizzo la partita caricata
			GameWin gameWin = gameMode.getGameWin();
			gameWin.setResizable(true);
			gameWin.setExtendedState(JFrame.MAXIMIZED_BOTH);
			Container c = gameWin.getContentPane();

			/*
			 *  rimuovo gli eventuali altri pannelli presenti sulla finestra
			 * e aggiungo quelli nuovi
			 */
			c.removeAll();
			CommandPanel commandPanel = gameMode.getCommandPanel();
			commandPanel
					.setPlayerLabel(gameMode.getPlayer(gameMode.getTurno()));
			c.add(commandPanel, BorderLayout.EAST);
			JScrollPane jsp = new JScrollPane();
			jsp.setViewportView(gameMode.getMappaGrafica());
			jsp.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
			c.add(jsp, BorderLayout.CENTER);

			gameWin.repaint();
			gameWin.validate();
		}
	}

	//Metodo per iniziare una nuova partita
	private void newOpt() {
		GameWin gameWin = gameMode.getGameWin();
		Container c = gameWin.getContentPane();

		/*
		 *  rimuovo gli eventuali altri pannelli presenti sulla finestra
		 * e aggiungo quelli nuovi
		 */
		c.removeAll();
		c.add(gameMode.getInitGame(), BorderLayout.CENTER);

		gameWin.repaint();
		gameWin.validate();

	}
}
