package controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GameWin;

public class LeavingListener implements ActionListener {

	private GameMode gameMode = GameMode.getGameMode();

	public final static String SIOPT = "si";
	public final static String NOOPT = "no";
	public final static String ANNULLAOPT = "annulla";

	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();

		if (com.equals(SIOPT)) {
			siOpt();
		} else if (com.equals(NOOPT)) {
			noOpt();
		} else if (com.equals(ANNULLAOPT)) {
			annullaOpt();
		}
	}

	private void annullaOpt() {
		if (gameMode.isPlayingMode()) {
			gameMode.getLeavingWin().setVisible(false);
			gameMode.getCommandPanel().enableAll();
		} else {
			gameMode.getLeavingWin().setVisible(false);
			gameMode.getLandPanel().enableAll();
		}

	}

	private void noOpt() {

		GameWin gameWin = gameMode.getGameWin();
		Container c = gameWin.getContentPane();

		gameMode.getLeavingWin().setVisible(false);

		// rimuovo gli eventuali altri pannelli presenti sulla finestra e
		// aggiungo quelli nuovi
		c.removeAll();
		gameMode.getLandPanel().enableAll();
		gameMode.resetAll();
		c.add(gameMode.getStartPanel(), BorderLayout.CENTER);
		gameWin.repaint();
		gameWin.validate();
	}

	private void siOpt() {
		if (gameMode.isPlayingMode()) {
			if (gameMode.salvaPartita()) {

				GameWin gameWin = gameMode.getGameWin();
				Container c = gameWin.getContentPane();

				gameMode.getLeavingWin().setVisible(false);

				// rimuovo gli eventuali altri pannelli presenti sulla finestra
				// e
				// aggiungo quelli nuovi
				c.removeAll();
				gameMode.getLandPanel().enableAll();
				gameMode.resetAll();
				c.add(gameMode.getStartPanel(), BorderLayout.CENTER);
				gameWin.repaint();
				gameWin.validate();
			}
		} else {
			if (gameMode.salvaMappa()) {

				GameWin gameWin = gameMode.getGameWin();
				Container c = gameWin.getContentPane();

				gameMode.getLeavingWin().setVisible(false);

				// rimuovo gli eventuali altri pannelli presenti sulla finestra
				// e
				// aggiungo quelli nuovi
				c.removeAll();
				gameMode.getLandPanel().enableAll();
				gameMode.resetAll();
				c.add(gameMode.getStartPanel(), BorderLayout.CENTER);
				gameWin.repaint();
				gameWin.validate();
			}

		}
	}
}
